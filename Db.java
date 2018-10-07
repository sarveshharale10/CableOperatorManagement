import java.sql.*;

class Db{
	static Connection conn;
	
	static{
		try{
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/com","root","sarvesh");
		}catch(Exception e){}
	}

	static Connection getConnection(){
		return conn;
	}
}