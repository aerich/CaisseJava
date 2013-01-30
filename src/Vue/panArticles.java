/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 *
 * @author Guy
 */
public class panArticles extends JPanel{

    public panArticles() {
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT,2,2);
        this.setLayout(flowLayout);
        JButton btn = new BoutonImage("croissant.jpg",100,100);
        
        add(btn);
        add(new BoutonImage("croissant.jpg",100,100));
        add(new BoutonImage("croissant.jpg",100,100));
        add(new BoutonImage("croissant.jpg",100,100));
        add(new BoutonImage("croissant.jpg",100,100));
        
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        slider.setSize(100, 20);
         ituneSlider sliderUI = new ituneSlider(slider);
        slider.setUI(sliderUI);
        add(slider);
          
        this.doLayout();
    }
    
     Component[] com;
    
    public void desactiverComposants()
    {
        com = this.getComponents();
        //Inside you action event where you want to disable everything  
        //Do the following  
        for (int a = 0; a < com.length; a++) {  
             com[a].setEnabled(false);  
        }
    }
    
    public void activerComposants()
    {
        com = this.getComponents();
        //Inside you action event where you want to disable everything  
        //Do the following  
        for (int a = 0; a < com.length; a++) {  
             com[a].setEnabled(true);  
        }
    }
    
    
    
    public static void main(String[] args)
	{
            JFrame fen = new JFrame();
            fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fen.setSize(500, 500);
             final panArticles pan = new panArticles();
            
            fen.add(pan);
            //fen.pack();
            pan.desactiverComposants();
            
            JPanel glass =  new glassPan();
            
            fen.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                pan.activerComposants();
                
            }
        });
            
            glass.setOpaque(false);
            
            fen.setGlassPane(glass);
            
            glass.setVisible(true);
            
            fen.setVisible(true);
        }
    
    
    
    
}


