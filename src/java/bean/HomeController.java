/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import rnegocio.clases.Usuario;
import rnegocio.funciones.FUsuario;

/**
 *
 * @author Alex
 */
@ManagedBean
@ViewScoped
public class HomeController {

    ArrayList<Usuario> lst = new ArrayList<>();

    public HomeController() throws Exception {
        lst = FUsuario.obtener();
    }

    public ArrayList<Usuario> getLst() {
        return lst;
    }

    public void setLst(ArrayList<Usuario> lst) {
        this.lst = lst;
    }

}
