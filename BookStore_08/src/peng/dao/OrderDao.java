package peng.dao;

import java.util.List;

import peng.bean.Order;

public interface OrderDao {

	/**
	 * save order
	 * @param order
	 * @return
	 */
	public int saveOrder(Order order);
	
	/**
	 * update order status
	 * @param order
	 * @return
	 */
	public int updateStatus(Order order);

	/**
	 * get all orders (with administrator authority)
	 * @return
	 */
	public List<Order> getOrderList();
	
	/**
	 * get all orders for single user 
	 * @param userId
	 * @return
	 */
	public List<Order> getOrderByUserId(Integer userId);

	public Order getOne(String orderId);
}
