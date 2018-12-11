/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectatm;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author a_ran
 */
public class ProjectATMTest {
    ATM m; 

    public ProjectATMTest() {
        
    }
    
    @Before
    public void setUpATM() {
        m = new ATM();
    }
    
    @Before
    public void checkDATA(){
        m.accDatabase();
        m.moneyDatabase();
    }
    
    
    
    @Test
    public void testInitilizationOfAtm(){
        assertEquals("Machine does not have the bills", 500, m.getBills());
    }
    
    @Test
    public void testAccNo(){
        //checks if database has account number
        assertEquals("Balance does not equal",true, m.accountMap.containsKey("00001"));
        assertEquals("Balance does not equal",true, m.accountMap.containsKey("00002"));
        assertEquals("Balance does not equal",true, m.accountMap.containsKey("00003"));
        assertEquals("Balance does not equal",true, m.accountMap.containsKey("00004"));
        assertEquals("Balance does not equal",false, m.accountMap.containsKey("00005"));
        assertEquals("Balance does not equal",false, m.accountMap.containsKey("00010"));
        assertEquals("Balance does not equal",false, m.accountMap.containsKey("00015"));
    }
    
    @Test
    public void testPinNo(){
        //checks if account number matches pin inside database
        assertEquals("Account Number does not match pin", null, m.accountMap.get("34434"));
        assertEquals("Account Number does not match pin", "00011", m.accountMap.get("00001"));
        assertEquals("Account Number does not match pin", "00022", m.accountMap.get("00002"));
        assertEquals("Account Number does not match pin","00033", m.accountMap.get("00003"));
        assertEquals("Account Number does not match pin", "00044", m.accountMap.get("00004"));
    }
  
    
    @Test
    public void testPopulate(){
        m.populate("10000", "00001", 250.50);
        
        assertEquals("Account Number does not match pin", "00001", m.accountMap.get("10000"));
        assertEquals("Account Number does not match money", 250.50 , m.moneyMap.get("10000"), .00);
        assertEquals("Balance does not equal",true, m.accountMap.containsKey("10000"));
    }
    
    
    @Test
    public void testBalanceInquiry(){        
        assertEquals("Account Number does not match balance",40.50 , m.getBalance("00001"), .00);
        assertEquals("Account Number does not match balance",500.00 , m.getBalance("00002"), .00);
        
    }    
    
    @Test
    public void testGetBalance(){        
        assertEquals("Account Number does not match balance",40.50 , m.getBalance("00001"),00);
        assertEquals("Account Number does not match balance",500.00 , m.getBalance("00002"), .00);
        
    }
    
  
  

    
    /**
     * Test of main method, of class ProjectATM.
     */

    
}
