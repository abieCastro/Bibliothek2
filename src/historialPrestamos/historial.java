/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historialPrestamos;

/**
 *
 * @author Mac
 */
public class historial {
    int claveHistorial;
    int prestamo_clavePrestamo;
    String ejemplarlibro_idEjemplarL;
    String ejempmatvisual_idEjemplarM;
    String devolucion_claveDevolucion;
    
    /*Atributos agregados*/
    String noControl;
    String grado;
    String ejemplar;
    String detalle;
    String fechaPrestamo;
    String fechaLimite;
    String fechaDevolucion;
    
    public historial () {
        
    }

    public historial(int claveHistorial, int prestamo_clavePrestamo, String ejemplarlibro_idEjemplarL, String ejempmatvisual_idEjemplarM, String devolucion_claveDevolucion, String noControl, String grado, String ejemplar, String detalle, String fechaPrestamo, String fechaLimite, String fechaDevolucion) {
        this.claveHistorial = claveHistorial;
        this.prestamo_clavePrestamo = prestamo_clavePrestamo;
        this.ejemplarlibro_idEjemplarL = ejemplarlibro_idEjemplarL;
        this.ejempmatvisual_idEjemplarM = ejempmatvisual_idEjemplarM;
        this.devolucion_claveDevolucion = devolucion_claveDevolucion;        
        
        this.noControl=noControl;
        this.grado=grado;
        this.ejemplar=ejemplar;
        this.detalle=detalle;
        this.fechaPrestamo=fechaPrestamo;
        this.fechaLimite=fechaLimite;
        this.fechaDevolucion=fechaDevolucion;
    }    

    public int getclaveHistorial() {
        return claveHistorial;
    }

    public void setclaveHistorial(int claveHistorial) {
        this.claveHistorial = claveHistorial;
    }

    public int getPrestamo_clavePrestamo() {
        return prestamo_clavePrestamo;
    }

    public void setPrestamo_clavePrestamo(int prestamo_clavePrestamo) {
        this.prestamo_clavePrestamo = prestamo_clavePrestamo;
    }

    public String getDevolucion_claveDevolucion() {
        return devolucion_claveDevolucion;
    }

    public void setDevolucion_claveDevolucion(String devolucion_claveDevolucion) {
        this.devolucion_claveDevolucion = devolucion_claveDevolucion;
    }

    public String getEjemplarlibro_idEjemplarL() {
        return ejemplarlibro_idEjemplarL;
    }

    public void setEjEmplarlibro_idEjemplarL(String ejemplarlibro_idEjemplarL) {
        this.ejemplarlibro_idEjemplarL = ejemplarlibro_idEjemplarL;
    }

    public String getEjempmatvisual_idEjemplarM() {
        return ejempmatvisual_idEjemplarM;
    }

    public void setEjempmatvisual_idEjemplarM(String ejempmatvisual_idEjemplarM) {
        this.ejempmatvisual_idEjemplarM = ejempmatvisual_idEjemplarM;
    }    
    
    /*Areibutos agregados*/
    
    public String getNoControl() {
        return noControl;
    }
    
    public void setNoControl(String noControl) {
        this.noControl=noControl;
    }
    
    public String getGrado() {
        return grado;
    }
    
    public void setGrado(String grado) {
        this.grado=grado;
    }
    
    public String getEjemplar() {
        return ejemplar;
    }
    
    public void setEjemplar(String ejemplar) {
        this.ejemplar=ejemplar;
    }
    
    public String getDetalle() {
        return detalle;
    }
    
    public void setDetalle(String detalle) {
        this.detalle=detalle;
    }
    
    public String getFechaPrestamo() {
        return fechaPrestamo;
    }
    
    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo=fechaPrestamo;
    }
    
    public String getFechaLimite() {
        return fechaLimite;
    }
    
    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite=fechaLimite;
    }
    
    public String getFechaDevolucion() {
        return fechaDevolucion;
    }
    
    public void setFechaDevolucion(String fechaDevolucionl) {
        this.fechaDevolucion=fechaDevolucion;
    }
}
