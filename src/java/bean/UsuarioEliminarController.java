/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import rnegocio.funciones.FUsuario;

/**
 *
 * @author Alex
 */
@ManagedBean
@ViewScoped
public class UsuarioEliminarController {

    public void eliminar() throws Exception {
        FUsuario.eliminarJmeter("jmeter");
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se Elimino Corectamente.", "Correcto."));
    }

}
