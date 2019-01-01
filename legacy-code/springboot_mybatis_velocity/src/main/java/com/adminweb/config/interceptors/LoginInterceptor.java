package com.adminweb.config.interceptors;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.adminweb.model.domain.AdminUser;

/**
 * @Title   Admin Page Login Interceptor
 * @Comment
 * -----------------------------------------------------------------------------------------------------------
 * [Date]				[Modifier]				[Comment]
 * 2016.05.24			kschoi					LoginInterceptor 추가 작업 진행(최초 추가)							
 * -----------------------------------------------------------------------------------------------------------
 * @Author kschoi
 * @AnotherComment
 * 1. preHandle       - controller 이벤트 호출전
 * 2. postHandle      - controller 호출 후 view 페이지 출력전
 * 3. afterCompletion - controller + view 페이지 모두 출력 후
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	private final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	
	private final String SESSION_KEY = "admin";
	
	
	@Override
	public boolean preHandle(
			HttpServletRequest  request, 
			HttpServletResponse response, 
			Object handler) throws Exception {
		
		HttpSession session = request.getSession();	
		AdminUser   user    = (AdminUser) session.getAttribute(SESSION_KEY);
		boolean preHandleYn = true;
		System.out.println("request URI => " +request.getRequestURI());
		System.out.println("request URL => " +request.getRequestURL());
		
		if (Pattern.matches("\\/admin\\/(.*?)", request.getRequestURI())) {
			boolean enterURIYn = request.getRequestURI().equals("/admin/login");
			boolean adminSessionYn = (user==null);
			
			// Login 페이지를 제외한 나머지 페이지 접근 시
			// 세션 만료의 경우.
			if (!enterURIYn && adminSessionYn) {
				log.info("session is expired or non valid by kschoi");
				response.sendRedirect("/admin/login");
				preHandleYn = false;
			}

			// 세션을 가지고 있는 유저가 로그인 페이지 접근 시
			else if (enterURIYn && !adminSessionYn) {
				log.debug("[LoginInterceptor.class] Session valid User");
				response.sendRedirect("/admin/index");
				preHandleYn = false;
			}
		}
		return preHandleYn;
	}

	@Override
	public void postHandle(
			HttpServletRequest  request, 
			HttpServletResponse response, 
			Object handler, 
			ModelAndView mav)
			throws Exception {
	}

	@Override
	public void afterCompletion(
			HttpServletRequest  request, 
			HttpServletResponse response, 
			Object handler, 
			Exception ex)
			throws Exception {
	}
}
