package data;


import java.sql.ResultSet;
import java.sql.SQLException;

public class DAL {
    
    public double getFrameprice(Calculator.FRAMETYPE frametype) throws Exception{
        try {
            Connector con = new Connector();
            ResultSet res = con.doQuery("SELECT * FROM `prices` WHERE `id` = '"+frametype.toString().toLowerCase()+"';");
            if(!res.next()) throw new Exception("Empty ResultSet!");
            double price = res.getDouble("price");
            return price;
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new Exception("Database exception:\n"+e.getMessage()+"!");
        }
    }
    
    public double getGlassprice() throws Exception{
        try {
            Connector con = new Connector();
            ResultSet res = con.doQuery("SELECT * FROM `prices` WHERE `id` = 'glass';");
            if(!res.next()) throw new Exception("Empty ResultSet!");
            double price = res.getDouble("price");
            return price;
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new Exception("Database exception:\n"+e.getMessage()+"!");
        }
    }
    
    public static void main(String[] args) {
        try {
            System.out.println("price = "+new DAL().getFrameprice(Calculator.FRAMETYPE.TYPE1));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
