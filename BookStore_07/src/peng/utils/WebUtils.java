package peng.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import peng.bean.Cart;
import peng.bean.User;

public class WebUtils {

	public static <T> T param2bean(HttpServletRequest request, T t) {
		Field[] fields = t.getClass().getDeclaredFields();
		for(Field field:fields) {
			String name = field.getName();
			String value = request.getParameter(name);
			try {
				BeanUtils.setProperty(t, name, value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return t;
	}
	
	public static <T> T param2bean2(HttpServletRequest request, T t) {
		Map map = request.getParameterMap();
		try {
			BeanUtils.populate(t, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	public static Cart getCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart==null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
	
	public static User getLoginUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User loginuser = (User) session.getAttribute("user");
		return loginuser;
	}
}
