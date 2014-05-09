package sistema;
import grafico.Desktop;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Funcionario extends PessoaFisica{
	public String cargo;
	public int horarioEntrada, horarioRetorno, horarioSaida, horarioPausa;
	public double salario;
	
	public Funcionario(int id, String nome, String rg,long cpf, long tel, boolean status, Endereco endereco, String cargo, int hE, int hP, int hR, int hS,double salario){	
		this.pessoa_id = id;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.telefone = tel;
		this.status=status;
		this.endereco=endereco;
		this.cargo = cargo;
		this.horarioEntrada = hE;
		this.horarioPausa = hP;
		this.horarioRetorno = hR;
		this.horarioSaida = hS;
		this.salario = salario;
	}
	public Funcionario(ArrayList<String> f){
		//pessoa_id|nome|rg|cpf|telefone|status|rua|numero|complemento|bairro|cidade|estado|cargo|horarioEntrada|
		//horarioPausa|horarioRetorno|horarioSaida|salario
		
		this.pessoa_id = Integer.parseInt(f.get(0));
		this.nome = f.get(1);
		this.rg = f.get(2);
		this.cpf = Long.parseLong(f.get(3));
		this.telefone = Long.parseLong(f.get(4));
		this.status=Boolean.parseBoolean(f.get(5));
		this.endereco=new Endereco(f.get(6), Integer.parseInt(f.get(7)), f.get(8),f.get(9),f.get(10),f.get(11));
		this.cargo = f.get(12);
		this.horarioEntrada = Integer.parseInt(f.get(13));
		this.horarioPausa = Integer.parseInt(f.get(14));
		this.horarioRetorno = Integer.parseInt(f.get(15));
		this.horarioSaida = Integer.parseInt(f.get(16));
		this.salario = Double.parseDouble(f.get(17));
	}
	
	public Funcionario(){
		super();
	}
	
	public static JPanel[] getPanels(){
		JPanel backListPanel = new JPanel();
		
		JPanel frontListPanel = new JPanel();
		
		JPanel productList = new JPanel();
		ArrayList<ArrayList<String>> ps = Desktop.banco.lerArquivo("Funcionario");
		
		productList.setLayout(new GridLayout((ps.size()+1),6,10,4));
		productList.add(new JLabel("Razao Social"));
		productList.add(new JLabel("Cargo"));
		productList.add(new JLabel("Horario de Entrada"));
		productList.add(new JLabel("Horario de Pausa"));
		productList.add(new JLabel("Horario de Retorno"));
		productList.add(new JLabel("Horario de Saida"));
		productList.add(new JLabel("Salario"));
	
		for(int x=0; x<ps.size(); x++){
			for(int y=0;y<6;y++){
				productList.add(new JLabel(ps.get(x).get(y)));
			}
		}
		backListPanel.add(frontListPanel);
		frontListPanel.add(productList);
		
		JPanel castPanel = new JPanel();
		
		JPanel FuncionarioCadastro = new Funcionario();
		castPanel.add(FuncionarioCadastro);
		JPanel [] p = { backListPanel, castPanel };
		return p;
	}
	public static JButton[] getButtons(){
		JButton [] b = { new JButton("Listagem"), new JButton("Cadastro") };
		return b;
	}
}
