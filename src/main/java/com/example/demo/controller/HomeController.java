package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomeController {
	@GetMapping("index")
	public String index() {
		return "index";
	}

	@GetMapping(value = { "admin", "admin/index" })
	public String admin() {
		return "admin/index";
	}


	@GetMapping(value = { "user", "user/index" })
	public String user() {
		return "user/index";
	}

	@GetMapping(value="login/qq")
	public String QQLogin() {
		return "loginqq";

	}

}
