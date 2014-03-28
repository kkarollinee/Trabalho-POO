import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;

public class Login extends JFrame{
	public Login(){
		setTitle("Controle de Estoque - Login");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(100,100);
		setSize((int)(screenSize.getWidth()-200),(int)(screenSize.getHeight()-200));
		
		JLabel textUser = new JLabel("Usuario:");
		textUser.setBounds(15, 15, 70, 15);
		JLabel textSenha = new JLabel("Senha:");
		textSenha.setBounds(15, 45, 70, 15);
		
		JTextField fieldUser = new JTextField(10);
		fieldUser.setBounds(75, 15, 70, 15);
		JTextField fieldSenha = new JTextField(10);
		fieldSenha.setBounds(75, 45, 70, 15);
		
		JPanel center = new JPanel();
		center.setLayout(null);
		
		center.add(textUser);
		center.add(fieldUser);
		center.add(textSenha);
		center.add(fieldSenha);
		

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(center,BorderLayout.CENTER);
	}
}
