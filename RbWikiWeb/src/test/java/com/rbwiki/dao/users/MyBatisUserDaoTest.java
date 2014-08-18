package com.rbwiki.dao.users;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rbwiki.domain.users.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class MyBatisUserDaoTest {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	
	@Autowired
	private UserDao userDao;
	
	@Test
	public void findById() {
		User user = userDao.findById("1111");
		logger.debug("user: {}", user);
	}
	
	@Test
	public void create(){
		User user = new User("rbEmployee", "password", "고용인", "rbEmployee@rbwiki.com");
		userDao.create(user);
		User actual = userDao.findById(user.getUserId());
		logger.debug("user: {}", actual);
		assertThat(actual, is(user));
	}

}
