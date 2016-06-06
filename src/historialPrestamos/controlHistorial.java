/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historialPrestamos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
import mantenimientoSolicitantes.alumno;
import mantenimientoSolicitantes.docente;
import org.freixas.jcalendar.JCalendarCombo;
import principal.conexion;
import sun.util.calendar.LocalGregorianCalendar;

/**
 *
 * @author Mac
 */
public class controlHistorial {
    conexion con = new conexion();
    interfazHistorial vistaHist;
    ArrayList<historial> listaHistorial;
    historial hist;
    
    public controlHistorial(interfazHistorial vistaHist) {
        this.vistaHist = vistaHist;
    }
    
    public void buscarHistGral (String fechaDesde, String fechaHasta) {        
        historial hist;
        listaHistorial = new ArrayList<historial>();
        try {
            Connection acceDB = con.getConexion();           
            PreparedStatement ps = acceDB.prepareStatement("SELECT \n" +
            "	case when a.noControlA is null \n" +
            "			then d.noControlD \n" +
            "		 when d.noControlD is null\n" +
            "			then a.noControlA\n" +
            "		end\n" +
            "		as noControl,\n" +
            "	case when a.noControlA is not null\n" +
            "			then a.gradoA	\n" +
            "		 when d.noControlD is not null\n" +
            "			then ' '\n" +
            "		end\n" +
            "		as grado,		\n" +
            "	case when h.ejemplarlibro_idEjemplarL is not null\n" +
            "			then h.ejemplarlibro_idEjemplarL\n" +
            "		 when h.ejempmatvisual_idEjemplarM is not null\n" +
            "			then h.ejempmatvisual_idEjemplarM\n" +
            "		end\n" +
            "		as ejemplar,\n" +
            "	case when h.ejemplarlibro_idEjemplarL is not null\n" +
            "			then CONCAT(l.tituloL, '-' ,l.añoL)\n" +
            "		 when h.ejempmatvisual_idEjemplarM is not null\n" +
            "			then CONCAT(m.tituloM, '-' ,m.añoM)\n" +
            "		end\n" +
            "		as detalle,	\n" +
            "	DATE_FORMAT(STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y'),'%d/%m/%y') as fechaPrestamo,\n" +
            "	DATE_FORMAT(STR_TO_DATE(p.fechaLimite, '%d/%m/%Y'), '%d/%m/%Y') as fechaLimite,\n" +
            "	case when devolucion_claveDevolucion is not null\n" +
            "			then DATE_FORMAT(STR_TO_DATE(de.fechaDevolucion, '%d/%m/%Y'), '%d/%m/%Y')\n" +
            "			else\n" +
            "				' '		\n" +
            "		end\n" +
            "		as fechaDevolucion \n" +
            "\n" +
            " FROM bibliothek.historial as h\n" +
            "	left join bibliothek.ejemplarlibro as el on h.ejemplarlibro_idEjemplarL = el.idEjemplarL\n" +
            "	left join bibliothek.libro as l on l.claveLibro = el.libro_claveLibro\n" +
            "	left join bibliothek.ejempmatvisual as em on h.ejempmatvisual_idEjemplarM = em.idEjemplarM\n" +
            "	left join bibliothek.materialvisual as m on m.claveMatVis = em.materialvisual_claveMatVis\n" +
            "	left join bibliothek.prestamo as p on h.prestamo_clavePrestamo = clavePrestamo\n" +
            "	left join bibliothek.alumno as a on p.alumno_claveAlumno = a.claveAlumno\n" +
            "	left join bibliothek.docente as d on p.docente_claveDocente = d.claveDocente\n" +
            "	left join bibliothek.devolucion as de on h.devolucion_claveDevolucion = de.claveDevolucion\n" +
            "\n" +
            "	where		\n" +
            "		STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') >= STR_TO_DATE('"+fechaDesde+"', '%d/%m/%Y')\n" +
            "		and STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') <= STR_TO_DATE('"+fechaHasta+"', '%d/%m/%Y')\n" +
            ";");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                hist = new historial();
                hist.setNoControl(rs.getString(1));
                hist.setGrado(rs.getString(2));
                hist.setEjemplar(rs.getString(3));
                hist.setDetalle(rs.getString(4));              
                hist.setFechaPrestamo(rs.getString(5));
                hist.setFechaLimite(rs.getString(6));
                hist.setFechaDevolucion(rs.getString(7));
                listaHistorial.add(hist);
            }
        }catch(Exception e) {            
        }   
    }
    
    public void mostrarHistGral(JTable tablaHistorial) {
        DefaultTableModel modeloHistorial = new DefaultTableModel();
        tablaHistorial.setModel(modeloHistorial);
        modeloHistorial.fireTableDataChanged();
        tablaHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        modeloHistorial.addColumn("noControl");
        modeloHistorial.addColumn("grado");
        modeloHistorial.addColumn("ejemplar");
        modeloHistorial.addColumn("detalle");
        modeloHistorial.addColumn("fechaPréstamo");
        modeloHistorial.addColumn("fechaLímite");
        modeloHistorial.addColumn("fechaDevolución");
        
        Object[] columna = new Object[11];
        
        for(int x=0; x<listaHistorial.size(); x++) {
            columna[0] = listaHistorial.get(x).getNoControl();
            columna[1] = listaHistorial.get(x).getGrado();
            columna[2] = listaHistorial.get(x).getEjemplar();
            columna[3] = listaHistorial.get(x).getDetalle();
            columna[4] = listaHistorial.get(x).getFechaPrestamo();
            columna[5] = listaHistorial.get(x).getFechaLimite();
            columna[6] = listaHistorial.get(x).getFechaDevolucion();
            modeloHistorial.addRow(columna);
        }
    }    
    
    public void buscarHistLib(String fechaDesde, String fechaHasta) {        
        historial hist;
        listaHistorial = new ArrayList<historial>();             
//        Date fecha = new Date();
//        SimpleDateFormat fechaAct = new SimpleDateFormat("dd/MM/yyyy");
//        String fechaAc=(fechaAct.format(fecha));  
        try {
            Connection acceDB = con.getConexion();           
            PreparedStatement ps = acceDB.prepareStatement("SELECT \n" +
            "	case when a.noControlA is null \n" +
            "			then d.noControlD \n" +
            "		 when d.noControlD is null\n" +
            "			then a.noControlA\n" +
            "		end\n" +
            "		as noControl,\n" +
            "	case when a.noControlA is not null\n" +
            "			then a.gradoA	\n" +
            "		 when d.noControlD is not null\n" +
            "			then ' '\n" +
            "		end\n" +
            "		as grado,		\n" +
            "	h.ejemplarlibro_idEjemplarL as ejemplar,\n" +
            "	CONCAT(l.tituloL, '-' ,l.añoL) as detalle,	\n" +
            "	DATE_FORMAT(STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y'),'%d/%m/%Y') as fechaPrestamo,\n" +
            "	DATE_FORMAT(STR_TO_DATE(p.fechaLimite, '%d/%m/%Y'),'%d/%m/%Y') as fechaLimite,\n" +
            "	case when devolucion_claveDevolucion is not null\n" +
            "			then DATE_FORMAT(STR_TO_DATE(de.fechaDevolucion, '%d/%m/%Y'),'%d/%m/%y')\n" +
            "			else\n" +
            "				' '		\n" +
            "		end\n" +
            "		as fechaDevolucion \n" +
            "\n" +
            " FROM bibliothek.historial as h\n" +
            "	left join bibliothek.ejemplarlibro as el on h.ejemplarlibro_idEjemplarL = el.idEjemplarL\n" +
            "	left join bibliothek.libro as l on l.claveLibro = el.libro_claveLibro	\n" +
            "	left join bibliothek.prestamo as p on h.prestamo_clavePrestamo = clavePrestamo\n" +
            "	left join bibliothek.alumno as a on p.alumno_claveAlumno = a.claveAlumno\n" +
            "	left join bibliothek.docente as d on p.docente_claveDocente = d.claveDocente\n" +
            "	left join bibliothek.devolucion as de on h.devolucion_claveDevolucion = de.claveDevolucion\n" +
            "\n" +
            "	where		\n" +
            "		h.ejemplarlibro_idEjemplarL is not null\n" +
            "		and STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') >= STR_TO_DATE('"+fechaDesde+"', '%d/%m/%Y')\n" +
            "		and STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') <= STR_TO_DATE('"+fechaHasta+"', '%d/%m/%Y')\n" +
            ";");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                hist = new historial();
                hist.setNoControl(rs.getString(1));
                hist.setGrado(rs.getString(2));
                hist.setEjemplar(rs.getString(3));
                hist.setDetalle(rs.getString(4));              
                hist.setFechaPrestamo(rs.getString(5));
                hist.setFechaLimite(rs.getString(6));
                hist.setFechaDevolucion(rs.getString(7));
                listaHistorial.add(hist);
            }
        }catch(Exception e) {            
        }   
    }
    
    public void mostrarHistLib(JTable tablaHistorial) {
        DefaultTableModel modeloHistorial = new DefaultTableModel();
        tablaHistorial.setModel(modeloHistorial);
        modeloHistorial.fireTableDataChanged();
        tablaHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        modeloHistorial.addColumn("noControl");
        modeloHistorial.addColumn("grado");
        modeloHistorial.addColumn("ejemplar");
        modeloHistorial.addColumn("detalle");
        modeloHistorial.addColumn("fechaPréstamo");
        modeloHistorial.addColumn("fechaLímite");
        modeloHistorial.addColumn("fechaDevolución");
        
        Object[] columna = new Object[11];
        
        for(int x=0; x<listaHistorial.size(); x++) {
            columna[0] = listaHistorial.get(x).getNoControl();
            columna[1] = listaHistorial.get(x).getGrado();
            columna[2] = listaHistorial.get(x).getEjemplar();
            columna[3] = listaHistorial.get(x).getDetalle();
            columna[4] = listaHistorial.get(x).getFechaPrestamo();
            columna[5] = listaHistorial.get(x).getFechaLimite();
            columna[6] = listaHistorial.get(x).getFechaDevolucion();
            modeloHistorial.addRow(columna);
        }
    }    
    
    public void buscarHistMatVis(String fechaDesde, String fechaHasta) {        
        historial hist;
        listaHistorial = new ArrayList<historial>();                   
        try {
            Connection acceDB = con.getConexion();           
            PreparedStatement ps = acceDB.prepareStatement("SELECT \n" +
            "	case when a.noControlA is null \n" +
            "			then d.noControlD \n" +
            "		 when d.noControlD is null\n" +
            "			then a.noControlA\n" +
            "		end\n" +
            "		as noControl,\n" +
            "	case when a.noControlA is not null\n" +
            "			then a.gradoA	\n" +
            "		 when d.noControlD is not null\n" +
            "			then 'N/A'\n" +
            "		end\n" +
            "		as grado,		\n" +
            "	h.ejempmatvisual_idEjemplarM as ejemplar,\n" +
            "	CONCAT(m.tituloM, '-' ,m.añoM) as detalle,	\n" +
            "	DATE_FORMAT(STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y'),'%d/%m/%Y') as fechaPrestamo,\n" +
            "	DATE_FORMAT(STR_TO_DATE(p.fechaLimite, '%d/%m/%Y'),'%d/%m/%Y') as fechaLimite,\n" +
            "	case when devolucion_claveDevolucion is not null\n" +
            "			then DATE_FORMAT(STR_TO_DATE(de.fechaDevolucion, '%d/%m/%Y'),'%d/%m/%y')\n" +
            "			else\n" +
            "				' '		\n" +
            "		end\n" +
            "		as fechaDevolucion \n" +
            "\n" +
            " FROM bibliothek.historial as h\n" +
            "	left join bibliothek.ejempmatvisual as em on h.ejempmatvisual_idEjemplarM = em.idEjemplarM\n" +
            "	left join bibliothek.materialvisual as m on m.claveMatVis = em.materialvisual_claveMatVis\n" +
            "	left join bibliothek.prestamo as p on h.prestamo_clavePrestamo = clavePrestamo\n" +
            "	left join bibliothek.alumno as a on p.alumno_claveAlumno = a.claveAlumno\n" +
            "	left join bibliothek.docente as d on p.docente_claveDocente = d.claveDocente\n" +
            "	left join bibliothek.devolucion as de on h.devolucion_claveDevolucion = de.claveDevolucion\n" +
            "\n" +
            "	where		\n" +
            "		h.ejempmatvisual_idEjemplarM is not null\n" +
            "		and STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') >= STR_TO_DATE('"+fechaDesde+"', '%d/%m/%Y')\n" +
            "		and STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') <= STR_TO_DATE('"+fechaHasta+"', '%d/%m/%Y')\n" +
            ";");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                hist = new historial();
                hist.setNoControl(rs.getString(1));
                hist.setGrado(rs.getString(2));
                hist.setEjemplar(rs.getString(3));
                hist.setDetalle(rs.getString(4));              
                hist.setFechaPrestamo(rs.getString(5));
                hist.setFechaLimite(rs.getString(6));
                hist.setFechaDevolucion(rs.getString(7));
                listaHistorial.add(hist);
            }
        }catch(Exception e) {            
        }   
    }
    
    public void filtrarHistGral(String noControl,String grado,String ejemplar, String fechaPrestamo, String fechaLimite, String fechaDev, JTable tablaHistorial){                  
        listaHistorial = new ArrayList<historial>();             
        String bu="";        
        int ban=0;
        String fechaDesde = selecFechaDesde();
        String fechaHasta = selecFechaHasta();
        
        try{         
            Connection acceDB = con.getConexion();  
            if(noControl.equalsIgnoreCase("")==false){    
                bu+= "and ( noControlA like '"+noControl+"%' or noControlD like '"+noControl+"%')";                                                
            }            
            if(grado.equalsIgnoreCase("")==false){                    
                bu+=" and ";
                bu+= " gradoA like '"+grado+"%'";                                 
            }
            
            if(ejemplar.equalsIgnoreCase("")==false){                      
                bu+=" and ";
                bu+= " (idEjemplarL like '"+ejemplar+"%' or idEjemplarM like '"+ejemplar+"%')";                                                 
            }
            
            if(fechaPrestamo.equalsIgnoreCase("")==false){                                              
                bu+=" and ";
                bu+= " fechaPrestamo like '"+fechaPrestamo+"%'";                                 
            }
            
            if(fechaLimite.equalsIgnoreCase("")==false){                                              
                bu+=" and ";
                bu+= " fechaLimite like '"+fechaLimite+"%'";                                 
            }
            
            if(fechaDev.equalsIgnoreCase("")==false){                              
                bu+=" and ";
                bu+= " fechaDevolucion like '"+fechaDev+"%'";                                 
            }
                    
            PreparedStatement ps = acceDB.prepareStatement("SELECT \n" +
            "	case when a.noControlA is null \n" +
            "			then d.noControlD \n" +
            "		 when d.noControlD is null\n" +
            "			then a.noControlA\n" +
            "	end\n" +
            "	as noControl,\n" +
            "	\n" +
            "	case when a.noControlA is not null\n" +
            "			then a.gradoA	\n" +
            "		when d.noControlD is not null\n" +
            "			then ' '\n" +
            "	end\n" +
            "	as grado,		\n" +
            "	case when h.ejemplarlibro_idEjemplarL is not null\n" +
            "			then h.ejemplarlibro_idEjemplarL\n" +
            "		 when h.ejempmatvisual_idEjemplarM is not null\n" +
            "			then h.ejempmatvisual_idEjemplarM\n" +
            "	end\n" +
            "	as ejemplar,\n" +
            "	case when h.ejemplarlibro_idEjemplarL is not null\n" +
            "			then CONCAT(l.tituloL, '-' ,l.añoL)\n" +
            "     	 when h.ejempmatvisual_idEjemplarM is not null\n" +
            "			then CONCAT(m.tituloM, '-' ,m.añoM)\n" +
            "	end\n" +
            "	as detalle,	\n" +
            "\n" +
            "	DATE_FORMAT(STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y'),'%d/%m/%Y') as fechaPrestamo,\n" +
            "	DATE_FORMAT(STR_TO_DATE(p.fechaLimite, '%d/%m/%Y'),'%d/%m/%Y') as fechaLimite,\n" +
            "	case when devolucion_claveDevolucion is not null\n" +
            "    		then DATE_FORMAT(STR_TO_DATE(de.fechaDevolucion, '%d/%m/%Y'),'%d/%m/%y')\n" +
            "		else\n" +
            "				' '		\n" +
            "		end\n" +
            "		as fechaDevolucion \n" +
            "\n" +
            "	FROM bibliothek.historial as h\n" +
            "	left join bibliothek.ejemplarlibro as el on h.ejemplarlibro_idEjemplarL = el.idEjemplarL\n" +
            "	left join bibliothek.libro as l on l.claveLibro = el.libro_claveLibro\n" +
            "    left join bibliothek.ejempmatvisual as em on h.ejempmatvisual_idEjemplarM = em.idEjemplarM\n" +
            "	left join bibliothek.materialvisual as m on m.claveMatVis = em.materialvisual_claveMatVis\n" +
            "	left join bibliothek.prestamo as p on h.prestamo_clavePrestamo = clavePrestamo\n" +
            "	left join bibliothek.alumno as a on p.alumno_claveAlumno = a.claveAlumno\n" +
            "	left join bibliothek.docente as d on p.docente_claveDocente = d.claveDocente\n" +
            "	left join bibliothek.devolucion as de on h.devolucion_claveDevolucion = de.claveDevolucion\n" +
            "\n" +
            "	where		\n" +
            "		DATE_FORMAT(STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y'),'%d/%m/%Y') >= DATE_FORMAT(STR_TO_DATE('"+fechaDesde+"', '%d/%m/%Y'),'%d/%m/%Y')\n" +
            "		and DATE_FORMAT(STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y'),'%d/%m/%Y') <= DATE_FORMAT(STR_TO_DATE('"+fechaHasta+"', '%d/%m/%Y'),'%d/%m/%Y')"+bu+
            ";");
            ResultSet rs = ps.executeQuery();        
            
            while(rs.next()){
                hist = new historial();
                hist.setNoControl(rs.getString(1));                
                hist.setGrado(rs.getString(2));
                hist.setEjemplar(rs.getString(3));
                hist.setDetalle(rs.getString(4));              
                hist.setFechaPrestamo(rs.getString(5));
                hist.setFechaLimite(rs.getString(6));
                hist.setFechaDevolucion(rs.getString(7));
                listaHistorial.add(hist);
            }
            
         }catch(SQLException e){
         
         }
        DefaultTableModel modeloHistorial = new DefaultTableModel();
        tablaHistorial.setModel(modeloHistorial);
        modeloHistorial.fireTableDataChanged();
        tablaHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        modeloHistorial.addColumn("noControl");
        modeloHistorial.addColumn("grado");
        modeloHistorial.addColumn("ejemplar");
        modeloHistorial.addColumn("detalle");
        modeloHistorial.addColumn("fechaPréstamo");
        modeloHistorial.addColumn("fechaLímite");
        modeloHistorial.addColumn("fechaDevolución");
        
        Object[] columna = new Object[11];
        
        for(int x=0; x<listaHistorial.size(); x++) {
            columna[0] = listaHistorial.get(x).getNoControl();
            columna[1] = listaHistorial.get(x).getGrado();
            columna[2] = listaHistorial.get(x).getEjemplar();
            columna[3] = listaHistorial.get(x).getDetalle();
            columna[4] = listaHistorial.get(x).getFechaPrestamo();
            columna[5] = listaHistorial.get(x).getFechaLimite();
            columna[6] = listaHistorial.get(x).getFechaDevolucion();
            modeloHistorial.addRow(columna);
        }
    }
    
    public void filtrarHistLib(String noControl,String grado,String ejemplar, String fechaPrestamo, String fechaLimite, String fechaDev, JTable tablaHistorial){                  
        listaHistorial = new ArrayList<historial>();             
        String fechaDesde = selecFechaDesde();
        String fechaHasta = selecFechaHasta();
        String bu="";        
        int ban=0;
        
        try{         
            Connection acceDB = con.getConexion();              
            
            if(noControl.equalsIgnoreCase("")==false){    
                bu+= " and (noControlA like '"+noControl+"%' or noControlD like '"+noControl+"%')";                                                
            }            
            if(grado.equalsIgnoreCase("")==false){                   
                bu+= " and gradoA like '"+grado+"%'";                                 
            }
            
            if(ejemplar.equalsIgnoreCase("")==false){      
                bu+= " and idEjemplarL like '"+ejemplar+"%'";                                                 
            }
            System.out.println(bu);
                        
            if(fechaPrestamo.equalsIgnoreCase("")==false){                              
                bu+= " and fechaPrestamo like '"+fechaPrestamo+"%'";                                             
            }
            
            if(fechaLimite.equalsIgnoreCase("")==false){                             
                bu+= " and fechaLimite like '"+fechaLimite+"%'";                 
            }
            
            if(fechaDev.equalsIgnoreCase("")==false){                        
                bu+= " and fechaDevolucion like '"+fechaDev+"%'";                
            }
                        
            PreparedStatement ps = acceDB.prepareStatement("SELECT \n" +
            "	case when a.noControlA is null \n" +
            "			then d.noControlD \n" +
            "		 when d.noControlD is null\n" +
            "			then a.noControlA\n" +
            "		end\n" +
            "		as noControl,\n" +
            "	case when a.noControlA is not null\n" +
            "			then a.gradoA	\n" +
            "		 when d.noControlD is not null\n" +
            "			then ' '\n" +
            "		end\n" +
            "		as grado,		\n" +
            "	h.ejemplarlibro_idEjemplarL as ejemplar,\n" +
            "	CONCAT(l.tituloL, '-' ,l.añoL) as detalle,	\n" +
            "	DATE_FORMAT(STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y'),'%d/%m/%Y') as fechaPrestamo,\n" +
            "	DATE_FORMAT(STR_TO_DATE(p.fechaLimite, '%d/%m/%Y'),'%d/%m/%Y') as fechaLimite,\n" +
            "	case when devolucion_claveDevolucion is not null\n" +
            "			then DATE_FORMAT(STR_TO_DATE(de.fechaDevolucion, '%d/%m/%Y'),'%d/%m/%y')\n" +
            "			else\n" +
            "				' '		\n" +
            "		end\n" +
            "		as fechaDevolucion \n" +
            "\n" +
            " FROM bibliothek.historial as h\n" +
            "	left join bibliothek.ejemplarlibro as el on h.ejemplarlibro_idEjemplarL = el.idEjemplarL\n" +
            "	left join bibliothek.libro as l on l.claveLibro = el.libro_claveLibro	\n" +
            "	left join bibliothek.prestamo as p on h.prestamo_clavePrestamo = clavePrestamo\n" +
            "	left join bibliothek.alumno as a on p.alumno_claveAlumno = a.claveAlumno\n" +
            "	left join bibliothek.docente as d on p.docente_claveDocente = d.claveDocente\n" +
            "	left join bibliothek.devolucion as de on h.devolucion_claveDevolucion = de.claveDevolucion\n" +
            "\n" +
            "	where		\n" +
            "		h.ejemplarlibro_idEjemplarL is not null and DATE_FORMAT(STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y'),'%d/%m/%Y') >= DATE_FORMAT(STR_TO_DATE('"+fechaDesde+"', '%d/%m/%Y'),'%d/%m/%Y')\n" +
            "		and DATE_FORMAT(STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y'),'%d/%m/%Y') <= DATE_FORMAT(STR_TO_DATE('"+fechaHasta+"', '%d/%m/%Y'),'%d/%m/%Y')\n" +
            bu+";" );
            ResultSet rs = ps.executeQuery();        
            
            while(rs.next()){
                hist = new historial();
                hist.setNoControl(rs.getString(1));                
                hist.setGrado(rs.getString(2));
                hist.setEjemplar(rs.getString(3));
                hist.setDetalle(rs.getString(4));              
                hist.setFechaPrestamo(rs.getString(5));
                hist.setFechaLimite(rs.getString(6));
                hist.setFechaDevolucion(rs.getString(7));
                listaHistorial.add(hist);
            }
            
         }catch(SQLException e){
         
         }
        DefaultTableModel modeloHistorial = new DefaultTableModel();
        tablaHistorial.setModel(modeloHistorial);
        modeloHistorial.fireTableDataChanged();
        tablaHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        modeloHistorial.addColumn("noControl");
        modeloHistorial.addColumn("grado");
        modeloHistorial.addColumn("ejemplar");
        modeloHistorial.addColumn("detalle");
        modeloHistorial.addColumn("fechaPréstamo");
        modeloHistorial.addColumn("fechaLímite");
        modeloHistorial.addColumn("fechaDevolución");
        
        Object[] columna = new Object[11];
        
        for(int x=0; x<listaHistorial.size(); x++) {
            columna[0] = listaHistorial.get(x).getNoControl();
            columna[1] = listaHistorial.get(x).getGrado();
            columna[2] = listaHistorial.get(x).getEjemplar();
            columna[3] = listaHistorial.get(x).getDetalle();
            columna[4] = listaHistorial.get(x).getFechaPrestamo();
            columna[5] = listaHistorial.get(x).getFechaLimite();
            columna[6] = listaHistorial.get(x).getFechaDevolucion();
            modeloHistorial.addRow(columna);
        }
    }
    
    public void filtrarHistMaVi(String noControl,String grado,String ejemplar, String fechaPrestamo, String fechaLimite, String fechaDev, JTable tablaHistorial){                  
        listaHistorial = new ArrayList<historial>();             
        String fechaDesde = selecFechaDesde();
        String fechaHasta = selecFechaHasta();
        String bu="";        
        int ban=0;
        
        try{         
            Connection acceDB = con.getConexion();              
            
            if(noControl.equalsIgnoreCase("")==false){    
                bu+= " and (noControlA like '"+noControl+"%' or noControlD like '"+noControl+"%')";                                                
            }            
            if(grado.equalsIgnoreCase("")==false){                   
                bu+= " and gradoA like '"+grado+"%'";                                 
            }
            
            if(ejemplar.equalsIgnoreCase("")==false){      
                bu+= " and idEjemplarM like '"+ejemplar+"%'";                                                 
            }
            System.out.println(bu);
                        
            if(fechaPrestamo.equalsIgnoreCase("")==false){                              
                bu+= " and fechaPrestamo like '"+fechaPrestamo+"%'";                                             
            }
            
            if(fechaLimite.equalsIgnoreCase("")==false){                             
                bu+= " and fechaLimite like '"+fechaLimite+"%'";                 
            }
            
            if(fechaDev.equalsIgnoreCase("")==false){                        
                bu+= " and fechaDevolucion like '"+fechaDev+"%'";                
            }
                        
            PreparedStatement ps = acceDB.prepareStatement("SELECT \n" +
            "	case when a.noControlA is null \n" +
            "			then d.noControlD \n" +
            "		 when d.noControlD is null\n" +
            "			then a.noControlA\n" +
            "		end\n" +
            "		as noControl,\n" +
            "	case when a.noControlA is not null\n" +
            "			then a.gradoA	\n" +
            "		 when d.noControlD is not null\n" +
            "			then 'N/A'\n" +
            "		end\n" +
            "		as grado,		\n" +
            "	h.ejempmatvisual_idEjemplarM as ejemplar,\n" +
            "	CONCAT(m.tituloM, '-' ,m.añoM) as detalle,	\n" +
            "	DATE_FORMAT(STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y'),'%d/%m/%Y') as fechaPrestamo,\n" +
            "	DATE_FORMAT(STR_TO_DATE(p.fechaLimite, '%d/%m/%Y'),'%d/%m/%Y') as fechaLimite,\n" +
            "	case when devolucion_claveDevolucion is not null\n" +
            "			then DATE_FORMAT(STR_TO_DATE(de.fechaDevolucion, '%d/%m/%Y'),'%d/%m/%y')\n" +
            "			else\n" +
            "				'N/A'		\n" +
            "		end\n" +
            "		as fechaDevolucion \n" +
            "\n" +
            " FROM bibliothek.historial as h\n" +
            "	left join bibliothek.ejempmatvisual as em on h.ejempmatvisual_idEjemplarM = em.idEjemplarM\n" +
            "	left join bibliothek.materialvisual as m on m.claveMatVis = em.materialvisual_claveMatVis\n" +
            "	left join bibliothek.prestamo as p on h.prestamo_clavePrestamo = clavePrestamo\n" +
            "	left join bibliothek.alumno as a on p.alumno_claveAlumno = a.claveAlumno\n" +
            "	left join bibliothek.docente as d on p.docente_claveDocente = d.claveDocente\n" +
            "	left join bibliothek.devolucion as de on h.devolucion_claveDevolucion = de.claveDevolucion\n" +
            "\n" +
            "	where		\n" +
            "		h.ejempmatvisual_idEjemplarM is not null and DATE_FORMAT(STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y'),'%d/%m/%Y') >= DATE_FORMAT(STR_TO_DATE('"+fechaDesde+"', '%d/%m/%Y'),'%d/%m/%Y')\n" +
            "		and DATE_FORMAT(STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y'),'%d/%m/%Y') <= DATE_FORMAT(STR_TO_DATE('"+fechaHasta+"', '%d/%m/%Y'),'%d/%m/%Y')"+bu+
            ";");
            ResultSet rs = ps.executeQuery();        
            
            while(rs.next()){
                hist = new historial();
                hist.setNoControl(rs.getString(1));                
                hist.setGrado(rs.getString(2));
                hist.setEjemplar(rs.getString(3));
                hist.setDetalle(rs.getString(4));              
                hist.setFechaPrestamo(rs.getString(5));
                hist.setFechaLimite(rs.getString(6));
                hist.setFechaDevolucion(rs.getString(7));
                listaHistorial.add(hist);
            }
            
         }catch(SQLException e){
         
         }
        DefaultTableModel modeloHistorial = new DefaultTableModel();
        tablaHistorial.setModel(modeloHistorial);
        modeloHistorial.fireTableDataChanged();
        tablaHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        modeloHistorial.addColumn("noControl");
        modeloHistorial.addColumn("grado");
        modeloHistorial.addColumn("ejemplar");
        modeloHistorial.addColumn("detalle");
        modeloHistorial.addColumn("fechaPréstamo");
        modeloHistorial.addColumn("fechaLímite");
        modeloHistorial.addColumn("fechaDevolución");
        
        Object[] columna = new Object[11];
        
        for(int x=0; x<listaHistorial.size(); x++) {
            columna[0] = listaHistorial.get(x).getNoControl();
            columna[1] = listaHistorial.get(x).getGrado();
            columna[2] = listaHistorial.get(x).getEjemplar();
            columna[3] = listaHistorial.get(x).getDetalle();
            columna[4] = listaHistorial.get(x).getFechaPrestamo();
            columna[5] = listaHistorial.get(x).getFechaLimite();
            columna[6] = listaHistorial.get(x).getFechaDevolucion();
            modeloHistorial.addRow(columna);
        }
    }
    
    public DefaultTableModel limpiarTablas(){  
        DefaultTableModel modelo = null;
        try {
            //Limpiar tabla historial General
            modelo=(DefaultTableModel) vistaHist.tbHistGeneral.getModel();
            int filasGral=vistaHist.tbHistGeneral.getRowCount();
            for (int i = 0;filasGral>i; i++) {
                modelo.removeRow(0);
            }
            
            //Limpiar tabla historial Libros
            modelo=(DefaultTableModel) vistaHist.tbHistLibros.getModel();
            int filasLib=vistaHist.tbHistLibros.getRowCount();
            for (int i = 0;filasLib>i; i++) {
                modelo.removeRow(0);
            }
            
            //Limpiar tabla historial Material Visual
            modelo=(DefaultTableModel) vistaHist.tbHistMatVis.getModel();
            int filas=vistaHist.tbHistMatVis.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
        return modelo;
    }
    
    
    public void mostrarHistMatVis(JTable tablaHistorial) {
        DefaultTableModel modeloHistorial = new DefaultTableModel();
        tablaHistorial.setModel(modeloHistorial);
        modeloHistorial.fireTableDataChanged();
        tablaHistorial.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        modeloHistorial.addColumn("noControl");
        modeloHistorial.addColumn("grado");
        modeloHistorial.addColumn("ejemplar");
        modeloHistorial.addColumn("detalle");
        modeloHistorial.addColumn("fechaPréstamo");
        modeloHistorial.addColumn("fechaLímite");
        modeloHistorial.addColumn("fechaDevolución");
        
        Object[] columna = new Object[11];
        
        for(int x=0; x<listaHistorial.size(); x++) {
            columna[0] = listaHistorial.get(x).getNoControl();
            columna[1] = listaHistorial.get(x).getGrado();
            columna[2] = listaHistorial.get(x).getEjemplar();
            columna[3] = listaHistorial.get(x).getDetalle();
            columna[4] = listaHistorial.get(x).getFechaPrestamo();
            columna[5] = listaHistorial.get(x).getFechaLimite();
            columna[6] = listaHistorial.get(x).getFechaDevolucion();
            modeloHistorial.addRow(columna);
        }
    }  
    
    public void buscarHistorial() {
        String fechaDesde;
        String fechaHasta;
        if (vistaHist.rbHistGeneral.isSelected()){
            if (vistaHist.rbHistGeneral.isSelected()){
                vistaHist.jpTablaHistGral.setVisible(true);
                vistaHist.spHistGeneral.setVisible(true);  
                vistaHist.jpTablaHistLib.setVisible(false);
                vistaHist.spHistLibros.setVisible(false);
                vistaHist.jpTablaHistMatVis.setVisible(false);
                vistaHist.spHistMatVis.setVisible(false);
                
                /*Filros General*/
                vistaHist.txtGralNoCont.setVisible(true);
                vistaHist.txtGralGrado.setVisible(true);
                vistaHist.txtGrlEjemp.setVisible(true);
                vistaHist.txtGralFecPres.setVisible(true);
                vistaHist.txtGralFecLim.setVisible(true);
                vistaHist.txtGralFecDev.setVisible(true);
                
                /*Filros Libros*/
                vistaHist.txtLibNoCont.setVisible(false);
                vistaHist.txtLibGrado.setVisible(false);
                vistaHist.txtLibEjemp.setVisible(false);
                vistaHist.txtLibFecPres.setVisible(false);
                vistaHist.txtLibFecLim.setVisible(false);
                vistaHist.txtLibFecDev.setVisible(false);
                
                /*Filros Materiales Visuales*/
                vistaHist.txtMaViNoCont.setVisible(false);
                vistaHist.txtMaViGrado.setVisible(false);
                vistaHist.txtMaViEjemp.setVisible(false);
                vistaHist.txtMaViFecPres.setVisible(false);
                vistaHist.txtMaViFecLim.setVisible(false);
                vistaHist.txtMaViFecDev.setVisible(false);
                
                fechaDesde=selecFechaDesde();         
                fechaHasta=selecFechaHasta();
                buscarHistGral (fechaDesde, fechaHasta);
                mostrarHistGral(vistaHist.tbHistGeneral);                
            }
        }
        
        if (vistaHist.rbHistLibros.isSelected()){
            if (vistaHist.rbHistLibros.isSelected()){
                vistaHist.jpTablaHistGral.setVisible(false);
                vistaHist.spHistGeneral.setVisible(false);  
                vistaHist.jpTablaHistLib.setVisible(true);
                vistaHist.spHistLibros.setVisible(true);
                vistaHist.jpTablaHistMatVis.setVisible(false);
                vistaHist.spHistMatVis.setVisible(false);
                
                /*Filros General*/
                vistaHist.txtGralNoCont.setVisible(false);
                vistaHist.txtGralGrado.setVisible(false);
                vistaHist.txtGrlEjemp.setVisible(false);
                vistaHist.txtGralFecPres.setVisible(false);
                vistaHist.txtGralFecLim.setVisible(false);
                vistaHist.txtGralFecDev.setVisible(false);
                
                /*Filros Libros*/
                vistaHist.txtLibNoCont.setVisible(true);
                vistaHist.txtLibGrado.setVisible(true);
                vistaHist.txtLibEjemp.setVisible(true);
                vistaHist.txtLibFecPres.setVisible(true);
                vistaHist.txtLibFecLim.setVisible(true);
                vistaHist.txtLibFecDev.setVisible(true);
                
                /*Filros Materiales Visuales*/
                vistaHist.txtMaViNoCont.setVisible(false);
                vistaHist.txtMaViGrado.setVisible(false);
                vistaHist.txtMaViEjemp.setVisible(false);
                vistaHist.txtMaViFecPres.setVisible(false);
                vistaHist.txtMaViFecLim.setVisible(false);
                vistaHist.txtMaViFecDev.setVisible(false);
                
                fechaDesde=selecFechaDesde();    
                fechaHasta=selecFechaHasta();
                buscarHistLib(fechaDesde, fechaHasta);
                mostrarHistLib(vistaHist.tbHistLibros);
            }
        }
        
        if (vistaHist.rbHistMatVis.isSelected()){
            if (vistaHist.rbHistMatVis.isSelected()){
                vistaHist.jpTablaHistGral.setVisible(false);
                vistaHist.spHistGeneral.setVisible(false);  
                vistaHist.jpTablaHistLib.setVisible(false);
                vistaHist.spHistLibros.setVisible(false);
                vistaHist.jpTablaHistMatVis.setVisible(true);
                vistaHist.spHistMatVis.setVisible(true);
                
                /*Filros General*/
                vistaHist.txtGralNoCont.setVisible(false);
                vistaHist.txtGralGrado.setVisible(false);
                vistaHist.txtGrlEjemp.setVisible(false);
                vistaHist.txtGralFecPres.setVisible(false);
                vistaHist.txtGralFecLim.setVisible(false);
                vistaHist.txtGralFecDev.setVisible(false);
                
                /*Filros Libros*/
                vistaHist.txtLibNoCont.setVisible(false);
                vistaHist.txtLibGrado.setVisible(false);
                vistaHist.txtLibEjemp.setVisible(false);
                vistaHist.txtLibFecPres.setVisible(false);
                vistaHist.txtLibFecLim.setVisible(false);
                vistaHist.txtLibFecDev.setVisible(false);
                
                /*Filros Materiales Visuales*/
                vistaHist.txtMaViNoCont.setVisible(true);
                vistaHist.txtMaViGrado.setVisible(true);
                vistaHist.txtMaViEjemp.setVisible(true);
                vistaHist.txtMaViFecPres.setVisible(true);
                vistaHist.txtMaViFecLim.setVisible(true);
                vistaHist.txtMaViFecDev.setVisible(true);
                
                fechaDesde=selecFechaDesde();       
                fechaHasta=selecFechaHasta();
                buscarHistMatVis(fechaDesde, fechaHasta);
                mostrarHistMatVis(vistaHist.tbHistMatVis);
            }
        }        
    }
    
    public String selecFechaDesde() {
            String fechaDesde = "";            
            
            int año = vistaHist.selecFechaDesde.getCalendar().get(Calendar.YEAR);
            int mes = vistaHist.selecFechaDesde.getCalendar().get(Calendar.MONTH) + 1;
            int dia = vistaHist.selecFechaDesde.getCalendar().get(Calendar.DAY_OF_MONTH);          
            fechaDesde= Integer.toString(dia)+"/"+Integer.toString(mes)+"/"+Integer.toString(año);             
            
            return fechaDesde;
    }
    
    public String selecFechaHasta() {
            String fechaHasta = "";
            
            int año = vistaHist.selecFechaHasta.getCalendar().get(Calendar.YEAR);
            int mes = vistaHist.selecFechaHasta.getCalendar().get(Calendar.MONTH) + 1;
            int dia = vistaHist.selecFechaHasta.getCalendar().get(Calendar.DAY_OF_MONTH);          
            fechaHasta= Integer.toString(dia)+"/"+Integer.toString(mes)+"/"+Integer.toString(año);             
            
            return fechaHasta;
    }
    
    public boolean validarFechHasta()  {
        String fechaDesde = selecFechaDesde();
        String fechaHasta = selecFechaHasta();
        boolean validar=true;
        
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy"); 
        Date DateDesde = null;
        Date DateHasta = null;
        try {
            DateDesde = formateador.parse(fechaDesde);        
            DateHasta = formateador.parse(fechaHasta);
        } catch (ParseException ex) {
            Logger.getLogger(controlHistorial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if ( DateHasta.before(DateDesde) ){
            validar = false;
        }
        return validar;
    }
}
