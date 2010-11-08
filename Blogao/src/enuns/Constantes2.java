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
	COMENTARIO_INEXISTENTE ("Comentario inexistente"),
	ATRIBUTO_INVALIDO2 ("Atributo inválido"),
	TITULO_INVALIDO ("Você deve especificar um título para o blog"),
	EMAIL_INVALIDO ("Email inválido"),
	TITULO_OBRIGATORIO ("Título obrigatório"),
	SENHA_INVALIDA ("Senha inválida"),
	DATA_INVALIDA ("Data inválida"),
	MIDIA_INVALIDA ("Midia inválida"),
	DADO_INVALIDO ("Dado inválido"),
	ANNOUNCEMENT_CADASTRADO ("Announcement já cadastrado"),
	USUARIO_NAO_PODE_SER_REMOVIDO ("O usuário não pode ser removido"),
	USUARIO_EXISTENTE ("Usuário existente"),
	BLOG_NAO_REMOVIDO ("O blog não pode ser removido"),
	BLOG_INEXISTENTE ("Blog inexistente"),
	BLOG_NAO_CRIADO ("O blog nao pode ser criado"),
	BLOG_NAO_PODE_SER_ATUALIZADO ("O blog não pode ser atualizado"),
	COMENTARIO_NAO_CRIADO ("O comentario não pode ser criado"),
	COMENTARIO_NAO_PODE_SER_REMOVIDO ("O comentario não pode ser removido"),
	POST_NAO_PODE_SER_CRIADO ("O post não pode ser criado"),
	POST_NAO_PODE_SER_REMOVIDO ("O post não pode ser removido"),
	POST_NAO_PODE_SER_ATUALIZADO ("O post não pode ser atualizado"),
	EMAIL_NAO_VALIDO ("O email não é válido"),
	EMAIL_INEXISTENTE ("Email inexistente");


	
	
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
