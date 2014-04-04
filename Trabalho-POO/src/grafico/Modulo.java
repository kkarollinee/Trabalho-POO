package grafico;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import javax.swing.text.IconView;

public class Modulo extends JPanel implements MouseListener{
	JLabel icone = new JLabel("");
	ImageIcon imgNormal;
	ImageIcon imgSelected;
	String ImgUrl;
	
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
}
