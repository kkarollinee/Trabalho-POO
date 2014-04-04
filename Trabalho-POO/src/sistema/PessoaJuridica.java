package sistema;
import java.util.*;
public abstract class PessoaJuridica {
	public int pessoa_id;
	public String razaoSocial;
	public String nomeFantasia;
	public long cnpj;
	public long telefone;
	public boolean status;
	public Endereco endereco;
	
	public PessoaJuridica(String nomeArquivo){	
		BD b = new BD();
		this.pessoa_id = b.getLastID(nomeArquivo)+1;
		
		Scanner dadosEmpresa = new Scanner(System.in);
		System.out.println("Este Formulario destina-se ao cadastro de uma Empresa\n" +
				"Digite as informacoes solicitadas:");
		System.out.print("\nRazao Social: ");
		this.razaoSocial = dadosEmpresa.nextLine();
		System.out.print("\nNome Fantasia: ");
		this.nomeFantasia = dadosEmpresa.nextLine();
		System.out.print("\nCNPJ: ");
		this.cnpj = dadosEmpresa.nextLong();
		System.out.print("\nTelefone: ");
		this.telefone = dadosEmpresa.nextLong();
		System.out.print("\nStatus 0-Inativo 1-Ativo: ");
		this.status = (dadosEmpresa.nextInt()==1)?true:false;

		this.endereco = new Endereco();
	}
	public PessoaJuridica(){
		
	}
}
