package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import persistencia.daos.BlogsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;
import interfaces.Constantes;
import classes.Blog;
import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.GerenciadorDePerfis;
import classes.gerenciadores.GerenciadorDeSessoes;
import classes.gerenciadores.GerenciadorDeBlogs;

/**
 * Facade de alteracoes do blog. Classe necessaria para testes.
 * 
 * @author Tiago
 * @author Rodolfo
 */
public class FacadeUserStore5 {
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

	// RETORNA OS ATRIBUTOS DO BLOG.
	public String getBlogInformation(String idBlog, String atributo)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException {
		return gerenteDados.getGerenteBlogs().getBlogInformation(idBlog, atributo);
	}

	// REALIZA AS MUDANCAS NO BLOG.
	public void changeBlogInformation(String idSessao, String idBlog,
			String atributo, String novoValor) throws PersistenceException,
			ArgumentInvalidException, IOException, UserInvalidException {
		gerenteDados.getGerenteBlogs().changeBlogInformation(idSessao, idBlog,
									atributo, novoValor);
	}
//		
//		String login = gerenteDados.getGerenteSessoes().getLoginPorSessao(idSessao);
//		if (!gerenteDados.getGerenteSessoes().isUserLogged(login)) {
//			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
//		}
//		
		
		
//		blog = blogsDAO.recupera(id);
//		gerenteBlogs.changeBlogInformation(blog, atributo, novoValor);
//		if (!blog.getIdSessao().equals(idSessao))
//			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);

	//}

	// METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException {
		gerenteDados.getGerenteSessoes().logoff(idSession);
	}

	// TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
