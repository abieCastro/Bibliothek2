/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historialPrestamos;

import principal.conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mac
 */
public class controlHistorial implements ActionListener{
    conexion con = new conexion();
    interfazHistorial vistaHist;
    
    public controlHistorial(interfazHistorial vistaHist) {
        this.vistaHist = vistaHist;
    }
    
    public void listaHistGral(JTable tablaHistorial) {         
        ArrayList<historial> listaHistorial = new ArrayList<historial>();
        historial hist;
        try {
            Connection acceDB = con.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("SELECT * FROM historial");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                hist = new historial();
                hist.setNoControl(rs.getString(4));
                hist.setGrado(rs.getString(5));
                hist.setEjemplar(rs.getString(6));
                hist.setDetalle(rs.getString(7));              
                hist.setFechaPrestamo(rs.getString(8));
                hist.setFechaLimite(rs.getString(9));
                hist.setFechaDevolucion(rs.getString(10));
                listaHistorial.add(hist);
            }
        }catch(Exception e) {            
        }        
        
        DefaultTableModel modeloHistorial = new DefaultTableModel();
        tablaHistorial.setModel(modeloHistorial);
        modeloHistorial.fireTableDataChanged();
        tablaHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        modeloHistorial.addColumn("noControl");
        modeloHistorial.addColumn("grado");
        modeloHistorial.addColumn("ejemplar");
        modeloHistorial.addColumn("detalle");
        modeloHistorial.addColumn("fechaPréstamo");
        modeloHistorial.addColumn("fechaLímite");
        modeloHistorial.addColumn("fechaDevolución");
        
        Object[] columna = new Object[11];
        
        for(int x=0; x<listaHistorial.size(); x++) {
            columna[0] = listaHistorial.get(x).getNoControl();
            columna[1] = listaHistorial.get(x).getGrado();
            columna[2] = listaHistorial.get(x).getEjemplar();
            columna[3] = listaHistorial.get(x).getDetalle();
            columna[4] = listaHistorial.get(x).getFechaPrestamo();
            columna[5] = listaHistorial.get(x).getFechaLimite();
            columna[6] = listaHistorial.get(x).getFechaDevolucion();
            modeloHistorial.addRow(columna);
        }
    }
    
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource()== vistaHist.rbHistGeneral){
            if (vistaHist.rbHistGeneral.isSelected()){
                vistaHist.jpTablaHistGral.setVisible(true);
                vistaHist.spHistGeneral.setVisible(true);  
                vistaHist.jpTablaHistLib.setVisible(false);
                vistaHist.spHistLibros.setVisible(false);
                vistaHist.jpTablaHistMatVis.setVisible(false);
                vistaHist.spHistMatVis.setVisible(false);
            }
        }
        
        if (evento.getSource()== vistaHist.rbHistLibros){
            if (vistaHist.rbHistLibros.isSelected()){
                vistaHist.jpTablaHistGral.setVisible(false);
                vistaHist.spHistGeneral.setVisible(false);  
                vistaHist.jpTablaHistLib.setVisible(true);
                vistaHist.spHistLibros.setVisible(true);
                vistaHist.jpTablaHistMatVis.setVisible(false);
                vistaHist.spHistMatVis.setVisible(false);
            }
        }
        
        if (evento.getSource()== vistaHist.rbHistMatVis){
            if (vistaHist.rbHistMatVis.isSelected()){
                vistaHist.jpTablaHistGral.setVisible(false);
                vistaHist.spHistGeneral.setVisible(false);  
                vistaHist.jpTablaHistLib.setVisible(false);
                vistaHist.spHistLibros.setVisible(false);
                vistaHist.jpTablaHistMatVis.setVisible(true);
                vistaHist.spHistMatVis.setVisible(true);
            }
        }
    }
}
