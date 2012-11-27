package caisse;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;

public class Facture
{
	Connection BDD;
    private Statement state;
    /**
     * Constructeur
     * @param con
     */
    public Facture(Connection con)
    {
    	BDD=con;
    }
    
    /**
     * Fonction qui ajoute une facture dans la base.
     * @param idClient
     * @param total => total d'une facture
     * @return true/false
     */
    public boolean ajoutFacture(int idClient,float total)
    {
    	Date now = new Date();
    	Timestamp NOW = new Timestamp(now.getTime());
    	
    	try {
            
            PreparedStatement requete = BDD.prepareStatement("INSERT INTO facture (idClient,dateFact,total)VALUES(?,?,?)");
            
            requete.setInt(1, idClient);
            requete.setTimestamp(2, NOW);
            requete.setFloat(3, total);

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
     * retourne l'id de la dernière facture
     * @return did => id dernière facture
     */
    public int getlastfact()
    {
    	int did=0;
         try {
             state = BDD.createStatement();
             ResultSet result = state.executeQuery("SELECT max(id_fac) FROM facture");
             while(result.next())
             {			
                 did=Integer.parseInt(result.getObject(1).toString());
             }
              
              result.close();
              state.close();
              
         }catch (Exception e) 
             {
                 e.printStackTrace();
             }
         //return i;
         return did;
    }
}
