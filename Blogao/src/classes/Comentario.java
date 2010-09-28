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
	private String idSessaoDono;
	private String corpoComentario;

	/**
	 * Contrutor da classe Comentario. Recebe uma string como texto do
	 * comentario.
	 * 
	 * @param {@link String}
	 *            corpo do Comentario
	 * @throws {@link ArgumentInvalidException}
	 * 			  caso o comentario seja invalido 
	 */
	public Comentario(String idSessao, String corpo) throws ArgumentInvalidException {
		corpoComentario = corpo;
		idSessaoDono = idSessao;
		setId(gerarId());

	}

	/**
	 * Metodo acessador de id de sessao do dono
	 * @return {@link String} id da sessao do dono
	 */
	public String getIdSessaoDono() {
		return idSessaoDono;
	}


	/**
	 * Metodo acessador do corpo de um comentario
	 * @return {@link String} corpo
	 */
	public String getCorpoComentario() {
		return corpoComentario;
	}

	/**
	 * Metodo modificador que seta um id do comentario
	 * @param {@link String} comentarioId
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Metodo acessador de id do comentario
	 * @return {@link String} comentarioId
	 */
	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Object objeto) {
		if (!(objeto instanceof Comentario)) {
			return false;
		}
		Comentario outra = (Comentario) objeto;
		return getId().equals(outra.getId());
	}
	

	private String gerarId() {
		return String.valueOf(this.hashCode());
	}

}
