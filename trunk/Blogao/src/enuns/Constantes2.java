package enuns;

public enum Constantes2 {
	
	SEXO_INVALIDO ("Sexo inválido"), 
	SESSAO_INVALIDA ("Sessão inválida"),
	LOGIN_EXISTENTE ("Login existente"),
	USUARIO_INEXISTENTE ("Usuário inexistente"),
	INDICE_INVALIDO ("Índice inválido"),
	ANNOUNCEMENT_INVALIDO ("Announcement inválido");
	
	
	private String name;
	
	
	Constantes2 (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
