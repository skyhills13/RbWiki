package com.rbwiki.web.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rbwiki.domain.users.User;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/users/form")
	public String form(){
		return "users/form";
	}
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String create(User user){
		logger.debug("User : {}", user);
		return "users/form";
	}
}
