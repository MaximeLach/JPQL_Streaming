/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Test;

/**
 *
 * @author tom
 */
public class ExportDB {
    
    @Test
    public void test() throws ClassNotFoundException, SQLException, DatabaseUnitException, IOException{
        
        // Connexion DB
        Class driverClass = Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/sample", "app", "app");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
        
        // Récupération noms tables
        String[] depTableNames = 
          TablesDependencyHelper.getAllDependentTables( connection, "LIEN" );
        IDataSet depDataset = connection.createDataSet( depTableNames );
        
        // Export
        FlatXmlDataSet.write(depDataset, new FileOutputStream("donnees.xml")); 
    }
    
}
