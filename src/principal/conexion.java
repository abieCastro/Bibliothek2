/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mac
 */
public class conexion {
    Connection conect=null;
    public Connection getConexion() {
        try {
           
            Class.forName("com.mysql.jdbc.Driver");
            conect =DriverManager.getConnection("jdbc:mysql://localhost/bibliothek","root","root");
            
        }catch(SQLException e) {
                       
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }catch(Exception e) {
            System.out.println("Se ha encontrado el siguiente error: "+e.getMessage());
        }
        return conect;      
    } 
}