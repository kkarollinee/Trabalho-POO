package sistema;
import banco.BD;
import java.util.Scanner;


public abstract class NotaFiscal {
	public int nota_id;
	public int data;
	public Produto produtos;
	
	public NotaFiscal(String nomeArquivo){
		Scanner input = new Scanner(System.in);
		
		BD b = new BD();
		
		this.nota_id = b.getLastID(nomeArquivo)+1;
		
		System.out.print("\nData: ");
		this.data = input.nextInt();
	}
	public NotaFiscal(){
		
	}
	public double getValor(){
		return this.produtos.valor*this.produtos.quantidadeNaNota;
	}
}
