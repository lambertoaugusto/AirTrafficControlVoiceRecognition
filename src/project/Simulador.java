package project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Point;

import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.sound.midi.SysexMessage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import java.awt.Canvas;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Window.Type;
import javax.swing.ImageIcon;

public class Simulador extends JFrame implements Runnable{

	private JPanel contentPane;
	JLabel[] lblFL = new JLabel[11];
	JLabel[] lblMAT = new JLabel[11];
	JLabel[] lblTRAN = new JLabel[11];
	JLabel[] lblPROC = new JLabel[11];
	JLabel[] lblDEST = new JLabel[11];
	JLabel[] lblTIPO = new JLabel[11];
	JLabel[] lblEOBT = new JLabel[11];
	JLabel[] lblFIXO1 = new JLabel[11];
	JLabel[] lblFIXO2 = new JLabel[11];
	//JPanel[] panel = new JPanel[11];
	JLabel lblMatTela;JLabel lblTRANTela;JLabel lblPROCTela;JLabel lblDESTTela;JLabel lblTIPOTela;JLabel lblEOBTTela;JLabel lblFIXO1Tela;JLabel lblFIXO2Tela;JLabel lblFLTela;JLabel lblTEXTOTela;
	Strip[] strips = new Strip[11];
	JTextArea textArea = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(textArea);
	JTextArea textAreaConsole = new JTextArea();
	JScrollPane scrollPaneConsole = new JScrollPane(textAreaConsole);
	JButton btnX = new JButton("X"); JButton btnOk = new JButton("\u221A");
	int planoAtual = -1;
	JPanel panelFIXO2Tela = new JPanel();
	JPanel panelFIXO1Tela = new JPanel();
	Etiqueta[] etiquetas = new Etiqueta[11];
	JLabel[] pista = new JLabel[11];
	Point abdul, fiber, ander, gamer, damas, tetra, midge;
	Iterator[] trajetorias = new Iterator[11]; 
			
	public void iniciarPlanos(){
		for(int i = 0; i < 11; i++){
			this.lblMAT[i] = new JLabel(strips[i].getMatricula());
			this.lblDEST[i] = new JLabel(strips[i].getDestino());
			this.lblTRAN[i] = new JLabel("A"+strips[i].getTransponder());
			this.lblPROC[i] = new JLabel(strips[i].getProcedencia());
			this.lblTIPO[i] = new JLabel(strips[i].getTipo());
			this.lblEOBT[i] = new JLabel(""+strips[i].getEobt());
			this.lblFIXO1[i] = new JLabel(strips[i].getFixo1());
			this.lblFIXO2[i] = new JLabel(strips[i].getFixo2());
			this.lblFL[i] = new JLabel("FL"+strips[i].getNivel());
			this.etiquetas[i] = new Etiqueta(strips[i].getMatricula(),strips[i].getNivel(),strips[i].getNivel(),400+(2*i),strips[i].getDestino(),"");			
		}		
	}
	
	
	public void changeFLPlan(String callSign, int fl){
		if(callSign.equalsIgnoreCase("TAP681")){
			lblFL[0].setText("FL"+fl);
		}
		else if(callSign.equalsIgnoreCase("AFR456")){
			lblFL[1].setText("FL"+fl);
		}		 
		else if(callSign.equalsIgnoreCase("SIA321")){
			lblFL[2].setText("FL"+fl);
		}
		else if(callSign.equalsIgnoreCase("CWC799")){
			lblFL[3].setText("FL"+fl);
		}
		else if(callSign.equalsIgnoreCase("AWE981")){
			lblFL[4].setText("FL"+fl);
		}
		else if(callSign.equalsIgnoreCase("THY771")){
			lblFL[5].setText("FL"+fl);
		}
		else if(callSign.equalsIgnoreCase("CCA652")){
			lblFL[6].setText("FL"+fl);
		}
		else if(callSign.equalsIgnoreCase("DLH179")){
			lblFL[7].setText("FL"+fl);
		}
		else if(callSign.equalsIgnoreCase("AEA401")){
			lblFL[8].setText("FL"+fl);
		}
		else if(callSign.equalsIgnoreCase("ARG442")){
			lblFL[9].setText("FL"+fl);
		}
		else if(callSign.equalsIgnoreCase("BAW756")){
			lblFL[10].setText("FL"+fl);
		}
	}
	
	public int indiceCallSignNumber(String callSign){
		//System.out.print(callSign);
		if(callSign.equalsIgnoreCase("681")){
			return 0;
		}
		else if(callSign.equalsIgnoreCase("456")){
			return 1;
		}		 
		else if(callSign.equalsIgnoreCase("321")){
			return 2;
		}
		else if(callSign.equalsIgnoreCase("799")){
			return 3;
		}
		else if(callSign.equalsIgnoreCase("981")){
			return 4;
		}
		else if(callSign.equalsIgnoreCase("771")){
			return 5;
		}
		else if(callSign.equalsIgnoreCase("652")){
			return 6;
		}
		else if(callSign.equalsIgnoreCase("179")){
			return 7;
		}
		else if(callSign.equalsIgnoreCase("401")){
			return 8;
		}
		else if(callSign.equalsIgnoreCase("442")){
			return 9;
		}
		else if(callSign.equalsIgnoreCase("756")){
			return 10;
		}
		else{
			return -1;
		}
	}
	
	public int indiceCallsign(String callSign){
		int ind = indiceCallSignNumber(callSign.substring(3));
		callSign = callSign.substring(0, 2);
		//System.out.print(callSign);
		if(ind!=-1){
			return ind;
		}		
		else if(callSign.equalsIgnoreCase("TAP")){
			return 0;
		}
		else if(callSign.equalsIgnoreCase("AFR")){
			return 1;
		}		 
		else if(callSign.equalsIgnoreCase("SIA")){
			return 2;
		}
		else if(callSign.equalsIgnoreCase("CWC")){
			return 3;
		}
		else if(callSign.equalsIgnoreCase("AWE")){
			return 4;
		}
		else if(callSign.equalsIgnoreCase("THY")){
			return 5;
		}
		else if(callSign.equalsIgnoreCase("CCA")){
			return 6;
		}
		else if(callSign.equalsIgnoreCase("DLH")){
			return 7;
		}
		else if(callSign.equalsIgnoreCase("AEA")){
			return 8;
		}
		else if(callSign.equalsIgnoreCase("ARG")){
			return 9;
		}
		else if(callSign.equalsIgnoreCase("BAW")){
			return 10;
		}
		else{
			return -1;
		}
	}
	
	public void visualizaPlano(int plano){
		this.planoAtual = plano;
		lblMatTela.setText(this.strips[plano].getMatricula());
		lblTRANTela.setText("A"+this.strips[plano].getTransponder());
		lblPROCTela.setText(this.strips[plano].getProcedencia());
		lblDESTTela.setText(this.strips[plano].getDestino());
		lblTIPOTela.setText(this.strips[plano].getTipo());
		lblEOBTTela.setText(""+this.strips[plano].getEobt());
		lblFIXO1Tela.setText(this.strips[plano].getFixo1());
		lblFIXO2Tela.setText(this.strips[plano].getFixo2());
		lblFLTela.setText("FL"+this.strips[plano].getNivel());	
		lblTEXTOTela.setText(this.strips[plano].getTexto());
		panelFIXO2Tela.setVisible(true);
		panelFIXO1Tela.setVisible(true);
		if(lblMAT[plano].getForeground().equals(Color.ORANGE)){
			lblMatTela.setForeground(Color.ORANGE);
			lblDESTTela.setForeground(Color.ORANGE);
			lblEOBTTela.setForeground(Color.ORANGE);
			lblFIXO1Tela.setForeground(Color.ORANGE);
			lblFIXO2Tela.setForeground(Color.ORANGE);
			lblFLTela.setForeground(Color.ORANGE);
			lblPROCTela.setForeground(Color.ORANGE);
			lblTEXTOTela.setForeground(Color.ORANGE);
			lblTIPOTela.setForeground(Color.ORANGE);
			lblTRANTela.setForeground(Color.ORANGE);
		}
	}
	
	public void visualizaInserirTexto(boolean bool){
		if(bool){
			this.textArea.setText(this.lblTEXTOTela.getText());
		}
		this.scrollPane.setVisible(bool);
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				strips[planoAtual].setTexto(textArea.getText());
				visualizaInserirTexto(false);
				lblTEXTOTela.setText(textArea.getText());
			}
		});
		this.btnOk.setVisible(bool);
		btnX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visualizaInserirTexto(false);
			}
		});
		this.btnX.setVisible(bool);
	}
	
	public void handOff(int indice){
		lblMAT[indice].setForeground(Color.ORANGE);
		lblDEST[indice].setForeground(Color.ORANGE);
		lblEOBT[indice].setForeground(Color.ORANGE);
		lblFIXO1[indice].setForeground(Color.ORANGE);
		lblFIXO2[indice].setForeground(Color.ORANGE);
		lblFL[indice].setForeground(Color.ORANGE);
		lblPROC[indice].setForeground(Color.ORANGE);
		lblTIPO[indice].setForeground(Color.ORANGE);
		lblTRAN[indice].setForeground(Color.ORANGE);
	}
	
	
	//comando 0 = chamou; 1 = stand by; 2 = hand off
	public void identificaAeronave(int ind, int comando){
		Color cor = Color.BLUE;
		if(comando == 1){
			cor = Color.GREEN;
		}
		else if(comando == 2){
			cor = Color.ORANGE;
		}
		else if(comando == 3){
			cor = Color.BLACK;
		}
		this.etiquetas[ind].destino.setForeground(cor);
		this.etiquetas[ind].flAtual.setForeground(cor);
		this.etiquetas[ind].flAutorizado.setForeground(cor);
		this.etiquetas[ind].matricula.setForeground(cor);
		this.etiquetas[ind].velocidade.setForeground(cor);
	}

	public void atualizaNiveis(int ind, String fl){
		strips[ind].setNivel(Integer.parseInt(fl.substring(2)));
		this.lblFL[ind].setText(fl);
		this.etiquetas[ind].setFlAutorizado(Integer.parseInt(fl.substring(2)));
	}
	
	public String getQSO() {  
		  
		// cria um StringBuilder  
		StringBuilder hora = new StringBuilder();
		StringBuilder minuto = new StringBuilder();
		  
		// cria um GregorianCalendar que vai conter a hora atual  
		GregorianCalendar d = new GregorianCalendar();
		
		hora.append(d.get(GregorianCalendar.HOUR_OF_DAY));
		minuto.append( d.get( GregorianCalendar.MINUTE ) );
		
		String retorno = "";
		
		if(hora.toString().length()==1){
			if(minuto.toString().length()==1){
				retorno = "Q0"+hora.toString()+"0"+minuto.toString()+"R";
			}
			else{
				retorno = "Q0"+hora.toString()+minuto.toString()+"R";
			}
		}
		else{
			if(minuto.toString().length()==1){
				retorno = "Q"+hora.toString()+"0"+minuto.toString()+"R";
			}
			else{
				retorno = "Q"+hora.toString()+minuto.toString()+"R";
			}
		}
		  
		// retorna a String do StringBuilder  
		return retorno;
	}
	
	public void clearence(int ind, StringTokenizer frase, String palavra){		
		if(palavra.equals("climb")){
			palavra = frase.nextToken();
			if(palavra.equals("to")){
				palavra = frase.nextToken();//fl
				atualizaNiveis(ind, palavra);
				strips[ind].setTexto(this.getQSO()+" +"+palavra);
				this.visualizaPlano(ind);
			}
			else if(palavra.equals("until")){
				palavra = frase.nextToken();//fl
				atualizaNiveis(ind, palavra);
				//colocar na quarta linha etiqueta
				strips[ind].setTexto(this.getQSO()+" +#"+palavra);
				etiquetas[ind].setLinha("FL"+strips[ind].getNivel());
				this.visualizaPlano(ind);
			}
		}
		else if(palavra.equals("descend") |palavra.equals("descent")){
			palavra = frase.nextToken();//to
			palavra = frase.nextToken();//fl					
			atualizaNiveis(ind, palavra);
			strips[ind].setTexto(this.getQSO()+" -"+palavra);
			this.visualizaPlano(ind);
		}
		else if(palavra.equals("maintain")){
			palavra = frase.nextToken();//fl					
			atualizaNiveis(ind, palavra);
			strips[ind].setTexto(this.getQSO()+" "+palavra);
			this.visualizaPlano(ind);
		}
	}

	
	public void reconheceu(String rec){
		StringTokenizer frase = new StringTokenizer(rec);
		if(frase.countTokens() != 0){			
			//reconhecendo a chamada inicial! regra calling
			String palavra = frase.nextToken();
			if(palavra.equals("recife")||palavra.equals("atlantic")||palavra.equals("amazonic")){
				if(frase.countTokens() == 2){
					palavra = frase.nextToken();					
				}
				palavra = frase.nextToken(); 
				int ind = this.indiceCallsign(palavra);
				if(ind != -1){
					this.visualizaPlano(ind);
					this.identificaAeronave(ind, 0);					
				}
				else{
					textAreaConsole.setText(textAreaConsole.getText()+"Matrícula não encontrada!\n");
					textAreaConsole.setCaretPosition(textAreaConsole.getDocument().getLength());
				}
			}
			else{
				//demais regras
				int ind = this.indiceCallsign(palavra);
				if(ind != -1){										
					palavra = frase.nextToken();
					//regra authorization sem cleared
					clearence(ind, frase, palavra);					
					//regra firstContact
					if(palavra.equals("squawk")){									
						palavra = frase.nextToken();//ident
						palavra = frase.nextToken();
						clearence(ind, frase, palavra);
					}
					//regra authorization com cleared
					else if(palavra.equals("cleared")){
						palavra = frase.nextToken();
						if(!palavra.equals("fly")){
							clearence(ind, frase, palavra);
						}
						//regra flyDirect
						else{
							palavra = frase.nextToken();//direct
							String posicao = "";
							palavra = frase.nextToken();
							while(!palavra.equals("intersection")){
								posicao = posicao+palavra;
								palavra = frase.nextToken();
							}
							strips[ind].setTexto(strips[ind].getTexto()+" DCT "+posicao);
							etiquetas[ind].setLinha("DCT");
							this.visualizaPlano(ind);
						}
					}
					//regra lastContact
					else if(palavra.equals("contact")){
						palavra = frase.nextToken();
						if(palavra.equals("recife")||palavra.equals("atlantic")||palavra.equals("amazonic")){
							palavra = frase.nextToken();//center							
							palavra = frase.nextToken();//on
							palavra = frase.nextToken();//frequency
							palavra = frase.nextToken();
							strips[ind].setTexto(strips[ind].getTexto()+" @"+palavra);							
							this.handOff(ind);
							this.identificaAeronave(ind, 2);
							this.visualizaPlano(ind);
						}						
					}
					//regra headingAndMiles
					else if(palavra.equals("heading")){
						String proa = frase.nextToken();
						palavra = frase.nextToken();//by
						String distancia = frase.nextToken();
						strips[ind].setTexto(strips[ind].getTexto()+" H"+proa+" "+distancia+"NM");
						this.visualizaPlano(ind);
					}
					//regra deviation
					else if(palavra.equals("deviation")){
						palavra = frase.nextToken();//by
						palavra = frase.nextToken();//the
						String lado = "DL";
						palavra = frase.nextToken();
						if(palavra.equals("right")){
							lado = "DR";
						}
						if(!frase.hasMoreTokens()){
							strips[ind].setTexto(strips[ind].getTexto()+" "+lado);
							this.visualizaPlano(ind);
						}
						else{
							palavra = frase.nextToken();
							if(palavra.equals("heading")){
								String proa = frase.nextToken();
								palavra = frase.nextToken();//by
								String distancia = frase.nextToken();
								strips[ind].setTexto(strips[ind].getTexto()+" "+lado+" H"+proa+" "+distancia+"NM");
								this.visualizaPlano(ind);
							}							
						}
					}
					//regra stand by
					else if(palavra.equals("stand")){
						this.visualizaPlano(ind);
						this.identificaAeronave(ind, 1);
					}
					//regra vector
					else if(palavra.equals("vector")){
						palavra = frase.nextToken();
						if(palavra.equals("for")){
							palavra = frase.nextToken();//spacing
						}
						else{
							palavra = frase.nextToken();//avoid
							palavra = frase.nextToken();//traffic
						}
						palavra = frase.nextToken();//turn
						palavra = frase.nextToken();
						String direcao = "R";
						if(palavra.equals("left")){
							direcao = "L";
						}
						palavra = frase.nextToken();//heading
						String proa = frase.nextToken();
						strips[ind].setTexto(strips[ind].getTexto()+" V"+direcao+" H"+proa);
						this.visualizaPlano(ind);
					}					
				}
				else{
					textAreaConsole.setText(textAreaConsole.getText()+"Matrícula "+palavra+" não encontrada!\n");
					textAreaConsole.setCaretPosition(textAreaConsole.getDocument().getLength());
				}
			}
		}
		else{
			textAreaConsole.setText(textAreaConsole.getText()+"Nenhum comando reconhecido!\n");
			textAreaConsole.setCaretPosition(textAreaConsole.getDocument().getLength());
		}
	}				
	
	/*public void reconheceu(String rec){
		StringTokenizer frase = new StringTokenizer(rec);
		int quantPalavras = 0;
		Vector palavras = new Vector();
		while(frase.hasMoreTokens()){
			palavras.add(frase.nextToken());
			quantPalavras++;
		}		
		int i = 0;		
		if(quantPalavras != 0){			
			//reconhecendo a chamada inicial! regra calling
			if(palavras.elementAt(i).equals("recife")||palavras.elementAt(i).equals("atlantic")){
				i++;
				if(palavras.elementAt(i).equals("center")){
					i++;					
				}
				int ind = this.indiceCallsign((String)palavras.elementAt(i));
				if(ind != -1){
					this.visualizaPlano(ind);
					this.identificaAeronave(ind);
				}
				else{
					textAreaConsole.setText(textAreaConsole.getText()+"Matrícula "+(String)palavras.elementAt(i)+" não encontrada!\n");
					textAreaConsole.setCaretPosition(textAreaConsole.getDocument().getLength());
				}
			}
			else{
				//demais regras
				String mat = (String)palavras.elementAt(i);
				int ind = this.indiceCallsign(mat);
				if(ind != -1){
					i++;					
					//reconhecendo a regra first contact e a regra authorization
					if(palavras.elementAt(i).equals("squawk") || (palavras.elementAt(i).equals("cleared")&&!(palavras.elementAt(i+1).equals("fly")))|| palavras.elementAt(i).equals("climb") || palavras.elementAt(i).equals("descent")|| palavras.elementAt(i).equals("descend")){
						boolean primeiroQSO = false;
						if(palavras.elementAt(i).equals("squawk")){
							i = i + 2;
							primeiroQSO = true;
						}
						else if(palavras.elementAt(i).equals("cleared")){
							i++;							
						}
						String comando = (String)palavras.elementAt(i);
						if(comando.equals("climb") || comando.equals("descent") || comando.equals("descend") || comando.equals("maintain")){
							if(comando.equals("maintain")){
								i++;
							}
							else{
								i = i + 2;
							}
							String nivel = ((String)palavras.elementAt(i));
							strips[ind].setNivel(Integer.parseInt(nivel.substring(2)));
							this.lblFL[ind].setText(nivel);							
							//int segundos = (int)(System.currentTimeMillis()/100000000);
							String QSO = "";
							if(!primeiroQSO){
								if(strips[ind].getTexto().length() > 5){
									QSO = strips[ind].getTexto().substring(0, 5);
								}
							}
							else{
								int hora = Calendar.getInstance().getTime().getHours();
								if(Integer.toString(hora).length() == 1){
									QSO = "Q0"+hora+Calendar.getInstance().getTime().getMinutes()+"R";
								}
								else{
									QSO = "Q"+hora+Calendar.getInstance().getTime().getMinutes()+"R";
								}
							}
							String finalTexto = "";
							if(strips[ind].getTexto().length() > 12){
								finalTexto = strips[ind].getTexto().substring(12);
							}
							if(comando.equals("climb")){
								strips[ind].setTexto(QSO+" +"+nivel+finalTexto);
							}
							else if(comando.equals("maintain")){
								strips[ind].setTexto(QSO+"  "+nivel+finalTexto);
							}
							else{
								strips[ind].setTexto(QSO+" -"+nivel+finalTexto);
							}
							this.visualizaPlano(ind);
						}
					}
					//reconhecendo a regra lastcontact
					else if(palavras.elementAt(i).equals("contact")){
						i++;
						if(palavras.elementAt(i).equals("recife")){
							i = i + 3;
							strips[ind].setTexto(strips[ind].getTexto()+" "+palavras.elementAt(i));							
							this.handOff(ind);
							this.visualizaPlano(ind);
							//this.
						}
						else{
							i = i + 3;
							strips[ind].setTexto(strips[ind].getTexto()+" "+palavras.elementAt(i));							
							this.handOff(ind);
							this.visualizaPlano(ind);
						}
					}
					//reconhecendo a regra headingAndMiles e a regra deviation
					else if(palavras.elementAt(i).equals("heading")||palavras.elementAt(i).equals("deviation")){
						String deviation = "";
						if(palavras.elementAt(i).equals("deviation")){
							i = i + 3;
							if(palavras.elementAt(i).equals("right")){
								deviation = "DR";
							}
							else{
								deviation = "DL";
							}
							i++;
						}
//**************verificar essa parte*****************8						
						
						i++;
						String heading = "";
						while(!(((String)palavras.elementAt(i)).equals("by"))){
							heading = heading+(String)palavras.elementAt(i);
							i++;
						}
						i++;
						String mile = "";
						while(!(((String)palavras.elementAt(i)).equals("mile")||((String)palavras.elementAt(i)).equals("miles"))){
							mile = mile+(String)palavras.elementAt(i);
							i++;
						}
						strips[ind].setTexto(strips[ind].getTexto()+" "+deviation+" H"+heading+" "+mile+"NM");
						
						//strips[ind].setTexto(strips[ind].getTexto()+" "+deviation);
						this.visualizaPlano(ind);
					}
					//reconhecendo a regra flyDirect
					else if(palavras.elementAt(i).equals("cleared")){
						i = i + 3;
						String position = "";
						while(!palavras.elementAt(i).equals("intersection")){
							position = position + ((String)palavras.elementAt(i)).toUpperCase();
							i++;
						}
						strips[ind].setTexto(strips[ind].getTexto()+" DCT "+position);
						this.visualizaPlano(ind);
					}
					//reconhecendo a regra stand by
					//reconhecendo a regra vector
					else if(palavras.elementAt(i).equals("vector")){
						i++;
						while(!palavras.elementAt(i).equals("turn")){
							i++;
						}
						i++;
						String direction = "";
						if(palavras.elementAt(i).equals("right")){
							direction = "VR";
						}
						else{
							direction = "VL";
						}
						i=i+2;
						String heading = "";
						while(i<palavras.size()){
							heading = heading + palavras.elementAt(i);
							i++;
						}
						strips[ind].setTexto(strips[ind].getTexto()+" "+direction+" H"+heading);
						this.visualizaPlano(ind);
					}
				}
				else{
					textAreaConsole.setText(textAreaConsole.getText()+"Matrícula "+mat+" não encontrada!\n");
					textAreaConsole.setCaretPosition(textAreaConsole.getDocument().getLength());
				}
			}
		}
	}*/
	
	public void modificaPosPista(int ind, int x, int y){
		this.etiquetas[ind].setLocation(x-55, y-50);
		pista[ind].setLocation(x,y);		
	}

	/**
	 * Create the frame.
	 */
	public Simulador(Strip[] s) {
		setAutoRequestFocus(false);
		setTitle("Air Traffic Controller by Voice");		
		this.strips = s;
		this.iniciarPlanos();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1, 1, 1360, 725);
		contentPane = new JPanel();		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		JLabel aerovias = new JLabel();
		aerovias.setBounds(-5, -25, 1366, 728);
		aerovias.setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\aerovias.png"));
		//panelTela.add(aerovias);
		//setContentPane(aerovias);
				
		this.visualizaInserirTexto(false);
		textAreaConsole.setWrapStyleWord(true);
		textAreaConsole.setLineWrap(true);
		textAreaConsole.setEditable(false);
			
		
				//textArea = new JTextArea();
		textAreaConsole.setColumns(10);
		textAreaConsole.setBackground(SystemColor.menu);
		textAreaConsole.setForeground(Color.BLACK);
		textAreaConsole.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textAreaConsole.setBounds(10,10, 100, 22);
		scrollPaneConsole = new JScrollPane(textAreaConsole);
		scrollPaneConsole.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneConsole.setBounds(40, 526, 430, 147);
		contentPane.add(scrollPaneConsole);
		scrollPaneConsole.setViewportBorder(null);
		textAreaConsole.setBorder(null);
		scrollPaneConsole.setBorder(null);
		scrollPaneConsole.setPreferredSize(new Dimension(5,5));
		//scrollPane.set
		//scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneConsole.setPreferredSize(new Dimension(30, 30));		
		
		JPanel panelTela = new JPanel();
		panelTela.setBounds(0, 0, 1114, 696);
		panelTela.setBackground(Color.GRAY);		
		contentPane.add(panelTela);
		panelTela.setLayout(null);		
		//contentPane.setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\aerovias.png"));
		
		/*JLabel aerovias = new JLabel();
		aerovias.setBounds(-5, -25, 1366, 728);
		aerovias.setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\aerovias.png"));
		panelTela.add(aerovias);*/		
		
		pista[1] = new JLabel();
		//pista[1].setOpaque(true);
		pista[1].setHorizontalAlignment(SwingConstants.CENTER);
		pista[1].setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\pista.png"));
		panelTela.add(pista[1]);
		
		pista[2] = new JLabel();
		//pista[2].setOpaque(true);
		pista[2].setHorizontalAlignment(SwingConstants.CENTER);
		pista[2].setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\pista.png"));
		panelTela.add(pista[2]);
		
		pista[3] = new JLabel();
		//pista[3].setOpaque(true);
		pista[3].setHorizontalAlignment(SwingConstants.CENTER);
		pista[3].setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\pista.png"));
		panelTela.add(pista[3]);
		
		pista[4] = new JLabel();
		//pista[4].setOpaque(true);
		pista[4].setHorizontalAlignment(SwingConstants.CENTER);
		pista[4].setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\pista.png"));
		panelTela.add(pista[4]);
		
		pista[5] = new JLabel();
		//pista[5].setOpaque(true);
		pista[5].setHorizontalAlignment(SwingConstants.CENTER);
		pista[5].setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\pista.png"));
		panelTela.add(pista[5]);
		
		pista[6] = new JLabel();
		//pista[6].setOpaque(true);
		pista[6].setHorizontalAlignment(SwingConstants.CENTER);
		pista[6].setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\pista.png"));
		panelTela.add(pista[6]);
		
		pista[7] = new JLabel();
		//pista[7].setOpaque(true);
		pista[7].setHorizontalAlignment(SwingConstants.CENTER);
		pista[7].setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\pista.png"));
		panelTela.add(pista[7]);
		
		pista[8] = new JLabel();
		//pista[8].setOpaque(true);
		pista[8].setHorizontalAlignment(SwingConstants.CENTER);
		pista[8].setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\pista.png"));
		panelTela.add(pista[8]);
		
		pista[9] = new JLabel();
		//pista[9].setOpaque(true);
		pista[9].setHorizontalAlignment(SwingConstants.CENTER);
		pista[9].setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\pista.png"));
		panelTela.add(pista[9]);
		
		pista[10] = new JLabel();
		//pista[10].setOpaque(true);
		pista[10].setHorizontalAlignment(SwingConstants.CENTER);
		pista[10].setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\pista.png"));
		panelTela.add(pista[10]);
		
		pista[0] = new JLabel();
		//pista[0].setOpaque(true);
		pista[0].setHorizontalAlignment(SwingConstants.CENTER);
		pista[0].setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\pista.png"));
		panelTela.add(pista[0]);	

		
		//adiciona etiquetas
		this.etiquetas[1].setBounds(200, 200, 70, 65);
		pista[1].setBounds(255, 250, 15, 20);
		pista[1].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(etiquetas[1].matricula.getForeground() == Color.GREEN){
					identificaAeronave(1, 3);
				}
			}
		});
		panelTela.add(etiquetas[1]);
		
		this.etiquetas[2].setBounds(0, 0, 70, 65);
		pista[2].setBounds(55, 50, 15, 20);
		pista[2].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(etiquetas[2].matricula.getForeground() == Color.GREEN){
					identificaAeronave(2, 3);
				}
			}
		});
		panelTela.add(etiquetas[2]);
		
		this.etiquetas[3].setBounds(0, 100, 70, 65);
		pista[3].setBounds(55, 150, 15, 20);
		pista[3].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(etiquetas[3].matricula.getForeground() == Color.GREEN){
					identificaAeronave(3, 3);
				}
			}
		});
		panelTela.add(etiquetas[3]);
		
		this.etiquetas[4].setBounds(0, 400, 70, 65);
		pista[4].setBounds(55, 450, 15, 20);
		pista[4].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(etiquetas[4].matricula.getForeground() == Color.GREEN){
					identificaAeronave(4, 3);
				}
			}
		});
		panelTela.add(etiquetas[4]);
		
		this.etiquetas[5].setBounds(0, 500, 70, 65);
		pista[5].setBounds(55, 550, 15, 20);
		pista[5].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(etiquetas[5].matricula.getForeground() == Color.GREEN){
					identificaAeronave(5, 3);
				}
			}
		});
		panelTela.add(etiquetas[5]);
		
		this.etiquetas[6].setBounds(0, 600, 70, 65);
		pista[6].setBounds(55, 650, 15, 20);
		pista[6].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(etiquetas[6].matricula.getForeground() == Color.GREEN){
					identificaAeronave(6, 3);
				}
			}
		});
		panelTela.add(etiquetas[6]);
		
		this.etiquetas[7].setBounds(200, 100, 70, 65);
		pista[7].setBounds(255, 150, 15, 20);
		pista[7].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(etiquetas[7].matricula.getForeground() == Color.GREEN){
					identificaAeronave(7, 3);
				}
			}
		});
		panelTela.add(etiquetas[7]);
		
		this.etiquetas[8].setBounds(100, 0, 70, 65);
		pista[8].setBounds(155, 50, 15, 20);
		pista[8].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(etiquetas[8].matricula.getForeground() == Color.GREEN){
					identificaAeronave(8, 3);
				}
			}
		});
		panelTela.add(etiquetas[8]);
		
		this.etiquetas[9].setBounds(100, 100, 70, 65);
		pista[9].setBounds(155, 150, 15, 20);
		pista[9].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(etiquetas[9].matricula.getForeground() == Color.GREEN){
					identificaAeronave(9, 3);
				}
			}
		});
		panelTela.add(etiquetas[9]);
		
		this.etiquetas[10].setBounds(100, 200, 70, 65);
		pista[10].setBounds(155, 250, 15, 20);
		pista[10].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(etiquetas[10].matricula.getForeground() == Color.GREEN){
					identificaAeronave(10, 3);
				}
			}
		});
		panelTela.add(etiquetas[10]);
		
		this.etiquetas[0].setBounds(100, 300, 70, 65);
		pista[0].setBounds(155, 350, 15, 20);
		pista[0].addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(etiquetas[0].matricula.getForeground() == Color.GREEN){
					identificaAeronave(0, 3);
				}
			}
		});
		panelTela.add(etiquetas[0]);
				
		JLabel lblAbdul = new JLabel();		
		lblAbdul.setForeground(Color.LIGHT_GRAY);
		lblAbdul.setText("ABDUL");
		//abdul.setOpaque(true);
		lblAbdul.setBounds(290, 250, 60, 20);
		lblAbdul.setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\fixo2.png"));
		panelTela.add(lblAbdul);		
		
		JLabel lblGamer = new JLabel();
		lblGamer.setForeground(Color.LIGHT_GRAY);
		lblGamer.setText("GAMER");
		//gamer.setOpaque(true);
		lblGamer.setBounds(40, 70, 60, 20);
		lblGamer.setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\fixo2.png"));
		panelTela.add(lblGamer);
		
		JLabel lblFiber = new JLabel();
		lblFiber.setForeground(Color.LIGHT_GRAY);
		lblFiber.setText("FIBER");
		//fiber.setOpaque(true);
		lblFiber.setBounds(540, 70, 60, 20);
		lblFiber.setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\fixo2.png"));
		panelTela.add(lblFiber);
		
		JLabel lblAnder = new JLabel();
		lblAnder.setForeground(Color.LIGHT_GRAY);
		lblAnder.setText("ANDER");
		//ander.setOpaque(true);
		lblAnder.setBounds(990, 70, 60, 20);
		lblAnder.setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\fixo2.png"));
		panelTela.add(lblAnder);
		
		JLabel lblMidge = new JLabel();
		lblMidge.setForeground(Color.LIGHT_GRAY);
		lblMidge.setText("MIDGE");
		//midge.setOpaque(true);
		lblMidge.setBounds(290, 70, 60, 20);
		lblMidge.setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\fixo2.png"));
		panelTela.add(lblMidge);
		
		JLabel lblDamas = new JLabel();
		lblDamas.setForeground(Color.LIGHT_GRAY);
		lblDamas.setText("DAMAS");
		//damas.setOpaque(true);
		lblDamas.setBounds(540, 250, 70, 20);
		lblDamas.setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\fixo2.png"));
		panelTela.add(lblDamas);
		
		JLabel lblTetra = new JLabel();
		lblTetra.setForeground(Color.LIGHT_GRAY);
		lblTetra.setText("TETRA");
		//tetra.setOpaque(true);
		lblTetra.setBounds(540, 450, 60, 20);
		lblTetra.setIcon(new ImageIcon("C:\\Users\\Beto\\Dropbox\\TG\\ATCV\\src\\project\\fixo2.png"));
		panelTela.add(lblTetra);
				
		//btnX = new JButton("X");
		btnX.setForeground(Color.RED);
		btnX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnX.setBounds(1031, 487, 43, 29);
		panelTela.add(btnX);
		
		//btnOk = new JButton("\u221A");
		btnOk.setForeground(Color.GREEN);
		btnOk.setBounds(1031, 458, 43, 29);
		panelTela.add(btnOk);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		//Panel aqui
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(522, 526, 552, 147);
		panelTela.add(panel);
		panel.setLayout(null);
		
		lblMatTela = new JLabel("");
		lblMatTela.setBounds(10, 5, 115, 39);
		lblMatTela.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblMatTela);
		
		lblTRANTela = new JLabel("");
		lblTRANTela.setBounds(140, 5, 103, 39);
		lblTRANTela.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblTRANTela);
		
		lblPROCTela = new JLabel("");
		lblPROCTela.setBounds(10, 55, 91, 39);
		lblPROCTela.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblPROCTela);
		
		lblDESTTela = new JLabel("");
		lblDESTTela.setBounds(140, 55, 91, 39);
		lblDESTTela.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblDESTTela);
		
		lblTIPOTela = new JLabel("");
		lblTIPOTela.setBounds(140, 105, 81, 36);
		lblTIPOTela.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblTIPOTela);
		
		lblEOBTTela = new JLabel("");
		lblEOBTTela.setBounds(10, 105, 79, 36);
		lblEOBTTela.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblEOBTTela);
		
		//JPanel panelFIXO1Tela = new JPanel();
		panelFIXO1Tela.setBounds(256, 47, 145, 55);
		panelFIXO1Tela.setLayout(null);
		panelFIXO1Tela.setBorder(new MatteBorder(0, 4, 0, 0, (Color) new Color(0, 0, 0)));
		panel.add(panelFIXO1Tela);
				
		lblFIXO1Tela = new JLabel("");
		lblFIXO1Tela.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblFIXO1Tela.setBounds(20, 8, 114, 39);
		panelFIXO1Tela.add(lblFIXO1Tela);
		panelFIXO1Tela.setVisible(false);
		
		//JPanel panelFIXO2Tela = new JPanel();
		panelFIXO2Tela.setBounds(410, 47, 145, 55);
		panelFIXO2Tela.setLayout(null);
		panelFIXO2Tela.setBorder(new MatteBorder(0, 4, 0, 0, (Color) new Color(0, 0, 0)));
		panel.add(panelFIXO2Tela);
		
		lblFIXO2Tela = new JLabel("");
		lblFIXO2Tela.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblFIXO2Tela.setBounds(20, 8, 114, 39);
		panelFIXO2Tela.add(lblFIXO2Tela);
		panelFIXO2Tela.setVisible(false);
		
		lblFLTela = new JLabel("");
		lblFLTela.setBounds(280, 5, 91, 39);
		lblFLTela.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel.add(lblFLTela);
		
		lblTEXTOTela = new JLabel("");
		lblTEXTOTela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(planoAtual != -1){
					visualizaInserirTexto(true);
				}
			}
		});
		lblTEXTOTela.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTEXTOTela.setBounds(266, 105, 270, 36);
		panel.add(lblTEXTOTela);
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				//System.out.println("entrou"+arg0.getExtendedKeyCode()+arg0.getKeyChar());
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER){
					String temp = textArea.getText();
					temp = temp.substring(0,temp.length()-1);					
					strips[planoAtual].setTexto(temp);
					visualizaInserirTexto(false);
					lblTEXTOTela.setText(temp);
				}
			}
		});
		
		
		//textArea = new JTextArea();
		textArea.setColumns(10);
		textArea.setBackground(SystemColor.menu);
		textArea.setForeground(Color.BLACK);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textArea.setBounds(443, 22, 100, 22);
		
		//scrollPane = new JScrollPane(textArea);
		//scrollPane.setLayout(null);
		scrollPane.setBounds(770, 458, 261, 58);
		panelTela.add(scrollPane);
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		textArea.setBorder(null);
		scrollPane.setBorder(null);
		scrollPane.setPreferredSize(new Dimension(5,5));
		//scrollPane.set
		//scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(30, 30));
		//canvas.createImage();
		
				
		JPanel panelStrips = new JPanel();
		panelStrips.setBounds(1114, 0, 240, 696);
		contentPane.add(panelStrips);
		panelStrips.setBackground(Color.BLACK);
		panelStrips.setLayout(null);
		
		JPanel panelTAP681 = new JPanel();
		panelTAP681.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				visualizaPlano(0);
			}
		});
		panelTAP681.setBounds(10, 11, 222, 50);
		panelStrips.add(panelTAP681);
		panelTAP681.setLayout(null);
		
		//lblMAT[0] = new JLabel("");
		lblMAT[0].setBounds(5, 0, 50, 14);
		panelTAP681.add(lblMAT[0]);
		
		//lblTRAN[0] = new JLabel("");
		lblTRAN[0].setBounds(60, 0, 46, 14);
		panelTAP681.add(lblTRAN[0]);
		
		//lblPROC[0] = new JLabel("");
		lblPROC[0].setBounds(5, 12, 46, 14);
		panelTAP681.add(lblPROC[0]);
		
		//lblDEST[0] = new JLabel("");
		lblDEST[0].setBounds(60, 12, 46, 14);
		panelTAP681.add(lblDEST[0]);
		
		//lblTIPO[0] = new JLabel("");
		lblTIPO[0].setBounds(60, 24, 46, 14);
		panelTAP681.add(lblTIPO[0]);
		
		//lblTIPO[0] = new JLabel("");
		lblTIPO[0].setBounds(5, 36, 46, 14);
		panelTAP681.add(lblTIPO[0]);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(98, 15, 52, 28);
		panelTAP681.add(panel_1);
		panel_1.setLayout(null);
		
		//lblFIXO1[0] = new JLabel("");
		lblFIXO1[0].setBounds(10, 5, 46, 14);
		panel_1.add(lblFIXO1[0]);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_2.setBounds(154, 15, 52, 27);
		panelTAP681.add(panel_2);
		
		//lblFIXO2[0] = new JLabel("");
		lblFIXO2[0].setBounds(10, 5, 53, 14);
		panel_2.add(lblFIXO2[0]);
		
		//lblFL[0] = new JLabel("");
		lblFL[0].setBounds(110, 0, 46, 14);
		panelTAP681.add(lblFL[0]);
		
		JPanel panelAFR456 = new JPanel();
		panelAFR456.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				visualizaPlano(1);
			}
		});
		panelAFR456.setLayout(null);
		panelAFR456.setBounds(10, 74, 222, 50);
		panelStrips.add(panelAFR456);
		
		//lblMAT[1] = new JLabel("");
		lblMAT[1].setBounds(5, 0, 50, 14);
		panelAFR456.add(lblMAT[1]);
		
		//lblTRAN[1] = new JLabel("");
		lblTRAN[1].setBounds(60, 0, 46, 14);
		panelAFR456.add(lblTRAN[1]);
		
		//lblPROC[1] = new JLabel("");
		lblPROC[1].setBounds(5, 12, 46, 14);
		panelAFR456.add(lblPROC[1]);
		
		//lblDEST[1] = new JLabel("");
		lblDEST[1].setBounds(60, 12, 46, 14);
		panelAFR456.add(lblDEST[1]);
		
		//lblTIPO[1] = new JLabel("");
		lblTIPO[1].setBounds(60, 24, 46, 14);
		panelAFR456.add(lblTIPO[1]);
		
		//lblTIPO[1] = new JLabel("");
		lblTIPO[1].setBounds(5, 36, 46, 14);
		panelAFR456.add(lblTIPO[1]);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_4.setBounds(98, 15, 52, 28);
		panelAFR456.add(panel_4);
		
		//lblFIXO1[1] = new JLabel("");
		lblFIXO1[1].setBounds(10, 5, 46, 14);
		panel_4.add(lblFIXO1[1]);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_5.setBounds(154, 15, 52, 27);
		panelAFR456.add(panel_5);
		
		//lblFIXO2[1] = new JLabel("");
		lblFIXO2[1].setBounds(10, 5, 53, 14);
		panel_5.add(lblFIXO2[1]);
		
	//lblFL[1] = new JLabel("");
		lblFL[1].setBounds(110, 0, 46, 14);
		panelAFR456.add(lblFL[1]);
		
		JPanel panelSIA321 = new JPanel();
		panelSIA321.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visualizaPlano(2);
			}
		});
		panelSIA321.setLayout(null);
		panelSIA321.setBounds(10, 135, 222, 50);
		panelStrips.add(panelSIA321);
		
		// lblMAT[2] = new JLabel("SIA321");
		lblMAT[2].setBounds(5, 0, 50, 14);
		panelSIA321.add(lblMAT[2]);
		
		// lblTRAN[2] = new JLabel("A5432");
		lblTRAN[2].setBounds(60, 0, 46, 14);
		panelSIA321.add(lblTRAN[2]);
		
		// lblPROC[2] = new JLabel("SBRF");
		lblPROC[2].setBounds(5, 12, 46, 14);
		panelSIA321.add(lblPROC[2]);
		
		// lblDEST[2] = new JLabel("EGGL");
		lblDEST[2].setBounds(60, 12, 46, 14);
		panelSIA321.add(lblDEST[2]);
		
		// lblTIPO[2] = new JLabel("A320");
		lblTIPO[2].setBounds(60, 24, 46, 14);
		panelSIA321.add(lblTIPO[2]);
		
		// lblTIPO[2] = new JLabel("1215");
		lblTIPO[2].setBounds(5, 36, 46, 14);
		panelSIA321.add(lblTIPO[2]);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_7.setBounds(98, 15, 52, 28);
		panelSIA321.add(panel_7);
		
		// lblFIXO1[2] = new JLabel("MIDGE");
		lblFIXO1[2].setBounds(10, 5, 46, 14);
		panel_7.add(lblFIXO1[2]);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_8.setBounds(154, 15, 52, 27);
		panelSIA321.add(panel_8);
		
		// lblFIXO2[2] = new JLabel("FIBER");
		lblFIXO2[2].setBounds(10, 5, 53, 14);
		panel_8.add(lblFIXO2[2]);
		
		//lblFL[2] = new JLabel("FL310");
		lblFL[2].setBounds(110, 0, 46, 14);
		panelSIA321.add(lblFL[2]);
		
		JPanel panelCWC799 = new JPanel();
		panelCWC799.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visualizaPlano(3);
			}
		});
		panelCWC799.setLayout(null);
		panelCWC799.setBounds(10, 198, 222, 50);
		panelStrips.add(panelCWC799);
		
		// lblMAT[3] = new JLabel("CWC799");
		lblMAT[3].setBounds(5, 0, 62, 14);
		panelCWC799.add(lblMAT[3]);
		
		// lblTRAN[3] = new JLabel("A5312");
		lblTRAN[3].setBounds(60, 0, 46, 14);
		panelCWC799.add(lblTRAN[3]);
		
		// lblPROC[3] = new JLabel("SBSV");
		lblPROC[3].setBounds(5, 12, 46, 14);
		panelCWC799.add(lblPROC[3]);
		
		// lblDEST[3] = new JLabel("EDDF");
		lblDEST[3].setBounds(60, 12, 46, 14);
		panelCWC799.add(lblDEST[3]);
		
		// lblTIPO[3] = new JLabel("B777");
		lblTIPO[3].setBounds(60, 24, 46, 14);
		panelCWC799.add(lblTIPO[3]);
		
		// lblTIPO[3] = new JLabel("1220");
		lblTIPO[3].setBounds(5, 36, 46, 14);
		panelCWC799.add(lblTIPO[3]);
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_10.setBounds(98, 15, 52, 28);
		panelCWC799.add(panel_10);
		
		// lblFIXO1[3] = new JLabel("DAMAS");
		lblFIXO1[3].setBounds(10, 5, 46, 14);
		panel_10.add(lblFIXO1[3]);
		
		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_11.setBounds(154, 15, 52, 27);
		panelCWC799.add(panel_11);
		
		// lblFIXO2[3] = new JLabel("TETRA");
		lblFIXO2[3].setBounds(10, 5, 53, 14);
		panel_11.add(lblFIXO2[3]);
		
		//lblFL[3] = new JLabel("FL330");
		lblFL[3].setBounds(110, 0, 46, 14);
		panelCWC799.add(lblFL[3]);
		
		JPanel panelAWE981 = new JPanel();
		panelAWE981.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visualizaPlano(4);
			}
		});
		panelAWE981.setLayout(null);
		panelAWE981.setBounds(10, 259, 222, 50);
		panelStrips.add(panelAWE981);
		
		// lblTRAN[4] = new JLabel("A5043");
		lblTRAN[4].setBounds(60, 0, 46, 14);
		panelAWE981.add(lblTRAN[4]);
		
		// lblPROC[4] = new JLabel("SBFZ");
		lblPROC[4].setBounds(5, 12, 46, 14);
		panelAWE981.add(lblPROC[4]);
		
		// lblDEST[4] = new JLabel("LSZH");
		lblDEST[4].setBounds(60, 12, 46, 14);
		panelAWE981.add(lblDEST[4]);
		
		// lblTIPO[4] = new JLabel("LJ35");
		lblTIPO[4].setBounds(60, 24, 46, 14);
		panelAWE981.add(lblTIPO[4]);
		
		// lblTIPO[4] = new JLabel("1210");
		lblTIPO[4].setBounds(5, 36, 46, 14);
		panelAWE981.add(lblTIPO[4]);
		
		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_13.setBounds(98, 15, 52, 28);
		panelAWE981.add(panel_13);
		
		// lblFIXO1[4] = new JLabel("ABDUL");
		lblFIXO1[4].setBounds(10, 5, 46, 14);
		panel_13.add(lblFIXO1[4]);
		
		JPanel panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_14.setBounds(154, 15, 52, 27);
		panelAWE981.add(panel_14);
		
		// lblFIXO2[4] = new JLabel("DAMAS");
		lblFIXO2[4].setBounds(10, 5, 53, 14);
		panel_14.add(lblFIXO2[4]);
		
		//lblFL[4] = new JLabel("FL310");
		lblFL[4].setBounds(110, 0, 46, 14);
		panelAWE981.add(lblFL[4]);
		
		 //lblMAT[4] = new JLabel("AWE981");
		lblMAT[4].setBounds(5, 0, 62, 14);
		panelAWE981.add(lblMAT[4]);
		
		JPanel panelTHY771 = new JPanel();
		panelTHY771.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visualizaPlano(5);
			}
		});
		panelTHY771.setLayout(null);
		panelTHY771.setBounds(10, 322, 222, 50);
		panelStrips.add(panelTHY771);
		
		// lblMAT[5] = new JLabel("THY771");
		lblMAT[5].setBounds(5, 0, 62, 14);
		panelTHY771.add(lblMAT[5]);
		
		// lblTRAN[5] = new JLabel("A5112");
		lblTRAN[5].setBounds(60, 0, 46, 14);
		panelTHY771.add(lblTRAN[5]);
		
		// lblPROC[5] = new JLabel("EDDM");
		lblPROC[5].setBounds(5, 12, 46, 14);
		panelTHY771.add(lblPROC[5]);
		
		// lblDEST[5] = new JLabel("SBGL");
		lblDEST[5].setBounds(60, 12, 46, 14);
		panelTHY771.add(lblDEST[5]);
		
		// lblTIPO[5] = new JLabel("E190");
		lblTIPO[5].setBounds(60, 24, 46, 14);
		panelTHY771.add(lblTIPO[5]);
		
		// lblTIPO[5] = new JLabel("1205");
		lblTIPO[5].setBounds(5, 36, 46, 14);
		panelTHY771.add(lblTIPO[5]);
		
		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_16.setBounds(98, 15, 52, 28);
		panelTHY771.add(panel_16);
		
		// lblFIXO1[5] = new JLabel("ANDER");
		lblFIXO1[5].setBounds(10, 5, 46, 14);
		panel_16.add(lblFIXO1[5]);
		
		JPanel panel_17 = new JPanel();
		panel_17.setLayout(null);
		panel_17.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_17.setBounds(154, 15, 52, 27);
		panelTHY771.add(panel_17);
		
		// lblFIXO2[5] = new JLabel("FIBER");
		lblFIXO2[5].setBounds(10, 5, 53, 14);
		panel_17.add(lblFIXO2[5]);
		
	//	lblFL[5] = new JLabel("FL400");
		lblFL[5].setBounds(110, 0, 46, 14);
		panelTHY771.add(lblFL[5]);
		
		JPanel panelCCA652 = new JPanel();
		panelCCA652.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visualizaPlano(6);
			}
		});
		panelCCA652.setLayout(null);
		panelCCA652.setBounds(10, 383, 222, 50);
		panelStrips.add(panelCCA652);
		
		// lblMAT[6] = new JLabel("CCA652");
		lblMAT[6].setBounds(5, 0, 62, 14);
		panelCCA652.add(lblMAT[6]);
		
		// lblTRAN[6] = new JLabel("A5121");
		lblTRAN[6].setBounds(60, 0, 46, 14);
		panelCCA652.add(lblTRAN[6]);
		
		// lblPROC[6] = new JLabel("LEMD");
		lblPROC[6].setBounds(5, 12, 46, 14);
		panelCCA652.add(lblPROC[6]);
		
		// lblDEST[6] = new JLabel("SBNT");
		lblDEST[6].setBounds(60, 12, 46, 14);
		panelCCA652.add(lblDEST[6]);
		
		 //lblTIPO[6] = new JLabel("B727");
		lblTIPO[6].setBounds(60, 24, 46, 14);
		panelCCA652.add(lblTIPO[6]);
		
		// lblTIPO[6] = new JLabel("1245");
		lblTIPO[6].setBounds(5, 36, 46, 14);
		panelCCA652.add(lblTIPO[6]);
		
		JPanel panel_19 = new JPanel();
		panel_19.setLayout(null);
		panel_19.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_19.setBounds(98, 15, 52, 28);
		panelCCA652.add(panel_19);
		
		// lblFIXO1[6] = new JLabel("DAMAS");
		lblFIXO1[6].setBounds(10, 5, 46, 14);
		panel_19.add(lblFIXO1[6]);
		
		JPanel panel_20 = new JPanel();
		panel_20.setLayout(null);
		panel_20.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_20.setBounds(154, 15, 52, 27);
		panelCCA652.add(panel_20);
		
		// lblFIXO2[6] = new JLabel("TETRA");
		lblFIXO2[6].setBounds(10, 5, 53, 14);
		panel_20.add(lblFIXO2[6]);
		
		//lblFL[6] = new JLabel("FL360");
		lblFL[6].setBounds(110, 0, 46, 14);
		panelCCA652.add(lblFL[6]);
		
		JPanel panelDLH179 = new JPanel();
		panelDLH179.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visualizaPlano(7);
			}
		});
		panelDLH179.setLayout(null);
		panelDLH179.setBounds(10, 446, 222, 50);
		panelStrips.add(panelDLH179);
		
		//lblMAT[7] = new JLabel("DLH179");
		lblMAT[7].setBounds(5, 0, 62, 14);
		panelDLH179.add(lblMAT[7]);
		
		//lblTRAN[7] = new JLabel("A5133");
		lblTRAN[7].setBounds(60, 0, 46, 14);
		panelDLH179.add(lblTRAN[7]);
		
		//lblPROC[7] = new JLabel("SBKP");
		lblPROC[7].setBounds(5, 12, 46, 14);
		panelDLH179.add(lblPROC[7]);
		
		//lblDEST[7] = new JLabel("SUMU");
		lblDEST[7].setBounds(60, 12, 46, 14);
		panelDLH179.add(lblDEST[7]);
		
		//lblTIPO[7] = new JLabel("A320");
		lblTIPO[7].setBounds(60, 24, 46, 14);
		panelDLH179.add(lblTIPO[7]);
		
		//lblTIPO[7] = new JLabel("1250");
		lblTIPO[7].setBounds(5, 36, 46, 14);
		panelDLH179.add(lblTIPO[7]);
		
		JPanel panel_22 = new JPanel();
		panel_22.setLayout(null);
		panel_22.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_22.setBounds(98, 15, 52, 28);
		panelDLH179.add(panel_22);
		
		//lblFIXO1[7] = new JLabel("FIBER");
		lblFIXO1[7].setBounds(10, 5, 46, 14);
		panel_22.add(lblFIXO1[7]);
		
		JPanel panel_23 = new JPanel();
		panel_23.setLayout(null);
		panel_23.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_23.setBounds(154, 15, 52, 27);
		panelDLH179.add(panel_23);
		
		//lblFIXO2[7] = new JLabel("ANDER");
		lblFIXO2[7].setBounds(10, 5, 53, 14);
		panel_23.add(lblFIXO2[7]);
		
		//lblFL[7] = new JLabel("FL380");
		lblFL[7].setBounds(110, 0, 46, 14);
		panelDLH179.add(lblFL[7]);
		
		JPanel panelAEA401 = new JPanel();
		panelAEA401.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visualizaPlano(8);
			}
		});
		panelAEA401.setLayout(null);
		panelAEA401.setBounds(10, 507, 222, 50);
		panelStrips.add(panelAEA401);
		
		//lblMAT[8] = new JLabel("AEA401");
		lblMAT[8].setBounds(5, 0, 62, 14);
		panelAEA401.add(lblMAT[8]);
		
		//lblTRAN[8] = new JLabel("A5544");
		lblTRAN[8].setBounds(60, 0, 46, 14);
		panelAEA401.add(lblTRAN[8]);
		
		//lblPROC[8] = new JLabel("SBSP");
		lblPROC[8].setBounds(5, 12, 46, 14);
		panelAEA401.add(lblPROC[8]);
		
		//lblDEST[8] = new JLabel("LPPT");
		lblDEST[8].setBounds(60, 12, 46, 14);
		panelAEA401.add(lblDEST[8]);
		
		//lblTIPO[8] = new JLabel("B747");
		lblTIPO[8].setBounds(60, 24, 46, 14);
		panelAEA401.add(lblTIPO[8]);
		
		//lblTIPO[8] = new JLabel("1300");
		lblTIPO[8].setBounds(5, 36, 46, 14);
		panelAEA401.add(lblTIPO[8]);
		
		JPanel panel_25 = new JPanel();
		panel_25.setLayout(null);
		panel_25.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_25.setBounds(98, 15, 52, 28);
		panelAEA401.add(panel_25);
		
		//lblFIXO1[8] = new JLabel("FIBER");
		lblFIXO1[8].setBounds(10, 5, 46, 14);
		panel_25.add(lblFIXO1[8]);
		
		JPanel panel_26 = new JPanel();
		panel_26.setLayout(null);
		panel_26.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_26.setBounds(154, 15, 52, 27);
		panelAEA401.add(panel_26);
		
		//lblFIXO2[8] = new JLabel("ANDER");
		lblFIXO2[8].setBounds(10, 5, 53, 14);
		panel_26.add(lblFIXO2[8]);
		
		//lblFL[8] = new JLabel("FL320");
		lblFL[8].setBounds(110, 0, 46, 14);
		panelAEA401.add(lblFL[8]);
		
		JPanel panelARG442 = new JPanel();
		panelARG442.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visualizaPlano(9);
			}
		});
		panelARG442.setLayout(null);
		panelARG442.setBounds(10, 568, 222, 50);
		panelStrips.add(panelARG442);
		
		//lblMAT[9] = new JLabel("ARG442");
		lblMAT[9].setBounds(5, 0, 62, 14);
		panelARG442.add(lblMAT[9]);
		
		//lblTRAN[9] = new JLabel("A5555");
		lblTRAN[9].setBounds(60, 0, 46, 14);
		panelARG442.add(lblTRAN[9]);
		
		//lblPROC[9] = new JLabel("SAEZ");
		lblPROC[9].setBounds(5, 12, 46, 14);
		panelARG442.add(lblPROC[9]);
		
		//lblDEST[9] = new JLabel("LFPG");
		lblDEST[9].setBounds(60, 12, 46, 14);
		panelARG442.add(lblDEST[9]);
		
		//lblTIPO[9] = new JLabel("B777");
		lblTIPO[9].setBounds(60, 24, 46, 14);
		panelARG442.add(lblTIPO[9]);
		
		//lblTIPO[9] = new JLabel("1310");
		lblTIPO[9].setBounds(5, 36, 46, 14);
		panelARG442.add(lblTIPO[9]);
		
		JPanel panel_28 = new JPanel();
		panel_28.setLayout(null);
		panel_28.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_28.setBounds(98, 15, 52, 28);
		panelARG442.add(panel_28);
		
		//lblFIXO1[9] = new JLabel("MIDGE");
		lblFIXO1[9].setBounds(10, 5, 46, 14);
		panel_28.add(lblFIXO1[9]);
		
		JPanel panel_29 = new JPanel();
		panel_29.setLayout(null);
		panel_29.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_29.setBounds(154, 15, 52, 27);
		panelARG442.add(panel_29);
		
		//lblFIXO2[9] = new JLabel("FIBER");
		lblFIXO2[9].setBounds(10, 5, 53, 14);
		panel_29.add(lblFIXO2[9]);
		
		//lblFL[9] = new JLabel("FL390");
		lblFL[9].setBounds(110, 0, 46, 14);
		panelARG442.add(lblFL[9]);
		
		JPanel panelTAP756 = new JPanel();
		panelTAP756.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visualizaPlano(10);
			}
		});
		panelTAP756.setLayout(null);
		panelTAP756.setBounds(10, 629, 222, 50);
		panelStrips.add(panelTAP756);
		
		//lblMAT[10] = new JLabel("TAP756");
		lblMAT[10].setBounds(5, 0, 62, 14);
		panelTAP756.add(lblMAT[10]);
		
		//lblTRAN[10] = new JLabel("A5412");
		lblTRAN[10].setBounds(60, 0, 46, 14);
		panelTAP756.add(lblTRAN[10]);
		
		//lblPROC[10] = new JLabel("SBBR");
		lblPROC[10].setBounds(5, 12, 46, 14);
		panelTAP756.add(lblPROC[10]);
		
		//lblDEST[10] = new JLabel("LPPT");
		lblDEST[10].setBounds(60, 12, 46, 14);
		panelTAP756.add(lblDEST[10]);
		
		//lblTIPO[10] = new JLabel("A320");
		lblTIPO[10].setBounds(60, 24, 46, 14);
		panelTAP756.add(lblTIPO[10]);
		
		//lblTIPO[10] = new JLabel("1320");
		lblTIPO[10].setBounds(5, 36, 46, 14);
		panelTAP756.add(lblTIPO[10]);
		
		JPanel panel_31 = new JPanel();
		panel_31.setLayout(null);
		panel_31.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_31.setBounds(98, 15, 52, 28);
		panelTAP756.add(panel_31);
		
		//lblFIXO1[10] = new JLabel("TETRA");
		lblFIXO1[10].setBounds(10, 5, 46, 14);
		panel_31.add(lblFIXO1[10]);
		
		JPanel panel_32 = new JPanel();
		panel_32.setLayout(null);
		panel_32.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
		panel_32.setBounds(154, 15, 52, 27);
		panelTAP756.add(panel_32);
		
		//lblFIXO2[10] = new JLabel("DAMAS");
		lblFIXO2[10].setBounds(10, 5, 53, 14);
		panel_32.add(lblFIXO2[10]);
		
		//lblFL[10] = new JLabel("FL410");
		lblFL[10].setBounds(110, 0, 46, 14);
		panelTAP756.add(lblFL[10]);
		
		this.modificaPosPista(0,415,350);
		this.modificaPosPista(1,450,70);
		this.modificaPosPista(2,180,70);
		this.modificaPosPista(3,540,120);
		this.modificaPosPista(4,270,250);
		this.modificaPosPista(5,970,70);
		this.modificaPosPista(6,540,220);
		this.modificaPosPista(7,600,70);
		this.modificaPosPista(8,800,70);
		this.modificaPosPista(9,345,70);
		this.modificaPosPista(10,540,480);		
		
		//inicia os pontos
		abdul = new Point(290,250);
		gamer = new Point(40,70);
		fiber = new Point(540,70);
		ander = new Point(990,70);
		midge = new Point(290,70);
		damas = new Point(540,250);
		tetra = new Point(540,450);
				
		
		//calcula as trajetorias
		for(int i = 0; i < 11; i++){
			//System.out.println(strips[i].getFixo1()+"|"+strips[i].getFixo2());
			//System.out.println(this.getPonto(strips[i].getFixo1()).toString()+"|"+this.getPonto(strips[i].getFixo2()).toString());
			//Vector temp = (new Reta(pista[i].getLocation(),this.getPonto(strips[i].getFixo1()))).draw();
			//temp.addAll((new Reta(this.getPonto(strips[i].getFixo1()),this.getPonto(strips[i].getFixo2()))).draw());
			//Vector temp = (new Reta(pista[i].getLocation(),this.getPonto(strips[i].getFixo2()))).draw();
			this.trajetorias[i] = ((new Reta(pista[i].getLocation(),this.getPonto(strips[i].getFixo2()))).draw()).iterator();
		}
		/*for(int i = 0; i < 11; i++){
			System.out.print("trajetoria "+i+":");
			while(this.trajetorias[i].hasNext()){
				Point p = ((Point)trajetorias[i].next());
				System.out.print("("+p.x+","+p.y+"),");
			}
			System.out.println("");
		}*/
	}
	
	public Point getPonto(String ponto){
		if(ponto.equals("ABDUL")){
			return this.abdul;
		}
		else if(ponto.equals("GAMER")){
			return this.gamer;
		}
		else if(ponto.equals("FIBER")){
			return this.fiber;
		}
		else if(ponto.equals("ANDER")){
			return this.ander;
		}
		else if(ponto.equals("MIDGE")){
			return this.midge;
		}
		else if(ponto.equals("DAMAS")){
			return this.damas;
		}
		else {
			return this.tetra;
		}
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		//GregorianCalendar d = new GregorianCalendar();
		//int anterior = d.get(GregorianCalendar.SECOND);
		while(true){			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			for(int i = 0; i < 11; i++){
				//for(int j = 0; j < 3; j++){
					if(trajetorias[i].hasNext()){
						this.trajetorias[i].next();
					}
				//}
				if(trajetorias[i].hasNext()){
					Point p = ((Point)trajetorias[i].next());
					this.modificaPosPista(i, p.x, p.y);
				}			
			}
		}		
	}	
}

/*d = new GregorianCalendar();
int atual = d.get(GregorianCalendar.SECOND);
int prox = (anterior+3)%60;
textAreaConsole.setText("Segundos: "+atual+" | "+anterior +" | "+prox);
if((prox > 2 && atual >= prox)||(prox <= 2 && (atual+3)%60 >= prox+3)){ //
	anterior = atual;
	this.modificaPosPista(0, this.pista[0].getX() + 1, this.pista[0].getY() + 1);
	this.modificaPosPista(1, this.pista[1].getX() + 1, this.pista[1].getY() + 1);
	this.modificaPosPista(2, this.pista[2].getX() + 1, this.pista[2].getY() + 1);
	this.modificaPosPista(3, this.pista[3].getX() + 1, this.pista[3].getY() + 1);
	this.modificaPosPista(4, this.pista[4].getX() + 1, this.pista[4].getY() + 1);
}*/