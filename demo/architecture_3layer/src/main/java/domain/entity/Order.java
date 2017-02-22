package domain.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Feb 21, 2017 
 */
public class Order {
    private int id;
    private int cusId;
    private int empId;
    List<Odetail> details;

    public Order(int id, int cusId, int empId) {
        this.id = id;
        this.cusId = cusId;
        this.empId = empId;
        details = new ArrayList();
    }

    public Order() {
    }

    public int getId() {
        return id;
    }
    
    public void addDetails(Odetail detail){
        details.add(detail);
    }
    
    public List<Odetail> getDetails(){
        return this.details;
    }
    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", cusId=" + cusId + ", empId=" + empId + '}';
    }

}
