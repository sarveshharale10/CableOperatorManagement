import javax.swing.table.*;
import java.util.*;
import java.sql.*;

class ComplaintDao{

    Connection conn;
    Statement st;
    PreparedStatement prepared;
    int noOfColumns;

    ComplaintDao(){
        try {
            conn = Db.getConnection();
            st = conn.createStatement();
        } catch (Exception e) {
            System.err.println(e);
        }
        noOfColumns = 5;
    }

    Vector<Vector<String>> getAllOpenComplaints(){
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        try{
            ResultSet rs = st.executeQuery("select * from complaint where complaintNo not in(select complaintNo from closedComplaint)");
            while(rs.next()){
                Vector<String> row = new Vector<String>();
				for(int i = 1;i <= noOfColumns;i++){
                    row.add(rs.getString(i));
                }
				data.add(row);
			}
        }catch(Exception e){
            System.err.println(e);
        }
        return data;
    }



    void add(int complaintNo,int customerNo,String customerName,String description,java.sql.Date dateOpened){
        try{
            prepared = conn.prepareStatement("insert into complaint values(?,?,?,?,?)");
            prepared.setInt(1,complaintNo);
            prepared.setInt(2,customerNo);
            prepared.setString(3,customerName);
            prepared.setString(4,description);
            prepared.setDate(5,dateOpened);
            prepared.execute();
        }catch(Exception e){
            System.err.println(e);
        }
    }

    void closeComplaint(int complaintNo,java.sql.Date dateClosed,String remarks){
        try {
            prepared = conn.prepareStatement("insert into closedComplaint values(?,?,?)");
            prepared.setInt(1,complaintNo);
            prepared.setDate(2,dateClosed);
            prepared.setString(3,remarks);
            prepared.execute();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}