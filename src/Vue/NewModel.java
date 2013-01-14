package Vue;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;


/**
 * Nouveau modele de la jtable
 * Code repris du site du zero (les vraies tableaux)
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
	 * Retourne la valeur e l'emplacement specifie
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
	* Retourne le titre de la colonne e l'indice specife
	*/
	public String getColumnName(int col)
	{
	  return this.title[col];
	}
	
	/**
	* Retourne la classe de la donnee de la colonne
	* @param col
	*/
	public Class getColumnClass(int col)
	{
		//On retourne le type de la cellule e la colonne demandee
		//On se moque de la ligne puisque les donnees sur chaque ligne sont les memes
		//On choisit donc la premiere ligne
		return this.data[0][col].getClass();
	}
	public boolean isCellEditable(int row, int col)
	{
		//On appelle la methode getValueAt qui retourne la valeur d'une cellule
		//Et on fait un traitement specifique si c'est un JButton
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
		//Cette methode permet d'avertir le tableau que les donnees ont ete modifiees
		//Ce qui permet une mise e jours complete du tableau
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
	 * Methode permettant de retirer une ligne du tableau
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
		//Cette methode permet d'avertir le tableau que les donnees ont ete modifiees
		//Ce qui permet une mise e jours complete du tableau
		this.fireTableDataChanged();
	}
	
}
