package sistema;
import java.util.ArrayList;
import java.util.Scanner;


public class NFSaidaFisico extends NotaFiscal{
	public ClienteFisico cliente;
	
	public NFSaidaFisico(int nota_id, int data, ClienteFisico cliente,Produto p){
		this.nota_id = nota_id;
		this.data = data;
		this.cliente = cliente;
		this.produtos = p;
	}
	public NFSaidaFisico(ArrayList<String>n){
		BD b = new BD();
		
		//nota_id|data|clientejuridico_id
		this.nota_id = Integer.parseInt(n.get(0));
		this.data = Integer.parseInt(n.get(1));
		ArrayList<String> f = b.pesquisa(Integer.parseInt(n.get(2)), "ClienteFisico");
		this.cliente = new ClienteFisico(f);
		
		//NotaEntradaFisicoProduto
		ArrayList<String> p = b.pesquisa(this.nota_id, "NotaSaidaFisicoProduto");
		this.produtos = new Produto(p);
	}

	public NFSaidaFisico(){
		super("NotaSaidaFisico");
		
		this.cliente = new ClienteFisico();
		this.produtos = new Produto();
	}
	public NFSaidaFisico(int fornecedorId, int produtoId){
		super("NotaSaidaFisico");
		
		BD b = new BD();
		Scanner i = new Scanner(System.in);
		
		ArrayList<String> f = b.pesquisa(fornecedorId, "ClienteFisico");
		if(f.size()==0){
			System.out.println("Cliente nao encontrado");
			return;
		}
		ArrayList<String> p = b.pesquisa(produtoId, "Produto");
		if(p.size()==0){
			System.out.println("Produto nao encontrado");
			return;
		}
		
		this.cliente = new ClienteFisico(f);
		this.produtos = new Produto(p);
		System.out.print("\nQuantidade de produto: ");
		this.produtos.quantidadeNaNota = Integer.parseInt(i.nextLine());
		System.out.print("\nValor unitario: ");
		this.produtos.valor = Integer.parseInt(i.nextLine());
	}
}
