package com.adminweb.config;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @comment
 * - @ControllerAdvice
 *   : 	Controller를 보조하는 어노테이션으로 Controller에서 쓰이는 공통기능들을 
 *   	모듈화하여 전역으로 쓰기 위한 어노테이션(egov 3.0, Spring 3.2.X부터 추가)
 * @author kschoi
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler{
	@ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
	void handleBadRequests(HttpServletResponse response) throws IOException {
	    response.sendError(HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler({Exception.class})
	void handleBadSqlRequest(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}
}
