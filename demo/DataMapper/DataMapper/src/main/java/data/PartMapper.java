package data;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Connection;
import model.Level;

 
//=== Maps between objects and tables
public class PartMapper {

    // must be put into Facade
    private Connection con;

    public PartMapper() {

        con = new DB().getConnection();
        // the connection will be released upon program 
        // termination by the garbage collector	
    }

    public String getPriceLevel(int pno) {
        String level = null;
        CallableStatement stmt = null;
        try {
            stmt = con.prepareCall("{call GetPartLevel(?,?)}");
            stmt.setInt(1, pno);
            stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
            stmt.execute();
            level = stmt.getString(2);
            
        } catch (SQLException e) {
            System.out.println("Fail in PartsMapper - call");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ignore) {
            }
        }

        return level;
    }    
}
