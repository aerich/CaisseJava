package Vue;

import javax.swing.JFrame;

public class Fenetre extends JFrame
{
	public Fenetre(String titre,int hauteur,int largeur)
	{
		this.setTitle(titre);
	    this.setSize(hauteur, largeur);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setVisible(true);
	}
}
