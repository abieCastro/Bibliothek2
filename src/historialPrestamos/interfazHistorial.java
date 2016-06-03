/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historialPrestamos;

import com.qt.datapicker.DatePicker;
import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.util.Locale;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.freixas.jcalendar.JCalendarCombo;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Mac
 */
public class interfazHistorial extends JFrame {
    controlHistorial ch = new controlHistorial(this);
    JPanel jpPrincHist;
    
    JLabel labFechaDesde;
    JCalendarCombo selecFechaDesde;    
    
    ButtonGroup grupoRbTipoHist;
    JRadioButton rbHistGeneral;
    JRadioButton rbHistLibros; 
    JRadioButton rbHistMatVis; 
    
    //General   
    JPanel jpTablaHistGral;
    JTable tbHistGeneral;
    JScrollPane spHistGeneral;
    
    //Libros
    JPanel jpTablaHistLib;
    JTable tbHistLibros;
    JScrollPane spHistLibros;
    
    //Materiales Visuales
    JPanel jpTablaHistMatVis;
    JTable tbHistMatVis;
    JScrollPane spHistMatVis;
    
    /*Filtros General*/
    JTextField txtGralNoCont;
    JTextField txtGralGrado;   
    JTextField txtGrlEjemp;
    JTextField txtGralFecPres;   
    JTextField txtGralFecLim;   
    JTextField txtGralFecDev;   
    
    /*Filtros Libros*/
    JTextField txtLibNoCont;
    JTextField txtLibGrado;   
    JTextField txtLibEjemp;
    JTextField txtLibFecPres;   
    JTextField txtLibFecLim;   
    JTextField txtLibFecDev;   
    
    /*Filtros Materiales Visuales*/
    JTextField txtMaViNoCont;
    JTextField txtMaViGrado;   
    JTextField txtMaViEjemp;
    JTextField txtMaViFecPres;   
    JTextField txtMaViFecLim;   
    JTextField txtMaViFecDev;   
    
    public JPanel jpPrincHist() {  
        jpPrincHist = new JPanel();
        jpPrincHist.setLayout(null);
        jpPrincHist.setBounds(10, 50, 1340, 650);        
        jpPrincHist.setVisible(true);
        
        labFechaDesde = new JLabel("Fecha Desde: ");
        labFechaDesde.setVisible(true);
        labFechaDesde.setFont(new java.awt.Font("Tahoma", Font.BOLD, 16));
        labFechaDesde.setBounds(950, 10, 150, 50);
        jpPrincHist.add(labFechaDesde);
        
        selecFechaDesde=new  JCalendarCombo();
        selecFechaDesde.setBounds(1100, 20, 200, 30);
        jpPrincHist.add(selecFechaDesde);
        
        //General
        jpTablaHistGral = new JPanel();
        jpTablaHistGral.setLayout(null);
        jpTablaHistGral.setBounds(20, 170, 1300, 450);
        jpPrincHist.add(jpTablaHistGral);
        jpTablaHistGral.setVisible(false);        
        
        tbHistGeneral = new JTable() ;               
        tbHistGeneral.setBounds(20, 170, 1400, 450);         
        spHistGeneral = new JScrollPane(tbHistGeneral);
        spHistGeneral.setBounds(0, 0, 1300, 450);
        jpTablaHistGral.add(spHistGeneral);
        spHistGeneral.setVisible(true);        
        
        //Libros
        jpTablaHistLib = new JPanel();
        jpTablaHistLib.setLayout(null);
        jpTablaHistLib.setBounds(20, 200, 1300, 450);
        jpPrincHist.add(jpTablaHistLib);
        jpTablaHistLib.setVisible(false);
        
        tbHistLibros = new JTable();
        tbHistLibros.setBounds(20, 200, 1400, 450);
        spHistLibros = new JScrollPane(tbHistLibros);
        spHistLibros.setBounds(0, 0, 1300, 450);
        jpTablaHistLib.add(spHistLibros);
        spHistLibros.setVisible(true);        
        
        //Materiales Visuales
        jpTablaHistMatVis = new JPanel();
        jpTablaHistMatVis.setLayout(null);
        jpTablaHistMatVis.setBounds(20, 200, 1300, 450);
        jpPrincHist.add(jpTablaHistMatVis);
        jpTablaHistMatVis.setVisible(false);
        
        tbHistMatVis = new JTable();
        tbHistMatVis.setBounds(20, 200, 1300, 450);
        spHistMatVis = new JScrollPane(tbHistMatVis);
        spHistMatVis.setBounds(0, 0, 1300, 450);
        jpTablaHistMatVis.add(spHistMatVis);
        spHistMatVis.setVisible(true);        
        
        grupoRbTipoHist = new ButtonGroup();
        rbHistGeneral = new JRadioButton("General");
        rbHistGeneral.setBounds(300, 80, 100, 30);
        rbHistGeneral.setVisible(true);
        jpPrincHist.add(rbHistGeneral);
        grupoRbTipoHist.add(rbHistGeneral);        
        
        rbHistLibros = new JRadioButton("Libros");
        rbHistLibros.setBounds(500, 80, 120, 30);
        rbHistLibros.setVisible(true);   
        jpPrincHist.add(rbHistLibros);
        grupoRbTipoHist.add(rbHistLibros);
        
        rbHistMatVis = new JRadioButton("Materiales Visuales");
        rbHistMatVis.setBounds(700, 80, 140, 30);        
        rbHistMatVis.setVisible(true);   
        jpPrincHist.add(rbHistMatVis);
        grupoRbTipoHist.add(rbHistMatVis);                
        
        rbHistGeneral.addActionListener(ch);
        rbHistLibros.addActionListener(ch);
        rbHistMatVis.addActionListener(ch);
        
        /*Filtros General*/
        
        txtGralNoCont = new JTextField();
        txtGralNoCont.setBounds(20,140,185,20);
        txtGralNoCont.setVisible(false);
        jpPrincHist.add(txtGralNoCont);
        
        txtGralNoCont.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                

                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                

                    }
        });

        txtGralGrado = new JTextField();
        txtGralGrado.setBounds(210,140,180,20);
        txtGralGrado.setVisible(false);
        jpPrincHist.add(txtGralGrado);
        
        txtGralGrado.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                
                        
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                

                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                

                    }
        });
        
        txtGrlEjemp = new JTextField();
        txtGrlEjemp.setBounds(395,140,180,20);
        txtGrlEjemp.setVisible(false);
        jpPrincHist.add(txtGrlEjemp);
        
        txtGrlEjemp.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                
                        
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                

                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                

                    }
        });
        
        txtGralFecPres = new JTextField();
        txtGralFecPres.setBounds(765,140,180,20);
        txtGralFecPres.setVisible(false);
        jpPrincHist.add(txtGralFecPres);
        
        txtGralFecPres.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                
                        
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                

                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                

                    }
        });
        
        txtGralFecLim = new JTextField();
        txtGralFecLim.setBounds(950,140,180,20);
        txtGralFecLim.setVisible(false);
        jpPrincHist.add(txtGralFecLim);
        
        txtGralFecLim.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                
                        
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                

                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                

                    }
        });
        
        txtGralFecDev = new JTextField();
        txtGralFecDev.setBounds(1135,140,185,20);
        txtGralFecDev.setVisible(false);
        jpPrincHist.add(txtGralFecDev);
        
        txtGralFecDev.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                
                        
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                

                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistGral(txtGralNoCont.getText(), txtGralGrado.getText(), txtGrlEjemp.getText(), txtGralFecPres.getText(), txtGralFecLim.getText(), txtGralFecDev.getText(), tbHistGeneral);                                                

                    }
        });
        
        /*Filtros Libros*/
        
        txtLibNoCont = new JTextField();
        txtLibNoCont.setBounds(20,140,185,20);
        txtLibNoCont.setVisible(false);
        jpPrincHist.add(txtLibNoCont);
        
        txtLibNoCont.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
        });

        txtLibGrado = new JTextField();
        txtLibGrado.setBounds(210,140,180,20);
        txtLibGrado.setVisible(false);
        jpPrincHist.add(txtLibGrado);
        
        txtLibGrado.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                                        
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
        });
        
        txtLibEjemp = new JTextField();
        txtLibEjemp.setBounds(395,140,180,20);
        txtLibEjemp.setVisible(false);
        jpPrincHist.add(txtLibEjemp);
        
        txtLibEjemp.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                                        
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
        });
        
        txtLibFecPres = new JTextField();
        txtLibFecPres.setBounds(765,140,180,20);
        txtLibFecPres.setVisible(false);
        jpPrincHist.add(txtLibFecPres);
        
        txtLibFecPres.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
        });
        
        txtLibFecLim = new JTextField();
        txtLibFecLim.setBounds(950,140,180,20);
        txtLibFecLim.setVisible(false);
        jpPrincHist.add(txtLibFecLim);
        
        txtLibFecLim.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                                        
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
        });
        
        txtLibFecDev = new JTextField();
        txtLibFecDev.setBounds(1135,140,185,20);
        txtLibFecDev.setVisible(false);
        jpPrincHist.add(txtLibFecDev);
        
        txtLibFecDev.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistLib(txtLibNoCont.getText(), txtLibGrado.getText(), txtLibEjemp.getText(), txtLibFecPres.getText(), txtLibFecLim.getText(), txtLibFecDev.getText(), tbHistLibros);                                                
                    }
        });
        
        /*Filtros Materiales Visuales*/
        
        txtMaViNoCont = new JTextField();
        txtMaViNoCont.setBounds(20,140,185,20);
        txtMaViNoCont.setVisible(false);
        jpPrincHist.add(txtMaViNoCont);
        
        txtMaViNoCont.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                
                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                
                    }
        });

        txtMaViGrado = new JTextField();
        txtMaViGrado.setBounds(210,140,180,20);
        txtMaViGrado.setVisible(false);
        jpPrincHist.add(txtMaViGrado);
        
        txtMaViGrado.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                                        
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                
                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                
                    }
        });
        
        txtMaViEjemp = new JTextField();
        txtMaViEjemp.setBounds(395,140,180,20);
        txtMaViEjemp.setVisible(false);
        jpPrincHist.add(txtMaViEjemp);
        
        txtMaViEjemp.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {                          
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {                        
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                
                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {                        
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                
                    }
        });
        
        txtMaViFecPres = new JTextField();
        txtMaViFecPres.setBounds(765,140,180,20);
        txtMaViFecPres.setVisible(false);
        jpPrincHist.add(txtMaViFecPres);
        
        txtMaViFecPres.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                                                           
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                

                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                
                    }
        });
        
        txtMaViFecLim = new JTextField();
        txtMaViFecLim.setBounds(950,140,180,20);
        txtMaViFecLim.setVisible(false);
        jpPrincHist.add(txtMaViFecLim);
        
        txtMaViFecLim.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                                        
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                
                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                
                    }
        });
        
        txtMaViFecDev = new JTextField();
        txtMaViFecDev.setBounds(1135,140,185,20);
        txtMaViFecDev.setVisible(false);
        jpPrincHist.add(txtMaViFecDev);
        
        txtMaViFecDev.getDocument().addDocumentListener(
                new javax.swing.event.DocumentListener() {
                    public void insertUpdate(javax.swing.event.DocumentEvent evt) {  
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                
                    }
                    public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                
                    }
                    public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        ch.filtrarHistMaVi(txtMaViNoCont.getText(), txtMaViGrado.getText(), txtMaViEjemp.getText(), txtMaViFecPres.getText(), txtMaViFecLim.getText(), txtMaViFecDev.getText(), tbHistMatVis);                                                
                    }
        });
        
        return jpPrincHist;
    }
    
    public JPanel getPrincHist() {
        return jpPrincHist;
    }
    public void limpiarMenuHist() {
        ch.limpiarTablas();
        jpTablaHistGral.setVisible(false);
        jpTablaHistLib.setVisible(false);
        jpTablaHistMatVis.setVisible(false);
        grupoRbTipoHist.clearSelection(); 
        txtGralNoCont.setVisible(false);
        txtGralGrado.setVisible(false);
        txtGrlEjemp.setVisible(false);
        txtGralFecPres.setVisible(false);
        txtGralFecLim.setVisible(false);
        txtGralFecDev.setVisible(false);
        txtLibNoCont.setVisible(false);
        txtLibGrado.setVisible(false);
        txtLibEjemp.setVisible(false);
        txtLibFecPres.setVisible(false);
        txtLibFecLim.setVisible(false);
        txtLibFecDev.setVisible(false);
        txtMaViNoCont.setVisible(false);
        txtMaViGrado.setVisible(false);
        txtMaViEjemp.setVisible(false);
        txtMaViFecPres.setVisible(false);
        txtMaViFecLim.setVisible(false);
        txtMaViFecDev.setVisible(false);
    }
    
}
