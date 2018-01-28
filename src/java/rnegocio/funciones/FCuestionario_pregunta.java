package rnegocio.funciones;
import accesodatos.Parametro;
import accesodatos.ConjuntoResultado;
import accesodatos.AccesoDatos;
import rnegocio.clases.Cuestionario_pregunta;
import java.util.ArrayList;
public class FCuestionario_pregunta{


 public static boolean insertar(Cuestionario_pregunta obj ) throws  Exception { 
 boolean band= false;
 String sql = "insert into public.cuestionario_pregunta values (?,?,?)";
ArrayList<Parametro> lstpar= new ArrayList<Parametro>();

//campos con referencias


lstpar.add(new Parametro(1,obj.getCuestionario().getId()));

lstpar.add(new Parametro(2,obj.getPregunta().getId()));

//campos sin referencias

 lstpar.add(new Parametro(3,obj.getOrden()));
try { 
   band = AccesoDatos.ejecutaComando1(sql, lstpar);
} catch (Exception ex) { 
throw ex;}
 return band; 
}



 public static boolean modificar(Cuestionario_pregunta obj ) throws  Exception { 
 boolean band= false;
 String sql = "update public.cuestionario_pregunta set id_cuestionario=?,id_pregunta=?,orden=? where id_cuestionario=? and id_pregunta=?  ";
ArrayList<Parametro> lstpar= new ArrayList<Parametro>();

//campos con referencias


lstpar.add(new Parametro(1,obj.getCuestionario().getId()));

lstpar.add(new Parametro(4,obj.getCuestionario().getId()));

lstpar.add(new Parametro(2,obj.getPregunta().getId()));

lstpar.add(new Parametro(5,obj.getPregunta().getId()));

//campos sin referencias

 lstpar.add(new Parametro(3,obj.getOrden()));
try { 
   band = AccesoDatos.ejecutaComando1(sql, lstpar);
} catch (Exception ex) { 
throw ex;}
 return band; 
}



 public static boolean eliminar(Cuestionario_pregunta obj ) throws  Exception { 
 boolean band= false;
 String sql = "delete from public.cuestionario_pregunta where id_cuestionario=? and id_pregunta=? ";
ArrayList<Parametro> lstpar= new ArrayList<Parametro>();

//campos con referencias


lstpar.add(new Parametro(1,obj.getCuestionario().getId()));

lstpar.add(new Parametro(2,obj.getPregunta().getId()));

//campos sin referencias

try { 
   band = AccesoDatos.ejecutaComando1(sql, lstpar);
} catch (Exception ex) { 
throw ex;}
 return band; 
}



 public static Cuestionario_pregunta obtener (int pid_cuestionario,int pid_pregunta) throws Exception  {  
 Cuestionario_pregunta miCuestionario_pregunta = null;
try{ 
 String sql = "select id_cuestionario,id_pregunta,orden from public.cuestionario_pregunta where   id_cuestionario=? and id_pregunta=? ";
ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
 lstpar.add(new Parametro(1,pid_cuestionario));
 lstpar.add(new Parametro(2,pid_pregunta));
ConjuntoResultado rs= AccesoDatos.ejecutaQuery(sql,lstpar);
ArrayList<Cuestionario_pregunta> lst=llenarCuestionario_preguntas(rs);
 for (Cuestionario_pregunta c : lst){
 miCuestionario_pregunta= c;
 } 

} catch (Exception ex) { 
throw ex; }
return  miCuestionario_pregunta;
}


 public static ArrayList<Cuestionario_pregunta> obtener () throws Exception  {  
 ArrayList<Cuestionario_pregunta> lst=new ArrayList<>();
try{ 
 String sql = "select id_cuestionario,id_pregunta,orden from public.cuestionario_pregunta; ";
ConjuntoResultado rs= AccesoDatos.ejecutaQuery(sql);
 lst=llenarCuestionario_preguntas(rs);

} catch (Exception ex) { 
throw ex; }
return  lst;
}


 private static ArrayList<Cuestionario_pregunta> llenarCuestionario_preguntas(ConjuntoResultado cr)  throws  Exception { 
 ArrayList<Cuestionario_pregunta> lst = new ArrayList<Cuestionario_pregunta>();
 Cuestionario_pregunta obj=null;
try { 
  while(cr.next()){
 obj = new Cuestionario_pregunta();

//campos con referencias


obj.setCuestionario(FCuestionario.obtener(cr.getInt(1)));

obj.setPregunta(FPregunta.obtener(cr.getInt(2)));

//campos sin referencias

obj.setOrden(cr.getInt(3));
lst.add(obj);

 }
} catch (Exception ex) { 
throw ex;}
 return lst;}


}