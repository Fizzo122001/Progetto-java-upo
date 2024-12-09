package eccezioni;


public class QuantitaNegativaException extends Exception {
	private static final long serialVersionUID = 1L;
	public QuantitaNegativaException(String message) {
        super(message);
    }
}
