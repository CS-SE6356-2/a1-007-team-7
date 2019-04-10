package OneStop;

//An object of type Deck represents a deck of playing cards.
public class Deck {
	private Card[] deck; // An array of 52 cards not including joker
	private int cardsUsed; // tracks number of cards that have been dealt from the deck

	public Deck() {
		deck = new Card[52];
		int cardCt = 0; // How many cards have been created so far.
		for (int suit = 0; suit <= 3; suit++) {
			for (int value = 1; value <= 13; value++) {
				deck[cardCt] = new Card(value, suit);
				cardCt++;
			}
		}
		cardsUsed = 0;
	}// creates a 52 deck of cards not including joker
		// Puts used cards back into the deck (if any) and shuffles the deck randomly(a
		// way of doing it)

	public void shuffle() {
		for (int i = deck.length - 1; i > 0; i--) {
			int rand = (int) (Math.random() * (i + 1));
			Card temp = deck[i];
			deck[i] = deck[rand];
			deck[rand] = temp;
		}
		cardsUsed = 0;
	}

	/*
	 * public ArrayList<Card> shuffledDeckList(){ return new
	 * ArrayList<Card>(Arrays.asList(deck)); }
	 */

	public int cardsLeft() {
		return deck.length - cardsUsed;
	}

	public Card dealCard() {
		if (cardsUsed == deck.length)
			throw new IllegalStateException("No cards are left in the deck.");
		cardsUsed++;
		return deck[cardsUsed - 1];
		// Programming note: Cards are not literally removed from the array
		// that represents the deck. We just keep track of how many cards
		// have been used.
	}

}
