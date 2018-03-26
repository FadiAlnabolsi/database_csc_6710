package user.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.UserDao;
import user.domain.User;
import user.service.UserService;

/**
 * Servlet implementation class findAll3, this find all the users other than the current user
 */

public class findAll3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDao userdao = new UserDao();
		User user = null;


		try {
			user = (User) request.getSession().getAttribute("session_user");
			String username = user.getUsername();
			request.setAttribute("UserList", userdao.findOthers(username));
			List<Object> li = userdao.findOthers(username);
			for(int i = 0; i < li.size();i++){
				System.out.println(li.get(i).toString());
			}

		} catch (Exception e) {
		    throw new ServletException(e);
		}

		request.getRequestDispatcher("/Queryresult/list2.jsp").forward(request, response);
	}
}
