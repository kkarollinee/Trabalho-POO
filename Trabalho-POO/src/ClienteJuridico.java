import java.util.ArrayList;
public class ClienteJuridico extends PessoaJuridica{
	public ClienteJuridico (int pessoa_id, String razao, String fantasia, long cnpj, long tel, boolean status, Endereco endereco){
		this.pessoa_id=pessoa_id;
		this.razaoSocial=razao;
		this.nomeFantasia=fantasia;
		this.cnpj=cnpj;
		this.telefone=tel;
		this.status=status;
		this.endereco=endereco;
	}
	public ClienteJuridico(ArrayList<String> M){
		//pessoa_id|razaoSocial|nomeFantasia|cnpj|telefone|status|rua|numero|complemento|bairro|cidade|estado
		
		this.pessoa_id=Integer.parseInt(M.get(0));
		this.razaoSocial=M.get(1);
		this.nomeFantasia=M.get(2);
		this.cnpj=Long.parseLong(M.get(3));
		this.telefone=Long.parseLong(M.get(4));
		this.status=Boolean.parseBoolean(M.get(5));
		this.endereco=new Endereco(M.get(6), Integer.parseInt(M.get(7)), M.get(8),M.get(9),M.get(10),M.get(11));
	}
	public ClienteJuridico(){
		super("ClienteJuridico");
	}
}
