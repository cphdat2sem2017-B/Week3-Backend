package data;

import domain.entity.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Feb 21, 2017
 */
public class CustomerMapper {

    public Customer getCustomer(int id) {
        Customer customer = null;
        try {
            Connection conn = new DB().getConnection();
            String sql = "SELECT cno, cname, street, zip, phone FROM customers WHERE cno = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int cno = rs.getInt("cno");
                String cname = rs.getString("cname");
                String street = rs.getString("street");
                int zip = rs.getInt("zip");
                String phone = rs.getString("phone");
                customer = new Customer(id, cname, street, zip, phone);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return customer;
    }
    //public List<Customer> getCustomers();
    //public void createCustomer(Customer customer);
    public static void main(String[] args) {
        Customer c = new CustomerMapper().getCustomer(1111);
        System.out.println(c);
    }
}
