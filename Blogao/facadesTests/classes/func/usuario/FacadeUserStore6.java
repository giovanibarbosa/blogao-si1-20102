package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.func.Data;
import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.GerenciadorDePerfis;
import classes.gerenciadores.GerenciadorDePosts;
import classes.gerenciadores.GerenciadorDeSessoes;
import classes.gerenciadores.GerenciadorDeBlogs;

public class FacadeUserStore6 {
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();

	// TODO APAGAR OS DADOS SALVOS
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

	public String todaysDate() {
		return new Data().todaysDate();
	}

	// CRIA O BLOG
	public String createBlog(String idSessao, String titulo, String descricao)
			throws ArgumentInvalidException, PersistenceException, IOException,
			UserInvalidException {

		return gerenteDados.getGerenteBlogs().createBlog(idSessao, titulo,
				descricao);
	}

	//CRIA O POST
	public String createPost(String idSession, String idBlog, String titulo,
			String texto) throws ArgumentInvalidException,
			PersistenceException, IOException, UserInvalidException {
		
		return gerenteDados.getGerentePosts().createPost(idSession, idBlog, titulo, texto);

	}

	//METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException {
		gerenteDados.getGerenteSessoes().logoff(idSession);
	}

	// SALVA TODOS OS DADOS NO BD
	public void saveData() throws PersistenceException, IOException {
		gerenteDados.saveData();
	}

	public String attachSound(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException, UserInvalidException {
		
		return gerenteDados.getGerentePosts().attachSound(sessionId, postId, descricao, dado);
	}

	public String attachMovie(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException, UserInvalidException {
		return gerenteDados.getGerentePosts().attachMovie(sessionId, postId, descricao, dado);
	}

	public String attachPicture(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException, UserInvalidException {
		return gerenteDados.getGerentePosts().attachPicture(sessionId, postId, descricao, dado);
	}

	public String getPostInformation(String id, String atributo)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		return gerenteDados.getGerentePosts().informacaoDoPost(id, atributo);
	}

	public String getNumberOfSounds(String id) throws FileNotFoundException, PersistenceException {
		return String.valueOf(gerenteDados.getGerentePosts().getNumeroDeSons(id));
	}

	public String getNumberOfMovies(String id) throws FileNotFoundException, PersistenceException {
		return String.valueOf(gerenteDados.getGerentePosts().getNumeroDeVideos(id));
	}

	public String getNumberOfPictures(String id) throws FileNotFoundException, PersistenceException {
		return String.valueOf(gerenteDados.getGerentePosts().getNumeroDeImagens(id));
	}

	public String getSound(String id, String index)
			throws FileNotFoundException, PersistenceException {
		return gerenteDados.getGerentePosts().getSom(id, index);
	}

	public String getSoundDescription(String audioId)
			throws FileNotFoundException {
		return gerenteDados.getGerentePosts().getDescricaoDoSom(audioId);
	}

	public String getSoundData(String audioId) throws FileNotFoundException {
		return gerenteDados.getGerentePosts().getDadoDoSom(audioId);
	}

	public String getMovie(String id, String index)
			throws FileNotFoundException, PersistenceException {
		return gerenteDados.getGerentePosts().getVideo(id, index);
	}

	public String getMovieDescription(String videoId)
			throws FileNotFoundException {
		return gerenteDados.getGerentePosts().getDescricaoDoVideo(videoId);
	}

	public String getMovieData(String videoId) throws FileNotFoundException {
		return gerenteDados.getGerentePosts().getDadoDoVideo(videoId);
	}

	public String getPicture(String id, String index)
			throws FileNotFoundException, PersistenceException {
		return gerenteDados.getGerentePosts().getImagem(id, index);
	}

	public String getPictureDescription(String imagemId)
			throws FileNotFoundException {
		return gerenteDados.getGerentePosts().getDescricaoDaImagem(imagemId);
	}

	public String getPictureData(String imagemId) throws FileNotFoundException {
		return gerenteDados.getGerentePosts().getDadoDaImagem(imagemId);
	}
}
