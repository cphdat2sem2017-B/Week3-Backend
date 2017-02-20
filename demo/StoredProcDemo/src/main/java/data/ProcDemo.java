package data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Nov 10, 2016
 */
public class ProcDemo {
//    
    PreparedStatement cusByIdPstmt = null;

    public String getPriceLevel(int pno) {
        String level = null;
        CallableStatement stmt = null;
        try {
            stmt = DB.getConnection().prepareCall("{call GetPartLevel(?,?)}");
            stmt.setInt(1, pno);
            stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
            stmt.execute();
            level = stmt.getString(2);
        } catch (SQLException ignore) {
        }
        return level;
    }

    public String getCustomerById(int id) {
        String cname = null;
                
        try {
            if(cusByIdPstmt == null)
                cusByIdPstmt = createcusByIdPstmt();
            cusByIdPstmt.setInt(1, id);
            ResultSet rs = cusByIdPstmt.executeQuery();
            rs.next();
            cname = rs.getString("cname");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            return cname;
    }

    private PreparedStatement createcusByIdPstmt() throws SQLException{
            String sql = "SELECT cname FROM customers WHERE cno = ?"; //prepared statement
//            String sql2 = "SELECT cname FROM customers WHERE cno ="+id; //alm statement
            Connection conn = DB.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            return pstmt;
}

    public static void main(String[] args) {
        ProcDemo pd = new ProcDemo();
        System.out.println("getPriceLevel");
        System.out.println(pd.getPriceLevel(10800));
        System.out.println("getCustomerById");
        System.out.println(pd.getCustomerById(1111));
    }
}
