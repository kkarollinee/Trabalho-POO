import java.util.ArrayList;
import java.util.Scanner;
public class Main {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		BD banco = new BD();
		int option2;
		Scanner  escolha = new Scanner(System.in);
		MinhaEmpresa empresa = null;
		while(true){
			try{
				ArrayList<String> emp = banco.pesquisa(1, "MinhaEmpresa");
				if(emp.size()==0){
					System.out.println("Deseja Cadastrar uma Empresa? Digite 1 (Sim) ou 2 (Nao):");
					option2 = Integer.parseInt(escolha.nextLine());
					if(option2 == 1){
						empresa = new MinhaEmpresa();
						banco.gravaEmpresa(empresa, false);
					}
					else{
						System.out.println(" ° Agradecemos a utilizacao do nosso software °  \n\n\t Todos os direitos reservados ");
						System.exit(1);
					}
				}
				else{
					empresa = new MinhaEmpresa(emp);
				}
				
				System.out.println("Digite 1 para Funcionario\t° Adicionar\n\t\t\t\t° Editar\n\t\t\t\t° Listar\n\n");
				System.out.println("Digite 2 para Fornecedor\t° Fisico °\t° Juridico °\n\n");
				System.out.println("Digite 3 para Produto\t\t° Adicionar\n\t\t\t\t° Editar\n\t\t\t\t° Listar\n\n");
				System.out.println("Digite 4 para Cliente\t\t° Fisico °\t° Juridico °\n\n");
				System.out.println("Digite 5 para NotaFiscal\t° Entrada °\t° Saida °\n\n");
				System.out.print("Digite 6 para Sair do Programa\n\n");
				System.out.print("Qual a opcao desejada: ");
				option2 = Integer.parseInt(escolha.nextLine());
				switch(option2){
				case 1:
					System.out.println("Funcionario - Digite a Opcao:\t\t° 1 - Adicionar\n\t\t\t\t\t° 2 - Editar\n\t\t\t\t\t° 3 - Listar\n\n");
					int opcao = Integer.parseInt(escolha.nextLine());
					if(opcao==1){
						Funcionario f = new Funcionario();
						banco.gravaFuncionario(f, false);
					}
					else if(opcao==2){
						System.out.println("Digite a ID do funcionario: ");
						int id = Integer.parseInt(escolha.nextLine());
						
						ArrayList<String> funInBanco = banco.pesquisa(id, "Funcionario");
						
						Funcionario f = new Funcionario();
						f.pessoa_id = Integer.parseInt(funInBanco.get(0));
						
						banco.gravaFuncionario(f, true);
					}
					else if(opcao==3){ 
						ArrayList<ArrayList<String>> lista = banco.lerArquivo("Funcionario");
						System.out.println("ID - nome - rg - cpf - telefone - status - rua - numero - " +
								"complemento - bairro - cidade - estado - cargo - horarioEntrada - " +
								"horarioPausa - horarioRetorno - horarioSaida - salario");
						for(int x=0; x<lista.size(); x++){
							for(int y=0;y<18;y++){
								System.out.print(lista.get(x).get(y)+" - ");
					
							}
							System.out.print("\n");
						}
						
						System.out.println("pressione Enter para continuar...");
						String s = escolha.nextLine();
					}
					else{
						System.out.println("A opcao digitada e invalida!");
					}
					
					break;
				case 2:
					System.out.println("Digite 1 para Fornecedor Fisico ou Digite 2 para Fornecedor Juridico");
					System.out.println("Opcao: ");
					option2 = Integer.parseInt(escolha.nextLine());
					if(option2 == 1){
						System.out.println("Fornecedor Fisico - Digite a Opcao:\t° 1 - Adicionar\n\t\t\t\t\t° 2 - Editar\n\t\t\t\t\t° 3 - Listar\n\n");
						opcao = Integer.parseInt(escolha.nextLine());
						if(opcao==1){
							FornecedorFisico f = new FornecedorFisico();
							banco.gravaFornecedorFis(f, false);
						}
						else if(opcao==2){
							System.out.println("Digite a ID do fornecedor: ");
							int id = Integer.parseInt(escolha.nextLine());
							
							ArrayList<String> forInBanco = banco.pesquisa(id, "FornecedorFisico");
							
							FornecedorFisico f = new FornecedorFisico();
							f.pessoa_id = Integer.parseInt(forInBanco.get(0));
							
							banco.gravaFornecedorFis(f, true);
						}
						else if(opcao==3){
							ArrayList<ArrayList<String>> lista = banco.lerArquivo("FornecedorFisico");
							System.out.println("ID - nome - rg - cpf - telefone - status - rua - numero - " +
									"complemento - bairro - cidade - estado");
							for(int x=0; x<lista.size(); x++){
								for(int y=0;y<12;y++){
									System.out.print(lista.get(x).get(y)+" - ");
								}
								System.out.print("\n");
							}
							System.out.println("pressione Enter para continuar...");
							String s = escolha.nextLine();
						}
						else{
							System.out.println("A opcao digitada e invalida!");
						}
					}
					else{
						System.out.println("Fornecedor Juridico - Digite a Opcao:\t° 1 - Adicionar\n\t\t\t\t\t° 2 - Editar\n\t\t\t\t\t° 3 - Listar\n\n");
						opcao = Integer.parseInt(escolha.nextLine());
						if(opcao==1){
							FornecedorJuridico f = new FornecedorJuridico();
							banco.gravaFornecedorJur(f, false);
						}
						else if(opcao==2){
							System.out.println("Digite a ID do fornecedor: ");
							int id = Integer.parseInt(escolha.nextLine());
							
							ArrayList<String> forInBanco = banco.pesquisa(id, "FornecedorJuridico");
							
							FornecedorJuridico f = new FornecedorJuridico();
							f.pessoa_id = Integer.parseInt(forInBanco.get(0));
							
							banco.gravaFornecedorJur(f, true);
						}
						else if(opcao==3){
							ArrayList<ArrayList<String>> lista = banco.lerArquivo("FornecedorJuridico");
							System.out.println("ID - Razao Social - Nome Fantasia - CNPJ - telefone - " +
									"status - rua - numero - " +
									"complemento - bairro - cidade - estado");
							for(int x=0; x<lista.size(); x++){
								for(int y=0;y<12;y++){
									System.out.print(lista.get(x).get(y)+" - ");
								}
								System.out.print("\n");
							}
							System.out.println("pressione Enter para continuar...");
							String s = escolha.nextLine();
						}
						else{
							System.out.println("A opcao digitada e invalida!");
						}
					}
					break;
				case 3:
					System.out.println("Produto - Digite a Opcao:\t\t° 1 - Adicionar\n\t\t\t\t\t° 2 - Editar\n\t\t\t\t\t° 3 - Listar\n\n");
					opcao = Integer.parseInt(escolha.nextLine());
					if(opcao==1){
						Produto p = new Produto();
						banco.gravaProduto(p, false);
					}
					else if(opcao==2){
						System.out.println("Digite a ID do produto: ");
						int id = Integer.parseInt(escolha.nextLine());
						
						ArrayList<String> funInBanco = banco.pesquisa(id, "Produto");
						
						Produto f = new Produto();
						f.produto_id = Integer.parseInt(funInBanco.get(0));
						
						banco.gravaProduto(f, true);
					}
					else if(opcao==3){
						ArrayList<ArrayList<String>> lista = banco.lerArquivo("Produto");
						System.out.println("ID - nome - valor - peso - quantidade estocada - grupo ");
						for(int x=0; x<lista.size(); x++){
							for(int y=0;y<6;y++){
								System.out.print(lista.get(x).get(y)+" - ");
							}
							System.out.print("\n");
						}
						System.out.println("pressione Enter para continuar...");
						String s = escolha.nextLine();
					}
					else{
						System.out.println("A opcao digitada e invalida!");
					}				
					break;
				case 4:
					System.out.println("Digite 1 para Cliente Fisico ou Digite 2 para Cliente Juridico");
					System.out.println("Opcao: ");
					option2 = Integer.parseInt(escolha.nextLine());
					if(option2 == 1){
						System.out.println("Cliente Fisico - Digite a Opcao:\t° 1 - Adicionar\n\t\t\t\t\t° 2 - Editar\n\t\t\t\t\t° 3 - Listar\n\n");
						opcao = Integer.parseInt(escolha.nextLine());
						if(opcao==1){
							ClienteFisico f = new ClienteFisico();
							banco.gravaClienteFis(f, false);
						}
						else if(opcao==2){
							System.out.println("Digite a ID do Cliente: ");
							int id = Integer.parseInt(escolha.nextLine());
							
							ArrayList<String> forInBanco = banco.pesquisa(id, "ClienteFisico");
							
							ClienteFisico f = new ClienteFisico();
							f.pessoa_id = Integer.parseInt(forInBanco.get(0));
							
							banco.gravaClienteFis(f, true);
						}
						else if(opcao==3){
							ArrayList<ArrayList<String>> lista = banco.lerArquivo("ClienteFisico");
							System.out.println("ID - nome - rg - cpf - telefone - status - rua - numero - " +
									"complemento - bairro - cidade - estado");
							for(int x=0; x<lista.size(); x++){
								for(int y=0;y<12;y++){
									System.out.print(lista.get(x).get(y)+" - ");
								}
								System.out.print("\n");
							}
							System.out.println("pressione Enter para continuar...");
							String s = escolha.nextLine();
						}
						else{
							System.out.println("A opcao digitada e invalida!");
						}
					}
					else{
						System.out.println("Cliente Juridico - Digite a Opcao:\t° 1 - Adicionar\n\t\t\t\t\t° 2 - Editar\n\t\t\t\t\t° 3 - Listar\n\n");
						opcao = Integer.parseInt(escolha.nextLine());
						if(opcao==1){
							ClienteJuridico f = new ClienteJuridico();
							banco.gravaClienteJur(f, false);
						}
						else if(opcao==2){
							System.out.println("Digite a ID do Cliente: ");
							int id = Integer.parseInt(escolha.nextLine());
							
							ArrayList<String> forInBanco = banco.pesquisa(id, "ClienteJuridico");
							
							ClienteJuridico f = new ClienteJuridico();
							f.pessoa_id = Integer.parseInt(forInBanco.get(0));
							
							banco.gravaClienteJur(f, true);
						}
						else if(opcao==3){
							ArrayList<ArrayList<String>> lista = banco.lerArquivo("ClienteJuridico");
							System.out.println("ID - Razao Social - Nome Fantasia - CNPJ - telefone - " +
									"status - rua - numero - " +
									"complemento - bairro - cidade - estado");
							for(int x=0; x<lista.size(); x++){
								for(int y=0;y<12;y++){
									System.out.print(lista.get(x).get(y)+" - ");
								}
								System.out.print("\n");
							}
							System.out.println("pressione Enter para continuar...");
							String s = escolha.nextLine();
						}
						else{
							System.out.println("A opcao digitada e invalida!");
						}
					}
					break;
				case 5:
					System.out.println("Digite 1 para Nota Fiscal de Entrada ou Digite 2 para Nota Fiscal de Saida");
					System.out.println("Opcao: ");
					option2 = Integer.parseInt(escolha.nextLine());
					
					//nota fiscal de entrada
					if(option2 == 1){
						System.out.println("Nota de Entrada - Digite a Opcao:\t° 1 - Fornecedor Fisico \n\t\t\t\t\t° 2 - Fornecedor Juridico \n\n");
						opcao = Integer.parseInt(escolha.nextLine());
						if(opcao==1){
							System.out.println("Nota de Entrada - Fornecedor Fisico - Digite a Opcao:\t° 1 - Dar entrada \n\t\t\t\t\t\t\t° 2 - Listar \n\n");
							opcao = Integer.parseInt(escolha.nextLine());
							if(opcao==1){
								System.out.println("Digite a ID do fornecedor: ");
								int idF = Integer.parseInt(escolha.nextLine());
								System.out.println("Digite a ID do produto: ");
								int idP = Integer.parseInt(escolha.nextLine());
								
								NFEntradaFisico f = new NFEntradaFisico(idF,idP);
								
								ArrayList<String> pInEstoque = banco.pesquisa(idP, "Produto");
								Produto inEstoque = new Produto(pInEstoque);
								
								banco.gravaNotaFiscalFornecedorFis(f, false);
								
								inEstoque.adicionarEstoque(f.produtos.quantidadeNaNota);
								
								empresa.comprar(f.getValor());
							}
							else if(opcao==2){
								ArrayList<ArrayList<String>> lista = banco.lerArquivo("NotaEntradaFisico");
								System.out.println("ID da nota - Data - Valor Total - " +
										"ID do Fornecedor - nome do Fornecedor - " +
										"ID do Produto - Nome do Produto - Quantidade - Valor Unitário");
								for(int x=0; x<lista.size(); x++){
									System.out.print(lista.get(x).get(0)+" - ");
									System.out.print(lista.get(x).get(1)+" - ");
									
									ArrayList<String> pInNota = banco.pesquisa(Integer.parseInt(lista.get(x).get(0)), "NotaEntradaFisicoProduto");
									ArrayList<String> pInfo = banco.pesquisa(Integer.parseInt(pInNota.get(1)), "Produto");

									System.out.print((Float.parseFloat(pInNota.get(2))*Float.parseFloat(pInNota.get(3)))+" - ");

									System.out.print(lista.get(x).get(2)+" - ");
									
									ArrayList<String> fornecedor = banco.pesquisa(Integer.parseInt(lista.get(x).get(2)), "FornecedorFisico");

									System.out.print(fornecedor.get(1)+" - ");
									
									System.out.print(pInfo.get(0)+" - ");
									System.out.print(pInfo.get(1)+" - ");

									System.out.print(pInNota.get(2)+" - ");
									System.out.print(pInNota.get(3)+" - ");
									
									System.out.print("\n");
								}
								System.out.println("pressione Enter para continuar...");
								String s = escolha.nextLine();
							}
							else{
								System.out.println("A opcao digitada e invalida!");
							}
						}
						else if(opcao==2){
							System.out.println("Nota de Entrada - Fornecedor Juridico - Digite a Opcao:\t° 1 - Dar entrada \n\t\t\t\t\t\t\t° 2 - Listar \n\n");
							opcao = Integer.parseInt(escolha.nextLine());
							if(opcao==1){
								System.out.println("Digite a ID do fornecedor: ");
								int idF = Integer.parseInt(escolha.nextLine());
								System.out.println("Digite a ID do produto: ");
								int idP = Integer.parseInt(escolha.nextLine());
								
								NFEntradaJuridico f = new NFEntradaJuridico(idF,idP);
								
								ArrayList<String> pInEstoque = banco.pesquisa(idP, "Produto");
								Produto inEstoque = new Produto(pInEstoque);
								
								banco.gravaNotaFiscalFornecedorJur(f, false);
								
								inEstoque.adicionarEstoque(f.produtos.quantidadeNaNota);
								
								empresa.comprar(f.getValor());
							}
							else if(opcao==2){
								ArrayList<ArrayList<String>> lista = banco.lerArquivo("NotaEntradaJuridico");
								System.out.println("ID da nota - Data - Valor Total - " +
										"ID do Fornecedor - Razao Social do Fornecedor - " +
										"ID do Produto - Nome do Produto - Quantidade - Valor Unitário");
								for(int x=0; x<lista.size(); x++){
									System.out.print(lista.get(x).get(0)+" - ");
									System.out.print(lista.get(x).get(1)+" - ");
									
									ArrayList<String> pInNota = banco.pesquisa(Integer.parseInt(lista.get(x).get(0)), "NotaEntradaJuridicoProduto");
									ArrayList<String> pInfo = banco.pesquisa(Integer.parseInt(pInNota.get(1)), "Produto");

									System.out.print((Float.parseFloat(pInNota.get(2))*Float.parseFloat(pInNota.get(3)))+" - ");

									System.out.print(lista.get(x).get(2)+" - ");
									
									ArrayList<String> fornecedor = banco.pesquisa(Integer.parseInt(lista.get(x).get(2)), "FornecedorJuridico");

									System.out.print(fornecedor.get(1)+" - ");
									
									System.out.print(pInfo.get(0)+" - ");
									System.out.print(pInfo.get(1)+" - ");

									System.out.print(pInNota.get(2)+" - ");
									System.out.print(pInNota.get(3)+" - ");
									
									System.out.print("\n");
								}
								System.out.println("pressione Enter para continuar...");
								String s = escolha.nextLine();
							}
							else{
								System.out.println("A opcao digitada e invalida!");
							}
						}
						else{
							System.out.println("A opcao digitada e invalida!");
						}
					}
					

					//nota de saida
					else{
						System.out.println("Nota de Saida - Digite a Opcao:\t\t° 1 - Cliente Fisico \n\t\t\t\t\t° 2 - Cliente Juridico \n\n");
						opcao = Integer.parseInt(escolha.nextLine());
						if(opcao==1){
							System.out.println("Nota de Saida - Cliente Fisico - Digite a Opcao:\t° 1 - Emitir \n\t\t\t\t\t\t\t° 2 - Listar \n\n");
							opcao = Integer.parseInt(escolha.nextLine());
							if(opcao==1){
								System.out.println("Digite a ID do cliente: ");
								int idC = Integer.parseInt(escolha.nextLine());
								System.out.println("Digite a ID do produto: ");
								int idP = Integer.parseInt(escolha.nextLine());
								
								NFSaidaFisico f = new NFSaidaFisico(idC,idP);
								
								ArrayList<String> pInEstoque = banco.pesquisa(idP, "Produto");
								Produto inEstoque = new Produto(pInEstoque);
								if((inEstoque.quantidadeEstocada-f.produtos.quantidadeNaNota)<0){
									try {  
									    System.out.println("Produto indisponivel em estoque.");  
									    Thread.sleep(3000);  
									} catch(Exception i) { 
										
									}
									continue;
								}
								
								banco.gravaNotaFiscalClienteFis(f, false);
								
								inEstoque.abaterEstoque(f.produtos.quantidadeNaNota);
								
								empresa.vender(f.getValor());
							}
							else if(opcao==2){
								ArrayList<ArrayList<String>> lista = banco.lerArquivo("NotaSaidaFisico");
								System.out.println("ID da nota - Data - Valor Total - " +
										"ID do Fornecedor - nome do Cliente - " +
										"ID do Produto - Nome do Produto - Quantidade - Valor Unitário");
								for(int x=0; x<lista.size(); x++){
									System.out.print(lista.get(x).get(0)+" - ");
									System.out.print(lista.get(x).get(1)+" - ");
									
									ArrayList<String> pInNota = banco.pesquisa(Integer.parseInt(lista.get(x).get(0)), "NotaSaidaFisicoProduto");
									ArrayList<String> pInfo = banco.pesquisa(Integer.parseInt(pInNota.get(1)), "Produto");

									System.out.print((Float.parseFloat(pInNota.get(2))*Float.parseFloat(pInNota.get(3)))+" - ");

									System.out.print(lista.get(x).get(2)+" - ");
									
									ArrayList<String> fornecedor = banco.pesquisa(Integer.parseInt(lista.get(x).get(2)), "ClienteFisico");

									System.out.print(fornecedor.get(1)+" - ");
									
									System.out.print(pInfo.get(0)+" - ");
									System.out.print(pInfo.get(1)+" - ");

									System.out.print(pInNota.get(2)+" - ");
									System.out.print(pInNota.get(3)+" - ");
									
									System.out.print("\n");
								}
								System.out.println("pressione Enter para continuar...");
								String s = escolha.nextLine();
							}
							else{
								System.out.println("A opcao digitada e invalida!");
							}
						}
						else if(opcao==2){
							System.out.println("Nota de Saida - Cliente Juridico - Digite a Opcao:\t° 1 - Emitir \n\t\t\t\t\t\t\t° 2 - Listar \n\n");
							opcao = Integer.parseInt(escolha.nextLine());
							if(opcao==1){
								System.out.println("Digite a ID do cliente: ");
								int idC = Integer.parseInt(escolha.nextLine());
								System.out.println("Digite a ID do produto: ");
								int idP = Integer.parseInt(escolha.nextLine());
								
								NFSaidaJuridico f = new NFSaidaJuridico(idC,idP);
								
								ArrayList<String> pInEstoque = banco.pesquisa(idP, "Produto");
								Produto inEstoque = new Produto(pInEstoque);
								if((inEstoque.quantidadeEstocada-f.produtos.quantidadeNaNota)<0){
									try {  
									    System.out.println("Produto indisponivel em estoque.");  
									    Thread.sleep(3000);  
									} catch(Exception i) { 
										
									}
									continue;
								}
								
								banco.gravaNotaFiscalClienteJur(f, false);
								
								inEstoque.abaterEstoque(f.produtos.quantidadeNaNota);
								
								empresa.vender(f.getValor());
							}
							else if(opcao==2){
								ArrayList<ArrayList<String>> lista = banco.lerArquivo("NotaSaidaJuridico");
								System.out.println("ID da nota - Data - Valor Total - " +
										"ID do Fornecedor - Razao Social do Cliente - " +
										"ID do Produto - Nome do Produto - Quantidade - Valor Unitário");
								for(int x=0; x<lista.size(); x++){
									System.out.print(lista.get(x).get(0)+" - ");
									System.out.print(lista.get(x).get(1)+" - ");
									
									ArrayList<String> pInNota = banco.pesquisa(Integer.parseInt(lista.get(x).get(0)), "NotaSaidaJuridicoProduto");
									ArrayList<String> pInfo = banco.pesquisa(Integer.parseInt(pInNota.get(1)), "Produto");

									System.out.print((Float.parseFloat(pInNota.get(2))*Float.parseFloat(pInNota.get(3)))+" - ");

									System.out.print(lista.get(x).get(2)+" - ");
									
									ArrayList<String> fornecedor = banco.pesquisa(Integer.parseInt(lista.get(x).get(2)), "ClienteJuridico");

									System.out.print(fornecedor.get(1)+" - ");
									
									System.out.print(pInfo.get(0)+" - ");
									System.out.print(pInfo.get(1)+" - ");

									System.out.print(pInNota.get(2)+" - ");
									System.out.print(pInNota.get(3)+" - ");
									
									System.out.print("\n");
								}
								System.out.println("pressione Enter para continuar...");
								String s = escolha.nextLine();
							}
							else{
								System.out.println("A opcao digitada e invalida!");
							}
						}
						else{
							System.out.println("A opcao digitada e invalida!");
						}
					}
					break;
				case 6: 
					System.exit(3);
					break;
				default:
					try {  
					    System.out.println("Algum dado inválido foi informado, aguarde para continuar");  
					    Thread.sleep(2000);  
					} catch(Exception i) { 
						
					}
					break;
				}//switch
			}//try
			catch (Exception e){
				try {  
				    System.out.println("Algum dado inválido foi informado, aguarde para continuar");  
				    Thread.sleep(2000);  
				} catch(Exception i) { 
					
				}
			}
		}//while
	}//main

}//classe