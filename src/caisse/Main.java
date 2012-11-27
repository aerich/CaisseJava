package caisse;
import Vue.Vue;
import java.awt.*;


public class Main 
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				try {
					Vue frame = new Vue();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

}
}