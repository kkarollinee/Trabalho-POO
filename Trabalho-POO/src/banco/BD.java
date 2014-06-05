package banco;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException; //imports necessarios para mysql
import java.sql.Statement;

import java.io.*;
import java.util.ArrayList;
import sistema.*;

public class BD {
	String serverName ="localhost";
	String mydatabase = "softy";
	String url ="jdbc:mysql://"+ serverName + "/" + mydatabase;
	String username= "softy";
	String password ="123456";
	
	Connection connection = null;
	
	public static void main (String [] args){
		BD teste = new BD();
		
		Produto p = new Produto(3,"produtoeditado", 52, 52, 23, null);
		
		try {
			ResultSet t = teste.gravaProduto(p,true);
			t.next();
			System.out.println(t.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public BD(){
		try{
			String driverName= "com.mysql.jdbc.Driver";
			Class.forName(driverName);
			connection = DriverManager.getConnection(url,username,password);
			
//			 Statement state = connection.createStatement();
//			ResultSet result = state.executeQuery("SELECT * FROM produto");
//			while(result.next()){
//				System.out.println(result.getString("nome"));
//			}
		}
		catch (SQLException e){
			System.out.println("Nao foi possivel conectar ao Banco de Dados");
		}
		catch (ClassNotFoundException e){
			System.out.println("O driver expecificado nao foi encontrado.");
		}
		
	}
	public ResultSet getProdutos() throws SQLException{
		Statement s = connection.createStatement();
		s.executeQuery(
			"SELECT * FROM tb_produto"
		);
		return s.getResultSet();
	}
	public ResultSet gravaProduto(Produto p,boolean editar) throws SQLException{
		String controleGrupo = (p.grupo != null? "'"+p.grupo.grupoID+"'":"NULL");
		
		Statement s = connection.createStatement();
		
		if (editar){
			s.executeUpdate(
				"UPDATE tb_produto " +
				"SET nome='"+p.nome+"', valor='"+p.valor+"', peso='"+p.peso+"', " +
				"quantidadeEstocada= '"+p.quantidadeEstocada+"', codGrupoProduto="+controleGrupo+" " +
				"WHERE codProduto='"+p.produto_id+"' ",
				Statement.RETURN_GENERATED_KEYS
			);
		}
		else {
			s.executeUpdate(
				"INSERT INTO tb_produto " +
				"(nome, valor, peso, quantidadeEstocada, codGrupoProduto) "+
				"VALUES ('"+p.nome+"', '"+p.valor+"', '"+p.peso+"', '"+p.quantidadeEstocada+"', "+controleGrupo+")",
				Statement.RETURN_GENERATED_KEYS
			);
		}
		return s.getGeneratedKeys();
	}
	
	public ResultSet gravaNotaSaida(NotaFiscal n, int tipoCliente) throws SQLException{
		Statement s = connection.createStatement();
		
		if(tipoCliente==0){ //0-fis 1-jur
			NFSaidaFisico nf = (NFSaidaFisico)n;
			s.executeUpdate(
				"INSERT INTO tb_nfsaida " +
				"(tipoCliente, data, valorNota, codFuncionario, codClienteFisico, codClienteJuridico) "+
				"VALUES ("+tipoCliente+", '"+nf.data+"', '"+nf.funcionario.pessoa_id+"', '"+nf.cliente.pessoa_id+"', NULL)",
				Statement.RETURN_GENERATED_KEYS
			);
			
		}
		else{
			NFSaidaJuridico nf = (NFSaidaJuridico)n;
			s.executeUpdate(
				"INSERT INTO tb_nfsaida " +
				"(tipoCliente, data, valorNota, codFuncionario, codClienteFisico, codClienteJuridico) "+
				"VALUES ("+tipoCliente+", '"+nf.data+"', '"+nf.funcionario.pessoa_id+"', NULL, '"+nf.cliente.pessoa_id+"')",
				Statement.RETURN_GENERATED_KEYS
			);
		}
		ResultSet t = s.getGeneratedKeys();
		t.next();
		int codNota=t.getInt(1);
		String query ="INSERT INTO tb_nfsaidaproduto (codNotaSaida, codProduto, quantidadeProduto, valorProduto) VALUES ";
		
		for(int x=0; x < n.arrayProduto.size(); x++){
			query += "("+codNota+", "+n.arrayProduto.get(x).produto_id+", "+n.arrayProduto.get(x).quantidadeNaNota+", "+n.arrayProduto.get(x).valor+")";
			if(x < n.arrayProduto.size()-1){
				query += ",";
			}
		}
		s.executeUpdate(query);
		
		return s.getGeneratedKeys();
	}
	
	
	//nota fiscal cliente fisico
	public void gravaNotaFiscalClienteFis(NFSaidaFisico nf, boolean editar){
		//nota_id|data|clienteFisico_id
		String registro = nf.nota_id+"|"+nf.data+"|"+nf.cliente.pessoa_id;
		if (editar){
			this.editaArquivo("NotaSaidaFisico", registro);
		}
		else
			this.gravaArquivo("NotaSaidaFisico", registro);
		
		this.gravaNotaFiscalClienteFisProduto(nf.nota_id,nf.produtos, editar);
	}
	public void gravaNotaFiscalClienteFisProduto(int nota_id, Produto p, boolean editar){
		//nota_id|produto_id|quantidade|precoUnitario
		String registro = nota_id+"|"+p.produto_id+"|"+p.quantidadeNaNota+"|"+p.valor;
		if (editar){
			this.editaArquivo("NotaSaidaFisicoProduto", registro);
		}
		else
			this.gravaArquivo("NotaSaidaFisicoProduto", registro);
	}
	
	//nota fiscal cliente juridico
	public void gravaNotaFiscalClienteJur(NFSaidaJuridico nf, boolean editar){
		//nota_id|data|clientejuridico_id
		String registro = nf.nota_id+"|"+nf.data+"|"+nf.cliente.pessoa_id;
		if (editar){
			this.editaArquivo("NotaSaidaJuridico", registro);
		}
		else
			this.gravaArquivo("NotaSaidaJuridico", registro);
		
		this.gravaNotaFiscalClienteJurProduto(nf.nota_id,nf.produtos,editar);
	}
	public void gravaNotaFiscalClienteJurProduto(int nota_id, Produto p,boolean editar){
		//nota_id|produto_id|quantidade|precoUnitario
		String registro = nota_id+"|"+p.produto_id+"|"+p.quantidadeNaNota+"|"+p.valor;
		if (editar){
			this.editaArquivo("NotaSaidaJuridicoProduto", registro);
		}
		else
			this.gravaArquivo("NotaSaidaJuridicoProduto", registro);
	}
	
	//nota fiscal fornecedor juridico
	public void gravaNotaFiscalFornecedorJur(NFEntrada nf,boolean editar){
		//nota_id|data|fornecedorjuridico_id
		String registro = nf.nota_id+"|"+nf.data+"|"+nf.fornecedor.pessoa_id;
		if (editar){
			this.editaArquivo("NotaEntradaJuridico", registro);
		}
		else
			this.gravaArquivo("NotaEntradaJuridico", registro);
		
		this.gravaNotaFiscalFornecedorJurProduto(nf.nota_id,nf.produtos,editar);
	}
	public void gravaNotaFiscalFornecedorJurProduto(int nota_id, Produto p,boolean editar){
		//nota_id|produto_id|quantidade|precoUnitario
		String registro = nota_id+"|"+p.produto_id+"|"+p.quantidadeNaNota+"|"+p.valor;
		if (editar){
			this.editaArquivo("NotaEntradaJuridicoProduto", registro);
		}
		else
			this.gravaArquivo("NotaEntradaJuridicoProduto", registro);
	}
	
	public void gravaFornecedorJur(Fornecedor f,boolean editar){
		//pessoa_id|razaoSocial|nomeFantasia|cnpj|telefone|status|rua|numero|complemento|bairro|cidade|estado
		String registro = f.pessoa_id+"|"+f.razaoSocialVal+"|"+f.nomeFantasiaVal+"|"+f.cnpjVal+"|"+f.telefoneVal+"|"+f.statusVal+"|"+
		f.endereco.ruaVal+"|"+f.endereco.numeroVal+"|"+f.endereco.complementoVal+"|"+f.endereco.bairroVal+"|"+
		f.endereco.cidadeVal+"|"+f.endereco.estadoVal;
		
		if (editar){
			this.editaArquivo("FornecedorJuridico", registro);
		}
		else
			this.gravaArquivo("FornecedorJuridico", registro);
	}
	public void gravaClienteFis(ClienteFisico f,boolean editar){
		//pessoa_id|nome|rg|cpf|telefone|status|rua|numero|complemento|bairro|cidade|estado
		String registro = f.pessoa_id+"|"+f.nome+"|"+f.rg+"|"+f.cpf+"|"+f.telefone+"|"+f.status+"|"+
		f.endereco.ruaVal+"|"+f.endereco.numeroVal+"|"+f.endereco.complementoVal+"|"+f.endereco.bairroVal+"|"+
		f.endereco.cidadeVal+"|"+f.endereco.estadoVal;
		
		if (editar){
			this.editaArquivo("ClienteFisico", registro);
		}
		else
			this.gravaArquivo("ClienteFisico", registro);
	}
	public void gravaClienteJur(ClienteJuridico f,boolean editar){
		//pessoa_id|razaoSocial|nomeFantasia|cnpj|telefone|status|rua|numero|complemento|bairro|cidade|estado
		String registro = f.pessoa_id+"|"+f.razaoSocialVal+"|"+f.nomeFantasiaVal+"|"+f.cnpjVal+"|"+f.telefoneVal+"|"+f.statusVal+"|"+
		f.endereco.ruaVal+"|"+f.endereco.numeroVal+"|"+f.endereco.complementoVal+"|"+f.endereco.bairroVal+"|"+
		f.endereco.cidadeVal+"|"+f.endereco.estadoVal;
		
		if (editar){
			this.editaArquivo("ClienteJuridico", registro);
		}
		else
			this.gravaArquivo("ClienteJuridico", registro);
	}
	public void gravaEmpresa(MinhaEmpresa f,boolean editar){
		//pessoa_id|razaoSocial|nomeFantasia|cnpj|telefone|status|rua|numero|complemento|bairro|cidade|estado|saldo
		String registro = f.pessoa_id+"|"+f.razaoSocialVal+"|"+f.nomeFantasiaVal+"|"+f.cnpjVal+"|"+f.telefoneVal+"|"+f.statusVal+"|"+
		f.endereco.ruaVal+"|"+f.endereco.numeroVal+"|"+f.endereco.complementoVal+"|"+f.endereco.bairroVal+"|"+
		f.endereco.cidadeVal+"|"+f.endereco.estadoVal+"|"+f.saldo;
		
		if (editar){
			this.editaArquivo("MinhaEmpresa", registro);
		}
		else
			this.gravaArquivo("MinhaEmpresa", registro);
	}
	public void gravaFuncionario(Funcionario f,boolean editar){
		//pessoa_id|nome|rg|cpf|telefone|status|rua|numero|complemento|bairro|cidade|estado|cargo|horarioEntrada|
		//horarioPausa|horarioRetorno|horarioSaida|salario
		String registro = f.pessoa_id+"|"+f.nome+"|"+f.rg+"|"+f.cpf+"|"+f.telefone+"|"+f.status+"|"+
		f.endereco.ruaVal+"|"+f.endereco.numeroVal+"|"+f.endereco.complementoVal+"|"+f.endereco.bairroVal+"|"+
		f.endereco.cidadeVal+"|"+f.endereco.estadoVal+"|"+f.cargo+"|"+f.horarioEntrada+"|"+
		f.horarioPausa+"|"+f.horarioRetorno+"|"+f.horarioSaida+"|"+f.salario;
		
		if (editar){
			this.editaArquivo("Funcionario", registro);
		}
		else
			this.gravaArquivo("Funcionario", registro);
	}
	public boolean gravaArquivo(String arquivo, String dado){
		try{
			FileWriter arq = new FileWriter(arquivo+".txt",true);
			BufferedWriter escrever = new BufferedWriter(arq);
			
			escrever.write(dado);
			escrever.newLine();
			
			escrever.close();
			arq.close();
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		return true;
	}
	
	public ArrayList<ArrayList<String>> lerArquivo(String arquivo){
		ArrayList<ArrayList<String>> dados = new ArrayList<ArrayList<String>>();
		try{
			FileReader arq = new FileReader(arquivo+".txt");
			BufferedReader ler = new BufferedReader(arq);
			String linha;
			ArrayList<String> numLinha = new ArrayList<String>();
			while(ler.ready()){
				linha = ler.readLine();
				numLinha = decodificaRegistro(linha);
				dados.add(numLinha);
			}
			
			ler.close();
			arq.close();
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		return dados;
	}
	public ArrayList<String> decodificaRegistro(String registro){
		ArrayList<String> dados = new ArrayList<String>();
		String ch = "";
		char [] reg = registro.toCharArray();
		for(int x = 0; x<reg.length;x++){
			if(reg[x]=='|'){
				dados.add(ch);
				ch = "";
			}
			else{
				ch += reg[x];
			}
		}
		dados.add(ch);
		return dados;
	}
	public ArrayList<String> pesquisa(int id, String arquivo){
		ArrayList<ArrayList<String>> a= lerArquivo(arquivo);
		
		for(int x=0; x<a.size(); x++){
			if(Integer.parseInt(a.get(x).get(0))==id){
				return a.get(x);
			}	
		}
		return new ArrayList<String>();	
	}
	public String codificaRegistro(ArrayList<String> registro){
		String R="";
		for(int x=0; x<registro.size()-1; x++){
			R+=registro.get(x)+"|";
		}
		R+=registro.get(registro.size()-1);
		return R;
	}
	public void editaArquivo(String nomeDoArquivo, String registro){
		ArrayList<String> reg = decodificaRegistro(registro);
		ArrayList<ArrayList<String>> p= lerArquivo(nomeDoArquivo);
		for(int x=0; x<p.size(); x++){
			if(p.get(x).get(0).equals(reg.get(0))){
				p.set(x, reg);
				break;
			}	
		}
		try{
			FileWriter arq = new FileWriter(nomeDoArquivo+".txt");
			BufferedWriter escrever = new BufferedWriter(arq);
			
			for(int x=0; x<p.size(); x++){
				escrever.write(this.codificaRegistro(p.get(x)));
				escrever.newLine();
			}
			
			escrever.close();
			arq.close();
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public int getLastID(String nomeArquivo){
		ArrayList<ArrayList<String>> a = lerArquivo(nomeArquivo);
		
		if(a.size()==0){
			return 0;
		}
		String lastId = a.get(a.size()-1).get(0);
		
		return Integer.parseInt(lastId);	
	}
}
