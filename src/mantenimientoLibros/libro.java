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
public class libro {
    String claveLibro;
    String tituloL;
    String autorL;
    String añoL;
    String editorialL;
    String clasificacionL;
    int existenciaL;
    int disponibilidadL;
    
    public libro () {
        
    }

    public libro(String claveLibro, String tituloL, String autorL, String añoL, String editorialL, String clasificacionL, int existenciaL, int disponibilidadL) {
        this.claveLibro = claveLibro;
        this.tituloL = tituloL;
        this.autorL = autorL;
        this.añoL = añoL;
        this.editorialL = editorialL;
        this.clasificacionL = clasificacionL;
        this.existenciaL = existenciaL;
        this.disponibilidadL = disponibilidadL;
    }

    public String getClaveLibro() {
        return claveLibro;
    }

    public void setClaveLibro(String claveLibro) {
        this.claveLibro = claveLibro;
    }

    public String getTituloL() {
        return tituloL;
    }

    public void setTituloL(String tituloL) {
        this.tituloL = tituloL;
    }

    public String getAutorL() {
        return autorL;
    }

    public void setAutorL(String autorL) {
        this.autorL = autorL;
    }

    public String getAñoL() {
        return añoL;
    }

    public void setAñoL(String anioL) {
        this.añoL = anioL;
    }

    public String getEditorialL() {
        return editorialL;
    }

    public void setEditorialL(String editorialL) {
        this.editorialL = editorialL;
    }

    public String getClasificacionL() {
        return clasificacionL;
    }

    public void setClasificacionL(String clasificacionL) {
        this.clasificacionL = clasificacionL;
    }

    public int getExistenciaL() {
        return existenciaL;
    }

    public void setExistenciaL(int existenciaL) {
        this.existenciaL = existenciaL;
    }

    public int getDisponibilidadL() {
        return disponibilidadL;
    }

    public void setDisponibilidadL(int disponibilidadL) {
        this.disponibilidadL = disponibilidadL;
    }
    
}
