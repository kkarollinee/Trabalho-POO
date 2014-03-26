import java.util.ArrayList;
import java.util.Scanner;
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
		super("Funcionario");
		
		Scanner dadosFuncionario = new Scanner(System.in);
		
		System.out.println("Este Formulario destina-se ao cadastro de um Funcionario\n" +
				"Digite as informações solicitadas:");
		System.out.print("\nCargo: ");
		this.cargo = dadosFuncionario.nextLine();
		System.out.print("\nHorario de Entrada: ");
		this.horarioEntrada = dadosFuncionario.nextInt();
		System.out.print("\nHorario de Pausa: ");
		this.horarioPausa = dadosFuncionario.nextInt();
		System.out.print("\nHorario de Retorno: ");
		this.horarioRetorno = dadosFuncionario.nextInt();
		System.out.print("\nHorario de Saida: ");
		this.horarioSaida = dadosFuncionario.nextInt();
		System.out.print("\nSalario: ");
		this.salario = dadosFuncionario.nextDouble();
	}
}
