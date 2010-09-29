package classes;


/**
 * Classe que inicializa uma {@link Sessao}
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 * @colaborator Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 *
 */
public class Sessao {
	
	private String idSessao;
	private String login;
	
	/**
	 * Construtor do objeto Sessao
	 * @param id {@link String} da Sessao
	 * @param login {@link String}
	 */
	public Sessao(String id, String log) {
		this.idSessao = id;
		this.login = log;
	}
	
	/**
	 * Metodo acessador de id da sessao
	 * @return String idSessao
	 */
	public String getIdSessao() {
		return idSessao;
	}
	
	/**
	 * Metodo acessador do {@link Login}
	 * @return String login
	 */
	public String getLogin() {
		return login;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Sessao))
			return false;
		Sessao sessao = (Sessao) obj;
		return sessao.getIdSessao().equals(this.idSessao);		
	}
	
	@Override
	public String toString() {
		return this.idSessao;
	}
	

}
