import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DBconnector {
	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "password";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;
	
	
	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection(String dbName_in) throws SQLException {	
			Connection conn = null;
			Properties connectionProps = new Properties();
			connectionProps.put("user", this.userName);
			connectionProps.put("password", this.password);
			conn = DriverManager.getConnection("jdbc:mysql://"
					+ this.serverName + ":" + this.portNumber + "/" + dbName_in,
					connectionProps);

			return conn;
	}

	// helper for executeUpdate to avoid creating a lot createStatement
	public boolean executeInsert(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {
	    	// This will run whether we throw an exception or not
	       // if (stmt != null) { stmt.close(); }
	    }
	}
	
	public ResultSet executeSelect(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(command); // This will throw a SQLException if it fails
	        return rs;
	    } finally {
	    	// This will run whether we throw an exception or not
	        //if (stmt != null) { stmt.close(); }
	    }
	}
	
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    stmt = conn.createStatement();
	    stmt.executeUpdate(command); // This will throw a SQLException if it fails
	    return true;
	}
}
