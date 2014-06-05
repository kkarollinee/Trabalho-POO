package sistema;
import banco.BD;
import grafico.Desktop;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;


public abstract class NotaFiscal extends JPanel {
	public int nota_id;
	public int data;
	public Produto produtos;
	public ArrayList <Produto> arrayProduto = new ArrayList <Produto>();
	
	public NotaFiscal(String nomeArquivo){
		Scanner input = new Scanner(System.in);
		
		BD b = new BD();
		
		this.nota_id = b.getLastID(nomeArquivo)+1;
		
	}
	public static JPanel[] getPanels(){
		JPanel backListPanel = new JPanel();
		
		JPanel frontListPanel = new JPanel();
		JPanel productList = new JPanel();
		ArrayList<ArrayList<String>> ps = Desktop.banco.lerArquivo("NotaFiscal");

		productList.setLayout(new GridLayout((ps.size()+1),6,10,4));
		productList.add(new JLabel("Data"));
		backListPanel.add(frontListPanel);
		frontListPanel.add(productList);
		
		
		JPanel castPanel = new JPanel();
		
		JPanel NFSaidaFisico = new NFSaidaFisico();
		JPanel NFSaidaJuridico = new NFSaidaJuridico();
		JPanel NFEntrada = new NFEntrada();
		castPanel.add(NFSaidaFisico);
		castPanel.add(NFSaidaJuridico);
		castPanel.add(NFEntrada);
		JPanel [] p = { backListPanel, castPanel };
		return p;
		
		
	}
	public NotaFiscal(){
		
	}
	public double getValor(){
		return this.produtos.valor*this.produtos.quantidadeNaNota;
	}
}
