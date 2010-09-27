package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;

import classes.gerenciadores.GerenciadorDeDados;

/**
 * @author Tiago
 */
public class FacadeUserStore11 {
	
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();
	
	// CARREGA TODOS OS DADOS DO BD
	public void loadData() throws FileNotFoundException {
		gerenteDados.loadData();
	}
	
	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		return gerenteDados.getGerenteSessoes().logon(login, senha);

	}
	
	// CRIA O BLOG
	public String createBlog(String idSessao, String titulo, String descricao)
			throws ArgumentInvalidException, PersistenceException, IOException,
			UserInvalidException {

		return gerenteDados.getGerenteBlogs().createBlog(idSessao, titulo,
				descricao);
	}
	
	//TODO METODO QUE DETELA O PROFILE DO USUARIO
	public void deleteProfile(String sesionId){
		
	}
	
	//METODO QUE RETORNA O NUMERO DE POSTS DO BLOG.
	public int getNumberOfPosts(String blogId) throws ArgumentInvalidException{
		return gerenteDados.getGerenteBlogs().getBlog(blogId).getNumeroDePosts();
		
	}
	
	// TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {
	}

}
