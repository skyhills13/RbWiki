package com.rbwiki.dao;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.io.InputStream;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.rbwiki.domain.users.User;

public class MyBatisTest {
	private static final Logger logger = LoggerFactory.getLogger(MyBatisTest.class);
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void setup() throws IOException {
		String resource = "mybatis-config-test.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("rbwiki.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		logger.info("database initailized success!");
	}
	
	private DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/rbwiki");
		dataSource.setUsername("sa");
		return dataSource;
	}

	@Test
	public void gettingStarted() throws Exception {
		try(SqlSession session = sqlSessionFactory.openSession()) {
			User user = session.selectOne("UserMapper.findById", "1111");
			logger.debug("User : {}", user);
		}
	}
	
	@Test
	public void insert() throws Exception {
		try(SqlSession session = sqlSessionFactory.openSession()) {
			User user = new User("2222", "222222", "twotwo", "222@222.com");
			session.insert("UserMapper.create ", user);
			User actual = session.selectOne("UserMapper.findById", user.getUserId());
			assertThat(actual, is(user));
		}
	}
}
  