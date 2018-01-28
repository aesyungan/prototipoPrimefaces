package rnegocio.funciones;

import accesodatos.Parametro;
import accesodatos.ConjuntoResultado;
import accesodatos.AccesoDatos;
import rnegocio.clases.Ciudadano;
import rnegocio.clases.Cuestionario;
import rnegocio.clases.Encuesta;
import rnegocio.clases.Usuario;
import java.util.ArrayList;

public class FEncuesta {

    public static boolean insertar(Encuesta obj) throws Exception {
        boolean band = false;
        String sql = "insert into public.encuesta(fecha, id_ciudadano, id_cuestionario, id_usuario, estado) values (?,?,?,?,?)";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
        lstpar.add(new Parametro(2, obj.getCiudadano().getId()));

        lstpar.add(new Parametro(3, obj.getCuestionario().getId()));

        lstpar.add(new Parametro(4, obj.getUsuario().getId()));

//campos sin referencias
        //lstpar.add(new Parametro(1,obj.getId()));
        lstpar.add(new Parametro(1, obj.getFecha()));
        lstpar.add(new Parametro(5, obj.getEstado()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static Encuesta insertarWithReturnInsert(Encuesta obj) throws Exception {
        Encuesta encuesta = null;
        boolean band = false;
        String sql = "insert into public.encuesta(fecha, id_ciudadano, id_cuestionario, id_usuario, estado) values (?,?,?,?,?) RETURNING *;";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
        lstpar.add(new Parametro(2, obj.getCiudadano().getId()));

        lstpar.add(new Parametro(3, obj.getCuestionario().getId()));

        lstpar.add(new Parametro(4, obj.getUsuario().getId()));

//campos sin referencias
        //lstpar.add(new Parametro(1,obj.getId()));
        lstpar.add(new Parametro(1, obj.getFecha()));
        lstpar.add(new Parametro(5, obj.getEstado()));
        try {
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            ArrayList<Encuesta> lst = llenarEncuestas(rs);
            for (Encuesta c : lst) {
                encuesta = c;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return encuesta;
    }

    public static boolean modificar(Encuesta obj) throws Exception {
        boolean band = false;
        String sql = "UPDATE public.encuesta set  fecha=?, id_ciudadano=?, id_cuestionario=?, id_usuario=?,  estado=? where id=?";
        // String sql = "update public.encuesta set id=?,fecha=?,id_ciudadano=?,id_cuestionario=?,id_usuario=?,estado=? where id=?  ";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
        lstpar.add(new Parametro(2, obj.getCiudadano().getId()));

        lstpar.add(new Parametro(3, obj.getCuestionario().getId()));

        lstpar.add(new Parametro(4, obj.getUsuario().getId()));

//campos sin referencias
        // lstpar.add(new Parametro(1, obj.getId()));
        lstpar.add(new Parametro(6, obj.getId()));
        lstpar.add(new Parametro(1, obj.getFecha()));
        lstpar.add(new Parametro(5, obj.getEstado()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static boolean eliminar(Encuesta obj) throws Exception {
        boolean band = false;
        String sql = "delete from public.encuesta where id=? ";
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

    public static Encuesta obtener(int pid) throws Exception {
        Encuesta miEncuesta = null;
        try {
            String sql = "select id,fecha,id_ciudadano,id_cuestionario,id_usuario,estado from public.encuesta where   id=? ";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pid));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            ArrayList<Encuesta> lst = llenarEncuestas(rs);
            for (Encuesta c : lst) {
                miEncuesta = c;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return miEncuesta;
    }

    public static ArrayList<Encuesta> obtener(Cuestionario cuestionario, Usuario usuario) throws Exception {
        ArrayList<Encuesta> lst = new ArrayList<>();
        try {
            String sql = "select e.id, e.fecha, e.id_ciudadano, e.id_cuestionario, e.id_usuario, e.estado from public.encuesta as e inner join public.cuestionario as c on c.id=e.id_cuestionario where e.estado=1 and  c.id=? and e.id_usuario=? ORDER BY e.id DESC;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, cuestionario.getId()));
            lstpar.add(new Parametro(2, usuario.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarEncuestas(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    public static ArrayList<Encuesta> obtener() throws Exception {
        ArrayList<Encuesta> lst = new ArrayList<>();
        try {
            String sql = "select id,fecha,id_ciudadano,id_cuestionario,id_usuario,estado from public.encuesta; ";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarEncuestas(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    private static ArrayList<Encuesta> llenarEncuestas(ConjuntoResultado cr) throws Exception {
        ArrayList<Encuesta> lst = new ArrayList<Encuesta>();
        Encuesta obj = null;
        try {
            while (cr.next()) {
                obj = new Encuesta();

//campos con referencias
                obj.setCiudadano(FCiudadano.obtener(cr.getInt(3)));

                obj.setCuestionario(FCuestionario.obtener(cr.getInt(4)));

                obj.setUsuario(FUsuario.obtener(cr.getInt(5)));

//campos sin referencias
                obj.setId(cr.getInt(1));
                obj.setFecha(cr.getLong(2));
                obj.setEstado(cr.getInt(6));
                lst.add(obj);

            }
        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

}
