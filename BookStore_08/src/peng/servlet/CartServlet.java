package peng.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import peng.bean.Book;
import peng.bean.Cart;
import peng.bean.CartItem;
import peng.service.BookService;
import peng.service.Impl.BookServiceImpl;
import peng.utils.WebUtils;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    BookService bs = new BookServiceImpl();
	
	/**
	 * use Ajax technik add books to cart
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book = WebUtils.param2bean2(request, new Book());
		// HttpSession session = request.getSession();
		Cart cart = WebUtils.getCart(request);
		
		Book one = bs.getOne(book);
		cart.addBook2Cart(one);
		//no need to set attribute again, cart can be regarded as pointer
		//session.setAttribute("cart", cart); 

		int totalCount = cart.getTotalCount();
		String title = one.getTitle();
		
		//pack totalCount and title into map
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("totalCount", totalCount);
		map.put("title", title);
		
		// try {
		// Thread.sleep(10000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		
		//transfer map to json using the Gson tool
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
		//send the String json to explorer
		response.getWriter().write(json);
	}

	/**
	 * delete a group of book in the cart, according to bookid
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get Cart
		Cart cart = WebUtils.getCart(request);

		// delete book according to bookid
		cart.deleteItem(request.getParameter("id"));

		//get last url link
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
	}
	
	/**
	 * update book amount in the cart
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get Cart
		Cart cart = WebUtils.getCart(request);
		
		// update book count
		cart.updateCount(request.getParameter("id"), request.getParameter("count"));

		// return cart.jsp
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
	}
	
	/**
	 * update book amount in the cart using ajax technique
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void updateAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get Cart
		Cart cart = WebUtils.getCart(request);
		
		String bookid = request.getParameter("id");
		
		// update book count
		int updateCount = cart.updateCount(bookid, request.getParameter("count"));

		// get itemTotalPrice, totalCount, totalMoney
		CartItem item = cart.getItem(bookid);
		double itemTotalPrice = item.getTotalPrice();
		int totalCount = cart.getTotalCount();
		double totalMoney = cart.getTotalMoney();
		
		// create map, put "itemTotalPrice, totalCount, totalMoney" in as key-value
		Map<String, Object> map = new HashMap<>();
		map.put("itemTotalPrice",itemTotalPrice);
		map.put("totalCount",totalCount);
		map.put("totalMoney",totalMoney);
		map.put("updateCount",updateCount);
		
		// turn map to json string using gson package,
		// which contains all information needed to 'partly' refresh the page
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
		//send the json String to cart.jsp
		response.getWriter().write(json);
	}
	
	/**
	 * clear cart
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get Cart
		Cart cart = WebUtils.getCart(request);
		
		// clear cart
		cart.clear();

		// return cart.jsp
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
	}
}
