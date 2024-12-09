/**
* …………
*
* @author Marco Chiappa 20047162
* @author Beatrice Capella 20034984
* classe Articolo per definire l'oggetto Articolo
*/

package progetto;

import eccezioni.CostoException;
import eccezioni.NomeStringaException;
import eccezioni.NomeVuotoException;
import eccezioni.QuantitaNegativaException;

public class Articolo {
	private String nome;
	private float costo;
	private String categoria;
	private int quantita;

	/**
	 * Costruttore classe Articolo
	 * Assegna al costrutture i valori di nome,costo,categoria settati nei Setter
	 * 
	 * @param nome
	 * @param costo
	 * @param categoria
	 * @param quantita
	 * @throws CostoException
	 * @throws QuantitaNegativaException
	 * @throws NomeVuotoException
	 * @throws NomeStringaException
	 * @throws CostoStringaException
	 * 
	 */

	public Articolo(String nome, float costo, String categoria, int quantita) throws CostoException,
			QuantitaNegativaException, NomeVuotoException, NomeStringaException {
		this.setNome(nome);
		this.setCosto(costo);
		this.setCategoria(categoria);
		this.setQuantita(quantita);
	}

	/**
	 * Imposta valore alla categoria
	 * 
	 * @param categoria valore della categoria inserito
	 */

	public void setCategoria(String categoria) {
		this.categoria = categoria;
		if (categoria.isEmpty() || categoria == null)
			this.categoria = "non categorizzati";
	}

	/**
	 * Imposta valore al costo se costo<0 eccezione
	 * 
	 * @param costo valore del costo inserito
	 * @throws CostoStringaException
	 */

	public void setCosto(float costo) throws CostoException {
		if (costo < 0)
			throw new CostoException("");
		this.costo = costo;
	}

	/**
	 * Imposta il valore al nome se nome vuoto o il nome è un numero/contiene un numero eccezione
	 * 
	 * @param nome
	 * @throws NomeVuotoException
	 * @throws NomeStringaException
	 */
	public void setNome(String nome) throws NomeVuotoException, NomeStringaException {
		if (nome.isEmpty() || nome == null)
			throw new NomeVuotoException("");

		if (!nome.matches("[a-zA-ZàèìòùÀÈÌÒÙáéíóúÁÉÍÓÚäëïöüÄËÏÖÜâêîôûÂÊÎÔÛçÇ ]+"))
			throw new NomeStringaException("");

		this.nome = nome;
	}

	/**
	 * Imposta valore alla quantità se quantita < 0 eccezione
	 * 
	 * @param quantità valore della quantità
	 */

	public void setQuantita(int quantita) throws QuantitaNegativaException {
		String quant = "" + quantita;
		if (quantita < 0)
			throw new QuantitaNegativaException("");

		if (quant.isEmpty() || quant == null)
			this.quantita = 1;
		else
			this.quantita = quantita;
	}

	/**
	 * Permette di ottenere e di usare il categoria dell'articolo nelle altre classi
	 * 
	 * @param getCosto Ritorna la categoria dell'articolo
	 */

	public String getCategoria() {
		return categoria;
	}

	/**
	 * Permette di ottenere e di usare il costo dell'articolo nelle altre classi
	 * 
	 * @param getCosto Ritorna il costo dell'articolo
	 */

	public float getCosto() {
		return costo;
	}

	/**
	 * Permette di ottenere e di usare la quantita dell'articolo nelle altre classi
	 * 
	 * @param getQuantita Ritorna il nome dell'articolo
	 */

	public int getQuantita() {
		return quantita;
	}

	/**
	 * Permette di ottenere e di usare il nome dell'articolo nelle altre classi
	 * 
	 * @param getNome Ritorna il nome dell'articolo
	 */

	public String getNome() {
		return nome;
	}
}
