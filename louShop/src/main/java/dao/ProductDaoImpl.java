package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
            	product.setPrice(""+result.getDouble("price"));
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
	
	
}
