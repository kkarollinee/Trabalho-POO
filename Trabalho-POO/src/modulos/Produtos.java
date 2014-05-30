package modulos;

import grafico.Desktop;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import banco.BD;

import sistema.GrupoProduto;
import sistema.Produto;

public class Produtos {
	JTextField fieldNome = new JTextField(50);
	JTextField fieldValor = new JTextField(10);
	JTextField fieldPeso = new JTextField(5);
	JTextField fieldQuantidadeEstocada = new JTextField(5);
	JTextField fieldGrupo = new JTextField(20);
	
	public JPanel getFormCadastro(){
		JLabel nome = new JLabel("Nome do Produto: ");
		JLabel valor = new JLabel("Valor: ");
		JLabel peso = new JLabel("Peso: ");
		JLabel quantidadeEstocada = new JLabel("Quantidade em Estoque: ");
		JLabel grupo = new JLabel("Grupo do Produto: ");
		JPanel panelNome = new JPanel();
		JPanel panelValor = new JPanel();
		JPanel panelGrupo = new JPanel();
		
		JPanel cadastro = new JPanel();
		JPanel cadastroExt = new JPanel();
		JPanel cadastroWButtons = new JPanel();
		cadastroWButtons.setLayout(new BoxLayout(cadastroWButtons,BoxLayout.Y_AXIS));
		
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
		panelValor.add(quantidadeEstocada);
		panelValor.add(fieldQuantidadeEstocada);
		cadastro.add(panelValor);
		
		panelGrupo.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelGrupo.add(grupo);
		panelGrupo.add(fieldGrupo);
		cadastro.add(panelGrupo);
		
		cadastroWButtons.add(cadastro);
		
		//Botoes de cadastro
		JPanel actionButtons = new JPanel();
		
		JButton limpar = new JButton("Limpar");
		limpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		limpar.addActionListener(new ButtonHandlerLimpar());
		actionButtons.add(limpar);
		
		JButton cadastrar = new JButton("Cadastrar");
		cadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cadastrar.addActionListener(new ButtonHandlerCadastrar());
		actionButtons.add(cadastrar);
		
		cadastroWButtons.add(actionButtons);
		
		//adiciona botoes na tela
		cadastroExt.add(cadastroWButtons);
		
		//define tamanho da tela pra aparecer os scrollers
		cadastroExt.setPreferredSize(new Dimension(650,500));
		
		return cadastroExt;
	}
	
	private class ButtonHandlerCadastrar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				String nome = fieldNome.getText();
				float valor = Float.parseFloat(fieldValor.getText());
				float peso = Float.parseFloat(fieldPeso.getText());
				int quant = Integer.parseInt(fieldQuantidadeEstocada.getText());
				GrupoProduto grupo = new GrupoProduto(fieldGrupo.getText());
				
				if(nome.length() < 1)
					throw new NumberFormatException();
				if(valor < 0)
					throw new NumberFormatException();
				if(peso < 0)
					throw new NumberFormatException();
				if(quant < 0)
					throw new NumberFormatException();
				
				Produto p = new Produto(0, nome, valor, peso, quant, grupo);
				
				BD banco = new BD();
				
				banco.gravaProduto(p, false);
			}
			catch(NumberFormatException except){
				JOptionPane.showMessageDialog(null,"Dado inválido informado","Erro",0);
			}
			catch(Exception except){
				JOptionPane.showMessageDialog(null,"Ocorreu um erro ao processar requisição","Erro",0);
			}
		}
	}
	private class ButtonHandlerLimpar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			fieldNome.setText("");
			fieldValor.setText("");
			fieldPeso.setText("");
			fieldQuantidadeEstocada.setText("");
			fieldGrupo.setText("");
		}
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
		
		//sportColumn.setCellEditor(new DefaultCellEditor(comboBox));
		
		//tabela com os produtos
		JTable tabelaProdutos = new JTable(listaProdutos, productHeader);
		
		//configura coluna combobox de grupo de produtos
		String [] grupos = { "Snowboarding", "Rowing", "adeda", "123", "None" };
		JComboBox grupoProdutos = new JComboBox(grupos);
		TableColumn grupoColum = tabelaProdutos.getColumnModel().getColumn(5);
		grupoColum.setCellEditor(new DefaultCellEditor(grupoProdutos));
		
		//configuracoes da tabela
		tabelaProdutos.setAutoCreateRowSorter(true); //inicia sortter para ordenar os itens da tabela
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
				column.setPreferredWidth(380);
			}
			else if(i == 5){
				column.setPreferredWidth(155);
			}
			else{
				column.setPreferredWidth(70);
			}
		}
		
		//Scroller que vai aparecer caso tenham muitos produtos
		JScrollPane scroller = new JScrollPane(tabelaProdutos);
		scroller.setPreferredSize(new Dimension(800,300));
		
		principal.add(scroller);
		
		//define o tamanho minimo do panel grandao pra aparecer os scrollers
		principal.setPreferredSize(new Dimension(800,340));
		
		return principal;
	}
	
	public JPanel[] getPanels(){
		JPanel [] p = { getListagem(), getFormCadastro(), new JPanel() };
		return p;
		
	}

	public JButton[] getButtons(){
		JButton [] b = { new JButton("Listagem"), new JButton("Cadastro"), new JButton("Grupos") };
		return b;
	}
}
