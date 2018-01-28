package rnegocio.funciones;

import accesodatos.Parametro;
import accesodatos.ConjuntoResultado;
import accesodatos.AccesoDatos;
import rnegocio.clases.Ciudadano;
import rnegocio.clases.Pregunta;
import java.util.ArrayList;

public class FCiudadano {

    public static boolean insertar(Ciudadano obj) throws Exception {
        boolean band = false;
        String sql = "insert into public.ciudadano(ci, pasaporte, fecha_nac, nivel_instruccion, genero) values (?,?,?,?,?)";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
//campos sin referencias
// lstpar.add(new Parametro(1,obj.getId()));
        lstpar.add(new Parametro(1, obj.getCi()));
        lstpar.add(new Parametro(2, obj.getPasaporte()));
        lstpar.add(new Parametro(3, obj.getFecha_nac()));
        lstpar.add(new Parametro(4, obj.getNivel_instruccion()));
        lstpar.add(new Parametro(5, obj.getGenero()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static Ciudadano insertarWithReturnInsert(Ciudadano obj) throws Exception {
        Ciudadano ciudadano = null;
        boolean band = false;
        String sql = "insert into public.ciudadano(ci, pasaporte, fecha_nac, nivel_instruccion, genero) values (?,?,?,?,?) RETURNING *;";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
//campos sin referencias
// lstpar.add(new Parametro(1,obj.getId()));
        lstpar.add(new Parametro(1, obj.getCi()));
        lstpar.add(new Parametro(2, obj.getPasaporte()));
        lstpar.add(new Parametro(3, obj.getFecha_nac()));
        lstpar.add(new Parametro(4, obj.getNivel_instruccion()));
        lstpar.add(new Parametro(5, obj.getGenero()));
        try {
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            ArrayList<Ciudadano> lst = llenarCiudadanos(rs);
            for (Ciudadano c : lst) {
                ciudadano = c;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return ciudadano;
    }

    public static boolean modificar(Ciudadano obj) throws Exception {
        boolean band = false;
        String sql = "update public.ciudadano set id=?,ci=?,pasaporte=?,fecha_nac=?,nivel_instruccion=?,genero=? where id=?  ";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
//campos sin referencias
        lstpar.add(new Parametro(1, obj.getId()));
        lstpar.add(new Parametro(7, obj.getId()));
        lstpar.add(new Parametro(2, obj.getCi()));
        lstpar.add(new Parametro(3, obj.getPasaporte()));
        lstpar.add(new Parametro(4, obj.getFecha_nac()));
        lstpar.add(new Parametro(5, obj.getNivel_instruccion()));
        lstpar.add(new Parametro(6, obj.getGenero()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static boolean eliminar(Ciudadano obj) throws Exception {
        boolean band = false;
        String sql = "delete from public.ciudadano where id=? ";
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

    public static Ciudadano obtener(int pid) throws Exception {
        Ciudadano miCiudadano = null;
        try {
            String sql = "select id,ci,pasaporte,fecha_nac,nivel_instruccion,genero from public.ciudadano where   id=? ";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pid));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            ArrayList<Ciudadano> lst = llenarCiudadanos(rs);
            for (Ciudadano c : lst) {
                miCiudadano = c;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return miCiudadano;
    }
    public static Ciudadano obtener(String cedula) throws Exception {
        Ciudadano miCiudadano = null;
        try {
            String sql = "select *from  public.ciudadano where ci like '"+cedula+"' limit 1; ";
            //ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            //lstpar.add(new Parametro(1, cedula));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            ArrayList<Ciudadano> lst = llenarCiudadanos(rs);
            for (Ciudadano c : lst) {
                miCiudadano = c;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return miCiudadano;
    }

    public static ArrayList<Ciudadano> obtener() throws Exception {
        ArrayList<Ciudadano> lst = new ArrayList<>();
        try {
            String sql = "select id,ci,pasaporte,fecha_nac,nivel_instruccion,genero from public.ciudadano; ";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarCiudadanos(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    private static ArrayList<Ciudadano> llenarCiudadanos(ConjuntoResultado cr) throws Exception {
        ArrayList<Ciudadano> lst = new ArrayList<Ciudadano>();
        Ciudadano obj = null;
        try {
            while (cr.next()) {
                obj = new Ciudadano();

//campos con referencias
//campos sin referencias
                obj.setId(cr.getInt(1));
                obj.setCi(cr.getString(2));
                obj.setPasaporte(cr.getString(3));
                obj.setFecha_nac(cr.getDate(4));
                obj.setNivel_instruccion(cr.getInt(5));
                obj.setGenero(cr.getInt(6));
                lst.add(obj);

            }
        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

}
