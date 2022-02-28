package dao;

import beans.User;

public interface UserDao {
	public User userLogIn(String email, String password);
}
