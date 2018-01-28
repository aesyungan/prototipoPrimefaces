package rnegocio.funciones;
import accesodatos.Parametro;
import accesodatos.ConjuntoResultado;
import accesodatos.AccesoDatos;
import rnegocio.clases.Opcion_tipopregunta;
import java.util.ArrayList;
public class FOpcion_tipopregunta{


 public static boolean insertar(Opcion_tipopregunta obj ) throws  Exception { 
 boolean band= false;
 String sql = "insert into public.opcion_tipopregunta values (?,?,?,?)";
ArrayList<Parametro> lstpar= new ArrayList<Parametro>();

//campos con referencias


lstpar.add(new Parametro(2,obj.getTipo_pregunta().getId()));

//campos sin referencias

 lstpar.add(new Parametro(1,obj.getId()));
 lstpar.add(new Parametro(3,obj.getOpcion()));
 lstpar.add(new Parametro(4,obj.getValor()));
try { 
   band = AccesoDatos.ejecutaComando1(sql, lstpar);
} catch (Exception ex) { 
throw ex;}
 return band; 
}



 public static boolean modificar(Opcion_tipopregunta obj ) throws  Exception { 
 boolean band= false;
 String sql = "update public.opcion_tipopregunta set id=?,id_tipopregunta=?,opcion=?,valor=? where id=? and id_tipopregunta=?  ";
ArrayList<Parametro> lstpar= new ArrayList<Parametro>();

//campos con referencias


lstpar.add(new Parametro(2,obj.getTipo_pregunta().getId()));

lstpar.add(new Parametro(6,obj.getTipo_pregunta().getId()));

//campos sin referencias

 lstpar.add(new Parametro(1,obj.getId()));
 lstpar.add(new Parametro(5,obj.getId()));
 lstpar.add(new Parametro(3,obj.getOpcion()));
 lstpar.add(new Parametro(4,obj.getValor()));
try { 
   band = AccesoDatos.ejecutaComando1(sql, lstpar);
} catch (Exception ex) { 
throw ex;}
 return band; 
}



 public static boolean eliminar(Opcion_tipopregunta obj ) throws  Exception { 
 boolean band= false;
 String sql = "delete from public.opcion_tipopregunta where id=? and id_tipopregunta=? ";
ArrayList<Parametro> lstpar= new ArrayList<Parametro>();

//campos con referencias


lstpar.add(new Parametro(2,obj.getTipo_pregunta().getId()));

//campos sin referencias

 lstpar.add(new Parametro(1,obj.getId()));
try { 
   band = AccesoDatos.ejecutaComando1(sql, lstpar);
} catch (Exception ex) { 
throw ex;}
 return band; 
}



 public static Opcion_tipopregunta obtener (int pid,int pid_tipopregunta) throws Exception  {  
 Opcion_tipopregunta miOpcion_tipopregunta = null;
try{ 
 String sql = "select id,id_tipopregunta,opcion,valor from public.opcion_tipopregunta where   id=? and id_tipopregunta=? ";
ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
 lstpar.add(new Parametro(1,pid));
 lstpar.add(new Parametro(2,pid_tipopregunta));
ConjuntoResultado rs= AccesoDatos.ejecutaQuery(sql,lstpar);
ArrayList<Opcion_tipopregunta> lst=llenarOpcion_tipopreguntas(rs);
 for (Opcion_tipopregunta c : lst){
 miOpcion_tipopregunta= c;
 } 

} catch (Exception ex) { 
throw ex; }
return  miOpcion_tipopregunta;
}


 public static ArrayList<Opcion_tipopregunta> obtener () throws Exception  {  
 ArrayList<Opcion_tipopregunta> lst=new ArrayList<>();
try{ 
 String sql = "select id,id_tipopregunta,opcion,valor from public.opcion_tipopregunta; ";
ConjuntoResultado rs= AccesoDatos.ejecutaQuery(sql);
 lst=llenarOpcion_tipopreguntas(rs);

} catch (Exception ex) { 
throw ex; }
return  lst;
}


 private static ArrayList<Opcion_tipopregunta> llenarOpcion_tipopreguntas(ConjuntoResultado cr)  throws  Exception { 
 ArrayList<Opcion_tipopregunta> lst = new ArrayList<Opcion_tipopregunta>();
 Opcion_tipopregunta obj=null;
try { 
  while(cr.next()){
 obj = new Opcion_tipopregunta();

//campos con referencias


obj.setTipo_pregunta(FTipo_pregunta.obtener(cr.getInt(2)));

//campos sin referencias

obj.setId(cr.getInt(1));
obj.setOpcion(cr.getString(3));
obj.setValor(cr.getDouble(4));
lst.add(obj);

 }
} catch (Exception ex) { 
throw ex;}
 return lst;}


}