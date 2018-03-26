package user.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
/**
 * Servlet implementation class singleblogpost
 */
public class singleblogpost extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public singleblogpost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String dbName = "sampledb2";
		String userName = "john";
		String password = "pass1234";
		String hostname = "sampledb2.cpvy4fmhbooi.us-west-2.rds.amazonaws.com";
		String port = "3306";
		Connection connect;
		PreparedStatement preparestatement;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			connect = DriverManager.getConnection(
				"jdbc:mysql://" + hostname + ":" + port + "/" + dbName, userName, password);
			String[] blogArray  = new String[4];
			String[] commentArray  = new String[3];
			List<String[]> commentArrList = new ArrayList<String[]>();

			String blogid = request.getParameter("blogid");
			String sql_blog = "SELECT * from blog " +
						  	"WHERE id=" + blogid;
			String sql_comments = "SELECT subject,  description, username " +
							  	  "FROM comment, comment_to_blog " +
							      "WHERE comment.id = comment_to_blog.comment_id " +
							      "and comment_to_blog.blog_id=" + blogid;

			try {
				preparestatement = connect.prepareStatement(sql_blog);
				ResultSet resultSet = preparestatement.executeQuery();
				while(resultSet.next()){
					blogArray  = new String[4];

					blogArray[0] = resultSet.getString("id");
					blogArray[1] = resultSet.getString("subject");
					blogArray[2] = resultSet.getString("description");
					blogArray[3] = resultSet.getString("date");
				}

				preparestatement = connect.prepareStatement(sql_comments);
				resultSet = preparestatement.executeQuery();
				while(resultSet.next()){
					commentArray  = new String[3];

					commentArray[0] = resultSet.getString("username");
					commentArray[1] = resultSet.getString("subject");
					commentArray[2] = resultSet.getString("description");
					commentArrList.add(commentArray);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("comments", commentArrList);
			request.setAttribute("blog", blogArray);

			System.out.println("we are at the end of the singleblogpost.java");
			request.getRequestDispatcher("/Queryresult/comment-to-blog.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
