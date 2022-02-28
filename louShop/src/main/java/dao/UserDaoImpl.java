package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import beans.User;

public class UserDaoImpl implements UserDao{
	
	private DaoFactory daoFactory;
	
	public UserDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public User userLogIn(String email, String password) {
        	User user = null;
			Connection connexion = null;
        	PreparedStatement statement = null;
        	ResultSet result = null;
        	try {
        		connexion = daoFactory.getConnection();
        		String query = "select * from users where email=? and password=?";
        		statement = connexion.prepareStatement(query);
        		statement.setString(1, email);
        		statement.setString(2, password);
        		result = statement.executeQuery();
                if(result.next()){
                	user = new User();
                	user.setId(result.getInt("user_id"));
                	user.setName(result.getString("name"));
                	user.setEmail(result.getString("email"));
                }
			} catch (Exception e) {
				e.printStackTrace();
			}
		return user;
	}
}
