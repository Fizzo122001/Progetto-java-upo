package sceltaMain;

import interfaccia.MainFrame;
import progetto.Main;
import jbook.util.Input;

public class MainScelta {

	public static void main(String[] args) {

		int scelta;
		do {
		System.out.println("Menu:");
		System.out.println("1. Interfaccia testuale");
		System.out.println("2. Interfaccia grafica");
		System.out.println("0. Esci");
		System.out.print("Scelta: ");
		scelta = Input.readInt();
		if (scelta > 2) 
			System.out.println("La scelta non è corretta. Riprova");
		} while(scelta > 2 || scelta < 0);
		
		switch (scelta) {
		case 1:
			Main.main(args);
			break;
		case 2:
			MainFrame.main(args);
			break;
		case 0:
			System.out.println("Stai uscendo");
			break;
		}
	}
}
