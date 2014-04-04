package sistema;
import java.util.ArrayList;
import java.util.Scanner;
public class Produto {
	public int produto_id;
	public String nome;
	public float valor, peso;
	public int quantidadeEstocada;
	public int quantidadeNaNota;
	public String grupo;
	
	public Produto(int produto_id,String nome, float valor, float peso, int quatidade,String grupo){
		this.produto_id=produto_id;
		this.nome=nome;
		this.valor=valor;
		this.peso=peso;
		this.quantidadeNaNota=quatidade;
		this.quantidadeEstocada=quatidade;
		this.grupo=grupo;
	}
	
	public Produto(ArrayList<String> M){
		// produto_id|nome|valor|peso|quatidadeEstocada|grupo
		
		this.produto_id=Integer.parseInt(M.get(0));
		this.nome=M.get(1);
		this.valor=Float.parseFloat(M.get(2));
		this.peso=Float.parseFloat(M.get(3));
		this.quantidadeNaNota=Integer.parseInt(M.get(4));
		this.quantidadeEstocada=Integer.parseInt(M.get(4));
		this.grupo=M.get(5);
	}
	public Produto(){
		Scanner input = new Scanner(System.in);
		
		BD b = new BD();
		
		this.produto_id = b.getLastID("Produto")+1;

		System.out.println("Formulario de Produto");
		System.out.print("\nNome: ");
		this.nome = input.nextLine();
		System.out.print("\nGrupo: ");
		this.grupo = input.nextLine();
		System.out.print("\nValor: ");
		this.valor= input.nextFloat();
		System.out.print("\nPeso: ");
		this.peso=input.nextFloat();
		System.out.print("\nQuantidade: ");
		int q = input.nextInt();
		this.quantidadeNaNota = q;
		this.quantidadeEstocada = q;
	}
	
	public void abaterEstoque(int quantidade){
		this.quantidadeEstocada -= quantidade;
		
		BD b = new BD();
		b.gravaProduto(this, true);
	}
	public void adicionarEstoque(int quantidade){
		this.quantidadeEstocada += quantidade;
		
		BD b = new BD();
		b.gravaProduto(this, true);
	}
}
