package com.care.library.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotifyController {

	@Autowired NotifyService service;
	
	@RequestMapping("notification")
	public String notification(Model model) {
		service.selectAll(model);
		return "default/notification";
	}

}
