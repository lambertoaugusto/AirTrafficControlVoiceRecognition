package project;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class ATCV{
	
	public ConfigurationManager config;
	public Recognizer recognizer;
	public Microphone microphone;
	public Strip[] strips = new Strip[11];
	public Simulador simula;
	
	public ATCV(){
		config = new ConfigurationManager(ATCV.class.getResource("ATCV.config.xml"));;
    
		recognizer = (Recognizer) config.lookup("recognizer");
		recognizer.allocate();

		// Inicializa o microfone
		microphone = (Microphone) config.lookup("microphone");
		if (!microphone.startRecording()) {
			System.out.println("Cannot start microphone.");
			recognizer.deallocate();
			System.exit(1);
		}		
		iniciarStrips();
		simula = new Simulador(this.strips);
		simula.setVisible(true);		
		Thread movimenta = new Thread(simula);
		movimenta.start();
	}
	
	public Simulador getSimulador(){
		return this.simula;
	}
	
	public void iniciarStrips(){
		strips[0] = new Strip("TAP681","SBGR","LPPT","A320",5622,1200,320,"ABDUL","GAMER","");	
		strips[1] = new Strip("AFR456","SBGL","LFPG","B747",5121,1230,350,"FIBER","ANDER","");
		strips[2] = new Strip("SIA321","SBRF","EGGL","A320",5432,1215,310,"MIDGE","FIBER","");
		strips[3] = new Strip("CWC799","SBSV","EDDF","B777",5312,1220,330,"DAMAS","TETRA","");
		strips[4] = new Strip("AWE981","SBFZ","LSZH","LJ35",5043,1210,310,"ABDUL","DAMAS","");
		strips[5] = new Strip("THY771","EDDM","SBGL","E190",5112,1205,400,"ANDER","FIBER","");
		strips[6] = new Strip("CCA652","LEMD","SBNT","B727",5121,1245,360,"DAMAS","TETRA","");
		strips[7] = new Strip("DLH179","SBKP","SUMU","A320",5133,1250,380,"FIBER","ANDER","");
		strips[8] = new Strip("AEA401","SBSP","LPPT","B747",5544,1300,320,"FIBER","ANDER","");
		strips[9] = new Strip("ARG442","SAEZ","LFPG","B777",5555,1310,390,"MIDGE","FIBER","");
		strips[10] = new Strip("BAW756","SBBR","LPPT","A320",5412,1320,410,"TETRA","DAMAS","");
	}
	
	public String callSign(int number){		
		String retorno = "";
		if(number < 4){
    		Result result = recognizer.recognize();
    		retorno = result.getBestFinalResultNoFiller();
    		System.out.println("reconheceu no método "+number+": "+result+"\n");
    		retorno = retorno +callSign(++number);
    	}
    	return retorno;    	
	}
	
	public String transformaFrase(String rec){
		StringTokenizer frase = new StringTokenizer(rec);
		String retorno = "";
		boolean anteriorNumero = false;
		int fl = 0;
		while(frase.hasMoreTokens()){
			String temp = frase.nextToken();
			if(temp.equals("zero")){
				retorno = retorno + "0";
				anteriorNumero = true;
				if(fl != 0){
					if(fl==3){
						//retorno = retorno + "0";
						fl = 0;
					}
					else{
						fl++;
					}
				}
			}
			else if(temp.equals("one")){				
				retorno = retorno + "1";
				anteriorNumero = true;
				if(fl != 0){
					if(fl==3){
						retorno = retorno.substring(0, retorno.length()-1) + "0";
						fl = 0;
					}
					else{
						fl++;
					}
				}
			}
			else if(temp.equals("two")){
				retorno = retorno + "2";
				anteriorNumero = true;
				if(fl != 0){
					if(fl==3){
						retorno = retorno.substring(0, retorno.length()-1) + "0";
						fl = 0;
					}
					else{
						fl++;
					}
				}
			}
			else if(temp.equals("three")){
				retorno = retorno + "3";
				anteriorNumero = true;
				if(fl != 0){
					if(fl==3){
						retorno = retorno.substring(0, retorno.length()-1) + "0";
						fl = 0;
					}
					else{
						fl++;
					}
				}
			}
			else if(temp.equals("four")){
				retorno = retorno + "4";
				anteriorNumero = true;
				if(fl != 0){
					if(fl==3){
						retorno = retorno.substring(0, retorno.length()-1) + "0";
						fl = 0;
					}
					else{
						fl++;
					}
				}
			}
			else if(temp.equals("five")){
				retorno = retorno + "5";
				anteriorNumero = true;
				if(fl != 0){
					if(fl==3){
						retorno = retorno.substring(0, retorno.length()-1) + "0";
						fl = 0;
					}
					else{
						fl++;
					}
				}
			}
			else if(temp.equals("six")){
				retorno = retorno + "6";
				anteriorNumero = true;
				if(fl != 0){
					if(fl==3){
						retorno = retorno.substring(0, retorno.length()-1) + "0";
						fl = 0;
					}
					else{
						fl++;
					}
				}
			}
			else if(temp.equals("seven")){
				retorno = retorno + "7";
				anteriorNumero = true;
				if(fl != 0){
					if(fl==3){
						retorno = retorno.substring(0, retorno.length()-1) + "0";
						fl = 0;
					}
					else{
						fl++;
					}
				}
			}
			else if(temp.equals("eight")){
				retorno = retorno + "8";
				anteriorNumero = true;
				if(fl != 0){
					if(fl==3){
						retorno = retorno.substring(0, retorno.length()-1) + "0";
						fl = 0;
					}
					else{
						fl++;
					}
				}
			}
			else if(temp.equals("nine")){
				retorno = retorno + "9";
				anteriorNumero = true;
				if(fl != 0){
					if(fl==3){
						retorno = retorno.substring(0, retorno.length()-1) + "0";
						fl = 0;
					}
					else{
						fl++;
					}
				}
			}
			else if(temp.equals("flight")){
				retorno = retorno + "F";
			}
			else if(temp.equals("level")){
				retorno = retorno + "L";
				fl++;
			}
			else if(temp.equals("decimal")){
				retorno = retorno + ".";
			}
			else if(temp.equals("sia")||temp.equals("uae")||temp.equals("afr")||temp.equals("cwc")||temp.equals("awe")||temp.equals("dlh")||temp.equals("aea")||temp.equals("thy")||temp.equals("cca")||temp.equals("tap")||temp.equals("arg")||temp.equals("baw")){
				retorno = retorno + temp;
			}
			/*else if(temp.equals("squawk")||temp.equals("cleared")||temp.equals("contact")||temp.equals("heading")||temp.equals("by")||temp.equals("miles")||temp.equals("mile")){
				retorno = retorno + " " + temp;
				espaco = true;
			}*/
			else{
				if(anteriorNumero){
					retorno = retorno + " " + temp + " ";
					anteriorNumero = false;
				}
				else{
					retorno = retorno + temp + " ";
				}
			}						
		}
		return retorno;
	}
	
	public static void main(String[] args) {
        
		final ATCV programa = new ATCV();
        
		
		//código para detectar o ppt		
		Action actionListener = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
				System.out.println("Iniciou gravação");				
				Result result = programa.recognizer.recognize();
				String resultText = result.getBestFinalResultNoFiller();
				//String resultText = "tap six eight one vector for spacing turn right heading two three zero";
				if(!resultText.equals("")){
					resultText = programa.transformaFrase(resultText);
					programa.simula.textAreaConsole.setText(programa.simula.textAreaConsole.getText()+"Reconhecido: " + resultText + '\n');
	        		programa.simula.textAreaConsole.setCaretPosition(programa.simula.textAreaConsole.getDocument().getLength());
	        		programa.getSimulador().reconheceu(resultText);
				}
				else{
					programa.simula.textAreaConsole.setText(programa.simula.textAreaConsole.getText()+"Não foi possível reconhecer!"+'\n');
            		programa.simula.textAreaConsole.setCaretPosition(programa.simula.textAreaConsole.getDocument().getLength());
				}
			}
		};
		
		JPanel content = (JPanel) programa.getSimulador().getContentPane();
	    KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
	    
	    InputMap inputMap = content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    inputMap.put(stroke, "GRAVAR");
	    content.getActionMap().put("GRAVAR", actionListener);
		//fim de código
		
		
		/*   
		while (true) {			
            
            Result result = programa.recognizer.recognize();

            if (result != null) {
            	
            	String resultText = result.getBestFinalResultNoFiller();
            	if(resultText != ""){            		
            		//resultText = "tap six eight one contact recife center on frequency one two six decimal eight five";
           		
            		resultText = programa.transformaFrase(resultText);
            		//System.out.println(resultText+"\n");
            		programa.simula.textAreaConsole.setText(programa.simula.textAreaConsole.getText()+"Reconhecido: " + resultText + '\n');
            		programa.simula.textAreaConsole.setCaretPosition(programa.simula.textAreaConsole.getDocument().getLength());
            		programa.getSimulador().reconheceu(resultText);
        //    		programa.getSimulador().reconheceu(resultText);            		
            	}
            	else{
            		programa.simula.textAreaConsole.setText(programa.simula.textAreaConsole.getText()+"Não foi possível reconhecer!"+'\n');
            		programa.simula.textAreaConsole.setCaretPosition(programa.simula.textAreaConsole.getDocument().getLength());
            	}          
                                
                //System.out.println("Você Falou1: " + resultado + '\n');
                //System.out.println("Você Falou2: " + resultado2 + '\n');
                //System.out.println("Você Falou3: " + resultado3 + '\n');
            } else {
            	programa.simula.textAreaConsole.setText(programa.simula.textAreaConsole.getText()+"Não foi possível reconhecer!.\n");
            	programa.simula.textAreaConsole.setCaretPosition(programa.simula.textAreaConsole.getDocument().getLength());
            }
        } */       
   }

	/*@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.print("gravando");
		Result result = this.recognizer.recognize();
		String resultText = result.getBestFinalResultNoFiller();
		resultText = this.transformaFrase(resultText);
		this.simula.textAreaConsole.setText(this.simula.textAreaConsole.getText()+"Reconhecido: " + resultText + '\n');
		this.simula.textAreaConsole.setCaretPosition(this.simula.textAreaConsole.getDocument().getLength());
		this.getSimulador().reconheceu(resultText);
	}*/
}
