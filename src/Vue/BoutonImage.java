/*
 * Bouton AVEC IMAGE
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JButton;

/**
 *
 * @author Guy
 */
public class BoutonImage extends JButton{
  public BoutonImage()
  {}
    
   Image img;
   public static Color  mauvebleutrans =new Color(153, 153, 255, 70);
   
  public BoutonImage(String ressource,int largeur,int hauteur)
  {
     this.setSize(largeur, hauteur);
     this.setPreferredSize(new Dimension(largeur, hauteur));
      this.largeur = largeur;
      this.hauteur = hauteur;
    try
    {
      img = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource(ressource), ressource));
      
    }
    catch (Exception e) { System.out.println("Image nontrouvée"); }
  }
  public BoutonImage(Image ressource)
  {  
      img=ressource;
     
  } 
    
    int largeur;
    int hauteur;
    @Override
    protected void paintComponent(Graphics g)
  {
    //super.paintComponent(g); 
    //largeur =  this.getWidth();
    //hauteur = this.getHeight();
    g.setColor(mauvebleutrans);
    g.fillRect(0, 0, largeur, hauteur);
    
    if (img != null)
      g.drawImage(img, 0,0,largeur,hauteur,this);
   
       
  }
       
  }

