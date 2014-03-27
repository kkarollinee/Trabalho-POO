import javax.swing.*;
import javax.swing.text.IconView;


public class Modulo extends JPanel {
	JLabel icone = new JLabel("");
	
	public Modulo(String IMG){
		this.icone.setIcon(new ImageIcon("Imagens/"+IMG.toLowerCase()+".png"));
		this.add(this.icone);
	}
	

}
