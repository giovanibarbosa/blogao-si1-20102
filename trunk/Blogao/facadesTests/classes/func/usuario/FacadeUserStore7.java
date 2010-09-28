package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;

import classes.gerenciadores.GerenciadorDeDados;
import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;

/*
 * #Permite a edição de um post existente. os seguintes campos podem ser alterados:
 #*Título do post
 #*Texto
 #*Áudio
 #*Vídeo
 #*Imagem
 #
 #No caso dos recursos multimídia, o cliente deve ser capaz de definir se deseja manter os dados existentes no servidor, ou se serão deletados.
 #Restrições:
 #    -O usuário deve estar logado no sistema (Fornecimento de um id de sessão válido)
 #   
 #    -Campos não-nulo:
 #        *Título do post    
 #Obs.: No caso do acesso via celular, não é necessário implementar a edição dos recursos multimídia existentes, ou seja, será realizada apenas #a edição da parte textual do post

 */

public class FacadeUserStore7 {
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();

	public void loadData() throws FileNotFoundException {
		gerenteDados.loadData();
	}

	public String logon(String login, String senha)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {

		return gerenteDados.getGerenteSessoes().logon(login, senha);

	}

	public int getNumberOfBlogsByLogin(String login)
			throws PersistenceException, ArgumentInvalidException,
			FileNotFoundException, UserInvalidException {

		return gerenteDados.getGerenteBlogs().totalDeBlogsPorLogin(login);

	}

	public int getNumberOfBlogsBySessionId(String sessionID)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException, UserInvalidException {
		return gerenteDados.getGerenteBlogs().totalDeBlogsPorSessao(sessionID);
	}

	public String getBlogBySessionId(String sessionID, int index)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException, UserInvalidException {
		return gerenteDados.getGerenteBlogs().recuperaIdBlogDesejado(sessionID,
				index);
	}

	public String getBlogByLogin(String login, int index)
			throws FileNotFoundException, PersistenceException,
			UserInvalidException, ArgumentInvalidException {

		return gerenteDados.getGerenteBlogs().recuperaIdBlogPorLogin(login,
				index);
	}

	public int getNumberOfPosts(String blogID) throws FileNotFoundException,
			PersistenceException, ArgumentInvalidException {
		return gerenteDados.getGerenteBlogs().totalDePosts(blogID);
	}

	public String getPost(String blogID, int index)
			throws NumberFormatException, FileNotFoundException,
			PersistenceException, ArgumentInvalidException {

		return gerenteDados.getGerenteBlogs().recuperaIdDoPost(blogID, index);
	}

	public void changePostInformation(String sessionID, String postID,
			String atributo, String novoTexto) throws FileNotFoundException,
			ArgumentInvalidException, PersistenceException,
			UserInvalidException {

		gerenteDados.getGerentePosts().mudarInformacaoDoPost(sessionID, postID,
				atributo, novoTexto);
	}

	public void deleteMovie(String sessionID, String idMovie)
			throws FileNotFoundException, PersistenceException,
			ArgumentInvalidException, UserInvalidException {

		gerenteDados.getGerentePosts().deletaVideo(sessionID, idMovie);

	}
	
	public void deletePicture(String sessionID, String idImagem)
			throws PersistenceException, ArgumentInvalidException,
			FileNotFoundException, UserInvalidException {
		gerenteDados.getGerentePosts().deletaImagem(sessionID, idImagem);

	}

	public String getNumberOfSounds(String id) throws FileNotFoundException,
			PersistenceException {
		return String.valueOf(gerenteDados.getGerentePosts()
				.getNumeroDeSons(id));
	}

	public String getNumberOfMovies(String id) throws FileNotFoundException,
			PersistenceException {
		return String.valueOf(gerenteDados.getGerentePosts().getNumeroDeVideos(
				id));
	}

	public String getNumberOfPictures(String id) throws FileNotFoundException,
			PersistenceException {
		return String.valueOf(gerenteDados.getGerentePosts()
				.getNumeroDeImagens(id));
	}

	public String getMovie(String id, int index) throws FileNotFoundException,
			PersistenceException {
		return gerenteDados.getGerentePosts().getVideo(id, index);
	}

	public String getSound(String id, int index) throws FileNotFoundException,
			PersistenceException, ArgumentInvalidException {
		return gerenteDados.getGerentePosts().getSom(id, index);
	}

	public String getPicture(String id, int index)
			throws FileNotFoundException, PersistenceException, ArgumentInvalidException {
		return gerenteDados.getGerentePosts().getImagem(id, index);
	}

	
	
	public void deleteSound(String sessionID, String idSom)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException, UserInvalidException {

		gerenteDados.getGerentePosts().deletaAudio(sessionID, idSom);

	}

	public void logoff(String idSession) throws ArgumentInvalidException {
		gerenteDados.getGerenteSessoes().logoff(idSession);
	}

	public void saveData() throws PersistenceException, IOException {
		gerenteDados.saveData();
	}

}
