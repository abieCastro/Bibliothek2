/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientoPrestamos;

/**
 *
 * @author Mac
 */
public class prestamo {

    int clavePrestamo;
    String fechaPrestamo;
    String fechalimite;
    int alumno_claveAlumno;
    int docente_claveDocente;

    public prestamo() {

    }

    public prestamo(int clavePrestamo, String fechaPrestamo, String fechalimite, int alumno_claveAlumno, int docente_claveDocente) {
        this.clavePrestamo = clavePrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechalimite = fechalimite;
        this.alumno_claveAlumno = alumno_claveAlumno;
        this.docente_claveDocente = docente_claveDocente;
    }

    public int getClavePrestamo() {
        return clavePrestamo;
    }

    public void setClavePrestamo(int clavePrestamo) {
        this.clavePrestamo = clavePrestamo;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechalimite() {
        return fechalimite;
    }

    public void setFechalimite(String fechalimite) {
        this.fechalimite = fechalimite;
    }

    public int getAlumno_claveAlumno() {
        return alumno_claveAlumno;
    }

    public void setAlumno_claveAlumno(int alumno_claveAlumno) {
        this.alumno_claveAlumno = alumno_claveAlumno;
    }

    public int getDocente_claveDocente() {
        return docente_claveDocente;
    }

    public void setDocente_claveDocente(int docente_claveDocente) {
        this.docente_claveDocente = docente_claveDocente;
    }
}
