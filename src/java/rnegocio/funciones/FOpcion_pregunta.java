package rnegocio.funciones;

import accesodatos.Parametro;
import accesodatos.ConjuntoResultado;
import accesodatos.AccesoDatos;
import rnegocio.clases.Cuestionario;
import rnegocio.clases.Opcion_pregunta;
import rnegocio.clases.Pregunta;
import java.util.ArrayList;

public class FOpcion_pregunta {

    public static boolean insertar(Opcion_pregunta obj) throws Exception {
        boolean band = false;
        String sql = "insert into public.opcion_pregunta(id_pregunta, opcion, valor,tipo) values (?,?,?,?)";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
        lstpar.add(new Parametro(1, obj.getPregunta().getId()));

//campos sin referencias
        //lstpar.add(new Parametro(1,obj.getId()));
        lstpar.add(new Parametro(2, obj.getOpcion()));
        lstpar.add(new Parametro(3, obj.getValor()));
        lstpar.add(new Parametro(4, obj.getTipo()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static Opcion_pregunta insertarWithReturnInsert(Opcion_pregunta obj) throws Exception {
        Opcion_pregunta preguntaReturn = null;
        String sql = "insert into public.opcion_pregunta(id_pregunta, opcion, valor,tipo) values (?,?,?,?) RETURNING *";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
        lstpar.add(new Parametro(1, obj.getPregunta().getId()));

//campos sin referencias
        //lstpar.add(new Parametro(1,obj.getId()));
        lstpar.add(new Parametro(2, obj.getOpcion()));
        lstpar.add(new Parametro(3, obj.getValor()));
        lstpar.add(new Parametro(4, obj.getTipo()));
        try {
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            ArrayList<Opcion_pregunta> lst = llenarOpcion_preguntas(rs);
            for (Opcion_pregunta c : lst) {
                preguntaReturn = c;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return preguntaReturn;
    }

    public static boolean modificar(Opcion_pregunta obj) throws Exception {
        boolean band = false;
        String sql = "update public.opcion_pregunta set id=?,id_pregunta=?,opcion=?,valor=?,tipo=? where id=? and id_pregunta=?  ";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
        lstpar.add(new Parametro(2, obj.getPregunta().getId()));

        lstpar.add(new Parametro(7, obj.getPregunta().getId()));

//campos sin referencias
        lstpar.add(new Parametro(1, obj.getId()));
        lstpar.add(new Parametro(6, obj.getId()));
        lstpar.add(new Parametro(3, obj.getOpcion()));
        lstpar.add(new Parametro(4, obj.getValor()));
        lstpar.add(new Parametro(5, obj.getTipo()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static boolean eliminar(Opcion_pregunta obj) throws Exception {
        boolean band = false;
        String sql = "delete from public.opcion_pregunta where id=? and id_pregunta=? ";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
        lstpar.add(new Parametro(2, obj.getPregunta().getId()));

//campos sin referencias
        lstpar.add(new Parametro(1, obj.getId()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }
    public static boolean eliminarByPregunta(Pregunta obj) throws Exception {
        boolean band = false;
        String sql = "delete from public.opcion_pregunta as p where p.id_pregunta=?  ";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
        lstpar.add(new Parametro(1, obj.getId()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static Opcion_pregunta obtener(int pid, int pid_pregunta) throws Exception {
        Opcion_pregunta miOpcion_pregunta = null;
        try {
            String sql = "select id,id_pregunta,opcion,valor,tipo from public.opcion_pregunta where   id=? and id_pregunta=? ";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pid));
            lstpar.add(new Parametro(2, pid_pregunta));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            ArrayList<Opcion_pregunta> lst = llenarOpcion_preguntas(rs);
            for (Opcion_pregunta c : lst) {
                miOpcion_pregunta = c;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return miOpcion_pregunta;
    }

    public static ArrayList<Opcion_pregunta> obtener() throws Exception {
        ArrayList<Opcion_pregunta> lst = new ArrayList<>();
        try {

            String sql = "select id,id_pregunta,opcion,valor,tipo from public.opcion_pregunta; ";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarOpcion_preguntas(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    public static ArrayList<Opcion_pregunta> obtener(Cuestionario cuestionario) throws Exception {
        ArrayList<Opcion_pregunta> lst = new ArrayList<>();
        try {
            String sql = "select op.id,op.id_pregunta,op.opcion,op.valor,op.tipo from public.cuestionario as c inner join public.cuestionario_pregunta as cp on c.id=cp.id_cuestionario inner join public.pregunta as p on cp.id_pregunta=p.id inner join public.opcion_pregunta as op on op.id_pregunta=p.id  where c.id=? ";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, cuestionario.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql,lstpar);
            lst = llenarOpcion_preguntas(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    private static ArrayList<Opcion_pregunta> llenarOpcion_preguntas(ConjuntoResultado cr) throws Exception {
        ArrayList<Opcion_pregunta> lst = new ArrayList<Opcion_pregunta>();
        Opcion_pregunta obj = null;
        try {
            while (cr.next()) {
                obj = new Opcion_pregunta();

//campos con referencias
                obj.setPregunta(FPregunta.obtener(cr.getInt(2)));

//campos sin referencias
                obj.setId(cr.getInt(1));
                obj.setOpcion(cr.getString(3));
                obj.setValor(cr.getDouble(4));
                obj.setTipo(cr.getInt(5));
                lst.add(obj);

            }
        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

}
