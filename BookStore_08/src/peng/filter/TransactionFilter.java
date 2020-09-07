package peng.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import peng.utils.JDBCUtils;

/**
 * Servlet Filter implementation class TransactionFilter
 */
public class TransactionFilter implements Filter {

    public TransactionFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// get connection corresponding to current thread
		Connection conn = JDBCUtils.getConnection();
		// set transaction control to manual
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			 e2.printStackTrace();
		}
		
		try {
			chain.doFilter(request, response);
			conn.commit();
		} catch (Exception e) {
			// failed to run the process, exception occurred
			System.out.println("filter receives an exception"+e.getMessage());
			// transaction rollback
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// transaction failed to carry out, go to hin site
			HttpServletRequest req = (HttpServletRequest)request;
			HttpServletResponse reps = (HttpServletResponse)response;
			reps.sendRedirect(req.getContextPath()+"/pages/exception.jsp");
		}
		// close connection, remove its id in Map
		JDBCUtils.releaseConnection();
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
