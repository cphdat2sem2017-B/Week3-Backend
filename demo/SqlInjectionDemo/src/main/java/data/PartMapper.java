package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Part;


public class PartMapper {
    // must be put into Facade
    private Connection con;

    public PartMapper() {
        con = new DB().getConnection();       
    }

    public Part getPart(int pno) {
        ResultSet rs = null;
        Statement stmt = null;
        Part part = null;

        String SQLString
                = "select * "
                + "from parts "
                + "where pno = " + pno;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQLString);

            if (rs.next()) {
                 part = new Part(pno,
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println("Fail in PartMapper - getPart");
            System.out.println(e.getMessage());
        }       
        return part;
    }    
   
    public Part getPart2(int pno) {
        ResultSet rs = null;
        Statement stmt = null;
        Part part = null;

        String SQLString
                = "select * "
                + "from parts "
                + "where pno = " + pno;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQLString);

            if (rs.next()) {
                 part = new Part(pno,
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println("Fail in PartMapper - getPart");
            System.out.println(e.getMessage());
        }       
        return part;
    }    
    
    public Part getPartByName(String pname) {
        ResultSet rs = null;
        Statement stmt = null;
        Part part = null;

                 // + "where pname = 'Land Before Time I11' or 1=1";
        String SQLString = "select * from parts where pname = '" + pname + "'";
        String SQLString2 = "SELECT * FROM parts WHERE pname = ?";
          try {
            //stmt = con.createStatement();
            
            PreparedStatement pstmt = con.prepareStatement(SQLString2);
            pstmt.setString(1, pname);
            ResultSet rs2 = pstmt.executeQuery();
            
            
            
            
            
            
            
            rs = stmt.executeQuery(SQLString);

            if (rs.next()) {
                 part = new Part(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println("Fail in PartMapper - getPart");
            System.out.println(e.getMessage());
        }       
        return part;
    }    
   
       
}
