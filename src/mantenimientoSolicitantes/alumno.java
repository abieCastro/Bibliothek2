/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientoSolicitantes;

/**
 *
 * @author ACER
 */
public class alumno {

    int claveAlumno;
    String noControlA;
    String nombreA;
    String apellidoPatA;
    String apellidoMatA;
    String gradoA;
    String grupoA;
    String telefonoA;
    String celularA;
    int limiteLibroA;
    int limiteMatVisA;
    int librosSolicA;
    int matVisSolicA;

    public alumno() {

    }

    public alumno(int claveAlumno, String noControlA, String nombreA, String apellidoPatA, String apellidoMatA, String gradoA, String grupoA, String telefonoA, String celularA, int limiteLibroA, int limiteMatVisA, int librosSolicA, int matVisSolicA) {
        this.claveAlumno = claveAlumno;
        this.noControlA = noControlA;
        this.nombreA = nombreA;
        this.apellidoPatA = apellidoPatA;
        this.apellidoMatA = apellidoMatA;
        this.gradoA = gradoA;
        this.grupoA = grupoA;
        this.telefonoA = telefonoA;
        this.celularA = celularA;
        this.limiteLibroA = limiteLibroA;
        this.limiteMatVisA = limiteMatVisA;
        this.librosSolicA = librosSolicA;
        this.matVisSolicA = matVisSolicA;
    }

    public int getClaveAlumno() {
        return claveAlumno;
    }

    public String getNoControlA() {
        return noControlA;
    }

    public String getNombreA() {
        return nombreA;
    }

    public String getApellidoPatA() {
        return apellidoPatA;
    }

    public String getApellidoMatA() {
        return apellidoMatA;
    }

    public String getGradoA() {
        return gradoA;
    }

    public String getGrupoA() {
        return grupoA;
    }

    public String getTelefonoA() {
        return telefonoA;
    }

    public String getCelularA() {
        return celularA;
    }

    public int getLimiteLibroA() {
        return limiteLibroA;
    }

    public int getLimiteMatVisA() {
        return limiteMatVisA;
    }

    public int getLibrosSolicA() {
        return librosSolicA;
    }

    public int getMatVisSolicA() {
        return matVisSolicA;
    }

    public void setClaveAlumno(int claveAlumno) {
        this.claveAlumno = claveAlumno;
    }

    public void setNoControlA(String noControlA) {
        this.noControlA = noControlA;
    }

    public void setNombreA(String nombreA) {
        this.nombreA = nombreA;
    }

    public void setApellidoPatA(String apellidoPatA) {
        this.apellidoPatA = apellidoPatA;
    }

    public void setApellidoMatA(String apellidoMatA) {
        this.apellidoMatA = apellidoMatA;
    }

    public void setGradoA(String gradoA) {
        this.gradoA = gradoA;
    }

    public void setGrupoA(String grupoA) {
        this.grupoA = grupoA;
    }

    public void setTelefonoA(String telefonoA) {
        this.telefonoA = telefonoA;
    }

    public void setCelularA(String celularA) {
        this.celularA = celularA;
    }

    public void setLimiteLibroA(int limiteLibroA) {
        this.limiteLibroA = limiteLibroA;
    }

    public void setLimiteMatVisA(int limiteMatVisA) {
        this.limiteMatVisA = limiteMatVisA;
    }

    public void setLibrosSolicA(int librosSolicA) {
        this.librosSolicA = librosSolicA;
    }

    public void setMatVisSolicA(int matVisSolicA) {
        this.matVisSolicA = matVisSolicA;
    }

}
