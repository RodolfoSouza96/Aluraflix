package br.com.aluraflix.aluraflix.config.validacao;

public class ExceptionNaoModificar extends Exception{

	private static final long serialVersionUID = 1L;

	public ExceptionNaoModificar() {
		super();
	}
	
	public ExceptionNaoModificar(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ExceptionNaoModificar(String message) {
		super(message);
	}
	
	public ExceptionNaoModificar(Throwable cause) {
		super(cause);
	}

}
