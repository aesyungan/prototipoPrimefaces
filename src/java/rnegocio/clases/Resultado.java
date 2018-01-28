/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rnegocio.clases;

/**
 *
 * @author XL
 */
//esta clase se creo para mostrar las respuestas al usuario en si esta clase no esta en la base de datos 
//
public class Resultado {

    String respuestar;
    int cantidad;

    public Resultado() {
        
    }

    public String getRespuestar() {
        return respuestar;
    }

    public void setRespuestar(String respuestar) {
        this.respuestar = respuestar;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
