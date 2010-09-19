package classes;

import ourExceptions.ArgumentInvalidException;

/**
 * Classe que implementa o objeto Texto do Blogao.
 * Classe que e responsavel pela manipulacao e edicao de um texto.
 * @author Tiago Brasileiro
 *
 */
public class Texto {
	private String titulo;
	private String corpo;
	
	/**
	 * Contrutor da classe, atribui os paramentos(apos serem validados)
	 * aos atributos corpo e titulo da classe.
	 * @param titulo
	 * @param corpo
	 * @throws ArgumentInvalidException 
	 */
	public Texto(String titulo, String corpo) throws ArgumentInvalidException{
		if(validaCorpo(corpo))
			this.corpo = corpo;
		else
			throw new ArgumentInvalidException("Corpo inválido");
		
		if(validaTitulo(titulo))
			this.titulo = titulo.trim();
		else
			throw new ArgumentInvalidException("Titulo inválido");
	}
	/**
	 * metodo responsavel pela validacao do parametro titulo,
	 * nao sera aceita entradas "null's"
	 * @param titulo
	 * @return um booleno referente a validacao ou nao da entrada.
	 */
	private boolean validaTitulo(String titulo) {
		if(titulo == null)
			return false;
		return true;
	}
	/**
	 * metodo responsavel pela validacao do parametro corpo,
	 * nao sera aceita entradas "null's" e vazias.
	 * @param corpo
	 * @return um booleno referente a validacao ou nao da entrada.
	 * @throws ArgumentInvalidException 
	 */
	private boolean validaCorpo(String corpo) {
		if(corpo == null || corpo.trim().equals(""))
			return true;
		else
			return true;
	}
	/**
	 * Metodo que retorna o titulo do texo.
	 * @return
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * Modifica o titulo.
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * retorna o corpo do texto.
	 * @return
	 */
	public String getCorpo() {
		return corpo;
	}
	/**
	 * Modifica o corpo do texto.
	 * @param corpo
	 */
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}
	/**
	 * retorna todo o texto, ou seja, a concatenacao do titulo com o corpo
	 * @return
	 */
	public String getTextoCompleto() {
		return titulo + "\n" + corpo;
	}
	

}
