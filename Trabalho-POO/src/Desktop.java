import javax.swing.*;
import java.util.ArrayList;
import java.awt.Color;
public class Desktop extends JFrame{
	// add gridLayout
	JPanel icones = new JPanel();
	public Desktop(ArrayList<Modulo> modulos) {
		setTitle("Controle de Estoque");
		setLocation(50,100);
		setSize(600,450);
		icones.setBackground(new Color(134,168,157));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		for(Modulo m: modulos){
			icones.add(m);
		}
		getContentPane().add(icones);
	}
}

