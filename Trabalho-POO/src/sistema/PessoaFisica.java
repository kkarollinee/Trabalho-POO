package sistema;
import banco.BD;
import grafico.Desktop;

import java.awt.GridLayout;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
public abstract class PessoaFisica extends JPanel{
	public int pessoa_id;
	public String nome, rg;
	public long cpf, telefone;
	public boolean status;
	public Endereco endereco;
	
	
	public PessoaFisica(ArrayList<String> M){
		this.pessoa_id=Integer.parseInt(M.get(0));
		this.nome=M.get(1);
		this.rg = M.get(2);
		this.cpf=Long.parseLong(M.get(3));
		this.telefone=Long.parseLong(M.get(4));
		this.status=Boolean.parseBoolean(M.get(5));
		this.endereco=new Endereco(M.get(6), Integer.parseInt(M.get(7)), M.get(8),M.get(9),M.get(10),M.get(11));
		
	}

			public static JPanel[] getPanels(){
			JPanel backListPanel = new JPanel();
			
			JPanel frontListPanel = new JPanel();
			JPanel productList = new JPanel();
			ArrayList<ArrayList<String>> ps = Desktop.banco.lerArquivo("PessoaFisica");

			productList.setLayout(new GridLayout((ps.size()+1),6,10,4));
			productList.add(new JLabel("Nome"));
			productList.add(new JLabel("Identidade"));
			productList.add(new JLabel("CPF"));
			productList.add(new JLabel("Telefone"));
			productList.add(new JLabel("Status 0-Inativo 1-Ativo"));
			for(int x=0; x<ps.size(); x++){
				for(int y=0;y<6;y++){
					productList.add(new JLabel(ps.get(x).get(y)));
				}
			}
			backListPanel.add(frontListPanel);
			frontListPanel.add(productList);
			
			
			JPanel castPanel = new JPanel();
			
			JPanel PessoaFisica = new ClienteFisico();
			JPanel PessoaFisica2 = new Funcionario();
			castPanel.add(PessoaFisica);
			castPanel.add(PessoaFisica2);
			JPanel [] p = { backListPanel, castPanel };
			return p;
			
		}
		
	public PessoaFisica(){
	
	}
}

