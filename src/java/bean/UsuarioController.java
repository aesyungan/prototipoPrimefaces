package bean;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import rnegocio.clases.Usuario;
import rnegocio.funciones.FUsuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Usuario
 */
@ManagedBean
@ViewScoped
public class UsuarioController {

    Usuario usuarioNuevo = new Usuario();

    public void guardarDatos() throws Exception {
        FUsuario.insertar(usuarioNuevo);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se Inserto Corectamente.", "Correcto."));
    }

    public Usuario getUsuarioNuevo() {
        return usuarioNuevo;
    }

    public void setUsuarioNuevo(Usuario usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
    }

   
    
}
