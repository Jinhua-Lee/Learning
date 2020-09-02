package com.c3p0;

import java.util.List;

public interface UserDao {
	public User getUserByID(int id);
	public List<User> getAllUser();
}
