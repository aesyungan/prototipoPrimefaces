package rnegocio.funciones;
import accesodatos.Parametro;
import accesodatos.ConjuntoResultado;
import accesodatos.AccesoDatos;
import rnegocio.clases.Tipo_pregunta;
import java.util.ArrayList;
public class FTipo_pregunta{


 public static boolean insertar(Tipo_pregunta obj ) throws  Exception { 
 boolean band= false;
 String sql = "insert into public.tipo_pregunta values (?,?)";
ArrayList<Parametro> lstpar= new ArrayList<Parametro>();

//campos con referencias


//campos sin referencias

 lstpar.add(new Parametro(1,obj.getId()));
 lstpar.add(new Parametro(2,obj.getDescripcion()));
try { 
   band = AccesoDatos.ejecutaComando1(sql, lstpar);
} catch (Exception ex) { 
throw ex;}
 return band; 
}



 public static boolean modificar(Tipo_pregunta obj ) throws  Exception { 
 boolean band= false;
 String sql = "update public.tipo_pregunta set id=?,descripcion=? where id=?  ";
ArrayList<Parametro> lstpar= new ArrayList<Parametro>();

//campos con referencias


//campos sin referencias

 lstpar.add(new Parametro(1,obj.getId()));
 lstpar.add(new Parametro(3,obj.getId()));
 lstpar.add(new Parametro(2,obj.getDescripcion()));
try { 
   band = AccesoDatos.ejecutaComando1(sql, lstpar);
} catch (Exception ex) { 
throw ex;}
 return band; 
}



 public static boolean eliminar(Tipo_pregunta obj ) throws  Exception { 
 boolean band= false;
 String sql = "delete from public.tipo_pregunta where id=? ";
ArrayList<Parametro> lstpar= new ArrayList<Parametro>();

//campos con referencias


//campos sin referencias

 lstpar.add(new Parametro(1,obj.getId()));
try { 
   band = AccesoDatos.ejecutaComando1(sql, lstpar);
} catch (Exception ex) { 
throw ex;}
 return band; 
}



 public static Tipo_pregunta obtener (int pid) throws Exception  {  
 Tipo_pregunta miTipo_pregunta = null;
try{ 
 String sql = "select id,descripcion from public.tipo_pregunta where   id=? ";
ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
 lstpar.add(new Parametro(1,pid));
ConjuntoResultado rs= AccesoDatos.ejecutaQuery(sql,lstpar);
ArrayList<Tipo_pregunta> lst=llenarTipo_preguntas(rs);
 for (Tipo_pregunta c : lst){
 miTipo_pregunta= c;
 } 

} catch (Exception ex) { 
throw ex; }
return  miTipo_pregunta;
}


 public static ArrayList<Tipo_pregunta> obtener () throws Exception  {  
 ArrayList<Tipo_pregunta> lst=new ArrayList<>();
try{ 
 String sql = "select id,descripcion from public.tipo_pregunta; ";
ConjuntoResultado rs= AccesoDatos.ejecutaQuery(sql);
 lst=llenarTipo_preguntas(rs);

} catch (Exception ex) { 
throw ex; }
return  lst;
}


 private static ArrayList<Tipo_pregunta> llenarTipo_preguntas(ConjuntoResultado cr)  throws  Exception { 
 ArrayList<Tipo_pregunta> lst = new ArrayList<Tipo_pregunta>();
 Tipo_pregunta obj=null;
try { 
  while(cr.next()){
 obj = new Tipo_pregunta();

//campos con referencias


//campos sin referencias

obj.setId(cr.getInt(1));
obj.setDescripcion(cr.getString(2));
lst.add(obj);

 }
} catch (Exception ex) { 
throw ex;}
 return lst;}


}