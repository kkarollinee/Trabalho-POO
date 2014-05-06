package sistema;
import java.awt.FlowLayout;
import java.util.Scanner;
import javax.swing.*;

public class Endereco extends JPanel{

	JPanel ruaPanel= new JPanel();
	JLabel rua = new JLabel("Rua: "); JTextField ruaField = new JTextField (30);
	JLabel numero = new JLabel("Numero: "); JTextField numeroField = new JTextField (5);
	JLabel complemento = new JLabel("Complemento: "); JTextField complementoField = new JTextField (5);
	
	JPanel bairroPanel= new JPanel();
	JLabel bairro = new JLabel("Bairro: "); JTextField bairroField = new JTextField (20);
	JLabel cidade = new JLabel("Cidade: "); JTextField cidadeField = new JTextField (21);
	JLabel estado = new JLabel("Estado: "); JTextField estadoField = new JTextField (2);
	
	
	public Endereco(){
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setSize(800,80);
		
		
		ruaPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		ruaPanel.add(rua); ruaPanel.add(ruaField);
		ruaPanel.add(numero); ruaPanel.add(numeroField);
		ruaPanel.add(complemento); ruaPanel.add(complementoField);
		add(ruaPanel);
		
		bairroPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		bairroPanel.add(bairro); bairroPanel.add(bairroField);
		bairroPanel.add(cidade); bairroPanel.add(cidadeField);
		bairroPanel.add(estado); bairroPanel.add(estadoField);
		add(bairroPanel);
		
	}
	public String ruaVal;
	public int numeroVal;
	public String complementoVal;
	public String bairroVal;
	public String cidadeVal;
	public String estadoVal;
	
	public Endereco (String rua,int numero, String complemento, String bairro, String cidade, String estado){
		this.ruaVal=rua;
		this.numeroVal=numero;
		this.complementoVal=complemento;
		this.bairroVal=bairro;
		this.cidadeVal=cidade;
		this.estadoVal=estado;
	}
}
