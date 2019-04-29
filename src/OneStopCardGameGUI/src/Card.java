
public class Card {

	int number;
	String shape;
	boolean cardUsed = false;
	String symbol;
	String name;
	int identity;
	
	
	public Card(int n, String s, int id)
	{
		this.number =n;
		this.shape =s;
		this.identity =id;
		
		
		if (number <11 && number >1 )
		{
			symbol = Integer.toString(number);
			name =Integer.toString(number);
		}
		else if (number ==1)
		{
			symbol ="A";
			name ="Ace";
		}
		else if (number ==11)
		{
			symbol ="J";
			name = "Jack";
		}
		else if (number ==12)
		{
			symbol ="Q";
			name = "Queen";
			
		}
		else if (number == 13 )
		{
			symbol ="K";
			name = "King";
		}
		
		System.out.println("Card " + name + " of " + shape + " was created");
		System.out.println("Card "+name+" of "+ shape + " was created, id "+ this.identity);
	}
}
