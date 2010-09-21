package classes.func.usuario;

/**
 * Facade do Gerenciamento de Secao. Usado para os testes.
 * @author Tiago
 */
public class FacadeUserStore2 {
	private Perfil perfil;
	private Usuario user;
	
	//TODO CARREGA TODOS OS DADOS DO BD
	public void loadData(){}
	
	//TODO METODO QUE LOGA O USUARIO
	public void logon(){}
	
	//TODO METODO QUE VERIFICA SE O USUARIO JA ESTA LOGADO
	public void isUserLogged(){}
	
	//VERIFICAR SE ESSE 'ID' VAI SER UM DOUBLE MESMO
	public void getProfileInformationBySessionId(double id, String login, String senha, String nome_exibicao, String email,
			String sexo, String dataNasc, String endereco, String interesses, String quem_sou_eu,
			String filmes, String musicas, String livros){}
	
	//TODO METODO QUE DESLOGA O USUARIO.
	public void logoff(){}
	
}
