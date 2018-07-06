package project;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Etiqueta extends JPanel {

	JLabel matricula = new JLabel("");
	JLabel flAtual = new JLabel("");
	JLabel flAutorizado = new JLabel("");
	JLabel velocidade = new JLabel("");
	JLabel destino = new JLabel("");
	JLabel linha = new JLabel("");
	
	
	public String getMatricula() {
		return matricula.getText();
	}


	public void setMatricula(String matricula) {
		this.matricula.setText(matricula);
	}


	public int getFlAtual() {
		return Integer.parseInt(flAtual.getText());
	}


	public void setFlAtual(int flAtual) {
		this.flAtual.setText(""+flAtual);
	}


	public int getFlAutorizado() {
		return Integer.parseInt(flAutorizado.getText());
	}


	public void setFlAutorizado(int flAutorizado) {
		this.flAutorizado.setText(""+flAutorizado);
	}


	public int getVelocidade() {
		return Integer.parseInt(velocidade.getText());
	}


	public void setVelocidade(int velocidade) {
		this.velocidade.setText(""+velocidade);
	}


	public String getDestino() {
		return destino.getText();
	}


	public void setDestino(String destino) {
		this.destino.setText(destino);
	}
	
	public String getLinha() {
		return linha.getText();
	}
	
	public void setLinha(String linha) {
		this.linha.setText(linha);
	}

	/**
	 * Create the panel.
	 */
	public Etiqueta(String m, int flAt, int flAut, int vel, String dest, String lin) {
		setLayout(null);
		setOpaque(false);		
		
		this.setMatricula(m);
		this.setFlAtual(flAt);
		this.setFlAutorizado(flAut);
		this.setVelocidade(vel);
		this.setDestino(dest);
		this.setLinha(lin);
		
		matricula.setBounds(0, 0, 50, 14);
		matricula.setForeground(Color.BLACK);
		add(matricula);
		
		
		flAtual.setBounds(0, 16, 25, 14);
		flAtual.setForeground(Color.BLACK);
		add(flAtual);
		
		
		flAutorizado.setBounds(26, 16, 25, 14);
		flAutorizado.setForeground(Color.BLACK);
		add(flAutorizado);
		
		
		velocidade.setBounds(0, 32, 25, 14);
		velocidade.setForeground(Color.BLACK);
		add(velocidade);
		
		
		destino.setBounds(26, 32, 40, 14);
		destino.setForeground(Color.BLACK);
		add(destino);
		
		linha.setBounds(0, 48, 50, 14);
		linha.setForeground(Color.BLACK);
		add(linha);

	}
}
