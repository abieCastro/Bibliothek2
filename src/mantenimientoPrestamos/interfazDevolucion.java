/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mantenimientoPrestamos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author Mac
 */
public class interfazDevolucion extends JFrame{
    controlDevolucion cd = new controlDevolucion(this);
    
    JPanel jpPrincDev;  
    
    /*PANELES: OPCIONES DEL CATÁLOGO DEVOLUCIÓN*/
    JPanel jpDevElegir;
    
    /*Devolución a elegir*/   
    Border borderDevElegir;
    
    JLabel labDevEjempBus;
    JTextField txtDevEjempBus;     
    JButton btDevBuscarEjemp;
    JButton btDevolver;  
    
    /*Información del libro o mat vis seleccionado*/
    int selectLibro=-1, selectMatVis=-1; 
    String clavePrest,ejemplar;
    
    //Libro
    JPanel jpTablaDevLib;
    JTable tbDevLibro;  
    JScrollPane spDevLibro;
    
    //MaterialVisual    
    JPanel jpTablaDevMaVi;
    JTable tbDevMatVis;
    JScrollPane spDevMatVis;
    
    JButton btCancelarDev;
    
    
    public JPanel jpPrincDev() {  
        jpPrincDev = new JPanel();
        jpPrincDev.setLayout(null);
        jpPrincDev.setBounds(10, 50, 1340, 650);
        jpPrincDev.setVisible(true);        
        
        jpDevElegir = new JPanel();
        jpDevElegir.setLayout(null);
        jpDevElegir.setBounds(10, 10, 1320, 600);
        jpDevElegir.setVisible(true);
        jpPrincDev.add(jpDevElegir);
        
        borderDevElegir = BorderFactory.createTitledBorder("Elegir");
        jpDevElegir.setBorder(borderDevElegir);
               
        labDevEjempBus = new JLabel("Ejemplar: ");
        labDevEjempBus.setVisible(true);
        labDevEjempBus.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
        labDevEjempBus.setBounds(500, 20, 100, 50);
        jpDevElegir.add(labDevEjempBus);
        
        txtDevEjempBus = new JTextField();    
        txtDevEjempBus.setBounds(600, 30, 135, 25);
        txtDevEjempBus.setVisible(true);
        jpDevElegir.add(txtDevEjempBus);
       
        btDevBuscarEjemp = new JButton("Buscar");
        btDevBuscarEjemp.setBounds(750, 30, 90, 25);
        btDevBuscarEjemp.setVisible(true);
        jpDevElegir.add(btDevBuscarEjemp);
        
        jpTablaDevLib = new JPanel();
        jpTablaDevLib.setLayout(null);
        jpTablaDevLib.setBounds(350, 140, 620, 40);
        jpDevElegir.add(jpTablaDevLib);
        jpTablaDevLib.setVisible(false);
                
        tbDevLibro = new JTable();       
        tbDevLibro.setBounds(350, 140, 720, 100);
        spDevLibro = new JScrollPane(tbDevLibro);
        spDevLibro.setBounds(0, 0, 620, 100);
        jpTablaDevLib.add(spDevLibro);
        spDevLibro.setVisible(true);
        
        jpTablaDevMaVi = new JPanel();
        jpTablaDevMaVi.setLayout(null);
        jpTablaDevMaVi.setBounds(350, 140, 620, 40);
        jpDevElegir.add(jpTablaDevMaVi);
        jpTablaDevMaVi.setVisible(false);
                
        tbDevMatVis = new JTable();       
        tbDevMatVis.setBounds(350, 140, 720, 100);
        spDevMatVis = new JScrollPane(tbDevMatVis);
        spDevMatVis.setBounds(0, 0, 620, 100);
        jpTablaDevMaVi.add(spDevMatVis);
        spDevMatVis.setVisible(true);        
        
        btDevolver = new JButton("Devolver");
        btDevolver.setBounds(750, 210, 90, 25);
        btDevolver.setVisible(false);
        jpDevElegir.add(btDevolver);
        
        btCancelarDev = new JButton("Cancelar");
        btCancelarDev.setBounds(880, 210, 90, 25);        
        btCancelarDev.setVisible(false);
        jpDevElegir.add(btCancelarDev);
        
        btDevBuscarEjemp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {                  
                boolean validarEjemp = cd.validarTipoEjempD();
                if(validarEjemp == true) {
                    boolean valBusqEjemp=cd.buscarEjempDev(txtDevEjempBus.getText());                      
                    if(valBusqEjemp == false) {
                        JOptionPane.showMessageDialog(null, "Clave de ejemplar inexistente", "ERROR", JOptionPane.ERROR_MESSAGE);                           
                        txtDevEjempBus.setText("");
                    } else {
                        txtDevEjempBus.setText("");
                        boolean validarEstado=cd.validarEstadoD(txtDevEjempBus.getText());
                        if(validarEstado==false) {
                            JOptionPane.showMessageDialog(null, "Ya se encuentra disponible, VERIFQUE EL EJEMPLAR", "ERROR", JOptionPane.ERROR_MESSAGE);                           
                        } else {                            
                            cd.mostrarEjempD(tbDevLibro, tbDevMatVis);                           
                            btDevolver.setVisible(true);
                            btCancelarDev.setVisible(true);
                        }
                    }                    
                } else {
                    JOptionPane.showMessageDialog(null, "Ingresa una clave del ejemplar valida", "INGRESA", JOptionPane.ERROR_MESSAGE);
                    txtDevEjempBus.setText("");
                } 
            }});
                       
        btDevolver.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {  
                cd.regDevolucion();                 
            }});  
        
        btCancelarDev.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cd.limpiarTabla();                
            }
        });

                
        return jpPrincDev;
    } 
    
    public JPanel getTabLib() {
        return jpTablaDevLib;
    }
    
    public JPanel getTabMatVis() {
        return jpTablaDevMaVi;
    }
    
    public void limpiarMenuDev() {
        cd.limpiarTabla();
    }
}
