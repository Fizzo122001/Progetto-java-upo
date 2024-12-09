package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eccezioni.CostoException;
import eccezioni.NomeEsistenteException;
import eccezioni.NomeStringaException;
import eccezioni.NomeVuotoException;
import eccezioni.QuantitaNegativaException;
import progetto.ListaSpesa;
import progetto.Articolo;
import progetto.GestioneListe;


public class TestGestioneListe {


    private ListaSpesa listaSpesa;
    @BeforeEach
    public void setUp() {
        listaSpesa = new ListaSpesa("Spesa Settimanale");

    }
    
    @Test
    public void testAggiungiLista() throws NomeEsistenteException {
        GestioneListe gestioneListe = new GestioneListe();
		gestioneListe.aggiungiLista(listaSpesa);
		 assertEquals(1, gestioneListe.size());  
    }
    
    @Test
    public void testRimuoviLista() throws NomeEsistenteException {
        GestioneListe gestioneListe = new GestioneListe();
		gestioneListe.aggiungiLista(listaSpesa);
		assertEquals(1, gestioneListe.size());
		gestioneListe.rimuoviLista(listaSpesa);
		assertEquals(0, gestioneListe.size());
		
    }
    
    @Test
    public void testTrovaListaPerNome() throws NomeEsistenteException {
        ListaSpesa listaSpesa = new ListaSpesa("Lista1"); 
        ListaSpesa listaSpesa2 = new ListaSpesa("");

        GestioneListe gestioneListe = new GestioneListe();
        gestioneListe.aggiungiLista(listaSpesa);
        gestioneListe.aggiungiLista(listaSpesa2);

        ListaSpesa result = gestioneListe.trovaListaPerNome("Lista1");
        assertNotNull(result); 
        assertEquals("Lista1", result.getNome()); 
    }
    
    @Test
    public void testRimuoviCategoria() throws CostoException, QuantitaNegativaException, NomeEsistenteException, NomeVuotoException, NomeStringaException {
        ListaSpesa listaSpesa1 = new ListaSpesa("Lista1");
        Articolo articolo1 = new Articolo("Latte", 1.20f, "Alimentari", 2);
        listaSpesa1.aggiungi(articolo1);
        ListaSpesa listaSpesa2 = new ListaSpesa("Pane");
        Articolo articolo2 = new Articolo("Sapone", 1, "Igiene", 1);
        Articolo articolo3 = new Articolo("Pasta", 1, "Alimentari", 3);
        listaSpesa2.aggiungi(articolo2);
        listaSpesa2.aggiungi(articolo3);
        GestioneListe gestioneListe = new GestioneListe();
        gestioneListe.aggiungiLista(listaSpesa1);
        gestioneListe.aggiungiLista(listaSpesa2);
        boolean result = gestioneListe.rimuoviCategoria("Alimentari"); 
        assertEquals(true, result);
        
    }
    
    @Test
    public void testTrovaCategoria() throws CostoException, QuantitaNegativaException, NomeEsistenteException, NomeVuotoException, NomeStringaException {
        ListaSpesa listaSpesa1 = new ListaSpesa("Lista1");
         Articolo articolo1 = new Articolo("Latte", 1.20f, "Alimentari", 2);
        listaSpesa1.aggiungi(articolo1);
        ListaSpesa listaSpesa2 = new ListaSpesa("Pane");
        Articolo articolo2 = new Articolo("Sapone", 1, "Igiene", 1);
        Articolo articolo3 = new Articolo("Pasta", 1, "Alimentari", 3);
        listaSpesa2.aggiungi(articolo2);
        listaSpesa2.aggiungi(articolo3);
        GestioneListe gestioneListe = new GestioneListe();
        gestioneListe.aggiungiLista(listaSpesa1);
        gestioneListe.aggiungiLista(listaSpesa2);
        boolean result = gestioneListe.trovaCategoria("Alimentari"); 
        assertEquals(true, result);
        
    }
    
}
