/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdca;


/**
 *
 * @author 640022156; 650022559
 */

public class Player extends Thread {
    
    Card[] cardHandArray; //this stores the 4 cards the player holds in their hand
    private final int PLAYER_NUM; //the player number of this player
    private CardDeck leftCardDeck; //this is the deck of cards to the player's left ie.
                            //the one that they discard cards to
    
    public void run() {    
        while (CardGame.isTheGameWon() == false){ //do this while no one has won:
            takeTurn(); //this method calls other methods that discard a card from
            //the player's hand and add it to the deck to their left, and to take
            //a card from the deck to their right            
           
           while (!isHandFull()) 
           {
                //try { sleep(100);} catch (InterruptedException e) {}           
           }
            if (!isHandFull()) 
                checkHand();//check to see if the player has won (if all their cards
                        //are the same
        }              
    }
    
    public Player (int playerNum){ 
        cardHandArray = new Card[4];//the array that holds the cards in the 
                                    //player's hand.
        PLAYER_NUM = playerNum;              
    }
    
    public void takeTurn(){
        leftCardDeck.enqueue(removeCardFromHand()); //remove an unwanted card from the
        //player's hand and add this to the deck to their left.
        deleteCardFromHand(); //the card has to be removed from the player's hand
                              //after it is passed to the card deck
        addCardToHand(CardGame.getPlayerToRight(PLAYER_NUM).leftCardDeck.dequeue()); 
        //add card to hand from next player's deck.
    }
    
    public void addCardToHand(Card c){           
        for(int count = 0; count < 4; count++){ //iterate through the player's 
                                                //hand                               
            if (cardHandArray[count] == null) { //if there is an empty index
                cardHandArray[count] = c; //add the new card here.
                return; //end the method because the card has been added and 
                        //there may be other empty spaces in the array that the
                        //card shouldn't get added to.
            }
        }
    }
    
    public Card removeCardFromHand(){
        //this method returns a card to be removed, but does not remove it
        //because it needs to be returned.
        for (int i = 0; i < 4; i++) {
            if (cardHandArray[i].getNumber() != (PLAYER_NUM)) { //if a card is
                //found that does not match the player's number
                return cardHandArray[i]; //return this card so it can be added
                                        //to the card deck.
            }
        }
        return cardHandArray[0];
    }
    
    public void deleteCardFromHand() {
        //this method removes a card by setting the index where it is stored
        //in the array to null
        for (int i = 0; i < 4; i++) {
            if (cardHandArray[i].getNumber() != (PLAYER_NUM)) {
                cardHandArray[i] = null; //remove the card that was found in
                return; //end the method so that no more cards are deleted
            }
        }
    }
        
    public int getNumber(){return PLAYER_NUM;}
    
    public void checkHand(){               
        int firstCardNum;
        firstCardNum = cardHandArray[0].getNumber(); //store the number of the
                                              //first card in the player's hand                                  
        for(int count = 1; count < 4; count++){
            if (cardHandArray[count] == null) {
                System.out.println(PLAYER_NUM + 
                        " hand not full for check hand " + count);
                return;
            }
            if (cardHandArray[count].getNumber() != firstCardNum) {
                return; //if another card in the hand doesn't equal the 1st one
                        //then the player has not won so return and end the
                        //method.
            } 
        }
        CardGame.winGame(PLAYER_NUM); //if all the cards were the same then
                //the player has won so call the winGame method in CardGame.
    }
    
    public void instantiateCardDeck(Card c1,Card c2,Card c3,Card c4){
        leftCardDeck = new CardDeck(c1,c2,c3,c4);
    }
    
    public boolean isHandFull(){ //this method returns true if there is a card
            //in every index of the cardHandArray and false if not        
        for(int count = 0; count < 4; count++){ //iterate through the player's 
                                                //hand
            if (cardHandArray[count] == null) { //if there is an empty index
                return false; 
            }
        }
        return true; //if no empty index was found return true/
    }
    
    public CardDeck getCardDeck() {return leftCardDeck;}
           
    
    
}
