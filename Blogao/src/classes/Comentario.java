package classes;

import ourExceptions.ArgumentInvalidException;

/**
 * Classe que inicializa um Comentario.
 * 
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 * @colaborator Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @colaborator Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @colaborator Giovani Barbosa - giovanibarbosa@gmail.com
 * 
 */
public class Comentario {

	private String id;
	private Texto corpoComentario;

	/**
	 * Contrutor da classe Comentario. Recebe uma string como texto do
	 * comentario.
	 * 
	 * @param String
	 *            corpo do Comentario
	 * @throws ArgumentInvalidException 
	 */
	public Comentario(String corpo) throws ArgumentInvalidException {
		corpoComentario = new Texto(corpo);
		setId(gerarId());

	}

	/**
	 * Gera o id da classe.
	 * 
	 * @return
	 */
	private String gerarId() {
		return String.valueOf(this.hashCode());
	}

	/**
	 * @return o texto relativo ao comentario.
	 */
	public Texto getCorpoComentario() {
		return corpoComentario;
	}

	/**
	 * Seta o id do Comentario.
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return o id do comnetario.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Metodo que verifica a igualdade entre objetos Blog.
	 * 
	 * @return True caso os objetos sejam iguais
	 */
	@Override
	public boolean equals(Object objeto) {
		if (!(objeto instanceof Comentario)) {
			return false;
		}
		Comentario outra = (Comentario) objeto;
		return getId().equals(outra.getId());

	}

}
