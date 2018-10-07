import java.sql.*;

class Test{
	public static void main(String[] args) throws Exception{
            Connection conn = Db.getConnection();
            PreparedStatement st = conn.prepareStatement("insert into customer values(?,?,?,?,?,?)");
				st.setInt(1,1);
				st.setString(2,"sarvesh");
				st.setString(3,"asdfjasf");
				st.setString(4,"992833");
				st.setInt(5,2000);
				st.setInt(6,0);
				st.executeUpdate();
	}
}