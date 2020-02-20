package hel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	
	
	
	public class DBConnection {
	    public Connection getConnection() {
	        Connection connection = null;
	      
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myTask", "root", "Dvnr@1991");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return connection;
	    }
	}


