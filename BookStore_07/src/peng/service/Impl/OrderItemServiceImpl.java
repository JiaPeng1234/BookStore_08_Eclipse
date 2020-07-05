package peng.service.Impl;

import java.util.List;

import peng.bean.OrderItem;
import peng.dao.BookDao;
import peng.dao.OrderItemDao;
import peng.dao.Impl.BookDaoImpl;
import peng.dao.Impl.OrderItemDaoImpl;
import peng.service.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {

	OrderItemDao oid = new OrderItemDaoImpl();
	
	@Override
	public void saveItem(List<OrderItem> orderItem) {
		//		for(OrderItem orderItem2 : orderItem) {
		//			oid.saveOrderItem(orderItem2);
		//		}
		oid.saveBatch(orderItem);
	}

	@Override
	public List<OrderItem> getOrderItems(String orderid) {
		return oid.getOrderItems(orderid);
	}

}
