package rnegocio.funciones;

import accesodatos.Parametro;
import accesodatos.ConjuntoResultado;
import accesodatos.AccesoDatos;
import rnegocio.clases.Cuestionario;
import java.util.ArrayList;

public class FCuestionario {

    public static boolean insertar(Cuestionario obj) throws Exception {
        boolean band = false;
        String sql = "insert into public.cuestionario( nombre, cabecera, pui, rutalogo, id_tipocuestionario, estado, fcreado, fmodificado, creadopr, modificadopor) values (?,?,?,?,?,?,?,?,?,?)";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
        lstpar.add(new Parametro(5, obj.getTipo_cuestionario().getId()));

//campos sin referencias
        //lstpar.add(new Parametro(1,obj.getId()));
        lstpar.add(new Parametro(1, obj.getNombre()));
        lstpar.add(new Parametro(2, obj.getCabecera()));
        lstpar.add(new Parametro(3, obj.getPui()));
        lstpar.add(new Parametro(4, obj.getRutalogo()));
        lstpar.add(new Parametro(6, obj.getEstado()));
        lstpar.add(new Parametro(7, obj.getFcreado()));
        lstpar.add(new Parametro(8, obj.getFmodificado()));
        lstpar.add(new Parametro(9, obj.getCreadopr()));
        lstpar.add(new Parametro(10, obj.getModificadopor()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static boolean modificar(Cuestionario obj) throws Exception {
        boolean band = false;
        String sql = "update public.cuestionario set id=?,nombre=?,cabecera=?,pui=?,rutalogo=?,id_tipocuestionario=?,estado=?,fcreado=?,fmodificado=?,creadopr=?,modificadopor=? where id=?  ";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
        lstpar.add(new Parametro(6, obj.getTipo_cuestionario().getId()));

//campos sin referencias
        lstpar.add(new Parametro(1, obj.getId()));
        lstpar.add(new Parametro(12, obj.getId()));
        lstpar.add(new Parametro(2, obj.getNombre()));
        lstpar.add(new Parametro(3, obj.getCabecera()));
        lstpar.add(new Parametro(4, obj.getPui()));
        lstpar.add(new Parametro(5, obj.getRutalogo()));
        lstpar.add(new Parametro(7, obj.getEstado()));
        lstpar.add(new Parametro(8, obj.getFcreado()));
        lstpar.add(new Parametro(9, obj.getFmodificado()));
        lstpar.add(new Parametro(10, obj.getCreadopr()));
        lstpar.add(new Parametro(11, obj.getModificadopor()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static boolean eliminar(Cuestionario obj) throws Exception {
        boolean band = false;
        String sql = "delete from public.cuestionario where id=? ";
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

    public static Cuestionario obtener(int pid) throws Exception {
        Cuestionario miCuestionario = null;
        try {
            String sql = "select id,nombre,cabecera,pui,rutalogo,id_tipocuestionario,estado,fcreado,fmodificado,creadopr,modificadopor from public.cuestionario where   id=? ";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pid));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            ArrayList<Cuestionario> lst = llenarCuestionarios(rs);
            for (Cuestionario c : lst) {
                miCuestionario = c;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return miCuestionario;
    }

    public static ArrayList<Cuestionario> obtener() throws Exception {
        ArrayList<Cuestionario> lst = new ArrayList<>();
        try {
            String sql = "select id,nombre,cabecera,pui,rutalogo,id_tipocuestionario,estado,fcreado,fmodificado,creadopr,modificadopor from public.cuestionario; ";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarCuestionarios(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Cuestionario> obtenerActivo() throws Exception {
        ArrayList<Cuestionario> lst = new ArrayList<>();
        try {
            String sql = "select id,nombre,cabecera,pui,rutalogo,id_tipocuestionario,estado,fcreado,fmodificado,creadopr,modificadopor from public.cuestionario where  estado=true;";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarCuestionarios(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    public static ArrayList<Cuestionario> obtenerIdUsuario(int id) throws Exception {
        ArrayList<Cuestionario> lst = new ArrayList<>();
        try {
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, id));
            String sql = "select id,nombre,cabecera,pui,rutalogo,id_tipocuestionario,estado,fcreado,fmodificado,creadopr,modificadopor from public.cuestionario where creadopr=? order by id desc; ";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarCuestionarios(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    private static ArrayList<Cuestionario> llenarCuestionarios(ConjuntoResultado cr) throws Exception {
        ArrayList<Cuestionario> lst = new ArrayList<Cuestionario>();
        Cuestionario obj = null;
        try {
            while (cr.next()) {
                obj = new Cuestionario();

//campos con referencias
                obj.setTipo_cuestionario(FTipo_cuestionario.obtener(cr.getInt(6)));

//campos sin referencias
                obj.setId(cr.getInt(1));
                obj.setNombre(cr.getString(2));
                obj.setCabecera(cr.getString(3));
                obj.setPui(cr.getString(4));
                obj.setRutalogo(cr.getString(5));
                obj.setEstado(cr.getBoolean(7));
                obj.setFcreado(cr.getLong(8));
                obj.setFmodificado(cr.getLong(9));
                obj.setCreadopr(cr.getInt(10));
                obj.setModificadopor(cr.getInt(11));
                lst.add(obj);

            }
        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

}
