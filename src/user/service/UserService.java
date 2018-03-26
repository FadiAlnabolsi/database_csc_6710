package user.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;


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
		System.out.println("Initiation Connection!!");
		try {
			String dbName = "sampledb2";
			String userName = "john";
			String password = "pass1234";
			String hostname = "sampledb2.cpvy4fmhbooi.us-west-2.rds.amazonaws.com";
			String port = "3306";

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connect = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + port + "/" + dbName, userName, password);

			String sql_user_table = "CREATE TABLE user (\n" +
					"first_name VARCHAR(20),\n" +
					"    last_name VARCHAR(20),\n" +
					"    username VARCHAR(20) NOT NULL,\n" +
					"    password VARCHAR(50) NOT NULL,\n" +
					"    email VARCHAR(60) NOT NULL,\n" +
					"    CHECK (username=\"Fadi\"),\n" +
					"	 PRIMARY KEY ( username, email )\n" +
					");";
			String sql_hobbies_table = "CREATE TABLE hobbies (\n" +
					"    id integer NOT NULL AUTO_INCREMENT,\n" +
					"    name varchar(30),\n" +
					"    PRIMARY KEY ( id )\n" +
					");";
			String sql_user_has_hobby = "CREATE TABLE user_has_hobby (\n" +
					"    username varchar(20),\n" +
					"    hobby integer,\n" +
					"    PRIMARY KEY(username, hobby),\n" +
					"    constraint fk_hobby_username foreign key (username) references user(username),\n" +
					"    constraint fk_hobby_hobby foreign key (hobby) references hobbies(id)\n" +
					");";
			String sql_user_follows_user = "CREATE TABLE user_follows (\n" +
					"    follower_name varchar(20),\n" +
					"    following_name varchar(20),\n" +
					"    PRIMARY KEY (follower_name, following_name),\n" +
					"    constraint fk_follow_user foreign key (follower_name) references user(username),\n" +
					"    constraint fk_follow_user_follows foreign key (following_name) references user(username)\n" +
					");";
			String sql_blog = "CREATE TABLE blog (\n" +
					"    id integer NOT NULL AUTO_INCREMENT,\n" +
					"    subject varchar(100),\n" +
					"    description varchar(1000),\n" +
					"    date datetime default CURRENT_TIMESTAMP,\n" +
					"    PRIMARY KEY (id)\n" +
					");";
			String sql_user_blog = "CREATE TABLE user_blog (\n" +
					"  username varchar(30),\n" +
					"  blog_id integer,\n" +
					"  PRIMARY KEY (username, blog_id),\n" +
					"  constraint fk_blog_user foreign key (username) references user(username),\n" +
					"  constraint fk_blog_blog foreign key (blog_id) references blog(id)\n" +
					");";
			String sql_tags = "CREATE TABLE tags (\n" +
					"  id integer NOT NULL AUTO_INCREMENT,\n" +
					"  description varchar(30),\n" +
					"  PRIMARY KEY (id)\n" +
					");";
			String sql_blog_has_tags = "CREATE TABLE blog_has_tags (\n" +
					"  tag_id integer,\n" +
					"  blog_id integer,\n" +
					"  constraint fk_blog_to_tag foreign key (tag_id) references tags(id),\n" +
					"  constraint fk_tag_to_blog foreign key (blog_id) references blog(id)\n" +
					");";
			String sql_comment = "CREATE TABLE comment (\n" +
					"  id integer NOT NULL AUTO_INCREMENT,\n" +
					"  subject VARCHAR(100),\n" +
					"  description VARCHAR(1000),\n" +
					"  date datetime default CURRENT_TIMESTAMP,\n" +
					"  primary key (id)\n" +
					");";

			String sql_comment_to_blog = "CREATE TABLE comment_to_blog (\n" +
					"  username varchar(30),\n" +
					"  blog_id integer,\n" +
					"  comment_id integer,\n" +
					"  constraint fk_comment_to_blog_user_id foreign key (username) references user(username),\n" +
					"  constraint fk_comment_to_blog_blog_id foreign key (blog_id) references blog(id),\n" +
					"  constraint fk_comment_to_blog_comment_id foreign key (comment_id) references comment(id)\n" +
					");";

			String sql_drop_table_user = "DROP TABLE user;";
			String sql_drop_table_hobbies = "DROP TABLE hobbies;";
			String sql_drop_table_user_has_hobbies = "DROP TABLE user_has_hobby;";
			String sql_drop_table_user_follows = "DROP TABLE user_follows;";
			String sql_drop_table_blog="DROP TABLE blog;";
			String sql_drop_table_user_blog="DROP TABLE user_blog;";
			String sql_drop_table_tags="DROP TABLE tags;";
			String sql_drop_table_blog_has_tags="DROP TABLE blog_has_tags;";
			String sql_drop_table_comment="DROP TABLE comment;";
			String sql_drop_table_comment_to_blog="DROP TABLE comment_to_blog;";

			Statement statement = null;
			try {
				DatabaseMetaData dbm = connect.getMetaData();

				statement = connect.createStatement();
				//check if user table exists
				ResultSet tables = dbm.getTables(null, null, "user", null);
				if (tables.next()){
					System.out.println("deleting tables!!");
					//user table exists, delete them
					statement.executeUpdate(sql_drop_table_comment_to_blog);
					statement.executeUpdate(sql_drop_table_user_has_hobbies);
					statement.executeUpdate(sql_drop_table_user_follows);
					statement.executeUpdate(sql_drop_table_user_blog);
					statement.executeUpdate(sql_drop_table_blog_has_tags);
					statement.executeUpdate(sql_drop_table_hobbies);
					statement.executeUpdate(sql_drop_table_blog);
					statement.executeUpdate(sql_drop_table_tags);
					statement.executeUpdate(sql_drop_table_comment);
					statement.executeUpdate(sql_drop_table_user);
				}

				//create the tables
				statement.executeUpdate(sql_user_table);
				statement.executeUpdate(sql_hobbies_table);
				statement.executeUpdate(sql_user_has_hobby);
				statement.executeUpdate(sql_user_follows_user);
				statement.executeUpdate(sql_blog);
				statement.executeUpdate(sql_user_blog);
				statement.executeUpdate(sql_tags);
				statement.executeUpdate(sql_blog_has_tags);
				statement.executeUpdate(sql_comment);
				statement.executeUpdate(sql_comment_to_blog);

				String [] queries = {
						//  fill the user table
						"insert into user (first_name, last_name, username, password, email) values ('Fadi', 'Al', 'McFads1', 'password', 'fadi@wsu.com')",
						"insert into user (first_name, last_name, username, password, email) values ('Al', 'Bay', 'BTA', 'password', 'bta@wsu.com')",
						"insert into user (first_name, last_name, username, password, email) values ('Ob', 'Bader', 'Obeidah', 'password', 'ob@wsu.com')",
						"insert into user (first_name, last_name, username, password, email) values ('Shair', 'Bey', 'Shair', 'password', 'shair@wsu.com')",
						"insert into user (first_name, last_name, username, password, email) values ('Mouz', 'Mouz', 'Mouz', 'password', 'mouz@wsu.com')",
						"insert into user (first_name, last_name, username, password, email) values ('Ack', 'Aljam', 'Ack', 'password', 'Ack@wsu.com')",
						"insert into user (first_name, last_name, username, password, email) values ('Moneer', 'Al', 'Moneer', 'password', 'moneer@wsu.com')",
						"insert into user (first_name, last_name, username, password, email) values ('Hannan', 'Al', 'Hannan', 'password', 'hannan@wsu.com')",
						"insert into user (first_name, last_name, username, password, email) values ('Amira', 'Al', 'Amira', 'password', 'amira@wsu.com')",
						"insert into user (first_name, last_name, username, password, email) values ('Basel', 'Al', 'Bas', 'password', 'bas@wsu.com')",
						"insert into user (first_name, last_name, username, password, email) values ('muheeb', 'nab', 'Moe', 'password', 'muheeb@wsu.com')",
						"insert into user (first_name, last_name, username, password, email) values ('fatima', 'mak', 'fatima', 'password', 'fatima@wsu.com')",
						"insert into user (first_name, last_name, username, password, email) values ('john', 'john', 'john', 'pass1234', 'john@wsu.com')",

						//fill the hobby table
						"insert into hobbies (name) values ('hiking')",
						"insert into hobbies (name) values ('biking')",
						"insert into hobbies (name) values ('martial arts')",
						"insert into hobbies (name) values ('singing')",
						"insert into hobbies (name) values ('working out')",
						"insert into hobbies (name) values ('fitness')",
						"insert into hobbies (name) values ('math')",
						"insert into hobbies (name) values ('acadamia')",
						"insert into hobbies (name) values ('gaming')",
						"insert into hobbies (name) values ('reading')",
						"insert into hobbies (name) values ('philosphy')",

						//fill the user has hobby table
						"insert into user_has_hobby (username, hobby) values ('McFads1', 1)",
						"insert into user_has_hobby (username, hobby) values ('BTA', 2)",
						"insert into user_has_hobby (username, hobby) values ('Obeidah', 3)",
						"insert into user_has_hobby (username, hobby) values ('Shair', 4)",
						"insert into user_has_hobby (username, hobby) values ('Mouz', 5)",
						"insert into user_has_hobby (username, hobby) values ('Ack', 6)",
						"insert into user_has_hobby (username, hobby) values ('Moneer', 7)",
						"insert into user_has_hobby (username, hobby) values ('Hannan', 8)",
						"insert into user_has_hobby (username, hobby) values ('Amira', 9)",
						"insert into user_has_hobby (username, hobby) values ('Bas', 10)",
						"insert into user_has_hobby (username, hobby) values ('Moe', 11)",

						//fill the user follows user table
						"insert into user_follows (follower_name, following_name) values ('McFads1', 'Obeidah')",
						"insert into user_follows (follower_name, following_name) values ('BTA', 'Shair')",
						"insert into user_follows (follower_name, following_name) values ('Obeidah', 'Ack')",
						"insert into user_follows (follower_name, following_name) values ('Shair', 'BTA')",
						"insert into user_follows (follower_name, following_name) values ('Mouz', 'Moneer')",
						"insert into user_follows (follower_name, following_name) values ('Ack', 'McFads1')",
						"insert into user_follows (follower_name, following_name) values ('Moneer', 'Amira')",
						"insert into user_follows (follower_name, following_name) values ('Hannan', 'McFads1')",
						"insert into user_follows (follower_name, following_name) values ('Amira', 'Moneer')",
						"insert into user_follows (follower_name, following_name) values ('Bas', 'Moe')",
						"insert into user_follows (follower_name, following_name) values ('Moe', 'Bas')",

						//fill the blog tables
						"insert into blog (subject, description, date) values ('launch!', 'hello world!', '2018-01-29')",
						"insert into blog (subject, description, date) values ('workout!', 'benched two plates today!', '2018-01-29')",
						"insert into blog (subject, description, date) values ('star wars!', 'just finished watching star wars, was great!', '2018-01-29')",
						"insert into blog (subject, description, date) values ('work!', 'work is such a bummer, need to quit', '2018-01-29')",
						"insert into blog (subject, description, date) values ('sleepy!', 'I dont get enough of it!', '2018-01-29')",
						"insert into blog (subject, description, date) values ('anime!', 'attack on titan is best anime!', '2018-01-29')",
						"insert into blog (subject, description, date) values ('squats!', 'squats are by far the hardest workout', '2018-01-29')",
						"insert into blog (subject, description, date) values ('music!', 'taylor swift is my favorite artist', '2018-01-29')",
						"insert into blog (subject, description, date) values ('video games!', 'dark souls is my favorite game', '2018-01-29')",
						"insert into blog (subject, description, date) values ('sleep!', 'I dont get enough sleep. maybe 4 hours a night', '2018-01-29')",
						"insert into blog (subject, description, date) values ('crypto!', 'I lost so much money because of crypto currency', '2018-01-29')",

						//user has blog
						"insert into user_blog (username, blog_id) values ('McFads1', 1)",
						"insert into user_blog (username, blog_id) values ('BTA', 2)",
						"insert into user_blog (username, blog_id) values ('Obeidah', 3)",
						"insert into user_blog (username, blog_id) values ('Shair', 4)",
						"insert into user_blog (username, blog_id) values ('Mouz', 5)",
						"insert into user_blog (username, blog_id) values ('Ack', 6)",
						"insert into user_blog (username, blog_id) values ('Moneer', 7)",
						"insert into user_blog (username, blog_id) values ('Hannan', 8)",
						"insert into user_blog (username, blog_id) values ('Amira', 9)",
						"insert into user_blog (username, blog_id) values ('Amira', 10)",
						"insert into user_blog (username, blog_id) values ('Moe', 11)",

						//fill the tags table
						"insert into tags (description) values ('happy')",
						"insert into tags (description) values ('sad')",
						"insert into tags (description) values ('mad')",
						"insert into tags (description) values ('exciting')",
						"insert into tags (description) values ('insane')",
						"insert into tags (description) values ('accident')",
						"insert into tags (description) values ('urgent')",
						"insert into tags (description) values ('rare')",
						"insert into tags (description) values ('mustsee')",
						"insert into tags (description) values ('lame')",
						"insert into tags (description) values ('overrated')",

						//fill the blog has tags table
						"insert into blog_has_tags (tag_id, blog_id) values (1, 1)",
						"insert into blog_has_tags (tag_id, blog_id) values (2, 2)",
						"insert into blog_has_tags (tag_id, blog_id) values (3, 3)",
						"insert into blog_has_tags (tag_id, blog_id) values (4, 4)",
						"insert into blog_has_tags (tag_id, blog_id) values (5, 5)",
						"insert into blog_has_tags (tag_id, blog_id) values (6, 6)",
						"insert into blog_has_tags (tag_id, blog_id) values (7, 7)",
						"insert into blog_has_tags (tag_id, blog_id) values (8, 8)",
						"insert into blog_has_tags (tag_id, blog_id) values (9, 9)",
						"insert into blog_has_tags (tag_id, blog_id) values (10, 10)",
						"insert into blog_has_tags (tag_id, blog_id) values (11, 11)",

						//fill the comments table
						"insert into comment (subject, description) values ('nice!', 'nice blog post!')",
						"insert into comment (subject, description) values ('amazing!', 'you changed my life with this post!')",
						"insert into comment (subject, description) values ('average!', 'ive seen better posts than this!')",
						"insert into comment (subject, description) values ('sleepy!', 'i fell asleep reading this')",
						"insert into comment (subject, description) values ('boring!', 'this post made me feel nothing')",
						"insert into comment (subject, description) values ('triggered!', 'I am so offended by this post! take it down asap!!')",
						"insert into comment (subject, description) values ('terrible!', 'your opinion is terrible and you should feel terrible')",
						"insert into comment (subject, description) values ('worst!', 'this is the worst comment ive ever read')",
						"insert into comment (subject, description) values ('polarizing!', 'i dont like your opinion but damnit i respect you')",
						"insert into comment (subject, description) values ('funny!', 'I was loling the whole time reading')",
						"insert into comment (subject, description) values ('apologies!', 'I disagree, please forgive me!!!!')",

						//fill the comment_to_blog_table
						"insert into comment_to_blog (username, blog_id, comment_id) values ('McFads1', 11, 11)",
						"insert into comment_to_blog (username, blog_id, comment_id) values ('BTA', 10, 10)",
						"insert into comment_to_blog (username, blog_id, comment_id) values ('Obeidah', 9, 9)",
						"insert into comment_to_blog (username, blog_id, comment_id) values ('Shair', 8, 8)",
						"insert into comment_to_blog (username, blog_id, comment_id) values ('Mouz', 6, 6)",
						"insert into comment_to_blog (username, blog_id, comment_id) values ('Ack', 7, 7)",
						"insert into comment_to_blog (username, blog_id, comment_id) values ('Moneer', 5, 5)",
						"insert into comment_to_blog (username, blog_id, comment_id) values ('Hannan', 4, 4)",
						"insert into comment_to_blog (username, blog_id, comment_id) values ('Amira', 3, 3)",
						"insert into comment_to_blog (username, blog_id, comment_id) values ('Bas', 2, 2)",
						"insert into comment_to_blog (username, blog_id, comment_id) values ('Moe', 1, 1)"
				};

				Statement fillDbStatement = null;
				fillDbStatement = connect.createStatement();

				for (String query : queries){
					fillDbStatement.addBatch(query);
				}

				fillDbStatement.executeBatch();
				fillDbStatement.close();
			} catch(SQLException e) {
				throw new RuntimeException(e);
			} finally {
				System.out.println("nothing");
			}
		} catch(SQLException e) {} finally {}
	}
}
