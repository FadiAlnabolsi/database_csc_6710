package user.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.UserDao;
import user.domain.Blog;
import user.domain.User;

/**
 * Servlet implementation class BlogServlet
 */

public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User currentuser = null;
		PrintWriter out = response.getWriter();
		String blogtitle = request.getParameter("blogTitle");
		String blogDescription = request.getParameter("blogDes");
		String blogTag = request.getParameter("blogTag");
		Blog blog = new Blog();
		blog.SetBlogTag(blogTag);
		blog.setBlogDescription(blogDescription);
		blog.setBlogTitle(blogtitle);

		currentuser = (User) request.getSession().getAttribute("session_user");
		String username = currentuser.getUsername();

		UserDao userdao = new UserDao();

		try {
			String blogId = null;
			System.out.println(blog);
			System.out.println(username);
			blogId = userdao.addBlog(blog, username);
			request.setAttribute("blogid", blogId);
			request.getSession().setAttribute("blogid", blogId);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/singleblogpost?blogid=" + blogId);

			dispatcher.forward(request, response);
			// response.sendRedirect(request.getContextPath() + "/singleblogpost");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
