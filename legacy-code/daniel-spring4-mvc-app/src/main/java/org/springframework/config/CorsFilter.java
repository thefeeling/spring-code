package org.springframework.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter {

	/**
	 * CORS Filter Add(2015-12-01)
	 * @Comment 2016-02-01 Access-Controler-Allow-Headers 내용 추가
	 * @author kschoi
	 */
	@Override
	protected void doFilterInternal(
			HttpServletRequest  request, 
			HttpServletResponse response, 
			FilterChain         filterChain)
			throws ServletException, IOException {
		// * => All Permit
		response.addHeader("Access-Control-Allow-Origin", "*");
		if ( request.getHeader("Access-Control-Request-Method") != null && 	"OPTIONS".equals(request.getMethod()) ){
			response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			//response.addHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
			response.addHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Authorization, X-Requested-With");
			response.addHeader("Access-Control-Max-Age", "1728000");
		}
		filterChain.doFilter(request, response);
	}
}
