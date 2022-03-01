package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Order;

public class OrderDaoImpl implements OrderDao{
	private DaoFactory daoFactory;
	
	public OrderDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public boolean insertOrder(Order order) {
		boolean status = false;
		Connection connexion = null;
        PreparedStatement statement = null;
        ResultSet result = null;
		try {
	        connexion = daoFactory.getConnection();
	        String query = "insert into orders (product_id, user_id, order_quantity, order_date) values(?,?,?,?)";
	        statement = connexion.prepareStatement(query);
	        statement.setInt(1, order.getId());
	        statement.setInt(2, order.getUserId());
	        statement.setInt(3, order.getQuantity());
	        statement.setString(4, order.getDate());
	        statement.executeUpdate();
	        status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
