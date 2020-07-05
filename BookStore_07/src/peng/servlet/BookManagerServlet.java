package peng.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import peng.bean.Book;
import peng.bean.Page;
import peng.service.BookService;
import peng.service.Impl.BookServiceImpl;
import peng.utils.WebUtils;

/**
 * Servlet implementation class BookManagerServlet
 */
public class BookManagerServlet extends BaseServlet {

	private BookService bookService = new BookServiceImpl();

	/**
	 * show the books in one page
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pn = request.getParameter("pn");
		Page<Book> page = bookService.getPage(pn, "4");
		page.setUrl("manager/BookManagerServlet?method=page");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	
	/**
	 * show all books as a list, better use page function which only shows a part of all books each time
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 查出所有图书数据并显示
		List<Book> list = bookService.getAll();
		// 将所有查出的图书交给页面book_manager.jsp显示
		request.setAttribute("list", list);
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}

	/**
	 * add a book ---> use update function below instead
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// turn the submitted form to bookbean
		Book book = WebUtils.param2bean2(request, new Book());
		// save bookbean to database
		boolean b = bookService.add(book);

		if (b) {
			// save successfully 保存成功，直接请求list，也可以直接调用list方法，但是不利于解耦所以算了
			response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=page");
		}
	}

	/**
	 * delete a book
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get current pageNo
		String pn = request.getParameter("pn");
		// turn the submitted form to bookbean
		Book book = WebUtils.param2bean2(request, new Book());
		// save bookbean to database
		boolean b = bookService.delete(book);

		// redirect to refresh booklist and show the book manager page.
		response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=page&pn="+pn);
	}

	/**
	 * get a book (by id)
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void getBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// turn the submitted form (only book id contained) to bookbean
		Book book = WebUtils.param2bean2(request, new Book());
		// get this one book (by id)
		Book one = bookService.getOne(book);
		// save this book to requestAttribute
		request.setAttribute("book", one);
		// redirect to refresh the book list and show the book manager page.
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}

	/**
	 * modify or add a book
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// modify or add a book
		Book book = WebUtils.param2bean2(request, new Book());
		if(book.getId()==0) {
			// if book id = 0, add a book 
			boolean b = bookService.add(book);
		}
		else {
			// else, modify the book
			boolean b = bookService.update(book);
		}
		// return to manager page
		String pn = request.getParameter("pn");
		response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=page&pn="+pn);
	}

}
