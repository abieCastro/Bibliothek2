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
public class interfazModAlumno {

    controlSolicitante cs = new controlSolicitante();

    JTextField txtNoControlA, txtApePaterA, txtApeMaterA, txtNombreA, txtCelular, txtTelefono, txtGrado, txtGrupo;
    JLabel laNoControlA, laApePaterA, laApeMaterA, labNombreA, laCelular, laTelefono, laGrado, laGrupo;
    JComboBox cbGrado, cbGrupo;
    JButton btModificar;
    JButton btCancelar;

    String grupo, grado, telefono, celular;
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

    public interfazModAlumno() {

        interfaz.setTitle("Bibliothek / Alumno / Modificar");
        interfaz.setLayout(null);
        interfaz.setResizable(false);
        //interfaz.setDefaultCloseOperation(0);
        interfaz.setBounds(100, 100, 400, 500);
        Inicializar();
        interfaz.setVisible(true);

    }

    public void Inicializar() {
        laNoControlA = new JLabel("No.Control:");
        laNoControlA.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laNoControlA.setBounds(5, 20, 80, 20);
        laNoControlA.setVisible(true);
        interfaz.add(laNoControlA);

        txtNoControlA = new JTextField();
        txtNoControlA.setEnabled(false);
        txtNoControlA.setBounds(100, 20, 150, 20);
        txtNoControlA.setVisible(true);
        interfaz.add(txtNoControlA);

        labNombreA = new JLabel("Nombre");
        labNombreA.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        labNombreA.setBounds(300, 80, 100, 20);
        labNombreA.setVisible(true);
        interfaz.add(labNombreA);

        txtNombreA = new JTextField();
        txtNombreA.setBounds(300, 100, 80, 20);
        txtNombreA.setVisible(true);
        interfaz.add(txtNombreA);

        laApePaterA = new JLabel("Apellido Paterno");
        laApePaterA.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laApePaterA.setBounds(5, 80, 120, 20);
        laApePaterA.setVisible(true);
        interfaz.add(laApePaterA);

        txtApePaterA = new JTextField();
        txtApePaterA.setBounds(5, 100, 105, 20);
        txtApePaterA.setVisible(true);
        interfaz.add(txtApePaterA);

        laApeMaterA = new JLabel("Apellido Materno");
        laApeMaterA.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laApeMaterA.setBounds(150, 80, 120, 20);
        laApeMaterA.setVisible(true);
        interfaz.add(laApeMaterA);

        txtApeMaterA = new JTextField();
        txtApeMaterA.setBounds(150, 100, 105, 20);
        txtApeMaterA.setVisible(true);
        interfaz.add(txtApeMaterA);

        laCelular = new JLabel("Celular");
        laCelular.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laCelular.setBounds(5, 120, 100, 20);
        laCelular.setVisible(true);
        interfaz.add(laCelular);

        txtCelular = new JTextField();
        txtCelular.setBounds(5, 140, 100, 20);
        txtCelular.setVisible(true);
        interfaz.add(txtCelular);

        laTelefono = new JLabel("Telefono");
        laTelefono.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laTelefono.setBounds(150, 120, 100, 20);
        laTelefono.setVisible(true);
        interfaz.add(laTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(150, 140, 100, 20);
        txtTelefono.setVisible(true);
        interfaz.add(txtTelefono);

        laGrado = new JLabel("Grado:");
        laGrado.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laGrado.setBounds(5, 160, 100, 25);
        laGrado.setVisible(true);
        interfaz.add(laGrado);

        cbGrado = new JComboBox();
        cbGrado.setBounds(5, 180, 100, 20);
        cbGrado.setVisible(true);
        interfaz.add(cbGrado);
        cbGrado.addItem("");
        cbGrado.addItem("1");
        cbGrado.addItem("2");
        cbGrado.addItem("3");

        laGrupo = new JLabel("Grupo:");
        laGrupo.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laGrupo.setBounds(150, 160, 100, 25);
        laGrupo.setVisible(true);
        interfaz.add(laGrupo);

        cbGrupo = new JComboBox();
        cbGrupo.setBounds(150, 180, 100, 20);
        cbGrupo.setVisible(true);
        interfaz.add(cbGrupo);
        cbGrupo.addItem("");
        cbGrupo.addItem("A");
        cbGrupo.addItem("B");
        cbGrupo.addItem("C");
        cbGrupo.addItem("D");
        cbGrupo.addItem("E");
        cbGrupo.addItem("F");

        laLimites = new JLabel("Limite de prestamos");
        laLimites.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laLimites.setBounds(50, 240, 200, 20);
        laLimites.setVisible(true);
        interfaz.add(laLimites);

        laLLibros = new JLabel("Libros");
        laLLibros.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laLLibros.setBounds(70, 270, 100, 20);
        laLLibros.setVisible(true);
        interfaz.add(laLLibros);

        txtLLibros = new JTextField();
        txtLLibros.setBounds(130, 270, 105, 20);
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
                int j = 0;
                boolean error = false;
                for (int i = 0; i < fuente.length; i++) {
                    if (fuente[i] >= '0' && fuente[i] <= '9' || fuente[i] == '.') {
                        resultado[j++] = fuente[i];
                    } else {
                        error = true;
                        java.awt.Toolkit.getDefaultToolkit().beep();
                    }
                }
                if (error) {
                    txtLLibros.setText("");
                    txtLLibros.setText(new String(resultado, 0, j));
                }
            }
        });

        laLMV = new JLabel("Material Visual");
        laLMV.setFont(new java.awt.Font("Tahoma", Font.BOLD, 13));
        laLMV.setBounds(5, 300, 140, 20);
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
                int j = 0;
                boolean error = false;
                for (int i = 0; i < fuente.length; i++) {
                    if (fuente[i] >= '0' && fuente[i] <= '9' || fuente[i] == '.') {
                        resultado[j++] = fuente[i];
                    } else {
                        error = true;
                        java.awt.Toolkit.getDefaultToolkit().beep();
                    }
                }
                if (error) {
                    txtLMV.setText("");
                    txtLMV.setText(new String(resultado, 0, j));
                }
            }
        });

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
        btCancelar.setBounds(260, 400, 100, 20);
        btCancelar.setVisible(true);
        interfaz.add(btCancelar);

        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        txtLLibroAntes = new JTextField();
        txtLMVAntes = new JTextField();
        txtPL = new JTextField();
        txtPMV = new JTextField();

        System.out.println("limitelibro Antes" + txtLLibroAntes.getText());
        System.out.println("limiteMV Antes" + txtLMVAntes.getText());
    }

    public void btModificarActionPerformed(ActionEvent evt) {

        grupo = "";
        grado = "";
        telefono = "";
        celular = "";

        grupo = cbGrupo.getSelectedItem().toString();
        grado = cbGrado.getSelectedItem().toString();

        respuesta = cs.validarDatos(txtNoControlA.getText(), txtNombreA.getText(), txtApePaterA.getText(), txtApeMaterA.getText(), grado, grupo);

        if (respuesta == false) {
            limite = cs.validarLimire(txtLLibros.getText(), txtLMV.getText());
            if (limite == false) {
                telefono = txtTelefono.getText();
                celular = txtCelular.getText();
                cs.modificarAlumno(txtNoControlA.getText().toUpperCase(), txtNombreA.getText().toUpperCase(), txtApePaterA.getText().toUpperCase(), txtApeMaterA.getText().toUpperCase(), grado, grupo, telefono, celular, txtLLibros.getText(), txtLMV.getText(), txtLLibroAntes.getText(), txtLMVAntes.getText(), txtPL.getText(), txtPMV.getText());
                interfaz.dispose();
            }

        } else {

        }

    }

    public void btCancelarActionPerformed(ActionEvent evt) {
        switch (JOptionPane.showOptionDialog(interfaz, "¿Está seguro que desea Cancelar la Modificación?", "Eliminar Libro", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{" SI ", " NO"}, "0")) {
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
