package rnegocio.clases;
public class Cuestionario_pregunta { 
	private  Cuestionario cuestionario; 
	private  Pregunta pregunta; 
	private  int orden; 

 public Cuestionario getCuestionario() {
return cuestionario;}

public void setCuestionario(Cuestionario cuestionario) {this.cuestionario= cuestionario;}

 public Pregunta getPregunta() {
return pregunta;}

public void setPregunta(Pregunta pregunta) {this.pregunta= pregunta;}

 public int getOrden() {
return orden;}

public void setOrden(int orden) {this.orden= orden;}
}
