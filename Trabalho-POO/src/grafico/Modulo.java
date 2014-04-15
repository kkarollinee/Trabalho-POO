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
					JButton b1 = new JButton("Listar");
			 		JButton b2 = new JButton("Cadastrar");
			 		
			 		JButton [] b = { b1, b2};
			 		
			 		JLabel l1 = new JLabel("listar");
			 		JLabel l2 = new JLabel("ESTE TEXTO DEVE SAIR");
			 		
			 		JPanel j1 = new JPanel();
			 		j1.add(l1);
			 		JPanel j2 = new JPanel();
			 		j2.add(l2);
			 		
			 		JPanel [] j = { j1, j2 };
			 		
			 		window = new WindowOfModule("Teste",j,b);
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
