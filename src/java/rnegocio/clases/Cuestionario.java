package rnegocio.clases;
public class Cuestionario { 
	private  int id; 
	private  String nombre; 
	private  String cabecera; 
	private  String pui; 
	private  String rutalogo; 
	private  Tipo_cuestionario tipo_cuestionario; 
	private  Boolean estado; 
	private  long fcreado; 
	private  long fmodificado; 
	private  int creadopr; 
	private  int modificadopor; 

 public int getId() {
return id;}

public void setId(int id) {this.id= id;}

 public String getNombre() {
return nombre;}

public void setNombre(String nombre) {this.nombre= nombre;}

 public String getCabecera() {
return cabecera;}

public void setCabecera(String cabecera) {this.cabecera= cabecera;}

 public String getPui() {
return pui;}

public void setPui(String pui) {this.pui= pui;}

 public String getRutalogo() {
return rutalogo;}

public void setRutalogo(String rutalogo) {this.rutalogo= rutalogo;}

 public Tipo_cuestionario getTipo_cuestionario() {
return tipo_cuestionario;}

public void setTipo_cuestionario(Tipo_cuestionario tipo_cuestionario) {this.tipo_cuestionario= tipo_cuestionario;}

 public Boolean getEstado() {
return estado;}

public void setEstado(Boolean estado) {this.estado= estado;}

 public long getFcreado() {
return fcreado;}

public void setFcreado(long fcreado) {this.fcreado= fcreado;}

 public long getFmodificado() {
return fmodificado;}

public void setFmodificado(long fmodificado) {this.fmodificado= fmodificado;}

 public int getCreadopr() {
return creadopr;}

public void setCreadopr(int creadopr) {this.creadopr= creadopr;}

 public int getModificadopor() {
return modificadopor;}

public void setModificadopor(int modificadopor) {this.modificadopor= modificadopor;}
}
