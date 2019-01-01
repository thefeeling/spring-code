package org.springframework.app.blogApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.webjars.WebJarAssetLocator;

@Controller
public class AssetController {
	
	
	/**
	 * @Comment Webjar Static Resource Controller
	 * @author kschoi
	 */
	@ResponseBody
	@RequestMapping("/webjarslocator/{webjar}/**")
	public ResponseEntity<String> locateWebjarAsset(
			@PathVariable String webjar, 
			HttpServletRequest request,
			HttpServletResponse response) {
    	WebJarAssetLocator assetLocator = new WebJarAssetLocator();
		try {
	    	String mvcPrefix = "/webjarslocator/" + webjar + "/";
	    	String mvcPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
	        String fullPath = assetLocator.getFullPath(webjar, mvcPath.substring(mvcPrefix.length()));
	        return new ResponseEntity(new ClassPathResource(fullPath), HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
