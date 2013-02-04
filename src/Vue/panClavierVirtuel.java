/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Guy
 */
public class panClavierVirtuel extends JPanel
{
    
    public panClavierVirtuel() {
      
        this.setLayout(new GridLayout(4, 5,10,10));
         this.add(new Bouton("1"));
         this.add(new Bouton("2"));
         this.add(new Bouton("3"));
         this.add(new Bouton("4"));
         this.add(new Bouton("5"));
         this.add(new Bouton("6"));
         this.add(new Bouton("7"));
         this.add(new Bouton("8"));
         this.add(new Bouton("9"));
         this.add(new Bouton("0"));
         this.add(new Bouton("←"));
         this.add(new Bouton("<┘"));
         
        
    }
    
  
    
}
