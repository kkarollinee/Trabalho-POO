package grafico;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


 public class WindowsAux extends JFrame{
	 public JPanel	total, //painel que contem todos os elementos
	 				buttonPanel, //painel com botoes
	 				gridButtonPanel, //painel interno de buttonPanel que vai ter GridLayout
	 				allPanels; //contem todas as partes do modulo, cadastro, listagem, etc
	 
	 public  WindowsAux(String title, JPanel [] panels, JButton [] bPanels){
		 
		setTitle(title);
		setLocation(50,100);
		setSize(600,450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		total = new JPanel();
		total.setLayout(new BorderLayout());
		
		allPanels = new JPanel();
		allPanels.setLayout(new CardLayout());
		allPanels.setSize(200,200);
		
		gridButtonPanel = new JPanel();
		gridButtonPanel.setSize(100,1000);
		gridButtonPanel.setLayout(new GridLayout(bPanels.length,1,10,10));
		gridButtonPanel.setBackground(new Color(210,210,255));
		
		for(int x = 0; x < panels.length; x++){
			panels[x].setBackground(Color.WHITE);
			allPanels.add(panels[x],"P"+x);
		}
		for(int x = 0; x < bPanels.length; x++){
			bPanels[x].addActionListener(new ButtonHandler(x));
			gridButtonPanel.add(bPanels[x]);
		}
		
		buttonPanel = new JPanel();
		buttonPanel.add(gridButtonPanel);
		buttonPanel.setBackground(new Color(210,210,255));
		
		total.add(buttonPanel,BorderLayout.WEST);
		total.add(allPanels,BorderLayout.CENTER);
		
		getContentPane().add(total);
	}
	
 	public static void main(String arg[]) {
 		JButton b1 = new JButton("Listar");
 		JButton b2 = new JButton("Cadastrar");
 		
 		JButton [] b = { b1, b2};
 		
 		JLabel l1 = new JLabel("listar");
 		JLabel l2 = new JLabel("Cadastrar");
 		
 		JPanel j1 = new JPanel();
 		j1.add(l1);
 		JPanel j2 = new JPanel();
 		j2.add(l2);
 		
 		JPanel [] j = { j1, j2};
 		
 		new  WindowsAux("Teste",j,b).setVisible(true);
 		
	}
 	
 	private class ButtonHandler implements ActionListener{
 		int panelNumber;
 		ButtonHandler(int panelNumber){
 			this.panelNumber = panelNumber;
 		}
		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout c = (CardLayout) allPanels.getLayout();
			c.show(allPanels,"P"+panelNumber);
		}
 	}
}