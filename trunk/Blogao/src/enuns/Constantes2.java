package enuns;

public enum Constantes2 {
	
	SEXO_INVALIDO ("Sexo inválido"), 
	SESSAO_INVALIDA ("Sessão inválida"),
	LOGIN_EXISTENTE ("Login existente"),
	USUARIO_INEXISTENTE ("Usuário inexistente"),
	USUARIO_LOGADO ("Usuário já logado"),
	INDICE_INVALIDO ("Índice inválido"),
	INDICE_INCORRETO ("Índice incorreto"),
	ANNOUNCEMENT_INVALIDO ("Announcement inválido"),
	LOGIN_OU_SENHA_INVALIDO ("Login ou senha inválido"),
	AUDIO ("audio"),
	VIDEO ("video"),
	ATRIBUTO_INVALIDO ("Atributo Inválido"),
	POST_INVALIDO ("Post inválido"),
	BLOG_INVALIDO ("Blog inválido"),
	EMAIL_EXISTENTE ("Email existente"),
	LOGIN_INVALIDO ("Login inválido"),
	SENHA ("senha"),
	EMAIL ("email"),
	LOGIN ("login"),
	COMENTARIO_INVALIDO ("Comentário inválido"),
	COMENTARIO_INEXISTENTE ("Comentario inexistente");
	
	
	private String name;
	
	
	Constantes2 (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getValue() {
		return Integer.valueOf(name);
	}
}
