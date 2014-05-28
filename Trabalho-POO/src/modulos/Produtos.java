package modulos;

import grafico.Desktop;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

public class Produtos {
	public JPanel getFormCadastro(){
		JLabel nome = new JLabel("Nome do Produto: ");
		JLabel valor = new JLabel("Valor: ");
		JLabel peso = new JLabel("Peso: ");
		JLabel quantidadeEstocada = new JLabel("Quantidade em Estoque");
		JLabel grupo = new JLabel("Grupo do Produto");
		JTextField fieldNome = new JTextField(50);
		JTextField fieldValor = new JTextField(10);
		JTextField fieldPeso = new JTextField(5);
		JTextField fieldQuantidadeEstocada = new JTextField(5);
		JTextField fieldGrupo = new JTextField(20);
		JPanel panelNome = new JPanel();
		JPanel panelValor = new JPanel();
		JPanel panelGrupo = new JPanel();
		
		JPanel cadastro = new JPanel();
		JPanel cadastroExt = new JPanel();
		
		cadastro.setLayout(new BoxLayout(cadastro,BoxLayout.Y_AXIS));
		cadastro.setSize(800,150);
		panelNome.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelNome.add(nome);
		panelNome.add(fieldNome);
		cadastro.add(panelNome);
		panelValor.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelValor.add(valor);
		panelValor.add(fieldValor);
		panelValor.add(peso);
		panelValor.add(fieldPeso);
		cadastro.add(panelValor);
		panelGrupo.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelGrupo.add(quantidadeEstocada);
		panelGrupo.add(fieldQuantidadeEstocada);
		panelGrupo.add(grupo);
		panelGrupo.add(fieldGrupo);
		cadastro.add(panelGrupo);
		cadastroExt.add(cadastro);
		
		//define tamanho da tela pra aparecer os scrollers
		cadastroExt.setPreferredSize(new Dimension(650,500));
		
		return cadastroExt;
	}
	public JPanel getListagem(){
		//Panel grandao com as barras de rolagem que se ajustam ao redimensionar a janela
		JPanel principal = new JPanel();
		
		//Cabecalho da listagem de produtos
		String[] productHeader = {
			"Codigo",
			"Nome",
			"Valor",
			"Peso",
			"Qnt. Estocada",
			"Grupo"
		};
		
		//listagem de produtos
		ArrayList<ArrayList<String>> ps = Desktop.banco.lerArquivo("Produto");
		
		Object[][] listaProdutos = new Object[ps.size()][6];
		
		for(int x=0; x<ps.size(); x++){
			for(int y=0;y<6;y++){
				listaProdutos[x][y] = ps.get(x).get(y);
			}
		}
		
		//tabela com os produtos
		JTable tabelaProdutos = new JTable(listaProdutos, productHeader);
		tabelaProdutos.setFillsViewportHeight(true); //tamanho ajustavel com a tela
		tabelaProdutos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //desabilita redimensionamento automatico
		tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //limita selecao de apenas 1 linha por vez
		
		//define tamanhos das colunas da tabela
		TableColumn column = null;
		for (int i = 0; i < 6; i++) {
			column = tabelaProdutos.getColumnModel().getColumn(i);
			if(i == 0){
				column.setPreferredWidth(50);
			}
			else if (i == 1){
				column.setPreferredWidth(250);
			}
			else if(i == 5){
				column.setPreferredWidth(135);
			}
			else{
				column.setPreferredWidth(70);
			}
		}
		
		//Scroller que vai aparecer caso tenham muitos produtos
		JScrollPane scroller = new JScrollPane(tabelaProdutos);
		scroller.setPreferredSize(new Dimension(650,200));
		
		principal.add(scroller);
		
		//define o tamanho minimo do panel grandao pra aparecer os scrollers
		principal.setPreferredSize(new Dimension(650,240));
		
		return principal;
	}
	
	public JPanel[] getPanels(){
		JPanel [] p = { getListagem(), getFormCadastro() };
		return p;
		
	}

	public JButton[] getButtons(){
		JButton [] b = { new JButton("Listagem"), new JButton("Cadastro") };
		return b;
	}
}
