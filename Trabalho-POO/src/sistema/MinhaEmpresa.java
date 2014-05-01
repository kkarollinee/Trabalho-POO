package sistema;
import banco.BD;
import java.util.ArrayList;
import banco.BD;
import java.util.Scanner;

public class MinhaEmpresa extends PessoaJuridica {
	public double saldo;
	public MinhaEmpresa (int pessoa_id, String razao, String fantasia, long cnpj, long tel, boolean status, Endereco endereco, double saldo){
		//pessoa_id|razaoSocial|nomeFantasia|cpnj|telefone|status|rua|numero|complemento|bairro|cidade|estado|saldo
		this.pessoa_id=pessoa_id;
		this.razaoSocialVal=razao;
		this.nomeFantasiaVal=fantasia;
		this.cnpjVal=cnpj;
		this.telefoneVal=tel;
		this.statusVal=status;
		this.endereco=endereco;
		this.saldo=saldo;
	}
	public MinhaEmpresa (ArrayList<String> M){
		//pessoa_id|razaoSocial|nomeFantasia|cpnj|telefone|status|rua|numero|complemento|bairro|cidade|estado|saldo
		this.pessoa_id=Integer.parseInt(M.get(0));
		this.razaoSocialVal=M.get(1);
		this.nomeFantasiaVal=M.get(2);
		this.cnpjVal=Long.parseLong(M.get(3));
		this.telefoneVal=Long.parseLong(M.get(4));
		this.statusVal=(M.get(5).equals("1"))?true:false;
		this.endereco=new Endereco(M.get(6), Integer.parseInt(M.get(7)), M.get(8),M.get(9),M.get(10),M.get(11));
		this.saldo=Double.parseDouble(M.get(12));
	}
	public MinhaEmpresa(){
		super();
		
		System.out.print("\nSaldo: ");
		Scanner input = new Scanner(System.in);
		this.saldo = input.nextFloat();
	}
	
	public void comprar(double valor){
		this.saldo -= valor;
		
		BD b = new BD();
		b.gravaEmpresa(this, true);
	}
	public void vender(double valor){
		this.saldo += valor;
		
		BD b = new BD();
		b.gravaEmpresa(this, true);
	}
}

