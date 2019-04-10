package OneStop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardGames implements IGameControl
{
	Scanner  keyboard = new Scanner(System.in);
	IGameView h = new IOHandler();
	Deck deck;
	boolean gamEnd; 
	List<Player> players;	
	Player discardPile;
	
	public CardGames()
	{
		init();
	}
	
	public void init(){
		
		String str = "This program lets you play the One card Stop game,\n" + 
			"this uses a standard 52 card pack (no Jokers).  \n"
			+ "5 cards are dealt to each player .  \n"
			+ "1 card is face up, as the discard pile. The undealt stock is placed face down on the table, \n"
			+ "and the top card of the stock is turned face up and placed \n"
			+ "First player to choose the game will start the game and others continue.\n"
			+"Players should play the card with either the same suit or the same number of the card on the table. \n"
			+ "When a 2 is played, the next player draws 2 cards, and lose their turn. \n"
			+"If the first player plays a 2, and the second player plays a 2, and the third player plays a 2 in a row; \n but the 4th player does not have a 2, then the 4th player draws 6 cards. \n"
			+ "A Jack can be played at any time, regardless of the current suit in play. \n "
			+"When a Jack is played, the player gets to declare what suit in play they would like to change to. \n"
			+"If a Jack is played as the final card, then the player who played that card does not get to declare the suit in play \n but the next player who play a card with the same suit as the Jack was.  \n"
			+"“One Card” If the player has one card left, he needs to yell out “One card” If not, he will get 2 cards for penalty. \n"
			+"“Stop!” When the last card is played, the player needs to yell “Stop!” Otherwise, the player will get 2 cards for penalty.\n";
		
		h.display(str);
		deck = new Deck();   
		deck.shuffle(); 
		gamEnd = false;		
	}

	public void runGame() 
	{
		char playAgain = 'f';       // Record user's response when user is asked whether he wants to play another game.
		
		
		int numPlayers = 0, numComp = 0;
		
		do{
			players = new ArrayList<Player>();
			
			System.out.println("How many Human players are going to play?");
			
			numPlayers = keyboard.nextInt();
			
			while (numPlayers <1)
			{
				System.out.println("There must be at least 1 human player");
				numPlayers = keyboard.nextInt();
			}

			for(int i=0; i <= numPlayers; i++)
			{
				players.add(new Player());
			}	
			
			playGame();
			
			h.display("Play again (t/f)" + "?");
			
			playAgain = h.getInput();
			
			if(playAgain == 't'){
				init();
			}
		} while (playAgain == 't');
		
		 h.display("\n **** Game Over***");
		 
	}// runs the game for computer 	
	
	public void playGame() 
	{
		deck = new Deck();   
		deck.shuffle(); 
		gamEnd = false;	
		
		for(int i=0; i<players.size(); i++)
		{
			for (int j = 0; j < 7; j++)		//shares cards between players
			{	
				players.get(i).addCard(deck.dealCard());
			}
		}	//deals 7 cards to all players
		
		Card topCard = deck.dealCard();		//top card to be played on
		
		int numRound = 1; 	
		
		discardPile = new Player();
		discardPile.addCard(topCard);
		gamEnd = true;
		
		while(gamEnd)
		{					//plays and checks for a winner
			if (deck.cardsLeft() ==0)
			{
				gamEnd = false;
				
			}
			playRound();
		}
	}
	
	private boolean playRound() 
	{	
		for(int i = 0; i < players.size()-1; i++){
			System.out.println("\nPlayer-"+ (i+1)+" has "+players.get(i).getHand().toString());
			Card topCard = discardPile.getHand().get(discardPile.getCardCount()-1);
			
			if(deck.cardsLeft() == 0)
			{
				discardPile.removeCard(topCard);
				discardPile.shuffle();
			}
			
			Card played = players.get(i).playCard(topCard, deck);
			players.get(i).removeCard(played);
			
			if(played == null)
			{
				inconclusive(i);
				players.get(i).addCard(deck.dealCard());
			}
			else
			{
				int temp = (i+1); 		//prevent computer 0 output	
				
				playedCard(temp, played);
				
				if(players.get(i).getCardCount() == 0)
				{ 						//checks for winner of game
					winner(temp);
					gamEnd = false;
					return gamEnd;
				}
				
				discardPile.addCard(played);
			}
			
			
		}		
		return gamEnd;	
	}
	
	public void playedCard(int player, Card played)
	{
		System.out.print("Player-" + player+" played-");
		h.display(played.toString());
	}
	
	private void winner(int playerNumb)
	{
		String msg = "Hoorayy!! Computer-" + playerNumb + " has won.";
		h.display(msg);
	}	
	
	private void inconclusive(int playerNumb)
	{
		h.display("------------Player-"+(playerNumb+1) + " Ran out of "
				+ "playable cards, match over!---------------\n");
	}

}





