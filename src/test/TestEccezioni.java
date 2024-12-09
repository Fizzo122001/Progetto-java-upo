package test;

import eccezioni.CostoException;
import eccezioni.NomeEsistenteException;
import eccezioni.NomeStringaException;
import eccezioni.NomeVuotoException;
import eccezioni.QuantitaNegativaException;
import progetto.Articolo;
import progetto.GestioneListe;
import progetto.ListaSpesa;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestEccezioni {

	@Test
	void TestEccezioniArticolo()
			throws CostoException, QuantitaNegativaException, NomeVuotoException, NomeStringaException {
		Articolo latte = new Articolo("Latte", 2, "Alimentari", 2);
		assertThrows(CostoException.class, () -> {
			latte.setCosto(-3);
		});

		assertThrows(QuantitaNegativaException.class, () -> {
			latte.setQuantita(-3);
		});

	}

	@Test
	void TestEccezioniListaSpesa() throws CostoException, QuantitaNegativaException, NomeEsistenteException,
			NomeVuotoException, NomeStringaException {
		Articolo latte = new Articolo("Latte", 2, "Alimentari", 2);
		Articolo pane = new Articolo("pane", 1.5f, "Alimentari", 2);
		Articolo detersivo = new Articolo("detersivo", 4.5f, "home", 2);
		Articolo pane2 = new Articolo("pane", 4.5f, "home", 2);
		ListaSpesa beatrice = new ListaSpesa("Beatrice");
		beatrice.aggiungi(detersivo);
		beatrice.aggiungi(pane);
		beatrice.aggiungi(latte);
		assertEquals(3, beatrice.getArticoli().size());
		assertThrows(NomeEsistenteException.class, () -> {
			beatrice.aggiungi(pane2);
		});
	}

	@Test
	void TestEccezioniGestioniSpesa() throws CostoException, QuantitaNegativaException, NomeEsistenteException,
			NomeVuotoException, NomeStringaException {
		ListaSpesa listaSpesa1 = new ListaSpesa("Lista1");
        Articolo articolo1 = new Articolo("Latte", 1.20f, "Alimentari", 2);
        listaSpesa1.aggiungi(articolo1);
        ListaSpesa listaSpesa2 = new ListaSpesa("Pane");
        Articolo articolo2 = new Articolo("Sapone", 1, "Igiene", 1);
        Articolo articolo3 = new Articolo("Pasta", 1, "Alimentari", 3);
        ListaSpesa listaSpesa3 = new ListaSpesa("Pane");
        listaSpesa2.aggiungi(articolo2);
        listaSpesa2.aggiungi(articolo3);
        GestioneListe gestioneListe = new GestioneListe();
        gestioneListe.aggiungiLista(listaSpesa1);
        gestioneListe.aggiungiLista(listaSpesa2);
        assertThrows(NomeEsistenteException.class , () ->{
        	gestioneListe.aggiungiLista(listaSpesa3);
        });
        
       

	}

}
