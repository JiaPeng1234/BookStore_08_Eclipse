package peng.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import peng.bean.Cart;
import peng.bean.Constants;
import peng.bean.Order;
import peng.bean.OrderItem;
import peng.bean.User;
import peng.service.OrderItemService;
import peng.service.OrderService;
import peng.service.Impl.OrderItemServiceImpl;
import peng.service.Impl.OrderServiceImpl;
import peng.utils.WebUtils;

/**
 * Servlet implementation class OrderClientServlet
 */
public class OrderClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	OrderService os = new OrderServiceImpl();
	OrderItemService ois = new OrderItemServiceImpl();
	
	protected void checkout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// get user
		User loginUser = WebUtils.getLoginUser(request);
		Cart cart = WebUtils.getCart(request);
		// checkout
		String orderId = os.checkout(cart, loginUser);
		session.setAttribute("orderId", orderId);
		response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
	}
	
	/**
	 * list all orders for an user
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. get login user
		User loginUser = WebUtils.getLoginUser(request);
		
		// 2. get orders of the user
		List<Order> myOrders = null;
		try {
			myOrders = os.getMyOrders(loginUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 3. save Myorders in session
		request.setAttribute("myOrders", myOrders);
		
		// 4. turn to myorders page
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
	}
	
	protected void receive(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. update order status to delivered
		os.updateStatus(request.getParameter("orderId"), Constants.DELIVERED);

		// 2. turn to myorders page
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
	}
	
	protected void detail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. get orderitems of this order
		List<OrderItem> orderItems = null;
		try {
			orderItems = ois.getOrderItems(request.getParameter("orderId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Order thisOrder = null;
		try {
			thisOrder = os.getOneOrder(request.getParameter("orderId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 2. save orderItems in session
		request.setAttribute("orderItems", orderItems);
		request.setAttribute("thisOrder", thisOrder);
		
		// 3. turn to myorders page
		request.getRequestDispatcher("/pages/order/order_detail.jsp").forward(request, response);
	}
}
