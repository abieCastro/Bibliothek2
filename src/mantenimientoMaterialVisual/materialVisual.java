/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientoMaterialVisual;

/**
 *
 * @author ACER
 */
public class materialVisual {

    String claveMatVis;
    String tituloM;
    String volumenM;
    String añoM;
    String clasificacionM;
    int existenciaM;
    int disponibilidadM;

    public materialVisual() {

    }

    public materialVisual(String claveMatVis, String tituloM, String volumenM, String añoM, String clasificacionM, int existenciaM, int disponibilidadM) {
        this.claveMatVis = claveMatVis;
        this.tituloM = tituloM;
        this.tituloM = tituloM;
        this.volumenM = volumenM;
        this.añoM = añoM;
        this.clasificacionM = clasificacionM;
        this.existenciaM = existenciaM;
        this.disponibilidadM = disponibilidadM;
    }

    public String getClaveMatVis() {
        return claveMatVis;
    }

    public String getTituloM() {
        return tituloM;
    }

    public String getvolumenM() {
        return volumenM;
    }

    public String getAñoM() {
        return añoM;
    }

    public String getClasificacionM() {
        return clasificacionM;
    }

    public int getExistenciaM() {
        return existenciaM;
    }

    public int getDisponibilidadM() {
        return disponibilidadM;
    }

    public void setClaveMatVis(String claveMatVis) {
        this.claveMatVis = claveMatVis;
    }

    public void setTituloM(String tituloM) {
        this.tituloM = tituloM;
    }

    public void setVolumneM(String volumenM) {
        this.volumenM = volumenM;
    }

    public void setAñoM(String añoM) {
        this.añoM = añoM;
    }

    public void setClasificacionM(String clasificacionM) {
        this.clasificacionM = clasificacionM;
    }

    public void setExistenciaM(int existenciaM) {
        this.existenciaM = existenciaM;
    }

    public void setDisponibilidadM(int disponibilidadM) {
        this.disponibilidadM = disponibilidadM;
    }
}
