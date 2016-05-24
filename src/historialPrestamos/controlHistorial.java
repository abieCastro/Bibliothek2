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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.freixas.jcalendar.JCalendarCombo;
import principal.conexion;
import sun.util.calendar.LocalGregorianCalendar;

/**
 *
 * @author Mac
 */
public class controlHistorial implements ActionListener{
    conexion con = new conexion();
    interfazHistorial vistaHist;
    ArrayList<historial> listaHistorial;
    
    public controlHistorial(interfazHistorial vistaHist) {
        this.vistaHist = vistaHist;
    }
    
    public void buscarHistGral (String fechaDesde) {        
        historial hist;
        listaHistorial = new ArrayList<historial>();             
        Date fecha = new Date();
        SimpleDateFormat fechaAct = new SimpleDateFormat("dd/MM/yyyy");
        String fechaAc=(fechaAct.format(fecha));  
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
            "	STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') as fechaPrestamo,\n" +
            "	STR_TO_DATE(p.fechaLimite, '%d/%m/%Y') as fechaLimite,\n" +
            "	case when devolucion_claveDevolucion is not null\n" +
            "			then STR_TO_DATE(de.fechaDevolucion, '%d/%m/%Y')\n" +
            "			else\n" +
            "				'N/A'		\n" +
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
            "		and STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') <= STR_TO_DATE('"+fechaAc+"', '%d/%m/%Y')\n" +
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
    
    public void buscarHistLib(String fechaDesde) {        
        historial hist;
        listaHistorial = new ArrayList<historial>();             
        Date fecha = new Date();
        SimpleDateFormat fechaAct = new SimpleDateFormat("dd/MM/yyyy");
        String fechaAc=(fechaAct.format(fecha));  
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
            "	h.ejemplarlibro_idEjemplarL as ejemplar,\n" +
            "	CONCAT(l.tituloL, '-' ,l.añoL) as detalle,	\n" +
            "	STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') as fechaPrestamo,\n" +
            "	STR_TO_DATE(p.fechaLimite, '%d/%m/%Y') as fechaLimite,\n" +
            "	case when devolucion_claveDevolucion is not null\n" +
            "			then STR_TO_DATE(de.fechaDevolucion, '%d/%m/%Y')\n" +
            "			else\n" +
            "				'N/A'		\n" +
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
            "		and STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') <= STR_TO_DATE('"+fechaAc+"', '%d/%m/%Y')\n" +
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
    
    public void buscarHistMatVis(String fechaDesde) {        
        historial hist;
        listaHistorial = new ArrayList<historial>();             
        Date fecha = new Date();
        SimpleDateFormat fechaAct = new SimpleDateFormat("dd/MM/yyyy");
        String fechaAc=(fechaAct.format(fecha));  
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
            "	STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') as fechaPrestamo,\n" +
            "	STR_TO_DATE(p.fechaLimite, '%d/%m/%Y') as fechaLimite,\n" +
            "	case when devolucion_claveDevolucion is not null\n" +
            "			then STR_TO_DATE(de.fechaDevolucion, '%d/%m/%Y')\n" +
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
            "		h.ejempmatvisual_idEjemplarM is not null\n" +
            "		and STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') >= STR_TO_DATE('"+fechaDesde+"', '%d/%m/%Y')\n" +
            "		and STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') <= STR_TO_DATE('"+fechaAc+"', '%d/%m/%Y')\n" +
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
    
    public void listaHistGral(JTable tablaHistorial) {         
        ArrayList<historial> listaHistorial = new ArrayList<historial>();
        historial hist;
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
            "	STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') as fechaPrestamo,\n" +
            "	STR_TO_DATE(p.fechaLimite, '%d/%m/%Y') as fechaLimite,\n" +
            "	case when devolucion_claveDevolucion is not null\n" +
            "			then STR_TO_DATE(de.fechaDevolucion, '%d/%m/%Y')\n" +
            "			else\n" +
            "				'N/A'		\n" +
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
            "		STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') >= STR_TO_DATE('01/05/2016', '%d/%m/%Y')\n" +
            "		and STR_TO_DATE(p.fechaPrestamo, '%d/%m/%Y') <= STR_TO_DATE('30/05/2016', '%d/%m/%Y')		\n" +
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
    
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource()== vistaHist.rbHistGeneral){
            if (vistaHist.rbHistGeneral.isSelected()){
                vistaHist.jpTablaHistGral.setVisible(true);
                vistaHist.spHistGeneral.setVisible(true);  
                vistaHist.jpTablaHistLib.setVisible(false);
                vistaHist.spHistLibros.setVisible(false);
                vistaHist.jpTablaHistMatVis.setVisible(false);
                vistaHist.spHistMatVis.setVisible(false);
                
                int año = vistaHist.selecFechaDesde.getCalendar().get(Calendar.YEAR);
                int mes = vistaHist.selecFechaDesde.getCalendar().get(Calendar.MONTH) + 1;
                int dia = vistaHist.selecFechaDesde.getCalendar().get(Calendar.DAY_OF_MONTH);          
                String fechaDesde= Integer.toString(dia)+"/"+Integer.toString(mes)+"/"+Integer.toString(año);  
                
                buscarHistGral (fechaDesde);
                mostrarHistGral(vistaHist.tbHistGeneral);                
            }
        }
        
        if (evento.getSource()== vistaHist.rbHistLibros){
            if (vistaHist.rbHistLibros.isSelected()){
                vistaHist.jpTablaHistGral.setVisible(false);
                vistaHist.spHistGeneral.setVisible(false);  
                vistaHist.jpTablaHistLib.setVisible(true);
                vistaHist.spHistLibros.setVisible(true);
                vistaHist.jpTablaHistMatVis.setVisible(false);
                vistaHist.spHistMatVis.setVisible(false);
                
                int año = vistaHist.selecFechaDesde.getCalendar().get(Calendar.YEAR);
                int mes = vistaHist.selecFechaDesde.getCalendar().get(Calendar.MONTH) + 1;
                int dia = vistaHist.selecFechaDesde.getCalendar().get(Calendar.DAY_OF_MONTH);          
                String fechaDesde= Integer.toString(dia)+"/"+Integer.toString(mes)+"/"+Integer.toString(año);  
                
                buscarHistLib(fechaDesde);
                mostrarHistLib(vistaHist.tbHistLibros);
            }
        }
        
        if (evento.getSource()== vistaHist.rbHistMatVis){
            if (vistaHist.rbHistMatVis.isSelected()){
                vistaHist.jpTablaHistGral.setVisible(false);
                vistaHist.spHistGeneral.setVisible(false);  
                vistaHist.jpTablaHistLib.setVisible(false);
                vistaHist.spHistLibros.setVisible(false);
                vistaHist.jpTablaHistMatVis.setVisible(true);
                vistaHist.spHistMatVis.setVisible(true);
                
                int año = vistaHist.selecFechaDesde.getCalendar().get(Calendar.YEAR);
                int mes = vistaHist.selecFechaDesde.getCalendar().get(Calendar.MONTH) + 1;
                int dia = vistaHist.selecFechaDesde.getCalendar().get(Calendar.DAY_OF_MONTH);          
                String fechaDesde= Integer.toString(dia)+"/"+Integer.toString(mes)+"/"+Integer.toString(año);  
                
                buscarHistMatVis(fechaDesde);
                mostrarHistMatVis(vistaHist.tbHistMatVis);
            }
        }
    }
}
