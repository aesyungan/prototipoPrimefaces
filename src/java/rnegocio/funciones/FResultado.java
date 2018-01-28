/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rnegocio.funciones;

import accesodatos.AccesoDatos;
import accesodatos.ConjuntoResultado;
import accesodatos.Parametro;
import rnegocio.clases.Categoria;
import rnegocio.clases.Cuestionario;
import rnegocio.clases.Encuesta;
import rnegocio.clases.Pregunta;
import rnegocio.clases.Resultado;
import rnegocio.clases.Usuario;
import java.util.ArrayList;

/**
 *
 * @author XL
 */
//ccase que permite cargar los datos desde la base de datos
public class FResultado {

    public static ArrayList<Resultado> obtenerRespuestaSeleccionMultipleONumerica(Pregunta pregunta) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select trim(lower(respuesta)) as respuestar, count(*) as cantidad from public.encuesta_pregunta as ep inner join public.encuesta as e on e.id = ep.id_encuesta where e.estado=1 and  ep.id_pregunta=? group by respuestar order by cantidad desc ;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pregunta.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Resultado> obtenerRespuestaSeleccionMultipleONumericaFecha(Pregunta pregunta,String inicio,String fin) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select trim(lower(respuesta)) as respuestar, count(*) as cantidad from public.encuesta_pregunta as ep inner join public.encuesta as e on e.id = ep.id_encuesta where e.estado=1 and  ep.id_pregunta=? and (to_timestamp(e.fecha/1000)::date between '"+inicio+"' and '"+fin+"') group by respuestar order by cantidad desc ;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pregunta.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    public static ArrayList<Resultado> obtenerRespuestaCasillasDeVerificacion(Pregunta pregunta) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select respuestar,cantidad from(select regexp_split_to_table(lower(respuesta),'[\\\\-\\\\.\\\\,\\\\:\\\\_\\\\ \\\\;\\\\+\\\\#-]') as respuestar ,count(*) as cantidad from public.encuesta_pregunta where id_pregunta=? group by respuestar ) as tabla_respuesta where not  respuestar='';";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pregunta.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
//-#-
     public static ArrayList<Resultado> obtenerRespuestaCasillasDeVerificacionPorDelimitadorDefault(Pregunta pregunta) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select respuestar,cantidad from(select regexp_split_to_table(lower(ep.respuesta),'-#-') as respuestar ,count(*) as cantidad from public.encuesta_pregunta  as ep  inner join public.encuesta as e on e.id=ep.id_encuesta where ep.id_pregunta=? and e.estado=1 group by respuestar ) as tabla_respuesta where not  respuestar='' order by cantidad desc ;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pregunta.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
     public static ArrayList<Resultado> obtenerRespuestaCasillasDeVerificacionPorDelimitadorDefaultFecha(Pregunta pregunta,String inicio ,String fin) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select respuestar,cantidad from(select regexp_split_to_table(lower(ep.respuesta),'-#-') as respuestar ,count(*) as cantidad from public.encuesta_pregunta  as ep  inner join public.encuesta as e on e.id=ep.id_encuesta where ep.id_pregunta=? and e.estado=1 and (to_timestamp(e.fecha/1000)::date between '"+inicio+"' and '"+fin+"') group by respuestar ) as tabla_respuesta where not  respuestar='' order by cantidad desc ;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pregunta.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
     //casillar de verificacion con otra opcion sin otra opcion id=5
     public static ArrayList<Resultado> obtenerRespuestaCasillasDeVerificacionSinOtraOpcion(Pregunta pregunta) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select op.opcion,(select count(*) from public.encuesta_pregunta as ep inner join public.encuesta as e on e.id = ep.id_encuesta where e.estado=1  and  ep.id_pregunta=p.id and ep.respuesta like '%'||op.opcion||'%' )as cantidadT from public.pregunta as p inner join public.opcion_pregunta as op on op.id_pregunta=p.id   where p.id=? order by cantidadT desc";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pregunta.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
     public static ArrayList<Resultado> obtenerRespuestaCasillasDeVerificacionSinOtraOpcionFecha(Pregunta pregunta ,String inicio,String fin) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select op.opcion,(select count(*) from public.encuesta_pregunta as ep inner join public.encuesta as e on e.id = ep.id_encuesta where e.estado=1 and  ep.id_pregunta=p.id and ep.respuesta like '%'||op.opcion||'%' and (to_timestamp(e.fecha/1000)::date between '"+inicio+"' and '"+fin+"') )as cantidadT from public.pregunta as p inner join public.opcion_pregunta as op on op.id_pregunta=p.id   where p.id=? order by cantidadT desc";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pregunta.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
     
    public static ArrayList<Resultado> obtenerRespuestaCorta(Pregunta pregunta) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select respuesta ,1 as numero from public.encuesta_pregunta where id_pregunta =?;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, pregunta.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
//otras
    //optener items de casillas de verificacion dada la id  pregunta y la id  encuesta

    public static ArrayList<Resultado> obtenerCasillasVerificacionSeleccionados(Encuesta e, Pregunta p) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select regexp_split_to_table(respuesta,'-#-') as respuestar ,1 as cantidad from public.encuesta_pregunta where id_pregunta=? and  id_encuesta=?; ";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, p.getId()));
            lstpar.add(new Parametro(2, e.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    //seleccion multiple con descripcion
    public static ArrayList<Resultado> obtenerSeleccionMultipleSinDescripcion( Pregunta p) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select split_part(lower(ep.respuesta),'-#-',2) as respuestar ,count(*) as cantidad from public.encuesta_pregunta as ep inner join public.encuesta as e on e.id = ep.id_encuesta where e.estado=1 and  ep.id_pregunta=?  group by respuestar order by cantidad Desc";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, p.getId()));
       
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Resultado> obtenerSeleccionMultipleSinDescripcionFecha( Pregunta p,String inicio,String fin) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select split_part(lower(ep.respuesta),'-#-',2) as respuestar ,count(*) as cantidad from public.encuesta_pregunta as ep inner join public.encuesta as e on e.id = ep.id_encuesta where e.estado=1 and  ep.id_pregunta=? and (to_timestamp(e.fecha/1000)::date between '"+inicio+"' and '"+fin+"')  group by respuestar order by cantidad Desc";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, p.getId()));
       
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Resultado> obtenerSeleccionMultipleSoloDescripcionDElimitadorEspacio( Pregunta p) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select result.respuestar, result.cantidad from (select regexp_split_to_table(lower(res.resp),'[\\\\-\\\\.\\\\,\\\\:\\\\_\\\\ \\\\;\\\\+\\\\#-]') as respuestar , count(*) as cantidad from (select split_part(lower(ep.respuesta),'-#-',1) as resp from public.encuesta_pregunta as ep inner join public.encuesta as e on e.id = ep.id_encuesta where e.estado=1 and  ep.id_pregunta=?) as res group by respuestar order by cantidad desc) as result where not  result.respuestar='' ";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, p.getId()));
       
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Resultado> obtenerSeleccionMultipleSoloDescripcionDElimitadorEspacioFecha( Pregunta p,String inicio,String fin) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select result.respuestar, result.cantidad from (select regexp_split_to_table(lower(res.resp),'[\\\\-\\\\.\\\\,\\\\:\\\\_\\\\ \\\\;\\\\+\\\\#-]') as respuestar , count(*) as cantidad from (select split_part(lower(ep.respuesta),'-#-',1) as resp from public.encuesta_pregunta as ep inner join public.encuesta as e on e.id = ep.id_encuesta where e.estado=1 and  ep.id_pregunta=? and (to_timestamp(e.fecha/1000)::date between '"+inicio+"' and '"+fin+"') ) as res group by respuestar order by cantidad desc) as result where not  result.respuestar='' ";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, p.getId()));
       
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Resultado> obtenerSeleccionMultipleSoloDescripcionConDelimitador( Pregunta p,String delimitador) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select result.respuestar, result.cantidad from (select trim(from regexp_split_to_table(lower(res.resp),'"+delimitador+"')) as respuestar , count(*) as cantidad from (select split_part(lower(ep.respuesta),'-#-',1) as resp from public.encuesta_pregunta as ep inner join public.encuesta as e on e.id = ep.id_encuesta where e.estado=1 and  ep.id_pregunta=?) as res group by respuestar order by cantidad desc) as result where not  result.respuestar=''";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, p.getId()));
       
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Resultado> obtenerSeleccionMultipleSoloDescripcionConDelimitadorFecha( Pregunta p,String delimitador,String inicio,String fin) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select result.respuestar, result.cantidad from (select trim(from regexp_split_to_table(lower(res.resp),'"+delimitador+"')) as respuestar , count(*) as cantidad from (select split_part(lower(ep.respuesta),'-#-',1) as resp from public.encuesta_pregunta as ep inner join public.encuesta as e on e.id = ep.id_encuesta where e.estado=1 and  ep.id_pregunta=? and (to_timestamp(e.fecha/1000)::date between '"+inicio+"' and '"+fin+"') ) as res group by respuestar order by cantidad desc) as result where not  result.respuestar=''";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, p.getId()));
       
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    //fin //seleccion multiple con descripcion

    public static ArrayList<Resultado> obtenerNumeroDeEncuestasRealizadasPorCadaCuestionario(Usuario usuario) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select  c.nombre, count(*) from public.cuestionario as c inner join public.encuesta as e on e.id_cuestionario=c.id where e.estado=1 and c.creadopr=? group by c.nombre  limit 3;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, usuario.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Resultado> obtenerNumeroDeEncuestasRealizadasPorCadaCuestionario() throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select  c.nombre, count(*) from public.cuestionario as c inner join public.encuesta as e on e.id_cuestionario=c.id   group by c.nombre  limit 3;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    public static ArrayList<Resultado> obtenerNumeroDeEcuestasContestadasPorMes(Usuario usuario) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select  to_char(to_timestamp(e.fecha/1000),'YYYY/Month') as fecha ,count(*) from public.cuestionario as c inner join public.encuesta as e on e.id_cuestionario=c.id where c.creadopr=? and e.estado=1 group by to_char(to_timestamp(e.fecha/1000),'YYYY/Month')";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, usuario.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Resultado> obtenerNumeroDeEcuestasContestadasPorMes() throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select  to_char(to_timestamp(e.fecha/1000),'YYYY/Month') as fecha ,count(*) from public.cuestionario as c inner join public.encuesta as e on e.id_cuestionario=c.id where  e.estado=1 group by to_char(to_timestamp(e.fecha/1000),'YYYY/Month')";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
          
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    public static ArrayList<Resultado> obtenerNumeroDeEcuestasContestadasPorMesInvitado(Usuario usuario) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select  to_char(to_timestamp(e.fecha/1000),'YYYY/Month') as fecha ,count(*) from public.cuestionario as c inner join public.encuesta as e on e.id_cuestionario=c.id  where e.id_usuario=? and e.estado=1 group by to_char(to_timestamp(e.fecha/1000),'YYYY/Month');";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, usuario.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    public static ArrayList<Resultado> obtenerNumeroDeEcuestasContestadas(Usuario usuario) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select  c.creadopr ,count(*) from public.cuestionario as c inner join public.encuesta as e on e.id_cuestionario=c.id where e.estado=1 and c.creadopr=? group by c.creadopr ";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, usuario.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Resultado> obtenerNumeroDeEcuestasContestadas() throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        try {
            String sql = "select  c.creadopr ,count(*) from public.cuestionario as c inner join public.encuesta as e on e.id_cuestionario=c.id where e.estado=1  group by c.creadopr ";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
           
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);

            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    public static Resultado obtenerTotalEncuestas(Cuestionario cuestionario) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        Resultado res = null;
        try {
            String sql = "select c.nombre , count(*) from public.cuestionario as c inner join public.encuesta as e on e.id_cuestionario=c.id where c.id=? and e.estado =1 group by c.nombre;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, cuestionario.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);
            for (Resultado item : lst) {
                res = item;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return res;
    }
    public static Resultado obtenerTotalEncuestasFecha(Cuestionario cuestionario,String inicio ,String fin) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        Resultado res = null;
        try {
            String sql = "select c.nombre , count(*) from public.cuestionario as c inner join public.encuesta as e on e.id_cuestionario=c.id where c.id=? and e.estado =1 and (to_timestamp(e.fecha/1000)::date between '"+inicio+"' and '"+fin+"') group by c.nombre;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, cuestionario.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);
            for (Resultado item : lst) {
                res = item;
            }

        } catch (Exception ex) {
            throw ex;
        }
        return res;
    }

    //total encuestas que ha llenado un usuario recolector
    public static ArrayList<Resultado> obtenerTotalEncuestasRealizadas(Usuario u) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        Resultado res = null;
        try {
            String sql = "select c.nombre,count(*) from public.usuario as u inner join public.encuesta as e on u.id=e.id_usuario  inner join public.cuestionario as c on c.id=e.id_cuestionario where   e.estado=1 and u.id=? group by c.nombre";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, u.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    public static ArrayList<Resultado> obtenerTotalEncuestasRealizadasTop3(Usuario u) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        Resultado res = null;
        try {
            String sql = "select c.nombre,count(*) from public.usuario as u inner join public.encuesta as e on u.id=e.id_usuario  inner join public.cuestionario as c on c.id=e.id_cuestionario where   e.estado=1 and u.id=? group by c.nombre limit 3;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, u.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    public static ArrayList<Resultado> obtenerInvitadoTotalCiudadanos(Usuario u) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        Resultado res = null;
        try {
            String sql = "select  c.ci,count(*) from public.usuario as u inner join public.encuesta as e on u.id=e.id_usuario inner join public.ciudadano as c on c.id=e.id_ciudadano where u.id=? group by c.ci;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, u.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    public static ArrayList<Resultado> obtenerInvitadoTotalCiudadanos() throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        Resultado res = null;
        try {
            String sql = "select  c.ci,count(*) from public.usuario as u inner join public.encuesta as e on u.id=e.id_usuario inner join public.ciudadano as c on c.id=e.id_ciudadano group by c.ci ;";

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Resultado> obtenerTotalCiudadanos() throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        Resultado res = null;
        try {
            String sql = "select  c.ci,c.id from public.usuario as u inner join public.encuesta as e on u.id=e.id_usuario inner join public.ciudadano as c on c.id=e.id_ciudadano ;";

            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    public static ArrayList<Resultado> obtenerNumeroDeEncuestasTotalPorUsuario(Cuestionario c) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        
        try {
            String sql = "select res.nombrecompleto, res.total from (select u.id ,u.nombrecompleto, count(*) as total from public.cuestionario as c inner join public.encuesta as e on e.id_cuestionario=c.id  inner join usuario as u on u.id=e.id_usuario where c.id=? and e.estado=1 group by u.id,u.nombrecompleto order by count(*) desc) as res;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, c.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Resultado> obtenerRespuestaCortaPalabras(Pregunta p) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        
        try {
            String sql = "select res.respuestar,res.cantidad from (select translate(trim(regexp_split_to_table(lower(ep.respuesta),'[\\\\ \\\\,\\\\;\\\\.]+')),'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜ','aeiouAEIOUaeiouAEIOU') as respuestar ,count(*) as cantidad from public.encuesta_pregunta  as ep  inner join public.encuesta as e on e.id=ep.id_encuesta where ep.id_pregunta=? and e.estado=1 group by respuestar  order by cantidad desc)as res where not  res.respuestar like '' and not res.respuestar like  ' ';";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, p.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Resultado> obtenerRespuestaCortaPalabrasFecha(Pregunta p,String inicio, String fin) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        
        try {
            String sql = "select res.respuestar,res.cantidad from (select translate(trim(regexp_split_to_table(lower(ep.respuesta),'[\\\\\\\\ \\\\\\\\,\\\\\\\\;\\\\\\\\.]+')),'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜ','aeiouAEIOUaeiouAEIOU') as respuestar ,count(*) as cantidad from public.encuesta_pregunta  as ep  inner join public.encuesta as e on e.id=ep.id_encuesta where ep.id_pregunta=? and e.estado=1 and (to_timestamp(e.fecha/1000)::date between '"+inicio+"' and '"+fin+"') group by respuestar  order by cantidad desc)as res where not  res.respuestar like '' and not res.respuestar like  ' ';";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, p.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Resultado> obtenerRespuestaCortasConDelimitador(Pregunta p,String delimitador) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        
        try {
            String sql = "select res.respuestar,res.cantidad from   (select translate(trim(regexp_split_to_table(lower(ep.respuesta),'"+delimitador+"')),'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜ','aeiouAEIOUaeiouAEIOU')  as respuestar ,count(*) as cantidad from public.encuesta_pregunta  as ep  inner join public.encuesta as e on e.id=ep.id_encuesta where ep.id_pregunta=? and e.estado=1 group by respuestar ) as res where not res.respuestar like '' order by res.cantidad desc ;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, p.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
    public static ArrayList<Resultado> obtenerRespuestaCortasConDelimitadorFecha(Pregunta p,String delimitador,String inicio ,String fin) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        
        try {
            String sql = "select res.respuestar,res.cantidad from   (select translate(trim(regexp_split_to_table(lower(ep.respuesta),'"+delimitador+"')),'áéíóúÁÉÍÓÚäëïöüÄËÏÖÜ','aeiouAEIOUaeiouAEIOU')  as respuestar ,count(*) as cantidad from public.encuesta_pregunta  as ep  inner join public.encuesta as e on e.id=ep.id_encuesta where ep.id_pregunta=? and e.estado=1 and (to_timestamp(e.fecha/1000)::date between '"+inicio+"' and '"+fin+"') group by respuestar ) as res where not res.respuestar like '' order by res.cantidad desc ;";
            ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
            lstpar.add(new Parametro(1, p.getId()));
            ConjuntoResultado rs = AccesoDatos.ejecutaQuery(sql, lstpar);
            lst = llenarResultados(rs);

        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }

    private static ArrayList<Resultado> llenarResultados(ConjuntoResultado cr) throws Exception {
        ArrayList<Resultado> lst = new ArrayList<Resultado>();
        Resultado obj = null;
        try {
            while (cr.next()) {
                obj = new Resultado();
                obj.setRespuestar(cr.getString(1));
                obj.setCantidad(cr.getInt(2));
                lst.add(obj);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return lst;
    }
}
