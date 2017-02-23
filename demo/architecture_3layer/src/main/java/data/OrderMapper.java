package data;

import domain.entity.Odetail;
import domain.entity.Order;
import domain.exception.OrderException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Feb 22, 2017
 */
public class OrderMapper {

    public Order getOrder(int id) throws OrderException {
        Order order = null;
        try {
            
            Connection conn = new DB().getConnection();
            String sql = "SELECT ono, cno, eno FROM orders WHERE ono = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int ono = rs.getInt("ono");
                int cno = rs.getInt("cno");
                int eno = rs.getInt("eno");
                order = new Order(ono, cno, eno);
            }
            
        } catch (SQLException ex) {
            throw new OrderException("Error in getOrder()"+ex.getMessage()); 
        }
        return order;
    }
    
    public void newOrder(Order order){
            Connection conn = new DB().getConnection();
        try {
            List<Odetail> details = order.getDetails();
            //Starter transaktion
            String ins_order = "INSERT INTO orders (ono, cno, eno) VALUES (?, ?, ?)";
            String ins_odetail = "INSERT INTO odetails (ono, pno, qty) VALUES (?, ?, ?)";
            PreparedStatement orderStmt = conn.prepareStatement(ins_order);
            PreparedStatement odetailStmt = conn.prepareStatement(ins_odetail);
            
            //Transaction
            conn.setAutoCommit(false);
            //Order
            orderStmt.setInt(1, order.getId());
            orderStmt.setInt(2, order.getCusId());
            orderStmt.setInt(3, order.getEmpId());
            orderStmt.executeUpdate();
            //Odetails
            for (Odetail detail : details) {
                odetailStmt.setInt(1, order.getId());
                odetailStmt.setInt(2, detail.getPno());
                odetailStmt.setInt(3, detail.getQty());
                odetailStmt.executeUpdate();
            }
            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                }
        }
    }
    
    public static void main(String[] args) {
        
        OrderMapper om = new OrderMapper();
        Order order = new Order(5, 1111, 1000);
        order.addDetails(new Odetail(order.getId(), 1, 10));
        order.addDetails(new Odetail(order.getId(), 10900, 5));
        
        om.newOrder(order);
        //Order order2 = om.getOrder(1020);
        //System.out.println(order2);
    }
}
