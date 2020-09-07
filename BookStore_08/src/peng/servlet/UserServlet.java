package peng.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.code.kaptcha.Constants;

import peng.bean.User;
import peng.service.UserService;
import peng.service.Impl.UserServiceImpl;
import peng.utils.WebUtils;

/**
 * handle all the requests that related to user issues. Servlet implementation
 * class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserServiceImpl();

	/**
	 * user login
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User bean = WebUtils.param2bean(request, new User());
		User user = us.login(bean);
		// 把user存在session里面，作为登录的证据
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
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

	/**
	 * user register
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		String sessionCode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (!code.equals(sessionCode)) {
			request.setAttribute("msg", "verification code is wrong!");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			return;
		}
		User bean = WebUtils.param2bean2(request, new User());
		boolean b = us.regist(bean);
		if (b) {
			// regist success, return success regist html redirect
			response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
		} else {
			// regist failed, return regist html request dispatch
			request.setAttribute("msg", "Username already exists!");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}// 用户注册

	/**
	 * user logout
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 销毁session
		session.invalidate();
//		request.getRequestDispatcher("/index.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	/**
	 * check username valid or not
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void checkuser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = WebUtils.param2bean2(request, new User());
		boolean b = us.checkuser(user);
		if(b) {
			// free to register
			response.getWriter().write("<p style='color: green;'>Username available</p>");
		}else {
			// username already in used
			response.getWriter().write("Username already exists");
		}
	}

}
