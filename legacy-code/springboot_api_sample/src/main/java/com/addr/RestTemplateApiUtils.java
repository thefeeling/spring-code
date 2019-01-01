package com.addr;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestTemplateApiUtils {
	
	RestTemplate restTpl = new RestTemplate();
	
	/**
	 * Simple RestTemplate Example(Get)
	 * 
	 * @param baseUrl      도메인
	 * @param currentPage  요청 페이지
	 * @param rowsPerPage  게시물 수
	 */
	public void sendGet(String baseUrl, int currentPage, int rowsPerPage){
		String reqUrl = baseUrl + "?authKey={authKey}&currentPage={currentPage}&rowsPerPage={rowsPerPage}";
		Map<String, Object> params = new HashMap<>();
		params.put("authKey", "clientAuthKey");
		params.put("currentPage", currentPage);
		params.put("rowsPerPage", rowsPerPage);
		restTpl.getForObject(reqUrl, String.class, params);
	}
	

	/**
	 * Simple RestTemplate Example(Post) + Headers + Body
	 * 
	 * @param reqUrl   요청 URL(POST)
	 * @param insertId 입력값(ID)
	 * @param content  입력값(내용)
	 */
	public void sendPost(String reqUrl, int insertId, String content){
		String body = "";
		HttpHeaders headers = null;
		HttpEntity entity = null;
		
		Map<String, Object> params = new HashMap<>();
		params.put("authKey"  , "clientAuthKey");
		params.put("insertId" , insertId);
		params.put("content"  , content);		
		
		try {
			body = new ObjectMapper().writeValueAsString(params);
			headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
			entity = new HttpEntity<>(body, headers);
			restTpl.postForEntity(reqUrl, entity, String.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
