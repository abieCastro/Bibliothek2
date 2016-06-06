/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientoMaterialVisual;

/**
 *
 * @author Mac
 */
public class ejempMatVisual {

    String idEjemplarM;
    String estadoM;
    String materialvisual_claveMatVis;
    int prestamo_clavePrestamo;
    int devolucion_idDevolucion;

    public ejempMatVisual() {

    }

    public ejempMatVisual(String idEjemplarM, String estadoM, String materialvisual_claveMatVis, int prestamo_clavePrestamo, int devolucion_idDevolucion) {
        this.idEjemplarM = idEjemplarM;
        this.estadoM = estadoM;
        this.materialvisual_claveMatVis = materialvisual_claveMatVis;
        this.prestamo_clavePrestamo = prestamo_clavePrestamo;
        this.devolucion_idDevolucion = devolucion_idDevolucion;
    }

    public String getIdEjemplarM() {
        return idEjemplarM;
    }

    public void setIdEjemplarM(String idEjemplarM) {
        this.idEjemplarM = idEjemplarM;
    }

    public String getEstadoM() {
        return estadoM;
    }

    public void setEstadoM(String estadoM) {
        this.estadoM = estadoM;
    }

    public String getMaterialvisual_claveMatVis() {
        return materialvisual_claveMatVis;
    }

    public void setMaterialvisual_claveMatVis(String materialvisual_claveMatVis) {
        this.materialvisual_claveMatVis = materialvisual_claveMatVis;
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
