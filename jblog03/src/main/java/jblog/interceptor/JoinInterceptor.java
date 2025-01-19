package jblog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JoinInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/* GET 요청의 경우 => Pass */
		if("GET".equalsIgnoreCase(request.getMethod())) {
			System.out.println("GET 요청이므로 패스==========");
			return true;
		}
		
		/* POST 요청의 경우 */
		String btnCheckemail = request.getParameter("btnCheckId");
		String agreeProv = request.getParameter("agreeProv");
		System.out.println("btnCheckId : " + btnCheckemail + ", agreeProv : " + agreeProv);
		
//		// id 중복체크 또는 약관동의 하지 않은 경우
//		if(!"true".equals(btnCheckemail) || !"true".equals(agreeProv)) {
//			System.out.println("POST 요청이지만 동의 안했으므로 패스==========");
//			request.setAttribute("name", request.getAttribute("name"));
//			request.setAttribute("id", request.getAttribute("id"));
//			request.setAttribute("password", request.getAttribute("password"));
//			
//			request
//				.getRequestDispatcher("/WEB-INF/views/user/join.jsp")
//				.forward(request, response);
//			
//			return false;
//		}

		System.out.println("POST 요청이고 동의했으므로 컨트롤러 ==========");
		return true;
	}
	
	

}
