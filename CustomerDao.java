import javax.swing.table.*;
import java.util.*;
import java.sql.*;

class CustomerDao{

    Connection conn;
    Statement st;
    PreparedStatement prepared;

    CustomerDao(){
        try {
            conn = Db.getConnection();
            st = conn.createStatement();
        } catch (Exception e) {
            System.err.println(e);
        }
        
    }

    Vector<Vector<String>> getAll(String... columns){
        Vector<Vector<String>> data = new Vector<Vector<String>>();

        try{
            ResultSet rs = st.executeQuery("select "+String.join(",",columns)+" from customer");       

			while(rs.next()){
                Vector<String> row = new Vector<String>();
				for(int i = 1;i <= columns.length;i++){
                    row.add(rs.getString(i));
                }
				data.add(row);
			}
        }catch(Exception e){
            System.err.println(e);
        }
        return data;
    }

    Vector<Vector<String>> getAllWithDue(String... columns){
        Vector<Vector<String>> data = new Vector<Vector<String>>();

        try{
            ResultSet rs = st.executeQuery("select "+String.join(",",columns)+" from customer where dueAmount > 0");       

			while(rs.next()){
                Vector<String> row = new Vector<String>();
				for(int i = 1;i <= columns.length;i++){
                    row.add(rs.getString(i));
                }
				data.add(row);
			}
        }catch(Exception e){
            System.err.println(e);
        }
        return data;
    }

    Vector<String> getByCustomerNo(int customerNo){
        Vector<String> row = new Vector<String>();
        try{
            ResultSet rs = st.executeQuery("select * from customer where customerNo="+customerNo);
            rs.next();
			row.add(rs.getString(1));
			row.add(rs.getString(2));
			row.add(rs.getString(3));
			row.add(rs.getString(4));
			row.add(rs.getString(5));
        }catch(Exception e){
            System.err.println(e);
        }
        return row;
    }

    void add(int customerNo,String customerName,String address,String contactNo,int monthlyCharge,int dueAmount){
        try{
            prepared = conn.prepareStatement("insert into customer values(?,?,?,?,?,?)");
            prepared.setInt(1,customerNo);
			prepared.setString(2,customerName);
			prepared.setString(3,address);
			prepared.setString(4,contactNo);
			prepared.setInt(5,monthlyCharge);
			prepared.setInt(6,dueAmount);
			prepared.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }

    void update(int customerNo,String customerName,String address,String contactNo,int monthlyCharge){
        try{
            prepared = conn.prepareStatement("update customer set customerName = ?,address = ?,contactNo = ?,monthlyCharge = ? where customerNo = ?");
            prepared.setString(1,customerName);
			prepared.setString(2,address);
			prepared.setString(3,contactNo);
			prepared.setInt(4,monthlyCharge);
			prepared.setInt(5,customerNo);
			prepared.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }
    }

    void updateDues(){
        try{
            st.executeUpdate("update customer set dueAmount = dueAmount + monthlyCharge");
        }catch(Exception e){
            System.err.println(e);
        }
    }
}