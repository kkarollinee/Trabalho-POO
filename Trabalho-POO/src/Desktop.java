import javax.swing.*;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.GridLayout;
public class Desktop extends JFrame{
	// add gridLayout
	JPanel icones = new JPanel();
	public Desktop(ArrayList<Modulo> modulos) {
		setTitle("Controle de Estoque");
		setLocation(50,100);
		setSize(600,450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		icones.setLayout(new GridLayout(2,1,1,1));
		for(Modulo m: modulos){
			icones.add(m);
		}
		getContentPane().add(icones);
	}
}

