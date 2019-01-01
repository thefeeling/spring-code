package com.adminweb.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminRestController {
	
	@Autowired
	AdminService adminService;
	
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	String index(){
		int test = adminService.txTest();
		System.out.println("Hello");
		System.out.println("Hello2");
		return adminService.test();
	}
}
