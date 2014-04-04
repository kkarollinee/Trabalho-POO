package sistema;
import java.util.ArrayList;
import java.util.Scanner;
public class NFSaidaJuridico extends NotaFiscal{
	public ClienteJuridico cliente;
	public NFSaidaJuridico(int nota_id, int data, ClienteJuridico cliente,Produto p){
		this.nota_id = nota_id;
		this.data = data;
		this.cliente = cliente;
		this.produtos = p;
	}

	public NFSaidaJuridico(ArrayList<String>n){
		BD b = new BD();
		
		//nota_id|data|clientejuridico_id
		this.nota_id = Integer.parseInt(n.get(0));
		this.data = Integer.parseInt(n.get(1));
		ArrayList<String> f = b.pesquisa(Integer.parseInt(n.get(2)), "ClienteJuridico");
		this.cliente = new ClienteJuridico(f);
		
		//NotaEntradaFisicoProduto
		ArrayList<String> p = b.pesquisa(this.nota_id, "NotaSaidaJuridicoProduto");
		this.produtos = new Produto(p);
	}

	public NFSaidaJuridico(){
		super("NotaSaidaJuridico");
		
		this.cliente = new ClienteJuridico();
		this.produtos = new Produto();
	}
	public NFSaidaJuridico(int fornecedorId, int produtoId){
		super("NotaSaidaJuridico");
		
		BD b = new BD();
		Scanner i = new Scanner(System.in);
		
		ArrayList<String> f = b.pesquisa(fornecedorId, "ClienteJuridico");
		if(f.size()==0){
			System.out.println("Cliente nao encontrado");
			return;
		}
		ArrayList<String> p = b.pesquisa(produtoId, "Produto");
		if(p.size()==0){
			System.out.println("Produto nao encontrado");
			return;
		}
		
		this.cliente = new ClienteJuridico(f);
		this.produtos = new Produto(p);
		System.out.print("\nQuantidade de produto: ");
		this.produtos.quantidadeNaNota = Integer.parseInt(i.nextLine());
		System.out.print("\nValor unitario: ");
		this.produtos.valor = Integer.parseInt(i.nextLine());
	}
}
