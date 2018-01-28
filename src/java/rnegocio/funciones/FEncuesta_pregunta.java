package rnegocio.funciones;

import accesodatos.Parametro;
import accesodatos.ConjuntoResultado;
import accesodatos.AccesoDatos;
import rnegocio.clases.Encuesta_pregunta;
import rnegocio.clases.Pregunta;
import java.util.ArrayList;

public class FEncuesta_pregunta {

    public static boolean insertar(Encuesta_pregunta obj) throws Exception {
        boolean band = false;
        String sql = "insert into public.encuesta_pregunta values (?,?,?,?,?)";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
        lstpar.add(new Parametro(1, obj.getEncuesta().getId()));

        lstpar.add(new Parametro(2, obj.getPregunta().getId()));

//campos sin referencias
        lstpar.add(new Parametro(3, obj.getRespuesta()));
        lstpar.add(new Parametro(4, obj.getValorrespuesta()));
        lstpar.add(new Parametro(5, obj.getResumenpregunta()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static boolean modificar(Encuesta_pregunta obj) throws Exception {
        boolean band = false;
        String sql = "update public.encuesta_pregunta set id_encuesta=?,id_pregunta=?,respuesta=?,valorrespuesta=?,resumenpregunta=? where id_encuesta=? and id_pregunta=?  ";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
        lstpar.add(new Parametro(1, obj.getEncuesta().getId()));

        lstpar.add(new Parametro(6, obj.getEncuesta().getId()));

        lstpar.add(new Parametro(2, obj.getPregunta().getId()));

        lstpar.add(new Parametro(7, obj.getPregunta().getId()));

//campos sin referencias
        lstpar.add(new Parametro(3, obj.getRespuesta()));
        lstpar.add(new Parametro(4, obj.getValorrespuesta()));
        lstpar.add(new Parametro(5, obj.getResumenpregunta()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static boolean eliminar(Encuesta_pregunta obj) throws Exception {
        boolean band = false;
        String sql = "delete from public.encuesta_pregunta where id_encuesta=? and id_pregunta=? ";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
        lstpar.add(new Parametro(1, obj.getEncuesta().getId()));

        lstpar.add(new Parametro(2, obj.getPregunta().getId()));

//campos sin referencias
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static Encuesta_pregunta obtener(Long pid_encuesta, int pid_pregunta) throws Exception {
        Encuesta_pregunta miEncuesta_pregunta = null;
        try {
            String sql = "select id_encuesta,id_pregunta,respuesta,valorrespuesta,resumenpregunta from public.encuesta_pregunta where   id_encuesta=? and id_pregunta=? ";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pid_encuesta));
            lstpar.add(new Parametro(2, pid_pregunta));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            ArrayList<Encuesta_pregunta> lst = llenarEncuesta_preguntas(rs);
            for (Encuesta_pregunta c : lst) {
                miEncuesta_pregunta = c;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return miEncuesta_pregunta;
    }

    public static ArrayList<Encuesta_pregunta> obtener() throws Exception {
        ArrayList<Encuesta_pregunta> lst = new ArrayList<>();
        try {
            String sql = "select id_encuesta,id_pregunta,respuesta,valorrespuesta,resumenpregunta from public.encuesta_pregunta; ";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarEncuesta_preguntas(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Encuesta_pregunta> obtener(Pregunta obj) throws Exception {
        ArrayList<Encuesta_pregunta> lst = new ArrayList<>();
        try {
            String sql = "select id_encuesta,id_pregunta,respuesta,valorrespuesta,resumenpregunta from public.encuesta_pregunta where  id_pregunta=?;";
           ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, obj.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql,lstpar);
            lst = llenarEncuesta_preguntas(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    private static ArrayList<Encuesta_pregunta> llenarEncuesta_preguntas(ConjuntoResultado cr) throws Exception {
        ArrayList<Encuesta_pregunta> lst = new ArrayList<Encuesta_pregunta>();
        Encuesta_pregunta obj = null;
        try {
            while (cr.next()) {
                obj = new Encuesta_pregunta();

//campos con referencias
                obj.setEncuesta(FEncuesta.obtener(cr.getInt(1)));

                obj.setPregunta(FPregunta.obtener(cr.getInt(2)));

//campos sin referencias
                obj.setRespuesta(cr.getString(3));
                obj.setValorrespuesta(cr.getDouble(4));
                obj.setResumenpregunta(cr.getString(5));
                lst.add(obj);

            }
        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

}
