package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import beans.Basket;

public class AddToBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()){
			ArrayList<Basket> products = new ArrayList<>();
			
			int idProduct = Integer.parseInt(request.getParameter("id"));
			
			Basket basket = new Basket();
			basket.setId(idProduct);
			basket.setQuantity(1);
			
			HttpSession session = request.getSession();
			ArrayList<Basket> basket_list = (ArrayList<Basket>) session.getAttribute("basket-list");
			
			if (basket_list == null) {
				products.add(basket);
                session.setAttribute("basket-list", products);
                response.sendRedirect("index.jsp");
			} else {
				products = basket_list;
				boolean exist = false;
                for (Basket productInBasket : basket_list) {
                    if (productInBasket.getId() == idProduct) {
                        exist = true;
                        out.println("<h3 style='color:crimson; text-align: center'>Item Already in Basket. <a href='basket.jsp'>GO to Basket Page</a></h3>");
                    }
                }
                
                if (!exist) {
                    products.add(basket);
                    response.sendRedirect("index.jsp");
                }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
