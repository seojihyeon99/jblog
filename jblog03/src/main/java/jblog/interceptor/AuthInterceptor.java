package jblog.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//1. Handler 종류 확인
		if(!(handler instanceof HandlerMethod)) {
			// DefaultServletRequestHandler 타입인 경우
			// DefaultServletHandler가 처리하는 경우(정적자원, /assets/**, mapping이 안되어 있는 URL)
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Handler에서 @Auth 가져오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		
		//4. Handler Method에서 @Auth가 없으면 클래스(타입)에서 가져오기
		if(auth == null) {
			auth = handlerMethod
					.getMethod()
					.getDeclaringClass()
					.getAnnotation(Auth.class);
		}
		
		//5. type 이나 method에 @Auth가 없는 경우
		if(auth == null) {
			return true;
		}
		
		//6. @Auth가 붙어 있기 때문에 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("user");
		
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		//7. 블로그 주인과 같은지 비교
		String userId = authUser.getId();
		String blogId = request.getRequestURI().split("/")[2];
		if(!userId.equals(blogId)) {
			response.sendRedirect(request.getContextPath() + "/" + blogId);
			return false;
		}
		
		//8. 옳은 권한 [인증 && 로그인유저 == 블로그 주인] 
		return true;
	}

}
