package peng.service;

import java.util.List;

import peng.bean.OrderItem;

public interface OrderItemService {

	public void saveItem(List<OrderItem> orderItem);

	public List<OrderItem> getOrderItems(String orderid);
}
