package OneStopCardGame;
//An object of type Card represents a playing card from a standard Poker deck
public class Card {
   public final static int SPADES = 0;   // Codes for the 4 suits.
   public final static int HEARTS = 1;
   public final static int DIAMONDS = 2;
   public final static int CLUBS = 3;
  
   public final static int ACE = 1;      // Codes for the non-numeric cards.
   public final static int JACK = 11;    //   Cards 2 through 10 have their 
   public final static int QUEEN = 12;   //   numerical values for their codes.
   public final static int KING = 13;	
   private final int suit; //suits can't be changed after construction
   private final int value; //value of the card can't be changed after construction
   
   public Card(int theValue, int theSuit) {
	      if (theSuit != SPADES && theSuit != HEARTS && theSuit != DIAMONDS && 
	    		  theSuit != CLUBS)
	          throw new IllegalArgumentException("Illegal playing card suit");
	      if (theValue < 1 || theValue > 13)
	           throw new IllegalArgumentException("Illegal playing card value");
	      value = theValue;
	      suit = theSuit;
   }//Creates a card with a specified suit and value.
   
   public int getSuit(){
	   return suit;
   }//Returns the suit of this card.
   
   public int getValue(){
	   return value;
   }//Returns the value of this card.
   
   public String getSuitAsString() {
      switch(suit){
      case SPADES:   return "Spades";
      case HEARTS:   return "Hearts";
      case DIAMONDS: return "Diamonds";
      default:    return "Clubs";
      }
   }//Returns a String representation of the card's suit.
   
   public String getValueAsString() {
	     switch ( value ) {
	     case 1:   return "Ace";
	     case 2:   return "2";
	     case 3:   return "3";
	     case 4:   return "4";
	     case 5:   return "5";
	     case 6:   return "6";
	     case 7:   return "7";
	     case 8:   return "8";
	     case 9:   return "9";
	     case 10:  return "10";
	     case 11:  return "Jack";
	     case 12:  return "Queen";
	     default:  return "King";  
	     }
   }//returns a string representation of the cards value
/*   
   public String card(Card cardPlayed){
	   return cardPlayed.getSuitAsString() + cardPlayed.getValueAsString();
   }*/
   

   public String toString(){
	   return getValueAsString() + " of " + getSuitAsString();
   }
}//end class
