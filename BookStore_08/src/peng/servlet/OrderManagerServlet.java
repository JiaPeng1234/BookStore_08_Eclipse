package peng.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import peng.bean.Constants;
import peng.bean.Order;
import peng.bean.OrderItem;
import peng.service.OrderItemService;
import peng.service.OrderService;
import peng.service.Impl.OrderItemServiceImpl;
import peng.service.Impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderManagerServlet
 */
public class OrderManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    OrderService os = new OrderServiceImpl();
    OrderItemService ois = new OrderItemServiceImpl();
    
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. get all orders
		List<Order> allOrder = os.getAllOrder();
		
		// 2. save all orders in request
		request.setAttribute("allOrder", allOrder);

		// 3. turn to page order management
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
	}

	protected void deliver(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. update order status for this user
		os.updateStatus(request.getParameter("orderId"), Constants.DELIVERING);

		// 2. turn to order management page with list method
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
		//	request.getRequestDispatcher("/manager/OrderManagerServlet?method=list").forward(request, response);
	}
	
	protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
