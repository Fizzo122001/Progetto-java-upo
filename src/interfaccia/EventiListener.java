package interfaccia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import eccezioni.CostoException;
import eccezioni.NomeEsistenteException;
import eccezioni.NomeStringaException;
import eccezioni.NomeVuotoException;
import eccezioni.QuantitaNegativaException;


public class EventiListener implements ActionListener {
	private MainFrame frame;

	public EventiListener(MainFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Visualizza Lista":
			frame.visualizzaLista();
			break;
		case "Aggiungi Articolo":
			try {
				frame.aggiungiArticolo();
			} catch (NomeEsistenteException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage() , "Nome già esistente" , JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "Rimuovi Articolo":
			frame.rimuoviArticolo();
			break;
		case "Salva su File":
			try {
				frame.salvaSuFile();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage() , "File non trovato" , JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage() , "IO errore" , JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (NomeVuotoException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage() , "Nome vuoto" , JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (QuantitaNegativaException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage() , "Quantità negativa" , JOptionPane.ERROR_MESSAGE);
			} catch (NomeStringaException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage() , "Nome stringa vuoto" , JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "Crea Nuova Lista":
			frame.creaLista();
			break;
		case "Visualizza Tutte le Liste":
			frame.visualizzaTutteLeListe();
			break;
		case "Carica lista da un file":
			try {
				frame.CaricaDaFile();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage() , "File non trovato" , JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage() , "IO error" , JOptionPane.ERROR_MESSAGE);
			} catch (NomeVuotoException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage() , "Nome vuoto" , JOptionPane.ERROR_MESSAGE);
			} catch (QuantitaNegativaException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage() , "Quantità negativa" , JOptionPane.ERROR_MESSAGE);
			} catch (NomeStringaException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage() , "Nome stringa vuota" , JOptionPane.ERROR_MESSAGE);
			} catch (CostoException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage() , "Costo vuoto" , JOptionPane.ERROR_MESSAGE);
			} catch (NomeEsistenteException e1) {
				JOptionPane.showMessageDialog(frame, e1.getMessage() , "Nome già esistente" , JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "Cerca articoli da categoria":
			frame.trovaCategoria(frame.getListaCorrente());
			break;
		case "Scegli la lista":
			frame.scegliLista();
			break;
		case "Totale della lista":
			frame.prezzo(frame.getListaCorrente());
			break;
		case "Elimina una categoria":
			frame.rimuoviCategoria(frame.getListaCorrente());
			break;
		case "Esci":
			System.exit(0);
			break;
		default:
			break;
		}

	}

}
