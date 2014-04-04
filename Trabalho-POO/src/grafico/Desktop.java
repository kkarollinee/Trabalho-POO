package grafico;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.util.ArrayList;

public class Desktop extends JFrame{
	// add gridLayout
	JPanel icones = new JPanel(){
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			int w = getWidth();
			int h = getHeight();
			Color color1 = new Color(234,237,242);
			Color color2 = new Color(99,140,162);
			GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
			g2d.setPaint(gp);
			g2d.fillRect(0, 0, w, h);
		}
	};
	
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

