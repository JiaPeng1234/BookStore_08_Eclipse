package peng.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import peng.bean.Book;
import peng.bean.Page;
import peng.service.BookService;
import peng.service.Impl.BookServiceImpl;

/**
 * Servlet implementation class BookClientServlet
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BookService bookservice = new BookServiceImpl();

	/**
	 * 给用户展示图书的分页数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pn = request.getParameter("pn");
		Page<Book> page = bookservice.getPage(pn, "4");
		page.setUrl("client/BookClientServlet?method=page");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/book/booklist.jsp").forward(request, response);
	}
	
	protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pn = request.getParameter("pn");
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		Page<Book> page = bookservice.getPageByPrice(pn, "4", min, max);
		page.setUrl("client/BookClientServlet?method=pageByPrice&min="+min+"&max="+max);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/book/booklist.jsp").forward(request, response);
	}

}
