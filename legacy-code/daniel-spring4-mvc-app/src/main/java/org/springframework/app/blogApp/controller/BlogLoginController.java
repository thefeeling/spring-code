package org.springframework.app.blogApp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.abstractClass.AbstractController;
import org.springframework.app.blogApp.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/blog/auth/")
public class BlogLoginController extends AbstractController {
	
	/**
	 * @Comment Login Page 반환
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpSession session, Model model) {
		log.info("Session Id =>" + session.getId());
		// log.info("Welcome login! {}", session.getId());
		return "blog/login";
	}

	
	/**
	 * 
	 * @param session
	 */
	@RequestMapping(value = "/loginProc", method = RequestMethod.POST)
	public void loginProc(HttpSession session) {
		System.out.println("loginProc");
	}

	/**
	 * 
	 * @param session
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpSession session) {
		
		CustomUserDetails userDetails = (CustomUserDetails) session.getAttribute("userLoginInfo");

		System.out.println("Welcome logout! {}, {}" + session.getId() + userDetails.getUsername());
		// log.info("Welcome logout! {}, {}", session.getId(),
		// userDetails.getUsername());

		session.invalidate();
	}

	/**
	 * 
	 * @param session
	 */
	@RequestMapping(value = "/login_success", method = RequestMethod.GET)
	public void login_success(HttpSession session) {
		CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getDetails();

		//log.info("Welcome login_success! {}, {}", session.getId(), userDetails.getUsername() + "/" + userDetails.getPassword());
		System.out.println("Welcome login_success! {}, {}" + session.getId() + userDetails.getUsername() + "/" + userDetails.getPassword());
		session.setAttribute("userLoginInfo", userDetails);
	}

	@RequestMapping(value = "/login_duplicate", method = RequestMethod.GET)
	public void login_duplicate() {
		log.info("Welcome login_duplicate!");
	}
}
