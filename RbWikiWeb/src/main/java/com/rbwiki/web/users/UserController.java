package com.rbwiki.web.users;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
	//여러개가 필요할때는 이렇게 해야
//	@Resource(name="userDao1")
//	private UserDao userDao;

//	@Resource(name="userDao2")
//	private UserDao userDao2;

	
	@RequestMapping("/form")
	public String form(Model model){
		model.addAttribute("user", new User());
		return "users/form";
	}
	@RequestMapping(value="", method=RequestMethod.POST)
	public String create(@Valid User user, BindingResult bindingResult){
		logger.debug("User : {}", user);
		if ( bindingResult.hasErrors()) {
			logger.debug("binding Result has error!");
			List<ObjectError> errors = bindingResult.getAllErrors();
			for (ObjectError error : errors) {
				logger.debug("error : {}, {}", error.getCode(), error.getDefaultMessage());
			}
			//validation에서 에러가 뜨면 다시 입력화면으로 넘어갈겡 
			return "users/form";
		}
		userDao.create(user);
		logger.debug("Database: {}", userDao.findById(user.getUserId()));
		//에러가 에러가 없을 때는 다시 입력화면으로 넘어갈 것이 아니라, 메인으로 넘어가는게 좋으니까. 
		return "redirect:/";
	}
}
