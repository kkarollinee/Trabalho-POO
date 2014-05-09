package banco;
import java.io.*;
import java.util.ArrayList;
import sistema.*;

public class BD {
	public BD(){
		
	}
	public void gravaProduto(Produto p,boolean editar){
		//formato do registro:
		// produto_id|nome|valor|peso|quatidadeEstocada|grupo
		
		String registro=p.produto_id+"|"+p.nome+"|"+p.valor+"|"+p.peso+"|"+p.quantidadeEstocada+"|"+p.grupo;
		
		if (editar){
			this.editaArquivo("Produto", registro);
		}
		else
			this.gravaArquivo("Produto", registro);
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
	public void gravaRelatorio(Relatorios r,boolean editar){
		String registro = "|"+r+"|"+r+"|"+r;
		
		if (editar){
			this.editaArquivo("Relatorios", registro);
		}
		else
			this.gravaArquivo("Relatorios", registro);
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
