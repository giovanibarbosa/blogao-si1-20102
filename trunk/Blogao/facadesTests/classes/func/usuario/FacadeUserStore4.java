package classes.func.usuario;

import ourExceptions.ArgumentInvalidException;
import interfaces.Logavel;
import classes.Blog;
import classes.Email;
import classes.Login;
import classes.Senha;
import classes.Texto;

/**
 * Facade de Blog. Essa classe chama os metodos necessários para o teste.
 * @author Tiago
 */
public class FacadeUserStore4 {
	private Blog blog;
	private Perfil perfil1;
	private Logavel user1;
	
	//TODO APAGAR OS DADOS SALVOS
	public void cleanPersistence() {}
	
	//TODO Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao, String email,
			String sexo, String dataNasc, String endereco, String interesses, String quem_sou_eu,
			String filmes, String musicas, String livros) throws Exception {
		
		
		Login log = new Login(login);
		Senha sen = new Senha(senha);
		Email mail = new Email(email);
		
		
		user1 = new Usuario(log, sen);
		perfil1 = new Perfil(user1, mail);	
		perfil1.setSexo(sexo);
		perfil1.setDataDeNascimento(dataNasc);
	}
	
	//TODO CRIA O BLOG
	public void createBlog(String titulo, String descricao) throws ArgumentInvalidException{
		Texto desc = new Texto(descricao);
		
		blog = new Blog(titulo, desc);
		blog.setDescricao(desc);
		blog.setTitulo(titulo);
	}
	
	//TODO RETORNA OS ATRIBUTOS DO BLOG.
	public void getBlogInformation(String titulo, String descricao){}
	
	//TODO METODO QUE LOGA O USUARIO
	public void logon(){}
	
	
}
