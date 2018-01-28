package rnegocio.clases;
import java.util.Date;
public class Ciudadano { 
	private  int id; 
	private  String ci; 
	private  String pasaporte; 
	private  Date fecha_nac; 
	private  int nivel_instruccion; 
	private  int genero; 

 public int getId() {
return id;}

public void setId(int id) {this.id= id;}

 public String getCi() {
return ci;}

public void setCi(String ci) {this.ci= ci;}

 public String getPasaporte() {
return pasaporte;}

public void setPasaporte(String pasaporte) {this.pasaporte= pasaporte;}

 public Date getFecha_nac() {
return fecha_nac;}

public void setFecha_nac(Date fecha_nac) {this.fecha_nac= fecha_nac;}

 public int getNivel_instruccion() {
return nivel_instruccion;}

public void setNivel_instruccion(int nivel_instruccion) {this.nivel_instruccion= nivel_instruccion;}

 public int getGenero() {
return genero;}

public void setGenero(int genero) {this.genero= genero;}
}
