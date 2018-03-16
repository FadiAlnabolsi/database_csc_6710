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
 * Servlet implementation class SearchFollow, this class connect between showfollow.jsp and list3.jsp, this class get follower and following from 
 * showfollow.jsp, then use UserDao.findUserbyfollow to get the corresponding users, then forward the users to list3.jsp.
 */

public class SearchFollow extends HttpServlet {
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

		String follower = null;
		String following = null;
		
		try {
			
			follower = request.getParameter("follower2");
			following = request.getParameter("following2");
			
			

			request.setAttribute("UserFollowList", userdao.findUserbyfollow(follower,following));
		//	List<Object> li = userdao.findUserbyfollow(follower,following);
	//		for(int i = 0; i < li.size();i++){
	//			System.out.println(li.get(i).toString());
	//		}

		} catch (Exception e) {
		    throw new ServletException(e);
		}
		
		
		

		
		
		request.getRequestDispatcher("/Queryresult/list3.jsp").forward(request, response);
	}

}
