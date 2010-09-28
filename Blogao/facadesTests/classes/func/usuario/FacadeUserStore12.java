package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.gerenciadores.GerenciadorDeDados;
/**
 * @author Tiago
 */
public class FacadeUserStore12 {
	
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();
	
	//APAGA OS DADOS SALVOS
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
	
	//METODO QUE BUSCA O PERFIL PELO NOME(MATCH) E RETORNA A LISTA DOS NOMES PERFIS
	public List<String> findProfileByName(String match){
		return gerenteDados.getGerentePerfis().getPerfilPorNome(match);
	}
	
	
	//METODO QUE BUSCA O PERFIL PELO INTERESSE(MATCH) E RETORNA A LISTA DOS NOMES PERFIS
	public List<String> findProfileByInterests(String match){
		return gerenteDados.getGerentePerfis().getPerfilPorInteresse(match);
	}
	
	//METODO QUE BUSCA O PERFIL PELO SEXO(MATCH) E RETORNA A LISTA DOS NOMES PERFIS
	public List<String> findProfileByGender(String match){
		return gerenteDados.getGerentePerfis().getPerfilPorSexo(match);
	}
	
	// CRIA O BLOG
	public String createBlog(String idSessao, String titulo, String descricao)
			throws ArgumentInvalidException, PersistenceException, IOException,
			UserInvalidException {

		return gerenteDados.getGerenteBlogs().createBlog(idSessao, titulo,
				descricao);
	}
	
	//METODO QUE BUSCA BLOGS PELO NOME(MATCH) E RETORNA A LISTA DE NOMES DOS BLOGS
	public List<String> findBlogByName(String match){
		return gerenteDados.getGerenteBlogs().getBlogPorNome(match);
	}
	
	//CRIA O POST
	public String createPost(String idSession, String idBlog, String titulo,
			String texto) throws ArgumentInvalidException,
			PersistenceException, IOException, UserInvalidException {
		
		return gerenteDados.getGerentePosts().createPost(idSession, idBlog, titulo, texto);

	}
	
	//TODO METODO QUE ADD COMETARIO AO POST
	//acho que já foi implementado em algum canto.
	public void addComment(String sessionId, String postId, String texto)
			throws ArgumentInvalidException, PersistenceException{
		gerenteDados.getGerenteComentarios().addComentario(sessionId, postId, texto);
	}
	
	
	//TODO METODO QUE RETORNA O NUMERO DE COMENTARIOS
	public String getNumberOfComments(String login, String blogId) throws UserInvalidException, ArgumentInvalidException, PersistenceException{
		return String.valueOf(gerenteDados.getGerentePosts().getNumberOfComments(login, blogId));
	}
	

	// METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException {
		gerenteDados.getGerenteSessoes().logoff(idSession);
	}

	// TODO SALVA TODOS OS DADOS NO BD
	public void saveData() throws PersistenceException, IOException {
		gerenteDados.saveData();
	}
	
	
}
