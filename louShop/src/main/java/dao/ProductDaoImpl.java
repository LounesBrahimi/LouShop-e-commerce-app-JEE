package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Basket;
import beans.Product;
import beans.User;

public class ProductDaoImpl implements ProductDao{

	private DaoFactory daoFactory;

	public ProductDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Product> listProducts() {
		List<Product> products = new ArrayList<Product>();
		Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;
        try {
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            result = statement.executeQuery("select * from products;");
            while (result.next()) {
            	Product product = new Product();
            	product.setId(result.getInt("product_id"));
            	product.setName(result.getString("name"));
            	product.setCategory(result.getString("category"));
            	product.setPrice(result.getDouble("price"));
            	product.setImage(result.getString("image"));
            	products.add(product);
            	System.out.println(product.getName());
            	System.out.println(product.getImage());
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<Basket> listBasketProducts(ArrayList<Basket> basketList) {
		List<Basket> catalog = new ArrayList<>();
		Connection connexion = null;
        PreparedStatement statement = null;
        ResultSet result = null;
		try {
			connexion = daoFactory.getConnection();
			if (basketList.size() > 0) {
				for (Basket productBasket : basketList) {
					String query = "select * from products where product_id=?";
					statement = connexion.prepareStatement(query);
					statement.setInt(1, productBasket.getId());
					result = statement.executeQuery();
					while (result.next()) {
						Basket basket = new Basket();
						basket.setId(result.getInt("product_id"));
						basket.setName(result.getString("name"));
						basket.setCategory(result.getString("category"));
						basket.setPrice(result.getDouble("price")*productBasket.getQuantity());
						basket.setQuantity(productBasket.getQuantity());
						catalog.add(basket);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return catalog;
	}

	@Override
	public double totalPriceBasket(ArrayList<Basket> basketList) {
		double total = 0;
		Connection connexion = null;
        PreparedStatement statement = null;
        ResultSet result = null;
		try {
			connexion = daoFactory.getConnection();
			if(!basketList.isEmpty()) {
				for (Basket basket : basketList) {
					String query = "select price from products where product_id=?";
					statement = connexion.prepareStatement(query);
					statement.setInt(1, basket.getId());
					result = statement.executeQuery();
					while(result.next()) {
						total+=result.getDouble("price")*basket.getQuantity();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return total;
	}
}
