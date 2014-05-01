package sistema;
import banco.BD;
import java.util.ArrayList;
import java.util.Scanner;
public class NFEntrada extends NotaFiscal{
	public Fornecedor fornecedor;
	public NFEntrada(int nota_id, int data, Fornecedor cliente,Produto p){
		this.nota_id = nota_id;
		this.data = data;
		this.fornecedor = cliente;
		this.produtos = p;
	}
	public NFEntrada(ArrayList<String>n){
		BD b = new BD();
		
		//nota_id|data|fornecedorfisico_id
		this.nota_id = Integer.parseInt(n.get(0));
		this.data = Integer.parseInt(n.get(1));
		ArrayList<String> f = b.pesquisa(Integer.parseInt(n.get(2)), "FornecedorJuridico");
		this.fornecedor = new Fornecedor(f);
		
		//NotaEntradaFisicoProduto
		ArrayList<String> p = b.pesquisa(this.nota_id, "NotaEntradaJuridicoProduto");
		this.produtos = new Produto(p);
	}
	public NFEntrada(){
		super("NotaEntradaJuridico");
		
		this.fornecedor = new Fornecedor();
		this.produtos = new Produto();
	}
	public NFEntrada(int fornecedorId, int produtoId){
		super("NotaEntradaJuridico");
		
		BD b = new BD();
		Scanner i = new Scanner(System.in);
		
		ArrayList<String> f = b.pesquisa(fornecedorId, "FornecedorJuridico");
		if(f.size()==0){
			System.out.println("Fornecedor nao encontrado");
			return;
		}
		ArrayList<String> p = b.pesquisa(produtoId, "Produto");
		if(p.size()==0){
			System.out.println("Produto nao encontrado");
			return;
		}
		
		this.fornecedor = new Fornecedor(f);
		this.produtos = new Produto(p);
		System.out.print("\nQuantidade de produto: ");
		this.produtos.quantidadeNaNota = Integer.parseInt(i.nextLine());
		System.out.print("\nValor unitario: ");
		this.produtos.valor = Integer.parseInt(i.nextLine());
	}
}
