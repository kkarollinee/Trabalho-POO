package sistema;
import banco.BD;
import java.util.*;
public abstract class PessoaFisica {
	public int pessoa_id;
	public String nome, rg;
	public long cpf, telefone;
	public boolean status;
	public Endereco endereco;
	
	public PessoaFisica(String nomeArquivo){
		BD b = new BD();
		this.pessoa_id = b.getLastID(nomeArquivo)+1;
		Scanner dadosPessoaFisica = new Scanner(System.in);
		System.out.println("Este Formulario destina-se ao cadastro de uma Pessoa Fisica\n" +
				"Digite as informações solicitadas:");
		System.out.print("\nNome: ");
		this.nome = dadosPessoaFisica.nextLine();
		System.out.print("\nIdentidade: ");
		this.rg = dadosPessoaFisica.nextLine();
		System.out.print("\nCPF: ");
		this.cpf = dadosPessoaFisica.nextLong();
		System.out.print("\nTelefone: ");
		this.telefone = dadosPessoaFisica.nextLong();
		System.out.print("\nStatus 0-Inativo 1-Ativo: ");
		this.status = (dadosPessoaFisica.nextInt()==1)?true:false;
	
		this.endereco = new Endereco();
	}
	public PessoaFisica(){
		
	}
}

