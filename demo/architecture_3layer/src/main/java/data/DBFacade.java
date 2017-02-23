package data;

import domain.entity.Customer;
import domain.entity.Employee;
import domain.entity.Order;
import domain.exception.OrderException;
import java.util.List;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Feb 21, 2017 
 */
public class DBFacade implements IDBFacade{
    CustomerMapper cm = new CustomerMapper();
    OrderMapper om = new OrderMapper();
    
    @Override
    public Customer getCustomer(int id) {
        return cm.getCustomer(id);
    }

    @Override
    public List<Customer> getCustomers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee getEmployee(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order getOrder(int id) throws OrderException {
        return om.getOrder(id);
    }

}
