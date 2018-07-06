package project;

import java.awt.event.ActionEvent;
import java.util.GregorianCalendar;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class VerificaPPT {
	
	public String getHora() {  
		  
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

  public static void main(final String args[]) {
	System.out.print(new VerificaPPT().getHora());  
    final JFrame frame = new JFrame("Frame Key");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Action actionListener = new AbstractAction() {
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Got an M");
	}
    };

    JPanel content = (JPanel) frame.getContentPane();
    KeyStroke stroke = KeyStroke.getKeyStroke("M");

    InputMap inputMap = content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    inputMap.put(stroke, "OPEN");
    content.getActionMap().put("OPEN", actionListener);

    frame.setSize(300, 300);
    frame.setVisible(true);
  }
}

/*
public class VerificaPPT implements Runnable {

	
	public VerificaPPT(){
		
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			
			@Override
			public boolean dispatchKeyEvent(KeyEvent arg0) {
				if(arg0.isActionKey()){
					System.out.print("teclou");
				}
				// TODO Auto-generated method stub
				return false;
			}
		});
	}
	
	
	public static void main(String[] args) {
		VerificaPPT teste = new VerificaPPT();
		teste.run();
		ATCV programa = new ATCV();
	}
}
*/