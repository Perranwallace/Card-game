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
import java.util.Arrays;

/**
 *
 * @author Perran
 */
public class CardGameTest {

    @BeforeClass
    public void setUpClass() {
        Player[] playersArray = new Player[];
        CardGame.NUM_OF_PLAYERS = 3;
        Card[] validDeck = {new Card(1), new Card(2), new Card(3), new Card(1),
        new Card(2), new Card(3), new Card(1), new Card(2), new Card(3), 
        new Card(1), new Card(2), new Card(3)};
    }
	
    @Before
    public void setUp() {
    }
	/**
	 * Tests the instantiatePlayers method, of class CardGame.
	 * This tests whether the right number number of players are instantiated.
	 */
	public void testInstantiatePlayers() {
	CardGame.instantiatePlayers();
	for (Player player : CardGame.playersArray) {
            assertNotNull("Players not properly instantiated", player);
        }
	}
	/**
	 * This tests the dealDeck method, of the CardGame class.
     * Tests whether the right cards have been dealt to the right players, ie. that 
     * the cards have been dealt in a round robin fashion.	 
	 */
	public void testDealDeck() {
        CardGame.cardArray = validDeck;
        dealDeck();
        Card[] dealtDeck = new Card[validDeck.length] //a new array is made of everything that was dealt to the players
        int[] numbersOfEachCard = new int[CardGame.NUM_OF_PLAYERS];
        int i = 0;		
        for (Player player : playersArray) {
            for (Card card : player.cardHandArray) {
                dealtDeck[i] = card;
                i++;
                numbersOfEachCard[card.getNumber()]++;
            }
        }
        for (Card card : validDeck) {
            assertTrue("Cards not dealt properly - not all the cards were received by someone.", Arrays.asList(dealtDeck).contains(card));
        }
        for (int number : numbersOfEachCard)
            assertTrue("Cards not dealt properly - not every one of each number was received by someone", number == 4);
	}
	/**
	 * Tests the CheckCards method, of class CardGame.
	 * This tests whether a valid deck will pass, whether one with too many cards fails
	 * and whether one with not enough threes fails.
	 */	
	public void testCheckCards() {
            Card[] notEnoughCardsDeck = {new Card(1), new Card(2)};
            Card[] notEnough3sDeck = {new Card(1), new Card(2), 
            new Card(3), new Card(1), new Card(2), new Card(3), 
            new Card(1), new Card(2), new Card(3), new Card(1), new Card(2)};
            CardGame.cardArray = validDeck;
            assertTrue(CardGame.checkCards());
            CardGame.cardArray = notEnoughCardsDeckDeck;
            assertFalse("Does not recodgnise that there are not enough cards.", CardGame.checkCards());
            CardGame.cardArray = notEnough4sDeck;
            assertFalse("Does not recognise that there are not enough of a certain number card.");
	}
	/**
	 * This tests the getPlayerToRight method, in class CardGame.
	 * Tests whether the second player in the array returns the first, and that the first returns the
	 * last.
	 */
	
	public void testGetPlayerToRight() { 
		assertEquals(CardGame.getPlayerToRight(2), playersArray[0]);                   
		assertEquals(CardGame.getPlayerToRight(1), playersArray[numOfPlayers - 1]);    
	}
}