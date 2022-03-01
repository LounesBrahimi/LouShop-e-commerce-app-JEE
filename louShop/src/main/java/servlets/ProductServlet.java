package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.DaoFactory;
import dao.ProductDao;

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductDao productDao;   
	
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.productDao = daoFactory.getProductDao();
    }
    
    public ProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("products", this.productDao.listProducts());
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
