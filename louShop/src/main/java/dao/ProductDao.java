package dao;

import java.util.ArrayList;
import java.util.List;

import beans.Basket;
import beans.Product;

public interface ProductDao {
	public List<Product> listProducts();
	public List<Basket> listBasketProducts(ArrayList<Basket> basketList);
}
