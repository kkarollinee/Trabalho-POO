package grafico;
import java.util.ArrayList;

import javax.swing.JFrame;
public class Start {
	public Start() {
		Modulo produtos = new Modulo("produtos","Produtos: Controle de estoque e cadastro de produtos.");
		Modulo fornecedores = new Modulo("fornecedores","Fornecedores: Gerência de fornecedores.");
		Modulo clientes = new Modulo("clientes","Clientes: Controle de cadastro de clientes.");
		Modulo notaFiscalEntrada = new Modulo("notaFiscalEntrada","Notas Fiscais de Entrada");
		Modulo notaFiscalSaida = new Modulo("notaFiscalSaida","Notas Fiscais de Saída");
		Modulo funcionarios = new Modulo("funcionarios","Funcionários: Controle de cadastro de funcionários.");
		Modulo minhaEmpresa = new Modulo("minhaEmpresa","Minha Empresa: Informações sobre a empresa.");
		Modulo relatorios = new Modulo("relatorios","Relatórios");
		
		ArrayList<Modulo> modulos = new ArrayList<Modulo>();
		modulos.add(produtos);
		modulos.add(fornecedores);
		modulos.add(clientes);
		modulos.add(notaFiscalEntrada);
		modulos.add(notaFiscalSaida);
		modulos.add(minhaEmpresa);
		modulos.add(funcionarios);
		modulos.add(relatorios);
		Desktop desktop = new Desktop(modulos);
		desktop.setVisible(true);
		desktop.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
