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

public class Card {
    
    private int number;
    
    public Card(int n) {
	number = n;
    }
    
    public synchronized int getNumber() {
        return number;
    }

}
