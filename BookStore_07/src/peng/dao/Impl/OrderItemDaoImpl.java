package peng.dao.Impl;

import java.util.List;

import peng.bean.OrderItem;
import peng.dao.BaseDao;
import peng.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

	/**
	 * save one orderItem into the database
	 */
	@Override
	public int saveOrderItem(OrderItem orderItem) {
		String sql = "insert into bs_order_item(title, count, price, total_price, order_id) values (?,?,?,?,?)";
		return update(sql, orderItem.getTitle(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(),
				orderItem.getOrderId());

	}

	@Override
	public List<OrderItem> getOrderItems(String orderId) {
		String sql = "select id, title, count, price, total_price totalPrice, order_id orderId from bs_order_item where order_id=?";
		return getBeanList(sql, orderId);
	}

	@Override
	public int saveBatch(List<OrderItem> item) {
		Object[][] objs = new Object[item.size()][5];
		String sql = "insert into bs_order_item(title, count, price, total_price, order_id) values(?,?,?,?,?)";
		int index = 0;
		for (OrderItem orderitem : item) {
			objs[index++] = new Object[] { orderitem.getTitle(), orderitem.getCount(), orderitem.getPrice(),
					orderitem.getTotalPrice(), orderitem.getOrderId() };
		}
		batch(sql, objs);
		return 1;
	}

}
