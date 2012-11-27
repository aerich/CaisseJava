package Vue;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;


/**
 * Nouveau mod�le de la jtable
 * Code repis du site du zero (les vraies tableaux)
 * @author Wes
 *
 */
public class NewModel extends AbstractTableModel
{
	private Object[][] data;
	private String[] title;
	
	public NewModel(Object[][] data, String[] title)
	{
		this.data = data;
		this.title = title;
	}
	/**
	 * Retourne le nombre de colonnes
	 */
	public int getColumnCount()
	{
		return this.title.length;
	}
	
	/**
	 * Retourne le nombre de lignes
	 */
	public int getRowCount() 
	{
		return this.data.length;
	}
	
	/**
	 * Retourne la valeur � l'emplacement sp�cifi�
	 */
	public Object getValueAt(int row, int col)
	{
		return this.data[row][col];
	}
	/****************************************************/
	public void SetValueAt(int row, int col,Object Valeur)
	{
		this.data[row][col]=Valeur;
		this.update2();
	}
	/********************************************************/
	/**
	* Retourne le titre de la colonne � l'indice sp�cif�
	*/
	public String getColumnName(int col)
	{
	  return this.title[col];
	}
	
	/**
	* Retourne la classe de la donn�e de la colonne
	* @param col
	*/
	public Class getColumnClass(int col)
	{
		//On retourne le type de la cellule � la colonne demand�e
		//On se moque de la ligne puisque les donn�es sur chaque ligne sont les m�mes
		//On choisit donc la premi�re ligne
		return this.data[0][col].getClass();
	}
	public boolean isCellEditable(int row, int col)
	{
		//On appelle la m�thode getValueAt qui retourne la valeur d'une cellule
		//Et on fait un traitement sp�cifique si c'est un JButton
		if(getValueAt(0, col) instanceof JButton)
			return true;
		return false; 
	}
	/**
	 * Permet d'ajouter une ligne dans le tableau
	 * @param data
	 */
	public void addRow(Object[] data)
	{
		int indice = 0, nbRow = this.getRowCount(), nbCol = this.getColumnCount();
		
		Object temp[][] = this.data;
		this.data = new Object[nbRow+1][nbCol];
		
		for(Object[] value : temp)
			this.data[indice++] = value;
		
			
		this.data[indice] = data;
		temp = null;
		//Cette m�thode permet d'avertir le tableau que les donn�es ont �t� modifi�es
		//Ce qui permet une mise � jours compl�te du tableau
		this.fireTableDataChanged();
	}
	public void update(Object[] data)
	{
		this.data = new Object[0][0];
		this.fireTableDataChanged();
	}
	public void update2()
	{
		this.fireTableDataChanged();
	}
	/**
	 * M�thode permettant de retirer une ligne du tableau
	 * @param position
	 */
	public void removeRow(int position){
		
		int indice = 0, indice2 = 0, nbRow = this.getRowCount()-1, nbCol = this.getColumnCount();
		Object temp[][] = new Object[nbRow][nbCol];
		
		for(Object[] value : this.data){
			if(indice != position){
				temp[indice2++] = value;
			}
			System.out.println("Indice = " + indice);
			indice++;
		}
		this.data = temp;
		temp = null;
		//Cette m�thode permet d'avertir le tableau que les donn�es ont �t� modifi�es
		//Ce qui permet une mise � jours compl�te du tableau
		this.fireTableDataChanged();
	}
	
}
