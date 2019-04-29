
public class main implements Runnable {
	
	GUI gui= new GUI();
 public static void main(String[] args)
 {
	 new Thread( new main()).start();
 }

 
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		while (true)
		{
			// refreshing the screen (JPanel)
			gui.renew();
			gui.repaint();
			
		}
	}
}
