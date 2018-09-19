package sdca;

/**
 *
 * @author 640022516 650022559
 */
import java.lang.ArrayIndexOutOfBoundsException;

public class CardDeck {
    
    
    private Card[] cards;
	
    public CardDeck(Card c1, Card c2, Card c3, Card c4) {
        cards = new Card[5]; //an array that can hold 5 cards, so the extra
                             //card can be held while swapping
        cards[1] = c1;   
        cards[2] = c2;
        cards[3] = c3;
        cards[4] = c4;
    }

    public void enqueue(Card c) {
        cards[0] = c;//the 0 index is the tail of the queue so add the new card here         
        //getCard(6);
    }

    public Card dequeue() {
        Card temp = cards[4];//make a copy of the card at the head of the queue            
        cards[4] = null;//remove the head
        for (int i=4; i==0; i--) {
                cards[i] = cards[i-1]; //shift every card up one index so
                                       //the 0 index is empty again
        }
        return temp; //return the dequeued card
    }
    
    /**
    * getCard returns the value (the card) in a given index of the card deck array
    */
    public synchronized Card getCard(int index) {       
        try{
            return cards[index];
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return null;
        }
    }
    
    
    
}
