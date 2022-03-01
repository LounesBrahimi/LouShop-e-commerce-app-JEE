package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import beans.Order;
import beans.User;
import beans.Basket;
import dao.DaoFactory;
import dao.OrderDao;
import dao.UserDao;

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDao orderDao;   
	
    public OrderServlet() {
        super();
    }
    
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.orderDao = daoFactory.getOrderDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			User auth = (User) request.getSession().getAttribute("auth");
			
			if (auth != null) {
				String productId = request.getParameter("id");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                if (productQuantity <= 0) {
                	productQuantity = 1;
                }
                Order order = new Order();
                order.setId(Integer.parseInt(productId));
                order.setUserId(auth.getId());
                order.setQuantity(productQuantity);
                order.setDate(formatter.format(date));
                
                boolean status = this.orderDao.insertOrder(order);
                if (status) {
                	 ArrayList<Basket> basketList = (ArrayList<Basket>) request.getSession().getAttribute("basket-list");
                	 if (basketList != null) {
                		 for (Basket basket : basketList) {
                             if (basket.getId() == Integer.parseInt(productId)) {
                            	 basketList.remove(basketList.indexOf(basket));
                                 break;
                             }
                		 }
                	 }
                	 response.sendRedirect("orders.jsp");
                } else {
                	out.println("Error order");
                }
			}else {
				response.sendRedirect("login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
