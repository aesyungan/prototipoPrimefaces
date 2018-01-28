package rnegocio.clases;
public class Opcion_tipopregunta { 
	private  int id; 
	private  Tipo_pregunta tipo_pregunta; 
	private  String opcion; 
	private  double valor; 

 public int getId() {
return id;}

public void setId(int id) {this.id= id;}

 public Tipo_pregunta getTipo_pregunta() {
return tipo_pregunta;}

public void setTipo_pregunta(Tipo_pregunta tipo_pregunta) {this.tipo_pregunta= tipo_pregunta;}

 public String getOpcion() {
return opcion;}

public void setOpcion(String opcion) {this.opcion= opcion;}

 public double getValor() {
return valor;}

public void setValor(double valor) {this.valor= valor;}
}
