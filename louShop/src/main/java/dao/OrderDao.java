package dao;

import java.util.List;

import beans.Order;

public interface OrderDao {
	public boolean insertOrder(Order order);
	public List<Order> userOrders(int id);
}
