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
import rnegocio.clases.Usuario;
import rnegocio.funciones.FUsuario;

/**
 *
 * @author Alex
 */
@ManagedBean
@ViewScoped
public class LoginController {

    Usuario usuario = new Usuario();

    public void logear() throws Exception {

        // System.out.println("usuario->"+usuario.getUsuario().toString());
        //System.out.println("usuario->"+usuario.getClave().toString());
        Usuario user_res = null;
        for (Usuario item : FUsuario.obtener()) {
            if (this.usuario.getUsuario().equals(item.getUsuario()) && this.usuario.getClave().equals(item.getClave())) {
                user_res = item;
            }
        }
        if (user_res != null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/Home.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de usuario o contraseña", "Usuario o Contraseña Incorecto."));
        }

        System.out.println("Logeando");
    }

    public void borrar() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
