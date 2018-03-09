package user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




import java.util.ArrayList;
import java.util.List;

import user.domain.User;



/**
 * DDL functions performed in database
 * @author changxin bai
 *
 */
public class UserDao {
	
	
	/**
	 * get the search result with username 
	 * @param username
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public User findByUsername(String username) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		User user = new User();
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    String dbName = "sampledb2";
		    String userName = "john";
		    String password = "pass1234";
		    String hostname = "sampledb2.cpvy4fmhbooi.us-west-2.rds.amazonaws.com";
		    String port = "3306";
			Connection connect = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + dbName, userName, password);
			
		    String sql = "select * from user where username=?";
		    PreparedStatement preparestatement = connect.prepareStatement(sql); 
		    preparestatement.setString(1,username);
		    ResultSet resultSet = preparestatement.executeQuery();
		    //ResultSet resultSet  = preparestatement.executeUpdate();
		    while(resultSet.next()){
		    	String user_name = resultSet.getString("username");
		    	if(user_name.equals(username)){
		    		user.setUsername(resultSet.getString("username"));
		    		user.setPassword(resultSet.getString("password"));
		    		user.setEmail(resultSet.getString("email"));
		    		}
		    }
		    
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return user;
	}
	
	
	
	
	/**
	 * insert User
	 * @param user
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void add(User user) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    String dbName = "sampledb2";
		    String userName = "john";
		    String password = "pass1234";
		    String hostname = "sampledb2.cpvy4fmhbooi.us-west-2.rds.amazonaws.com";
		    String port = "3306";
			Connection connect = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + dbName, userName, password);

			String sql = "insert into user values(?,?,?,?,?)";
			PreparedStatement preparestatement = connect.prepareStatement(sql);
		    preparestatement.setString(1,user.getFirstName());
		    preparestatement.setString(2,user.getLastName());
		    preparestatement.setString(3,user.getUsername());
		    preparestatement.setString(4,user.getPassword());
		    preparestatement.setString(5,user.getEmail());
		    preparestatement.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		List<Object> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		    String dbName = "sampledb2";
		    String userName = "john";
		    String password = "pass1234";
		    String hostname = "sampledb2.cpvy4fmhbooi.us-west-2.rds.amazonaws.com";
		    String port = "3306";
			Connection connect = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + dbName, userName, password);
			
			String sql = "select * from user";
			PreparedStatement preparestatement = connect.prepareStatement(sql); 
			ResultSet resultSet = preparestatement.executeQuery();
			
			while(resultSet.next()){
				User user = new User();
				user.setUsername(resultSet.getString("username"));
	    			user.setPassword(resultSet.getString("password"));
	    			user.setEmail(resultSet.getString("email"));
	    			user.setFirstName(resultSet.getString("first_name"));
	    			user.setLastName(resultSet.getString("last_name"));
	    			list.add(user);
			 }
			 
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
		
	}
		
}
