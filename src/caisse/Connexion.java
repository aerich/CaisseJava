package caisse;
import java.util.*;
	import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connexion 
{
	 private Connection con;
	 private boolean Etat; 
	 /**
	  * constructeur
	  * @param url
	  * @param user
	  * @param passwd
	  */
	    public Connexion(String url,String user,String passwd)
	    {
	        try 
	        {
	            Class.forName("com.mysql.jdbc.Driver");
	            String url_tot = "jdbc:mysql://"+url;
	            con = DriverManager.getConnection(url_tot, user, passwd);
	            Etat=true;
				
		} catch (Exception e) 
	            {
	                e.printStackTrace();
	                Etat = false;
	            }
	    }
	    /**
	     * Mutateur / Acceseur
	     */
	    public Connection getCon()
	    {
	        return con;
	    }
	    public boolean getEtat()
	    {
	    	return Etat;
	    }
            
            public boolean deconnecter()
            {
                boolean ok=true;
                try {
                        con.close();
                        
                 } catch (SQLException ex) {
                         Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
                         ok=false;
                 }
                return ok;
                
            }
}
