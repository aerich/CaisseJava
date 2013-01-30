/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Guy
 */
public class glassPan extends JPanel{
    
    Color  mauvebleutrans =new Color(153, 153, 255, 127);
     public glassPan() {
   
    }

   

    @Override
    public void paintComponent(Graphics g) {
       
        
        
        //Set the color to with red with a 50% alpha  
      g.setColor(mauvebleutrans);  
   
      //Fill a rectangle with the 50% red color  
      g.fillRect(0, 0, this.getWidth(), this.getHeight());  
    }
    
}
