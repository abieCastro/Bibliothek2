/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientoMaterialVisual;

import principal.conexion;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author ACER
 */
public class interfazModificarMaterialVisual {
    
    
    conexion c = new conexion();
    controlMaterialVisual CMV = new controlMaterialVisual();
    interfazMaterialVisual IMV;
    JFrame interfaz = new JFrame();
   
    JTextField txtTitulo;
    JLabel labTitulo;
    JLabel labVolumen;
    JTextField txtVolumen;
    
    JLabel labAno;
    JTextField txtAno;
    JLabel labCantidad,labCantidad2;
    JTextField txtCantidad,txtCantidad2;
    JLabel labClasificacion;
    JComboBox cbClasificacion;
    JButton btModificar;
    JButton btCancelar;
    JLabel labClaveL;
    JTextField txtClaveL;
    
    public String SQL;
    Connection Conexion;
    ResultSet resultset;
    Statement statement;
    ResultSetMetaData rsmd;
    PreparedStatement Pstatement ;
    
    
    /*Estas Variables son para crear los nuevos Ejemplares*/
    String Clave;
    String CortarClaveT=null;
    String CortarNumMax=null;
    String STransNumMax = null;
    int ETransNumMax=0;
    String NuevoEjem=null;
    
    /*Estas variables son para generar la diferencia de cantidad VS la existencia*/
    int dispo= 0;
    int exist = 0;
    int diferencia = 0;
        
    int Totaldispo= 0;
    int TotalExis= 0;
    String clasificacion=null;
    
    public interfazModificarMaterialVisual (){

        interfaz.setTitle("Bibliothek / Material Visual / Modificar" );
        interfaz.setLayout(null);
        interfaz.setResizable(false); 
        interfaz.setDefaultCloseOperation(0);
        interfaz.setBounds(200,300,400, 300);  
        Inicializar();
        interfaz.setVisible(true);
    }  
    
    public void Inicializar(){
        labClaveL = new JLabel("Clave Material Visual:");
        labClaveL .setFont(new java.awt.Font("Tahoma", Font.BOLD,  13));
        labClaveL .setBounds(0,10,120,20);
        labClaveL .setVisible(true);
        interfaz.add(labClaveL );
        
        txtClaveL= new JTextField();
        txtClaveL.setBounds(150,10,100,20);
        txtClaveL.setEditable(false);

        txtClaveL.setVisible(true);
        interfaz.add(txtClaveL);
        
        
        
        labTitulo = new JLabel("Título:");
        labTitulo.setFont(new java.awt.Font("Tahoma", Font.BOLD,  13));
        labTitulo.setBounds(0,50,40,20);
        labTitulo.setVisible(true);
        interfaz.add(labTitulo);
        
        txtTitulo= new JTextField();
        txtTitulo.setBounds(60,50,300,20);
        txtTitulo.setVisible(true);
        interfaz.add(txtTitulo);
    
        labVolumen = new JLabel("Volumen:");
        labVolumen.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        labVolumen.setBounds(0,75,90,20);
        labVolumen.setVisible(true);
        interfaz.add(labVolumen);
        
        txtVolumen= new JTextField();
        txtVolumen.setBounds(60,75,300,20);
        txtVolumen.setVisible(true);
        interfaz.add(txtVolumen);
        
        
        
        labAno = new JLabel("Año:");
        labAno.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        labAno.setBounds(0,125,50,20);
        labAno.setVisible(true);
        interfaz.add(labAno);
        
        txtAno= new JTextField();
        txtAno.setBounds(60,125,50,20);
        txtAno.setVisible(true);
        interfaz.add(txtAno);
        
        labCantidad = new JLabel("Cantidad:");
        labCantidad.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        labCantidad.setBounds(180,125,70,20);
        labCantidad.setVisible(true);
        interfaz.add(labCantidad);
        
        
        
        txtCantidad= new JTextField();
        txtCantidad.setBounds(250,125,50,20);
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
        
        
        
        
        
        interfaz.add(txtCantidad);
        
        txtCantidad2= new JTextField();
        txtCantidad2.setBounds(250,125,50,20);
        txtCantidad2.setVisible(false);
        interfaz.add(txtCantidad2);
        
        labClasificacion = new JLabel("Clasificación:");
        labClasificacion.setFont(new java.awt.Font("Tahoma",Font.BOLD, 13));
        labClasificacion.setBounds(0,150,100,20);
        labClasificacion.setVisible(true);
        interfaz.add(labClasificacion);
        
        cbClasificacion = new JComboBox();
        cbClasificacion.setBounds(100,150,150,20);
        cbClasificacion.setVisible(true);
        interfaz.add(cbClasificacion);
        cbClasificacion.addItem("");
        cbClasificacion.addItem("Ciencia");
        cbClasificacion.addItem("Historia");
        cbClasificacion.addItem("Recreativo");
        cbClasificacion.addItem("Geografía");
        cbClasificacion.addItem("Español");
        cbClasificacion.addItem("Matemáticas");
        
        
        btModificar = new JButton("Guardar Cambios");
        btModificar.setBounds(10,220,150,20);
        btModificar.setVisible(true);
        interfaz.add(btModificar);
        
        btModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModificarActionPerformed(evt);
            }
        });
        
        
        btCancelar = new JButton("Cancelar");
        btCancelar.setBounds(260,220,100,20);
        btCancelar.setVisible(true);
        interfaz.add(btCancelar);
        
        
         btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });
        
        
        
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
    
    
    private void btModificarActionPerformed(ActionEvent evt) {
        String clasificacion= cbClasificacion.getSelectedItem().toString();
        boolean respuesta = CMV.validarDatos(txtTitulo.getText().toUpperCase(), txtVolumen.getText().toUpperCase(),txtAno.getText(), txtCantidad.getText(), clasificacion);

          if(respuesta==false){
            int cantidad1=Integer.parseInt(txtCantidad2.getText());
            int cantidad2=Integer.parseInt(txtCantidad.getText());
            CMV.modificarMV(cantidad1, cantidad2,txtClaveL.getText(),txtTitulo.getText().toUpperCase(),txtVolumen.getText().toUpperCase(),txtAno.getText(),clasificacion);
            
            IMV = new interfazMaterialVisual();
            IMV.jtTablaMV=null;
            IMV.jtTablaMV=CMV.generarTablaEjemplarMV();
            
            IMV.jtTablaRegMV=null;
            IMV.jtTablaRegMV=CMV.generarTablaMV();
            interfaz.dispose(); 
          }
        
        
    }
    
}

