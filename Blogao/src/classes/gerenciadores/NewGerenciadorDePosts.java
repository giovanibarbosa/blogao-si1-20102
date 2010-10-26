package classes.gerenciadores;

import interfaces.Constantes;
import interfaces.Gerenciador;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

/**
 * Classe que gerencia Posts.
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 */
public class NewGerenciadorDePosts implements Gerenciador {
	private NewGerenciadorDeDados gerenteDados;
	private List<Post> listaPosts;
	private PostsDAO postsDAO = PostsDAO.getInstance();

	private static final int TEXTO = 110256354;
	private static final int TITULO = -873444423;
	private static final int DATA_CRIACAO = 358082837;

	/**
	 * Construtor da classe.
	 * 
	 * @param gerenteDados
	 *            {@link GerenciadorDeDados}
	 */
	public NewGerenciadorDePosts(NewGerenciadorDeDados gerenteDados) {
		this.gerenteDados = gerenteDados;
		listaPosts = new ArrayList<Post>();

	}

	/**
	 * Metodo que gerancia a criacao de um Post.
	 * 
	 * @param idSessao
	 *            {@link String}
	 * @param idBlog
	 *            {@link String}
	 * @param titulo
	 *            {@link String}
	 * @param texto
	 *            {@link String}
	 * @return {@link String} Id do post.
	 * @throws Exception
	 */
	public String createPost(String idSessao, String blogId, String titulo,
			String texto) throws Exception {

		Blog blog = gerenteDados.getGerenteBlogs().getBlog(blogId);
		verificaDonoDoBlog(idSessao, blog);

		String login = recuperaLoginPorSessionId(idSessao);
		Usuario user = recuperaUsuarioPorLogin(login);

		user.removeBlog2(blog);
		// gerenteDados.getGerenteBlogs().deleteBlog(blog); //adicionei essa
		// linha
		Post post = new Post(titulo, texto, blogId);
		blog.addPost(post);
		user.addBlog2(blog);
		// gerenteDados.getGerenteBlogs().atualizaBlog(blog); //adicionei essa
		// linha
		// gerenteDados.getGerenteBlogs().createBlog(blog.getIdSessao(),
		// blog.getTitulo(), blog.getDescricao()); // adicionei essa linha tbm
		// gerenteDados.getGerenteBlogs().addBlogTiagoLeite(blog); // adicionei
		// essa linha tbm
		listaPosts.add(post);

		gerenteDados.getGerenteBlogs().avisaListeners(blog, post.getId());

		return post.getId();
	}

	private Usuario recuperaUsuarioPorLogin(String login)
			throws UserInvalidException {
		return gerenteDados.getGerenteUsuarios().getUsuario(login);
	}

	private String recuperaLoginPorSessionId(String idSessao)
			throws ArgumentInvalidException {
		return gerenteDados.getGerenteSessoes().getLoginPorSessao(idSessao);
	}

	private void verificaDonoDoBlog(String idSessao, Blog blog)
			throws ArgumentInvalidException {
		gerenteDados.getGerenteBlogs().validaDonoBlog(blog, idSessao);
	}

	/**
	 * Adiciona um audio ao post.
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @param postId
	 *            {@link String}
	 * @param descricao
	 *            {@link String}
	 * @param dado
	 *            {@link String}
	 * @return Id {@link String}
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 * @throws IOException
	 * @throws UserInvalidException
	 */
	public String attachSound(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException, UserInvalidException {

		Post post = getPostPorId(postId);
		Blog blog = gerenteDados.getGerenteBlogs()
				.getBlog(post.getIdBlogDono());
		verificaDonoDoBlog(sessionId, blog);

		String login = recuperaLoginPorSessionId(sessionId);
		Usuario user = recuperaUsuarioPorLogin(login);

		user.removeBlog2(blog);
		blog.removePost(post);

		Audio audio = new Audio(descricao, dado);
		post.addAudio(audio);

		blog.addPost(post);
		user.addBlog2(blog);

		return audio.getId();
	}

	/**
	 * Adiciona uma foto ao post.
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @param postId
	 *            {@link String}
	 * @param descricao
	 *            {@link String}
	 * @param dado
	 *            {@link String}
	 * @return Id {@link String}
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 * @throws IOException
	 * @throws UserInvalidException
	 */
	public String attachPicture(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException, UserInvalidException {

		Post post = getPostPorId(postId);
		Blog blog = gerenteDados.getGerenteBlogs()
				.getBlog(post.getIdBlogDono());
		verificaDonoDoBlog(sessionId, blog);

		String login = recuperaLoginPorSessionId(sessionId);
		Usuario user = recuperaUsuarioPorLogin(login);

		user.removeBlog2(blog);
		blog.removePost(post);

		Imagem imagem = new Imagem(descricao, dado);
		post.addImagem(imagem);

		blog.addPost(post);
		user.addBlog2(blog);

		return imagem.getId();
	}

	/**
	 * Adiciona um filme ao Post.s
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @param postId
	 *            {@link String}
	 * @param descricao
	 *            {@link String}
	 * @param dado
	 *            {@link String}
	 * @return {@link String} Id.
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 * @throws IOException
	 * @throws UserInvalidException
	 */
	public String attachMovie(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException, UserInvalidException {

		Post post = getPostPorId(postId);
		Blog blog = gerenteDados.getGerenteBlogs()
				.getBlog(post.getIdBlogDono());
		verificaDonoDoBlog(sessionId, blog);

		String login = recuperaLoginPorSessionId(sessionId);
		Usuario user = recuperaUsuarioPorLogin(login);

		user.removeBlog2(blog);
		blog.removePost(post);

		Video video = new Video(descricao, dado);
		post.addVideo(video);

		blog.addPost(post);
		user.addBlog2(blog);

		return video.getId();
	}

	/**
	 * Retorna as inforacoes do post.
	 * 
	 * @param idDoPost
	 *            {@link String}
	 * @param atributo
	 *            {@link String}
	 * @return informacoes {@link String}
	 * @throws ArgumentInvalidException
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 */
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

	/**
	 * Deleta post.
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @param postId
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 * @throws IOException
	 */
	public void deletePost(String sessionId, String postId)
			throws ArgumentInvalidException, PersistenceException, IOException {
		validaPostId(postId, sessionId);
		removePost(postId);
	}

	/**
	 * Retorna o numero de arquivos de audio no post.
	 * 
	 * @param idDoPost
	 *            {@link String}
	 * @return numero de audios.
	 */
	public int getNumeroDeSons(String idDoPost) {
		Post post;
		try {
			post = getPostPorId(idDoPost);
		} catch (Exception e) {
			return 0;
		}
		return post == null ? 0 : post.getListaDeAudio().size();
	}

	/**
	 * Retorna o numeto de Videos do post
	 * 
	 * @param idDoPost
	 *            {@link String}
	 * @return numero de videos.
	 */
	public int getNumeroDeVideos(String idDoPost) {
		Post post = null;
		try {
			post = getPostPorId(idDoPost);
		} catch (Exception e) {
			return 0;
		}
		return post == null ? 0 : post.getListaDeVideo().size();
	}

	/**
	 * Retorna o numeor de imagens do post.
	 * 
	 * @param idDoPost
	 *            {@link String}
	 * @return numero de imagens.
	 */
	public int getNumeroDeImagens(String idDoPost) {
		Post post = null;
		try {
			post = getPostPorId(idDoPost);
		} catch (Exception e) {
			return 0;
		}
		return post == null ? 0 : post.getListaDeImagem().size();
	}

	/**
	 * Acessa um audio.
	 * 
	 * @param idDoPost
	 *            {@link String}
	 * @param index
	 *            {@link int}
	 * @return Id do audio.
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 * @throws ArgumentInvalidException
	 */
	public String getSom(String idDoPost, int index)
			throws FileNotFoundException, PersistenceException,
			ArgumentInvalidException {
		Post post = getPostPorId(idDoPost);
		if (index >= post.getListaDeAudio().size())
			throw new ArgumentInvalidException(Constantes.INDICE_INVALIDO);
		return post.getListaDeAudio().get(Integer.valueOf(index)).getId();
	}

	/**
	 * Retorna a descricao do som.
	 * 
	 * @param audioId
	 *            {@link String}
	 * @return descricao do som.
	 * @throws FileNotFoundException
	 */
	public String getDescricaoDoSom(String audioId)
			throws FileNotFoundException {
		Audio audio = getAudio(audioId);
		return audio.getDescricao();
	}

	/**
	 * Retorna dos dados do audio.
	 * 
	 * @param audioId
	 *            {@link String}
	 * @return dados do audio.
	 * @throws FileNotFoundException
	 */
	public String getDadoDoSom(String audioId) throws FileNotFoundException {
		Audio audio = getAudio(audioId);
		return audio.getDado();
	}

	/**
	 * Recupera o audio.
	 * 
	 * @param audioId
	 *            {@link String}
	 * @return {@link Audio}
	 * @throws FileNotFoundException
	 */
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

	/**
	 * Retorna a descricao da imagem.
	 * 
	 * @param imagemId
	 *            {@link String}
	 * @return descricao da imagem.
	 * @throws FileNotFoundException
	 */
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

	/**
	 * Recupera os dados da imagem.
	 * 
	 * @param imagemId
	 *            {@link String}
	 * @return dados da imagem.
	 * @throws FileNotFoundException
	 */
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

	/**
	 * Recupera uma imagem da lista.
	 * 
	 * @param id
	 *            {@link String}
	 * @param index
	 *            {@link String}
	 * @return id da imagem.
	 * @throws PersistenceException
	 * @throws ArgumentInvalidException
	 */
	public String getImagem(String id, int index) throws PersistenceException,
			ArgumentInvalidException {
		Post post = getPostPorId(id);
		if (index >= post.getListaDeImagem().size())
			throw new ArgumentInvalidException(Constantes.INDICE_INVALIDO);
		return post.getListaDeImagem().get(index).getId();
	}

	/**
	 * Recupera a descricao do video.
	 * 
	 * @param videoId
	 *            {@link String}
	 * @return descricao do video.
	 * @throws FileNotFoundException
	 */
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

	/**
	 * Retorna os dados do video.
	 * 
	 * @param videoId
	 *            {@link String}
	 * @return Dados do video.
	 * @throws FileNotFoundException
	 */
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

	/**
	 * Recupera um video da lista.
	 * 
	 * @param id
	 *            {@link String}
	 * @param index
	 *            {@link int}
	 * @return id do video.
	 * @throws PersistenceException
	 */
	public String getVideo(String id, int index) throws PersistenceException {
		Post post = getPostPorId(id);
		if (index >= post.getListaDeVideo().size())
			throw new PersistenceException(Constantes.INDICE_INVALIDO);
		return post.getListaDeVideo().get(index).getId();
	}

	@Override
	/**
	 * Salva os dados.
	 */
	public void saveData() throws PersistenceException, IOException {
		postsDAO.limparPosts();
		for (Post post : listaPosts) {
			postsDAO.criar(post);
		}

	}

	@Override
	/**
	 * Carrega os dados.
	 */
	public void loadData() {
		try {
			listaPosts = postsDAO.recuperaPosts();
		} catch (FileNotFoundException e) {
			listaPosts = new ArrayList<Post>();
		}

	}

	/**
	 * Recupera o total de filmes em um post.
	 * 
	 * @param postID
	 *            {@link String}
	 * @return numero de filmes
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 */
	public int recuperaTotalDeFilmesDoPost(String postID)
			throws FileNotFoundException, PersistenceException {
		Post post = getPostPorId(postID);
		return post.getListaDeVideo().size();
	}

	/**
	 * Recupera total de iamgens do post
	 * 
	 * @param postID
	 *            {@link String}
	 * @return total de imagens.
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 */
	public int recuperaTotalDeImagensDoPost(String postID)
			throws FileNotFoundException, PersistenceException {
		Post post = getPostPorId(postID);
		return post.getListaDeImagem().size();
	}

	/**
	 * Recupera o total de audios do post.
	 * 
	 * @param postID
	 *            {@link String}
	 * @return numero de audios.
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 */
	public int recuperaTotalDeAudiosDoPost(String postID)
			throws FileNotFoundException, PersistenceException {
		Post post = getPostPorId(postID);
		return post.getListaDeAudio().size();
	}

	/**
	 * Recupera o id do audio.
	 * 
	 * @param postID
	 *            {@link String}
	 * @param index
	 *            {@link int}
	 * @return id do Audio.
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 */
	public int recuperaIDaudio(String postID, int index)
			throws FileNotFoundException, PersistenceException {
		Post postRecuperado = getPostPorId(postID);
		return Integer.valueOf(postRecuperado.getListaDeAudio().get(index)
				.getId());

	}

	/**
	 * Recupera o Id do video.
	 * 
	 * @param postID
	 *            {@link String}
	 * @param index
	 *            {@link int}
	 * @return id do video.
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 */
	public int recuperaIDvideo(String postID, int index)
			throws FileNotFoundException, PersistenceException {
		Post postRecuperado = getPostPorId(postID);
		return Integer.valueOf(postRecuperado.getListaDeVideo().get(index)
				.getId());

	}

	/**
	 * Recupera o id da imagem.
	 * 
	 * @param postID
	 *            {@link String}
	 * @param index
	 *            {@link int}
	 * @return id da imagem.
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 */
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

	/**
	 * Recupera um post.
	 * 
	 * @param idDoPost
	 *            {@link String}
	 * @return {@link Post}
	 * @throws PersistenceException
	 */
	public Post getPostPorId(String idDoPost) throws PersistenceException {
		for (Post post : listaPosts) {
			if (post.getId().equals(idDoPost)) {
				return post;
			}
		}
		throw new PersistenceException(Constantes.POST_INVALIDO);
	}

	@Override
	/**
	 * Elimina os arquivos persistentes e limpa o ambiente.
	 */
	public void cleanPersistence() {
		postsDAO.limparPosts();
		listaPosts = new ArrayList<Post>();
	}

	/**
	 * Modifica as informacoes do post.
	 * 
	 * @param sessionID
	 *            {@link String}
	 * @param postID
	 *            {@link String}
	 * @param atributo
	 *            {@link String}
	 * @param novoTexto
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 * @throws UserInvalidException
	 */
	public void mudarInformacaoDoPost(String sessionID, String postID,
			String atributo, String novoTexto) throws ArgumentInvalidException,
			PersistenceException, UserInvalidException {

		Post post = getPostPorId(postID);

		Blog blog = gerenteDados.getGerenteBlogs()
				.getBlog(post.getIdBlogDono());

		verificaDonoDoBlog(sessionID, blog);

		String login = recuperaLoginPorSessionId(sessionID);
		Usuario user = recuperaUsuarioPorLogin(login);

		user.removeBlog2(blog);
		blog.removePost(post);

		post.setAtributo(atributo, novoTexto);

		blog.addPost(post);
		user.addBlog2(blog);

	}

	/**
	 * Deleta um video.
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @param idMovie
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 * @throws UserInvalidException
	 */
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

	/**
	 * Deleta um audio.
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @param idAudio
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 * @throws UserInvalidException
	 */
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

	/**
	 * Deleta uma imagem.
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @param idImagem
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 * @throws UserInvalidException
	 */
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

	/**
	 * Recupera o numero de comentarios.
	 * 
	 * @param postId
	 *            {@link String}
	 * @return total de comentarios.
	 * @throws PersistenceException
	 */
	public int getNumberOfComments(String postId) throws PersistenceException {
		Post post = getPostPorId(postId);
		return post.getListaComentarios().size();
	}

	/**
	 * Recupera o numero de comentarios.
	 * 
	 * @param login
	 *            {@link String}
	 * @param blogId
	 *            {@link String}
	 * @return total de comentarios.
	 * @throws UserInvalidException
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 */
	public int getNumberOfComments(String login, String blogId)
			throws UserInvalidException, ArgumentInvalidException,
			PersistenceException {
		validateLoggedUser(login);
		List<Blog> blogs = gerenteDados.getGerenteBlogs().getListaDeBlogs();
		Blog blg = null;
		for (Blog blog : blogs) {
			if (blog.getId().equals(blogId))
				blg = blog;
		}
		if (blg == null)
			throw new ArgumentInvalidException(Constantes.BLOG_INVALIDO);
		int retorno = 0;
		for (String p : blg.getListaDePostagens())
			retorno += gerenteDados.getGerentePosts().getNumberOfComments(p);
		return retorno;
	}

	private void validateLoggedUser(String login) throws UserInvalidException {
		Usuario user = gerenteDados.getGerenciadorDeUsuarios()
				.getUsuario(login);
	}

	/**
	 * Remove um post.
	 * 
	 * @param postId
	 *            {@link String}
	 * @throws PersistenceException
	 * @throws ArgumentInvalidException
	 */
	public void removePost(String postId) throws PersistenceException,
			ArgumentInvalidException {
		Post post = getPostPorId(postId);
		while (!post.getListaComentarios().isEmpty()) {
			Comentario removido = post.getListaComentarios().remove(0);
			gerenteDados.getGerenteComentarios().remove(removido);
		}
		listaPosts.remove(post);
		String blogDonoId = post.getIdBlogDono();
		Blog blog = gerenteDados.getGerenteBlogs().getBlog(blogDonoId);
		blog.removePost2(post);

	}

	/**
	 * Valida um Post.
	 * 
	 * @param postId
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 */
	public void validaPostId(String postId) throws ArgumentInvalidException {
		for (Post post : listaPosts) {
			if (post.getId().equals(postId))
				return;
		}
		throw new ArgumentInvalidException(Constantes.POST_INVALIDO);
	}

	/**
	 * Recupera um comentario.
	 * 
	 * @param postId
	 *            {@link String}
	 * @param index
	 *            {@link int}
	 * @return {@link Comentario}
	 * @throws PersistenceException
	 * @throws ArgumentInvalidException
	 */
	public Comentario GetComentario(String postId, int index)
			throws PersistenceException, ArgumentInvalidException {

		Post post = getPostPorId(postId);
		if (index >= post.getListaComentarios().size())
			throw new ArgumentInvalidException(Constantes.INDICE_INVALIDO);
		return post.getComentario(index);
	}

	/**
	 * Recupera a lista de posts.
	 * 
	 * @param blog
	 *            {@link Blog}
	 * @return List<{@link Post}>
	 */
	public List<Post> getListaPostsPorBlog(Blog blog) {
		List<Post> listaPostsProcurados = new ArrayList<Post>();
		for (Post post : listaPosts) {
			if (post.getIdBlogDono().equals(blog.getId()))
				listaPostsProcurados.add(post);
		}
		return listaPostsProcurados;
	}

	/**
	 * Valida o id do post.
	 * 
	 * @param postId
	 *            {@link String}
	 * @param sessionId
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 */
	public void validaPostId(String postId, String sessionId)
			throws ArgumentInvalidException, PersistenceException {
		validaPostId(postId);
		gerenteDados.getGerenteSessoes().validaSessao(sessionId);
		Blog dono = gerenteDados.getGerenteBlogs().getBlog(
				getPostPorId(postId).getIdBlogDono());
		if (!dono.getIdSessao().equals(sessionId))
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);

	}
}
