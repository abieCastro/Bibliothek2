/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
import mantenimientoPrestamos.interfazPrestamo;

/**
 *
 * @author Mac
 */
public class principal {
    public static void main(String [] args) throws SQLException, ParseException{       
        menuPrinc  i  = new menuPrinc();        
        i.setVisible(true);
    }    
}
