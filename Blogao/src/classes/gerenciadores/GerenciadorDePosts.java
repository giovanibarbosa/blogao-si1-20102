package classes.gerenciadores;

import interfaces.Constantes;
import interfaces.Gerenciador;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sun.tools.jxc.apt.Const;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import persistencia.daos.PostsDAO;
import classes.func.multimidia.Audio;
import classes.func.multimidia.Imagem;
import classes.func.multimidia.Video;
import classes.func.usuario.Usuario;
import classes.Comentario;
import classes.Post;
import classes.Blog;

public class GerenciadorDePosts implements Gerenciador {
	private GerenciadorDeDados gerenteDados;
	private List<Post> listaPosts;
	private PostsDAO postsDAO = PostsDAO.getInstance();

	private static final int TEXTO = 110256354;
	private static final int TITULO = -873444423;
	private static final int DATA_CRIACAO = 358082837;

	public GerenciadorDePosts(GerenciadorDeDados gerenteDados) {
		this.gerenteDados = gerenteDados;
		listaPosts = new ArrayList<Post>();

	}

	/**
	 * Metodo que gerancia a criacao de um Post.
	 * 
	 * @param idBlog
	 * @param titulo
	 * @param texto
	 * @param texto2
	 * @return
	 * @throws IOException
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 * @throws UserInvalidException
	 */
	public String createPost(String idSessao, String blogId, String titulo,
			String texto) throws IOException, ArgumentInvalidException,
			PersistenceException, UserInvalidException {

		Blog blog = gerenteDados.getGerenteBlogs().getBlog(blogId);

		gerenteDados.getGerenteBlogs().validaDonoBlog(blog, idSessao);

		String login = gerenteDados.getGerenteSessoes().getLoginPorSessao(
				idSessao);
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);

		user.removeBlog2(blog);
		Post post = new Post(titulo, texto, blogId);
		blog.addPost(post);
		user.addBlog2(blog);
		listaPosts.add(post);

		return post.getId();
	}

	// Metodo para adicionar som a um post
	public String attachSound(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException, UserInvalidException {

		Post post = getPostPorId(postId);
		Blog blog = gerenteDados.getGerenteBlogs()
				.getBlog(post.getIdBlogDono());
		gerenteDados.getGerenteBlogs().validaDonoBlog(blog, sessionId);

		String login = gerenteDados.getGerenteSessoes().getLoginPorSessao(
				sessionId);
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);

		user.removeBlog2(blog);
		blog.removePost(post);

		Audio audio = new Audio(descricao, dado);
		post.addAudio(audio);

		blog.addPost(post);
		user.addBlog2(blog);

		return audio.getId();
	}

	public String attachPicture(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException, UserInvalidException {

		Post post = getPostPorId(postId);
		Blog blog = gerenteDados.getGerenteBlogs()
				.getBlog(post.getIdBlogDono());
		gerenteDados.getGerenteBlogs().validaDonoBlog(blog, sessionId);

		String login = gerenteDados.getGerenteSessoes().getLoginPorSessao(
				sessionId);
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);

		user.removeBlog2(blog);
		blog.removePost(post);

		Imagem imagem = new Imagem(descricao, dado);
		post.addImagem(imagem);

		blog.addPost(post);
		user.addBlog2(blog);

		return imagem.getId();
	}

	public String attachMovie(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException, UserInvalidException {

		Post post = getPostPorId(postId);
		Blog blog = gerenteDados.getGerenteBlogs()
				.getBlog(post.getIdBlogDono());
		gerenteDados.getGerenteBlogs().validaDonoBlog(blog, sessionId);

		String login = gerenteDados.getGerenteSessoes().getLoginPorSessao(
				sessionId);
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);

		user.removeBlog2(blog);
		blog.removePost(post);

		Video video = new Video(descricao, dado);
		post.addVideo(video);

		blog.addPost(post);
		user.addBlog2(blog);

		return video.getId();
	}

	public String informacaoDoPost(String idDoPost, String atributo)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException {

		Post post = getPostPorId(idDoPost);
		if (atributo == null)
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO);

		int codigoAtributo = atributo.hashCode();
		String retorno = "";
		if (post != null) {
			switch (codigoAtributo) {
			case TITULO:
				retorno = post.getTitulo();
				break;
			case TEXTO:
				retorno = post.getCorpo();
				break;
			case DATA_CRIACAO:
				retorno = post.getDataCriacao();
				break;
			default:
				throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO);

			}
		}
		return retorno;
	}

	public int getNumeroDeSons(String idDoPost) throws FileNotFoundException,
			PersistenceException {
		Post post = getPostPorId(idDoPost);
		return post == null ? 0 : post.getListaDeAudio().size();
	}

	public int getNumeroDeVideos(String idDoPost) throws FileNotFoundException,
			PersistenceException {
		Post post = getPostPorId(idDoPost);
		return post == null ? 0 : post.getListaDeVideo().size();
	}

	public int getNumeroDeImagens(String idDoPost)
			throws FileNotFoundException, PersistenceException {
		Post post = getPostPorId(idDoPost);
		return post == null ? 0 : post.getListaDeImagem().size();
	}

	public String getSom(String idDoPost, int index)
			throws FileNotFoundException, PersistenceException,
			ArgumentInvalidException {
		Post post = getPostPorId(idDoPost);
		if (index >= post.getListaDeAudio().size())
			throw new ArgumentInvalidException(Constantes.INDICE_INVALIDO);
		return post.getListaDeAudio().get(Integer.valueOf(index)).getId();
	}

	public String getDescricaoDoSom(String audioId)
			throws FileNotFoundException {
		Audio audio = getAudio(audioId);
		return audio.getDescricao();
	}

	public String getDadoDoSom(String audioId) throws FileNotFoundException {
		Audio audio = getAudio(audioId);
		return audio.getDado();
	}

	private Audio getAudio(String audioId) throws FileNotFoundException {

		for (Post post : listaPosts) {
			for (Audio audio : post.getListaDeAudio()) {
				if (audio.getId().equals(audioId)) {
					return audio;
				}
			}
		}
		throw new FileNotFoundException(Constantes.ATRIBUTO_INVALIDO);
	}

	public String getDescricaoDaImagem(String imagemId)
			throws FileNotFoundException {

		for (Post post : listaPosts) {
			for (Imagem imagem : post.getListaDeImagem()) {
				if (imagem.getId().equals(imagemId)) {
					return imagem.getDescricao();
				}
			}
		}
		throw new FileNotFoundException(Constantes.ATRIBUTO_INVALIDO);
	}

	public String getDadoDaImagem(String imagemId) throws FileNotFoundException {
		for (Post post : listaPosts) {
			for (Imagem imagem : post.getListaDeImagem()) {
				if (imagem.getId().equals(imagemId)) {
					return imagem.getDado();
				}
			}
		}
		throw new FileNotFoundException(Constantes.ATRIBUTO_INVALIDO);
	}

	public String getImagem(String id, int index) throws PersistenceException,
			ArgumentInvalidException {
		Post post = getPostPorId(id);
		if (index >= post.getListaDeImagem().size())
			throw new ArgumentInvalidException(Constantes.INDICE_INVALIDO);
		return post.getListaDeImagem().get(index).getId();
	}

	public String getDescricaoDoVideo(String videoId)
			throws FileNotFoundException {

		for (Post post : listaPosts) {
			for (Video video : post.getListaDeVideo()) {
				if (video.getId().equals(videoId)) {
					return video.getDescricao();
				}
			}
		}
		throw new FileNotFoundException(Constantes.ATRIBUTO_INVALIDO);
	}

	public String getDadoDoVideo(String videoId) throws FileNotFoundException {
		for (Post post : listaPosts) {
			for (Video video : post.getListaDeVideo()) {
				if (video.getId().equals(videoId)) {
					return video.getDado();
				}
			}
		}
		throw new FileNotFoundException(Constantes.ATRIBUTO_INVALIDO);
	}

	public String getVideo(String id, int index) throws PersistenceException {
		Post post = getPostPorId(id);
		if (index >= post.getListaDeVideo().size())
			throw new PersistenceException(Constantes.INDICE_INVALIDO);
		return post.getListaDeVideo().get(index).getId();
	}

	@Override
	public void saveData() throws PersistenceException, IOException {
		postsDAO.limparPosts();
		for (Post post : listaPosts) {
			postsDAO.criar(post);
		}

	}

	@Override
	public void loadData() {
		try {
			listaPosts = postsDAO.recuperaPosts();
		} catch (FileNotFoundException e) {
			listaPosts = new ArrayList<Post>();
		}

	}

	public int recuperaTotalDeFilmesDoPost(String postID)
			throws FileNotFoundException, PersistenceException {
		Post post = getPostPorId(postID);
		return post.getListaDeVideo().size();
	}

	public int recuperaTotalDeImagensDoPost(String postID)
			throws FileNotFoundException, PersistenceException {
		Post post = getPostPorId(postID);
		return post.getListaDeImagem().size();
	}

	public int recuperaTotalDeAudiosDoPost(String postID)
			throws FileNotFoundException, PersistenceException {
		Post post = getPostPorId(postID);
		return post.getListaDeAudio().size();
	}

	public int recuperaIDaudio(String postID, int index)
			throws FileNotFoundException, PersistenceException {
		Post postRecuperado = getPostPorId(postID);
		return Integer.valueOf(postRecuperado.getListaDeAudio().get(index)
				.getId());

	}

	public int recuperaIDvideo(String postID, int index)
			throws FileNotFoundException, PersistenceException {
		Post postRecuperado = getPostPorId(postID);
		return Integer.valueOf(postRecuperado.getListaDeVideo().get(index)
				.getId());

	}

	public int recuperaIDimagem(String postID, int index)
			throws FileNotFoundException, PersistenceException {
		Post postRecuperado = getPostPorId(postID);
		return Integer.valueOf(postRecuperado.getListaDeImagem().get(index)
				.getId());
	}

	/**
	 * @return the listaPosts
	 */
	public List<Post> getListaPosts() {
		return listaPosts;
	}

	/**
	 * @param listaPosts
	 *            the listaPosts to set
	 */
	public void setListaPosts(List<Post> listaPosts) {
		this.listaPosts = listaPosts;
	}

	//
	public Post getPostPorId(String idDoPost) throws PersistenceException {
		for (Post post : listaPosts) {
			if (post.getId().equals(idDoPost)) {
				return post;
			}
		}
		throw new PersistenceException(Constantes.POST_INVALIDO);
	}

	@Override
	public void cleanPersistence() {
		postsDAO.limparPosts();
	}

	public void mudarInformacaoDoPost(String sessionID, String postID,
			String atributo, String novoTexto) throws ArgumentInvalidException,
			PersistenceException, UserInvalidException {

		Post post = getPostPorId(postID);

		Blog blog = gerenteDados.getGerenteBlogs()
				.getBlog(post.getIdBlogDono());

		gerenteDados.getGerenteBlogs().validaDonoBlog(blog, sessionID);

		String login = gerenteDados.getGerenteSessoes().getLoginPorSessao(
				sessionID);
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);

		user.removeBlog2(blog);
		blog.removePost(post);

		post.setAtributo(atributo, novoTexto);

		blog.addPost(post);
		user.addBlog2(blog);

	}

	public void deletaVideo(String sessionId, String idMovie)
			throws ArgumentInvalidException, UserInvalidException {
		Post post = getPostByVideoID(idMovie);

		if (post == null)
			throw new ArgumentInvalidException(Constantes.INDICE_INVALIDO);
		post.removeVideo(post.getMapaVideos().get(idMovie));
	}

	private Post getPostByVideoID(String movieID) {
		for (Post post : listaPosts) {
			if (post.getMapaVideos().containsKey(movieID)) {
				return post;
			}
		}
		return null;
	}

	public void deletaAudio(String sessionId, String idAudio)
			throws ArgumentInvalidException, UserInvalidException {
		Post post = getPostByAudioID(idAudio);

		if (post == null)
			throw new ArgumentInvalidException(Constantes.INDICE_INVALIDO);
		post.removeAudio(post.getMapaAudio().get(idAudio));
	}

	private Post getPostByAudioID(String audioID) {
		for (Post post : listaPosts) {
			if (post.getMapaAudio().containsKey(audioID)) {
				return post;
			}
		}
		return null;
	}

	public void deletaImagem(String sessionId, String idImagem)
			throws ArgumentInvalidException, UserInvalidException {
		Post post = getPostByImagemID(idImagem);

		if (post == null)
			throw new ArgumentInvalidException(Constantes.INDICE_INVALIDO);
		post.removeImagem(post.getMapaImagens().get(idImagem));
	}

	private Post getPostByImagemID(String imagemID) {
		for (Post post : listaPosts) {
			if (post.getMapaImagens().containsKey(imagemID)) {
				return post;
			}
		}
		return null;
	}

	public int getNumberOfComments(String postId) throws PersistenceException {
		Post post = getPostPorId(postId);
		return post.getListaComentarios().size();
	}
	public int getNumberOfComments(String login, String blogId)
			throws UserInvalidException, ArgumentInvalidException,
			PersistenceException {
		List<Blog> blogs = gerenteDados.getGerenteBlogs()
				.getBlogPorLogin(login);
		Blog blog = getBlogAuxiliar(blogs, blogId);
		if (blog == null)
			throw new ArgumentInvalidException(Constantes.BLOG_INVALIDO);
		int retorno = 0;
		for (String p : blog.getListaDePostagens())
			retorno += gerenteDados.getGerentePosts().getNumberOfComments(p);
		return retorno;
	}
	
	private Blog getBlogAuxiliar(List<Blog> blogs, String blogId) {
		for (Blog b : blogs) {
			if (blogId.equals(b.getId()))
				return b;
		}
		return null;
	}
	
	public void removePost(String postId) throws PersistenceException {
		Post post = getPostPorId(postId);
		while (!post.getListaComentarios().isEmpty()) {
			Comentario removido = post.getListaComentarios().remove(0);
			gerenteDados.getGerenteComentarios().remove(removido);
		}
		listaPosts.remove(post);

	}

	public void validaPostId(String postId) throws ArgumentInvalidException {
		for (Post post : listaPosts) {
			if (post.getId().equals(postId))
				return;
		}
		throw new ArgumentInvalidException(Constantes.POST_INVALIDO);
	}

	public Comentario GetComentario(String postId, int index)
			throws PersistenceException, ArgumentInvalidException {

		Post post = getPostPorId(postId);
		if (index >= post.getListaComentarios().size())
			throw new ArgumentInvalidException(Constantes.INDICE_INVALIDO);
		return post.getComentario(index);
	}

	public List<Post> getListaPostsPorBlog(Blog blog) {
		List<Post> listaPostsProcurados = new ArrayList<Post>();
		for (Post post : listaPosts) {
			if (post.getIdBlogDono().equals(blog.getId()))
				listaPostsProcurados.add(post);
		}
		return listaPostsProcurados;
	}

	public void validaPostId(String postId, String sessionId)
			throws ArgumentInvalidException, PersistenceException {
		validaPostId(postId);
		Blog dono = gerenteDados.getGerenteBlogs().getBlog(
				getPostPorId(postId).getIdBlogDono());
		if (!dono.getIdSessao().equals(sessionId))
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);

	}
}
