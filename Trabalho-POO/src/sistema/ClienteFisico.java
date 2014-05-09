package sistema;
import grafico.Desktop;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class ClienteFisico extends PessoaFisica{
	
	public ClienteFisico(int id, String nome, String rg,long cpf, long tel, boolean status, Endereco endereco){	
		this.pessoa_id = id;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.telefone = tel;
		this.status=status;
		this.endereco=endereco;
	}
	public ClienteFisico(ArrayList<String> f){
		//pessoa_id|nome|rg|cpf|telefone|status|rua|numero|complemento|bairro|cidade|estado
		
		this.pessoa_id = Integer.parseInt(f.get(0));
		this.nome = f.get(1);
		this.rg = f.get(2);
		this.cpf = Long.parseLong(f.get(3));
		this.telefone = Long.parseLong(f.get(4));
		this.status=Boolean.parseBoolean(f.get(5));
		this.endereco=new Endereco(f.get(6), Integer.parseInt(f.get(7)), f.get(8),f.get(9),f.get(10),f.get(11));
		
		
	}
	public ClienteFisico(){
		super();
	}
	
	public static JPanel[] getPanels(){
		JPanel backListPanel = new JPanel();
		
		JPanel frontListPanel = new JPanel();
		
		JPanel productList = new JPanel();
		ArrayList<ArrayList<String>> ps = Desktop.banco.lerArquivo("ClienteFisico");

		productList.setLayout(new GridLayout((ps.size()+1),6,10,4));
		productList.add(new JLabel("Nome"));
		productList.add(new JLabel("RG"));
		productList.add(new JLabel("CPF"));
		productList.add(new JLabel("Telefone"));
		productList.add(new JLabel("Status"));
		for(int x=0; x<ps.size(); x++){
			for(int y=0;y<6;y++){
				productList.add(new JLabel(ps.get(x).get(y)));
			}
		}
		backListPanel.add(frontListPanel);
		frontListPanel.add(productList);
		
		
		JPanel castPanel = new JPanel();
		
		JPanel ClienteCadastro = new ClienteFisico();
		castPanel.add(ClienteCadastro);
		JPanel [] p = { backListPanel, castPanel };
		return p;
		
	}

	public static JButton[] getButtons(){
		JButton [] b = { new JButton("Listagem"), new JButton("Cadastro") };
		return b;
	}
	
		
		
}
