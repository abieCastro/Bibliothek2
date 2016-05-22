/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mantenimientoLibros;

/**
 *
 * @author Mac
 */
public class ejemplarLibro {
    String idEjemplarL;
    String estadoL;
    String libro_claveLibro;
    int prestamo_clavePrestamo;
    int devolucion_idDevolucion;
    
    public ejemplarLibro() {
        
    }

    public ejemplarLibro(String idEjemplarL, String estadoL, String libro_claveLibro, int prestamo_clavePrestamo, int devolucion_idDevolucion) {
        this.idEjemplarL = idEjemplarL;
        this.estadoL = estadoL;
        this.libro_claveLibro = libro_claveLibro;
        this.prestamo_clavePrestamo = prestamo_clavePrestamo;
        this.devolucion_idDevolucion = devolucion_idDevolucion;
    }
    

    public String getIdEjemplarL() {
        return idEjemplarL;
    }

    public void setIdEjemplarL(String idEjemplarL) {
        this.idEjemplarL = idEjemplarL;
    }

    public String getEstadoL() {
        return estadoL;
    }

    public void setEstadoL(String estadoL) {
        this.estadoL = estadoL;
    }

    public String getLibro_claveLibro() {
        return libro_claveLibro;
    }

    public void setLibro_claveLibro(String libro_claveLibro) {
        this.libro_claveLibro = libro_claveLibro;
    }

    public int getPrestamo_clavePrestamo() {
        return prestamo_clavePrestamo;
    }

    public void setPrestamo_clavePrestamo(int prestamo_clavePrestamo) {
        this.prestamo_clavePrestamo = prestamo_clavePrestamo;
    }

    public int getDevolucion_idDevolucion() {
        return devolucion_idDevolucion;
    }

    public void setDevolucion_idDevolucion(int devolucion_idDevolucion) {
        this.devolucion_idDevolucion = devolucion_idDevolucion;
    }
    
}
