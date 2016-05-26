/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mantenimientoLibros;
/*Comentario*/


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import mantenimientoLibros.controlLibro;
import mantenimientoMaterialVisual.controlMaterialVisual;

import mantenimientoMaterialVisual.interfazMaterialVisual;
/**
 *
 * @author ACER
 */
public class interfazLibro {
    
    controlLibro CL = new controlLibro();
    interfazMaterialVisual MV = new interfazMaterialVisual();
    controlMaterialVisual CMV = new controlMaterialVisual();

    JPanel jpCatalogo;
    Border bordeCatal,borderRB,bordeRegL,borderExisL,borderNuevL;
    ButtonGroup groupCatalogos,groupOpciones;
    JPanel jpRB,jpRegL,jpExisL,jpNuevL;
    JRadioButton rbtLibros,rbtMaterial_Visual;
    JToggleButton btRegistrar,btConsultas;
    JLabel labTitulo,labAutor, labEditorial,labAno;
    JTextField txtTitulo,txtAutor,txtEditorial,txtAno;
    JLabel labCantidad,labClasificacion;
    JTextField txtCantidad ;
    JComboBox cbClasificacion;
    JButton btAceptar,btLimRegL,btModifEjemL;
    JPanel JPConsulEjem;
    Border bordeEjemL;
    JTable jtTablaConsul;
    JScrollPane SCPConsultaL;
    JButton btEliminar;
    JPanel jpBusqL;
    JTextField txtFilClaTiL,txtFilClaEjL,txtFilTitulL,txtFilAutorL;
    JTextField txtFilClasiL ,txtFilEditoL ,txtFilAnoL,txtFilEstL;
    JPanel jpTabRegLib,jpClavGL  ;
    JTable jtTablaRegLib;
    JScrollPane SCPRegLib,scrolClaveG,scrolClaveGM;
    Border borderClavGL ;
    JTextArea jtxClaveG ,jtxClaveGM;
    JButton btImprimir,btLimTxtClavL,btModifiTitL;
    
    int NumTupla;
    boolean respuesta;
   
    JPanel jpRegMV,jpConsMV;
    
    
    public JPanel InterfazLibro(){
        
        
        
    /*COMPONENTES DE LIBROS Y MATERIAL VISUAL*/
        /* Componentes de Catalogos.*/
        jpCatalogo = new JPanel();
        jpCatalogo.setLayout(null);
        jpCatalogo.setBounds(10,80,1340,580);
//        jpCatalogo.setBackground(Color.LIGHT_GRAY);
        //add(jpCatalogo);
        jpCatalogo.setVisible(true);
        
        bordeCatal = BorderFactory.createTitledBorder("Catálogos");
        jpCatalogo.setBorder(bordeCatal);
        
        jpRegMV = new JPanel();
        jpRegMV=MV.RegistrarMV();
        jpCatalogo.add(jpRegMV);
        jpRegMV.setVisible(false);
        
        
        jpConsMV = new JPanel();
        jpConsMV=MV.ConsultaMV();
        jpCatalogo.add(jpConsMV);
        jpConsMV.setVisible(false);
        
        /*Grupo de catalogos*/
        groupCatalogos = new ButtonGroup();
        
        
        jpRB = new JPanel();
        jpRB.setLayout(null);
        jpRB.setBounds(10,60,150,500);
//        jpRB.setBackground(Color.LIGHT_GRAY);
        jpRB.setVisible(true);
        jpCatalogo.add(jpRB);
        
        borderRB = BorderFactory.createTitledBorder("Catálogos");
        jpRB.setBorder(borderRB);

        /*Libros*/
        rbtLibros=new JRadioButton("Libros"); 
        rbtLibros.setBounds(10,20,100,20);
//        rbtLibros.setBackground(Color.LIGHT_GRAY);
        rbtLibros.setVisible(true); 
        jpRB.add(rbtLibros);
        groupCatalogos.add(rbtLibros);
        
        rbtLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtLibros(evt);
            }  
        });
       
        /*Material_Visual*/
        rbtMaterial_Visual =new JRadioButton("Material Visual"); 
        rbtMaterial_Visual.setBounds(10,40,120,20);
//        rbtMaterial_Visual.setBackground(Color.LIGHT_GRAY);
        rbtMaterial_Visual.setVisible(true); 
        jpRB.add(rbtMaterial_Visual);
        groupCatalogos.add(rbtMaterial_Visual);
        
        rbtMaterial_Visual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtMaterial_Visual(evt);
            }      
        });
        
        /*GroupOpciones*/
        groupOpciones= new ButtonGroup();
        btRegistrar = new JToggleButton("Registrar");
        btRegistrar.setBounds(200,15,100,30);
        btRegistrar.setVisible(false);
        jpCatalogo.add(btRegistrar);
        
        btRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegistrar(evt);
            }       
        });
        
        btConsultas = new JToggleButton("Consultas");
        btConsultas.setBounds(300,15,100,30);
        btConsultas.setVisible(false);
        jpCatalogo.add(btConsultas);
        
        btConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConsultas(evt);
            }    
        });
        
        groupOpciones.add(btRegistrar);
        groupOpciones.add(btConsultas);
        
        /*Componenetes de Nuevo - Libros y Material Visual*/
        jpRegL = new JPanel();
        jpRegL.setLayout(null);
        //jpRegL.setBackground(Color.LIGHT_GRAY);
        jpRegL.setBounds(200,60,1050,500);
        jpCatalogo.add(jpRegL);
        jpRegL.setVisible(false);
        

        bordeRegL = BorderFactory.createTitledBorder("Registrar Libro");
        jpRegL.setBorder(bordeRegL);
        
        jpExisL = new JPanel();
        jpExisL.setLayout(null);
//        jpExisL.setBackground(Color.LIGHT_GRAY);
        jpExisL.setBounds(15,20,700,220);
        jpRegL.add(jpExisL);
        jpExisL.setVisible(true);
        
        borderExisL = BorderFactory.createTitledBorder("Libros en Existencia");
        jpExisL.setBorder(borderExisL);
        
        
        jpNuevL = new JPanel();
        jpNuevL.setLayout(null);
//        jpNuevL.setBackground(Color.LIGHT_GRAY);
        jpNuevL.setBounds(15,300,700,180);
        jpRegL.add(jpNuevL);
        jpNuevL.setVisible(true);
        
        borderNuevL = BorderFactory.createTitledBorder("Añadir Libro");
        jpNuevL.setBorder(borderNuevL);
        
        labTitulo = new JLabel("Título:");
        labTitulo.setFont(new java.awt.Font("Tahoma", Font.BOLD,  13));
        labTitulo.setBounds(20,20,40,20);
        labTitulo.setVisible(true);
        jpNuevL.add(labTitulo);
             
        txtTitulo= new JTextField();
        txtTitulo.setBounds(85,20,300,20);
        txtTitulo.setVisible(true);
        jpNuevL.add(txtTitulo);
            
        txtTitulo.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                CL.filtrarDatosLibro(txtTitulo.getText(),txtAutor.getText(),txtEditorial.getText(),txtAno.getText());
       
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                CL.filtrarDatosLibro(txtTitulo.getText(),txtAutor.getText(),txtEditorial.getText(),txtAno.getText());
       
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
               CL.filtrarDatosLibro(txtTitulo.getText(),txtAutor.getText(),txtEditorial.getText(),txtAno.getText());
       
            }                  
        });
               
        labAutor = new JLabel("Autor:");
        labAutor.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        labAutor.setBounds(20,45,50,20);
        labAutor.setVisible(true);
        jpNuevL.add(labAutor);
        
        txtAutor= new JTextField();
        txtAutor.setBounds(85,45,300,20);
        txtAutor.setVisible(true);
        jpNuevL.add(txtAutor);
        
        txtAutor.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                CL.filtrarDatosLibro(txtTitulo.getText(),txtAutor.getText(),txtEditorial.getText(),txtAno.getText());
       
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                CL.filtrarDatosLibro(txtTitulo.getText(),txtAutor.getText(),txtEditorial.getText(),txtAno.getText());
       
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
              CL.filtrarDatosLibro(txtTitulo.getText(),txtAutor.getText(),txtEditorial.getText(),txtAno.getText());
       
            }                  
        });
                            
        labEditorial = new JLabel("Editorial:");
        labEditorial.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        labEditorial.setBounds(20,70,80,20);
        labEditorial.setVisible(true);
        jpNuevL.add(labEditorial);
        
        txtEditorial= new JTextField();
        txtEditorial.setBounds(85,70,300,20);
        txtEditorial.setVisible(true);
        jpNuevL.add(txtEditorial);
        
        txtEditorial.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                CL.filtrarDatosLibro(txtTitulo.getText(),txtAutor.getText(),txtEditorial.getText(),txtAno.getText());
       
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
               CL.filtrarDatosLibro(txtTitulo.getText(),txtAutor.getText(),txtEditorial.getText(),txtAno.getText());
       
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                CL.filtrarDatosLibro(txtTitulo.getText(),txtAutor.getText(),txtEditorial.getText(),txtAno.getText());
       
            }                  
        });
        
        labAno = new JLabel("Año:");
        labAno.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        labAno.setBounds(20,95,50,20);
        labAno.setVisible(true);
        jpNuevL.add(labAno);
        
        txtAno= new JTextField();
        txtAno.setBounds(85,95,50,20);
        txtAno.setVisible(true);
        jpNuevL.add(txtAno);
        txtAno.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
        unoKeyReleased(evt);
        }
                private void unoKeyReleased(java.awt.event.KeyEvent evt) {
                  String str = txtAno.getText();
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
                      txtAno.setText("");
                      txtAno.setText(new String(resultado,0,j));
                    }
              }});
           
        txtAno.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {     
                
                CL.filtrarDatosLibro(txtTitulo.getText(),txtAutor.getText(),txtEditorial.getText(),txtAno.getText());
       
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                CL.filtrarDatosLibro(txtTitulo.getText(),txtAutor.getText(),txtEditorial.getText(),txtAno.getText());
       
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                CL.filtrarDatosLibro(txtTitulo.getText(),txtAutor.getText(),txtEditorial.getText(),txtAno.getText());
       
            }                  
        });
        
        labCantidad = new JLabel("Cantidad:");
        labCantidad.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        labCantidad.setBounds(260,95,70,20);
        labCantidad.setVisible(true);
        jpNuevL.add(labCantidad);
        
        txtCantidad= new JTextField();
        txtCantidad.setBounds(335,95,50,20);
        txtCantidad.setVisible(true);
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
        unoKeyReleased(evt);
        }
                private void unoKeyReleased(java.awt.event.KeyEvent evt) {
                  String str = txtCantidad.getText();
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
                      txtCantidad.setText("");
                      txtCantidad.setText(new String(resultado,0,j));
                    }
              }});
        jpNuevL.add(txtCantidad);
        
        labClasificacion = new JLabel("Clasificación:");
        labClasificacion.setFont(new java.awt.Font("Tahoma",Font.BOLD, 13));
        labClasificacion.setBounds(20,120,100,20);
        labClasificacion.setVisible(true);
        jpNuevL.add(labClasificacion);
        
        cbClasificacion = new JComboBox();
        cbClasificacion.setBounds(120,120,150,20);
        cbClasificacion.setVisible(true);
        jpNuevL.add(cbClasificacion);
        cbClasificacion.addItem("");
        cbClasificacion.addItem("Biología");
        cbClasificacion.addItem("Física");
        cbClasificacion.addItem("Química");
        cbClasificacion.addItem("Geografía");
        cbClasificacion.addItem("Español");
        cbClasificacion.addItem("Matemáticas");
        cbClasificacion.addItem("Cuento");
        cbClasificacion.addItem("Novela");
        cbClasificacion.addItem("Historia");
        cbClasificacion.addItem("Biografía");
        cbClasificacion.addItem("Diccionario");
        cbClasificacion.addItem("Inglés");
        cbClasificacion.addItem("Poema");
        cbClasificacion.addItem("Material de Apoyo");
        cbClasificacion.addItem("Arte");
             
        
        btAceptar = new JButton("Añadir");
        btAceptar.setBounds(420,20,90,20);
        btAceptar.setVisible(true);
        jpNuevL.add(btAceptar);
        
        btAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAceptar(evt);
            }
        });
        
        btLimRegL = new JButton("Limpiar");
        btLimRegL.setBounds(420,60,90,20);
        btLimRegL.setVisible(true);
        jpNuevL.add(btLimRegL);     
        
        btLimRegL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { 
                btLimRegLActionPerformed(evt);
            }
        });
          
        /*Tabla de consulta Ejemplares Libros*/
        
        JPConsulEjem = new JPanel();
        JPConsulEjem.setLayout(null);
//        JPConsulEjem.setBackground(Color.LIGHT_GRAY);
        JPConsulEjem.setBounds(200,60,1050,500);
        jpCatalogo.add(JPConsulEjem);
        JPConsulEjem.setVisible(false);
        
        bordeEjemL = BorderFactory.createTitledBorder("Ejemplares en Existencia");
        JPConsulEjem.setBorder(bordeEjemL);
        
        
        
        btModifEjemL = new JButton("Modificar");
        btModifEjemL.setBounds(15,465,100,20);
        btModifEjemL.setVisible(true);
        JPConsulEjem.add(btModifEjemL);
        
        btModifEjemL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModifEjemL(evt);
            }
        });
        
        /*Boton Eliminar*/
        btEliminar = new JButton("Eliminar");
        btEliminar.setBounds(215,465,100,20);
        btEliminar.setVisible(true);
        JPConsulEjem.add(btEliminar);
        
        btEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              btEliminar(evt);
            }
        });
        
        /*FILTROS DE INFORMACION*/
        jpBusqL = new JPanel();
        jpBusqL.setLayout(null);
//        jpBusqL.setBackground(Color.LIGHT_GRAY);
        jpBusqL.setBounds(15,50,1020,400);
        JPConsulEjem .add(jpBusqL);
        jpBusqL.setVisible(true);
        
        bordeEjemL = BorderFactory.createTitledBorder("Búsqueda Avanzada");
        jpBusqL.setBorder(bordeEjemL);
        
        
        jtTablaConsul=new JTable();
        jtTablaConsul=CL.generarTablaEjemplar();
        jtTablaConsul.setBounds(190,140,900,300);
        SCPConsultaL = new JScrollPane(jtTablaConsul);
        SCPConsultaL.setBounds(5,50,1005,340);
        jpBusqL.add(SCPConsultaL);
        //SCPConsultaL.setVisible(true);
        
        txtFilClaTiL = new JTextField();
        txtFilClaTiL.setBounds(5,20,115,20);
        txtFilClaTiL.setVisible(true);
        jpBusqL.add(txtFilClaTiL);
        
        txtFilClaTiL.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
              
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            
           

        });
        
        
        txtFilClaEjL = new JTextField();
        txtFilClaEjL.setBounds(130,20,115,20);
        txtFilClaEjL.setVisible(true);
        jpBusqL.add(txtFilClaEjL);
        
        txtFilClaEjL.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            
           

        });
        
        txtFilTitulL = new JTextField();
        txtFilTitulL.setBounds(255,20,115,20);
        txtFilTitulL.setVisible(true);
        jpBusqL.add(txtFilTitulL);
        
        txtFilTitulL.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            
           

        });
        
        
        txtFilAutorL = new JTextField();
        txtFilAutorL.setBounds(375,20,115,20);
        txtFilAutorL.setVisible(true);
        jpBusqL.add(txtFilAutorL);
        
        txtFilAutorL.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            
           

        });
        
        
        txtFilClasiL = new JTextField();
        txtFilClasiL.setBounds(500,20,115,20);
        txtFilClasiL.setVisible(true);
        jpBusqL.add(txtFilClasiL);
        
        txtFilClasiL.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            
           

        });
        
        
        txtFilEditoL = new JTextField();
        txtFilEditoL.setBounds(620,20,115,20);
        txtFilEditoL.setVisible(true);
        jpBusqL.add(txtFilEditoL);
        
        txtFilEditoL.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            
           

        });
        
        
        
        txtFilAnoL = new JTextField();
        txtFilAnoL.setBounds(750,20,115,20);
        txtFilAnoL.setVisible(true);
        jpBusqL.add(txtFilAnoL);
        
        
        txtFilAnoL.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
        unoKeyReleased(evt);
        }
                private void unoKeyReleased(java.awt.event.KeyEvent evt) {
                  String str = txtFilAnoL.getText();
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
                      txtFilAnoL.setText("");
                      txtFilAnoL.setText(new String(resultado,0,j));
                    }
              }});
        
        txtFilAnoL.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            
           

        });
        
        
        txtFilEstL = new JTextField();
        txtFilEstL.setBounds(870,20,115,20);
        txtFilEstL.setVisible(true);
        jpBusqL.add(txtFilEstL);
        
        txtFilEstL.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                  CL.filtrarDatosEjemplar(txtFilClaTiL.getText(),txtFilClaEjL.getText(),txtFilTitulL.getText(),txtFilAutorL.getText(),txtFilClasiL.getText(),txtFilEditoL.getText(),txtFilAnoL.getText(),txtFilEstL.getText());
    
            }
            
           

        });
             
        /*Tabla Libros Registrados*/
        
        jpTabRegLib = new JPanel();
        jpTabRegLib.setLayout(null);
        jpTabRegLib.setBounds(5,20,680,150);
//        jpTabRegLib.setBackground(Color.LIGHT_GRAY);
        jpTabRegLib.setVisible(true);
        jpExisL.add(jpTabRegLib);
        
        jtTablaRegLib=new JTable();
        jtTablaRegLib=CL.generarTablaLibro();
        jtTablaRegLib.setBounds(190,100,680,150);
        SCPRegLib = new JScrollPane(jtTablaRegLib);
        SCPRegLib.setBounds(0,0,680,150);
        jpTabRegLib.add(SCPRegLib);
//        scrollPaneL.setVisible(true);
        
        
        
        jpClavGL = new JPanel();
        jpClavGL.setLayout(null);
//        jpClavGL.setBackground(Color.LIGHT_GRAY);
        jpClavGL.setBounds(740,20,290,460);
        jpRegL.add(jpClavGL);
        jpClavGL.setVisible(true);
        
        borderClavGL = BorderFactory.createTitledBorder("Claves Generadas");
        jpClavGL.setBorder(borderClavGL);
        
        jtxClaveG = new JTextArea("");
        jtxClaveG.setEditable(false);
        jtxClaveG.setLineWrap(false);
        jtxClaveG.setWrapStyleWord(false);
        scrolClaveG = new JScrollPane(jtxClaveG);
        scrolClaveG.setBounds(10,20,270,380);
        //scrolClaveG.setVisible(true);
        jpClavGL.add(scrolClaveG);
        
        
        jtxClaveGM = new JTextArea("");
        jtxClaveGM.setEditable(false);
        jtxClaveGM.setLineWrap(false);
//        jtxClaveGM.setBackground(Color.LIGHT_GRAY);
        jtxClaveGM.setWrapStyleWord(false);
        scrolClaveGM = new JScrollPane(jtxClaveGM);
        scrolClaveGM.setBounds(570,250,600,100);
        //scrolClaveGM.setVisible(false);
//        jpCatalogo.add(scrolClaveGM);
        
        /*Boton de imprimir*/
        btImprimir = new JButton("Imprimir");
        btImprimir.setBounds(10,430,100,20);
        btImprimir.setVisible(true);
        jpClavGL.add(btImprimir);
        
        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimir(evt);
            }
        });
        
        
        /*Boton para Limpiar TextArea*/
        btLimTxtClavL = new JButton("Limpiar");
        btLimTxtClavL.setBounds(180,430,100,20);
        btLimTxtClavL.setVisible(true);
        jpClavGL.add(btLimTxtClavL);
        
        btLimTxtClavL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimTxtClavL(evt);
            }
        });
        
        /*Boton de Modificar*/
        btModifiTitL= new JButton("Modificar");
        btModifiTitL.setBounds(5,180,100,20);
        btModifiTitL.setVisible(true);
        jpExisL.add(btModifiTitL);
                
        btModifiTitL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModifiTitL(evt);
            }
         });
            
        return  jpCatalogo;
    }
    
    public void btLimRegLActionPerformed(ActionEvent evt){
            txtTitulo.setText("");
            txtAutor.setText(""); 
            txtEditorial.setText("");
            txtAno.setText("");
            txtCantidad.setText("");
            cbClasificacion.setSelectedItem(""); 
    }
    
    public void btModifiTitL(ActionEvent evt){

         NumTupla = jtTablaRegLib.getSelectedRow();
            if(NumTupla<0){
                JOptionPane.showMessageDialog(jpCatalogo, "Seleccione Información a Modificar.");
                System.out.println("NO SELECCIONO NADA");
            }else{
                if(NumTupla>=0){
                   System.out.println("SI SELECCIONO "+NumTupla);
                   CL.mostrarLibro(NumTupla);
                   jtTablaConsul=null;
                   jtTablaConsul=CL.generarTablaEjemplar();
                   
                   jtTablaRegLib=null;
                   jtTablaRegLib=CL.generarTablaLibro();
                }
                
            }
          System.out.println("modificar....");
    }
    
    public void btLimTxtClavL(ActionEvent evt){
        jtxClaveG.setText("");
    }
    
    public void btImprimir(ActionEvent evt){
         try {
            /*Mensaje de encabezado*/          
            MessageFormat header = new MessageFormat("Claves Generadas del Libro:"+txtTitulo.getText());
            MessageFormat footer = new MessageFormat("");
            /*Imprimir JTextArea*/
            jtxClaveG.print(header, footer, true, null, null, true);
            }catch (PrinterException ex) {
                Logger.getLogger(interfazLibro.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public void btEliminar(ActionEvent evt){
        NumTupla = jtTablaConsul.getSelectedRow();
          if(NumTupla<0){
              JOptionPane.showMessageDialog(jpCatalogo, "Seleccione Información a Eliminar.");
          }else{
              if(NumTupla>=0){
                 switch(JOptionPane.showOptionDialog(jpCatalogo, "¿Está seguro que desea eliminar este registro?", "Eliminar Libro", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{" SI "," NO"},"0"))
               {
                case 0:
                       CL.eliminarEjemplar(NumTupla);
                       jtTablaConsul=null;
                       jtTablaConsul=CL.generarTablaEjemplar();
                       txtFilClaTiL.setText("");
                       txtFilClaEjL.setText("");
                       txtFilTitulL.setText("");
                       txtFilAutorL.setText("");
                       txtFilClasiL.setText("");
                       txtFilEditoL.setText("");
                       txtFilAnoL.setText("");
                       txtFilEstL.setText("");
                       break;
                case 1:
                       //JOptionPane.showMessageDialog(this, "NO");
                       break;
                default:
                       JOptionPane.showMessageDialog(jpCatalogo, "No se eligió ninguna opción");
                       break;
                }
               }

          }
    }
    
    public void btModifEjemL(ActionEvent evt){
        NumTupla = jtTablaConsul.getSelectedRow();
        if(NumTupla<0){
              JOptionPane.showMessageDialog(jpCatalogo, "Seleccione Información a Modificar.");
        }else{
          if(NumTupla>=0){
               CL.mostrarDatosEjemplar(NumTupla);
                  jtTablaConsul=null;
                   jtTablaConsul=CL.generarTablaEjemplar();

                   jtTablaRegLib=null;
                   jtTablaRegLib=CL.generarTablaLibro();

                   txtFilClaTiL.setText("");
                   txtFilClaEjL.setText("");
                   txtFilTitulL.setText("");
                   txtFilAutorL.setText("");
                   txtFilClasiL.setText("");
                   txtFilEditoL.setText("");
                   txtFilAnoL.setText("");
                   txtFilEstL.setText("");
               
            }
        }
      
    }
    
    public void btAceptar(ActionEvent evt){
         jtxClaveG.setText(""); 
         jtxClaveGM.setText("");
         String clasificacion = null;
         

         int Cantidad = 0;

          if(rbtLibros.isSelected()==true){/*if si rblibro se selecciono*/
              
              clasificacion = cbClasificacion.getSelectedItem().toString();
                  
                      respuesta = CL.validarDatos(txtTitulo.getText().toUpperCase(), txtAutor.getText().toUpperCase(),txtAno.getText(),txtEditorial.getText().toUpperCase(), txtCantidad.getText(), clasificacion);
                       
                      if(respuesta==false){
                          /*validad si la tabla esta vacia*/
                          if ((jtTablaRegLib.getRowCount() == 0 && jtTablaRegLib.getSelectedRow() == -1)) {
                              System.out.println("--Respuesta: Falso"+respuesta);
                              Cantidad = Integer.parseInt(txtCantidad.getText());
                              CL.registrarLibro(txtTitulo.getText().toUpperCase(), txtAutor.getText().toUpperCase(),txtAno.getText(),txtEditorial.getText().toUpperCase(), Cantidad, clasificacion);

                              /*Se muestra en el panel*/
                              jtxClaveG.append("Claves Generadas:"+"\n");
                              String Cadena=CL.mostrarClaveEjemplarLibro();
                              jtxClaveG.append(Cadena+"\n");

                              /*Se Muestra en un JOptionPane*/
                              jtxClaveGM.append("Claves Generadas:"+"\n");
                              jtxClaveGM.append(Cadena+"\n");
                              JOptionPane.showMessageDialog(jpCatalogo, jtxClaveGM);

                              txtTitulo.setText("");
                              txtAutor.setText("");
                              txtEditorial.setText("");
                              txtAno.setText("");
                              txtCantidad.setText("");
                              cbClasificacion.setSelectedItem("");

                              jtTablaRegLib=null;
                              jtTablaRegLib=CL.generarTablaLibro();
                          }else{
                             JOptionPane.showMessageDialog(jpCatalogo, "Ya existe un registro con esas caracteristicas.");
                      }
                      }else{
                           System.out.println("--Respuesta: verdad"+respuesta);
                      }
            
            
          }/* fin if si rblibro se selecciono*/
          
          
     /*Validacion si se selecciono material visual*/
          if(rbtMaterial_Visual.isSelected()==true){
               System.out.println("ESTOY EN GUARDAR MATERIAL VISUAL");
           }
    }
    
    public void btConsultas(ActionEvent evt){
        
            if(rbtLibros.isSelected()==true){        
            jpRegL.setVisible(false);
 
            JPConsulEjem.setVisible(true);
           
            jtTablaConsul = null;
            jtTablaConsul = CL.generarTablaEjemplar();
            
            jtTablaRegLib = null;
            jtTablaRegLib = CL.generarTablaLibro();
            
            jpCatalogo.remove(jpRegMV);
            jpCatalogo.add(jpRegMV);
            
            jpCatalogo.remove(jpConsMV);
            jpCatalogo.add(jpConsMV);
            
            jpRegMV.setVisible(false);
            jpConsMV.setVisible(false);
            
            
            }
            if(rbtMaterial_Visual.isSelected()==true){
                 jpRegL.setVisible(false);
                 
                 JPConsulEjem.setVisible(false);
                 
                 jpCatalogo.remove(jpRegMV);
                jpCatalogo.add(jpRegMV);

                jpCatalogo.remove(jpConsMV);
                jpCatalogo.add(jpConsMV);
                 
                 jpRegMV.setVisible(false);
                 jpConsMV.setVisible(true);
                 
                   
                 
            }
    }
    
    public void rbtMaterial_Visual(ActionEvent evt){
        btRegistrar.setVisible(true);
        btConsultas.setVisible(true);
        

        if(btRegistrar.isSelected()==true){
            jpRegL.setVisible(false);
            JPConsulEjem.setVisible(false);


            jpRegMV.setVisible(true);
            jpConsMV.setVisible(false);}

        if(btConsultas.isSelected()==true){
            jpRegL.setVisible(false);
            JPConsulEjem.setVisible(false);


            jpRegMV.setVisible(false);
            jpConsMV.setVisible(true);
        }
    }
    
    public void btRegistrar(ActionEvent evt){
       
        if(rbtLibros.isSelected()==true){         
            jpRegL.setVisible(true);

            JPConsulEjem.setVisible(false);
            jtTablaRegLib=null;
            jtTablaRegLib=CL.generarTablaLibro();
       
            
            jpCatalogo.remove(jpRegMV);
            jpCatalogo.add(jpRegMV);
            
            jpCatalogo.remove(jpConsMV);
            jpCatalogo.add(jpConsMV);
            
            jpRegMV.setVisible(false);
            jpConsMV.setVisible(false);
            
            
        }
        if(rbtMaterial_Visual.isSelected()==true){
             jpRegL.setVisible(false);

             JPConsulEjem.setVisible(false);
             
             
             jpCatalogo.remove(jpRegMV);
            jpCatalogo.add(jpRegMV);
            
            jpCatalogo.remove(jpConsMV);
            jpCatalogo.add(jpConsMV);
             
             jpRegMV.setVisible(true);
             jpConsMV.setVisible(false);
             
            
       }
    }
    
    public void rbtLibros(ActionEvent evt){
       
        
        btRegistrar.setVisible(true);
        btConsultas.setVisible(true);

        if(btRegistrar.isSelected()==true){
            
            jpRegL.setVisible(true);
            JPConsulEjem.setVisible(false);

            
            jpCatalogo.remove(jpRegMV);
            jpCatalogo.add(jpRegMV);
            
            jpCatalogo.remove(jpConsMV);
            jpCatalogo.add(jpConsMV);
            
            jpRegMV.setVisible(false);
            jpConsMV.setVisible(false);
        }
        if(btConsultas.isSelected()==true){
            jpRegL.setVisible(false);
            JPConsulEjem.setVisible(true);

            
            jpCatalogo.remove(jpRegMV);
            jpCatalogo.add(jpRegMV);
            
            jpCatalogo.remove(jpConsMV);
            jpCatalogo.add(jpConsMV);
            jpRegMV.setVisible(false);
            jpConsMV.setVisible(false);
        }
    }
}
