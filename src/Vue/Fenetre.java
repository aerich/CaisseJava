package Vue;

import java.awt.Color;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Fenetre extends JDialog
{

   
	public Fenetre(String titre,int hauteur,int largeur,Frame owner, boolean modal)
	{
            super(owner, titre, modal);
	    this.setSize(hauteur, largeur);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setUndecorated(true);
            this.setAlwaysOnTop(true);
            this.getRootPane().setBorder(new LineBorder(Color.darkGray, 10));
            
            
	}
}
