package peng.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// ��ȡ�û���
		String username = request.getParameter("username");
		// ��ȡ����
		String password = request.getParameter("password");
	
		// �ж��û��Ƿ��½�ɹ�
		if(username.length()>=6&&"123456".equals(password)) {
			//��½�ɹ�-->�ض��򵽳ɹ�ҳ��
			System.out.println("asdasdasd");
			response.sendRedirect("success/login-success.html");
		}else {
			//��¼ʧ��-->ת����ʧ�ܽ���
			System.out.println("hehheha");
			request.getRequestDispatcher("success/login-error.html").forward(request, response);
		}
	}

}
