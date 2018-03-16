package user.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import user.dao.UserDao;
import user.domain.User;

/**
 * logic functions such as register, login
 * @author changxin bai
 *
 */
public class UserService {
	private UserDao userDao = new UserDao();
	
	/**
	 * register a user
	 * @param form
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void regist(User form) throws UserException, ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		// check the user name
		User user = userDao.findByUsername(form.getUsername());
		if(user.getUsername()!=null && user.getUsername().equals(form.getUsername())) throw new UserException("This user name has been registered!");
		userDao.add(form);
	}
	
	
	/**
	 * Login function
	 * @param form
	 * @return
	 * @throws UserException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void login(User form) throws UserException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		User user = userDao.findByUsername(form.getUsername());
		if(user.getUsername()==null) throw new UserException("The user is not in the database");
		
		String password = user.getPassword();
		
		if(password!=null && !password.equals(form.getPassword()))
			throw new UserException(" The password is not right");
		
	}
	
	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return userDao.findall();
		
	}
	
	public List<Object> findOthers(String username) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return userDao.findOthers(username);
		
	}
	
	public void Instantiate() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connect = DriverManager
			      // .getConnection("jdbc:mysql://141.217.48.128:3306/dataview?"
				//              + "user=shiyong&password=view1234");
			    		   	.getConnection("jdbc:mysql://localhost/sampledb?"
				              + "user=john&password=pass1234");
			
			String sql = "Call Instantiate();";
			PreparedStatement preparestatement = connect.prepareStatement(sql); 
			
			 preparestatement.executeQuery();
			 

			 
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	
	
	
}
