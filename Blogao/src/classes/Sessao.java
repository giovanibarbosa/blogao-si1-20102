package classes;

public class Sessao {
	
	private String idSessao;
	private String login;
	
	public Sessao(String id, String log) {
		this.idSessao = id;
		this.login = log;
	}
	
	public String getIdSessao() {
		return idSessao;
	}
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
