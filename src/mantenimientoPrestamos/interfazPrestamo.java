/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mantenimientoPrestamos;

import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
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
public class interfazPrestamo extends JFrame {       
    controlPrestamo cp = new controlPrestamo(this);
    
    JPanel jpPrincPrest;
    
    //PANELES: OPCIONES DEL CATÁLOGO PRESTAMO   
    JPanel jpPresSolic;
    JPanel jpPresElegir;
    JPanel jpPrestamo;
    JPanel jpDetallePrest;    
    
    /*Solicitante a realizar prestamo*/   
    Border borderSolic;
        
    JLabel labPresNoContBus;
    JTextField txtPresNoContBus;
    JButton btPresBuscarSolic;
    
    //alumno   
    JPanel jpTablaPresAlum;
    JTable tbPresAlumno;
    JScrollPane spPresAlumno;
        
    //docente
    JPanel jpTablaPresDoc;
    JTable tbPresDocente;
    JScrollPane spPresDocente;
            
    JLabel labLimiteLibro, labLimiteMatVis,labResLimLibro, labResLimMatVis;
    
    /*Préstamo a elegir*/
    Border borderElegir;
    
    JLabel labPresEjempBus;
    JTextField txtPresEjempBus;
    JButton btPresBuscarEjemp;
    
    
    //Libro
    JPanel jpTablaPresLib;
    JTable tbPresLibro;  
    JScrollPane spPresLibro;
    
    //MaterialVisual    
    JPanel jpTablaPresMaVi;
    JTable tbPresMatVis;
    JScrollPane spPresMatVis;
    
    JButton btAgregar;
    
    //Información del libro o mat vis seleccionado
    int selectLibro=-1, selectMatVis=-1;
    
    /*Detalle préstamo*/
    Border borderPresDetalle;    
    
    //Tabla detalle
    JPanel jpTablaPresDet;
    JTable tbPresDetalle;
    JScrollPane spPresDetalle;
    
    JButton btPrestQuitar;
    
    /*Préstamo*/    
    Border borderPrestamo;
    
    JLabel labFechaLim;
    JLabel labFechaPrest;
    JLabel labFechaSist;    
    
    JCalendar calendar;
    
    JButton btRegPrestamo;
    
    
    public JPanel jpPrincPrest() {  
        /*PRÉSTAMOS*/        
        jpPrincPrest = new JPanel();
        jpPrincPrest.setLayout(null);
        jpPrincPrest.setBounds(10, 50, 1340, 650);
        jpPrincPrest.setVisible(true);
        
        jpPresSolic = new JPanel();
        jpPresSolic.setLayout(null);
        jpPresSolic.setBounds(10, 10, 650, 300);
        jpPresSolic.setVisible(true);
        jpPrincPrest.add(jpPresSolic);
        
        borderSolic = BorderFactory.createTitledBorder("Solicitante");
        jpPresSolic.setBorder(borderSolic);

        jpPresElegir = new JPanel();
        jpPresElegir.setLayout(null);
        jpPresElegir.setBounds(10, 320, 650, 300);        
        jpPresElegir.setVisible(false);
        jpPrincPrest.add(jpPresElegir);
        
        borderElegir = BorderFactory.createTitledBorder("Elegir");
        jpPresElegir.setBorder(borderElegir);

        jpPrestamo = new JPanel();
        jpPrestamo.setLayout(null);
        jpPrestamo.setBounds(680, 230, 650, 200);     
        jpPrestamo.setVisible(false);
        jpPrincPrest.add(jpPrestamo);
        
        borderPrestamo = BorderFactory.createTitledBorder("Préstamo");
        jpPrestamo.setBorder(borderPrestamo);

        jpDetallePrest = new JPanel();
        jpDetallePrest.setLayout(null);
        jpDetallePrest.setBounds(680, 10, 650, 210);     
        jpDetallePrest.setVisible(false);
        jpPrincPrest.add(jpDetallePrest);
        
        borderPresDetalle = BorderFactory.createTitledBorder("Detalle Préstamo");
        jpDetallePrest.setBorder(borderPresDetalle);         
       
        /*Solicitante que se le realizara el préstamo*/
        labPresNoContBus = new JLabel("noControl: ");
        labPresNoContBus.setVisible(true);
        labPresNoContBus.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
        labPresNoContBus.setBounds(10, 20, 100, 50);
        jpPresSolic.add(labPresNoContBus);
        
        txtPresNoContBus = new JTextField();        
        txtPresNoContBus.setBounds(110, 30, 135, 25);
        txtPresNoContBus.setVisible(true);
        jpPresSolic.add(txtPresNoContBus);
        
        btPresBuscarSolic = new JButton("Buscar");
        btPresBuscarSolic.setBounds(250, 30, 80, 25);
        btPresBuscarSolic.setVisible(true);
        jpPresSolic.add(btPresBuscarSolic);        
                
        //Alumno
        jpTablaPresAlum = new JPanel();
        jpTablaPresAlum.setLayout(null);
        jpTablaPresAlum.setBounds(10, 100, 620, 40);
        jpPresSolic.add(jpTablaPresAlum);
        jpTablaPresAlum.setVisible(false);
        
        tbPresAlumno = new JTable() ;               
        tbPresAlumno.setBounds(10, 100, 720, 40);         
        jpTablaPresAlum.add(tbPresAlumno);       
        
        tbPresAlumno = new JTable();       
        tbPresAlumno.setBounds(10, 100, 720, 100);
        spPresAlumno = new JScrollPane(tbPresAlumno);
        spPresAlumno.setBounds(0, 0, 620, 100);
        jpTablaPresAlum.add(spPresAlumno);
        spPresAlumno.setVisible(true);        
        
        //Docente
        jpTablaPresDoc = new JPanel();
        jpTablaPresDoc.setLayout(null);
        jpTablaPresDoc.setBounds(10, 100, 620, 40);
        jpPresSolic.add(jpTablaPresDoc);
        jpTablaPresDoc.setVisible(false);
                
        tbPresDocente = new JTable();       
        tbPresDocente.setBounds(10, 100, 720, 100);
        spPresDocente = new JScrollPane(tbPresDocente);
        spPresDocente.setBounds(0, 0, 620, 100);
        jpTablaPresDoc.add(spPresDocente);
        spPresDocente.setVisible(true);
        
        labLimiteLibro = new JLabel("Límite Libro= ");
        labLimiteLibro.setVisible(false);
        labLimiteLibro.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));        
        labLimiteLibro.setBounds(10, 250, 100, 50);
        jpPresSolic.add(labLimiteLibro);
        
        labLimiteMatVis = new JLabel("Límite Material Visual= ");
        labLimiteMatVis.setVisible(false);
        labLimiteMatVis.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));        
        labLimiteMatVis.setBounds(200, 250, 180, 50);
        jpPresSolic.add(labLimiteMatVis);
        
        labResLimLibro = new JLabel("");
        labResLimLibro.setVisible(false);
        labResLimLibro.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
        labResLimLibro.setForeground(Color.RED);
        labResLimLibro.setBounds(110, 250, 50, 50);
        jpPresSolic.add(labResLimLibro);
        
        labResLimMatVis = new JLabel("");
        labResLimMatVis.setVisible(false);
        labResLimMatVis.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
        labResLimMatVis.setForeground(Color.RED);
        labResLimMatVis.setBounds(360, 250, 50, 50);
        jpPresSolic.add(labResLimMatVis);
        
        //Articulos a préstar
        labPresEjempBus = new JLabel("Ejemplar: ");
        labPresEjempBus.setVisible(true);
        labPresEjempBus.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
        labPresEjempBus.setBounds(10, 20, 100, 50);
        jpPresElegir.add(labPresEjempBus);
        
        txtPresEjempBus = new JTextField();        
        txtPresEjempBus.setBounds(110, 30, 135, 25);
        txtPresEjempBus.setVisible(true);
        jpPresElegir.add(txtPresEjempBus);
        
        btPresBuscarEjemp = new JButton("Buscar");
        btPresBuscarEjemp.setBounds(250, 30, 80, 25);
        btPresBuscarEjemp.setVisible(true);
        jpPresElegir.add(btPresBuscarEjemp);        
        
        jpTablaPresLib = new JPanel();
        jpTablaPresLib.setLayout(null);
        jpTablaPresLib.setBounds(10, 100, 620, 40);
        jpPresElegir.add(jpTablaPresLib);
        jpTablaPresLib.setVisible(false);
                
        tbPresLibro = new JTable();       
        tbPresLibro.setBounds(10, 100, 720, 100);
        spPresLibro = new JScrollPane(tbPresLibro);
        spPresLibro.setBounds(0, 0, 620, 100);
        jpTablaPresLib.add(spPresLibro);
        spPresLibro.setVisible(true);
        
        jpTablaPresMaVi = new JPanel();
        jpTablaPresMaVi.setLayout(null);
        jpTablaPresMaVi.setBounds(10, 100, 620, 40);
        jpPresElegir.add(jpTablaPresMaVi);
        jpTablaPresMaVi.setVisible(false);
                
        tbPresMatVis = new JTable();       
        tbPresMatVis.setBounds(10, 100, 720, 100);
        spPresMatVis = new JScrollPane(tbPresMatVis);
        spPresMatVis.setBounds(0, 0, 620, 100);
        jpTablaPresMaVi.add(spPresMatVis);
        spPresMatVis.setVisible(true);        
        
        //*Elegir préstamo para detalle de préstamo
        btAgregar = new JButton("Agregar");
        btAgregar.setBounds(10, 250, 100, 30);
        btAgregar.setVisible(false);
        jpPresElegir.add(btAgregar);
        
        //Detalle Préstamo
        jpTablaPresDet = new JPanel();
        jpTablaPresDet.setLayout(null);
        jpTablaPresDet.setBounds(20, 30, 600, 100);
        jpDetallePrest.add(jpTablaPresDet);
        jpTablaPresDet.setVisible(true);        
        
        tbPresDetalle = new JTable();
        tbPresDetalle.setBounds(20, 30, 700, 100);
        spPresDetalle = new JScrollPane(tbPresDetalle);
        spPresDetalle.setBounds(0, 0, 600, 100);
        jpTablaPresDet.add(spPresDetalle);
        spPresDetalle.setVisible(true);
        
        btPrestQuitar = new JButton("Quitar");
        btPrestQuitar.setBounds(20, 150, 100, 30);
        btPrestQuitar.setVisible(true);
        jpDetallePrest.add(btPrestQuitar);
        
        /*Datos de Préstamo*/        
        labFechaLim = new JLabel("Seleccione Fecha Límite");
        labFechaLim.setVisible(true);
        labFechaLim.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
        labFechaLim.setBounds(250, 10, 300, 50);
        jpPrestamo.add(labFechaLim);

        labFechaPrest = new JLabel("Fecha de Préstamo");
        labFechaPrest.setVisible(true);
        labFechaPrest.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
        labFechaPrest.setBounds(10, 10, 300, 50);
        jpPrestamo.add(labFechaPrest);
        
        labFechaSist = new JLabel();
        labFechaSist.setVisible(true);
        labFechaSist.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14));
        labFechaSist.setBounds(40, 30, 300, 50);
        jpPrestamo.add(labFechaSist);
               
        calendar =  new JCalendar();
        calendar.setBounds(250, 50, 200, 150);        
        jpPrestamo.add(calendar);
        
        btRegPrestamo = new JButton("Registrar Préstamo");
        btRegPrestamo.setBounds(680, 570, 150, 30);
        btRegPrestamo.setVisible(false);
        jpPrincPrest.add(btRegPrestamo);
        
        btPresBuscarSolic.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //primero tengo que buscar que tipo de solicitante es: acuerdate que agregaremos al noControl una A O D al gafete y yo nomas recorto
                boolean validarSolic = cp.validarTipoSolicP();
                if(validarSolic == true) {
                    boolean validarBusq=cp.buscarPresSolic(txtPresNoContBus.getText());
                    if(validarBusq == false) {
                        JOptionPane.showMessageDialog(null, "noControl inexistente", "ERROR", JOptionPane.ERROR_MESSAGE);                        
                        txtPresNoContBus.setText("");
                    } else {
                        cp.mostrarPresSolic(tbPresAlumno,tbPresDocente);                        
                        txtPresNoContBus.setText("");   
                        
                        cp.validarTipoPres();                 
                    }
                        
                } else {
                    JOptionPane.showMessageDialog(null, "Ingresa una clave del noControl valido", "INGRESA", JOptionPane.ERROR_MESSAGE);
                    txtPresEjempBus.setText("");
                }
            }
        });  
        
        btPresBuscarEjemp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                
                boolean validarEjemp = cp.validarTipoEjempP();
                if(validarEjemp == true) {
                    boolean validarAceptPres = cp.validarAceptPres();                    
                    if(validarAceptPres == false) {
                        txtPresEjempBus.setText("");    
                        jpTablaPresLib.setVisible(false);
                        jpTablaPresMaVi.setVisible(false);
                    } else {
                        boolean valBusqEjemp=cp.buscarEjempPres(txtPresEjempBus.getText());                      
                        if(valBusqEjemp == false) {
                            JOptionPane.showMessageDialog(null, "Clave de ejemplar inexistente", "ERROR", JOptionPane.ERROR_MESSAGE);
                            txtPresEjempBus.setText("");
                            jpTablaPresLib.setVisible(false);
                            jpTablaPresMaVi.setVisible(false);
                            btAgregar.setVisible(false);
                        } else {
                            boolean validarEstado=cp.validarEstadoP(txtPresEjempBus.getText());                               
                            if(validarEstado == true ) {
                                boolean encontrarDetPres = cp.encontrarDetPres();
                                if(encontrarDetPres == false) {
                                    JOptionPane.showMessageDialog(null, "Ejemplar ya solicitado", "ERROR", JOptionPane.ERROR_MESSAGE);
                                } else {                     
                                    cp.mostrarEjempP(tbPresLibro,tbPresMatVis);                             
                                    btAgregar.setVisible(true);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Ejemplar no disponible", "ERROR", JOptionPane.ERROR_MESSAGE);                                                                                           
                                jpTablaPresLib.setVisible(false);
                                jpTablaPresMaVi.setVisible(false);
                                btAgregar.setVisible(false);                                                                
                            }                        
                            txtPresEjempBus.setText("");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ingresa una clave del ejemplar valido", "INGRESA", JOptionPane.ERROR_MESSAGE);
                    txtPresEjempBus.setText("");
                    jpTablaPresLib.setVisible(false);
                    jpTablaPresMaVi.setVisible(false);
                    
                }                            
            }
        }); 
        
        btAgregar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    cp.agregarDetPres(tbPresDetalle);
                } catch (ParseException ex) {
                    Logger.getLogger(interfazPrestamo.class.getName()).log(Level.SEVERE, null, ex);
                }
                boolean validar = cp.validarCumTodLim();
                if(validar == false) {
                    JOptionPane.showMessageDialog(null, "Ya cumple con el limite de préstamo de libros y material visual", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    jpPresElegir.setVisible(false);
                } else {
                    jpPresElegir.setVisible(true);
                }
            }
        });        
        
        btRegPrestamo.addActionListener(new ActionListener() {            
            public void actionPerformed(ActionEvent evt) {                
                String fechaLimite=cp.selecFechaLimite();
                
                cp.regPrestamo(labFechaSist.getText(),fechaLimite);
            }
        });
        
        return jpPrincPrest;
    }    
    
}