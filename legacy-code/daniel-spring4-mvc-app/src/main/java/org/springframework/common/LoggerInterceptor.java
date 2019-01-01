package org.springframework.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
	protected Log log = LogFactory.getLog(LoggerInterceptor.class);

	/***
	 * Interceptor 전처리
	 */
	@Override
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		/**
		 * log4j.xml logger 설정 참고
		 * logger 설정에 따라 info, debug 등이 true/false로 분기 처리됨.
		 * 설정값에 없는 info,debug 등은 출력이 되지 않음.
		 */
		if(log.isInfoEnabled()){
			log.info("======================================          START         ======================================");
			log.info("Request URI : [" + request.getRequestURI() + "]");			
		}
		
		else if (log.isDebugEnabled()) {
			log.debug("======================================          START         ======================================");
			log.debug("Request URI : [" + request.getRequestURI() + "]");
		}

		// TODO Auto-generated method stub
		return super.preHandle(request, response, handler);
	}

	/**
	 * Interceptor 후처리
	 */
	@Override
	public void postHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if(log.isInfoEnabled()){
            log.info("======================================           END          ======================================\n");			
		}
		
		else if (log.isDebugEnabled()) {
            log.debug("======================================           END          ======================================\n");
        }		
		// TODO Auto-generated method stub
		//super.postHandle(request, response, handler, modelAndView);
	}

}
