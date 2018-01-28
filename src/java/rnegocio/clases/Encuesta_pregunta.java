package rnegocio.clases;
public class Encuesta_pregunta { 
	private  Encuesta encuesta; 
	private  Pregunta pregunta; 
	private  String respuesta; 
	private  Double valorrespuesta; 
	private  String resumenpregunta; 

 public Encuesta getEncuesta() {
return encuesta;}

public void setEncuesta(Encuesta encuesta) {this.encuesta= encuesta;}

 public Pregunta getPregunta() {
return pregunta;}

public void setPregunta(Pregunta pregunta) {this.pregunta= pregunta;}

 public String getRespuesta() {
return respuesta;}

public void setRespuesta(String respuesta) {this.respuesta= respuesta;}

 public Double getValorrespuesta() {
return valorrespuesta;}

public void setValorrespuesta(Double valorrespuesta) {this.valorrespuesta= valorrespuesta;}

 public String getResumenpregunta() {
return resumenpregunta;}

public void setResumenpregunta(String resumenpregunta) {this.resumenpregunta= resumenpregunta;}
}
