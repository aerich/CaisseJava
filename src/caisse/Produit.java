package caisse;

import java.util.*;
import java.sql.*;
public class Produit 
{
    Connection BDD;
    private Statement state;
    /**
     * Constructeur du produit
     * @param con
     */
    public Produit(Connection con)
    {
        BDD=con;
    }
    
    /**
     * Fonction permettant de connaitrre le nombre de ligne dans une BDD
     * @return i
     */
    public int quantite()
    {
        int i=1;
        try {
            state = BDD.createStatement();
            ResultSet result = state.executeQuery("SELECT id_art FROM article");

            while(result.next())
            {			
                i++;
            }
             result.close();
             state.close();
        }catch (Exception e) 
            {
                e.printStackTrace();
            }
        return i;
    }
    
    
    /**
     * Fonction qui retourne les articles
     * @return art
     */
    public String [][] listeArticle()
    {
        String[][] art=new String[this.quantite()-1][5];
        try {
            Statement state = BDD.createStatement();
            ResultSet result = state.executeQuery("SELECT article.id_art,nom,prix,stock,nomCat" +
            		" FROM article LEFT JOIN categorie ON article.idCategorie=categorie.id_cat WHERE stock > 0");
            ResultSetMetaData resultMeta = result.getMetaData();
            int j=0;
            while(result.next())
            {	
            	for(int i = 1; i <=  resultMeta.getColumnCount(); i++)
            	{
            		art[j][i-1]=result.getObject(i).toString();
	            }
            	j++;
            }
             result.close();
             state.close();
             
        }catch (Exception e) 
            {
                e.printStackTrace();
            }
        return art;
    }
    /**
     * Fonction qui retourne les articles en filtrant la sortie
     * @param filtre
     * @return art=> tout les article dans un ordre demande
     */
    public String [][] Article(String filtre)
    {
        String[][] art=new String[this.quantite()-1][5];
        try {
            Statement state = BDD.createStatement();
            ResultSet result = state.executeQuery("SELECT article.id_art,nom,prix,stock,nomCat" +
            		" FROM article LEFT JOIN categorie ON article.idCategorie=categorie.id_cat WHERE stock > 1 ORDER BY "+filtre+"");
            ResultSetMetaData resultMeta = result.getMetaData();
            int j=0;
            while(result.next())
            {	
            	for(int i = 1; i <=  resultMeta.getColumnCount(); i++)
            	{
            		art[j][i-1]=result.getObject(i).toString();
	            }
            	j++;
            }
             result.close();
             state.close();
             
        }catch (Exception e) 
            {
                e.printStackTrace();
            }
        return art;
    }
    
    //teste de verification
    public void verification(Object tabproduit,Object produitSelect)
    {
    	
    }
    
    
    /**
     * Fonction qui ajoute un produit dans la base de donnees
     * @param donnee
     * @return true/false
     */
    public boolean AjoutProduit(Object donnee[])
    {
        try {
             
            PreparedStatement requete = BDD.prepareStatement("insert into article (nom,description,prix,stock,idCategorie) values (?,?,?,?,?)");
            for(int i=1;i<=donnee.length;i++)
            {
            		requete.setObject(i, donnee[i-1]);
            }
             requete.executeUpdate();
             requete.close();
             return true;
        }catch (Exception e) 
            {
                e.printStackTrace();
                return false;
            }
    }
    /**
     * Fonction qui met e jour le stock d'un aticle
     * @param id
     * @param valeur
     */
    public void updatestock(int id,int valeur)
    {
    	 try {
    		 state = BDD.createStatement();
             PreparedStatement requete = BDD.prepareStatement("UPDATE article SET stock = ? WHERE id_art = "+id+"");
             requete.setInt(1, valeur);
             requete.executeUpdate();
             requete.close();
         }catch (Exception e) 
             {
             }
    }
    
    /**
     * fonction qui retourne le stock d'un article
     * @param id
     * @return valeur => le stock
     */
    public int getstock(int id)
    {
    	int valeur=0;
    	try {
    		state = BDD.createStatement();
    		ResultSet result = state.executeQuery("SELECT stock FROM article WHERE id_art = "+id+"");
    		
    		while(result.next())
            {		
                valeur=Integer.parseInt(result.getObject(1).toString());
            }
    		result.close();
            state.close();
        }catch (Exception e) 
            {
            }
    	return valeur;
    }
}
