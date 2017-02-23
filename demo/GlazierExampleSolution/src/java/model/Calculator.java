package model;


import data.DAL;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Calculator {
        public enum FRAMETYPE {TYPE1, TYPE2, TYPE3};
	private double height;
	private double width;
        private double frameprice;
        private double glassprice;
	
	public Calculator(double height, double width, FRAMETYPE frametype) {
            this.height = height/100d;
            this.width = width/100d;
            DAL dal = new DAL();
            try {
                frameprice = dal.getFrameprice(frametype);
                glassprice = dal.getGlassprice();
            } catch (Exception ex) {
                System.out.println("Unable to retrieve frame price!");
                ex.printStackTrace();
                System.exit(0);
            }
	}

	public double getGlassPrice(){
		return height * width * glassprice;
	}
	
	public double getFramePrice(){
		return (2 * height + 2 * width) * frameprice;
	}
	
	public double getTotalPrice(){
		return getGlassPrice() + getFramePrice();
	}
	
}
