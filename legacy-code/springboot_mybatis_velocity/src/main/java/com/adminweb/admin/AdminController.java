
package com.adminweb.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.adminweb.model.domain.AdminUser;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
	
	private final Logger log = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	String init(Model model){
		adminService.txTest();
		return "index";
	}

	@RequestMapping(value="/main", method=RequestMethod.GET)
	String adminMain(Model model){
		String viewName = "admin/main";
		model.addAttribute("HelloWorld", "안녕하세요");
		return viewName;
	}	

	@RequestMapping(value="/index", method=RequestMethod.GET)
	String adminIndex(HttpServletRequest request, HttpServletResponse response, Model model){
		String viewName = "admin/index";
		model.addAttribute("HelloWorld", "안녕하세요");
		
		return viewName;
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	String adminAuth(HttpServletRequest request, HttpServletResponse response, Model model){
		String viewName = "admin/login";
		HttpSession session = request.getSession();		
		AdminUser user = (AdminUser) session.getAttribute("admin");
		
		if (user == null) {
			log.debug("[/login] adminUserSession is null");
		}else{
			log.debug("[/login] adminUserSession is not null");
		}
		
		//Cookie[] cookies = request.getCookies();
		//for (Cookie cookie : cookies) {
		//	System.out.println(cookie);
		//}
		
		model.addAttribute("HelloWorld", "안녕하세요");
		return viewName;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	ModelAndView adminAuthProc(AdminUser adminUser, HttpServletRequest request, HttpServletResponse response){
		log.info("---------login // post---------------");
		HttpSession session = request.getSession();
		adminUser.setEmail("ssingame@nate.com");
		session.setAttribute("admin", adminUser);
		
		
		// response.addCookie(new Cookie("AD_KEY","t12346875856756"));
		
		return new ModelAndView("redirect:/admin/index");
	}	
	
	
	
}
