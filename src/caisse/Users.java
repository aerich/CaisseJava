package caisse;

import java.util.*;
import java.sql.*;
public class Users 
{
    Connection BDD;
    private Statement state;
    private int nombrecol,nombrelig;
    
    /**
     * Constructeur
     * @param con
     */
    public Users(Connection con)
    {
        BDD=con;
    }
    
    /**
     * Fonction pour connaitre le nombre d'entree dans une table
     * @param zone
     * @param id
     * @param option
     * @return ligne => le nombre de ligned d'une table
     */
    public int quantite(String zone, String id,String option)
    {
        int ligne=1;
        try {
            state = BDD.createStatement();
            ResultSet result = state.executeQuery("SELECT "+id+" FROM "+zone+" "+option+"");

            while(result.next())
            {			
            	ligne++;
            }
             result.close();
             state.close();
        }catch (Exception e) 
            {
                e.printStackTrace();
            }
        return ligne;
    }
    /**
     * Fonction qui retourne tout les utilisateurs valide.
     * @return
     */
    public String [][] ListeUser()
    {
        String[][] user=new String[this.quantite("users","id","WHERE actif=1")-1][this.getnombreCol()];
        try {
            state = BDD.createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM users WHERE actif=1");
            ResultSetMetaData resultMeta = result.getMetaData();
            int j=0;
            while(result.next())
	        {
            	for(int i = 1; i <=  resultMeta.getColumnCount(); i++)
            	{
	               user[j][i-1]=result.getObject(i).toString();
	            }
            	j++;
            }
             result.close();
             state.close();
             
        }catch (Exception e) 
            {
                e.printStackTrace();
            }
        return user;
    }
    
    
    
    /**
     * Fonction qui retourne le nombre de colonne de la table users
     * @return nombrecol
     */
    public int getnombreCol()
    {
        try {
            state = BDD.createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM users");
            ResultSetMetaData resultMeta = result.getMetaData();
            nombrecol=resultMeta.getColumnCount();
             result.close();
             state.close();
             
        }catch (Exception e) 
            {
                e.printStackTrace();
            }
        return nombrecol;
    }
    
    /**
     * Retourne le nombre de ligne 
     * @return nombrelig
     */
    public int getnombrelign()
    {
        nombrelig=this.quantite("users","id","");
        return nombrelig;
    }
    
    /**
     * Fonction qui ajoute des utlisateurs dans la base de donnees
     * @param donnee
     * @param donneechif
     * @return true / false
     */
    public boolean AjoutUser2(String donnee[],String donneechif[])
    {
        try {
             
            PreparedStatement requete = BDD.prepareStatement("insert into users (mail,nom,prenom,adresse,Ville,localite,telephone,actif) values (?,?,?,?,?,?,?,?)");
            for(int i=1;i<=donnee.length;i++)
            {
            		requete.setString(i, donnee[i-1]);
            }
            for(int i=1;i<=donneechif.length;i++)
            {
            		requete.setString(i+donnee.length, donneechif[i-1]);
            }
            
            requete.setString(8, "1");
             requete.executeUpdate();
             requete.close();
             return true;
        }catch (Exception e) 
            {
                e.printStackTrace();
                return false;
            }
    }
}

