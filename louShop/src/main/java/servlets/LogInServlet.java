package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import beans.User;
import dao.DaoFactory;
import dao.UserDao;

public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	
    public LogInServlet() {
        super();
    }
    
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.userDao = daoFactory.getUserDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()){
			String email = request.getParameter("login-email");
			String password = request.getParameter("login-password");
			User user = this.userDao.userLogIn(email, password);
			if (user != null) {
				request.getSession().setAttribute("auth", user);
				response.sendRedirect("index.jsp");
			} else {
				out.println("Error not a user");
			}
		} catch (Exception e) {
			response.sendRedirect("index.jsp");
		}
	}
}
