package com.addr;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ch.qos.logback.classic.Logger;

@RestController
public class AddrController {
	
	private final Logger log = (Logger) org.slf4j.LoggerFactory.getLogger(AddrController.class);
	
	/**
	 * CommonHttpRequest + DocumentBuilder XML Parse
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/http", method = RequestMethod.GET)
	public Result<Address> httpGet(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> map
			) throws Exception {
		ParseService parseService = new XmlParseServiceImpl();
		Response<Address> context = new Response<Address>();
		return context.getResponse(parseService, map);
	}

	
	/**
	 * RestTemplate + DocumentBuilder XML Parse
	 * @param request
	 * @param response
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rest", method = RequestMethod.GET)
	public Result<Address> restTpl(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Map<String, Object> map
			) throws Exception{
		ParseService parseService = new RestTemplateServiceImpl();
		Response<Address> context = new Response<Address>();
		return context.getResponse(parseService, map);
	}
}
