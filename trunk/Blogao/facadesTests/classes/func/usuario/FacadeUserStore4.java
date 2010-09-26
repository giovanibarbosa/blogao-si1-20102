package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.Blog;
import classes.gerenciadores.GerenciadorDeDados;

/**
 * Facade de Blog. Essa classe chama os metodos necess�rios para o teste.
 * 
 * @author Tiago
 * @author Rodolfo Marinho
 */
public class FacadeUserStore4 {
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();

	// APAGAR OS DADOS SALVOS
	public void cleanPersistence() {
		gerenteDados.cleanPersistence();
	}

	// Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {
		gerenteDados.getGerentePerfis().createProfile(login, senha,
				nome_exibicao, email, sexo, dataNasc, endereco, interesses,
				quem_sou_eu, filmes, musicas, livros);

	}

	public String createBlog(String idSessao, String titulo, String descricao)
			throws ArgumentInvalidException, PersistenceException, IOException, UserInvalidException
			 {
		return gerenteDados.getGerenteBlogs().createBlog(idSessao, titulo, descricao);

	}

	// RETORNA OS ATRIBUTOS DO BLOG.
	public String getBlogInformation(String idBlog, String atributo)
			throws ArgumentInvalidException, FileNotFoundException, PersistenceException {
		Blog blog = gerenteDados.getGerenteBlogs().getBlog(idBlog);
		return gerenteDados.getGerenteBlogs().getAtributo(blog, atributo);
	}

	public String logon(String login, String senha)
			throws PersistenceException, FileNotFoundException,
			ArgumentInvalidException {
		return gerenteDados.getGerenteSessoes().logon(login, senha);

	}

	public void saveData() {
	}

	public void logoff(String idSession) throws Exception {
		gerenteDados.getGerenteSessoes().logoff(idSession);

	}

}
