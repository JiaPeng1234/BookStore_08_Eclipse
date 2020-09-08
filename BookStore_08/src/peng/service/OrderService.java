package peng.service;

import java.util.List;

import peng.bean.Cart;
import peng.bean.Order;
import peng.bean.User;

public interface OrderService {
	
	/**
	 * get all orders with administrator authority
	 * @return
	 */
	public List<Order> getAllOrder();
	
	/**
	 * get all orders of single user
	 * @param userId
	 * @return
	 */
	public List<Order> getMyOrders(Integer userId);

	/**
	 * update status of the order
	 * @param order
	 * @param status
	 * @return
	 */
	public void updateStatus(String orderid, String status);

	/**
	 * go checkout the cart
	 * @param UserId
	 * @param cart
	 * @return
	 */
	public String checkout(Cart cart, User user);
	
	/**
	 * get one order
	 * @param orderId
	 * @return
	 */
	public Order getOneOrder(String orderId);
}
