package com.rbwiki.domain.users;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {
	private static final Logger logger = LoggerFactory.getLogger(UserTest.class);
	private static Validator validator; 
	
	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void userIdWhenIsEmpty() {
		User user = new User("", "password", "name","111@111.com");
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		assertThat(constraintViolations.size(), is(2));
		
		for (ConstraintViolation<User> constraintViolation : constraintViolations) {
			logger.debug("validation error message: {}", constraintViolation.getMessage());
		}
	}
	
	@Test
	public void matchPassword() throws Exception {
		String password = "password";
		User user = new User("userId", password, "name","111@111.com");
		Authenticate authenticate = new Authenticate("userId", password);
		assertTrue(user.matchPassword(authenticate));
		
		authenticate = new Authenticate("userId", "password2");
		assertFalse(user.matchPassword(authenticate));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void updateWhenMisMatchUserId() throws Exception {
		User user = new User("userId", "password", "name","111@111.com");
		User updateUser = new User("user", "password", "kyky", "222@222.com");
		user.update(updateUser);
	}

	@Test
	public void update() throws Exception {
		User user = new User("userId", "password", "name","111@111.com");
		User updateUser = new User("userId", "password", "kyky", "222@222.com");
		User updatedUser = user.update(updateUser);
		assertThat(updatedUser, is(updateUser));
	}

}
 