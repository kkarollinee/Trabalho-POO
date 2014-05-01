package sistema;
import grafico.Desktop;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	public static JPanel[] getPanels(){
		JPanel backListPanel = new JPanel();
		
		JPanel frontListPanel = new JPanel();
		
		JPanel productList = new JPanel();
		ArrayList<ArrayList<String>> ps = Desktop.banco.lerArquivo("Produto");

		productList.setLayout(new GridLayout((ps.size()+1),6,10,4));
		productList.add(new JLabel("Codigo"));
		productList.add(new JLabel("Nome"));
		productList.add(new JLabel("Valor"));
		productList.add(new JLabel("Peso"));
		productList.add(new JLabel("Quant. Estocada"));
		productList.add(new JLabel("Grupo"));
		for(int x=0; x<ps.size(); x++){
			for(int y=0;y<6;y++){
				productList.add(new JLabel(ps.get(x).get(y)));
			}
		}
		backListPanel.add(frontListPanel);
		frontListPanel.add(productList);
		JPanel [] p = { backListPanel, new JPanel() };
		return p;
		
	}

	public static JButton[] getButtons(){
		JButton [] b = { new JButton("Listagem"), new JButton("Cadastro") };
		return b;
	}
}
