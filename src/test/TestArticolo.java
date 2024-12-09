package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eccezioni.CostoException;
import eccezioni.NomeStringaException;
import eccezioni.NomeVuotoException;
import eccezioni.QuantitaNegativaException;

import static org.junit.jupiter.api.Assertions.assertEquals;


import progetto.Articolo;


public class TestArticolo {
    private Articolo articolo1;
    
    @BeforeEach
    public void setUp() throws CostoException, QuantitaNegativaException, NomeVuotoException, NomeStringaException {
        articolo1 = new Articolo("Latte", 1.20f, "Alimentari", 2);
    }
    
    @Test
    public void test() {
        assertEquals("Latte", articolo1.getNome());
        assertEquals(1.2f, articolo1.getCosto(), 0.001);
        assertEquals("Alimentari", articolo1.getCategoria());
        assertEquals(2, articolo1.getQuantita());
    }
}
