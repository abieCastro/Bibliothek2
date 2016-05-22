/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mantenimientoPrestamos;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.MinMaxDateEvaluator;
import principal.conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import mantenimientoLibros.*;
import mantenimientoMaterialVisual.ejempMatVisual;
import mantenimientoMaterialVisual.materialVisual;
import mantenimientoSolicitantes.*;
/**
 *
 * @author Mac
 */
public class controlPrestamo {
    conexion con = new conexion();
    interfazPrestamo vistaPres;
    int contPresLib=0;
    int contPresMatVis=0;
    int actLimLibro;
    int actLimMatVis;
    int selectAlumno = -1;
    int selectDocente = -1;
    int selectLibro = -1;
    int selectMatVis = -1;    
    String valoresDetPres="";
    ArrayList<alumno> listaAlumno;
    ArrayList<docente> listaDocente;
    ArrayList<libro> listaLibro;
    ArrayList<ejemplarLibro> listaEjempLibro;
    ArrayList<materialVisual> listaMatVis;
    ArrayList<ejempMatVisual> listaEjempMatVis;
        
    public controlPrestamo (interfazPrestamo vistaPres) {     
        this.vistaPres=vistaPres;
    }
        
    //PRÉSTAMOS   
    
    public boolean validarTipoSolicP () {
        boolean validar = false;
        
        if(vistaPres.txtPresNoContBus.getText().toUpperCase().startsWith("A-")) {
            validar=true;
            selectAlumno=1;
            selectDocente=-1;
        } else {
            if(vistaPres.txtPresNoContBus.getText().toUpperCase().startsWith("D-")) {     
               validar=true;
               selectAlumno=-1;
               selectDocente=1;
            }
            else {
                validar=false;
                selectAlumno=-1;
                selectDocente=-1;
            }
        }        
        return validar;
    }    
    
    public boolean buscarPresSolic (String noControl) {        
        boolean validar=false;        
        
        if(selectAlumno == 1) {  
            alumno alum;
            listaAlumno = new ArrayList<alumno>();             
            vistaPres.jpPresSolic.setBorder(javax.swing.BorderFactory.createTitledBorder("Solicitante Alumno"));
            
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("SELECT * FROM alumno WHERE noControlA='"+noControl+"'");
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()) {
                    validar=true;  
                    alum = new alumno();
                    alum.setClaveAlumno(rs.getInt(1));
                    alum.setNoControlA(rs.getString(2));
                    alum.setNombreA(rs.getString(3));
                    alum.setApellidoPatA(rs.getString(4));
                    alum.setApellidoMatA(rs.getString(5));
                    alum.setGradoA(rs.getString(6));
                    alum.setGrupoA(rs.getString(7));              
                    alum.setLimiteLibroA(rs.getInt(10));
                    alum.setLimiteMatVisA(rs.getInt(11));
                    alum.setLibrosSolicA(rs.getInt(12));
                    alum.setMatVisSolicA(rs.getInt(13));
                    listaAlumno.add(alum);                    
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(controlPrestamo.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        if(selectDocente == 1 ) {   
            docente doc;
            listaDocente = new ArrayList<docente>();
            vistaPres.jpPresSolic.setBorder(javax.swing.BorderFactory.createTitledBorder("Solicitante Docente"));
            
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("SELECT * FROM docente WHERE noControlD='"+noControl+"'");
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()) {
                    validar=true;  
                    doc = new docente();
                    doc.setClaveDocente(rs.getInt(1));                
                    doc.setNoControlD(rs.getString(2));
                    doc.setNombreD(rs.getString(3));
                    doc.setApellidoPatD(rs.getString(4));
                    doc.setApellidoMatD(rs.getString(5));
                    doc.setLimiteLibroD(rs.getInt(6));
                    doc.setLimiteMatVisD(rs.getInt(7));
                    doc.setLibrosSolicD(rs.getInt(8));
                    doc.setMatVisSolicD(rs.getInt(9));
                    listaDocente.add(doc);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(controlPrestamo.class.getName()).log(Level.SEVERE, null, ex);
            }             
        }
        return validar;           
    }
    
    public void mostrarPresSolic(JTable tablaAlumno, JTable tablaDocente) {
        
        if(selectAlumno == 1) {            
            DefaultTableModel modeloAlumno = new DefaultTableModel();
            tablaAlumno.setModel(modeloAlumno);
            modeloAlumno.fireTableDataChanged();
            tablaAlumno.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            modeloAlumno.addColumn("noControl");
            modeloAlumno.addColumn("nombre");
            modeloAlumno.addColumn("apellido paterno");
            modeloAlumno.addColumn("apellido materno");
            modeloAlumno.addColumn("grado");
            modeloAlumno.addColumn("grupo");

            Object[] columna = new Object[6];

            for(int x=0; x<listaAlumno.size(); x++) {            
                columna[0] = listaAlumno.get(x).getNoControlA();
                columna[1] = listaAlumno.get(x).getNombreA();
                columna[2] = listaAlumno.get(x).getApellidoPatA();
                columna[3] = listaAlumno.get(x).getApellidoMatA();
                columna[4] = listaAlumno.get(x).getGradoA();
                columna[5] = listaAlumno.get(x).getGrupoA();
                modeloAlumno.addRow(columna);
            }
            vistaPres.jpTablaPresAlum.setVisible(true);                         
            vistaPres.jpTablaPresDoc.setVisible(false);
        }
        
        if(selectDocente == 1) {
            DefaultTableModel modeloDocente = new DefaultTableModel();
            tablaDocente.setModel(modeloDocente);
            modeloDocente.fireTableDataChanged();
            tablaDocente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            modeloDocente.addColumn("noControl");
            modeloDocente.addColumn("nombre");
            modeloDocente.addColumn("apellido Paterno");
            modeloDocente.addColumn("apellido materno");

            Object[] columna = new Object[4];
            for(int x=0; x<listaDocente.size(); x++) {            
                columna[0] = listaDocente.get(x).getNoControlD();
                columna[1] = listaDocente.get(x).getNombreD();
                columna[2] = listaDocente.get(x).getApellidoPatD();
                columna[3] = listaDocente.get(x).getApellidoMatD();
                modeloDocente.addRow(columna);
            }
            vistaPres.jpTablaPresAlum.setVisible(false);                         
            vistaPres.jpTablaPresDoc.setVisible(true);
        }        
    }   
    
    public void validarTipoPres() {
        if(selectAlumno == 1) {
            vistaPres.labLimiteLibro.setVisible(false);
            vistaPres.labLimiteMatVis.setVisible(false);
            vistaPres.labResLimLibro.setVisible(false);
            vistaPres.labResLimMatVis.setVisible(false);
            vistaPres.labResLimLibro.setText("");
            vistaPres.labResLimMatVis.setText("");
            vistaPres.jpPresElegir.setVisible(false);

            if(listaAlumno.get(0).getLimiteLibroA()==0  && listaAlumno.get(0).getLimiteMatVisA()==0) {
                JOptionPane.showMessageDialog(null, "Ya cumple con el limite de préstamo de libros y material visual", "Advertencia", JOptionPane.WARNING_MESSAGE);
                vistaPres.labLimiteLibro.setVisible(false);
                vistaPres.labLimiteMatVis.setVisible(false);
                vistaPres.labResLimLibro.setVisible(false);
                vistaPres.labResLimMatVis.setVisible(false);
                vistaPres.labResLimLibro.setText(listaAlumno.get(0).getLimiteLibroA()+"");
                vistaPres.labResLimMatVis.setText(listaAlumno.get(0).getLimiteMatVisA()+"");
                vistaPres.jpPresElegir.setVisible(false);
            }       
            if(listaAlumno.get(0).getLimiteLibroA()!=0 && listaAlumno.get(0).getLimiteMatVisA()!=0) {
                vistaPres.labLimiteLibro.setVisible(true);
                vistaPres.labLimiteMatVis.setVisible(true);
                vistaPres.labResLimLibro.setVisible(true);
                vistaPres.labResLimMatVis.setVisible(true);
                vistaPres.labResLimLibro.setText(listaAlumno.get(0).getLimiteLibroA()+"");
                vistaPres.labResLimMatVis.setText(listaAlumno.get(0).getLimiteMatVisA()+"");
                vistaPres.jpPresElegir.setVisible(true);
            }

            if(listaAlumno.get(0).getLimiteLibroA()==0 && listaAlumno.get(0).getLimiteMatVisA()!=0) {
                vistaPres.labLimiteLibro.setVisible(true);
                vistaPres.labLimiteMatVis.setVisible(true);
                vistaPres.labResLimLibro.setVisible(true);
                vistaPres.labResLimMatVis.setVisible(true);
                vistaPres.labResLimLibro.setText(listaAlumno.get(0).getLimiteLibroA()+"");
                vistaPres.labResLimMatVis.setText(listaAlumno.get(0).getLimiteMatVisA()+"");
                vistaPres.jpPresElegir.setVisible(true);
            }
            if(listaAlumno.get(0).getLimiteLibroA()!=0 && listaAlumno.get(0).getLimiteMatVisA()==0) {
                vistaPres.labLimiteLibro.setVisible(true);
                vistaPres.labLimiteMatVis.setVisible(true);
                vistaPres.labResLimLibro.setVisible(true);
                vistaPres.labResLimMatVis.setVisible(true);
                vistaPres.labResLimLibro.setText(listaAlumno.get(0).getLimiteLibroA()+"");
                vistaPres.labResLimMatVis.setText(listaAlumno.get(0).getLimiteMatVisA()+"");
                vistaPres.jpPresElegir.setVisible(true);
            }
        }
        
        if(selectDocente == 1) {
            vistaPres.labLimiteLibro.setVisible(false);
            vistaPres.labLimiteMatVis.setVisible(false);
            vistaPres.labResLimLibro.setVisible(false);
            vistaPres.labResLimMatVis.setVisible(false);
            vistaPres.labResLimLibro.setText("");
            vistaPres.labResLimMatVis.setText("");
            vistaPres.jpPresElegir.setVisible(false);

            if(listaDocente.get(0).getLimiteLibroD()==0  && listaDocente.get(0).getLimiteMatVisD()==0) {
                JOptionPane.showMessageDialog(null, "Ya cumple con el limite de préstamo de libros y material visual", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }       
            if(listaDocente.get(0).getLimiteLibroD()!=0 && listaDocente.get(0).getLimiteMatVisD()!=0) {
                vistaPres.labLimiteLibro.setVisible(true);
                vistaPres.labLimiteMatVis.setVisible(true);
                vistaPres.labResLimLibro.setVisible(true);
                vistaPres.labResLimMatVis.setVisible(true);
                vistaPres.labResLimLibro.setText(listaDocente.get(0).getLimiteLibroD()+"");
                vistaPres.labResLimMatVis.setText(listaDocente.get(0).getLimiteMatVisD()+"");
                vistaPres.jpPresElegir.setVisible(true);
            }

            if(listaDocente.get(0).getLimiteLibroD()==0 && listaDocente.get(0).getLimiteMatVisD()!=0) {
                vistaPres.labLimiteLibro.setVisible(true);
                vistaPres.labLimiteMatVis.setVisible(true);
                vistaPres.labResLimLibro.setVisible(true);
                vistaPres.labResLimMatVis.setVisible(true);
                vistaPres.labResLimLibro.setText(listaDocente.get(0).getLimiteLibroD()+"");
                vistaPres.labResLimMatVis.setText(listaDocente.get(0).getLimiteMatVisD()+"");
                vistaPres.jpPresElegir.setVisible(true);
            }
            if(listaDocente.get(0).getLimiteLibroD()!=0 && listaDocente.get(0).getLimiteMatVisD()==0) {
                vistaPres.labLimiteLibro.setVisible(true);
                vistaPres.labLimiteMatVis.setVisible(true);
                vistaPres.labResLimLibro.setVisible(true);
                vistaPres.labResLimMatVis.setVisible(true);
                vistaPres.labResLimLibro.setText(listaDocente.get(0).getLimiteLibroD()+"");
                vistaPres.labResLimMatVis.setText(listaDocente.get(0).getLimiteMatVisD()+"");
                vistaPres.jpPresElegir.setVisible(true);
            }
        }
    }
    
    public boolean validarTipoEjempP () {
        boolean validar = false;
        
        if(vistaPres.txtPresEjempBus.getText().toUpperCase().startsWith("LIB")) {
            validar=true;
            selectLibro=1;
            selectMatVis=-1;            
        } else {
            if(vistaPres.txtPresEjempBus.getText().toUpperCase().startsWith("MV")) {     
               validar=true;
               selectLibro=-1;
               selectMatVis=1;
            }
            else {
                validar=false;
                selectLibro=-1;
                selectMatVis=-1;
            }
        }        
        return validar;
    }
    
    public boolean validarAceptPres() {
        boolean validar = false;
        
        if(selectLibro == 1) {
            if(vistaPres.labResLimLibro.getText().equalsIgnoreCase("0") ) {
                validar = false;
                JOptionPane.showMessageDialog(null, "Ya cumple con el límite de préstamos de libros", "ERROR", JOptionPane.ERROR_MESSAGE);                                           
            } else {
                validar = true;                
            }            
        }
        
        if(selectMatVis == 1) {
            if(vistaPres.labResLimMatVis.getText().equalsIgnoreCase("0") ) {
                validar = false;
                JOptionPane.showMessageDialog(null, "Ya cumple con el límite de préstamos de material visual", "ERROR", JOptionPane.ERROR_MESSAGE);                           
            } else {
                validar = true;                
            }
        }
        
        return validar;
    }
    
    public boolean buscarEjempPres(String ejemplar) {
        boolean validar=false;        
        
        if(selectLibro == 1 ) {  
            libro lib;
            listaLibro = new ArrayList<libro>();             
            ejemplarLibro ejempLib;
            listaEjempLibro = new ArrayList<ejemplarLibro>();             
                        
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("SELECT libro.claveLibro, ejemplarlibro.idEjemplarL, libro.tituloL, libro.autorL, libro.añoL, libro.editorialL, libro.clasificacionL, libro.existenciaL, libro.disponibilidadL, ejemplarlibro.estadoL FROM libro INNER JOIN ejemplarlibro ON (libro.claveLibro=ejemplarlibro.libro_claveLibro) WHERE ejemplarlibro.idEjemplarL='"+ejemplar+"';");
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()) {
                    validar=true;  
                    lib = new libro();
                    ejempLib = new ejemplarLibro();
                    lib.setClaveLibro(rs.getString(1));
                    ejempLib.setIdEjemplarL(rs.getString(2));
                    lib.setTituloL(rs.getString(3));
                    lib.setAutorL(rs.getString(4));
                    lib.setAñoL(rs.getString(5));
                    lib.setEditorialL(rs.getString(6));
                    lib.setClasificacionL(rs.getString(7));
                    lib.setExistenciaL(rs.getInt(8));
                    lib.setDisponibilidadL(rs.getInt(9));
                    ejempLib.setEstadoL(rs.getString(10));
                    listaLibro.add(lib);
                    listaEjempLibro.add(ejempLib);                    
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(controlPrestamo.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        if(selectMatVis==1) {
            materialVisual matVis;
            listaMatVis = new ArrayList<materialVisual>();             
            ejempMatVisual ejempMatVis;
            listaEjempMatVis = new ArrayList<ejempMatVisual>();             
                        
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("SELECT materialvisual.claveMatVis, ejempmatvisual.idEjemplarM, materialvisual.tituloM, materialvisual.volumenM, materialvisual.añoM, materialvisual.clasificacionM, materialvisual.existenciaM, materialvisual.disponibilidadM, ejempmatvisual.EstadoM FROM materialvisual INNER JOIN ejempmatvisual ON (materialvisual.claveMatVis=ejempmatvisual.materialvisual_claveMatVis) WHERE ejempmatvisual.idEjemplarM='"+ejemplar+"';");
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()) {
                    validar=true;  
                    matVis = new materialVisual();
                    ejempMatVis = new ejempMatVisual();
                    matVis.setClaveMatVis(rs.getString(1));
                    ejempMatVis.setIdEjemplarM(rs.getString(2));
                    matVis.setTituloM(rs.getString(3));
                    matVis.setVolumneM(rs.getString(4));
                    matVis.setAñoM(rs.getString(5));
                    matVis.setClasificacionM(rs.getString(6));
                    matVis.setExistenciaM(rs.getInt(7));
                    matVis.setDisponibilidadM(rs.getInt(8));
                    ejempMatVis.setEstadoM(rs.getString(9));
                    listaMatVis.add(matVis);
                    listaEjempMatVis.add(ejempMatVis);                    
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(controlPrestamo.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
        
        return validar;
    }
    
    public boolean validarEstadoP (String estado) {
        boolean validar=false;
        
        if(selectLibro==1) {     
            
            if(listaEjempLibro.get(0).getEstadoL().equalsIgnoreCase("DISPONIBLE")) {
                validar= true;                
            } else {
                validar=false;
            }            
        }
        if(selectMatVis==1) {    
            
            if(listaEjempMatVis.get(0).getEstadoM().equalsIgnoreCase("DISPONIBLE")) {
                validar= true;                
            } else {
                validar=false;
            }            
        }
        
        return validar;
    }
    
    public void mostrarEjempP(JTable tablaLibro, JTable tablaMatVis) {
        
        if(selectLibro==1) {
            DefaultTableModel modeloLibro = new DefaultTableModel();
            tablaLibro.setModel(modeloLibro);
            modeloLibro.fireTableDataChanged();
            tablaLibro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            modeloLibro.addColumn("clave");
            modeloLibro.addColumn("ejemplar");
            modeloLibro.addColumn("título");
            modeloLibro.addColumn("autor");
            modeloLibro.addColumn("año");
            modeloLibro.addColumn("editorial");
            modeloLibro.addColumn("clasificación");            

            Object[] columna = new Object[8];

            for(int y=0; y<listaLibro.size(); y++) { 
                columna[0] = listaLibro.get(y).getClaveLibro();
                columna[2] = listaLibro.get(y).getTituloL();
                columna[3] = listaLibro.get(y).getAutorL();
                columna[4] = listaLibro.get(y).getAñoL();
                columna[5] = listaLibro.get(y).getEditorialL();
                columna[6] = listaLibro.get(y).getClasificacionL();
                for(int x=0; x<listaEjempLibro.size(); x++) {                 
                    columna[1] = listaEjempLibro.get(x).getIdEjemplarL();                    
                    modeloLibro.addRow(columna);
                }
                modeloLibro.addRow(columna);
            }
            //ejempPres = vistaPres.txtEjempBus.getText();
            vistaPres.jpTablaPresLib.setVisible(true);                         
            vistaPres.jpTablaPresMaVi.setVisible(false);          
        }
        
        if(selectMatVis==1) {
            DefaultTableModel modeloMatVis = new DefaultTableModel();
            tablaMatVis.setModel(modeloMatVis);
            modeloMatVis.fireTableDataChanged();
            tablaMatVis.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            modeloMatVis.addColumn("clave");
            modeloMatVis.addColumn("ejemplar");
            modeloMatVis.addColumn("título");
            modeloMatVis.addColumn("volumen");
            modeloMatVis.addColumn("axño");
            modeloMatVis.addColumn("clasificación");              

            Object[] columna = new Object[7];

            for(int y=0; y<listaMatVis.size(); y++) { 
                columna[0] = listaMatVis.get(y).getClaveMatVis();
                columna[2] = listaMatVis.get(y).getTituloM();
                columna[3] = listaMatVis.get(y).getvolumenM();
                columna[4] = listaMatVis.get(y).getAñoM();
                columna[5] = listaMatVis.get(y).getClasificacionM();
                for(int x=0; x<listaEjempMatVis.size(); x++) {                 
                    columna[1] = listaEjempMatVis.get(x).getIdEjemplarM();                    
                    modeloMatVis.addRow(columna);
                }
                modeloMatVis.addRow(columna);
            }
            //ejempPres = vistaPres.txtEjempBus.getText();
            vistaPres.jpTablaPresLib.setVisible(false);                         
            vistaPres.jpTablaPresMaVi.setVisible(true);                     
        }
    }
        
    public String recorrerDetPres() {
        int fila = vistaPres.tbPresDetalle.getRowCount();   
            
            valoresDetPres=""; 
            for (int i = 0; i < fila; i++) {
                String valor = (String) vistaPres.tbPresDetalle.getValueAt(i, 0)+"/"+(String) vistaPres.tbPresDetalle.getValueAt(i, 1)+"/"+(String) vistaPres.tbPresDetalle.getValueAt(i, 2);
                valoresDetPres += valor +"--";
            }    
        return valoresDetPres;
    }
    
    public boolean encontrarDetPres() {  
        boolean validar = false;
        
        String valoresClave = recorrerDetPres();
        String ejemp ="";
        
        String[] prestamos=valoresClave.split("--");
        
        for(int x=0; x<prestamos.length; x++) {
            ejemp = ejemp + prestamos[x];
        }
        
        String[] valoresEjemp = ejemp.split("/");
        for(int y=0; y<valoresEjemp.length; y++) {
            if(valoresEjemp[y].equalsIgnoreCase(vistaPres.txtPresEjempBus.getText())) {
                validar = false;
                break;
            } else {
                validar = true;
            }
        }
        
        return validar;             
    }
    
    public void agregarDetPres(JTable tbDetalle) throws ParseException {
        String agregar="";
        
        if (selectLibro == 1) {      
            if(valoresDetPres!="") {
                String todoL=listaLibro.get(0).getClaveLibro()+"/"+listaEjempLibro.get(0).getIdEjemplarL()+"/"+listaLibro.get(0).getTituloL();
                agregar=valoresDetPres+todoL;
                llenarDetallePres(agregar, tbDetalle); 
                contPresLib = contPresLib +1;
                actLimLibro=Integer.parseInt(vistaPres.labResLimLibro.getText())-1;
                vistaPres.labResLimLibro.setText(String.valueOf(actLimLibro));
            } else {
                agregar=listaLibro.get(0).getClaveLibro()+"/"+listaEjempLibro.get(0).getIdEjemplarL()+"/"+listaLibro.get(0).getTituloL();                    
                llenarDetallePres(agregar,tbDetalle);
                contPresLib = contPresLib +1;
                actLimLibro=Integer.parseInt(vistaPres.labResLimLibro.getText())-1;
                vistaPres.labResLimLibro.setText(String.valueOf(actLimLibro));
            }
        }
        
        if (selectMatVis == 1) {      
            if(valoresDetPres!="") {
                String todoM=listaMatVis.get(0).getClaveMatVis()+"/"+listaEjempMatVis.get(0).getIdEjemplarM()+"/"+listaMatVis.get(0).getTituloM();
                agregar=valoresDetPres+todoM;
                llenarDetallePres(agregar, tbDetalle); 
                contPresMatVis = contPresMatVis + 1;
                actLimMatVis=Integer.parseInt(vistaPres.labResLimMatVis.getText())-1;
                vistaPres.labResLimMatVis.setText(String.valueOf(actLimMatVis));
            } else {   
                agregar=listaMatVis.get(0).getClaveMatVis()+"/"+listaEjempMatVis.get(0).getIdEjemplarM()+"/"+listaMatVis.get(0).getTituloM();        
                llenarDetallePres(agregar,tbDetalle);
                contPresMatVis = contPresMatVis + 1;
                actLimMatVis=Integer.parseInt(vistaPres.labResLimMatVis.getText())-1;
                vistaPres.labResLimMatVis.setText(String.valueOf(actLimMatVis));
            }
        }
    }       
     
    public boolean validarCumTodLim() {
        boolean validar=false;
        if(vistaPres.labResLimLibro.getText().equalsIgnoreCase("0") && vistaPres.labResLimMatVis.getText().equalsIgnoreCase("0")) {
            validar=false;
        } else {
            validar=true;
        }        
        return validar;
    }
//    public boolean validarLimPres(JTable tbDetalle) throws ParseException {  
//        boolean validar=false;
//        String valores=calcularRegPres();
//        String agregar="";
//        
//        if (selectLibro == 1) {      
//            String[] prestamos=valores.split("--");
//            for(int x=0; x<prestamos.length; x++) {
//                if(prestamos[x].startsWith("LI")){
//                    contPresLib=contPresLib+1;                    
//                }                        
//            }     
//          
//            
//            if(Integer.parseInt(vistaPres.labResLimLibro.getText())<=contPresLib) {
//                JOptionPane.showMessageDialog(null, "Ya cumple con el límite de préstamos de libros");                 
//            } else {                
//                if(valores!="") {
//                    String todoL=listaLibro.get(0).getClaveLibro()+"/"+listaEjempLibro.get(0).getIdEjemplarL()+"/"+listaLibro.get(0).getTituloL();
//                    agregar=valores+todoL;
//                    llenarDetallePres(agregar, tbDetalle); 
//                    contPresLib=contPresLib+1;
//                } else {    
//                    
//                    agregar=listaLibro.get(0).getClaveLibro()+"/"+listaEjempLibro.get(0).getIdEjemplarL()+"/"+listaLibro.get(0).getTituloL();                    
//                    llenarDetallePres(agregar,tbDetalle);
//                    contPresLib=contPresLib+1;            
//                }
//                                  
//            }
//        }
//        
//        if (selectMatVis == 1) {      
//            String[] prestamos=valores.split("--");
//            for(int x=0; x<prestamos.length; x++) {
//                if(prestamos[x].startsWith("MV")){                    
//                    contPresMatVis=contPresLib+1;                    
//                }                        
//            }     
//          
//            
//            if(Integer.parseInt(vistaPres.labResLimMatVis.getText())<=contPresMatVis) {
//                JOptionPane.showMessageDialog(null, "Ya cumple con el límite de préstamos de material visual"); 
//            } else {                
//                if(valores!="") {
//                    String todoM=listaMatVis.get(0).getClaveMatVis()+"/"+listaEjempMatVis.get(0).getIdEjemplarM()+"/"+listaMatVis.get(0).getTituloM();
//                    agregar=valores+todoM;
//                    llenarDetallePres(agregar, tbDetalle); 
//                    contPresMatVis=contPresMatVis+1;
//                } else {    
//                    
//                    agregar=listaMatVis.get(0).getClaveMatVis()+"/"+listaEjempMatVis.get(0).getIdEjemplarM()+"/"+listaMatVis.get(0).getTituloM();                    
//                    llenarDetallePres(agregar,tbDetalle);
//                    contPresMatVis=contPresMatVis+1;            
//                }
//                                  
//            }
//        }
//        
//        return validar;
//    }
    
    public void llenarDetallePres(String valores, JTable tbDetalle) throws ParseException {    
        DefaultTableModel modeloDetalle = new DefaultTableModel();
        String[] filaDetalle;
        
        modeloDetalle.addColumn("Clave");
        modeloDetalle.addColumn("Ejemplar");
        modeloDetalle.addColumn("Detalle");
        tbDetalle.setModel(modeloDetalle);
       
        String[] agregar = valores.split("--"); 
                
        for(int i=0; i<agregar.length;i++) {
            filaDetalle = agregar[i].split("/");              
            modeloDetalle.addRow(filaDetalle); 
        }        
        
        validarDetPres();     
        
        vistaPres.btAgregar.setVisible(false);
        vistaPres.jpTablaPresLib.setVisible(false);
        vistaPres.jpTablaPresMaVi.setVisible(false);
    }
 
    public void validarDetPres() throws ParseException {
        
            fechaPrestamo();
            vistaPres.jpDetallePrest.setVisible(true);
            vistaPres.jpTablaPresDet.setVisible(true);
            vistaPres.spPresDetalle.setVisible(true);           
            
            
            validarFechaLim();
            vistaPres.jpPrestamo.setVisible(true);                        
            vistaPres.btRegPrestamo.setVisible(true);
        
    }
    
    public void fechaPrestamo() {
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        vistaPres.labFechaSist.setText(sdf.format(fecha));        
    }
    
    public void validarFechaLim() throws ParseException {
        RangeEvaluator evaluator = new RangeEvaluator() {
        };        
        
        Date fecha = new Date();
        Calendar fecha1 = Calendar.getInstance();
        fecha1.setTime(fecha);
        fecha1.add(Calendar.DAY_OF_YEAR, -1);
        fecha = fecha1.getTime();

        SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy");
        evaluator.setMaxSelectableDate(ff.parse(ff.format(fecha)));       

        vistaPres.calendar.getDayChooser().addDateEvaluator(evaluator);
        vistaPres.calendar.setCalendar(vistaPres.calendar.getCalendar());
    }
    
    public String selecFechaLimite(){
        int año = vistaPres.calendar.getCalendar().get(Calendar.YEAR);
        int mes = vistaPres.calendar.getCalendar().get(Calendar.MONTH) + 1;
        int dia = vistaPres.calendar.getCalendar().get(Calendar.DAY_OF_MONTH);  
        
        String fechaLimite= Integer.toString(dia)+"/"+Integer.toString(mes)+"/"+Integer.toString(año);                
        
        return fechaLimite;
    }
    
    public int crearClavePres() {
        int clavePrest=0;
        try {
            Connection acceDB = con.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("SELECT MAX(clavePrestamo) from prestamo;");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                clavePrest=rs.getInt(1);
            }
            clavePrest=clavePrest+1; 
            
        } catch (SQLException ex) {
            Logger.getLogger(controlPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clavePrest;
    }
    
    public boolean registrarPrest(int clavePrest, String fechaPrest, String fechaLimite) {
        boolean registro = false;
        if(selectAlumno==1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("INSERT INTO prestamo(clavePrestamo,fechaPrestamo,fechaLimite,alumno_claveAlumno)VALUES(?,?,?,?)");
                ps.setInt(1, clavePrest);
                ps.setString(2, fechaPrest);
                ps.setString(3, fechaLimite);
                ps.setInt(4, listaAlumno.get(0).getClaveAlumno());
                int validar = ps.executeUpdate();
                if(validar>0){
                    registro=true;
                }
                else {
                    registro=false;
                }
            } catch (SQLException ex) {            
            }
        }
        if(selectDocente==1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("INSERT INTO prestamo(clavePrestamo,fechaPrestamo,fechaLimite,docente_claveDocente)VALUES(?,?,?,?)");
                        
                ps.setInt(1, clavePrest);
                ps.setString(2, fechaPrest);
                ps.setString(3, fechaLimite);
                ps.setInt(4, listaDocente.get(0).getClaveDocente());
                int validar=ps.executeUpdate();
                if(validar>0){
                    registro=true;
                }
                else {
                    registro=false;
                }
            } catch (SQLException ex) {            
            }
        }
        return registro;
    }
    
    public boolean cambiarEstadoP(int clavePrestamo) {
        boolean registro = false;        
        String valores=recorrerDetPres();
        
        String[] prestamos=valores.split("/");
        
        for(int x=0; x<prestamos.length; x++) {
            if(prestamos[x].startsWith("LI")){
                try{ 
                    Connection acceDB = con.getConexion();
                    PreparedStatement ps = acceDB.prepareStatement("UPDATE ejemplarlibro SET estadoL='OCUPADO', prestamo_clavePrestamo="+clavePrestamo+" where ejemplarlibro.idEjemplarL='"+prestamos[x]+"';");                
                    int validar=ps.executeUpdate();
                    if(validar>0){
                        registro=true;
                    }
                    else {
                        registro=false;
                    }
                }catch(SQLException e){
                }            
            }
            if(prestamos[x].startsWith("MV")){
                try{ 
                    Connection acceDB = con.getConexion();
                    PreparedStatement ps = acceDB.prepareStatement("UPDATE ejempmatvisual SET estadoM='OCUPADO', prestamo_clavePrestamo="+clavePrestamo+" where ejempmatvisual.idEjemplarM='"+prestamos[x]+"';");                
                    int validar=ps.executeUpdate();                
                    if(validar>0){
                        registro=true;
                    }
                    else {
                        registro=false;
                    }
                }catch(SQLException e){
                }   
            }                  
        }
        return registro;
    }
    
    public boolean disminuirDispP(int clavePrestamo) {
        boolean registro = false;
        String valores=recorrerDetPres();
           
        String[] prestamos=valores.split("/");
        
        for(int x=0; x<prestamos.length; x++) {
            if(prestamos[x].startsWith("LI")){
                System.out.println("");
                try{ 
                    Connection acceDB = con.getConexion();
                    PreparedStatement ps = acceDB.prepareStatement("UPDATE libro SET disponibilidadL=disponibilidadL-1 WHERE claveLibro=(select ejemplarlibro.libro_claveLibro from ejemplarlibro where ejemplarlibro.idEjemplarL='"+prestamos[x]+"');");                
                    int validar=ps.executeUpdate();
                    if(validar>0){
                        registro=true;
                    }
                    else {
                        registro=false;
                    }
                }catch(SQLException e){
                }            
            }
            if(prestamos[x].startsWith("MV")){
                try{ 
                    Connection acceDB = con.getConexion();
                    PreparedStatement ps = acceDB.prepareStatement("UPDATE materialvisual SET disponibilidadM=disponibilidadM-1 WHERE claveMatVis=(select ejempmatvisual.materialvisual_claveMatVis from ejempmatvisual where ejempmatvisual.idEjemplarM='"+prestamos[x]+"');");                
                    ps.executeUpdate();                
                }catch(SQLException e){
                }   
            }                  
        }
        return registro;
    }
    
    public boolean disminuirLimiteP() {
        boolean registro = false;
        int noLimiteLibro=Integer.parseInt(vistaPres.labResLimLibro.getText());
        int noLimiteMatVis=Integer.parseInt(vistaPres.labResLimMatVis.getText());
        
        String valores=recorrerDetPres();
        String[] prestamos=valores.split("/");
        
        for(int x=0; x<prestamos.length; x++) {
            if(prestamos[x].startsWith("LI") && selectAlumno==1){
                System.out.println("");
                try{ 
                    Connection acceDB = con.getConexion();                    
                    PreparedStatement ps = acceDB.prepareStatement("UPDATE alumno SET limiteLibroA="+noLimiteLibro+" where claveAlumno="+listaAlumno.get(0).getClaveAlumno()+";");                
                    int validar=ps.executeUpdate();
                    if(validar>0){
                        registro=true;
                    }
                    else {
                        registro=false;
                    }
                }catch(SQLException e){
                }            
            }
            if(prestamos[x].startsWith("MV") && selectAlumno==1){
                try{ 
                    Connection acceDB = con.getConexion();                    
                    PreparedStatement ps = acceDB.prepareStatement("UPDATE alumno SET limiteMatVisA="+noLimiteMatVis+" where claveAlumno="+listaAlumno.get(0).getClaveAlumno()+";");                
                    int validar=ps.executeUpdate();                
                    if(validar>0){
                        registro=true;
                    }
                    else {
                        registro=false;
                    }
                }catch(SQLException e){
                }   
            }   
            
            if(prestamos[x].startsWith("LI") && selectDocente==1){                
                try{ 
                    Connection acceDB = con.getConexion();                    
                    PreparedStatement ps = acceDB.prepareStatement("UPDATE docente SET limiteLibroD="+noLimiteLibro+" where claveDocente="+listaDocente.get(0).getClaveDocente()+";");                
                    int validar=ps.executeUpdate();
                    if(validar>0){
                        registro=true;
                    }
                    else {
                        registro=false;
                    }
                }catch(SQLException e){
                }            
            }
            if(prestamos[x].startsWith("MV") && selectDocente==1){
                try{ 
                    Connection acceDB = con.getConexion();                    
                    PreparedStatement ps = acceDB.prepareStatement("UPDATE docente SET limiteMatVisD="+noLimiteMatVis+" where claveDocente="+listaDocente.get(0).getClaveDocente()+";");                
                    int validar=ps.executeUpdate();                
                    if(validar>0){
                        registro=true;
                    }
                    else {
                        registro=false;
                    }
                }catch(SQLException e){
                }   
            }                  
        }
        return registro;
    }
    
    public boolean aumentarSolicP() {      
        boolean registro = false;
        
        String valores=recorrerDetPres();
        String[] prestamos=valores.split("/");
        
        for(int x=0; x<prestamos.length; x++) {
            if(prestamos[x].startsWith("LI") && selectAlumno==1){
                int librosTotalA = contPresLib + listaAlumno.get(0).getLibrosSolicA();
                
                try{ 
                    Connection acceDB = con.getConexion();
                    PreparedStatement ps = acceDB.prepareStatement("UPDATE alumno SET librosSolicA="+librosTotalA+" where claveAlumno="+listaAlumno.get(0).getClaveAlumno()+";");                
                    int validar=ps.executeUpdate();
                    if(validar>0){
                        registro=true;
                    }
                    else {
                        registro=false;
                    }
                }catch(SQLException e){
                }            
            }
            if(prestamos[x].startsWith("MV") && selectAlumno==1){
                int matVisTotalA = contPresMatVis + listaAlumno.get(0).getMatVisSolicA();
                try{ 
                    Connection acceDB = con.getConexion();
                    PreparedStatement ps = acceDB.prepareStatement("UPDATE alumno SET matVisSolicA="+matVisTotalA+" where claveAlumno="+listaAlumno.get(0).getClaveAlumno()+";");                
                    int validar=ps.executeUpdate();                
                    if(validar>0){
                        registro=true;
                    }
                    else {
                        registro=false;
                    }
                }catch(SQLException e){
                }   
            }   
            
            if(prestamos[x].startsWith("LI") && selectDocente==1){
                int librosTotalD = contPresLib + listaDocente.get(0).getLibrosSolicD();
                try{ 
                    Connection acceDB = con.getConexion();
                    PreparedStatement ps = acceDB.prepareStatement("UPDATE docente SET librosSolicD="+librosTotalD+" where claveDocente="+listaDocente.get(0).getClaveDocente()+";");                
                    int validar=ps.executeUpdate();
                    if(validar>0){
                        registro=true;
                    }
                    else {
                        registro=false;
                    }
                }catch(SQLException e){
                }            
            }
            if(prestamos[x].startsWith("MV") && selectDocente==1){
                int matVisTotalD = contPresMatVis + listaDocente.get(0).getLibrosSolicD();
                try{ 
                    Connection acceDB = con.getConexion();
                    PreparedStatement ps = acceDB.prepareStatement("UPDATE docente SET matVisSolicD="+matVisTotalD+" where claveDocente="+listaDocente.get(0).getClaveDocente()+";");                
                    int validar=ps.executeUpdate();                
                    if(validar>0){
                        registro=true;
                    }
                    else {
                        registro=false;
                    }
                }catch(SQLException e){
                }   
            }                  
        }        
        return registro;
    }
    
    public boolean regHistorialP(int clavePrest,String fechaPrest,String fechaLimite) {       
        boolean registro=false;
        String ejemplar = "";
        String detalle = "";
        String[] detallePrestamos;       
        int fila = vistaPres.tbPresDetalle.getRowCount();        
        String filaDetalle[] = null;
        String valores="";
        for (int i = 0; i < fila; i++) {
            String valor = (String) vistaPres.tbPresDetalle.getValueAt(i, 1)+"--"+vistaPres.tbPresDetalle.getValueAt(i, 2);            
            valores += valor+"/";
        }          
        String[] prestamos = valores.split("/");                 
        for(int x=0; x<prestamos.length; x++) {            
            if(prestamos[x].split("--")!=null) {
                detallePrestamos=prestamos[x].split("--");
                ejemplar = detallePrestamos[0];
                detalle = detallePrestamos[1];
                
                if(selectAlumno==1){
                    if(ejemplar.startsWith("LIB")) {
                        try {
                            Connection acceDB = con.getConexion();
                            PreparedStatement ps = acceDB.prepareStatement("INSERT INTO historial(prestamo_clavePrestamo, ejemplarlibro_idEjemplarL)VALUES(?,?)");
                            ps.setInt(1, clavePrest);
                            ps.setString(2, ejemplar);
                            int validar = ps.executeUpdate();
                            if(validar>0){
                                registro=true;
                            }
                            else {
                                registro=false;
                            }
                        }catch(SQLException ex) {            
                        }     
                    }
                    if(ejemplar.startsWith("MV")) {
                        try {
                            Connection acceDB = con.getConexion();
                            PreparedStatement ps = acceDB.prepareStatement("INSERT INTO historial(prestamo_clavePrestamo,ejempmatvisual_idEjemplarM)VALUES(?,?)");
                            ps.setInt(1, clavePrest);
                            ps.setString(2, ejemplar);
                            int validar = ps.executeUpdate();
                            if(validar>0){
                                registro=true;
                            }
                            else {
                                registro=false;
                            }
                        }catch(SQLException ex) {            
                        }     
                    }
                }
                
                if(selectDocente==1) {
                    if(ejemplar.startsWith("LIB")) {
                        try {                        
                            Connection acceDB = con.getConexion();
                            PreparedStatement ps = acceDB.prepareStatement("INSERT INTO historial(prestamo_clavePrestamo,ejemplarlibro_idEjemplarL)VALUES(?,?)");
                            ps.setInt(1, clavePrest);   
                            ps.setString(2, ejemplar);
                            int validar = ps.executeUpdate();
                            if(validar>0){
                                registro=true;
                            } else {
                                registro=false;
                            }
                        }catch(SQLException ex) {            
                        }
                    }
                    
                    if(ejemplar.startsWith("MV")) {
                        try {                        
                            Connection acceDB = con.getConexion();
                            PreparedStatement ps = acceDB.prepareStatement("INSERT INTO historial(prestamo_clavePrestamo,ejempmatvisual_idEjemplarM)VALUES(?,?)");
                            ps.setInt(1, clavePrest);   
                            ps.setString(2, ejemplar);
                            int validar = ps.executeUpdate();
                            if(validar>0){
                                registro=true;
                            } else {
                                registro=false;
                            }
                        }catch(SQLException ex) {            
                        }
                    }
                    
                }
            }
        }   
        
        return registro;
    }

    public void regPrestamo (String fechaPrest, String fechaLimite) {
        int clavePrest = crearClavePres();        
        boolean b1=registrarPrest(clavePrest, fechaPrest, fechaLimite);
        boolean b2=cambiarEstadoP(clavePrest);
        boolean b3=disminuirDispP(clavePrest);
        boolean b4=disminuirLimiteP();
        boolean b5=aumentarSolicP(); 
        boolean b6=regHistorialP(clavePrest, fechaPrest, fechaLimite);
    }
}

