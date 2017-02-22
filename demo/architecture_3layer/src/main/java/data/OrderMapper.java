package data;

import domain.entity.Customer;
import domain.entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Feb 22, 2017
 */
public class OrderMapper {

    public Order getOrder(int id) {
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
            ex.printStackTrace();
        }
        return order;
    }
    public static void main(String[] args) {
        OrderMapper om = new OrderMapper();
        Order order = om.getOrder(1020);
        System.out.println(order);
    }
}
