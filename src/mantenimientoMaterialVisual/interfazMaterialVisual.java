/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mantenimientoMaterialVisual;

import principal.conexion;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
/**
 *
 * @author ACER
 */
public class interfazMaterialVisual {
    
    //JPanel interfaz;
    int filaC;
    
    controlMaterialVisual CMV = new controlMaterialVisual();
    
    JPanel jpRegMV,jpNuevMV,jpExisMV,jpTabRegMV,jpClavGMV;
    Border borderRegMV,borderNuevMV,borderExisMV,borderClavGMV;
    JLabel labTituloMV,labVolumen,labCantidadMV,labAnoMV,labClasificacionMV;
    JTextField txtTituloMV,txtVolumen,txtCantidadMV,txtAnoMV;
    JComboBox cbClasificacionMV;
    JButton btAceptarMV,btLimRegMV;
    JTextArea jtxClaveGMV,jtx;
    
    JTable jtTablaRegMV;
    JScrollPane SCPRegMV,scrolClaveGMV,s;

    JPanel JPConsulMV ;
    Border bordeEjemMV;
    JTable  jtTablaMV;
    JScrollPane SCPMV;
    JButton btModifTitMV,btModifEjemMv;
    JButton btEliminarMV;
    
    JPanel jpBusqMV;
    Border borderEjemMV;
    JTextField txtFilVolumen,txtFilClaTiMV,txtFilTitulMV,txtFilClaEjMV,txtFilClasiMV;
    JTextField txtFilestMV,txtFilANoMV;
    JButton btImprimirMV,btLimTxtClavMV;
    
    boolean respuesta;
  
    
    public JPanel RegistrarMV(){
        //interfaz = new JPanel();
        jtTablaRegMV=null;
        jtTablaRegMV=CMV.generarTablaMV();
          /*MATERIAL VISUAL*/
        jpRegMV = new JPanel();
        jpRegMV.setLayout(null);
//        jpRegMV.setBackground(Color.LIGHT_GRAY);
        jpRegMV.setBounds(200,60,1050,500);
        //interfaz.add(jpRegMV);
        jpRegMV.setVisible(true);
        
        borderRegMV = BorderFactory.createTitledBorder("Registrar Material Visual");
        jpRegMV.setBorder(borderRegMV);
         
         
        jpNuevMV = new JPanel();
        jpNuevMV.setLayout(null);
//        jpNuevMV.setBackground(Color.LIGHT_GRAY);
        jpNuevMV.setBounds(15,300,700,180);
        jpRegMV.add(jpNuevMV);
        jpNuevMV.setVisible(true);
        
        borderNuevMV = BorderFactory.createTitledBorder("Añadir Material Visual");
        jpNuevMV.setBorder(borderNuevMV);
         
        
        labTituloMV = new JLabel("Título:");
        labTituloMV.setFont(new java.awt.Font("Tahoma", Font.BOLD,  13));
        labTituloMV.setBounds(20,20,40,20);
        labTituloMV.setVisible(true);
        jpNuevMV.add(labTituloMV);
        
              
        txtTituloMV = new JTextField();
        txtTituloMV.setBounds(85,20,300,20);
        txtTituloMV.setVisible(true);
        jpNuevMV.add(txtTituloMV);
        
        
        txtTituloMV.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                CMV.filtrarDatosMV(txtTituloMV.getText(),txtVolumen.getText(),txtAnoMV.getText());
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosMV(txtTituloMV.getText(),txtVolumen.getText(),txtAnoMV.getText());
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
               CMV.filtrarDatosMV(txtTituloMV.getText(),txtVolumen.getText(),txtAnoMV.getText());
            }                  
        });
        
         
        labVolumen = new JLabel("Volumen:");
        labVolumen.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        labVolumen.setBounds(20,45,90,20);
        labVolumen.setVisible(true);
        jpNuevMV.add(labVolumen);
        
        txtVolumen= new JTextField();
        txtVolumen.setBounds(85,45,300,20);
        txtVolumen.setVisible(true);
        jpNuevMV.add(txtVolumen);
        
        
        txtVolumen.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
               CMV.filtrarDatosMV(txtTituloMV.getText(),txtVolumen.getText(),txtAnoMV.getText());
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosMV(txtTituloMV.getText(),txtVolumen.getText(),txtAnoMV.getText());
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
              CMV.filtrarDatosMV(txtTituloMV.getText(),txtVolumen.getText(),txtAnoMV.getText());
            }                  
        });
        
        labAnoMV = new JLabel("Año:");
        labAnoMV.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        labAnoMV.setBounds(20,95,50,20);
        labAnoMV.setVisible(true);
        jpNuevMV.add(labAnoMV);
        
        txtAnoMV= new JTextField();
        txtAnoMV.setBounds(85,95,50,20);
        txtAnoMV.setVisible(true);
        jpNuevMV.add(txtAnoMV);
        
       txtAnoMV.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
        unoKeyReleased(evt);
        }
                private void unoKeyReleased(java.awt.event.KeyEvent evt) {
                  String str = txtAnoMV.getText();
                  char[] fuente = str.toCharArray();
                  char[] resultado = new char[fuente.length];
                  int j=0;
                    boolean error=false;
                    for(int i=0; i<fuente.length;i++){
                    if(fuente[i]>='0' && fuente[i]<='9'||fuente[i]=='.')
                    resultado[j++] = fuente[i];
                    else{
                    error=true;
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    }
                    }
                    if(error){
                      txtAnoMV.setText("");
                      txtAnoMV.setText(new String(resultado,0,j));
                    }
              }});
          
        txtAnoMV.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                CMV.filtrarDatosMV(txtTituloMV.getText(),txtVolumen.getText(),txtAnoMV.getText());
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosMV(txtTituloMV.getText(),txtVolumen.getText(),txtAnoMV.getText());
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosMV(txtTituloMV.getText(),txtVolumen.getText(),txtAnoMV.getText());
            }                  
        });
//        
        
        
        
        labCantidadMV = new JLabel("Cantidad:");
        labCantidadMV.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        labCantidadMV.setBounds(260,95,70,20);
        labCantidadMV.setVisible(true);
        jpNuevMV.add(labCantidadMV);
        
        txtCantidadMV= new JTextField();
        txtCantidadMV.setBounds(335,95,50,20);
        txtCantidadMV.setVisible(true);
        txtCantidadMV.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
        unoKeyReleased(evt);
        }
                private void unoKeyReleased(java.awt.event.KeyEvent evt) {
                  String str = txtCantidadMV.getText();
                  char[] fuente = str.toCharArray();
                  char[] resultado = new char[fuente.length];
                  int j=0;
                    boolean error=false;
                    for(int i=0; i<fuente.length;i++){
                    if(fuente[i]>='0' && fuente[i]<='9'||fuente[i]=='.')
                    resultado[j++] = fuente[i];
                    else{
                    error=true;
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    }
                    }
                    if(error){
                      txtCantidadMV.setText("");
                      txtCantidadMV.setText(new String(resultado,0,j));
                    }
              }});
        
        jpNuevMV.add(txtCantidadMV);
        
        
        
        labClasificacionMV = new JLabel("Clasificación:");
        labClasificacionMV.setFont(new java.awt.Font("Tahoma",Font.BOLD, 13));
        labClasificacionMV.setBounds(20,120,100,20);
        labClasificacionMV.setVisible(true);
        jpNuevMV.add(labClasificacionMV);
        
        
        cbClasificacionMV = new JComboBox();
        cbClasificacionMV.setBounds(120,120,150,20);
        cbClasificacionMV.setVisible(true);
        jpNuevMV.add(cbClasificacionMV);
        cbClasificacionMV.addItem("");
        cbClasificacionMV.addItem("Ciencia");
        cbClasificacionMV.addItem("Historia");
        cbClasificacionMV.addItem("Recreativo");
        cbClasificacionMV.addItem("Geografía");
        cbClasificacionMV.addItem("Español");
        cbClasificacionMV.addItem("Matemáticas");
        
        btAceptarMV = new JButton("Añadir");
        btAceptarMV.setBounds(420,20,90,20);
        btAceptarMV.setVisible(true);
        jpNuevMV.add(btAceptarMV);
        
        btAceptarMV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAceptarMV(evt);
            }
        });
        
        btLimRegMV = new JButton("Limpiar");
        btLimRegMV.setBounds(420,60,90,20);
        btLimRegMV.setVisible(true);
        jpNuevMV.add(btLimRegMV);
        
        
        btLimRegMV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimRegMV(evt);  
            }
        });
        
        jpExisMV = new JPanel();
        jpExisMV.setLayout(null);
//        jpExisMV.setBackground(Color.LIGHT_GRAY);
        jpExisMV.setBounds(15,20,700,220);
        jpRegMV.add(jpExisMV);
        jpExisMV.setVisible(true);
        
        borderExisMV = BorderFactory.createTitledBorder("Material Visual en Existencia");
        jpExisMV.setBorder(borderExisMV);



        /*Tabla Material Visual Registrados*/
        
        jpTabRegMV = new JPanel();
        jpTabRegMV.setLayout(null);
        jpTabRegMV.setBounds(5,20,680,150);
//        jpTabRegMV.setBackground(Color.LIGHT_GRAY);
        jpTabRegMV.setVisible(true);
        jpExisMV.add(jpTabRegMV);
        
        jtTablaRegMV=new JTable();
        jtTablaRegMV=CMV.generarTablaMV();
        jtTablaRegMV.setBounds(190,100,680,150);
        SCPRegMV = new JScrollPane(jtTablaRegMV);
        SCPRegMV.setBounds(0,0,680,150);
        jpTabRegMV.add(SCPRegMV);
        
        
        
        
        btModifTitMV= new JButton("Modificar");
        btModifTitMV.setBounds(5,180,100,20);
        btModifTitMV.setVisible(true);
        jpExisMV.add(btModifTitMV);
        
        
         btModifTitMV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModifTitMV(evt);
            }
        });
         
         

        
        jpClavGMV = new JPanel();
        jpClavGMV.setLayout(null);
//        jpClavGMV.setBackground(Color.LIGHT_GRAY);
        jpClavGMV.setBounds(740,20,290,460);
        jpRegMV.add(jpClavGMV);
        jpClavGMV.setVisible(true);
        
        borderClavGMV = BorderFactory.createTitledBorder("Claves Generadas");
        jpClavGMV.setBorder(borderClavGMV);
        
        jtxClaveGMV = new JTextArea();
        jtxClaveGMV.setEditable(false);
        jtxClaveGMV.setLineWrap(false);
        jtxClaveGMV.setBackground(Color.WHITE);
        jtxClaveGMV.setWrapStyleWord(false);
        scrolClaveGMV = new JScrollPane(jtxClaveGMV);
        scrolClaveGMV.setBounds(10,20,270,380);
        //scrolClaveGMV.setVisible(true);
        jpClavGMV.add(scrolClaveGMV);
        
        
        jtx = new JTextArea();
        jtx.setEditable(false);
        jtx.setLineWrap(false);
//        jtx.setBackground(Color.LIGHT_GRAY);
        jtx.setWrapStyleWord(false);
        s = new JScrollPane(jtx);
        s.setBounds(570,250,600,100);
       // s.setVisible(false);
//        jpCatalogo.add(scrolClaveGM);
        
       
        
        
        return jpRegMV;
    }

    public JPanel ConsultaMV(){
        JPConsulMV = new JPanel();
        JPConsulMV.setLayout(null);
//        JPConsulMV.setBackground(Color.LIGHT_GRAY);
        JPConsulMV.setBounds(200,60,1050,500);
        //interfaz.add(JPConsulMV);
        JPConsulMV.setVisible(false);
        
        bordeEjemMV = BorderFactory.createTitledBorder("Ejemplares en Existencia");
        JPConsulMV.setBorder(bordeEjemMV);
        
        
       // SCPMV.setVisible(true);
         

         
        btModifEjemMv = new JButton("Modificar");
        btModifEjemMv.setBounds(15,465,100,20);
        btModifEjemMv.setVisible(true);
        JPConsulMV.add(btModifEjemMv);
        
        btModifEjemMv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModifEjemMv(evt);
            }
        });
        
        btEliminarMV = new JButton("Eliminar");
        btEliminarMV.setBounds(215,465,100,20);
        btEliminarMV.setVisible(true);
        JPConsulMV.add(btEliminarMV);
        
        btEliminarMV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEliminarMV(evt);
            }
        });
        

        jpBusqMV = new JPanel();
        jpBusqMV.setLayout(null);
//        jpBusqMV.setBackground(Color.LIGHT_GRAY);
        jpBusqMV.setBounds(15,50,1020,400);
        JPConsulMV .add(jpBusqMV);
        jpBusqMV.setVisible(true);
        
        borderEjemMV = BorderFactory.createTitledBorder("Búsqueda Avanzada");
        jpBusqMV.setBorder(borderEjemMV);
        
        jtTablaMV =new JTable();
        jtTablaMV=CMV.generarTablaEjemplarMV();
        jtTablaMV.setBounds(190,140,900,300);
        SCPMV = new JScrollPane(jtTablaMV);
        SCPMV.setBounds(5,50,1005,340);
        jpBusqMV.add(SCPMV);
        
        txtFilClaTiMV = new JTextField();
        txtFilClaTiMV.setBounds(5,20,130,20);
        txtFilClaTiMV.setVisible(true);
        jpBusqMV.add(txtFilClaTiMV);
        
        txtFilClaTiMV.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
              
               CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
               CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            
           

        });
        
        
        txtFilClaEjMV = new JTextField();
        txtFilClaEjMV.setBounds(145,20,120,20);
        txtFilClaEjMV.setVisible(true);
        jpBusqMV.add(txtFilClaEjMV);
        
        txtFilClaEjMV.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            
           

        });
        
        txtFilTitulMV = new JTextField();
        txtFilTitulMV.setBounds(290,20,130,20);
        txtFilTitulMV.setVisible(true);
        jpBusqMV.add(txtFilTitulMV);
        
        txtFilTitulMV.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
               CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            
           

        });
        
        
        txtFilVolumen = new JTextField();
        txtFilVolumen.setBounds(430,20,130,20);
        txtFilVolumen.setVisible(true);
        jpBusqMV.add(txtFilVolumen);
        
        txtFilVolumen.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            
           

        });
        
        
        txtFilClasiMV = new JTextField();
        txtFilClasiMV .setBounds(570,20,130,20);
        txtFilClasiMV .setVisible(true);
        jpBusqMV.add(txtFilClasiMV);
        
        txtFilClasiMV.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            
           

        });
        
        txtFilANoMV = new JTextField();
        txtFilANoMV  .setBounds(710,20,130,20);
        txtFilANoMV .setVisible(true);
        jpBusqMV.add(txtFilANoMV);
        
        txtFilANoMV .getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
               CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            
           

        });
        
        txtFilestMV = new JTextField();
        txtFilestMV  .setBounds(850,20,130,20);
        txtFilestMV .setVisible(true);
        jpBusqMV.add(txtFilestMV);
        
        txtFilestMV .getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                CMV.filtrarDatosEjemplar(txtFilClaTiMV.getText(),txtFilClaEjMV.getText(),txtFilTitulMV.getText(),txtFilVolumen.getText(),txtFilClasiMV.getText(),txtFilANoMV.getText(),txtFilestMV.getText());
    
            }
            
           

        });
        
        btImprimirMV = new JButton("Imprimir");
        btImprimirMV.setBounds(10,430,100,20);
        btImprimirMV.setVisible(true);
        jpClavGMV.add(btImprimirMV);
        
        
        btImprimirMV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               btImprimirMV(evt);
            }
        });
        
        
        
         
        btLimTxtClavMV = new JButton("Limpiar");
        btLimTxtClavMV.setBounds(180,430,100,20);
        btLimTxtClavMV.setVisible(true);
        jpClavGMV.add(btLimTxtClavMV);
        
        
        btLimTxtClavMV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimTxtClavMV(evt);
            }
        });
        
        return JPConsulMV;
    }
    
 
    
    /*boton limpiar registros*/
    public void btLimRegMV(ActionEvent evt){
        txtTituloMV.setText("");
        txtVolumen.setText("");
        txtAnoMV.setText("");
        txtCantidadMV.setText("");
        cbClasificacionMV.setSelectedItem(""); 
        
        
        jtTablaMV=null;
        jtTablaMV=CMV.generarTablaEjemplarMV();
                       
        jtTablaRegMV=null;
        jtTablaRegMV=CMV.generarTablaMV();
    }
    /*boton limpiar claves*/
    
    public void btLimTxtClavMV(ActionEvent evt){
        jtxClaveGMV.setText("");
        
        jtTablaMV=null;
        jtTablaMV=CMV.generarTablaEjemplarMV();
                       
        jtTablaRegMV=null;
        jtTablaRegMV=CMV.generarTablaMV();
        
    }

    public void btImprimirMV(ActionEvent evt){
          try {
            //Mensaje de encabezado           
            MessageFormat header = new MessageFormat("Claves Generadas de Material Visual:"+txtTituloMV.getText());
            MessageFormat footer = new MessageFormat("");
            //Imprimir JTextArea
            jtxClaveGMV.print(header, footer, true, null, null, true);
        } catch (PrinterException ex) {
            Logger.getLogger(interfazMaterialVisual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btEliminarMV(ActionEvent evt){
        
        filaC = jtTablaMV.getSelectedRow();
          
          if(filaC<0){
              JOptionPane.showMessageDialog(jpRegMV, "Seleccione Información a Eliminar.");
          }else{
              if(filaC>=0){
                 switch(JOptionPane.showOptionDialog(jpRegMV, "¿Está seguro que desea eliminar este registro?", "Eliminar material visual", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{" SI "," NO"},"0"))
               {
                case 0:
                       CMV.eliminarEjemplar(filaC);
                    jtTablaMV=null;
                    jtTablaMV=CMV.generarTablaEjemplarMV();

                    jtTablaRegMV=null;
                    jtTablaRegMV=CMV.generarTablaMV();
                    
                    txtFilVolumen.setText("");
                    txtFilClaTiMV.setText("");
                    txtFilTitulMV.setText("");
                    txtFilClaEjMV.setText("");
                    txtFilClasiMV.setText("");
                       break;
                case 1:
                       //JOptionPane.showMessageDialog(this, "NO");
                       break;
                default:
                       JOptionPane.showMessageDialog(jpRegMV, "No se eligió ninguna opción");
                       break;
                }
               }

          }
          
          jtTablaMV=null;
       jtTablaMV=CMV.generarTablaEjemplarMV();
                       
        jtTablaRegMV=null;
        jtTablaRegMV=CMV.generarTablaMV();
    }

    public void btModifEjemMv(ActionEvent evt){
            
         filaC = jtTablaMV.getSelectedRow();
          
        if(filaC <0){
              JOptionPane.showMessageDialog(jpRegMV, "Seleccione Información a Modificar.");
        }else{
          if(filaC >=0){
               CMV.mostrarEjem(filaC);
               jtTablaMV=null;
               jtTablaMV=CMV.generarTablaEjemplarMV();

                 jtTablaRegMV=null;
                 jtTablaRegMV=CMV.generarTablaMV();
               
            }
        }
       jtTablaMV=null;
       jtTablaMV=CMV.generarTablaEjemplarMV();
                       
        jtTablaRegMV=null;
        jtTablaRegMV=CMV.generarTablaMV();
        
        txtFilVolumen.setText("");
        txtFilClaTiMV.setText("");
        txtFilTitulMV.setText("");
        txtFilClaEjMV.setText("");
        txtFilClasiMV.setText("");
                
              
    }

    public void btModifTitMV(ActionEvent evt){
         filaC = jtTablaRegMV.getSelectedRow(); 
         
            if(filaC<0){
                JOptionPane.showMessageDialog(JPConsulMV, "Seleccione Información a Modificar.");
                System.out.println("NO SELECCIONO NADA");
            }else{
                if(filaC>=0){
                   System.out.println("SI SELECCIONO "+filaC);
                   CMV.mostrarMV(filaC);
                   jtTablaMV=null;
                    jtTablaMV=CMV.generarTablaEjemplarMV();

                    jtTablaRegMV=null;
                    jtTablaRegMV=CMV.generarTablaMV();
                }
                
            }
    
        jtTablaMV=null;
        jtTablaMV=CMV.generarTablaEjemplarMV();
                       
        jtTablaRegMV=null;
        jtTablaRegMV=CMV.generarTablaMV(); 
        txtFilVolumen.setText("");
        txtFilClaTiMV.setText("");
        txtFilTitulMV.setText("");
        txtFilClaEjMV.setText("");
        txtFilClasiMV.setText("");
            
        
        System.out.println("modificar....");
    }

    public void btAceptarMV(ActionEvent evt){
        String clasificacion=null;
         clasificacion= cbClasificacionMV.getSelectedItem().toString();

         int Cantidad =0;

                  
              respuesta = CMV.validarDatos(txtTituloMV.getText().toUpperCase(), txtVolumen.getText().toUpperCase(),txtAnoMV.getText(), txtCantidadMV.getText(), clasificacion);

              if(respuesta==false){
                  /*validad si la tabla esta vacia*/
                  if ((jtTablaRegMV.getRowCount() == 0 && jtTablaRegMV.getSelectedRow() == -1)) {
                      System.out.println("--Respuesta: Falso"+respuesta);
                      Cantidad = Integer.parseInt(txtCantidadMV.getText());
                      CMV.registrarMV(txtTituloMV.getText().toUpperCase(), txtVolumen.getText().toUpperCase(),txtAnoMV.getText(), Cantidad, clasificacion);

                      /*Se muestra en el panel*/
                        jtxClaveGMV.append("CLAVES GENERADAS:"+"\n");
                        String CadenaD=CMV.mostrarClaveMV();
                        jtxClaveGMV.append(CadenaD+"\n");

                              System.out.println("CLAVE GENersda"+CadenaD+"\n");

                        /*Se Muestra en un JOptionPane*/
                        jtx.append("CLAVES GENERADAS:"+"\n");
                        String Cadena=CMV.mostrarClaveMV();
                        jtx.append(Cadena+"\n");

                        JOptionPane.showMessageDialog(jpRegMV, jtx);

                        txtTituloMV.setText("");
                        txtVolumen.setText("");
                        txtAnoMV.setText("");
                        txtCantidadMV.setText("");
                        cbClasificacionMV.setSelectedItem(""); 

                        /*Tabla de mv Registrados*/

                           jtTablaMV=null;
                           jtTablaMV=CMV.generarTablaEjemplarMV();

                            jtTablaRegMV=null;
                            jtTablaRegMV=CMV.generarTablaMV();
                  }else{
                     JOptionPane.showMessageDialog(jpRegMV, "Ya existe un registro con esas caracteristicas.");
              }
              }
                
    }

    /*Limpiar*/
      
    public void limpiarInterfazMV(){
        System.out.println("entreeeee");

        jtTablaMV=null;
        jtTablaMV=CMV.generarTablaEjemplarMV();

        jtTablaRegMV=null;
        jtTablaRegMV=CMV.generarTablaMV();
        
        txtTituloMV.setText("");
        txtVolumen.setText("");
        txtAnoMV.setText("");
        txtCantidadMV.setText("");
        cbClasificacionMV.setSelectedItem("");
        jtxClaveGMV.setText(" ");
        jtx.setText(" ");
        
        txtFilVolumen.setText(" ");
        txtFilClaTiMV.setText(" ");
        txtFilClaEjMV.setText(" ");
        txtFilClasiMV.setText(" ");
        txtFilestMV.setText(" ");
        txtFilANoMV.setText(" ");
    }
    
}
