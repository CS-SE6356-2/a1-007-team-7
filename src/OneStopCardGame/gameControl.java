package OneStopCardGame;
import java.util.*;

public class gameControl implements IGameControl {
	Scanner  keyboard = new Scanner(System.in);
	IGameView h = new IOHandler();
	Deck deck; 
	ArrayList<Player> players;	
	Player discardPile;
	Card topCard;
	
	public gameControl(){
		init();
	}
	
	@Override
	public void init(){
		String str = "This program lets you play the simple card game,\n" + 
			"One Stop Game.  One Stop Game uses a standard 52 card pack (no Jokers).  \n"
			+ "The dealer deals (singly) five cards to each player.  \n"
			+ "The undealt stock is placed face down on the table, \n"
			+ "and the top card of the stock is turned face up and placed \n"
			+ "beside the stock to start this discard pile.\n";
		h.display(str);
		players = new ArrayList<Player>();
		deck = new Deck();   
		deck.shuffle(); 	
		topCard = null;
	}

	@Override
	public void runGame() 
	{
		char playAgain = 'f';       // Record user's response when user is asked whether he wants to play another game.
		int numPlayers = 0;
		
		do{
			System.out.println("How many computer players are going to play?");
			numPlayers = keyboard.nextInt();
			while (numPlayers<2){
				System.out.println("Please players must be more than 1 to play?");
				numPlayers = keyboard.nextInt();
			}

			for(int i=0; i<numPlayers; i++){			
				players.add(new Player());
			}		
			
			playGame();
			
			h.display("Play again (t/f)" + "?");
			playAgain = h.getInput();
			
			if(playAgain == 't'){
				players.clear();
			
				init();
				
			}
		} while (playAgain == 't');
		 h.display("\n **** Game Over***");
	}// runs the game for computer 	
	
	public void playGame() {
		for(int i=0; i<players.size(); i++){
			for (int j=0; j<7; j++){	//shares cards between players
				players.get(i).addCard(deck.dealCard());
			}
		}//deals 7 cards to all players 
		
		topCard = deck.dealCard();//top card to be played on			
		discardPile = new Player();
		discardPile.addCard(topCard);
		
		while(playRound() != false){//plays and checks for a winner		
			System.out.println("...................NEXT PLAY RND....................");
		}
	}
	
	private boolean playRound() {	
		Card played;
		for(int i =0; i<players.size(); i++){
			int temp = (i+1); //prevent computer 0 output	
			
			played = players.get(i).playCard(topCard, deck);
						
			if(played == null){
					discardPile.useDiscardPile(topCard);
//					played = players.get(i).playAgain(topCard, discardPile);	
				}
			
			else{
				topCard = played;
				h.display("Player-"+ temp + " played: "+ played.toString());
				discardPile.addCard(played);
				played = null;
				
				if(players.get(i).getCardCount()-1 == 0){ //checks for winner of game
					h.display("Hoorayy!! Computer-" + temp + " has won.");
//					discardPile.clear();
					topCard = null;
					return false;
				}
			}
			
			if(deck.cardsLeft() == 0){
				discardPile.useDiscardPile(topCard);
			}
		}		
		return true;	
	}
}

class IOHandler implements IGameView{
	Scanner  sc = new Scanner(System.in);
	char input;
	private static char[] matches = new char[]{'f', 't', 'T', 'F'};

	public void display(String message) {
		System.out.println(message);	
	}
	
	public char getInput() {
		boolean isCorrectInput = true;
		char use = 0;
		use = sc.next().charAt(0);
		if(use!='t' || use !='f' || use !='F' ||use !='T'){
			while(!isCorrectInput){
				System.out.print("Please respond with an expected character:  "); 
				use = sc.next().charAt(0);
			}
		}      
		return use;
	}
	
}
