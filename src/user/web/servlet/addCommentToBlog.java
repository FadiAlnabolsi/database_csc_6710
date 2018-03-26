package user.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class addCommentToBlog
 */

public class addCommentToBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCommentToBlog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] resultArr  = new String[4];

		Connection connect;
		PreparedStatement preparestatement;
		ResultSet resultSet;

		String dbName = "sampledb2";
		String userName = "john";
		String password = "pass1234";
		String hostname = "sampledb2.cpvy4fmhbooi.us-west-2.rds.amazonaws.com";
		String port = "3306";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String subject = request.getParameter("subject");
		String description = request.getParameter("description");
		String comment_sql = "INSERT INTO `sampledb2`.`comment`" +
							 "(`subject`, `description`)" +
							 "VALUES (" +
							 "'" + subject + "', " +
							 "'" + description + "');";

		String blogId = request.getParameter("blogId");
        String get_last_comment_id_sql = "SELECT LAST_INSERT_ID();";

        String posterUsername = request.getParameter("username");
        String comment_id = null;

		try {
			connect = DriverManager.getConnection(
				"jdbc:mysql://" + hostname + ":" + port + "/" + dbName, userName, password);

			preparestatement = connect.prepareStatement(comment_sql);
			preparestatement.executeUpdate();

			preparestatement = connect.prepareStatement(get_last_comment_id_sql);
			resultSet = preparestatement.executeQuery();

			while(resultSet.next()){
				comment_id = resultSet.getString("LAST_INSERT_ID()");
			}

			//link comment to user/blog
			comment_sql = "INSERT INTO `sampledb2`.`comment_to_blog`" +
								 "(`username`, `blog_id`, `comment_id`)" +
								 "VALUES (" +
								 "'" + posterUsername + "', " +
								 "'" + blogId + "', " +
								 "'" + comment_id + "');";

			preparestatement = connect.prepareStatement(comment_sql);
			preparestatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/singleblogpost?blogid=" + blogId).forward(request, response);
	}


}
