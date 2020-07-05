package peng.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import peng.bean.Cart;
import peng.bean.User;
import peng.utils.WebUtils;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// check if user login or not
		HttpServletRequest req = (HttpServletRequest)request;
		User loginUser = WebUtils.getLoginUser(req);
		
		// System.out.println("Entering LoginFilter...");
		if (loginUser == null) {
			// please login first
			// System.out.println("login first...");
			request.setAttribute("msg", "Please login before checkout!");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else {
			// already login, then permitted through
			// System.out.println("permitted through...");
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
