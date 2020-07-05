package peng.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import peng.bean.Book;
import peng.bean.Cart;
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
	 * add books to cart
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book = WebUtils.param2bean2(request, new Book());
		HttpSession session = request.getSession();
		Cart cart = WebUtils.getCart(request);
		
		Book one = bs.getOne(book);
		cart.addBook2Cart(one);
		session.setAttribute("cart", cart);
		
		//get the book title that has just been added
		session.setAttribute("title", one.getTitle());
		//get last url link
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
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
		
		// delete book according to bookid
		cart.updateCount(request.getParameter("id"), request.getParameter("count"));

		// return cart.jsp
		String refer = request.getHeader("referer");
		response.sendRedirect(refer);
	}
	
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
