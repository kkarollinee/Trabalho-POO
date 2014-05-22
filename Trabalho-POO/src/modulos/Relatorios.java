package modulos;

import grafico.Desktop;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Relatorios extends JPanel{
	public static JPanel[] getPanels(){
		JPanel backListPanel = new JPanel();
		
		JPanel frontListPanel = new JPanel();
		
		JPanel productList = new JPanel();
		ArrayList<ArrayList<String>> ps = Desktop.banco.lerArquivo("Relatorios");

		for(int x=0; x<ps.size(); x++){
			for(int y=0;y<6;y++){
				productList.add(new JLabel(ps.get(x).get(y)));
			}
		}
		backListPanel.add(frontListPanel);
		frontListPanel.add(productList);
		
		
		JPanel castPanel = new JPanel();
		
		JPanel Relatorios = new Relatorios();
		castPanel.add(Relatorios);
		JPanel [] p = { backListPanel, castPanel };
		return p;
		
	}

	public static JButton[] getButtons(){
		JButton [] b = { new JButton("Listagem")};
		return b;
	}
}
