package rnegocio.funciones;

import accesodatos.Parametro;
import accesodatos.ConjuntoResultado;
import accesodatos.AccesoDatos;
import rnegocio.clases.Cuestionario;
import rnegocio.clases.Encuesta;
import rnegocio.clases.Usuario;
import java.util.ArrayList;

public class FUsuario {

    public static boolean insertar(Usuario obj) throws Exception {
        boolean band = false;
        String sql = "insert into public.usuario(nombrecompleto, email, usuario, clave, activo,foto,rol) values (?,?,?,?,?,?,?)";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
//campos sin referencias
        //lstpar.add(new Parametro(1,obj.getId()));
        lstpar.add(new Parametro(1, obj.getNombrecompleto()));
        lstpar.add(new Parametro(2, obj.getEmail()));
        lstpar.add(new Parametro(3, obj.getUsuario()));
        lstpar.add(new Parametro(4, obj.getClave()));
        lstpar.add(new Parametro(5, obj.getActivo()));
        lstpar.add(new Parametro(6, obj.getFoto()));
        lstpar.add(new Parametro(7, obj.getRol()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static Usuario insertarWithReturnInsert(Usuario obj) throws Exception {
        Usuario usuario = null;
        String sql = "insert into public.usuario(nombrecompleto, email, usuario, clave, activo,foto,rol) values (?,?,?,?,?,?,?)RETURNING *;";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
//campos sin referencias
        //lstpar.add(new Parametro(1,obj.getId()));
        lstpar.add(new Parametro(1, obj.getNombrecompleto()));
        lstpar.add(new Parametro(2, obj.getEmail()));
        lstpar.add(new Parametro(3, obj.getUsuario()));
        lstpar.add(new Parametro(4, obj.getClave()));
        lstpar.add(new Parametro(5, obj.getActivo()));
        lstpar.add(new Parametro(6, obj.getFoto()));
        lstpar.add(new Parametro(7, obj.getRol()));
        try {
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            ArrayList<Usuario> lst = llenarUsuarios(rs);
            for (Usuario c : lst) {
                usuario = c;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return usuario;
    }

    public static boolean modificar(Usuario obj) throws Exception {
        boolean band = false;
        String sql = "update public.usuario set id=?,nombrecompleto=?,email=?,usuario=?,clave=?,activo=?,foto=? ,rol=? where id=?  ";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
//campos sin referencias
        lstpar.add(new Parametro(1, obj.getId()));
        lstpar.add(new Parametro(9, obj.getId()));
        lstpar.add(new Parametro(2, obj.getNombrecompleto()));
        lstpar.add(new Parametro(3, obj.getEmail()));
        lstpar.add(new Parametro(4, obj.getUsuario()));
        lstpar.add(new Parametro(5, obj.getClave()));
        lstpar.add(new Parametro(6, obj.getActivo()));
        lstpar.add(new Parametro(7, obj.getFoto()));
        lstpar.add(new Parametro(8, obj.getRol()));

        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static boolean eliminarJmeter(String name) throws Exception {
        boolean band = false;
        String sql = "delete from public.usuario where usuario=? ";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
//campos sin referencias
        lstpar.add(new Parametro(1, name));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static boolean eliminar(Usuario obj) throws Exception {
        boolean band = false;
        String sql = "delete from public.usuario where id=? ";
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

    public static Usuario obtener(int pid) throws Exception {
        Usuario miUsuario = null;
        try {
            String sql = "select id,nombrecompleto,email,usuario,clave,activo,foto,rol from public.usuario where   id=? ";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pid));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            ArrayList<Usuario> lst = llenarUsuarios(rs);
            for (Usuario c : lst) {
                miUsuario = c;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return miUsuario;
    }

    public static Usuario obtenerUserName(Usuario u) throws Exception {
        Usuario miUsuario = null;
        try {
            String sql = "select id,nombrecompleto,email,usuario,clave,activo,foto,rol from public.usuario where usuario  like ?;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, u.getUsuario()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            ArrayList<Usuario> lst = llenarUsuarios(rs);
            for (Usuario c : lst) {
                miUsuario = c;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return miUsuario;
    }

    public static ArrayList<Usuario> obtener() throws Exception {
        ArrayList<Usuario> lst = new ArrayList<>();
        try {
            String sql = "select id,nombrecompleto,email,usuario,clave,activo,foto,rol from public.usuario order by id desc; ";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarUsuarios(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    public static ArrayList<Usuario> obtener(Cuestionario c) throws Exception {
        ArrayList<Usuario> lst = new ArrayList<>();
        try {
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, c.getId()));
            String sql = "select DISTINCT u.id, u.nombrecompleto, u.email, u.usuario, u.clave, u.activo, u.foto, u.rol from public.cuestionario as c inner join public.encuesta as e on e.id_cuestionario=c.id  inner join usuario as u on u.id=e.id_usuario  where c.id=? and e.estado=1 order by u.nombrecompleto asc";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarUsuarios(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    private static ArrayList<Usuario> llenarUsuarios(ConjuntoResultado cr) throws Exception {
        ArrayList<Usuario> lst = new ArrayList<Usuario>();
        Usuario obj = null;
        try {
            while (cr.next()) {
                obj = new Usuario();

//campos con referencias
//campos sin referencias
                obj.setId(cr.getInt(1));
                obj.setNombrecompleto(cr.getString(2));
                obj.setEmail(cr.getString(3));
                obj.setUsuario(cr.getString(4));
                obj.setClave(cr.getString(5));
                obj.setActivo(cr.getBoolean(6));
                obj.setFoto(cr.getString(7));
                obj.setRol(cr.getInt(8));
                lst.add(obj);

            }
        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

}
