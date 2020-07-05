package peng.dao;

import java.util.List;

import peng.bean.Order;
import peng.bean.OrderItem;

public interface OrderItemDao {
	
	/**
	 * save an orderitem
	 * @param orderItem
	 */
	public int saveOrderItem(OrderItem item);

	/**
	 * get all orderitems in single order
	 * @param orderId
	 * @return
	 */
	public List<OrderItem> getOrderItems(String orderId);

	/**
	 * save a batch of orderitems 
	 * @param userId
	 * @return
	 */
	public int saveBatch(List<OrderItem> item);
}
