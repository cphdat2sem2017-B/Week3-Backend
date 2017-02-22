package domain.entity;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk created on Feb 21, 2017
 */
public class Customer {

    private int id;
    private String name;
    private String street;
    private int zip;
    private String phone;

    public Customer(int id, String name, String street, int zip, String phone) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.phone = phone;
    }

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", street=" + street + ", zip=" + zip + ", phone=" + phone + '}';
    }
    
}
