package sistema;
import java.util.Scanner;

public class Endereco {
	public String rua;
	public int numero;
	public String complemento;
	public String bairro;
	public String cidade;
	public String estado;
	
	public Endereco (String rua,int numero, String complemento, String bairro, String cidade, String estado){
		this.rua=rua;
		this.numero=numero;
		this.complemento=complemento;
		this.bairro=bairro;
		this.cidade=cidade;
		this.estado=estado;
	}
	public Endereco(){
		Scanner dadosPessoaFisica = new Scanner(System.in);
		System.out.print("\nRua: ");
		this.rua = dadosPessoaFisica.nextLine();
		System.out.print("\nComplemento: ");
		this.complemento = dadosPessoaFisica.nextLine();
		System.out.print("\nBairro: ");
		this.bairro = dadosPessoaFisica.nextLine();
		System.out.print("\nCidade: ");
		this.cidade = dadosPessoaFisica.nextLine();
		System.out.print("\nEstado: ");
		this.estado = dadosPessoaFisica.nextLine();
		System.out.print("\nNumero: ");
		this.numero = dadosPessoaFisica.nextInt();
	}
}
