package peng.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import peng.bean.User;
import peng.service.UserService;
import peng.service.Impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = us.login(new User(null, username, password, null));
		if (user == null) {
			// failed return login page, request dispatch 转发
			request.setAttribute("msg", "Username/password is incorrect!");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		} else {
			// success send redirect to login success page 重定向
			// System.out.println(request.getContextPath()); // "/BookStore_02"
			response.sendRedirect(request.getContextPath() + "/pages/user/login_success.jsp");
		}
	}
}
