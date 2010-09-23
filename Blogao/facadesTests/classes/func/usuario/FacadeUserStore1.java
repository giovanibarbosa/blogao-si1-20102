package classes.func.usuario;

import classes.Email;
import classes.Login;
import classes.Senha;
import interfaces.Logavel;
import persistencia.daos.*;


/**
 * Facade do Perfil. Utilizada para chamar os metodos necessarios para os testes.
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public class FacadeUserStore1 {
	
	private Perfil perfil1;
	private Logavel user1;
	private LogaveisDAO logDAO = new LogaveisDAO();
	
	
	//TODO APAGAR OS DADOS SALVOS
	public void cleanPersistence() {}
	
	//TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {}
	
	//TODO FAZER ESTE METODO
	//Precisa-se do BD para tal!
	public void getProfileInformation(String login, String senha, String nome_exibicao, String email,
			String sexo, String dataNasc, String endereco, String interesses, String quem_sou_eu,
			String filmes, String musicas, String livros) {
		
		
	}
	
	//TODO Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao, String email,
			String sexo, String dataNasc, String endereco, String interesses, String quem_sou_eu,
			String filmes, String musicas, String livros) throws Exception {
		
		
		Login log = new Login(login);
		Senha sen = new Senha(senha);
		Email mail = new Email(email);
		
		
		perfil1 = new Perfil();	
		perfil1.setEmail(mail);
		user1 = new Usuario(log, sen, perfil1);
		
		
		perfil1.setSexo(sexo);
		perfil1.setDataDeNascimento(dataNasc);
		logDAO.criar(user1);
	}
	
	
	
	

}
