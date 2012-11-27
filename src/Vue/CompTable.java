package Vue;

import java.awt.Component;

import javax.swing.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CompTable extends DefaultTableCellRenderer
{
	public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row,int column) 
	{
		//Si la valeur de la cellule est un JButton, on transtype notre valeur
		if (value instanceof JButton)
		{
			return (JButton) value;
		}
		else
			return this;
	}
}
