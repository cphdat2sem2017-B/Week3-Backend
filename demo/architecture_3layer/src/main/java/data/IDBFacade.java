package data;

import domain.entity.Customer;
import domain.entity.Employee;
import java.util.List;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Feb 21, 2017
 */
public interface IDBFacade {
Customer getCustomer(int id);
List<Customer> getCustomers();
Employee getEmployee(int id);
}
