import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
	// screen size
	int aW = 1280;
	int aH = 800;

	
	// game phases booleans
	boolean bool_player_turn=true;
	boolean bool_dealer_turn=false;
	boolean bool_play_more=false;
	
	// background color
	Color colorBackground = new Color(39, 119, 20);
	Color colorButton = new Color(204, 204, 0);

	// buttons
	JButton bStart = new JButton();
	JButton bQuit = new JButton();
	JButton bYes = new JButton();
	JButton bNo = new JButton();

	// Fonts
	Font fontButton = new Font("Times New Roman", Font.PLAIN, 30);
	Font fontCard = new Font("Times New Roman", Font.BOLD, 35);
	Font fontQuestion = new Font ("Times New Roman", Font.BOLD,30);
	
	//questions
	String play_more_q = "Want to continue?";
	// card grid positioning and dimensioning
	int gridX = 50;
	int gridY = 50;
	int gridW = 900;
	int gridH = 400;

	// card dimensioning and spacing
	int cardSpacing = 10;
	int cardEdgeSofting = 7;
	int cardTW = gridW / 7;
	int cardTH = gridH / 2;
	int cardAW = cardTW - 2 * cardSpacing;
	int cardAH = cardTH - 2 * cardSpacing;

	// totals and hit-stay grid positioning and dimensioning
	int hsX = gridX + gridW + 50;
	int hsY = gridY;
	int hsW = 250;
	int hsH = 400;

	// Question grid positioning and dimensions
	int pmX = hsX;
	int pmY = hsY + hsH + 50;
	int pmW = hsW;
	int pmH = 200;

	// arraylist that contains all the cards
	ArrayList<Card> AllCards = new ArrayList<Card>();
	ArrayList<Card> playerCards = new ArrayList<Card>();
	ArrayList<Card> dealerCards = new ArrayList<Card>();
	// integer used to generate random numbers for the cards
	int rand = new Random().nextInt(52);

	public GUI() {
		this.setSize(aW + 6, aH + 29);
		this.setTitle("BlackJack");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Board board = new Board();
		this.setContentPane(board);
		this.setLayout(null);

		ActStart aStart = new ActStart();
		bStart.addActionListener(aStart);
		bStart.setBounds(hsX + 60, hsY + 40, 120, 80);
		bStart.setBackground(colorButton);
		bStart.setFont(fontButton);
		bStart.setText("Start");
		board.add(bStart);

		ActQuit aQuit = new ActQuit();
		bQuit.addActionListener(aQuit);
		bQuit.setBounds(hsX + 60, hsY + 280, 120, 80);
		bQuit.setBackground(colorButton);
		bQuit.setFont(fontButton);
		bQuit.setText("Quit");
		board.add(bQuit);

		ActYes aYes = new ActYes();
		bYes.addActionListener(aYes);
		bYes.setBounds(pmX + 10, pmY + 110, 100, 80);
		bYes.setBackground(colorButton);
		bYes.setFont(fontButton);
		bYes.setText("Yes");
		board.add(bYes);

		ActNo aNo = new ActNo();
		bNo.addActionListener(aNo);
		bNo.setBounds(pmX + 140, pmY + 110, 100, 80);
		bNo.setBackground(colorButton);
		bNo.setFont(fontButton);
		bNo.setText("No");
		board.add(bNo);

		String shapeS1 = null;
		int id_setter = 0;
		for (int s = 1; s < 5; s++) {
			if (s == 1) {
				shapeS1 = "Spades";
			} else if (s == 2) {
				shapeS1 = "Hearts";
			} else if (s == 3) {
				shapeS1 = "Diamonds";
			} else if (s == 4) {
				shapeS1 = "Clubs";
			}

			for (int j = 1; j < 14; j++) {
				AllCards.add(new Card(j, shapeS1, id_setter));
				id_setter++;
			}

		}

		for (int i = 1; i <= 7; i++) {

			// shuffle the cards
			rand = new Random().nextInt(52);
			playerCards.add(AllCards.get(rand));
			AllCards.get(rand).cardUsed = true;
			// shuffle the cards
			rand = new Random().nextInt(52);

			while (true) {
				if (AllCards.get(rand).cardUsed == false) {
					dealerCards.add(AllCards.get(rand));
					AllCards.get(rand).cardUsed = true;
					break;
				} else {
					rand = new Random().nextInt(52);
				}
			}
		}

		int k = 1;
		for (Card c : playerCards) {
			System.out.println("Player has " + k + " card " + c.name + " of " + c.shape);
			k++;
		}

		int m = 1;
		for (Card c : dealerCards) {
			System.out.println("Dealer has " + m + " card " + c.name + " of " + c.shape);
			m++;
		}

	}
	
	public void renew()
	{
		if(bool_player_turn == true)
		{
			bStart.setVisible(true);
			bQuit.setVisible(true);
			bYes.setVisible(false);
			bNo.setVisible(false);
		}
		else if (bool_dealer_turn == true)
		{
			bStart.setVisible(false);
			bQuit.setVisible(false);
			bYes.setVisible(false);
			bNo.setVisible(false);
		}
		else if (bool_play_more == true)
		{
			bStart.setVisible(false);
			bQuit.setVisible(false);
			bYes.setVisible(false);
			bNo.setVisible(false);
		}
	
		
	}
	
	public void dealerHitStay()
	{
		
	}

	public class Board extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(colorBackground);
			g.fillRect(0, 0, aW, aH);

			// temporary grid painting
			g.setColor(Color.black);
			g.drawRect(gridX, gridY, gridW, gridH);
			// temporary log borders painting
			g.drawRect(gridX, gridY + gridH + 100, gridW, 500);
			// temporary totals and hit-stay messages and buttons grid
			g.drawRect(hsX, hsY, hsW, hsH);
			// yes no grid
			g.drawRect(pmX, pmY, pmW, pmH);
			if(bool_play_more==true)
			{
				g.setFont(fontQuestion);
				g.drawString(play_more_q, pmX+20, pmY+60);
			}
			
			
			//cards painting
			
			for (int i = 0; i < 7; i++) {
				g.drawRect(gridX + i * cardTW + cardSpacing, gridY + cardSpacing, cardAW, cardAH);
				g.drawRect(gridX + i * cardTW + cardSpacing, gridY + cardSpacing + cardTH, cardAW, cardAH);
			}
			
			
			// player card section
			int i = 0;
			for (Card c : playerCards) {
				g.setColor((Color.white));
				g.fillRect(gridX + i * cardTW + cardSpacing, gridY + cardSpacing + cardEdgeSofting, cardAW,
						cardAH - 2 * cardEdgeSofting);
				g.fillRect(gridX + i * cardTW + cardSpacing + cardEdgeSofting, gridY + cardSpacing,
						cardAW - 2 * cardEdgeSofting, cardAH);
				g.fillOval(gridX + i * cardTW + cardSpacing, gridY + cardSpacing, 2 * cardEdgeSofting,
						2 * cardEdgeSofting);
				g.fillOval(gridX + i * cardTW + cardSpacing + cardAW - 2 * cardEdgeSofting, gridY + cardSpacing,
						2 * cardEdgeSofting, 2 * cardEdgeSofting);
				g.fillOval(gridX + i * cardTW + cardSpacing, gridY + cardSpacing + cardAH - 2 * cardEdgeSofting,
						2 * cardEdgeSofting, 2 * cardEdgeSofting);
				g.fillOval(gridX + i * cardTW + cardSpacing + cardAW - 2 * cardEdgeSofting,
						gridY + cardSpacing + cardAH - 2 * cardEdgeSofting, 2 * cardEdgeSofting, 2 * cardEdgeSofting);
				g.setColor(Color.black);

				if (c.shape == "Hearts" || c.shape == "Diamonds") {
					// Test
					g.setColor(Color.red);
					
				}

				if ( c.shape=="Clubs") 
				{
					g.setColor(Color.black);
					g.fillOval(gridX + i * cardTW+40 , gridY+85,40 , 40);
					g.fillOval(gridX + i * cardTW+70 , gridY+85,40 , 40);
					g.fillArc(gridX + i * cardTW+30, gridY+28, 90, 70, 230, 80);
					g.fillRect(gridX+i*cardTW+70, gridY+90, 10, 50);
					
				}
				
				else if ( c.shape=="Hearts") 
				{
					g.setColor(Color.red);
					g.fillOval(gridX + i * cardTW+40 , gridY+70,40 , 40);
					g.fillOval(gridX + i * cardTW+70 , gridY+70,40 , 40);
					g.fillArc(gridX + i * cardTW+30, gridY+96, 90, 70, 50, 80);
					
					
				}
				
				
				
				else if (c.shape =="Diamonds")
				{
					int x1 =75+gridX+i*cardTW, y1=70+gridY, x2=55+gridX+i*cardTW, y2=100+gridY,x3= 75+gridX+i*cardTW, y3=130+gridY,x4=95+gridX+i*cardTW,y4=100+gridY;
					int []Polx = {x1,x2,x3,x4};
					int [] Poly= {y1,y2,y3,y4};
					g.fillPolygon(Polx, Poly,4);
				}
				
				else if ( c.shape=="Spades") 
				{
					g.setColor(Color.black);
					g.fillOval(gridX + i * cardTW+40 , gridY+70,40 , 40);
					g.fillOval(gridX + i * cardTW+70 , gridY+70,40 , 40);
					g.fillArc(gridX + i * cardTW+30, gridY+96, 90, 70, 50, 80);
					
					
				}
				else if ( c.shape=="Clubs") 
				{
					g.setColor(Color.black);
					g.fillOval(gridX + i * cardTW+40 , gridY+70,40 , 40);
					g.fillOval(gridX + i * cardTW+70 , gridY+70,40 , 40);
					g.fillArc(gridX + i * cardTW+30, gridY+96, 90, 70, 50, 80);
					
					
				}

				g.setFont(fontCard);
				g.drawString(c.symbol, gridX + i * cardTW + cardSpacing * 2, gridY + cardAH);
				
			
				
				i++;
				
				
			}

			
			// dealer card section
			if (bool_dealer_turn ==true)
			{	
			 i = 0;
			for (Card c : dealerCards) {
				g.setColor((Color.white));
				g.fillRect(gridX + i * cardTW + cardSpacing, gridY + cardTH+ cardSpacing + cardEdgeSofting, cardAW,
						cardAH - 2 * cardEdgeSofting);
				g.fillRect(gridX + i * cardTW + cardSpacing + cardEdgeSofting, gridY + cardSpacing+ cardTH,
						cardAW - 2 * cardEdgeSofting, cardAH);
				g.fillOval(gridX + i * cardTW + cardSpacing, gridY+ cardTH + cardSpacing, 2 * cardEdgeSofting,
						2 * cardEdgeSofting);
				g.fillOval(gridX + i * cardTW + cardSpacing + cardAW - 2 * cardEdgeSofting, gridY + cardSpacing+ cardTH,
						2 * cardEdgeSofting, 2 * cardEdgeSofting);
				g.fillOval(gridX + i * cardTW + cardSpacing, gridY + + cardTH+cardSpacing + cardAH - 2 * cardEdgeSofting,
						2 * cardEdgeSofting, 2 * cardEdgeSofting);
				g.fillOval(gridX + i * cardTW + cardSpacing + cardAW - 2 * cardEdgeSofting,
						gridY + + cardTH+cardSpacing + cardAH - 2 * cardEdgeSofting, 2 * cardEdgeSofting, 2 * cardEdgeSofting);
				g.setColor(Color.black);

				if (c.shape == "Hearts" || c.shape == "Diamonds") {
					// Test
					g.setColor(Color.red);
					
				}

				if ( c.shape=="Clubs") 
				{
					g.setColor(Color.black);
					g.fillOval(gridX + i * cardTW+40 , gridY+85+ cardTH,40 , 40);
					g.fillOval(gridX + i * cardTW+70 , gridY+85+ cardTH,40 , 40);
					g.fillArc(gridX + i * cardTW+30, gridY+28+ cardTH, 90, 70, 230, 80);
					g.fillRect(gridX+i*cardTW+70, gridY+90+ cardTH, 10, 50);
					
				}
				
				else if ( c.shape=="Hearts") 
				{
					g.setColor(Color.red);
					g.fillOval(gridX + i * cardTW+40 , gridY+70+ cardTH,40 , 40);
					g.fillOval(gridX + i * cardTW+70 , gridY+70+ cardTH,40 , 40);
					g.fillArc(gridX + i * cardTW+30, gridY+96+ cardTH, 90, 70, 50, 80);
					
					
				}
				
				
				
				else if (c.shape =="Diamonds")
				{
					int x1 =75+gridX+i*cardTW, y1=70+gridY+ cardTH, x2=55+gridX+i*cardTW, y2=100+gridY+ cardTH,x3= 75+gridX+i*cardTW, y3=130+gridY+ cardTH,x4=95+gridX+i*cardTW,y4=100+gridY+ cardTH;
					int []Polx = {x1,x2,x3,x4};
					int [] Poly= {y1,y2,y3,y4};
					g.fillPolygon(Polx, Poly,4);
				}
				
				else if ( c.shape=="Spades") 
				{
					g.setColor(Color.black);
					g.fillOval(gridX + i * cardTW+40 , gridY+70+ cardTH,40 , 40);
					g.fillOval(gridX + i * cardTW+70 , gridY+70+ cardTH,40 , 40);
					g.fillArc(gridX + i * cardTW+30, gridY+96+ cardTH, 90, 70, 50, 80);
					
					
				}
				else if ( c.shape=="Clubs") 
				{
					g.setColor(Color.black);
					g.fillOval(gridX + i * cardTW+40 , gridY+70,40 , 40);
					g.fillOval(gridX + i * cardTW+70 , gridY+70,40 , 40);
					g.fillArc(gridX + i * cardTW+30, gridY+96, 90, 70, 50, 80);
					
					
				}

				g.setFont(fontCard);
				g.drawString(c.symbol, gridX + i * cardTW + cardSpacing * 2, gridY+ cardTH + cardAH);
				
			
				
				i++;
				
				
			}
			}

		}
	}

	public class ActStart implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("Good luck!");
			bool_player_turn=false;
			bool_dealer_turn =true;
		}

	}

	public class ActQuit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("See you next time!");
		}

	}

	public class ActYes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("You go!");
		}

	}

	public class ActNo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("That's fine!");
		}

	}
}