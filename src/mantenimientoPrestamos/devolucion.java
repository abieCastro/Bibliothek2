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
public class devolucion {

    int claveDevolucion;
    String fechaDevolucion;
    int alumno_claveAlumno;
    int docente_claveDocente;
    int prestamo_clavePrestamo;

    public devolucion(int claveDevolucion, String fechaDevolucion, int alumno_claveAlumno, int docente_claveDocente, int prestamo_clavePrestamo) {
        this.claveDevolucion = claveDevolucion;
        this.fechaDevolucion = fechaDevolucion;
        this.alumno_claveAlumno = alumno_claveAlumno;
        this.docente_claveDocente = docente_claveDocente;
        this.prestamo_clavePrestamo = prestamo_clavePrestamo;
    }

    public int getIdDevolucion() {
        return claveDevolucion;
    }

    public void setClaveDevolucion(int claveDevolucion) {
        this.claveDevolucion = claveDevolucion;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
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

    public int getPrestamo_clavePrestamo() {
        return prestamo_clavePrestamo;
    }

    public void setPrestamo_clavePrestamo(int prestamo_clavePrestamo) {
        this.prestamo_clavePrestamo = prestamo_clavePrestamo;
    }

}
