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


public class RemoveBasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveBasketServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()){
			String elementId = request.getParameter("id");
			if (elementId != null) {
				ArrayList<Basket> basketList = (ArrayList<Basket>) request.getSession().getAttribute("basket-list");
				if (basketList != null) {
					for (Basket basket : basketList) {
						if (basket.getId() == Integer.parseInt(elementId)) {
							basketList.remove(basketList.indexOf(basket));
							break;
						}
					}
				}
				response.sendRedirect("basket.jsp");
			} else {
				response.sendRedirect("basket.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
