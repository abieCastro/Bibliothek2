/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientoPrestamos;

import principal.conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import mantenimientoLibros.ejemplarLibro;
import mantenimientoLibros.libro;
import mantenimientoMaterialVisual.ejempMatVisual;
import mantenimientoMaterialVisual.materialVisual;
import mantenimientoSolicitantes.alumno;
import mantenimientoSolicitantes.docente;

/**
 *
 * @author Mac
 */
public class controlDevolucion {

    conexion con = new conexion();
    interfazDevolucion vistaDev;
    ArrayList<libro> listaLibro;
    ArrayList<ejemplarLibro> listaEjempLibro;
    ArrayList<materialVisual> listaMatVis;
    ArrayList<ejempMatVisual> listaEjempMatVis;
    ArrayList<prestamo> listaPres;
    int selectLibro = -1;
    int selectMatVis = -1;
    int selectAlumno = -1;
    int selectDocente = -1;

    public controlDevolucion(interfazDevolucion vistaDev) {
        this.vistaDev = vistaDev;
    }

    public boolean validarTipoEjempD() {
        boolean validar = false;

        if (vistaDev.txtDevEjempBus.getText().toUpperCase().startsWith("LIB")) {
            validar = true;
            selectLibro = 1;
            selectMatVis = -1;
        } else {
            if (vistaDev.txtDevEjempBus.getText().toUpperCase().startsWith("MV")) {
                validar = true;
                selectLibro = -1;
                selectMatVis = 1;
            } else {
                validar = false;
                selectLibro = -1;
                selectMatVis = -1;
            }
        }
        return validar;
    }

    public boolean buscarEjempDev(String ejemplar) {
        boolean validar = false;

        if (selectLibro == 1) {
            libro lib;
            listaLibro = new ArrayList<libro>();
            ejemplarLibro ejempLib;
            listaEjempLibro = new ArrayList<ejemplarLibro>();

            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("SELECT libro.claveLibro, ejemplarlibro.idEjemplarL, libro.tituloL, libro.autorL, libro.añoL, libro.editorialL, libro.clasificacionL, libro.existenciaL, libro.disponibilidadL, ejemplarlibro.estadoL, ejemplarlibro.prestamo_clavePrestamo FROM libro INNER JOIN ejemplarlibro ON (libro.claveLibro=ejemplarlibro.libro_claveLibro) WHERE ejemplarlibro.idEjemplarL='" + ejemplar + "';");
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    validar = true;
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
                    ejempLib.setPrestamo_clavePrestamo(rs.getInt(11));
                    listaLibro.add(lib);
                    listaEjempLibro.add(ejempLib);
                }

            } catch (SQLException ex) {
                Logger.getLogger(controlPrestamo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (selectMatVis == 1) {
            materialVisual matVis;
            listaMatVis = new ArrayList<materialVisual>();
            ejempMatVisual ejempMatVis;
            listaEjempMatVis = new ArrayList<ejempMatVisual>();

            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("SELECT materialvisual.claveMatVis, ejempmatvisual.idEjemplarM, materialvisual.tituloM, materialvisual.volumenM, materialvisual.añoM, materialvisual.clasificacionM, materialvisual.existenciaM, materialvisual.disponibilidadM, ejempmatvisual.EstadoM, ejempmatvisual.prestamo_clavePrestamo FROM materialvisual INNER JOIN ejempmatvisual ON (materialvisual.claveMatVis=ejempmatvisual.materialvisual_claveMatVis) WHERE ejempmatvisual.idEjemplarM='" + ejemplar + "';");
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    validar = true;
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
                    ejempMatVis.setPrestamo_clavePrestamo(rs.getInt(10));
                    listaMatVis.add(matVis);
                    listaEjempMatVis.add(ejempMatVis);
                }

            } catch (SQLException ex) {
                Logger.getLogger(controlPrestamo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return validar;
    }

    public boolean validarEstadoD(String ejemplar) {
        boolean validar = false;

        if (selectLibro == 1) {

            if (listaEjempLibro.get(0).getEstadoL().equalsIgnoreCase("DISPONIBLE")) {
                validar = false;
            } else {
                validar = true;
            }
        }
        if (selectMatVis == 1) {

            if (listaEjempMatVis.get(0).getEstadoM().equalsIgnoreCase("DISPONIBLE")) {
                validar = false;
            } else {
                validar = true;
            }
        }

        return validar;
    }

    public void mostrarEjempD(JTable tablaLibro, JTable tablaMatVis) {

        if (selectLibro == 1) {
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

            for (int y = 0; y < listaLibro.size(); y++) {
                columna[0] = listaLibro.get(y).getClaveLibro();
                columna[2] = listaLibro.get(y).getTituloL();
                columna[3] = listaLibro.get(y).getAutorL();
                columna[4] = listaLibro.get(y).getAñoL();
                columna[5] = listaLibro.get(y).getEditorialL();
                columna[6] = listaLibro.get(y).getClasificacionL();
                for (int x = 0; x < listaEjempLibro.size(); x++) {
                    columna[1] = listaEjempLibro.get(x).getIdEjemplarL();
                    modeloLibro.addRow(columna);
                }
                modeloLibro.addRow(columna);
            }
            //ejempPres = vistaPres.txtEjempBus.getText();
            vistaDev.jpTablaDevLib.setVisible(true);
            vistaDev.jpTablaDevMaVi.setVisible(false);
        }

        if (selectMatVis == 1) {
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

            for (int y = 0; y < listaMatVis.size(); y++) {
                columna[0] = listaMatVis.get(y).getClaveMatVis();
                columna[2] = listaMatVis.get(y).getTituloM();
                columna[3] = listaMatVis.get(y).getvolumenM();
                columna[4] = listaMatVis.get(y).getAñoM();
                columna[5] = listaMatVis.get(y).getClasificacionM();
                for (int x = 0; x < listaEjempMatVis.size(); x++) {
                    columna[1] = listaEjempMatVis.get(x).getIdEjemplarM();
                    modeloMatVis.addRow(columna);
                }
                modeloMatVis.addRow(columna);
            }
            //ejempPres = vistaPres.txtEjempBus.getText();
            vistaDev.jpTablaDevLib.setVisible(false);
            vistaDev.jpTablaDevMaVi.setVisible(true);
        }
    }

    public String fechaDevolucion() {
        Date fecha = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha);
    }

    public int crearClaveDev() {
        int claveDev = 0;
        try {
            Connection acceDB = con.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("SELECT MAX(claveDevolucion) from devolucion;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                claveDev = rs.getInt(1);
            }
            claveDev = claveDev + 1;

        } catch (SQLException ex) {
            Logger.getLogger(controlPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return claveDev;
    }

    public void saberSolic() {
        prestamo pres;
        listaPres = new ArrayList<prestamo>();

        if (selectLibro == 1) {
            try {
                Connection acceDBA = con.getConexion();
                PreparedStatement psA = acceDBA.prepareStatement("SELECT * FROM prestamo WHERE clavePrestamo='" + listaEjempLibro.get(0).getPrestamo_clavePrestamo() + "' and alumno_claveAlumno IS NOT NULL;");
                ResultSet rsA = psA.executeQuery();

                if (rsA.next()) {
                    selectAlumno = 1;
                    selectDocente = -1;
                    pres = new prestamo();
                    pres.setClavePrestamo(rsA.getInt(1));
                    pres.setFechaPrestamo(rsA.getString(2));
                    pres.setFechalimite(rsA.getString(3));;
                    pres.setAlumno_claveAlumno(rsA.getInt(4));
                    pres.setDocente_claveDocente(rsA.getInt(5));
                    listaPres.add(pres);

                } else {
                    Connection acceDBD = con.getConexion();
                    PreparedStatement psD = acceDBD.prepareStatement("SELECT * FROM prestamo WHERE clavePrestamo='" + listaEjempLibro.get(0).getPrestamo_clavePrestamo() + "' and docente_claveDocente IS NOT NULL;");
                    ResultSet rsD = psD.executeQuery();

                    if (rsD.next()) {
                        selectAlumno = -1;
                        selectDocente = 1;
                        pres = new prestamo();
                        pres.setClavePrestamo(rsD.getInt(1));
                        pres.setFechaPrestamo(rsD.getString(2));
                        pres.setFechalimite(rsD.getString(3));;
                        pres.setAlumno_claveAlumno(rsD.getInt(4));
                        pres.setDocente_claveDocente(rsD.getInt(5));
                        listaPres.add(pres);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(controlPrestamo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (selectMatVis == 1) {
            try {
                Connection acceDBL = con.getConexion();
                PreparedStatement psL = acceDBL.prepareStatement("SELECT * FROM prestamo WHERE clavePrestamo='" + listaEjempMatVis.get(0).getPrestamo_clavePrestamo() + "' and alumno_claveAlumno IS NOT NULL;");
                ResultSet rsL = psL.executeQuery();

                if (rsL.next()) {
                    selectAlumno = 1;
                    selectDocente = -1;
                    pres = new prestamo();
                    pres.setClavePrestamo(rsL.getInt(1));
                    pres.setFechaPrestamo(rsL.getString(2));
                    pres.setFechalimite(rsL.getString(3));;
                    pres.setAlumno_claveAlumno(rsL.getInt(4));
                    pres.setDocente_claveDocente(rsL.getInt(5));
                    listaPres.add(pres);

                } else {
                    Connection acceDBD = con.getConexion();
                    PreparedStatement psD = acceDBD.prepareStatement("SELECT * FROM prestamo WHERE clavePrestamo='" + listaEjempMatVis.get(0).getPrestamo_clavePrestamo() + "' and docente_claveDocente IS NOT NULL;");
                    ResultSet rsD = psD.executeQuery();

                    if (rsD.next()) {
                        selectAlumno = -1;
                        selectDocente = 1;
                        pres = new prestamo();
                        pres.setClavePrestamo(rsD.getInt(1));
                        pres.setFechaPrestamo(rsD.getString(2));
                        pres.setFechalimite(rsD.getString(3));;
                        pres.setAlumno_claveAlumno(rsD.getInt(4));
                        pres.setDocente_claveDocente(rsD.getInt(5));
                        listaPres.add(pres);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(controlPrestamo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean registrarDev(int claveDev) {
        boolean registro = false;
        if (selectAlumno == 1) {
            String fechaDev = fechaDevolucion();
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("INSERT INTO devolucion(claveDevolucion,fechaDevolucion,alumno_claveAlumno,prestamo_clavePrestamo)VALUES(?,?,?,?)");
                ps.setInt(1, claveDev);
                ps.setString(2, fechaDev);
                ps.setInt(3, listaPres.get(0).getAlumno_claveAlumno());
                ps.setInt(4, listaPres.get(0).getClavePrestamo());
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException ex) {
            }
        }
        if (selectDocente == 1) {
            String fechaDev = fechaDevolucion();
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("INSERT INTO devolucion(claveDevolucion,fechaDevolucion,docente_claveDocente,prestamo_clavePrestamo)VALUES(?,?,?,?)");

                ps.setInt(1, claveDev);
                ps.setString(2, fechaDev);
                ps.setInt(3, listaPres.get(0).getDocente_claveDocente());
                ps.setInt(4, listaPres.get(0).getClavePrestamo());
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException ex) {
            }
        }
        return registro;
    }

    public boolean cambiarEstadoD() {
        boolean registro = false;

        if (selectLibro != -1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE ejemplarlibro SET estadoL='DISPONIBLE' where ejemplarlibro.idEjemplarL='" + listaEjempLibro.get(0).getIdEjemplarL() + "';");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }
        if (selectMatVis != -1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE ejempmatvisual SET estadoM='DISPONIBLE' where ejempmatvisual.idEjemplarM='" + listaEjempMatVis.get(0).getIdEjemplarM() + "';");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }
        return registro;
    }

    public boolean cancelarPrest() {
        boolean registro = false;

        if (selectLibro != -1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE ejemplarlibro SET prestamo_clavePrestamo=null where ejemplarlibro.idEjemplarL='" + listaEjempLibro.get(0).getIdEjemplarL() + "';");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }
        if (selectMatVis != -1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE ejempmatvisual SET prestamo_clavePrestamo=null where ejempmatvisual.idEjemplarM='" + listaEjempMatVis.get(0).getIdEjemplarM() + "';");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }

        return registro;
    }

    public boolean aumentarDispD() {
        boolean registro = false;
        if (selectLibro != -1) {
            System.out.println("");
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE libro SET disponibilidadL=disponibilidadL+1 WHERE claveLibro=(select ejemplarlibro.libro_claveLibro from ejemplarlibro where ejemplarlibro.idEjemplarL='" + listaEjempLibro.get(0).getIdEjemplarL() + "');");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }
        if (selectMatVis != -1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE materialvisual SET disponibilidadM=disponibilidadM+1 WHERE claveMatVis=(select ejempmatvisual.materialvisual_claveMatVis from ejempmatvisual where ejempmatvisual.idEjemplarM='" + listaEjempMatVis.get(0).getIdEjemplarM() + "');");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }
        return registro;
    }

    public boolean aumentarLimiteD() {
        boolean registro = false;

        if (selectLibro == 1 && selectAlumno == 1) {

            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE alumno SET limiteLibroA=limiteLibroA+1 where claveAlumno='" + listaPres.get(0).getAlumno_claveAlumno() + "';");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }
        if (selectMatVis == 1 && selectAlumno == 1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE alumno SET limiteMatVisA=limiteMatVisA+1 where claveAlumno='" + listaPres.get(0).getAlumno_claveAlumno() + "';");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }

        if (selectLibro == 1 && selectDocente == 1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE docente SET limiteLibroD=limiteLibroD+1 where claveDocente='" + listaPres.get(0).getDocente_claveDocente() + "';");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }
        if (selectMatVis == 1 && selectDocente == 1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE docente SET limiteMatVisD=limiteMatVisD+1 where claveDocente='" + listaPres.get(0).getDocente_claveDocente() + "';");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }
        return registro;
    }

    public boolean disminuirSolicD() {
        boolean registro = false;

        if (selectLibro == 1 && selectAlumno == 1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE alumno SET librosSolicA=librosSolicA-1 where claveAlumno=" + listaPres.get(0).getAlumno_claveAlumno() + ";");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }

        if (selectMatVis == 1 && selectAlumno == 1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE alumno SET matVisSolicA=matVisSolicA-1 where claveAlumno=" + listaPres.get(0).getAlumno_claveAlumno() + ";");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }

        if (selectLibro == 1 && selectDocente == 1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE docente SET librosSolicD=librosSolicD-1 where claveDocente=" + listaPres.get(0).getDocente_claveDocente() + ";");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }

        if (selectMatVis == 1 && selectDocente == 1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE docente SET matVisSolicD=matVisSolicD-1 where claveDocente=" + listaPres.get(0).getDocente_claveDocente() + ";");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }
        return registro;
    }

    public boolean regHistorialD(int idDev) {
        boolean registro = false;
        if (selectLibro == 1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE historial SET devolucion_claveDevolucion='" + idDev + "' WHERE prestamo_clavePrestamo='" + listaPres.get(0).getClavePrestamo() + "' and ejemplarlibro_idEjemplarL='" + listaEjempLibro.get(0).getIdEjemplarL() + "';");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }

        if (selectMatVis == 1) {
            try {
                Connection acceDB = con.getConexion();
                PreparedStatement ps = acceDB.prepareStatement("UPDATE historial SET devolucion_claveDevolucion='" + idDev + "' WHERE prestamo_clavePrestamo='" + listaPres.get(0).getClavePrestamo() + "' and ejempmatvisual_idEjemplarM='" + listaEjempMatVis.get(0).getIdEjemplarM() + "';");
                int validar = ps.executeUpdate();
                if (validar > 0) {
                    registro = true;
                } else {
                    registro = false;
                }
            } catch (SQLException e) {
            }
        }

        return registro;
    }

    public DefaultTableModel limpiarTabla() {
        DefaultTableModel modelo = null;
        /*Si el ejemplar a devolver fue un libro limpiar su tabla*/
        if (selectLibro == 1) {
            try {
                modelo = (DefaultTableModel) vistaDev.tbDevLibro.getModel();
                int filas = vistaDev.tbDevLibro.getRowCount();
                for (int i = 0; filas > i; i++) {
                    modelo.removeRow(0);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
            }

        }

        /*Si el ejemplar a devolver fue un material visual limpiar su tabla*/
        if (selectMatVis == 1) {
            try {
                modelo = (DefaultTableModel) vistaDev.tbDevMatVis.getModel();
                int filas = vistaDev.tbDevMatVis.getRowCount();
                for (int i = 0; filas > i; i++) {
                    modelo.removeRow(0);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
            }
        }
        vistaDev.jpTablaDevLib.setVisible(false);
        vistaDev.jpTablaDevMaVi.setVisible(false);
        vistaDev.btDevolver.setVisible(false);
        vistaDev.btCancelarDev.setVisible(false);
        return modelo;
    }

    public void mensajeD(int idDev, boolean regDev, boolean camEst, boolean cancPrest, boolean aumDisp, boolean aumLim, boolean dismSolic, boolean regHist) {
        if (idDev != 0 && regDev == true && camEst == true && cancPrest == true && aumDisp == true && aumLim == true && dismSolic == true && regHist) {
            limpiarTabla();
            vistaDev.jpTablaDevLib.setVisible(false);
            vistaDev.jpTablaDevMaVi.setVisible(false);
            vistaDev.btDevolver.setVisible(false);
            JOptionPane.showMessageDialog(null, "Devolución éxitoso", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Devolución no éxitoso", "Mensaje", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void regDevolucion() {
        int idDev = crearClaveDev();
        fechaDevolucion();
        saberSolic();
        boolean regDev = registrarDev(idDev);
        boolean camEst = cambiarEstadoD();
        boolean cancPrest = cancelarPrest();
        boolean aumDisp = aumentarDispD();
        boolean aumLim = aumentarLimiteD();
        boolean dismSolic = disminuirSolicD();
        boolean regHist = regHistorialD(idDev);
        mensajeD(idDev, regDev, camEst, cancPrest, aumDisp, aumLim, dismSolic, regHist);
    }
}
