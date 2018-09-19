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
public class CardDeckTest {
    
    static CardDeck instance;
    	
    @BeforeClass
    public static void setUpClass() {
        instance = new CardDeck(new Card(1),new Card(2),new Card(3),new Card(4)); 
    }

    @Before
    public static void setUp() {
        
    }
    /**
    * Test of enqueue method, of class CardQueue.
    * this tests whether the card at the bottom of the pack is the one which 
    * was enqueued, and whether there are now the correct number of cards in 
    * the pack (5).
    */
    @Test
    public static void testEnqueue() {   
        System.out.println("enqueue");  
        Card cardToEnqueue = new Card(1);
        instance.enqueue(cardToEnqueue); //enqueue a new card to the deck which
                                        //should go to index 0 of the array
        assertEquals("Card not successfully enqueued.", 
                instance.getCard(0), cardToEnqueue);
        for (int i = 0; i == 5; i++) {
            if (instance.getCard(i) == null) //if there is a null index in the array
                fail("There are not the correct number of cards in the pack.");
        }
    }
    /**
    * Test of dequeue method, of class CardQueue.
    * this tests whether the 0 index of the array is now null, and that there
    * are 4 cards in indexes 1 though 4.
    */
    @Test
    public static void testDequeue() {
        System.out.println("dequeue");
        assertEquals("Card was not dequeued.", instance.getCard(0), null);
        for (int i = 1; i == 5; i++) {
            if (instance.getCard(i) == null) //if there aren't 4 cards in indexes
                                            //1 through 4
                fail("There are not the correct number of cards in the pack.");
        }
    }
    /**
    * Test of getCard method, of class CardQueue.
    */
    @Test
    public static void testGetCard() {
        System.out.println("getCard");
        assertEquals("The wrong card was returned", instance.getCard(0), null);
        assertEquals("The wrong card was returned", 
                instance.getCard(1).getNumber(), 1);
        assertEquals("The wrong card was returned", 
                instance.getCard(2).getNumber(), 2);
        assertEquals("The wrong card was returned", 
                instance.getCard(3).getNumber(), 3);
        assertEquals("The wrong card was returned", 
                instance.getCard(4).getNumber(), 4);
    }
}