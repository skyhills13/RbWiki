package com.rbwiki.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/")
	public String home(){
		//logger.debug("debug level, into home"); //이것은 debug level이라 콘솔에 보이지 않음. 어디서 볼수있게? 
		logger.info("into home");
		
		return "home";
	}
}
