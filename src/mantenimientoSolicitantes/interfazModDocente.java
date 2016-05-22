/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientoSolicitantes;
import principal.conexion;

/**
 *
 * @author ACER
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Marisol
 */
public class interfazModDocente {

    controlSolicitante cs = new controlSolicitante();
    
    JTextField txtNoControlD, txtApePaterD, txtApeMaterD, txtNombreD;
    JLabel laNoControlD, laApePaterD, laApeMaterD, labNombreD;
   
    JButton btModificar;
    JButton btCancelar;
    String grupo, grado;
    boolean respuesta, limite;
    
    /*Modificar limites*/
    JLabel laLimites;
    JLabel laLLibros;
    JLabel laLMV;
    
    JTextField txtLLibros;
    JTextField txtLMV;
    
    JFrame interfaz = new JFrame();
    
    JTextField txtLLibroAntes;
    JTextField txtLMVAntes;
    
    /*prestamos*/
    JTextField txtPL;
    JTextField txtPMV;
    
    

    public interfazModDocente(){

        interfaz.setTitle("Bibliothek / Docente / Modificar");
        interfaz.setLayout(null);
        interfaz.setResizable(false); 
        //interfaz.setDefaultCloseOperation(0);
        interfaz.setBounds(100,100,400, 500);  
        Inicializar();
        interfaz.setVisible(true);
        
    }

    public void Inicializar() {
        laNoControlD = new JLabel("No.Control:");
        laNoControlD.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laNoControlD.setBounds(5,20,80,20);
        laNoControlD.setVisible(true);
        interfaz.add(laNoControlD);

        txtNoControlD = new JTextField();
        txtNoControlD.setEnabled(false);
        txtNoControlD.setBounds(100,20,150,20);
        txtNoControlD.setVisible(true);
        interfaz.add(txtNoControlD);

        labNombreD = new JLabel("Nombre");
        labNombreD.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        labNombreD.setBounds(300,80,100,20);
        labNombreD.setVisible(true);
        interfaz.add(labNombreD);

        txtNombreD = new JTextField();
        txtNombreD.setBounds(300,100,80,20);
        txtNombreD.setVisible(true);
        interfaz.add(txtNombreD);

        laApePaterD = new JLabel("Apellido Paterno");
        laApePaterD.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laApePaterD.setBounds(5,80,120, 20);
        laApePaterD.setVisible(true);
        interfaz.add(laApePaterD);

        txtApePaterD = new JTextField();
        txtApePaterD.setBounds(5,100,105,20);
        txtApePaterD.setVisible(true);
        interfaz.add(txtApePaterD);

        laApeMaterD = new JLabel("Apellido Materno");
        laApeMaterD.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laApeMaterD.setBounds(150, 80, 120, 20);
        laApeMaterD.setVisible(true);
        interfaz.add(laApeMaterD);

        txtApeMaterD = new JTextField();
        txtApeMaterD.setBounds(150, 100, 105, 20);
        txtApeMaterD.setVisible(true);
        interfaz.add(txtApeMaterD);


        

       


        laLimites = new JLabel("Limite de prestamos");
        laLimites.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laLimites.setBounds(50,240,200, 20);
        laLimites.setVisible(true);
        interfaz.add(laLimites);
        
        laLLibros = new JLabel("Libros");
        laLLibros.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laLLibros.setBounds(70,270,100, 20);
        laLLibros.setVisible(true);
        interfaz.add(laLLibros);
        
        txtLLibros = new JTextField();
        txtLLibros.setBounds(130, 270,105, 20);
        txtLLibros.setVisible(true);
        interfaz.add(txtLLibros);
        
        txtLLibros.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
        unoKeyReleased(evt);
        }
                private void unoKeyReleased(java.awt.event.KeyEvent evt) {
                  String str = txtLLibros.getText();
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
                      txtLLibros.setText("");
                      txtLLibros.setText(new String(resultado,0,j));
                    }
              }});
        
        
        laLMV = new JLabel("Material Visual");
        laLMV.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laLMV.setBounds(5,300,140, 20);
        laLMV.setVisible(true);
        interfaz.add(laLMV);
        

        txtLMV = new JTextField();
        txtLMV.setBounds(130, 300, 105, 20);
        txtLMV.setVisible(true);
        interfaz.add(txtLMV);
        
        txtLMV.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
        unoKeyReleased(evt);
        }
                private void unoKeyReleased(java.awt.event.KeyEvent evt) {
                  String str = txtLMV.getText();
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
                      txtLMV.setText("");
                      txtLMV.setText(new String(resultado,0,j));
                    }
              }});
        
        
        
        
        btModificar = new JButton("Guardar Cambios");
        btModificar.setBounds(5, 400, 140, 20);
        btModificar.setVisible(true);
        interfaz.add(btModificar);

        btModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarActionPerformed(evt);
            }
        });
        
        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(260,400,100,20);
        btCancelar.setVisible(true);
        interfaz.add(btCancelar);
        
        
         btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        
         txtLLibroAntes = new JTextField();
         txtLMVAntes= new JTextField();
         txtPL = new JTextField();
         txtPMV = new JTextField();
         
       
         System.out.println("limitelibro Antes"+txtLLibroAntes.getText());
         System.out.println("limiteMV Antes"+txtLMVAntes.getText());
    }

    public void btModificarActionPerformed(ActionEvent evt){
        
        grupo = "na";
        grado = "na";

         

            respuesta = cs.validarDatos(txtNoControlD.getText(), txtNombreD.getText(), txtApePaterD.getText(), txtApeMaterD.getText(), grado,grupo);

        if(respuesta==false){
              limite = cs.validarLimire(txtLLibros.getText(), txtLMV.getText());
            if(limite==false){
                
                cs.modificarDocente(txtNoControlD.getText().toUpperCase(), txtNombreD.getText().toUpperCase(), txtApePaterD.getText().toUpperCase(), txtApeMaterD.getText().toUpperCase(),txtLLibros.getText(),txtLMV.getText(),txtLLibroAntes.getText(),txtLMVAntes.getText(),txtPL.getText(),txtPMV.getText());
                interfaz.dispose();
            }
            
             
        }else{

        }

        
        
    }
    
    public void btCancelarActionPerformed(ActionEvent evt){
        switch(JOptionPane.showOptionDialog(interfaz, "¿Está seguro que desea Cancelar la Modificación?", "Eliminar Libro", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{" SI "," NO"},"0"))
       {
        case 0:
                interfaz.dispose();
               break;
        case 1:
               //JOptionPane.showMessageDialog(this, "NO");
               break;
        default:
               JOptionPane.showMessageDialog(interfaz, "No se eligió ninguna opción");
               break;
        }
        
    }


}
