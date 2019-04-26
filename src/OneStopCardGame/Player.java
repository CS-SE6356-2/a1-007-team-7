package OneStopCardGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Player {
	private String password;
	private ArrayList<Card> cardsInhand;// cardlist in domain diagram
	String playerName;
	String playingGameID;
	Double score;
	String level;
//	ArrayList<Card> cardsHold;
	int numberofCardsHold;
	Boolean Iswaiting;
	Boolean myTurn;

//	protected Hand hand;
	public Player() {
//		hand = new Hand();
		this.cardsInhand = new ArrayList<Card>();
	}
	public void SetPassword(String password) {
		this.password = password;
	}

//	public void addCard(Card toAdd) {
//		hand.addCard(toAdd);
//	}

	public void addCard(Card c) {
		if (c == null)
			throw new NullPointerException("Can't add a null card to a hand.");
		this.cardsInhand.add(c);
	}

//	public Card playCard(Card topCard, Deck deck) {
//		for(Card k : this.getHand()){
//			if(topCard.getSuit() == k.getSuit()){
//				this.removeCard(k);
//				return k;
//			}
//			if(topCard.getValue() == k.getValue() || k.getValue() == 8){
//				this.removeCard(k);
//				return k;
//			}
//		}
//		boolean played = false;//hasn't played
//		
//		do{ 
//			if(deck.cardsLeft() == 0){
//				return null;	//if deck is empty return asking for discount pile
//			}
//			Card j = deck.dealCard();
//			if(topCard.getSuit() == j.getSuit()){
//				return j;
//			}//plays card if suit is the same
//			if(topCard.getValue() == j.getValue()|| j.getValue() == 8){
//				return j;
//			}//plays card if value is the same
//			played = false;
//			this.addCard(j);
//		}while(!played);
//	
//		return null;// means deck is empty and they should pick from discount pile
//	}

	public Card playCard(Card topCard, Deck deck) {

		System.out.println("\n\tTop card is " + topCard);

		List<Card> playerHand = cardsToPlay(topCard, this.cardsInhand);

		System.out.println("\n===================You can play===================\n");
		for (int i = 0; i < playerHand.size(); i++) {
			System.out.println((i + 1) + ") " + playerHand.get(i).toString());
		}

		return selectCard(playerHand);
	}

	public void removeCard(Card c) {
		this.cardsInhand.remove(c);
	}

	public Card removeCard(int position) {
		if (position < 0 || position >= this.cardsInhand.size())
			throw new IllegalArgumentException("Position does not exist in hand: " + position);
		return this.cardsInhand.remove(position);
	}// plays/removes card at a particular position

	public int getCardCount() {
		return this.cardsInhand.size();
	}// returns number of cards left to be played

	public ArrayList<Card> getHand() {
		return this.cardsInhand;
	}

	public void shuffle() {
		Collections.shuffle(this.cardsInhand);
	}

	public void useDiscardPile(Card topCard) {
		this.cardsInhand.remove(topCard);
		shuffle();
	}

	public void sortBySuit() {
		ArrayList<Card> newHand = new ArrayList<Card>();
		while (this.cardsInhand.size() > 0) {
			int pos = 0; // Position of minimal card.
			Card c = this.cardsInhand.get(0); // Minimal card.
			for (int i = 1; i < this.cardsInhand.size(); i++) {
				Card c1 = this.cardsInhand.get(i);
				if (c1.getSuit() < c.getSuit() || (c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue())) {
					pos = i;
					c = c1;
				}
			}
			this.cardsInhand.remove(pos);
			newHand.add(c);
		}
		// Collections.sort(list, c);
		this.cardsInhand = newHand;
		// Collections.sort(list, c);
	}// sorts cards in suits by hand

	public void sortByValue() {
		ArrayList<Card> newHand = new ArrayList<Card>();
		while (this.cardsInhand.size() > 0) {
			int pos = 0; // Position of minimal card.
			Card c = this.cardsInhand.get(0); // Minimal card.
			for (int i = 1; i < this.cardsInhand.size(); i++) {
				Card c1 = this.cardsInhand.get(i);
				if (c1.getValue() < c.getValue() || (c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit())) {
					pos = i;
					c = c1;
				}
			}
			this.cardsInhand.remove(pos);
			newHand.add(c);
		}
		this.cardsInhand = newHand;
	}// sorts cards in hand by value

//	public Card playCard(Card topCard, Deck deck) {
//		return null;
//	};

	public Card playAgain(Card topCard, ArrayList<Card> deck) {
		for (int i = 0; i < this.getCardCount(); i++) {
			Card playable = this.removeCard(i);
			if (playable.getSuit() == topCard.getSuit() || playable.getValue() == 8
					|| playable.getValue() == topCard.getValue()) {
				return playable;
			} else {
				this.addCard(playable);
			}
		}
		return null;
	}// if deck runs out of card this is used

	Scanner keyboard = new Scanner(System.in);

	public Card selectCard(List<Card> pHand) {
		if (pHand.isEmpty())
			return null;
		int selection = 0;

		System.out.println("Select a card to play");
		selection = keyboard.nextInt();

		while (selection <= 0 || selection > pHand.size()) {
			System.out.println("Invalid Card Input. Try a different card: ");
			selection = keyboard.nextInt();
		}

		return pHand.get(selection - 1);
	}

	private List<Card> cardsToPlay(Card topCard, ArrayList<Card> hand) {
		if (hand == null)
			return null;

		List<Card> returnList = new ArrayList<Card>();
		for (int i = 0; i < this.getHand().size(); i++) {
			if (topCard.getSuit() == this.getHand().get(i).getSuit()
					|| topCard.getValue() == this.getHand().get(i).getValue())
				returnList.add(this.getHand().get(i));
		}

		return returnList;
	}

//	 public Card playAgain(Card topCard, Hand deck){
//			return null;
//		}//if deck runs out of card this is used

//		public Card selectCard() {
//			
//			return null;
//		}
//	 
	public Card selectCard() {
		return null;
	};
	
	public Player(String name) {
		this.playerName = name;
	}

	public String getName() {
		return this.playerName;
	}

	public Double getScore() {
		return this.score;
	}

//	public void AddCard(Card card) {
//		cardsHold.add(card);
//		this.numberofCardsHold++;
//	}

	public void LevelUp() {

	}

	public void ChooseGame() {
		// get input
	}

	public void ChooseGameCategory() {

	}

	public String getGameID() {
		return this.playingGameID;
	}

	public void SetWaiting() {
		if (this.Iswaiting)
			this.Iswaiting = false;
		else
			this.Iswaiting = true;
	}
	
	public void ActonCard() {
		//display rules
		//grab input
		//code accordign to input
	}
	
	public void AddPenaltyCard() {
		
	}
	
	public String MentionSuit() {
		return "";
	}
}
