package sistema;
import grafico.Desktop;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Fornecedor extends PessoaJuridica{
	public Fornecedor (int pessoa_id, String razao, String fantasia, long cnpj, long tel, boolean status, Endereco endereco){
		this.pessoa_id=pessoa_id;
		this.razaoSocialVal=razao;
		this.nomeFantasiaVal=fantasia;
		this.cnpjVal=cnpj;
		this.telefoneVal=tel;
		this.statusVal=status;
		this.endereco=endereco;
	}
	public Fornecedor(ArrayList<String> M){
		//pessoa_id|razaoSocial|nomeFantasia|cnpj|telefone|status|rua|numero|complemento|bairro|cidade|estado
		
		this.pessoa_id=Integer.parseInt(M.get(0));
		this.razaoSocialVal=M.get(1);
		this.nomeFantasiaVal=M.get(2);
		this.cnpjVal=Long.parseLong(M.get(3));
		this.telefoneVal=Long.parseLong(M.get(4));
		this.statusVal=Boolean.parseBoolean(M.get(5));
		this.endereco=new Endereco(M.get(6), Integer.parseInt(M.get(7)), M.get(8),M.get(9),M.get(10),M.get(11));
	}
	public Fornecedor(){
		super();
	}
	public static JPanel[] getPanels(){
		JPanel backListPanel = new JPanel();
		
		JPanel frontListPanel = new JPanel();
		
		JPanel productList = new JPanel();
		ArrayList<ArrayList<String>> ps = Desktop.banco.lerArquivo("Fornecedor");

		productList.setLayout(new GridLayout((ps.size()+1),6,10,4));
		productList.add(new JLabel("Razao Social"));
		productList.add(new JLabel("Nome Fantasia"));
		productList.add(new JLabel("CNPJ"));
		productList.add(new JLabel("Telefone"));
		productList.add(new JLabel("Rua"));
		productList.add(new JLabel("Numero"));
		productList.add(new JLabel("Complemento"));
		productList.add(new JLabel("Bairro"));
		productList.add(new JLabel("Cidade"));
		productList.add(new JLabel("Estado"));
		productList.add(new JLabel("Status"));
		for(int x=0; x<ps.size(); x++){
			for(int y=0;y<6;y++){
				productList.add(new JLabel(ps.get(x).get(y)));
			}
		}
		backListPanel.add(frontListPanel);
		frontListPanel.add(productList);
		
		
		JPanel castPanel = new JPanel();
		
		JPanel FornecedorCadastro = new Fornecedor();
		castPanel.add(FornecedorCadastro);
		JPanel [] p = { backListPanel, castPanel };
		return p;
		
	}

	public static JButton[] getButtons(){
		JButton [] b = { new JButton("Listagem"), new JButton("Cadastro") };
		return b;
	}
}
