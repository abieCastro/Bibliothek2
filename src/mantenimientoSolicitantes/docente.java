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
public class docente {

    int claveDocente;
    String noControlD;
    String nombreD;
    String apellidoPatD;
    String apellidoMatD;
    int limiteLibroD;
    int limiteMatVisD;
    int librosSolicD;
    int matVisSolicD;

    public docente() {

    }

    public docente(int claveDocente, String noControlD, String nombreD, String apellidoPatD, String apellidoMatD, int limiteLibroD, int limiteMatVisD, int librosSolicD, int matVisSolicD) {
        this.claveDocente = claveDocente;
        this.noControlD = noControlD;
        this.nombreD = nombreD;
        this.apellidoPatD = apellidoPatD;
        this.apellidoMatD = apellidoMatD;
        this.limiteLibroD = limiteLibroD;
        this.limiteMatVisD = limiteMatVisD;
        this.librosSolicD = librosSolicD;
        this.matVisSolicD = matVisSolicD;
    }

    public int getClaveDocente() {
        return claveDocente;
    }

    public String getNoControlD() {
        return noControlD;
    }

    public String getNombreD() {
        return nombreD;
    }

    public String getApellidoPatD() {
        return apellidoPatD;
    }

    public String getApellidoMatD() {
        return apellidoMatD;
    }

    public int getLimiteLibroD() {
        return limiteLibroD;
    }

    public int getLimiteMatVisD() {
        return limiteMatVisD;
    }

    public int getLibrosSolicD() {
        return librosSolicD;
    }

    public int getMatVisSolicD() {
        return matVisSolicD;
    }

    public void setClaveDocente(int claveDocente) {
        this.claveDocente = claveDocente;
    }

    public void setNoControlD(String noControlD) {
        this.noControlD = noControlD;
    }

    public void setNombreD(String nombreD) {
        this.nombreD = nombreD;
    }

    public void setApellidoPatD(String apellidoPatD) {
        this.apellidoPatD = apellidoPatD;
    }

    public void setApellidoMatD(String apellidoMatD) {
        this.apellidoMatD = apellidoMatD;
    }

    public void setLimiteLibroD(int limiteLibroD) {
        this.limiteLibroD = limiteLibroD;
    }

    public void setLimiteMatVisD(int limiteMatVisD) {
        this.limiteMatVisD = limiteMatVisD;
    }

    public void setLibrosSolicD(int librosSolicD) {
        this.librosSolicD = librosSolicD;
    }

    public void setMatVisSolicD(int matVisSolicD) {
        this.matVisSolicD = matVisSolicD;
    }

}
