/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import model.Part;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thomas Hartmann - tha@cphbusiness.dk
 */
public class PartMapperTest {
    
    PartMapper pm;    
       
    @Before
    public void setUp() {
         pm = new PartMapper();
    }
    

     @Test
     public void getPartbyPno() {
         Part p = pm.getPart(10506);
         String pname = p.getPname();
         String exp = "Land Before Time I";
         assertEquals(exp, pname);
     }
             
     @Test
     public void getPartInjection() {
        String goodSearch = "Land Before Time II";
        String injection =  "Land Before Time II' or '1'='1";
        Part p = pm.getPartByName(goodSearch);
        String pname = p.getPname();
        String exp = "Land Before Time II";
        assertEquals(exp, pname);
     }
     
}
