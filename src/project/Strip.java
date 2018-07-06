package project;

public class Strip {
	
	private String matricula;
	private String procedencia;
	private String destino;
	private String tipo;
	private int transponder;
	private int eobt;
	private int nivel;
	private String[] rota; 
	private String texto;
	
	public Strip(String m, String p, String d, String t, int trans, int eo, int n, String fixo1, String fixo2, String tx){
		this.matricula = m;
		this.procedencia = p;
		this.destino = d;
		this.tipo = t;
		this.transponder = trans;
		this.eobt = eo;
		this.nivel = n;
		this.rota = new String[2];
		this.setRota(fixo1, fixo2);
		this.texto = tx;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String tx) {
		this.texto = tx;
	}
	
	public String getProcedencia() {
		return procedencia;
	}
	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getTransponder() {
		return transponder;
	}
	public void setTransponder(int transponder) {
		this.transponder = transponder;
	}
	public int getEobt() {
		return eobt;
	}
	public void setEobt(int eobt) {
		this.eobt = eobt;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public String[] getRota() {
		return rota;
	}
	public String getFixo1(){
		return this.rota[0];
	}
	public String getFixo2(){
		return this.rota[1];
	}
	public void setRota(String[] rota) {
		this.rota = rota;
	}
	public void setRota(String fixo1, String fixo2){
		this.rota[0] = fixo1;
		this.rota[1] = fixo2;
	}	
}
