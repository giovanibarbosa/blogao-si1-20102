package classes.func.usuario;

/**
 * Facade de alteracao do perfil. Necessarios para os testes US3
 * @author Tiago
 *
 */
public class FacadeUserStore3 {
	private Perfil perfil;
	private Usuario user;
	
	//TODO CARREGA TODOS OS DADOS DO BD
	public void loadData(){}
	
	//TODO METODO QUE LOGA O USUARIO
	public void logon(){}
	
	//VERIFICAR SE ESSE 'ID' VAI SER UM DOUBLE MESMO
	public void getProfileInformationBySessionId(double id, String login, String senha, String nome_exibicao, String email,
			String sexo, String dataNasc, String endereco, String interesses, String quem_sou_eu,
			String filmes, String musicas, String livros){}
	
	//TODO SETA TODAS AS VARIAVEIS DO PERFIL E TESTA-AS.
	public void changeProfileInformation(String login, String senha, String nome_exibicao, String email,
			String sexo, String dataNasc, String endereco, String interesses, String quem_sou_eu,
			String filmes, String musicas, String livros){}
	
	//TODO METODO QUE DESLOGA O USUARIO.
	public void logoff(){}
	
	//TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {}
	
}
