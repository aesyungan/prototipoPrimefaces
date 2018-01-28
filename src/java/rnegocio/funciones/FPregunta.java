package rnegocio.funciones;

import accesodatos.Parametro;
import accesodatos.ConjuntoResultado;
import accesodatos.AccesoDatos;
import rnegocio.clases.Cuestionario;
import rnegocio.clases.Pregunta;
import java.util.ArrayList;

public class FPregunta {

    public static boolean insertar(Pregunta obj) throws Exception {
        boolean band = false;
        String sql = "insert into public.pregunta(nombre, enunciado, id_categoria, id_tipopregunta, fcreada, fmodificada, creadapor, modificadapor, estado) values (?,?,?,?,?,?,?,?,?)";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
        lstpar.add(new Parametro(3, obj.getCategoria().getId()));

        lstpar.add(new Parametro(4, obj.getTipo_pregunta().getId()));

//campos sin referencias
// lstpar.add(new Parametro(1,obj.getId()));
        lstpar.add(new Parametro(1, obj.getNombre()));
        lstpar.add(new Parametro(2, obj.getEnunciado()));
        lstpar.add(new Parametro(5, obj.getFcreada()));
        lstpar.add(new Parametro(6, obj.getFmodificada()));
        lstpar.add(new Parametro(7, obj.getCreadapor()));
        lstpar.add(new Parametro(8, obj.getModificadapor()));
        lstpar.add(new Parametro(9, obj.getEstado()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static boolean modificar(Pregunta obj) throws Exception {
        boolean band = false;
        String sql = "update public.pregunta set id=?,nombre=?,enunciado=?,id_categoria=?,id_tipopregunta=?,fcreada=?,fmodificada=?,creadapor=?,modificadapor=?,estado=? where id=?  ";
        ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

//campos con referencias
        lstpar.add(new Parametro(4, obj.getCategoria().getId()));

        lstpar.add(new Parametro(5, obj.getTipo_pregunta().getId()));

//campos sin referencias
        lstpar.add(new Parametro(1, obj.getId()));
        lstpar.add(new Parametro(11, obj.getId()));
        lstpar.add(new Parametro(2, obj.getNombre()));
        lstpar.add(new Parametro(3, obj.getEnunciado()));
        lstpar.add(new Parametro(6, obj.getFcreada()));
        lstpar.add(new Parametro(7, obj.getFmodificada()));
        lstpar.add(new Parametro(8, obj.getCreadapor()));
        lstpar.add(new Parametro(9, obj.getModificadapor()));
        lstpar.add(new Parametro(10, obj.getEstado()));
        try {
            band = AccesoDatos.ejecutaComando1(sql, lstpar);
        } catch (Exception ex) {
            throw ex;
        }
        return band;
    }

    public static boolean eliminar(Pregunta obj) throws Exception {
        boolean band = false;
        String sql = "delete from public.pregunta where id=? ";
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

    public static Pregunta obtener(int pid) throws Exception {
        Pregunta miPregunta = null;
        try {
            String sql = "select id,nombre,enunciado,id_categoria,id_tipopregunta,fcreada,fmodificada,creadapor,modificadapor,estado from public.pregunta where   id=? ";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pid));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            ArrayList<Pregunta> lst = llenarPreguntas(rs);
            for (Pregunta c : lst) {
                miPregunta = c;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return miPregunta;
    }

//RETURNING *    
    public static Pregunta insertarWithReturnInsert(Pregunta obj) throws Exception {
        Pregunta miPregunta = null;
        try {

            String sql = "insert into public.pregunta(nombre, enunciado, id_categoria, id_tipopregunta, fcreada, fmodificada, creadapor, modificadapor, estado) values (?,?,?,?,?,?,?,?,?) RETURNING *";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

            //campos con referencias
            lstpar.add(new Parametro(3, obj.getCategoria().getId()));

            lstpar.add(new Parametro(4, obj.getTipo_pregunta().getId()));

            //campos sin referencias
            // lstpar.add(new Parametro(1,obj.getId()));
            lstpar.add(new Parametro(1, obj.getNombre()));
            lstpar.add(new Parametro(2, obj.getEnunciado()));
            lstpar.add(new Parametro(5, obj.getFcreada()));
            lstpar.add(new Parametro(6, obj.getFmodificada()));
            lstpar.add(new Parametro(7, obj.getCreadapor()));
            lstpar.add(new Parametro(8, obj.getModificadapor()));
            lstpar.add(new Parametro(9, obj.getEstado()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            ArrayList<Pregunta> lst = llenarPreguntas(rs);
            for (Pregunta c : lst) {
                miPregunta = c;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return miPregunta;
    }

    //RETURNING *    
    public static ArrayList<Pregunta> obtener(Cuestionario obj) throws Exception {
        ArrayList<Pregunta> lst = new ArrayList<>();
        try {

            String sql = "select p.id, p.nombre, p.enunciado, p.id_categoria, p.id_tipopregunta, p.fcreada, p.fmodificada, p.creadapor, p.modificadapor, p.estado from public.cuestionario as c inner join public.cuestionario_pregunta as cp on c.id=cp.id_cuestionario inner join public.pregunta as p on cp.id_pregunta=p.id where c.id=? and p.estado=true order by cp.orden asc";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();

            //campos con referencias
            //campos sin referencias
            // lstpar.add(new Parametro(1,obj.getId()));
            lstpar.add(new Parametro(1, obj.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarPreguntas(rs);
            

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    
    public static ArrayList<Pregunta> obtener() throws Exception {
        ArrayList<Pregunta> lst = new ArrayList<>();
        try {
            String sql = "select id,nombre,enunciado,id_categoria,id_tipopregunta,fcreada,fmodificada,creadapor,modificadapor,estado from public.pregunta; ";
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarPreguntas(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    private static ArrayList<Pregunta> llenarPreguntas(ConjuntoResultado cr) throws Exception {
        ArrayList<Pregunta> lst = new ArrayList<Pregunta>();
        Pregunta obj = null;
        try {
            while (cr.next()) {
                obj = new Pregunta();

//campos con referencias
                obj.setCategoria(FCategoria.obtener(cr.getInt(4)));

                obj.setTipo_pregunta(FTipo_pregunta.obtener(cr.getInt(5)));

//campos sin referencias
                obj.setId(cr.getInt(1));
                obj.setNombre(cr.getString(2));
                obj.setEnunciado(cr.getString(3));
                obj.setFcreada(cr.getLong(6));
                obj.setFmodificada(cr.getLong(7));
                obj.setCreadapor(cr.getInt(8));
                obj.setModificadapor(cr.getInt(9));
                obj.setEstado(cr.getBoolean(10));
                lst.add(obj);

            }
        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

}
