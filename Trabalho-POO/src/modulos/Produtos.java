package modulos;

import grafico.Desktop;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
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
	
	//Cabecalho da listagem de produtos
	final String[] productHeader = {
		"Codigo",
		"Nome",
		"Valor",
		"Peso",
		"Qnt. Estocada",
		"Grupo"
	};
	
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
			catch(SQLException except){
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
	
	private class TabelModelProdutos extends DefaultTableModel{
		private boolean[] canEdit = new boolean []{ false, true, true, true, false, true };
		
		public TabelModelProdutos(Object[][] tabela, Object[] headers) {
			super(tabela,headers);
		}
		
		public boolean isCellEditable(int rowIndex, int mColIndex) {  
			return canEdit[mColIndex];  
		}
	}
	private void iniciaTabelaProdutos(final JTable tabela, final ArrayList<Produto> editados, final ArrayList<Integer> editadosList){
		try {
			BD banco = new BD();
			//listagem de produtos
			ResultSet ps = banco.getProdutos();
	
			ps.last();
			int size = ps.getRow();
			ps.beforeFirst();
		    
			final Object[][] listaProdutos = new Object[size][6];
			while(ps.next()){
				for(int y=0;y<6;y++){
					listaProdutos[ps.getRow()-1][y] = ps.getString(y+1);
				}
			}
			
			tabela.setModel(new TabelModelProdutos(listaProdutos,productHeader));
			
			//configura coluna combobox de grupo de produtos
			String [] grupos = { "Snowboarding", "Rowing", "adeda", "123", "None" };
			JComboBox grupoProdutos = new JComboBox(grupos);
			TableColumn grupoColum = tabela.getColumnModel().getColumn(5);
			grupoColum.setCellEditor(new DefaultCellEditor(grupoProdutos));
			
			//configuracoes da tabela
			tabela.setAutoCreateRowSorter(true); //inicia sortter para ordenar os itens da tabela
			tabela.setFillsViewportHeight(true); //tamanho ajustavel com a tela
			tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //desabilita redimensionamento automatico
			tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //limita selecao de apenas 1 linha por vez
			
			//define tamanhos das colunas da tabela
			TableColumn column = null;
			for (int i = 0; i < 6; i++) {
				column = tabela.getColumnModel().getColumn(i);
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
			
			//ouvinte de edição
			tabela.getModel().addTableModelListener(new TableModelListener() {
				public void tableChanged(TableModelEvent e) {
					int linha = e.getFirstRow();
					try{
						switch(e.getType()){  
							case TableModelEvent.UPDATE:
								int produto_id = Integer.parseInt((String) tabela.getValueAt(linha,0));
								String nome = (String) tabela.getValueAt(linha,1);
								float valor = Float.parseFloat((String)tabela.getValueAt(linha,2));
								float peso = Float.parseFloat((String)tabela.getValueAt(linha,3));
								int quatidade = Integer.parseInt((String)tabela.getValueAt(linha,4));
								GrupoProduto grupo = new GrupoProduto((String) tabela.getValueAt(linha,5));
								
								Produto p = new Produto(produto_id, nome, valor, peso, quatidade, grupo);
								
								if(editadosList.contains(produto_id)){
									int i = editadosList.indexOf(produto_id);
									
									editados.set(i, p);
								}
								else{
									editadosList.add(produto_id);
									editados.add(p);
								}
								break;  
						}  
					} 
					catch(Exception except){
						JOptionPane.showMessageDialog(null,"Dado inválido informado","Erro",0);
					}
				}
			});

		} catch (Exception e) {
			
		}
	}
	public JPanel getListagem(){
		//Panel grandao com as barras de rolagem que se ajustam ao redimensionar a janela
		JPanel principal = new JPanel();
		
		try{
			//tabela com os produtos
			final JTable tabelaProdutos = new JTable();
			
			//Variavel que armazena os produtos que foram editados
			final ArrayList<Produto> editados = new ArrayList<Produto>();
			final ArrayList<Integer> editadosList = new ArrayList<Integer>();
			
			//modelo da tabela
			iniciaTabelaProdutos(tabelaProdutos,editados,editadosList);
			
			//Scroller que vai aparecer caso tenham muitos produtos
			JScrollPane scroller = new JScrollPane(tabelaProdutos);
			scroller.setPreferredSize(new Dimension(800,300));
			
			principal.add(scroller);
			
			//botao de edicao
			JButton editar = new JButton("Salvar Modificações");
			editar.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try{
						BD banco = new BD();
						
						for(int x = 0; x < editados.size(); x++)
							banco.gravaProduto(editados.get(x), true);
					}
					catch(SQLException except){
						JOptionPane.showMessageDialog(null,"Ocorreu um erro ao processar requisição","Erro",0);
					}
				}
			});
			principal.add(editar);
	
			//botao de desfazer
			JButton desfazer = new JButton("Desfazer Modificações");
			desfazer.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try{
						editados.clear();
						editadosList.clear();
						iniciaTabelaProdutos(tabelaProdutos,editados,editadosList);
					}
					catch(Exception except){
						JOptionPane.showMessageDialog(null,"Ocorreu um erro ao processar requisição","Erro",0);
					}
				}
			});
			principal.add(desfazer);
			
			//define o tamanho minimo do panel grandao pra aparecer os scrollers
			principal.setPreferredSize(new Dimension(800,340));

		}
		catch(Exception e){
			System.out.println(e);
		}
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
