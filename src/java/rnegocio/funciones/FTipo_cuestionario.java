package rnegocio.funciones;
import accesodatos.Parametro;
import accesodatos.ConjuntoResultado;
import accesodatos.AccesoDatos;
import rnegocio.clases.Tipo_cuestionario;
import java.util.ArrayList;
public class FTipo_cuestionario{


 public static boolean insertar(Tipo_cuestionario obj ) throws  Exception { 
 boolean band= false;
 String sql = "insert into public.tipo_cuestionario values (?,?)";
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



 public static boolean modificar(Tipo_cuestionario obj ) throws  Exception { 
 boolean band= false;
 String sql = "update public.tipo_cuestionario set id=?,descripcion=? where id=?  ";
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



 public static boolean eliminar(Tipo_cuestionario obj ) throws  Exception { 
 boolean band= false;
 String sql = "delete from public.tipo_cuestionario where id=? ";
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



 public static Tipo_cuestionario obtener (int pid) throws Exception  {  
 Tipo_cuestionario miTipo_cuestionario = null;
try{ 
 String sql = "select id,descripcion from public.tipo_cuestionario where   id=? ";
ArrayList<Parametro> lstpar = new ArrayList<Parametro>();
 lstpar.add(new Parametro(1,pid));
ConjuntoResultado rs= AccesoDatos.ejecutaQuery(sql,lstpar);
ArrayList<Tipo_cuestionario> lst=llenarTipo_cuestionarios(rs);
 for (Tipo_cuestionario c : lst){
 miTipo_cuestionario= c;
 } 

} catch (Exception ex) { 
throw ex; }
return  miTipo_cuestionario;
}


 public static ArrayList<Tipo_cuestionario> obtener () throws Exception  {  
 ArrayList<Tipo_cuestionario> lst=new ArrayList<>();
try{ 
 String sql = "select id,descripcion from public.tipo_cuestionario; ";
ConjuntoResultado rs= AccesoDatos.ejecutaQuery(sql);
 lst=llenarTipo_cuestionarios(rs);

} catch (Exception ex) { 
throw ex; }
return  lst;
}


 private static ArrayList<Tipo_cuestionario> llenarTipo_cuestionarios(ConjuntoResultado cr)  throws  Exception { 
 ArrayList<Tipo_cuestionario> lst = new ArrayList<Tipo_cuestionario>();
 Tipo_cuestionario obj=null;
try { 
  while(cr.next()){
 obj = new Tipo_cuestionario();

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