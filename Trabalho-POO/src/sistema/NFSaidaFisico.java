package sistema;
import banco.BD;
import grafico.Desktop;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


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
		
	
	}
	
	public static JPanel[] getPanels(){
		JPanel backListPanel = new JPanel();
		
		JPanel frontListPanel = new JPanel();
		
		JPanel productList = new JPanel();
		ArrayList<ArrayList<String>> ps = Desktop.banco.lerArquivo("NotaSaidaFisico");

		productList.setLayout(new GridLayout((ps.size()+1),6,10,4));
		productList.add(new JLabel("Quantidade de produto"));
		productList.add(new JLabel("Valor unitario"));
		for(int x=0; x<ps.size(); x++){
			for(int y=0;y<6;y++){
				productList.add(new JLabel(ps.get(x).get(y)));
			}
		}
		backListPanel.add(frontListPanel);
		frontListPanel.add(productList);
		
		
		JPanel castPanel = new JPanel();
		
		JPanel NFSaidaFisico = new NFSaidaFisico();
		castPanel.add(NFSaidaFisico);
		JPanel [] p = { backListPanel, castPanel };
		return p;
		
	}

	public static JButton[] getButtons(){
		JButton [] b = { new JButton("Listagem"), new JButton("Cadastro") };
		return b;
	}
}
