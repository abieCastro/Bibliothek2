/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package principal;

import historialPrestamos.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import mantenimientoLibros.*;
import mantenimientoMaterialVisual.*;
import mantenimientoPrestamos.*;
import mantenimientoSolicitantes.*;
/**
 *
 * @author Mac
 */
public class menuPrinc extends JFrame{
    interfazSolicitante menuSolic = new interfazSolicitante();
    interfazLibro menuLib = new interfazLibro();
    interfazMaterialVisual menuMatVis = new interfazMaterialVisual();
    interfazPrestamo menuPres = new interfazPrestamo();
    interfazDevolucion menuDev = new interfazDevolucion();
    interfazHistorial menuHist = new interfazHistorial();
   
    
    //Opciones Generales    
    JToolBar tbarMenuPrinc;
    JButton btMenuCatalogos;
    JButton btMenuSolic;
    JButton btMenuPres;
    JButton btMenuDev;
    JButton btMenuHistorial;    
    
    JLabel labTitulo;
    JLabel labSubTitulo;
    JLabel labImgPrinc;
    
    JPanel jpMenuCatalogos;
    JPanel jpMenuSolic;
    JPanel jpMenuPres;  
    JPanel jpMenuDev;
    JPanel jpMenuHistorial;
    
    
    
    
    public menuPrinc () throws ParseException{
        this.setLayout(null);
        this.setTitle("Bibliothek");
        this.setBounds(0, 0, 1400, 700);
        this.setDefaultCloseOperation(3);
        inicializar();
    
    }
    
    public void inicializar() throws ParseException{
        tbarMenuPrinc = new JToolBar();
        btMenuCatalogos = new JButton("Catálogos");
        btMenuCatalogos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMenuCatalogosActionPerformed(evt);
            }
        });
        
        btMenuSolic = new JButton("Solicitantes");
        btMenuSolic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMenuSolicActionPerformed(evt);
            }
        });
        
        btMenuPres = new JButton("Préstamos");           
        btMenuPres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrestamosActionPerformed(evt);
            }
        });
        
        btMenuDev = new JButton("Devoluciones");
        btMenuDev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDevolucionesActionPerformed(evt);
            }
        });
        
        btMenuHistorial = new JButton("Historial");
        btMenuHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHistorialActionPerformed(evt);
            }
        });
        
        tbarMenuPrinc.add(btMenuCatalogos);
        tbarMenuPrinc.add(btMenuSolic);
        tbarMenuPrinc.add(btMenuPres);
        tbarMenuPrinc.add(btMenuDev);
        tbarMenuPrinc.add(btMenuHistorial);
        tbarMenuPrinc.setBounds(10, 10, 1400, 40);
        tbarMenuPrinc.setFloatable(false);
        this.getContentPane().add(tbarMenuPrinc);
        this.setResizable(false);
               
   
        jpMenuCatalogos=menuLib.InterfazLibro();
        add(jpMenuCatalogos);
        jpMenuCatalogos.setVisible(false);

       jpMenuSolic = new JPanel();
       jpMenuSolic=menuSolic.Solicitantes();
       add(jpMenuSolic);
       jpMenuSolic.setVisible(false);
       
       jpMenuPres = new JPanel();       
       jpMenuPres = menuPres.jpPrincPrest();
       add(jpMenuPres);
       jpMenuPres.setVisible(false);       
       
       jpMenuDev = new JPanel();
       jpMenuDev = menuDev.jpPrincDev();
       add(jpMenuDev);
       jpMenuDev.setVisible(false);
       
       jpMenuHistorial = new JPanel();
       jpMenuHistorial = menuHist.jpPrincHist();
       add(jpMenuHistorial);
       jpMenuHistorial.setVisible(false);
       
       labTitulo = new JLabel("David Alfaro Siquieros Gral. No.3");
       labTitulo.setVisible(true);
       labTitulo.setFont(new java.awt.Font("Tahoma", Font.BOLD, 30));
       labTitulo.setBounds(400, 50, 500, 40);
       this.add(labTitulo);

       labSubTitulo = new JLabel("Clave: 02DES0009F");
       labSubTitulo.setVisible(true);
       labSubTitulo.setFont(new java.awt.Font("Tahoma", Font.BOLD, 20));
       labSubTitulo.setBounds(550, 70, 300, 60);
       this.add(labSubTitulo);
       
       labImgPrinc = new JLabel(); 
       labImgPrinc.setIcon(new ImageIcon("C:\\Users\\Mac\\Documents\\NetBeansProjects\\Bibliothek5.1\\build\\classes\\imagenes\\biblioteca.png"));
       labImgPrinc.setBounds(250,200,791,509);
       this.add(labImgPrinc); 
    }
    
    /*Evento Catalogos*/
    public void btMenuCatalogosActionPerformed(ActionEvent evt){       
        labSubTitulo.setVisible(false);
        labTitulo.setVisible(false);
        jpMenuCatalogos.setVisible(true);
    
//        jpSolicitantes.setVisible(false);
        jpMenuPres.setVisible(false);
        jpMenuDev.setVisible(false);
        jpMenuHistorial.setVisible(false);
        
        jpMenuSolic.setVisible(false);
    }
    
    /*Evento Solicitantes*/
    public void btMenuSolicActionPerformed(ActionEvent evt){
        labSubTitulo.setVisible(false);
        labTitulo.setVisible(false);
        jpMenuCatalogos.setVisible(false);
        
        jpMenuSolic.setVisible(true);
        jpMenuPres.setVisible(false);
        jpMenuDev.setVisible(false);
        jpMenuHistorial.setVisible(false);
        jpMenuCatalogos.setVisible(false);
    }
    
    /*Evento Prestamos*/
    public void btPrestamosActionPerformed(ActionEvent evt){     
        labSubTitulo.setVisible(false);
        labTitulo.setVisible(false); 
        
        jpMenuPres.setVisible(true);
        jpMenuDev.setVisible(false);
        jpMenuHistorial.setVisible(false);
        
        jpMenuCatalogos.setVisible(false);
        jpMenuSolic.setVisible(false);
        //btMenuPres.setBackground(Color.BLUE);
        //btMenuPres.setForeground(Color.red);
    }
    
    /*Evento Devoluciones*/
    public void btDevolucionesActionPerformed(ActionEvent evt){    
        labSubTitulo.setVisible(false);
        labTitulo.setVisible(false);        
        jpMenuPres.setVisible(false);
        jpMenuDev.setVisible(true);
        jpMenuHistorial.setVisible(false);
        
        jpMenuCatalogos.setVisible(false);
        jpMenuSolic.setVisible(false);
    }
    
    /*Evento Historial*/
    public void btHistorialActionPerformed(ActionEvent evt){     
        labSubTitulo.setVisible(false);
        labTitulo.setVisible(false);
        jpMenuPres.setVisible(false);
        jpMenuDev.setVisible(false);
        jpMenuHistorial.setVisible(true);
        
        jpMenuCatalogos.setVisible(false);
        jpMenuSolic.setVisible(false);
    }   
        
}


