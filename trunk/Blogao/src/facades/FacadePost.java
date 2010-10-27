package facades;

import interfaces.Constantes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.Blog;
import classes.Comentario;
import classes.Post;
import classes.func.multimidia.Audio;
import classes.func.multimidia.Imagem;
import classes.func.multimidia.Video;
import classes.func.usuario.Usuario;
import classes.gerenciadores.NewGerenciadorDeBlogs;
import classes.gerenciadores.NewGerenciadorDeDados;
import classes.gerenciadores.NewGerenciadorDePosts;

public class FacadePost {
	private static FacadePost instance;
	private NewGerenciadorDePosts gerentePost;

	protected FacadePost() {
		gerentePost = new NewGerenciadorDePosts(NewGerenciadorDeDados.getInstance());
	}


	public static FacadePost getInstance() {
		if (instance == null) {
			instance = new FacadePost();
		}
		return instance;
	}
	
	/**
	 * Metodo que encapsula o metodo createPost de gerenciador de perfil.
	 * @throws Exception 
	 */
	public String createPost(String idSessao, String blogId, String titulo,
			String texto) throws Exception{
		return gerentePost.createPost(idSessao, blogId, titulo, texto);
	}


	/**
	 * Metodo que encapsula o metodo attachSound de gerenciador de perfil.
	 * @throws UserInvalidException 
	 * @throws IOException 
	 * @throws PersistenceException 
	 * @throws ArgumentInvalidException 
	 */
	public String attachSound(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException, PersistenceException, IOException, UserInvalidException {
		return gerentePost.attachSound(sessionId, postId, descricao, dado);
	}

	/**
	 * Metodo que encapsula o metodo attachPicture de gerenciador de perfil.
	 * @throws UserInvalidException 
	 * @throws IOException 
	 * @throws PersistenceException 
	 * @throws ArgumentInvalidException 
	 */
	public String attachPicture(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException, PersistenceException, IOException, UserInvalidException {
		return gerentePost.attachPicture(sessionId, postId, descricao, dado);
	}

	/**
	 * Metodo que encapsula o metodo attachMovie de gerenciador de perfil.
	 * @throws UserInvalidException 
	 * @throws IOException 
	 * @throws PersistenceException 
	 * @throws ArgumentInvalidException 
	 */
	public String attachMovie(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException, PersistenceException, IOException, UserInvalidException {
		return gerentePost.attachMovie(sessionId, postId, descricao, dado);
	}

	/**
	 * Metodo que encapsula o metodo informacaoDoPost de gerenciador de perfil.
	 * @throws PersistenceException 
	 * @throws ArgumentInvalidException 
	 * @throws FileNotFoundException 
	 */
	public String informacaoDoPost(String idDoPost, String atributo) throws FileNotFoundException, ArgumentInvalidException, PersistenceException{
			return gerentePost.informacaoDoPost(idDoPost, atributo);
	}

	/**
	 * Metodo que encapsula o metodo deletePost de gerenciador de perfil.
	 * @throws IOException 
	 * @throws PersistenceException 
	 * @throws ArgumentInvalidException 
	 */
	public void deletePost(String sessionId, String postId) throws ArgumentInvalidException, PersistenceException, IOException{
		gerentePost.deletePost(sessionId, postId);
	}

	/**
	 * Metodo que encapsula o metodo getNumeroDeSons de gerenciador de perfil.
	 */
	public int getNumeroDeSons(String idDoPost) {
		return gerentePost.getNumeroDeSons(idDoPost);
	}

	/**
	 * Metodo que encapsula o metodo getNumeroDeVideos de gerenciador de perfil.
	 */
	public int getNumeroDeVideos(String idDoPost) {
		return gerentePost.getNumeroDeVideos(idDoPost);
	}

	/**
	 * Metodo que encapsula o metodo getNumeroDeImagens de gerenciador de perfil.
	 */
	public int getNumeroDeImagens(String idDoPost) {
		return gerentePost.getNumeroDeImagens(idDoPost);
	}

	/**
	 * Metodo que encapsula o metodo getSom de gerenciador de perfil.
	 * @throws ArgumentInvalidException 
	 * @throws PersistenceException 
	 * @throws FileNotFoundException 
	 */
	public String getSom(String idDoPost, int index) throws FileNotFoundException, PersistenceException, ArgumentInvalidException{
		return gerentePost.getSom(idDoPost, index);
	}

	/**
	 * Metodo que encapsula o metodo getDescricaoDoSom de gerenciador de perfil.
	 * @throws FileNotFoundException 
	 */
	public String getDescricaoDoSom(String audioId) throws FileNotFoundException{
		return gerentePost.getDescricaoDoSom(audioId);
	}

	/**
	 * Metodo que encapsula o metodo getDadoDoSom de gerenciador de perfil.
	 * @throws FileNotFoundException 
	 */
	public String getDadoDoSom(String audioId) throws FileNotFoundException{
		return gerentePost.getDadoDoSom(audioId);
	}


	/**
	 * Metodo que encapsula o metodo getDescricaoDaImagem de gerenciador de perfil.
	 * @throws FileNotFoundException 
	 */
	public String getDescricaoDaImagem(String imagemId) throws FileNotFoundException{
		return gerentePost.getDescricaoDaImagem(imagemId);
	}

	/**
	 * Metodo que encapsula o metodo getDadoDaImagem de gerenciador de perfil.
	 * @throws FileNotFoundException 
	 */
	public String getDadoDaImagem(String imagemId) throws FileNotFoundException{
		return gerentePost.getDadoDaImagem(imagemId);
	}

	/**
	 * Metodo que encapsula o metodo getImagem de gerenciador de perfil.
	 * @throws ArgumentInvalidException 
	 * @throws PersistenceException 
	 */
	public String getImagem(String id, int index) throws PersistenceException, ArgumentInvalidException{
		return gerentePost.getImagem(id, index);
	}

	/**
	 * Metodo que encapsula o metodo getDescricaoDoVideo de gerenciador de perfil.
	 * @throws FileNotFoundException 
	 */
	public String getDescricaoDoVideo(String videoId) throws FileNotFoundException{
		return gerentePost.getDescricaoDoVideo(videoId);
	}

	/**
	 * Metodo que encapsula o metodo getDadoDoVideo de gerenciador de perfil.
	 * @throws FileNotFoundException 
	 */
	public String getDadoDoVideo(String videoId) throws FileNotFoundException{
		return gerentePost.getDadoDoVideo(videoId);
	}

	/**
	 * Metodo que encapsula o metodo getVideo de gerenciador de perfil.
	 * @throws PersistenceException 
	 */
	public String getVideo(String id, int index) throws PersistenceException{
		return gerentePost.getVideo(id, index);
	}

	/**
	 * Metodo que encapsula o metodo saveData de gerenciador de perfil.
	 */
	public void saveData() throws PersistenceException, IOException {
		gerentePost.saveData();

	}

	/**
	 * Metodo que encapsula o metodo loadData de gerenciador de perfil.
	 */
	public void loadData() {
		gerentePost.loadData();

	}

	/**
	 * Metodo que encapsula o metodo recuperaTotalDeFilmesDoPost de gerenciador de perfil.
	 * @throws PersistenceException 
	 * @throws FileNotFoundException 
	 */
	public int recuperaTotalDeFilmesDoPost(String postID) throws FileNotFoundException, PersistenceException{
			return gerentePost.recuperaTotalDeFilmesDoPost(postID);
	}

	/**
	 * Metodo que encapsula o metodo recuperaTotalDeImagensDoPost de gerenciador de perfil.
	 * @throws PersistenceException 
	 * @throws FileNotFoundException 
	 */
	public int recuperaTotalDeImagensDoPost(String postID) throws FileNotFoundException, PersistenceException{
			return gerentePost.recuperaTotalDeImagensDoPost(postID);
	}

	/**
	 * Metodo que encapsula o metodo recuperaTotalDeAudiosDoPost de gerenciador de perfil.
	 * @throws PersistenceException 
	 * @throws FileNotFoundException 
	 */
	public int recuperaTotalDeAudiosDoPost(String postID) throws FileNotFoundException, PersistenceException{
		return gerentePost.recuperaTotalDeAudiosDoPost(postID);
	}

	/**
	 * Metodo que encapsula o metodo recuperaIDaudio de gerenciador de perfil.
	 * @throws PersistenceException 
	 * @throws FileNotFoundException 
	 */
	public int recuperaIDaudio(String postID, int index) throws FileNotFoundException, PersistenceException{
		return gerentePost.recuperaIDaudio(postID, index);
	}

	/**
	 * Metodo que encapsula o metodo recuperaIDvideo de gerenciador de perfil.
	 * @throws PersistenceException 
	 * @throws FileNotFoundException 
	 */
	public int recuperaIDvideo(String postID, int index) throws FileNotFoundException, PersistenceException{
		return gerentePost.recuperaIDvideo(postID, index);

	}

	/**
	 * Metodo que encapsula o metodo recuperaIDimagem de gerenciador de perfil.
	 * @throws PersistenceException 
	 * @throws FileNotFoundException 
	 */
	public int recuperaIDimagem(String postID, int index) throws FileNotFoundException, PersistenceException{
		return gerentePost.recuperaIDimagem(postID, index);
	}

	/**
	 * Metodo que encapsula o metodo getListaPosts de gerenciador de perfil.
	 */
	public List<Post> getListaPosts() {
		return gerentePost.getListaPosts();
	}

	/**
	 * Metodo que encapsula o metodo setListaPosts de gerenciador de perfil.
	 */
	public void setListaPosts(List<Post> listaPosts) {
		gerentePost.setListaPosts(listaPosts);
	}

	/**
	 * Metodo que encapsula o metodo getPostPorId de gerenciador de perfil.
	 * @throws PersistenceException 
	 */
	public Post getPostPorId(String idDoPost) throws PersistenceException {
		return gerentePost.getPostPorId(idDoPost);
	}

	/**
	 * Metodo que encapsula o metodo cleanPersistence de gerenciador de perfil.
	 */
	public void cleanPersistence() {
		gerentePost.cleanPersistence();
	}

	/**
	 * Metodo que encapsula o metodo mudarInformacaoDoPost de gerenciador de perfil.
	 * @throws UserInvalidException 
	 * @throws PersistenceException 
	 * @throws ArgumentInvalidException 
	 */
	public void mudarInformacaoDoPost(String sessionID, String postID,
			String atributo, String novoTexto) throws ArgumentInvalidException, PersistenceException, UserInvalidException{
		gerentePost.mudarInformacaoDoPost(sessionID, postID, atributo, novoTexto);

	}

	/**
	 * Metodo que encapsula o metodo deletaVideo de gerenciador de perfil.
	 * @throws UserInvalidException 
	 * @throws ArgumentInvalidException 
	 */
	public void deletaVideo(String sessionId, String idMovie) throws ArgumentInvalidException, UserInvalidException{
		gerentePost.deletaVideo(sessionId, idMovie);
	}


	/**
	 * Metodo que encapsula o metodo deletaAudio de gerenciador de perfil.
	 * @throws UserInvalidException 
	 * @throws ArgumentInvalidException 
	 */
	public void deletaAudio(String sessionId, String idAudio) throws ArgumentInvalidException, UserInvalidException{
		gerentePost.deletaAudio(sessionId, idAudio);
	}


	/**
	 * Metodo que encapsula o metodo deletaImagem de gerenciador de perfil.
	 * @throws UserInvalidException 
	 * @throws ArgumentInvalidException 
	 */
	public void deletaImagem(String sessionId, String idImagem) throws ArgumentInvalidException, UserInvalidException{
		gerentePost.deletaImagem(sessionId, idImagem);
	}


	/**
	 * Metodo que encapsula o metodo getNumberOfComments de gerenciador de perfil.
	 */
	public int getNumberOfComments(String postId) throws PersistenceException {
		return gerentePost.getNumberOfComments(postId);
	}

	/**
	 * Metodo que encapsula o metodo getNumberOfComments de gerenciador de perfil.
	 * @throws PersistenceException 
	 * @throws ArgumentInvalidException 
	 * @throws UserInvalidException 
	 */
	public int getNumberOfComments(String login, String blogId) throws UserInvalidException, ArgumentInvalidException, PersistenceException{
		return gerentePost.getNumberOfComments(login, blogId);
	}


	/**
	 * Metodo que encapsula o metodo removePost de gerenciador de perfil.
	 * @throws ArgumentInvalidException 
	 * @throws PersistenceException 
	 */
	public void removePost(String postId) throws PersistenceException, ArgumentInvalidException{
		gerentePost.removePost(postId);

	}

	/**
	 * Metodo que encapsula o metodo validaPostId de gerenciador de perfil.
	 * @throws ArgumentInvalidException 
	 */
	public void validaPostId(String postId) throws ArgumentInvalidException{
		gerentePost.validaPostId(postId);
	}

	/**
	 * Metodo que encapsula o metodo GetComentario de gerenciador de perfil.
	 * @throws ArgumentInvalidException 
	 * @throws PersistenceException 
	 */
	public Comentario GetComentario(String postId, int index) throws PersistenceException, ArgumentInvalidException{
		return gerentePost.GetComentario(postId, index);
	}

	/**
	 * Metodo que encapsula o metodo getListaPostsPorBlog de gerenciador de perfil.
	 */
	public List<Post> getListaPostsPorBlog(Blog blog) {
		return gerentePost.getListaPostsPorBlog(blog);
	}
	
	/**
	 * Metodo que encapsula o metodo validaPostId de gerenciador de perfil.
	 * @throws PersistenceException 
	 * @throws ArgumentInvalidException 
	 */
	public void validaPostId(String postId, String sessionId) throws ArgumentInvalidException, PersistenceException{
		gerentePost.validaPostId(postId, sessionId);

	}
}
