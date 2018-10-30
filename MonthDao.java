import java.sql.*;

class MonthDao{
    Connection conn;
    Statement st;

    MonthDao(){
        try{
            conn = Db.getConnection();
            st = conn.createStatement();
        }catch(Exception e){
            System.err.println(e);
        }
    }

    int getMonth(){
        int returnValue = -1;
        try {
            ResultSet rs = st.executeQuery("select monthIndex from month");
            rs.next();
            returnValue = rs.getInt(1);
        } catch (Exception e) {
            System.err.println(e);
        }
        return returnValue;
    }

    void setMonth(int month){
        try{
            st.executeUpdate("update month set monthIndex = "+month);
        }catch(Exception e){
            System.err.println(e);
        }
    }

}