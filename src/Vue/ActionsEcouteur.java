/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Guy
 */
public class ActionsEcouteur {

    PropertyChangeListener montantAZero;
    JLabel lblmontant;
    JButton bouton;
    Fenetre fenetre;
    
    public ActionsEcouteur(JLabel montant,JButton boutonValider)
    {
        lblmontant=montant;
        bouton=boutonValider;
        
        montantAZero = new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                
                if(lblmontant.getText().equals("0,00")||lblmontant.getText().equals(""))
                {
                    bouton.setEnabled(false);
                }
                else
                {
                    bouton.setEnabled(true);
                }
            }
        };
        
        lblmontant.addPropertyChangeListener("text",montantAZero);
        
    }
    
    public void setDispose(JButton jButton,Fenetre fen)
    {
        bouton = jButton;
        fenetre = fen;
        jButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fenetre.dispose();
            }
        });
        
    }
    
    
    
}
