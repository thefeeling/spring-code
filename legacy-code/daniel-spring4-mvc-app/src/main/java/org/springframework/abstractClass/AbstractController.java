package org.springframework.abstractClass;

import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;


import org.springframework.common.CommandMap;
import org.springframework.context.MessageSource;

public class AbstractController {
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name = "messageSource")
	private MessageSource messageSource;	
	
	/**
	 * Exception 발생없이 후처리로직 실행을 위한 메소드 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 */	
	protected String getMessage(String msgKey) {
		return getMessage(msgKey, new String[] {});
	}
	/**
	 * Exception 발생없이 후처리로직 실행을 위한 메소드 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들 
	 */
	protected String getMessage(String msgKey, String[] msgArgs) {
		return getMessage(msgKey, msgArgs, null);
	}
	/**
	 * Exception 발생없이 후처리로직 실행을 위한 메소드 
	 * @param msgKey 메세지리소스에서 제공되는 메세지의 키값
	 * @param msgArgs msgKey의 메세지에서 변수에 취환되는 값들 
	 * @param locale 명시적 국가/언어지정 
	 */
	protected String getMessage(String msgKey, String[] msgArgs, Locale locale) {
		return messageSource.getMessage(msgKey, msgArgs, null, locale);
	}		
	
	/**
	 * CommandMap 객체로 맵핑 되는 값 확인
	 * @param request     by Controller
	 * @param commandMap  by Controller
	 */
	public void commandMapProc(HttpServletRequest request, CommandMap commandMap) {
		try {
			log.info("Http Method => " + request.getMethod());
			if(commandMap.isEmpty() == false){
				Iterator<Entry<String,Object>> iterator = commandMap.getMap().entrySet().iterator();
				Entry<String,Object> entry = null;
				while(iterator.hasNext()){
					entry = iterator.next();
					log.info("key : "+entry.getKey()+", value : "+entry.getValue());
				}
			}else{
				log.info("------------------[DanielApp] No Parameter---------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
