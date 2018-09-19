/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdca;

/**
 *
 * @author Perran
 */


import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.ArrayIndexOutOfBoundsException;

public class CardGame {
    
    private static int NUM_OF_PLAYERS; // an integer to store the number of players
                                       //in this game (2-7)
    private static Player[] playersArray; //an array that stores the instances
                                          //of the players playing the game    
    private static Card[] cardArray; //an array that stores the cards from the
                                     //deck file
    private static boolean gameWon = false; //a boolean that stores whether
                    //the game has been won. When this is set to true players
                    //will stop playing the game.
    private static boolean turnsTaken = false; //a boolean that stores whether
                    //every player has taken their turn. When this is set to true 
                    //players can take their next turn
    
    
    public static void main (String[] args) {        
        
        try{NUM_OF_PLAYERS = Integer.parseInt(args[0]);} //the first input is the 
                                   //number of players so store this in a variable             
        
        catch(NumberFormatException nfe){ //if the first input was not a valid integer
           System.out.println("Please enter a number between 2-7");
           //display an error message
           System.exit(0); //exit from the program, since it cannot be run without
                            //a valid number of players.
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Please enter a number between 2-7");        
           //display an error message
           System.exit(0); //exit from the program, since it cannot be run without
                            //a valid number of players.
        }
        readDeck(args[1]); //extract the cards from the deck file
        
        checkCards(); //check that there are at least 4 cards of n types where n
                      //is the number of players, so that it is possible for
                      //players to win.
        
        instantiatePlayers();

        dealDeck();//deal the cards from the card array to the players.
        
        startPlayers();
    }
    
    public synchronized int getNumOfPlayers(){
        return NUM_OF_PLAYERS;        
    }
    
    public static void readDeck(String filePath){  //this method reads the
            //deck file, checks that there are enough cards, instantiates a card
            //object for each card, and adds these objects to the card array.        
        int lineCount;
        lineCount = 0;        
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            while (line != null) {
                lineCount += 1; //count how many cards there are in the deck file
                line = br.readLine();
            }
        }
        catch(IOException e){
            System.out.println("Invalid input");
            e.printStackTrace();
        }                
        if (lineCount < (NUM_OF_PLAYERS * 8)){ //There must be at least 8 cards 
                                             //per player
            System.out.println("Not enough cards");
            System.exit(0); //exit from the program, since it cannot be run
                            //without enough cards. 
        }            
        cardArray = new Card[lineCount];        
        lineCount = 0; //reset lineCount so that it can be used to keep track of
            //which card we are on as we iterate through to instatiate each card        
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            while (line != null) { //iterate through each line/card in the file
                int cardNumber = 0; 
                try{
                    cardNumber = Integer.parseInt(line); //store the value of the card
                }
                catch(NumberFormatException nfe){
                    System.out.println("Invalid deck input");
                    System.exit(0); //if the line is not an integer and therefor
                    //a valid card number display an error message and exit.
                }
                Card c = new Card(cardNumber);//instantiate a card object and pass 
                                               //the card number to the constructor
                cardArray[lineCount] = c; //store the new card in the cardArray
                lineCount += 1;
                line = br.readLine(); //read the next line/card
            }
        }
        catch(IOException e){
            System.out.println("Invalid input");
            e.printStackTrace();
        }
    }
    
    static void dealDeck(){                
        int playerCount = 0;//keep track of which player to deal to
        for(int count = 0; count < ((NUM_OF_PLAYERS * 4)); count++){//iterate
                                //through each card to be dealt to the players
            playersArray[playerCount].addCardToHand(cardArray[count]); //deal
            //the card to the current player
            if(playerCount == NUM_OF_PLAYERS-1)
                playerCount = 0;//if the current player is the last player then
                                //go back to the 1st player
            else
                playerCount += 1; //otherwise move to the next player       
        }
        int cardDeckCount = NUM_OF_PLAYERS - 1; //keep track of which cards to deal
                //to the card decks. Start on the number of players - 1 because
                //the cards before that have been dealt to players.
        for (Player player : playersArray) {
            player.checkHand(); //check to see if a player has won with the
                                //1st cards they were dealt.
            player.instantiateCardDeck(cardArray[cardDeckCount],
                    cardArray[cardDeckCount+1],cardArray[cardDeckCount+2],
                    cardArray[cardDeckCount+3]); //pass the cards to be dealt
                        //to the new deck to the method that instantiates them
                        //in the player class.            
        }
        
    }
    
    private static void checkCards(){ // this method checks that there are at 
                    //least 4 cards of n types where n is the number of players
                    //so that it is possible for players to win.
        int[] tempArray = new int[13]; //an array to store the frequencies of
                                       //each type of card in the deck.
        
        for (Card card : cardArray)
        {            
            tempArray[card.getNumber()-1] = tempArray[card.getNumber()-1] + 1;            
        }
        int count = 0; //to count the frequencies
        for (int i : tempArray)
        {
            if (i > 3)
                count += 1;
        }        
        for(int i = 0; i < NUM_OF_PLAYERS; count++){
            if (tempArray[i] < 4)
            {
                System.out.println("Insufficient cards to win. "
                    + "Please provide a valid deck.");//display an 
                                                              //error message
                System.exit(0); //exit from the program, since it cannot be run 
                           //without n number of 4 of the same cards.
            }
        }                
    }
    
    private static void instantiatePlayers(){    
        playersArray = new Player[NUM_OF_PLAYERS];
        
        for (int count = 0; count < (NUM_OF_PLAYERS); count++) {
            playersArray[count] =  new Player(count + 1); //instantiate a new
                                            //player and add them to the array
            //System.out.println("playerTracker: " + playersArray[count].getNumber());                               
        }       
    }
    
    private static void startPlayers() //this method starts each instance of 
                                      //player running on a thread.
    {
        for (int count = 0; count < (NUM_OF_PLAYERS); count++) {            
            playersArray[count].start(); //start the thread that will run
                                            //that player
        }   
    }
    
    public static synchronized Player getPlayerToRight(int playerNum){//this method returns
                    //the player that is to the right (or the previous player
                    //in the array) to the player passed to it.
        if (playerNum == 1)
            return playersArray[NUM_OF_PLAYERS - 1];
        else
            return playersArray[playerNum - 2];
    }

    public static synchronized boolean isTheGameWon(){return gameWon;}
    
    public static synchronized boolean turnsTaken(){return turnsTaken;}
    
    public static synchronized void winGame(int playerNum){
        //System.out.printf("Player %s has won.", playerNum);
        System.out.println("Player " + playerNum + " has won.");
        gameWon = true;
        System.exit(0);
    }
    
}