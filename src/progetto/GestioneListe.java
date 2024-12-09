package progetto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import eccezioni.NomeEsistenteException;

public class GestioneListe implements Iterable<ListaSpesa> {

    private List<ListaSpesa> liste;

    /**
     * Crea un Arraylist dove verranno salvate tutte le liste della spesa
     */
    public GestioneListe() {
        this.liste = new ArrayList<>();
    }
    
    /**
     * Aggiuge una lista se esiste il nome eccezione
     * @param lista
     */
    
    public void aggiungiLista(ListaSpesa lista) throws NomeEsistenteException{
    	for (int i=0; i< liste.size(); i = i+1)
    	{
			if (lista.getNome().equals(liste.get(i).getNome()))
				throw new NomeEsistenteException(""); 
    	}
        this.liste.add(lista);
    }
    /**
     * Rimuove una lista
     * @param lista
     */
    public void rimuoviLista(ListaSpesa lista) {
        this.liste.remove(lista);
    }
    
    /**
     * Il parametro nome servirà nel forEach per cercare nell'arrayList liste la lista della spesa con lo stesso nome
     * @param nome
     * @return la lista se è è presente se no null
     */
    public ListaSpesa trovaListaPerNome(String nome) {
        for (ListaSpesa lista : liste) {
            if (lista.getNome().equals(nome)) {
                return lista;
            }
        }
        return null;
    }
    
    /**
     * Il parametro nome servirà nel forEach per cercare nell'arrayList gli articoli della spesa con lo stesso nome
     * @param nomeCategoria
     * @return la lista se è è presente se no null
     */
    public ListaSpesa trovaCategoriaPerNome(String nomeCategoria) {
        for (ListaSpesa lista : liste) {
            for (Articolo articolo : lista.getArticoli()) {
                if (articolo.getCategoria().equals(nomeCategoria)) {
                    return lista;
                }
            }
        }
        return null;
    }
    
    /**
     * Richiama la funzione rimuovi categoria della lista della spesa fornendo come parametro il nome
     * @param nomeCategoria
     * @return true
     */
    public boolean rimuoviCategoria(String nomeCategoria) {
        for (ListaSpesa lista : liste) {
            lista.rimuoviCategoria(nomeCategoria);
        }
		return true;
    }
    
    /**
     * 
     * @param nomeCategoria
     * @return trovato, in caso la categoria sia trovata quindi torna true else torna false
     */
   
    public boolean trovaCategoria(String nomeCategoria) {
        boolean trovato = false;
        for (ListaSpesa lista : liste) {
            lista.trovaCategoria(nomeCategoria);
            trovato = true;
        }
		return trovato;
    }

    @Override
    public Iterator<ListaSpesa> iterator() {
        return liste.iterator();
    }
    /**
     * fornisce il numero delle liste presenti nell'arrayList
     * @return quante liste ci sono
     */
    public int size() {
        return liste.size();
    }
    /**
     * Cerca l'indice della lista
     * @param index
     * @return l'indice
     */
    public ListaSpesa get(int index) {
        return liste.get(index);
    }
}
