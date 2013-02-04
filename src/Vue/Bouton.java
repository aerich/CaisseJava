/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.text.JTextComponent;
import javax.swing.text.TableView.TableCell;

/**
 *
 * @author Guy
 */
public final class Bouton extends JButton{
    Robot robot;
    public static JTable cible;
    public static JTextComponent editor;
    
    ActionListener action= new ActionListener() {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            String chaine=getText();
           String str=editor.getText();
           if(chaine.contains("←"))
           {
               
               try {
                   editor.setText(str.substring(0, str.length()-1));
               } catch (java.lang.StringIndexOutOfBoundsException ev) {
               }
               
           }
           else
           if(chaine.contains("<┘")){
           cible.getCellEditor().cancelCellEditing();}
            /*else if(chaine.contains("-")){Gui_TP1_ex03.calc.operation(chaine);}
            else if (chaine.contains("X")){Gui_TP1_ex03.calc.operation(chaine);}
            else if (chaine.contains(":")){Gui_TP1_ex03.calc.operation(chaine);}
            else if (chaine.contains("=")){Gui_TP1_ex03.calc.calcul();}
            else{Gui_TP1_ex03.calc.afficheur.setText(Gui_TP1_ex03.calc.afficheur.getText()+getText());}*/
            
        else{editor.setText(str+chaine);
           }
        
    };
    };
    public static void setTarget(JTable tab)
    {
        cible=tab;
    }
    
    Bouton(String string) {
        super(string);
        setSize(50, 50);
        this.addActionListener(action);
    }

            
            }
