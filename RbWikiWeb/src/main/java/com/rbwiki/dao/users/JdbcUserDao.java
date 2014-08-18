package com.rbwiki.dao.users;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.rbwiki.domain.users.User;

public class JdbcUserDao extends JdbcDaoSupport implements UserDao {
	private static final Logger logger = LoggerFactory.getLogger(JdbcUserDao.class);
	@PostConstruct
	public void initialize() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("rbwiki.sql"));
		DatabasePopulatorUtils.execute(populator, getDataSource());
		logger.info("database initailized success!");
	}

	/* (non-Javadoc)
	 * @see com.rbwiki.dao.users.IUserDao#findById(java.lang.String)
	 */
	@Override
	public User findById(String userId) {
		String sql = "select * from USERS where userId = ?";
		RowMapper<User> rowMapper = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new User(
						rs.getString("userId"),
						rs.getString("password"), 
						rs.getString("name"),
						rs.getString("email"));
			}
		};
		
		try{
			return getJdbcTemplate().queryForObject(sql, rowMapper, userId);
		}catch( EmptyResultDataAccessException e){
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.rbwiki.dao.users.IUserDao#create(com.rbwiki.domain.users.User)
	 */
	@Override
	public void create(User user) {
		String sql = "insert into USERS VALUES (?, ?, ?, ?)";
		getJdbcTemplate().update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	/* (non-Javadoc)
	 * @see com.rbwiki.dao.users.IUserDao#update(com.rbwiki.domain.users.User)
	 */
	@Override
	public void update(User user) {
		String sql = "update USERS set password = ?, name = ?, email = ? where userId = ?";
		getJdbcTemplate().update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}
}
