package com.rbwiki.web.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rbwiki.dao.users.UserDao;
import com.rbwiki.domain.users.User;

@Controller
@RequestMapping("/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDao userDao; 
	
	@RequestMapping("/form")
	public String form(Model model){
		model.addAttribute("user", new User());
		return "users/form";
	}
	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(User user){
		logger.debug("User : {}", user);
		userDao.create(user);
		logger.debug("Database: {}", userDao.findById(user.getUserId()));
		return "users/form";
	}
}
