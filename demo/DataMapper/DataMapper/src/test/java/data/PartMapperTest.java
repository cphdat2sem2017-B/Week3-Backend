/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk
 */
public class PartMapperTest {

    PartMapper pm = null;
   
    @Before
    public void setUp() throws Exception {
        pm = new PartMapper();
    }
   
    @Test
    public void testPriceLevelLow() {
        String result = pm.getPriceLevel(10800);
        assertTrue("Get level failed1", result != null);
        String exp = "LOW";
        assertEquals("Get level failed2", result, exp);
    }
    
       @Test
    public void testPriceLevelMedium() {
        String result = pm.getPriceLevel(10506);
        assertTrue("Get level failed1", result != null);
        String exp = "MEDIUM";
        assertEquals("Get level failed2", result, exp);
    }
    
       @Test
    public void testPriceLevelHigh() {
        String result = pm.getPriceLevel(10601);
        assertTrue("Get level failed1", result != null);
        String exp = "HIGH";
        assertEquals("Get level failed2", result, exp);
    }
     
    
}
