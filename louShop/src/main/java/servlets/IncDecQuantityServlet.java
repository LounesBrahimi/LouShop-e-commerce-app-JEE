package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import beans.Basket;

public class IncDecQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IncDecQuantityServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()){
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Basket> basket_list = (ArrayList<Basket>) request.getSession().getAttribute("basket-list");
			
			if (action != null && id >= 1) {
				if (action.equals("inc")) {
					for (Basket basket : basket_list) {
						if (basket.getId() == id) {
							basket.setQuantity(basket.getQuantity()+1);
							response.sendRedirect("basket.jsp");
						}
					}
				} else if (action.equals("dec")) {
					for (Basket basket : basket_list) {
						if (basket.getId() == id && basket.getQuantity() > 1) {
							basket.setQuantity(basket.getQuantity()-1);
							break;
						}
					}
					response.sendRedirect("basket.jsp");
				}
			} else {
				response.sendRedirect("basket.jsp");
			}
			
		} catch (Exception e) {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
