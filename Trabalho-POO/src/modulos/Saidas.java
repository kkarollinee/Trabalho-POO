package modulos;

import grafico.Desktop;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.util.ArrayList;

public class Saidas {
	public JPanel[] getPanels(){
	JPanel backListPanel = new JPanel();
	
	JPanel frontListPanel = new JPanel();
	
	JPanel productList = new JPanel();
	ArrayList<ArrayList<String>> ps = Desktop.banco.lerArquivo("NotaSaidaFisico");

	productList.setLayout(new GridLayout((ps.size()+1),6,10,4));
	productList.add(new JLabel("Quantidade de produto"));
	productList.add(new JLabel("Valor unitario"));
	for(int x=0; x<ps.size(); x++){
		for(int y=0;y<6;y++){
			productList.add(new JLabel(ps.get(x).get(y)));
		}
	}
	backListPanel.add(frontListPanel);
	frontListPanel.add(productList);
	
	
	JPanel castPanel = new JPanel();
	
	//castPanel.add(NFSaidaFisico);
	JPanel [] p = { backListPanel, castPanel };
	return p;
	
}

public JButton[] getButtons(){
	JButton [] b = { new JButton("Listagem"), new JButton("Cadastro") };
	return b;
}
}
