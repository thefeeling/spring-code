package org.springframework.app.blogApp.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/blog")
public class BlogController {

	
	@Secured("ROLE_USER")
	@RequestMapping("/main")
	public ModelAndView mav(Model model) {
		ModelAndView mav = new ModelAndView("blog/main");
		return mav;
	}

}
