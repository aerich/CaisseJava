/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package caisse;

import java.util.*;
import java.sql.*;
/**
 *
 * @author Guy
 */
public class Categorie {
    
    Connection BDD;
    private Statement state;
        
    public Categorie(Connection con)
    {
        BDD=con;
    }
    
    public String [] listeCategorie()
    {
        ArrayList<String> articlesTab = new ArrayList<String>();
        
        try {
            state = BDD.createStatement();
            ResultSet result = state.executeQuery("SELECT nomCat FROM categorie");
            ResultSetMetaData resultMeta = result.getMetaData();
            int j=0;
            while(result.next())
            {	
            	for(int i = 1; i <=  resultMeta.getColumnCount(); i++)
            	{
            		articlesTab.add(result.getObject(i).toString());
                        //System.out.println(result.getObject(i).toString());
	            }
            	j++;
            }
             result.close();
             state.close();
        }catch (Exception e) 
            {
                e.printStackTrace();
            }
        return Arrays.copyOf(articlesTab.toArray(), articlesTab.size(), String[].class);
    }
}
