/**
* …………
*
* @author Marco Chiappa 20047162
* @author Beatrice Capella 20034984
*/
package interfaccia;

import javax.swing.*;

import eccezioni.CostoException;
import eccezioni.NomeEsistenteException;
import eccezioni.NomeStringaException;
import eccezioni.NomeVuotoException;
import eccezioni.QuantitaNegativaException;
import progetto.Articolo;
import progetto.ListaSpesa;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	private List<ListaSpesa> tutteLeListe;
	private ListaSpesa listaCorrente;
	private EventiListener controller;

	public List<ListaSpesa> getTutteLeListe() {
		return tutteLeListe;
	}

	public void setTutteLeListe(List<ListaSpesa> tutteLeListe) {
		this.tutteLeListe = tutteLeListe;
	}

	public ListaSpesa getListaCorrente() {
		return listaCorrente;
	}

	public void setListaCorrente(ListaSpesa listaCorrente) {
		this.listaCorrente = listaCorrente;
	}

	public MainFrame() {
		super("Interfaccia per gestire le liste della spesa");
		controller = new EventiListener(this);
		tutteLeListe = new ArrayList<>();
		listaCorrente = null;

		// Creazione dei componenti
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel centerPanel = new JPanel(new GridLayout(4, 3, 10, 10));

		JLabel label = new JLabel("Scegli la funzionalità");
		JButton aggiungiArticoloButton = new JButton("Aggiungi Articolo");
		JButton rimuoviArticoloButton = new JButton("Rimuovi Articolo");
		JButton visualizzaListaButton = new JButton("Visualizza Lista");
		JButton salvaSuFileButton = new JButton("Salva su File");
		JButton creaListaButton = new JButton("Crea Nuova Lista");
		JButton visualizzaTutteLeListeButton = new JButton("Visualizza Tutte le Liste");
		JButton CaricaDaFileButton = new JButton("Carica lista da un file");
		JButton visualizzaArticoliDaCategoriaButton = new JButton("Cerca articoli da categoria");
		JButton ScegliListaButton = new JButton("Scegli la lista");
		JButton VisualizzaTotaleButton = new JButton("Totale della lista");
		JButton EliminaCategoriaButton = new JButton("Elimina una categoria");
		JButton EsciButton = new JButton("Esci");
		EsciButton.setBackground(Color.GRAY);

		textArea = new JTextArea(20, 40);
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		topPanel.add(label);
		bottomPanel.add(scrollPane, BorderLayout.CENTER);

		centerPanel.add(aggiungiArticoloButton);
		centerPanel.add(rimuoviArticoloButton);
		centerPanel.add(creaListaButton);
		centerPanel.add(visualizzaListaButton);
		centerPanel.add(salvaSuFileButton);
		centerPanel.add(visualizzaTutteLeListeButton);
		centerPanel.add(CaricaDaFileButton);
		centerPanel.add(visualizzaArticoliDaCategoriaButton);
		centerPanel.add(ScegliListaButton);
		centerPanel.add(VisualizzaTotaleButton);
		centerPanel.add(EliminaCategoriaButton);
		centerPanel.add(EsciButton);

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

		add(mainPanel);
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		// Azioni ai pulsanti
		aggiungiArticoloButton.addActionListener(controller);
		rimuoviArticoloButton.addActionListener(controller);
		CaricaDaFileButton.addActionListener(controller);
		visualizzaListaButton.addActionListener(controller);
		salvaSuFileButton.addActionListener(controller);
		creaListaButton.addActionListener(controller);
		visualizzaTutteLeListeButton.addActionListener(controller);
		visualizzaArticoliDaCategoriaButton.addActionListener(controller);
		ScegliListaButton.addActionListener(controller);
		VisualizzaTotaleButton.addActionListener(controller);
		EliminaCategoriaButton.addActionListener(controller);
		EsciButton.addActionListener(controller);

	}

	public void aggiungiArticolo() throws NomeEsistenteException {
		try {
			String nome = JOptionPane.showInputDialog(this, "Inserisci il nome dell'articolo:", "Inserimento Nome",
					JOptionPane.PLAIN_MESSAGE);
			if (nome == null || nome.isEmpty()) {
				throw new NomeVuotoException("Il nome non deve essere vuoto.");
			}
			if (!nome.matches("[a-zA-Z]+")) {
				throw new NomeStringaException(nome);
			}

			String costoInput = JOptionPane.showInputDialog(this, "Inserisci il costo dell'articolo:",
					"Inserimento Costo", JOptionPane.PLAIN_MESSAGE);
			if (costoInput == null || costoInput.isEmpty()) {
				throw new CostoException("Il costo non può essere vuoto.");
			}

			float costo;
			try {
				costo = Float.parseFloat(costoInput);

			} catch (NumberFormatException e) {
				throw new CostoException("Il costo deve essere un numero.");
			}
			if (costo <= 0) {
				throw new CostoException("Il costo deve essere positivo.");
			}

			String categoria = JOptionPane.showInputDialog(this, "Inserisci la categoria dell'articolo:",
					"Inserimento Categoria", JOptionPane.PLAIN_MESSAGE);
			if (categoria == null || categoria.isEmpty()) {
				categoria = "Non categorizzati";
			}

			String quantitaInput = JOptionPane.showInputDialog(this, "Inserisci la quantità dell'articolo:",
					"Inserimento Quantità", JOptionPane.PLAIN_MESSAGE);
			if (quantitaInput == null || quantitaInput.isEmpty()) {
				quantitaInput = "1";
			}

			int quantita;
			try {
				quantita = Integer.parseInt(quantitaInput);
			} catch (NumberFormatException e) {
				throw new QuantitaNegativaException("La quantità deve essere un numero.");
			}
			if (quantita <= 0) {
				throw new QuantitaNegativaException("La quantità deve essere positiva.");
			}

			Articolo articolo = new Articolo(nome, costo, categoria, quantita);

			if (listaCorrente == null) {
				textArea.setText("Non ci sono liste di spesa create. Creane una nuova.");
				creaLista();
			}

			if (listaCorrente != null) {
				listaCorrente.aggiungi(articolo);
				textArea.setText("Articolo aggiunto con successo alla lista della spesa.");
			}

		} catch (NomeStringaException e) {
			JOptionPane.showMessageDialog(this, "Errore: Il nome non deve contenere numeri.", "Errore",
					JOptionPane.ERROR_MESSAGE);
		} catch (QuantitaNegativaException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		} catch (CostoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		} catch (NomeVuotoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
		} catch (NomeEsistenteException e) {
			JOptionPane.showMessageDialog(this, "Errore: Il nome dell'articolo già esistente", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void rimuoviArticolo() {
		if (listaCorrente == null) {
			textArea.setText("Non ci sono liste di spesa create. Non puoi rimuovere articoli");

		} else {
			int flag = 0;
			String daRimuovere = JOptionPane.showInputDialog(this, "Inserisci il nome dell'articolo da rimuovere: ",
					"Inserisci il nome dell'articolo da rimuovere", JOptionPane.PLAIN_MESSAGE);
			;

			for (Articolo articolo : listaCorrente.getArticoli()) {
				if (articolo.getNome().equals(daRimuovere)) {
					listaCorrente.rimuoviArticolo(articolo);
					textArea.setText(articolo.getNome() + " rimosso con successo dalla lista della spesa.");
					flag = 1;
					break;
				}
			}

			if (flag == 0) {
				textArea.setText("Articolo non trovato nella lista della spesa.");
			}

		}

	}

	public void visualizzaLista() {
		if (listaCorrente != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Proprietario della lista: ").append(listaCorrente.getNome()).append("\n");

			ArrayList<Articolo> articoli = (ArrayList<Articolo>) listaCorrente.getArticoli();

			for (Articolo articolo : articoli) {
				sb.append("Nome: ").append(articolo.getNome()).append(", Costo: ").append(articolo.getCosto())
						.append(", Quantità: ").append(articolo.getQuantita()).append(", Categoria: ")
						.append(articolo.getCategoria()).append("\n");
			}

			textArea.setText(sb.toString());
		} else {
			textArea.setText("Nessuna lista della spesa corrente disponibile.");
		}
	}

	public void salvaSuFile() throws IOException, NomeVuotoException, QuantitaNegativaException, NomeStringaException,
			FileNotFoundException {
		textArea.setText("Inserisci il nome del file per salvare la lista: ");
		String nomeFile = JOptionPane.showInputDialog(this, "Inserisci il nome del File:", "Inserimento Nome",
				JOptionPane.PLAIN_MESSAGE);
		if (nomeFile == null) {
			textArea.setText("Nome file vuoto. ");
			return;
		}
		File file = new File(nomeFile);

		if (!file.exists()) {
			textArea.setText("File non trovato.");
			return;
		}

		float prezzoTotale = 0;
		for (Articolo articolo : listaCorrente.getArticoli()) {
			prezzoTotale = prezzoTotale + (articolo.getCosto() * articolo.getQuantita());
		}

		try (PrintWriter writer = new PrintWriter(file)) {
			writer.println("Questa è la lista di " + listaCorrente.getNome());
			for (Articolo articolo : listaCorrente.getArticoli()) {
				writer.println("Nome: " + articolo.getNome() + ", Costo: " + articolo.getCosto() + ", Quantità: "
						+ articolo.getQuantita() + ", Categoria: " + articolo.getCategoria());
			}
			writer.println("Prezzo totale: " + prezzoTotale);
			textArea.setText("Lista della spesa salvata su file: " + nomeFile);
		}
	}

	public void creaLista() {
		String nome = JOptionPane.showInputDialog(this, "Crezione della Lista");
		if (nome == null || nome.isEmpty()) {
			textArea.setText("Nome vuoto lista non creata. ");
			return;
		}
		for(int i = 0; i < tutteLeListe.size() ; i++)
		{
			if (tutteLeListe.get(i).getNome().equals(nome))
			{
				textArea.setText("Errore : Lista già esistente, lista non creata");
				return;
			}
		}
		ListaSpesa listaSpesa = new ListaSpesa(nome);
		tutteLeListe.add(listaSpesa);
		listaCorrente = listaSpesa;
		JOptionPane.showMessageDialog(this, "Nuova lista della spesa \"" + nome + "\" creata con successo.");
		textArea.setText("Nuova lista della spesa \"" + nome + "\" creata con successo.");
	}

	public void visualizzaTutteLeListe() {
		if (listaCorrente == null) {
			textArea.setText("Non ci sono liste di spesa create. Per visualizzare le liste creane almeno una.");
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("Tutte le liste di spesa:\n\n");

			for (int i = 0; i < tutteLeListe.size(); i++) {
				ListaSpesa listaSpesa = tutteLeListe.get(i);
				sb.append((i + 1)).append(". ").append(listaSpesa.getNome()).append("\n");

				List<Articolo> articoli = listaSpesa.getArticoli();

				for (Articolo articolo : articoli) {
					sb.append("    Nome: ").append(articolo.getNome()).append(", Costo: ").append(articolo.getCosto())
							.append(", Quantità: ").append(articolo.getQuantita()).append(", Categoria: ")
							.append(articolo.getCategoria()).append("\n");
				}
				sb.append("\n");
			}

			textArea.setText(sb.toString());
		}
	}

	public void CaricaDaFile() throws IOException, NomeVuotoException, QuantitaNegativaException, NomeStringaException,
			FileNotFoundException, CostoException, NomeEsistenteException {
		textArea.setText("Inserisci il nome del file per salvare la lista: ");
		String nomeFile = JOptionPane.showInputDialog(this, "Inserisci il nome del File:", "Inserimento Nome",
				JOptionPane.PLAIN_MESSAGE);
		if (nomeFile == null) {
			textArea.setText("Nome file vuoto. ");
			return;
		}
		File file = new File(nomeFile);

		if (!file.exists()) {
			textArea.setText("File non trovato.");
			return;
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String nomeLista = JOptionPane.showInputDialog(this, "Inserisci il nome della lista:",
					"Inserimento nome lista", JOptionPane.PLAIN_MESSAGE);
			ListaSpesa nuovaLista = new ListaSpesa(nomeLista);
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("Nome: ")) {
					String[] parts = line.split(", ");
					String nome = parts[0].substring(6);
					float costo = Float.parseFloat(parts[1].substring(7));
					int quantita = Integer.parseInt(parts[2].substring(10));
					String categoria = parts[3].substring(11);
					Articolo articolo = new Articolo(nome, costo, categoria, quantita);
					nuovaLista.aggiungi(articolo);
				}
			}

			tutteLeListe.add(nuovaLista);
			try {
				listaCorrente = nuovaLista;
			} catch (Exception e) {
				e.printStackTrace();
			}
			textArea.setText("Lista della spesa caricata con successo da " + nomeFile);
		}
	}

	public void trovaCategoria(ListaSpesa listaSpesa) {
		if (listaCorrente == null) {
			textArea.setText("Non ci sono liste di spesa create. Non puoi cercare una categoria, crea una lista.");
		}

		else {
			List<Articolo> articoliTrovati = new ArrayList<>();
			textArea.setText("Nome categoria da cercare: ");
			String trova = JOptionPane.showInputDialog(this, "Inserisci il nome della Categoria:",
					"Inserimento Categoria", JOptionPane.PLAIN_MESSAGE);
			boolean categoriaTrovata = false;

			for (Articolo articolo : listaSpesa.getArticoli()) {
				if (articolo.getCategoria().equals(trova)) {
					articoliTrovati.add(articolo);
					categoriaTrovata = true;
				}
			}

			if (categoriaTrovata) {
				listaSpesa.trovaCategoria(trova);
				textArea.setText("Gli articoli nella categoria " + trova + " sono: \n");

				for (Articolo articolo : articoliTrovati) {
					textArea.append("- " + articolo.getNome() + "\n");
				}
			} else {
				textArea.setText("Categoria non trovata nella lista della spesa.");
			}
		}
	}

	public void scegliLista() {
		if (listaCorrente == null) {
			textArea.setText("Non ci sono liste di spesa create. Creane una nuova.");
			creaLista();
		} else {
			textArea.setText("Tutte le liste di spesa:\n");
			for (int i = 0; i < tutteLeListe.size(); i++) {
				textArea.append((i + 1) + ". " + tutteLeListe.get(i).getNome() + "\n");
			}

			String sceltaInput = JOptionPane.showInputDialog(this, "Inserisci il numero della tua scelta:",
					"Inserimento scelta", JOptionPane.PLAIN_MESSAGE);

			int scelta = Integer.parseInt(sceltaInput);
			if (scelta > 0 && scelta <= tutteLeListe.size()) {
				listaCorrente = tutteLeListe.get(scelta - 1);
				textArea.setText("Hai scelto la lista: " + listaCorrente.getNome());
			} else {
				textArea.setText("Scelta non valida. Riprova.");
			}
		}

	}

	public void prezzo(ListaSpesa listaSpesa) {
		if (listaCorrente == null) {
			textArea.setText("Non ci sono liste di spesa create. Creane una nuova.");
		} else {
			float prezzo = 0;
			for (Articolo articolo : listaSpesa.getArticoli()) {
				prezzo = prezzo + (articolo.getCosto() * articolo.getQuantita());
			}
			textArea.setText("Prezzo totale: " + prezzo);
		}
	}

	public void rimuoviCategoria(ListaSpesa listaSpesa) {
		if (listaCorrente == null) {
			textArea.setText("Non ci sono liste di spesa create. Non puoi eliminare una categoria.");
		} else {
			textArea.setText("Nome categoria da rimuovere: ");
			String rimuovi = JOptionPane.showInputDialog(this, "Inserisci la categoria da rimuovere:",
					"Inserimento categoria da rimuovere", JOptionPane.PLAIN_MESSAGE);
			boolean categoriaTrovata = false;

			for (Articolo articolo : listaSpesa.getArticoli()) {
				if (articolo.getCategoria().equals(rimuovi)) {
					articolo.setCategoria("non categorizzati");
					categoriaTrovata = true;
				}
			}

			if (categoriaTrovata) {
				listaSpesa.rimuoviCategoria(rimuovi);
				textArea.setText("Categoria rimossa dalla lista della spesa e articoli aggiornati.");
			} else {
				textArea.setText("Categoria non trovata nella lista della spesa.");
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		});
	}
}
