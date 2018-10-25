import javax.swing.table.*;
import java.util.*;
import java.sql.*;

class PaymentDao{
    Connection conn;
    Statement st;
    PreparedStatement prepared;

    PaymentDao(){
        try {
            conn = Db.getConnection();
            st = conn.createStatement();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    Vector<Vector<String>> getByCustomerNo(int customerNo){
        Vector<Vector<String>> data = new Vector<Vector<String>>();
        try{
            ResultSet rs = st.executeQuery("select dateOfPayment,amount from payment where customerNo="+customerNo);
			while(rs.next()){
                Vector<String> row = new Vector<String>();
				for(int i = 1;i <= 2;i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
        }catch(Exception e){
            System.err.println(e);
        }
        return data;
    }
}