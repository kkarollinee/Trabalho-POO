package sistema;
import java.util.ArrayList;
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
		super("ClienteFisico");
	}
}
