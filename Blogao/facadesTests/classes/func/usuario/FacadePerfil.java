package classes.func.usuario;

import classes.Email;
import classes.Login;
import classes.Senha;
import interfaces.Logavel;


/**
 * Facade do Perfil. Utilizada para chamar os metodos necessarios para os testes.
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public class FacadePerfil {
	
	private Perfil perfil1;
	private Logavel user1;
	
	
	//TODO APAGAR OS DADOS SALVOS
	public void cleanPersistence() {}
	
	//TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {}
	
	//TODO FAZER ESTE METODO
	public void getProfileInformation() {}
	
	public void createProfile(String login, String senha, String nome_exibicao, String email,
			String sexo, String dataNasc, String endereco, String interesses, String quem_sou_eu,
			String filmes, String musicas, String livros) throws Exception {
		
		
		Login log = new Login(login);
		Senha sen = new Senha(senha);
		Email mail = new Email(email);
		
		user1 = new Usuario(log, sen);
		perfil1 = new Perfil(user1, mail);		
	}
	
	
	
	

}
