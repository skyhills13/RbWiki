package com.rbwiki.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/") // 괄호 안의 url로 접근 시, 아래의 method 호출.
	public String home(){
		//logger.debug("debug level, into home"); //이것은 debug level이라 콘솔에 보이지 않음. 어디서 볼수있게? 
		logger.info("into home");
		
		//spring setting인 theReader-servlet.xml에서 prefix와 suffix를 지정해주었으므로 "/home.jsp"와 같음 
		return "home";
	}
}
