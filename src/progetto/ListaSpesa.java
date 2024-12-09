package progetto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import eccezioni.NomeEsistenteException;

public class ListaSpesa implements Iterable<Articolo> {
    private String nome;
    private List<Articolo> articoli;

    /**
     * costruttore ListaSpesa
     * @param nome
     */
    
    public ListaSpesa(String nome) {
        this.nome = nome;
        this.articoli = new ArrayList<>();
    }
    
    /**
     * Il metodo aggiungi utilizzato come appoggio in aggiungiArticolo se l'articolo esiste già eccezione
     * @param articolo
     */

    public void aggiungi(Articolo articolo) throws NomeEsistenteException{
		for (int i=0; i< articoli.size(); i = i+1)
    	{
			if (articolo.getNome().equals(articoli.get(i).getNome()))
				throw new NomeEsistenteException(""); 
    	}
        articoli.add(articolo);
    }
       
   
    /**
     * Rimuove la categoria in input 
     * @param categoriaDaRimuovere viene inserito come parametro la categoria da rimuovere la cerca e in caso la trova la rimuove
     */

    
    public boolean rimuoviCategoria(String categoriaDaRimuovere) {
        boolean categoriaTrovata = false;

        for (Articolo articolo : articoli) {
            if (articolo.getCategoria().equals(categoriaDaRimuovere)) {
                articolo.setCategoria("non categorizzati");
                categoriaTrovata = true;
            }
        }

        return categoriaTrovata;
    }
    
    
    /**
     * Il metodo trovaCategoria serve per creare un arrayList e va in 
     * ricerca di tutti gli articoli con la categoria data come parametro,poi stampa il nome di tutti gli articoli con quella categoria.
     * @param categoriaDaTrovare
     * @return l'arrayList contentente tutti gli articoli
     */
    
    public List<Articolo> trovaCategoria(String categoriaDaTrovare) {
        List<Articolo> articoliTrovati = new ArrayList<>();

        for (Articolo articolo : articoli) {
            if (articolo.getCategoria().equals(categoriaDaTrovare)) 
                articoliTrovati.add(articolo);
            
        }
       return articoliTrovati;
		
    }
    
    /**
     * Serve a ottenere tutti gli articoli prensenti nella lista
     * @return Articoli
     */
    public List<Articolo> getArticoli() {
        return articoli;
    }

    /**
     * Serve a ottnere il nome della lista della spesa
     * @return nome(della lista)
     */
    public String getNome() {
        return nome;
    }

    @Override
    public Iterator<Articolo> iterator() {
        return articoli.iterator();
    }

    
    /**
     * Rimuove l'articolo 
     * @param articolo
     */
    public void rimuoviArticolo(Articolo articolo) {
        articoli.remove(articolo);
    }
    
    /**
     * Calcola il prezzo totale della lista della spesa con un for each
     * @return prezzo totale della lista della spesa
     */
    public float prezzo() {
        float prezzo = 0;
        for (Articolo articolo : articoli) {
            prezzo = prezzo + (articolo.getCosto() * articolo.getQuantita());
        }
        
        return prezzo;
    }   
    
}
