/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

/**
 *
 * @author Guy
 */

    public class ituneSlider extends BasicSliderUI 
    {
            private int thumbHeight = 0;
    private int thumbWidth = 0;
     
    public ituneSlider(JSlider slider)
    {
    super(slider);
    this.thumbHeight = slider.getHeight();
    this.thumbWidth = slider.getHeight();
    }
    
       
    
    @Override
    protected Dimension getThumbSize()
    {
    return new Dimension(thumbHeight, thumbWidth);
    }
   
    public void paintThumb(Graphics g)
    {
    int y = 3;
    int x = thumbRect.x + 1;
    int height = thumbHeight - 6;
    int width = thumbWidth - 3;
     
    g.setColor(Color.BLACK);
    g.fillRect(x, y, width, height);
    }
     
    @Override
    public void paintTrack(Graphics g)
    {
    g.setColor(new Color(230, 230, 210));
    g.fillRect(0, 0, slider.getWidth(), slider.getHeight());
    g.setColor(Color.BLACK);
    g.drawRect(0, 0, slider.getWidth() - 1, slider.getHeight() - 1);
     
    int elapsedIndicatorWidth = thumbWidth / 4;
    for (int i = 3; i < thumbRect.getX(); i = i + (elapsedIndicatorWidth * 2)) {
    g.setColor(new Color(170, 170, 150));
    g.fillRect(i, 3, elapsedIndicatorWidth, slider.getHeight() - (3 * 2));
    }
    }
    
    }

