/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mantenimientoSolicitantes;

import principal.conexion;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;


/**
 *
 * @author ACER
 */
public class interfazSolicitante {
    
     controlSolicitante cs = new controlSolicitante();
        
      JPanel jpCatalogo;
      JPanel jpAlumnos;
      Border bordeCatal;
      ButtonGroup groupCatalogo,groupOpciones;
      JPanel jpRB,jpRegA,jpRegD;
      Border borderRB,bordeRegA,bordeRegD;
      JRadioButton rbtAlumnos,rbtDocentes;
      JToggleButton btRegistrar,btConsultas;
      JLabel labControl,labNombre,labPaterno,labMaterno,labLada,labCelular,labTelefono;
      JTextField txtControl,txtNombre,txtPaterno,txtMaterno,txtLadaT,txtLadaC,txtCelular,txtTelefono;
      JLabel labGrado,labGrupo;
      JComboBox cbGrado,cbGrupo;
      JButton btRegistrarA;
      JPanel jpConsultasA, jpBusqA,jpBusqD,jpConsultasD;
      Border bordeConsulA,bordeBusqA,bordeBusqD,bordeConsulD;
      JTextField txtControlD,txtNombreD,txtPaternoD,txtMaternoD;
      JButton btRegistrarD;
      JTable jtAlumno, jtDocente;
      JScrollPane jsAlumno, jsDocente;
      
      String grupo = "";
      String grado = "";
      String telefono = "";
      String celular = "";
      boolean respuesta;
      int NumTupla;
      
      /*Filtros Alumnos*/
      JTextField txtFNoControlA;
      JTextField txtFNombreA;
      JTextField txtFPaternoA;
      JTextField txtFMaternoA;
      JTextField txtFGrupo;
      JTextField txtFGrado;
      /*Botones alumnos*/
      JButton btModificarA;
      JButton btEliminarA;
      
      /*Filtros Docentes*/
      JTextField txtFNoControlD;
      JTextField txtFNombreD;
      JTextField txtFPaternoD;
      JTextField txtFMaternoD;

      /*Botones alumnos*/
      JButton btModificarD;
      JButton btEliminarD;
      
  
      public JPanel Solicitantes(){

            jpCatalogo = new JPanel();
            jpCatalogo.setLayout(null);
            jpCatalogo.setBounds(10,80,1255,580);
            jpCatalogo.setBackground(Color.LIGHT_GRAY);
            jpCatalogo.setVisible(true);

            bordeCatal = BorderFactory.createTitledBorder("Solicitantes");
            jpCatalogo.setBorder(bordeCatal);


            jpRB = new JPanel();
            jpRB.setLayout(null);
            jpRB.setBounds(10,60,150,500);
            jpRB.setBackground(Color.LIGHT_GRAY);
            jpRB.setVisible(true);
            jpCatalogo.add(jpRB);

            borderRB = BorderFactory.createTitledBorder("Ocupación");
            jpRB.setBorder(borderRB);

             ///Grupo de catalogos
            groupCatalogo = new ButtonGroup();

            rbtAlumnos=new JRadioButton("Alumnos"); 
            rbtAlumnos.setBounds(10,20,100,20);
            rbtAlumnos.setBackground(Color.LIGHT_GRAY);
            rbtAlumnos.setVisible(true); 
            jpRB.add(rbtAlumnos);
            groupCatalogo.add(rbtAlumnos);
            rbtAlumnos.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    rbtAlumnos(evt);
                }

            });


            rbtDocentes =new JRadioButton("Docentes"); 
            rbtDocentes.setBounds(10,40,120,20);
            rbtDocentes.setBackground(Color.LIGHT_GRAY);
            rbtDocentes.setVisible(true); 
            jpRB.add(rbtDocentes);
            groupCatalogo.add(rbtDocentes);

            rbtDocentes.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                   rbtDocentes(evt);
                }


            });

            groupOpciones= new ButtonGroup();
            btRegistrar = new JToggleButton("Registrar");
            btRegistrar.setBounds(200,15,100,30);
            btRegistrar.setVisible(false);
            jpCatalogo.add(btRegistrar);

             btRegistrar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    if(rbtDocentes.isSelected()==true){
                    jpRegA.setVisible(false);
                    jpConsultasA.setVisible(false);

                    jpRegD.setVisible(true);
                    jpConsultasD.setVisible(false);}

                    if(rbtAlumnos.isSelected()==true){
                    jpRegA.setVisible(true);
                    jpConsultasA.setVisible(false);

                    jpRegD.setVisible(false);
                    jpConsultasD.setVisible(false);}


                }});


            btConsultas = new JToggleButton("Consultas");
            btConsultas.setBounds(300,15,100,30);
            btConsultas.setVisible(false);
            jpCatalogo.add(btConsultas);
            btConsultas.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    btConsultas(evt);

                }});

            groupOpciones.add(btRegistrar);
            groupOpciones.add(btConsultas);


            jpRegA = new JPanel();
            jpRegA.setLayout(null);
            jpRegA.setBackground(Color.LIGHT_GRAY);
            jpRegA.setBounds(200,60,1050,500);
            jpCatalogo.add(jpRegA);
            jpRegA.setVisible(false);

            /*Registrar Alumnos Componentes*/
            bordeRegA = BorderFactory.createTitledBorder("Registrar Alumno");
            jpRegA.setBorder(bordeRegA);


            labControl = new JLabel("No.Control:");
            labControl.setBounds(15,35,100,20);
            labControl.setVisible(true);
            jpRegA.add(labControl);

            txtControl = new JTextField();
            txtControl.setBounds(90,35,100,20);
            txtControl.setVisible(true);
            jpRegA.add(txtControl);

            labPaterno = new JLabel("Apellido Paterno");
            labPaterno.setBounds(15,70,100,20);
            labPaterno.setVisible(true);
            jpRegA.add(labPaterno);

            txtPaterno = new JTextField();
            txtPaterno.setBounds(15,90,100,20);
            txtPaterno.setVisible(true);
            jpRegA.add(txtPaterno);

            labMaterno = new JLabel("Apellido Materno");
            labMaterno.setBounds(125,70,100,20);
            labMaterno.setVisible(true);
            jpRegA.add(labMaterno);

            txtMaterno = new JTextField();
            txtMaterno.setBounds(125,90,100,20);
            txtMaterno.setVisible(true);
            jpRegA.add(txtMaterno);

            labNombre = new JLabel("Nombre");
            labNombre.setBounds(240,70,100,20);
            labNombre.setVisible(true);
            jpRegA.add(labNombre);

            txtNombre = new JTextField();
            txtNombre.setBounds(240,90,100,20);
            txtNombre.setVisible(true);
            jpRegA.add(txtNombre);

           labLada = new JLabel("Lada");
           labLada.setBounds(15,130,50,20);
           labLada.setVisible(true);
           jpRegA.add(labLada);

           txtLadaC = new JTextField();
           txtLadaC.setBounds(15,150,50,20);
           txtLadaC.setVisible(true);
           jpRegA.add(txtLadaC);

           labCelular = new JLabel("Celular");
           labCelular.setBounds(70,130,100,20);
           labCelular.setVisible(true);
           jpRegA.add(labCelular);

           txtCelular = new JTextField();
           txtCelular.setBounds(70,150,100,20);
           txtCelular.setVisible(true);
           jpRegA.add(txtCelular);

           labLada = new JLabel("Lada");
           labLada.setBounds(200,130,50,20);
           labLada.setVisible(true);
           jpRegA.add(labLada);

           txtLadaT = new JTextField();
           txtLadaT.setBounds(200,150,50,20);
           txtLadaT.setVisible(true);
           jpRegA.add(txtLadaT);

           labTelefono = new JLabel("Telefono");
           labTelefono.setBounds(260,130,100,20);
           labTelefono.setVisible(true);
           jpRegA.add(labTelefono);

           txtTelefono = new JTextField();
           txtTelefono.setBounds(260,150,100,20);
           txtTelefono.setVisible(true);
           jpRegA.add(txtTelefono);

           labGrado = new JLabel("Grado");
           labGrado.setBounds(15,200,50,20);
           labGrado.setVisible(true);
           jpRegA.add(labGrado);

           cbGrado = new JComboBox();
           cbGrado.setBounds(60,200,50,20);
           cbGrado.setVisible(true);
           cbGrado.addItem("");
           cbGrado.addItem("1");
           cbGrado.addItem("2");
           cbGrado.addItem("3");
           jpRegA.add(cbGrado);

          labGrupo = new JLabel("Grupo");
          labGrupo.setBounds(140,200,50,20);
          labGrupo.setVisible(true);
          jpRegA.add(labGrupo);

          cbGrupo = new JComboBox();
          cbGrupo.setBounds(180,200,50,20);
          cbGrupo.setVisible(true);
          cbGrupo.addItem("");
          cbGrupo.addItem("A");
          cbGrupo.addItem("B");
          cbGrupo.addItem("C");
          cbGrupo.addItem("D");
          cbGrupo.addItem("E");
          cbGrupo.addItem("F");
          jpRegA.add(cbGrupo);
          /*Boton registrar Alumnos*/
          btRegistrarA = new JButton("Registrar");
          btRegistrarA.setBounds(15,300,100,20);
          btRegistrarA.setVisible(true);
          jpRegA.add(btRegistrarA);

          btRegistrarA.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    btRegistrarA(evt);
                }});


            jpConsultasA = new JPanel();
            jpConsultasA.setLayout(null);
            jpConsultasA.setBackground(Color.LIGHT_GRAY);
            jpConsultasA.setBounds(200,60,1050,500);
            jpCatalogo.add(jpConsultasA);
            jpConsultasA.setVisible(false);

            bordeConsulA = BorderFactory.createTitledBorder("Alumnos Registrados");
            jpConsultasA.setBorder(bordeConsulA);

            btModificarA = new JButton("Modificar");
            btModificarA.setBounds(15,465,100,20);
            btModificarA.setVisible(true);
            jpConsultasA.add(btModificarA);
            
            btModificarA.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btModificarA(evt);
                }});

            btEliminarA = new JButton("Eliminar");
            btEliminarA.setBounds(215,465,100,20);
            btEliminarA.setVisible(true);
            jpConsultasA.add(btEliminarA);
            btEliminarA.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btEliminarA(evt);
                }});
            
            /*DENTRO DE ESTE PANEL VA LA TABLA DE CONSULTAS Y LOS FILTROS PARA
            ALUMNOS*/
            jpBusqA = new JPanel();
            jpBusqA.setLayout(null);
            jpBusqA.setBackground(Color.LIGHT_GRAY);
            jpBusqA.setBounds(15,50,1020,400);
            jpConsultasA.add(jpBusqA);
            jpBusqA.setVisible(true);

            bordeBusqA= BorderFactory.createTitledBorder("Búsqueda Avanzada");
            jpBusqA.setBorder(bordeBusqA);
            

            
            txtFNoControlA = new JTextField();
            txtFNoControlA.setBounds(5,20,80,20);
            txtFNoControlA.setVisible(true);
            jpBusqA.add(txtFNoControlA);
            
            txtFNoControlA.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {

                public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
                public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
                public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
            });
            
            txtFNombreA = new JTextField();
            txtFNombreA.setBounds(90,20,80,20);
            txtFNombreA.setVisible(true);
            jpBusqA.add(txtFNombreA);
            
            txtFNombreA.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {

                public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
                public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
                public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
            });
            
            txtFPaternoA = new JTextField();
            txtFPaternoA.setBounds(175,20,80,20);
            txtFPaternoA.setVisible(true);
            jpBusqA.add(txtFPaternoA);
            
            txtFPaternoA.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {

                public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
                public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
                public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
            });
            
            txtFMaternoA = new JTextField();
            txtFMaternoA.setBounds(260,20,75,20);
            txtFMaternoA.setVisible(true);
            jpBusqA.add(txtFMaternoA);
            
            txtFMaternoA.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {

                public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
                public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
                public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
            });
            
            txtFGrupo = new JTextField();
            txtFGrupo.setBounds(340,20,80,20);
            txtFGrupo.setVisible(true);
            jpBusqA.add(txtFGrupo);
            txtFGrupo.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {

                public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
                public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
                public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
            });
            
            txtFGrupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
            unoKeyReleased(evt);
            }
                    private void unoKeyReleased(java.awt.event.KeyEvent evt) {
                      String str = txtFGrupo.getText();
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
                          txtFGrupo.setText("");
                          txtFGrupo.setText(new String(resultado,0,j));
                        }
                  }});
            
            txtFGrado = new JTextField();
            txtFGrado.setBounds(425,20,80,20);
            txtFGrado.setVisible(true);
            jpBusqA.add(txtFGrado);
            txtFGrado.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {

                public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
                public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
                public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                      cs.filtrarDatosAlumno(txtFNoControlA.getText(),txtFNombreA.getText(),txtFPaternoA.getText(),txtFMaternoA.getText(),txtFGrupo.getText(),txtFGrado.getText());

                }
            });
            
            
            jtAlumno = new JTable();
            jtAlumno= cs.generarTablaAlumnos();
            
            jsAlumno = new JScrollPane(jtAlumno);
            jsAlumno.setBounds(5,50,1005,340);
            jsAlumno.setVisible(true);
            jpBusqA.add(jsAlumno);



            /*COMPONENTES PARA DOCENTES*/

            jpRegD = new JPanel();
            jpRegD.setLayout(null);
            jpRegD.setBackground(Color.LIGHT_GRAY);
            jpRegD.setBounds(200,60,1050,500);
            jpCatalogo.add(jpRegD);
            jpRegD.setVisible(false);

            bordeRegD = BorderFactory.createTitledBorder("Registrar Docente");
            jpRegD.setBorder(bordeRegD);

            labControl = new JLabel("No.Control:");
            labControl.setBounds(15,35,100,20);
            labControl.setVisible(true);
            jpRegD.add(labControl);

            txtControlD = new JTextField();
            txtControlD.setBounds(90,35,100,20);
            txtControlD.setVisible(true);
            jpRegD.add(txtControlD);

            labPaterno = new JLabel("Apellido Paterno");
            labPaterno.setBounds(15,70,100,20);
            labPaterno.setVisible(true);
            jpRegD.add(labPaterno);

            txtPaternoD = new JTextField();
            txtPaternoD.setBounds(15,90,100,20);
            txtPaternoD.setVisible(true);
            jpRegD.add(txtPaternoD);

            labMaterno = new JLabel("Apellido Materno");
            labMaterno.setBounds(125,70,100,20);
            labMaterno.setVisible(true);
            jpRegD.add(labMaterno);

            txtMaternoD = new JTextField();
            txtMaternoD.setBounds(125,90,100,20);
            txtMaternoD.setVisible(true);
            jpRegD.add(txtMaternoD);

            labNombre = new JLabel("Nombre");
            labNombre.setBounds(240,70,100,20);
            labNombre.setVisible(true);
            jpRegD.add(labNombre);

            txtNombreD = new JTextField();
            txtNombreD.setBounds(240,90,100,20);
            txtNombreD.setVisible(true);
            jpRegD.add(txtNombreD);


          btRegistrarD = new JButton("Registrar");
          btRegistrarD.setBounds(15,120,100,20);
          btRegistrarD.setVisible(true);
          jpRegD.add(btRegistrarD);

          btRegistrarD.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btRegistrarD(evt);
                }});





            /*DENTRO DE ESTE PANEL VA LA TABLA DE CONSULTAS Y LOS FILTROS PARA
            DOCENTES*/
            
            jpConsultasD = new JPanel();
            jpConsultasD.setLayout(null);
            jpConsultasD.setBackground(Color.LIGHT_GRAY);
            jpConsultasD.setBounds(200,60,1050,500);
            jpCatalogo.add(jpConsultasD);
            jpConsultasD.setVisible(false);

            bordeConsulD = BorderFactory.createTitledBorder("Docentes Registrados");
            jpConsultasD.setBorder(bordeConsulD);
          
          
          
            jpBusqD = new JPanel();
            jpBusqD.setLayout(null);
            jpBusqD.setBackground(Color.LIGHT_GRAY);
            jpBusqD.setBounds(15,50,1020,400);
            jpConsultasD.add(jpBusqD);
            jpBusqD.setVisible(true);
            
            bordeBusqD= BorderFactory.createTitledBorder("Búsqueda Avanzada");
            jpBusqD.setBorder(bordeBusqD);
            
            jtDocente = new JTable();
            jtDocente= cs.generarTablaDocente();
            
            jsDocente = new JScrollPane(jtDocente);
            jsDocente.setBounds(5,50,1005,340);
            jsDocente.setVisible(true);
            jpBusqD.add(jsDocente);

            txtFNoControlD = new JTextField();
            txtFNoControlD.setBounds(5,20,120,20);
            txtFNoControlD.setVisible(true);
            jpBusqD.add(txtFNoControlD);
            
            txtFNoControlD.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {

                public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                      cs.filtrarDatosDocente(txtFNoControlD.getText(),txtFNombreD.getText(),txtFPaternoD.getText(),txtFMaternoD.getText());

                }
                public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        cs.filtrarDatosDocente(txtFNoControlD.getText(),txtFNombreD.getText(),txtFPaternoD.getText(),txtFMaternoD.getText());

                }
                public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        cs.filtrarDatosDocente(txtFNoControlD.getText(),txtFNombreD.getText(),txtFPaternoD.getText(),txtFMaternoD.getText());

                }
            });
            
            txtFNombreD = new JTextField();
            txtFNombreD.setBounds(130,20,120,20);
            txtFNombreD.setVisible(true);
            jpBusqD.add(txtFNombreD);
            
            txtFNombreD.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {

                public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                        cs.filtrarDatosDocente(txtFNoControlD.getText(),txtFNombreD.getText(),txtFPaternoD.getText(),txtFMaternoD.getText());

                }
                public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        cs.filtrarDatosDocente(txtFNoControlD.getText(),txtFNombreD.getText(),txtFPaternoD.getText(),txtFMaternoD.getText());

                }
                public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        cs.filtrarDatosDocente(txtFNoControlD.getText(),txtFNombreD.getText(),txtFPaternoD.getText(),txtFMaternoD.getText());

                }
            });

            txtFPaternoD = new JTextField();
            txtFPaternoD.setBounds(255,20,120,20);
            txtFPaternoD.setVisible(true);
            jpBusqD.add(txtFPaternoD);
            
            txtFPaternoD.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {

                public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                        cs.filtrarDatosDocente(txtFNoControlD.getText(),txtFNombreD.getText(),txtFPaternoD.getText(),txtFMaternoD.getText());

                }
                public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        cs.filtrarDatosDocente(txtFNoControlD.getText(),txtFNombreD.getText(),txtFPaternoD.getText(),txtFMaternoD.getText());

                }
                public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        cs.filtrarDatosDocente(txtFNoControlD.getText(),txtFNombreD.getText(),txtFPaternoD.getText(),txtFMaternoD.getText());

                }
            });
            
            txtFMaternoD = new JTextField();
            txtFMaternoD.setBounds(380,20,120,20);
            txtFMaternoD.setVisible(true);
            jpBusqD.add(txtFMaternoD);
            
            txtFMaternoD.getDocument().addDocumentListener(
            new javax.swing.event.DocumentListener() {

                public void insertUpdate(javax.swing.event.DocumentEvent evt) {           
                        cs.filtrarDatosDocente(txtFNoControlD.getText(),txtFNombreD.getText(),txtFPaternoD.getText(),txtFMaternoD.getText());

                }
                public void removeUpdate(javax.swing.event.DocumentEvent evt) {
                        cs.filtrarDatosDocente(txtFNoControlD.getText(),txtFNombreD.getText(),txtFPaternoD.getText(),txtFMaternoD.getText());

                }
                public void changedUpdate(javax.swing.event.DocumentEvent evt) {
                        cs.filtrarDatosDocente(txtFNoControlD.getText(),txtFNombreD.getText(),txtFPaternoD.getText(),txtFMaternoD.getText());

                }
            });
            
            
            
            btModificarD = new JButton("Modificar");
            btModificarD.setBounds(15,465,100,20);
            btModificarD.setVisible(true);
            jpConsultasD.add(btModificarD);
            
            btModificarD.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btModificarD(evt);
                }});

            btEliminarD = new JButton("Eliminar");
            btEliminarD.setBounds(215,465,100,20);
            btEliminarD.setVisible(true);
            jpConsultasD.add(btEliminarD);
            btEliminarD.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btEliminarD(evt);
                }});
            
            
            

          return jpCatalogo;
      }


       public void btRegistrarA(ActionEvent evt){
           grupo = "";
           grado = "";
           telefono = "";
           celular = "";
           
           if(rbtAlumnos.isSelected()==true){
               grupo = cbGrupo.getSelectedItem().toString();
               grado = cbGrado.getSelectedItem().toString();
               
               respuesta = cs.validarDatos(txtControl.getText(), txtNombre.getText(), txtPaterno.getText(), txtMaterno.getText(), grado,grupo);
               
               if(respuesta==false){
                   telefono = txtLadaT.getText()+" "+txtTelefono.getText();
                   celular = txtLadaC.getText()+" "+txtCelular.getText();
                   cs.registrarAlumno(txtControl.getText().toUpperCase(), txtNombre.getText().toUpperCase(), txtPaterno.getText().toUpperCase(), txtMaterno.getText().toUpperCase(), grado, grupo, telefono, celular);
               }else{
                   
               }

           }
           txtControl.setText("");
           txtNombre.setText("");
           txtPaterno.setText("");
           txtMaterno.setText("");
           txtLadaT.setText("");
           txtTelefono.setText("");
           txtLadaC.setText("");
           txtCelular.setText("");
           cbGrado.setSelectedItem("");
           cbGrupo.setSelectedItem("");
           
           jtAlumno = null;
           jtAlumno = cs.generarTablaAlumnos();
           System.out.println("Presione boton registrar en Alumnos");
       }

       public void btRegistrarD(ActionEvent evt){
           grupo = "na";
           grado = "na";
           telefono = "na";
           celular = "na";
           
           if(rbtDocentes.isSelected()==true){
              
               
               respuesta = cs.validarDatos(txtControlD.getText(), txtNombreD.getText(), txtPaternoD.getText(), txtMaternoD.getText(), grado,grupo);
               
               if(respuesta==false){
                   
                   cs.registrarDocente(txtControlD.getText().toUpperCase(), txtNombreD.getText().toUpperCase(), txtPaternoD.getText().toUpperCase(), txtMaternoD.getText().toUpperCase());
               }else{
                   
               }

           }
           txtControlD.setText("");
           txtNombreD.setText("");
           txtPaternoD.setText("");
           txtMaternoD.setText("");
           jtDocente = null;
           jtDocente = cs.generarTablaDocente();
           
           
           System.out.println("Presione boton registrar en Docentes");
       }

       public void btConsultas(ActionEvent evt){
           System.out.println("btConsultas");
           if(rbtDocentes.isSelected()==true){
                System.out.println("tbDocentes");
                jpRegA.setVisible(false);
                jpConsultasA.setVisible(false);

                jpRegD.setVisible(false);
                jpConsultasD.setVisible(true);
                
                jtDocente = null;
                jtDocente = cs.generarTablaDocente();
           }

           if(rbtAlumnos.isSelected()==true){
                System.out.println("rbAlumnos");
                jpRegA.setVisible(false);
                jpConsultasA.setVisible(true);

                jpRegD.setVisible(false);
                jpConsultasD.setVisible(false);
                
                jtAlumno = null;
                jtAlumno = cs.generarTablaAlumnos();
           }
       }

       public void rbtAlumnos(ActionEvent evt){
           System.out.println("rbtAlumnos");
            btConsultas.setVisible(true);
            btRegistrar.setVisible(true);
           if(btRegistrar.isSelected()==true){
               btConsultas.setVisible(true);
               btRegistrar.setVisible(true);
               
               
                jpRegA.setVisible(true);
                jpConsultasA.setVisible(false);

                jpRegD.setVisible(false);
                jpConsultasD.setVisible(false);
            }

            if(btConsultas.isSelected()==true){
                btConsultas.setVisible(true);
                btRegistrar.setVisible(true);
                
                
                jpRegA.setVisible(false);
                jpConsultasA.setVisible(true);

                jpRegD.setVisible(false);
                jpConsultasD.setVisible(false);
            }
       }

       public void rbtDocentes(ActionEvent evt){
            btConsultas.setVisible(true);
            btRegistrar.setVisible(true);
            if(btRegistrar.isSelected()==true){
                btConsultas.setVisible(true);
                btRegistrar.setVisible(true);
            
                jpRegA.setVisible(false);
                jpConsultasA.setVisible(false);

                jpRegD.setVisible(true);
                jpConsultasD.setVisible(false);
            }

            if(btConsultas.isSelected()==true){
                btConsultas.setVisible(true);
                btRegistrar.setVisible(true);
                
                jpRegA.setVisible(false);
                jpConsultasA.setVisible(false);

                jpRegD.setVisible(false);
                jpConsultasD.setVisible(true);
            }
       }
       
       public void btModificarA(ActionEvent evt){
           NumTupla = jtAlumno.getSelectedRow();
           
           if(NumTupla<0){
                JOptionPane.showMessageDialog(jpCatalogo, "Seleccione Información a Modificar.");
                System.out.println("NO SELECCIONO NADA");
            }else{
                if(NumTupla>=0){
                   System.out.println("SI SELECCIONO "+NumTupla);
                   cs.mostrarDatosAlumno(NumTupla);
                }
            }
           System.out.println("clik en modificar");
       }
       
       public void btEliminarA(ActionEvent evt){
           NumTupla = jtAlumno.getSelectedRow();
          if(NumTupla<0){
              JOptionPane.showMessageDialog(jpCatalogo, "Seleccione Información a Eliminar.");
          }else{
              if(NumTupla>=0){
                 switch(JOptionPane.showOptionDialog(jpCatalogo, "¿Está seguro que desea eliminar este registro?", "Eliminar Libro", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{" SI "," NO"},"0"))
               {
                case 0:
                       cs.eliminarAlumno(NumTupla);
                       jtAlumno=null;
                       jtAlumno=cs.generarTablaAlumnos();
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

       public void btModificarD(ActionEvent evt){
           
           
           NumTupla = jtDocente.getSelectedRow();
           
           if(NumTupla<0){
                JOptionPane.showMessageDialog(jpCatalogo, "Seleccione Información a Modificar.");
                System.out.println("NO SELECCIONO NADA");
            }else{
                if(NumTupla>=0){
                   System.out.println("SI SELECCIONO "+NumTupla);
                   cs.mostrarDatosDocente(NumTupla);
                }
            }
           System.out.println("clik en modificar");
           
           
           System.out.println("Modificar Docentes");
       }
       
       public void btEliminarD(ActionEvent evt){
            NumTupla = jtDocente.getSelectedRow();
          if(NumTupla<0){
              JOptionPane.showMessageDialog(jpCatalogo, "Seleccione Información a Eliminar.");
          }else{
              if(NumTupla>=0){
                 switch(JOptionPane.showOptionDialog(jpCatalogo, "¿Está seguro que desea eliminar este registro?", "Eliminar Libro", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{" SI "," NO"},"0"))
               {
                case 0:
                       cs.eliminarDocente(NumTupla);
                       jtDocente=null;
                       jtDocente=cs.generarTablaDocente();
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


}