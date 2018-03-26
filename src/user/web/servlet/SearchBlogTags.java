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
 * Servlet implementation class SearchBlogTags
 */
public class SearchBlogTags extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBlogTags() {
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
		List<String[]> resultArrList = new ArrayList<String[]>();
		String[] resultArr  = new String[4];

		String tag = request.getParameter("tag");
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

		String sql = "SELECT blog.id, blog.subject, blog.description, tags.description as tag " +
					 "FROM blog_has_tags, blog, tags " +
					 "WHERE blog_has_tags.blog_id = blog.id and blog_has_tags.tag_id = tags.id " +
					 "and tags.description='" + tag + "'";

		try {
			connect = DriverManager.getConnection(
				"jdbc:mysql://" + hostname + ":" + port + "/" + dbName, userName, password);
			preparestatement = connect.prepareStatement(sql);
			resultSet = preparestatement.executeQuery();

		    while (resultSet.next()) {
			  resultArr  = new String[4];

		      String id = resultSet.getString("id");
		      String subject = resultSet.getString("subject");
		      String description = resultSet.getString("description");
  		      String resultTag = resultSet.getString("tag");
  		      resultArr[0] = id;
  		      resultArr[1] = subject;
  		      resultArr[2] = description;
  		      resultArr[3] = resultTag;

  		      resultArrList.add(resultArr);
		    }

		    request.setAttribute("results", resultArrList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/Queryresult/list-blog-tag-search-results.jsp").forward(request, response);
	}

}
