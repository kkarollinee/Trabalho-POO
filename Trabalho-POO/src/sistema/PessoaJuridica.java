package sistema;
import javax.swing.*;
import banco.BD;

import java.awt.FlowLayout;
import java.util.*;
public abstract class PessoaJuridica extends JPanel{
	String [] selecaoStatus = {"Ativo", "Inativo"};
	
	JPanel razaoSocialPanel= new JPanel();
	JLabel razaoSocial = new JLabel("Razao Social: "); JTextField razaoSocialField = new JTextField (50);
	
	JPanel nomeFantasiaPanel= new JPanel();
	JLabel nomeFantasia = new JLabel("Nome Fantasia: "); JTextField nomeFantasiaField = new JTextField (49);
	
	JPanel cnpjPanel= new JPanel();
	JLabel cnpj = new JLabel("CNPJ (Somente Numeros): "); JTextField cnpjField = new JTextField (20);
	
	JPanel telefonePanel= new JPanel();
	JLabel telefone = new JLabel("Telefone (Somente Numeros): "); JTextField telefoneField = new JTextField (10);
	
	public Endereco endereco = new Endereco();
	
	JPanel statusPanel= new JPanel();
	JLabel status = new JLabel("Status: "); JComboBox statusBox = new JComboBox(selecaoStatus);
	
	
	
	public PessoaJuridica (){
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setSize(800,300);
		
		razaoSocialPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		razaoSocialPanel.add(razaoSocial); razaoSocialPanel.add(razaoSocialField);
		add(razaoSocialPanel);
		
		nomeFantasiaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		nomeFantasiaPanel.add(nomeFantasia); nomeFantasiaPanel.add(nomeFantasiaField);
		add(nomeFantasiaPanel);
		
		cnpjPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		cnpjPanel.add(cnpj); cnpjPanel.add(cnpjField);
		add(cnpjPanel);
		
		telefonePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		telefonePanel.add(telefone); telefonePanel.add(telefoneField);
		add(telefonePanel);
		
		add(endereco);
		
		statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		statusPanel.add(status); statusPanel.add(statusBox);
		add(statusPanel);
		
		
		
	}
	public int pessoa_id;
	public String razaoSocialVal;
	public String nomeFantasiaVal;
	public long cnpjVal;
	public long telefoneVal;
	public boolean statusVal;
}
