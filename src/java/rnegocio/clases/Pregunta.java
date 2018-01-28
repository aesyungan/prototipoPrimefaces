package rnegocio.clases;
public class Pregunta { 
	private  int id; 
	private  String nombre; 
	private  String enunciado; 
	private  Categoria categoria; 
	private  Tipo_pregunta tipo_pregunta; 
	private  Long fcreada; 
	private  Long fmodificada; 
	private  int creadapor; 
	private  int modificadapor; 
	private  Boolean estado; 

 public int getId() {
return id;}

public void setId(int id) {this.id= id;}

 public String getNombre() {
return nombre;}

public void setNombre(String nombre) {this.nombre= nombre;}

 public String getEnunciado() {
return enunciado;}

public void setEnunciado(String enunciado) {this.enunciado= enunciado;}

 public Categoria getCategoria() {
return categoria;}

public void setCategoria(Categoria categoria) {this.categoria= categoria;}

 public Tipo_pregunta getTipo_pregunta() {
return tipo_pregunta;}

public void setTipo_pregunta(Tipo_pregunta tipo_pregunta) {this.tipo_pregunta= tipo_pregunta;}

 public Long getFcreada() {
return fcreada;}

public void setFcreada(Long fcreada) {this.fcreada= fcreada;}

 public Long getFmodificada() {
return fmodificada;}

public void setFmodificada(Long fmodificada) {this.fmodificada= fmodificada;}

 public int getCreadapor() {
return creadapor;}

public void setCreadapor(int creadapor) {this.creadapor= creadapor;}

 public int getModificadapor() {
return modificadapor;}

public void setModificadapor(int modificadapor) {this.modificadapor= modificadapor;}

 public Boolean getEstado() {
return estado;}

public void setEstado(Boolean estado) {this.estado= estado;}
}
