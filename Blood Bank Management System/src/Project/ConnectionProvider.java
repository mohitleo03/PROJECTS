package Project;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class ConnectionProvider {

	public static Connection getCon() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank","root","A818CAD3");
			return conn;
		}
		catch (Exception e){
			System.out.print(e);
			return null;
			
		}
	}
}
