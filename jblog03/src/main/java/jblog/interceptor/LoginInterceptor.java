package jblog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jblog.service.UserService;
import jblog.vo.UserVo;

public class LoginInterceptor implements HandlerInterceptor {
	private UserService userService;
	
	public LoginInterceptor(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/* GET 요청의 경우 => Pass */
		if("GET".equalsIgnoreCase(request.getMethod())) {
			return true;
		}
		
		/* POST 요청의 경우 */
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// 존재하는 회원인지 찾기
		UserVo userVo = userService.getUser(id, password);
		if(userVo == null) {
			request.setAttribute("id", id);
			request
				.getRequestDispatcher("/WEB-INF/views/user/login.jsp")
				.forward(request, response);
			
			return false;
		}
		
		// login 처리
		HttpSession session = request.getSession();
		session.setAttribute("user", userVo);
		response.sendRedirect(request.getContextPath());
		
		return false;
	}
	
	

}
