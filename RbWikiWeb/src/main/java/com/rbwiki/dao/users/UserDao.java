package com.rbwiki.dao.users;

import com.rbwiki.domain.users.User;

public interface UserDao {

	User findById(String userId);

	void create(User user);

	void update(User user);

}