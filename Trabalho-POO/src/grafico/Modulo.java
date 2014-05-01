package grafico;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import javax.swing.text.IconView;

import sistema.*;

public class Modulo extends JPanel implements MouseListener, WindowListener{
	JLabel icone = new JLabel("");
	ImageIcon imgNormal;
	ImageIcon imgSelected;
	String ImgUrl;
	static WindowOfModule window = null;
	
	public Modulo(String IMG, String altText){
		setLayout(new GridBagLayout());
		setOpaque(false);
		setBackground(new Color(0,0,0,0));
		
		ImgUrl = IMG;
		
		icone.setToolTipText(altText);
		
		imgNormal = new ImageIcon("Imagens/"+ImgUrl.toLowerCase()+".png");
		imgSelected = new ImageIcon("Imagens/"+ImgUrl.toLowerCase()+"_selected.png");
		
		icone.setIcon(imgNormal);
		add(this.icone);
		
		icone.addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		switch(ImgUrl){
			case "produtos":
				if(window == null){
					window = new WindowOfModule("Teste",Produto.getPanels(),Produto.getButtons());
			 		window.addWindowListener(this);
				}
				
				window.setVisible(true);
				break;
			case "fornecedores":
				if(window == null){
					window = new WindowOfModule("Teste",Fornecedor.getPanels(),Fornecedor.getButtons());
			 		window.addWindowListener(this);
				}
				
				window.setVisible(true);
				break;
			case "clientes":
				if(window == null){
					window = new WindowOfModule("Teste",null,null);
			 		window.addWindowListener(this);
				}
				
				window.setVisible(true);
				break;
			case "notas fiscais de entrada":
				if(window == null){
					window = new WindowOfModule("Teste",null,null);
			 		window.addWindowListener(this);
				}
				
				window.setVisible(true);
				break;
			case "notas fiscais de saida":
				if(window == null){
					window = new WindowOfModule("Teste",null,null);
			 		window.addWindowListener(this);
				}
				
				window.setVisible(true);
				break;
					
			case "minha empresa":
				if(window == null){
					window = new WindowOfModule("Teste",null,null);
			 		window.addWindowListener(this);
				}
				
				window.setVisible(true);
				break;
			case "funcionario":
				if(window == null){
					window = new WindowOfModule("Teste",null,null);
			 		window.addWindowListener(this);
				}
				
				window.setVisible(true);
				break;
			case "relatorios":
				if(window == null){
					window = new WindowOfModule("Teste",null,null);
			 		window.addWindowListener(this);
				}
				
				window.setVisible(true);
				break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		icone.setIcon(imgSelected);
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		icone.setIcon(imgNormal);
	}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) {
		window = null;
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {}
	@Override
	public void windowDeiconified(WindowEvent arg0) {}
	@Override
	public void windowIconified(WindowEvent arg0) {}
	@Override
	public void windowOpened(WindowEvent arg0) {}
}
