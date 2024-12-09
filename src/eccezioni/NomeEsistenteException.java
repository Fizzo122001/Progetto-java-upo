package eccezioni;

public class NomeEsistenteException extends Exception{
	private static final long serialVersionUID = 1L;
	public NomeEsistenteException(String message) {
		super(message);
	}
}
