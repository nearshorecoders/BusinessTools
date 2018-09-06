package com.org.pos.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

//    @GetMapping("/")
//    public String home1() {
//        return "/login";
//    }

    @GetMapping("/home")
    public String home() {
        return "/layout";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @PostMapping("/login")
    public String login2() {
    	return "redirect:/";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
    
	@RequestMapping(value = "/")
	public String index(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/index";
		}
		return "login";
	}
	
	@RequestMapping(value = "/login")
	public String login(final Model model) {
		return "redirect:/";
	}

}