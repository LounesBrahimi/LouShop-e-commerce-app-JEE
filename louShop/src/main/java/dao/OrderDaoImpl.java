package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Order;
import beans.Product;

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

	@Override
	public List<Order> userOrders(int id) {
		List<Order> listOrders = new ArrayList<>();
		Connection connexion = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        
		try {
			connexion = daoFactory.getConnection();
			String query = "SELECT * FROM orders where user_id=? ORDER BY orders.order_id DESC";
			statement = connexion.prepareStatement(query);
			statement.setInt(1, id);
            result = statement.executeQuery();
            
            while(result.next()) {
            	Order order = new Order();
            	int productId = result.getInt("product_id");
            	Product product = DaoFactory.getInstance().getProductDao().getProduct(productId);
            	order.setOrderId(result.getInt("order_id"));
                order.setId(productId);
                order.setName(product.getName());
                order.setCategory(product.getCategory());
                order.setPrice(product.getPrice()*result.getInt("order_quantity"));
                order.setQuantity(result.getInt("order_quantity"));
                order.setDate(result.getString("order_date"));
                
                listOrders.add(order);
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOrders;
	}
}
