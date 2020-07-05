package peng.test;

import java.math.BigDecimal;

import org.junit.Test;

import peng.bean.Book;
import peng.bean.Cart;

public class CartTest {

	Book book1 = new Book(1, "西游记1", "吴承恩", 0.01, 1, 1, "");
	Book book2 = new Book(2, "西游记2", "吴承恩", 0.06, 1, 1, "");
	Book book3 = new Book(3, "西游记3", "吴承恩", 31.11, 1, 1, "");
	Book book4 = new Book(4, "西游记4", "吴承恩", 41.11, 1, 1, "");
	Book book5 = new Book(5, "西游记5", "吴承恩", 51.11, 1, 1, "");
	Book book6 = new Book(6, "西游记6", "吴承恩", 61.11, 1, 1, "");

	@Test
	public void cartTest() {
		Cart cart = new Cart();
		cart.addBook2Cart(book1);
		cart.addBook2Cart(book2);
		System.out.println(cart.getAllItems());
		System.out.println("当前总计" + cart.getTotalCount() + "本书");
		System.out.println("当前总价" + cart.getTotalMoney());
		cart.updateCount("1", "3");
		System.out.println(cart.getAllItems());
		System.out.println("当前总计" + cart.getTotalCount() + "本书");
		System.out.println("当前总价" + cart.getTotalMoney());
		cart.deleteItem("1");
		System.out.println(cart.getAllItems());
		System.out.println("当前总计" + cart.getTotalCount() + "本书");
		System.out.println("当前总价" + cart.getTotalMoney());
	}

	@Test
	public void test2() {
//		int i = 1;
//		for (int j = 1; j < 15; j++) {
//			i *= j;
//		}
//		System.out.println(i);
		
//		double a = 0.01;
//		double b = 0.06;
//		BigDecimal decimal1 = new BigDecimal(a);
//		BigDecimal decimal2 = new BigDecimal(b);
//		System.out.println(decimal1.add(decimal2));
		int num = 123456;
		System.out.println(Integer.toString(num));
		System.out.println((Integer.toString(num)).getClass());
	}
}
