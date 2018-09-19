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
//import java.util.Arrays.*;

/**
 *
 * @author Perran
 */
public class PlayerTest {
    
    static Player instance;
    
		
    @BeforeClass
    public static void setUpClass() {
        instance = new Player(1); 
        
    }
    
    @Before
    public void setUp() {
        for (int i = 0; i == 4; i++)
            instance.addCardToHand(new Card(i));
        instance.instantiateCardDeck(new Card(1), new Card(2), new Card(3), new Card(4));    
    }    

    /**
     * Test of addCardToHand method, of class Player.
     */
    @Test
    public void testAddCardToHand() {
        System.out.println("addCardToHand");
        instance.cardHandArray[2] = null;         
        //a card is deleted from the hand. this tests for the possibility
        //of the first null in the player's hand not being successfully
        //replaced with the new card.
        Card c = new Card(1);
        instance.addCardToHand(c);		  
        assertEquals ("New card not added to player's hand",
                instance.cardHandArray[2],c);
        
    }

    /**
     * Test of removeCardFromHand method, of class Player.
     * his tests whether the removed card has a suitable number (ie. not
     * the same as the player number)
     */
    @Test
    public void testRemoveCardFromHand() { 
        System.out.println("removeCardFromHand");    
        
        assertNotEquals("Player removed wrong card",
                (instance.removeCardFromHand()).getNumber(),1);
        //the player number of instance is 1 so they should not remove a 1 card.
    }
    /**
     * Test of deteleCardFromHand method, of class Player.
     * this tests whether the new hand has a null (ie. removed) entry
     */
    @Test
    public void testDeleteCardFromHand() {  
        System.out.println("deleteCardFromHand");    
        instance.deleteCardFromHand(); 
        if (!(java.util.Arrays.asList(instance.cardHandArray).contains(null)))
            fail("Card was not removed");        
        
    }

    /**
     * Test of getNumber method, of class Player.
     */
    @Test
    public void testGetNumber() { 
        assertEquals("Player returned wrong player number",
                (instance.getNumber()),1);
    }
    
    /**
     * Test of checkHand method, of class Player.
     */
    @Test
    public void testCheckHand() {         
        assertEquals("Player returned wrong player number",
                (instance.getNumber()),1);
    }
    
    /**
     * Test of instantiateCardDeck method, of class Player.
     */
    @Test
    public void testInstantiateCardDeck() { 
        Card c1 = new Card(1);
        Card c2 = new Card(2);
        Card c3 = new Card(3);
        Card c4 = new Card(4);
        instance.instantiateCardDeck(c1,c2,c3,c4);
        
        assertSame("leftCardDeck not properly instantiated",
                instance.getCardDeck().getCard(0), c1);
        assertSame("leftCardDeck not properly instantiated",
                instance.getCardDeck().getCard(1), c2);
        assertSame("leftCardDeck not properly instantiated",
                instance.getCardDeck().getCard(2), c3);
        assertSame("leftCardDeck not properly instantiated",
                instance.getCardDeck().getCard(3), c4);
    }
    
    /**
     * Test of isHandFull method, of class Player.
     */
    @Test
    public void testIsHandFull() {         
        assertTrue("Player's hand should be full and isn't",
                (instance.isHandFull())); //at this point the hand should be full
                 //because the player was dealt 4 cards in the setUp() method.
        instance.removeCardFromHand(); //remove a card from the player's hand       
        assertFalse("Player's hand should not be full and is",
                (instance.isHandFull())); //at this point the hand should not be 
                 //full because a card was just removed.
        
    }
    
}
