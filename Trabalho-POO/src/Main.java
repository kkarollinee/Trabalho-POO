import java.util.ArrayList;
public class Main {

	public static void main(String[] args) {
		Login l = new Login();
		l.setVisible(true);
		if(false){
			Modulo produtos = new Modulo("Produtos");
			Modulo fornecedores = new Modulo("Fornecedores");
			Modulo clientes = new Modulo("clientes");
			Modulo notaFiscalEntrada = new Modulo("notaFiscalEntrada");
			Modulo notaFiscalSaida = new Modulo("notaFiscalSaida");
			Modulo funcionarios = new Modulo("Funcionarios");
			Modulo minhaEmpresa = new Modulo("MinhaEmpresa");
			
			ArrayList<Modulo> modulos = new ArrayList<Modulo>();
			modulos.add(produtos);
			modulos.add(fornecedores);
			modulos.add(clientes);
			modulos.add(notaFiscalEntrada);
			modulos.add(notaFiscalSaida);
			modulos.add(minhaEmpresa);
			modulos.add(funcionarios);
			Desktop desktop = new Desktop(modulos);
			desktop.setVisible(true);
		}
	}

}
