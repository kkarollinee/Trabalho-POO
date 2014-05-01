package grafico;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


 public class WindowOfModule extends JFrame{
	 public JPanel	total, //painel que contem todos os elementos
	 				buttonPanel, //painel com botoes
	 				gridButtonPanel, //painel interno de buttonPanel que vai ter GridLayout
	 				allPanels; //contem todas as partes do modulo, cadastro, listagem, etc
	 
	 public  WindowOfModule(String title, JPanel [] panels, JButton [] bPanels){
		 
		setTitle(title);
		setLocation(50,100);
		setSize(800,600);
		
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