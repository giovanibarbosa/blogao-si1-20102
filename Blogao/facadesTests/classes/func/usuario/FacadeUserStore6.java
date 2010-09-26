package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import classes.func.Data;
import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.GerenciadorDePerfis;
import classes.gerenciadores.GerenciadorDePosts;
import classes.gerenciadores.GerenciadorDeSessoes;
import classes.gerenciadores.GerenciadorDeBlogs;

public class FacadeUserStore6 {
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	private GerenciadorDeBlogs gerenteBlog = new GerenciadorDeBlogs(gerente);
	private GerenciadorDePosts gerentePost = new GerenciadorDePosts(gerente,
			gerenteBlog);
	GerenciadorDeDados gerenteDados = new GerenciadorDeDados();
	private GerenciadorDePerfis gerenteDoPerfil = new GerenciadorDePerfis();

	// TODO APAGAR OS DADOS SALVOS
	public void cleanPersistence() {
		gerenteDados.cleanPersistence();
	}

	// Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {

		gerenteDoPerfil.createProfile(login, senha, nome_exibicao, email, sexo,
				dataNasc, endereco, interesses, quem_sou_eu, filmes, musicas,
				livros);
	}

	// TODO METODO QUE LOGA O USUARIO
	public String logon(String login, String senha)
			throws PersistenceException, FileNotFoundException,
			ArgumentInvalidException {
		return gerente.logon(login, senha);

	}

	public String todaysDate() {
		return new Data().todaysDate();
	}

	// TODO CRIA O BLOG
	public String createBlog(String idSession, String titulo, String descricao)
			throws Exception {
		return gerenteBlog.createBlog(idSession, titulo, descricao);

	}

	// TODO CRIA O POST
	public String createPost(String idSession, String idBlog, String titulo,
			String texto) throws ArgumentInvalidException,
			PersistenceException, IOException {
		return gerentePost.createPost(idSession, idBlog, titulo, texto);

	}

	// TODO METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException {
		gerente.logoff(idSession);
	}

	// TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {
	}

	public String attachSound(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException {
		return gerentePost.attachSound(sessionId, postId, descricao, dado);
	}

	public String attachMovie(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException {
		return gerentePost.attachMovie(sessionId, postId, descricao, dado);
	}

	public String attachPicture(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException {
		return gerentePost.attachPicture(sessionId, postId, descricao, dado);
	}

	public String getPostInformation(String id, String atributo)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		return gerentePost.informacaoDoPost(id, atributo);
	}

	public String getNumberOfSounds(String id) throws FileNotFoundException {
		return String.valueOf(gerentePost.getNumeroDeSons(id));
	}

	public String getNumberOfMovies(String id) throws FileNotFoundException {
		return String.valueOf(gerentePost.getNumeroDeVideos(id));
	}

	public String getNumberOfPictures(String id) throws FileNotFoundException {
		return String.valueOf(gerentePost.getNumeroDeImagens(id));
	}

	public String getSound(String id, String index)
			throws FileNotFoundException {
		return gerentePost.getSom(id, index);
	}

	public String getSoundDescription(String audioId)
			throws FileNotFoundException {
		return gerentePost.getDescricaoDoSom(audioId);
	}

	public String getSoundData(String audioId) throws FileNotFoundException {
		return gerentePost.getDadoDoSom(audioId);
	}

	public String getMovie(String id, String index)
			throws FileNotFoundException {
		return gerentePost.getVideo(id, index);
	}

	public String getMovieDescription(String videoId)
			throws FileNotFoundException {
		return gerentePost.getDescricaoDoVideo(videoId);
	}

	public String getMovieData(String videoId) throws FileNotFoundException {
		return gerentePost.getDadoDoVideo(videoId);
	}

	public String getPicture(String id, String index)
			throws FileNotFoundException {
		return gerentePost.getImagem(id, index);
	}

	public String getPictureDescription(String imagemId)
			throws FileNotFoundException {
		return gerentePost.getDescricaoDaImagem(imagemId);
	}

	public String getPictureData(String imagemId) throws FileNotFoundException {
		return gerentePost.getDadoDaImagem(imagemId);
	}
}
