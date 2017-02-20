/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk
 */
public class ProcDemoTest {
    
    public ProcDemoTest() {
    }

    /**
     * Test of getPriceLevel method, of class ProcDemo.
     */
    @Test
    public void testGetPriceLevel() {
        System.out.println("getPriceLevel");
        int pno = 10800;
        String expResult = "LOW";
        String result = new ProcDemo().getPriceLevel(pno);
        assertEquals(expResult, result);
        
    }

    
}
