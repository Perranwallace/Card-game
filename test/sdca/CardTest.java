/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdca;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Perran
 */
public class CardTest {
    
    static Card instance;
    static int cardNumber = 1;   
	
	
    @BeforeClass
    public static void setUpClass() {
        instance = new Card(cardNumber); 
    }

    @Test
    /**
    * Test of getNumber method, of class Card.
    * this tests whether the card has the expected value
    */
    public void testGetNumber() {    
        assertEquals("Value on card is not what it was instantiated with.",
                instance.getNumber(), cardNumber);
    }
}