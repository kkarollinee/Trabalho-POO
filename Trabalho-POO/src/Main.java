import java.util.ArrayList;
public class Main {

	public static void main(String[] args) {
		Modulo pecas = new Modulo("Pecas");
		Modulo pecas2 = new Modulo("Pecas2");
		ArrayList<Modulo> modulos = new ArrayList<Modulo>();
		modulos.add(pecas);
		modulos.add(pecas2);
		Desktop desktop = new Desktop(modulos);
		desktop.setVisible(true);
		
	}

}
