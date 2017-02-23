package data;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Ronnie Dalsgaard 
 */
public class Connector {
	private Connection connection = null;
	
	//Constants
	private static final String IP	     = "127.4.180.130";
	private static final int    PORT     = 3306;
	public static final String DATABASE  = "glazier";
	private static final String USERNAME = "glazier_admin"; 
	private static final String PASSWORD = "1234";	
	
	/**
	 * Connects to the specified database
	 * 
	 * @param ip the ip to connect to
	 * @param port the port to connect on
	 * @param database the name of the database to use
	 * @param username the username to login with
	 * @param password the password to login with
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connector(String ip, int port, String database, String username, String password)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// Do this when using a "normal" SQL server
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String url = "jdbc:mysql://" + ip + ":" + port + "/" + database + "?autoReconnect=true&useSSL=false";
		this.connection = (Connection) DriverManager.getConnection(url, username, password);
	}
	public Connector() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		this(IP, PORT, DATABASE, USERNAME, PASSWORD);
	}

	/**
	 * Executes the query on the database
	 * 
	 * @param query the query to be executed
	 * @return a ResultSet containing the rows which fulfills the query
	 * @throws DALException
	 */
	public ResultSet doQuery(String query) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeQuery(query);
	}

	/**
	 * Executes an update on the database
	 * 
	 * @param query the query to be executed
	 * @return 1 if update successful, else 0
	 * @throws DALException
	 */
	public boolean doUpdate(String query) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeUpdate(query) > 0;
	}
	
	/**
	 * Create a prepared statement
	 * 
	 * @param query the content of the statement
	 * @return the statement
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(String query) throws SQLException{
		Statement statement = connection.createStatement();
		return statement.getConnection().prepareStatement(query);
	}
}