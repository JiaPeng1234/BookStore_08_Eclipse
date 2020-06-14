package peng.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import peng.bean.User;
import peng.service.UserService;
import peng.service.Impl.UserServiceImpl;
import peng.utils.WebUtils;
/**
 * handle all the requests that related to user issues.
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserServiceImpl();
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User bean = WebUtils.param2bean(request, new User());
		User user = us.login(bean);
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

	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User bean = WebUtils.param2bean2(request, new User());
		// 用户注册
		boolean b = us.regist(bean);
		if(b) {
			//regist success, return success regist html redirect
			response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
		}else {
			//regist failed, return regist html request dispatch
			request.setAttribute("msg", "Username already exists!");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}

}
