package com.example.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@Autowired
	private PasswordEncoder encode;

	@RequestMapping("/index")
	public String home() {
		return "index";
	}

	@RequestMapping(value = "login")
	public String login() {
		System.out.println(encode.encode("12345"));
		return "login";
	}

	@RequestMapping("logout")
	public String logout() {
		return "logout";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}
}
