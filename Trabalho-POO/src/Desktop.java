import javax.swing.*;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

public class Desktop extends JFrame{
	// add gridLayout
	JPanel icones = new JPanel();
	public Desktop(ArrayList<Modulo> modulos) {
		setTitle("Controle de Estoque");
		setLocation(50,100);
		setSize(1000,500);
		setMinimumSize(new Dimension(650, 430));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		icones.setLayout(new GridLayout(2, 0, 0, 0));
		for(Modulo m: modulos){
			icones.add(m);
		}
		getContentPane().add(icones);
	}
}

