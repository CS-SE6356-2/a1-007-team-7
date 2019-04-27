package OneStopCardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.PasswordView;

import sun.security.util.Password;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class CardGames implements IGameControl {
	Scanner keyboard = new Scanner(System.in);
	IGameView h = new IOHandler();
	Deck deck;
	boolean gamEnd;
	List<Player> players;
	Player discardPile;

	public CardGames() {
		init();
	}

	public void init() {

		String str = "This program lets you play the One card Stop game,\n"
				+ "this uses a standard 52 card pack (no Jokers).  \n" + "5 cards are dealt to each player .  \n"
				+ "1 card is face up, as the discard pile. The undealt stock is placed face down on the table, \n"
				+ "and the top card of the stock is turned face up and placed \n"
				+ "First player to choose the game will start the game and others continue.\n"
				+ "Players should play the card with either the same suit or the same number of the card on the table. \n"
				+ "When a 2 is played, the next player draws 2 cards, and lose their turn. \n"
				+ "If the first player plays a 2, and the second player plays a 2, and the third player plays a 2 in a row; \n but the 4th player does not have a 2, then the 4th player draws 6 cards. \n"
				+ "A Jack can be played at any time, regardless of the current suit in play. \n "
				+ "When a Jack is played, the player gets to declare what suit in play they would like to change to. \n"
				+ "If a Jack is played as the final card, then the player who played that card does not get to declare the suit in play \n but the next player who play a card with the same suit as the Jack was.  \n"
				+ "“One Card” If the player has one card left, he needs to yell out “One card” If not, he will get 2 cards for penalty. \n"
				+ "“Stop!” When the last card is played, the player needs to yell “Stop!” Otherwise, the player will get 2 cards for penalty.\n";

		h.display(str);
		deck = new Deck();
		deck.shuffle();
		gamEnd = false;
	}

	public void runGame() {
		char playAgain = 'f'; // Record user's response when user is asked whether he wants to play another
								// game.

		int numPlayers = 0, numComp = 0;

		do {
			players = new ArrayList<Player>();

			System.out.println("How many players are going to play?");

			numPlayers = keyboard.nextInt();

			while (numPlayers < 2) {
				System.out.println("There must be at least 2 player");
				numPlayers = keyboard.nextInt();
			}

			for (int i = 0; i < numPlayers; i++) {
				players.add(new Player());
			}

			playGame();

			h.display("Play again (t/f)" + "?");

			playAgain = h.getInput();

			if (playAgain == 't') {
				init();
			}
		} while (playAgain == 't');

		h.display("\n **** Game Over***");

	}// runs the game for computer

	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	} 
	public void playGame() {
		deck = new Deck();
		deck.shuffle();
		gamEnd = false;

		for (int i = 0; i < players.size(); i++) {
			
//			System.out.println("Please keep the messages private to each player.");
//			System.out.println("Enter Player"+(i+1)+" Name: ");
//			players.get(i).playerName = keyboard.next();
//			System.out.println("Enter Player"+(i+1)+" Password: ");
//			players.get(i).SetPassword(keyboard.next()); 			
//			System.out.println("Sharing Cards to  "+players.get(i).playerName+"... ");
			
			for (int j = 0; j < 5; j++) // shares cards between players
			{
				players.get(i).addCard(deck.dealCard());
			}
		} // deals 5 cards to all players

		Card topCard = deck.dealCard(); // top card to be played on

		int numRound = 1;

		discardPile = new Player();
		discardPile.addCard(topCard);
		gamEnd = true;

		while (gamEnd) { // plays and checks for a winner
			if (deck.cardsLeft() == 0) {
				gamEnd = false;

			}
			
			playRound();
		}
		return;
	}

	private boolean playRound() {
		for (int i = 0; i < players.size() - 1; i++) {
			System.out.println("\nPlayer-" + players.get(i).playerName+ " has " + players.get(i).getHand().toString());
			Card topCard = discardPile.getHand().get(discardPile.getCardCount() - 1);

			if (deck.cardsLeft() == 0) {
				discardPile.removeCard(topCard);
				discardPile.shuffle();
			}

			Card played = players.get(i).playCard(topCard, deck);
			players.get(i).removeCard(played);

			if (played == null) {
				int count=1;
				if(topCard.getValue()==2) {
					while(discardPile.getHand().get(discardPile.getCardCount() - count).getValue()==2) {
						count++;		
					}
					for(int j=0;j<2*count;j++) {
						players.get(i).addCard(deck.dealCard());
					}
				}else {
					inconclusive(i);
					players.get(i).addCard(deck.dealCard());	
				}
			} else {
				int temp = (i + 1); // prevent computer 0 output

				playedCard(temp, played);

				if (players.get(i).getCardCount() == 0) { // checks for winner of game
					winner(temp);
					gamEnd = false;
					return gamEnd;
				}

				discardPile.addCard(played);
			}
		}
		return gamEnd;
	}

	public void playedCard(int player, Card played) {
		System.out.print("Player-" + players.get(player-1).getName() + " played-");
		h.display(played.toString());
	}

	private void winner(int playerNumb) {
		String msg = "Hoorayy!! Player-" + players.get(playerNumb-1).getName()  + " has won.";
		h.display(msg);
	}

	private void inconclusive(int playerNumb) {
		h.display("------------Player-" + players.get(playerNumb).getName()  + " Ran out of "
				+ "playable cards, match over!---------------\n");
	}

}
