package rnegocio.funciones;

import accesodatos.Parametro;
import accesodatos.ConjuntoResultado;
import accesodatos.AccesoDatos;
import rnegocio.clases.Categoria;
import java.util.ArrayList;

public class FCategoria {

    public static boolean insertar(Categoria obj) throws Exception {
        boolean band = false;
        String sql = "insert into public.categoria values (?,?,?)";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
//campos sin referencias
        lstpar.add(new Parametro(1, obj.getId()));
        lstpar.add(new Parametro(2, obj.getDescripcion()));
        lstpar.add(new Parametro(3, obj.getId_padre()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static boolean modificar(Categoria obj) throws Exception {
        boolean band = false;
        String sql = "update public.categoria set id=?,descripcion=?,id_padre=? where id=?  ";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
//campos sin referencias
        lstpar.add(new Parametro(1, obj.getId()));
        lstpar.add(new Parametro(4, obj.getId()));
        lstpar.add(new Parametro(2, obj.getDescripcion()));
        lstpar.add(new Parametro(3, obj.getId_padre()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static boolean eliminar(Categoria obj) throws Exception {
        boolean band = false;
        String sql = "delete from public.categoria where id=? ";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
//campos sin referencias
        lstpar.add(new Parametro(1, obj.getId()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static Categoria obtener(int pid) throws Exception {
        Categoria miCategoria = null;
        try {
            String sql = "select id,descripcion,id_padre from public.categoria where   id=? ";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pid));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            ArrayList<Categoria> lst = llenarCategorias(rs);
            for (Categoria c : lst) {
                miCategoria = c;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return miCategoria;
    }

    public static ArrayList<Categoria> obtener() throws Exception {
        ArrayList<Categoria> lst = new ArrayList<>();
        try {
            String sql = "select id,descripcion,id_padre from public.categoria; ";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarCategorias(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    private static ArrayList<Categoria> llenarCategorias(ConjuntoResultado cr) throws Exception {
        ArrayList<Categoria> lst = new ArrayList<Categoria>();
        Categoria obj = null;
        try {
            while (cr.next()) {
                obj = new Categoria();

//campos con referencias
//campos sin referencias
                obj.setId(cr.getInt(1));
                obj.setDescripcion(cr.getString(2));
                obj.setId_padre(cr.getInt(3));
                lst.add(obj);

            }
        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

}
