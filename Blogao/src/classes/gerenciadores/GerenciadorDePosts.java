package classes.gerenciadores;

import interfaces.Gerenciador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import persistencia.daos.PostsDAO;
import classes.Blog;
import classes.Comentario;
import classes.Post;
import classes.func.multimidia.Audio;
import classes.func.multimidia.Imagem;
import classes.func.multimidia.Midia;
import classes.func.multimidia.Video;
import classes.func.usuario.Usuario;
import enuns.Constantes;

/**
 * Classe que gerencia Posts.
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 */
public class GerenciadorDePosts implements Gerenciador {
	private List<Post> listaPosts = new ArrayList<Post>();
	private PostsDAO postsDAO = PostsDAO.getInstance();
	private static GerenciadorDePosts instancia;

	private static final int TEXTO = 110256354;
	private static final int TITULO = -873444423;
	private static final int DATA_CRIACAO = 358082837;
	
	private GerenciadorDePosts() {
		
	}
	
	public static GerenciadorDePosts getInstance() {
		if(instancia == null)
			instancia = new GerenciadorDePosts();
		return instancia;
	}

	@Override
	/**
	 * Salva os dados.
	 */
	public void saveData() throws PersistenceException, IOException {
		postsDAO.limparPosts();
		Iterator<Post> it = iteradorPost();
		while(it.hasNext()){
			postsDAO.criar((Post) it.next());
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

	@Override
	/**
	 * Elimina os arquivos persistentes e limpa o ambiente.
	 */
	public void cleanPersistence() {
		postsDAO.limparPosts();
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

		Blog blog = getBlog(blogId);
		verificaDonoDoBlog(idSessao, blog);
		String login = recuperaLoginPorSessionId(idSessao);
		Usuario user = recuperaUsuarioPorLogin(login);
		user.removeBlog(blog);
		Post post = new Post(titulo, texto, blogId);
		blog.addPost(post);
		user.addBlog(blog);
		listaPosts.add(post);
		avisaListeners(blog, post);
		return post.getId();
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
		Blog blog = getBlog(post);
		verificaDonoDoBlog(sessionId, blog);

		String login = recuperaLoginPorSessionId(sessionId);
		Usuario user = recuperaUsuarioPorLogin(login);

		user.removeBlog(blog);
		blog.removePost(post);

		Midia audio = new Midia().fabricaDeMidia(Constantes.AUDIO.getName() ,descricao, dado);
		post.addMidia(audio);

		blog.addPost(post);
		user.addBlog(blog);

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
		Blog blog = getBlog(post);
		verificaDonoDoBlog(sessionId, blog);

		String login = recuperaLoginPorSessionId(sessionId);
		Usuario user = recuperaUsuarioPorLogin(login);

		user.removeBlog(blog);
		blog.removePost(post);

		Midia imagem = new Midia().fabricaDeMidia("imagem" ,descricao, dado);
		post.addMidia(imagem);

		blog.addPost(post);
		user.addBlog(blog);

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
		Blog blog = getBlog(post);
		verificaDonoDoBlog(sessionId, blog);

		String login = recuperaLoginPorSessionId(sessionId);
		Usuario user = recuperaUsuarioPorLogin(login);

		user.removeBlog(blog);
		blog.removePost(post);

		Midia video = new Midia().fabricaDeMidia(Constantes.VIDEO.getName() ,descricao, dado);
		post.addMidia(video);

		blog.addPost(post);
		user.addBlog(blog);

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
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO.getName());

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
				throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO.getName());

			}
		}
		return retorno;
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
			throw new ArgumentInvalidException(Constantes.INDICE_INCORRETO.getName());
		return getSom(index, post);
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
	 * Retorna a descricao da imagem.
	 * 
	 * @param imagemId
	 *            {@link String}
	 * @return descricao da imagem.
	 * @throws FileNotFoundException
	 */
	public String getDescricaoDaImagem(String imagemId)
			throws FileNotFoundException {
	
		Iterator<Post> it = iteradorPost();
		while(it.hasNext()){
			Post post = (Post) it.next();
			for (Imagem imagem : post.getListaDeImagem()) {
				if (imagem.getId().equals(imagemId)) {
					return imagem.getDescricao();
				}
			}
		}
		throw new FileNotFoundException(Constantes.ATRIBUTO_INVALIDO.getName());
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
		Iterator<Post> it = iteradorPost();
		while(it.hasNext()){
			Post post = (Post) it.next();
			for (Imagem imagem : post.getListaDeImagem()) {
				if (imagem.getId().equals(imagemId)) {
					return imagem.getDado();
				}
			}
		}
		throw new FileNotFoundException(Constantes.ATRIBUTO_INVALIDO.getName());
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
			throw new ArgumentInvalidException(Constantes.INDICE_INCORRETO.getName());
		return getImagem(index, post);
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
	
		Iterator<Post> it = iteradorPost();
		while(it.hasNext()){
			Post post = (Post) it.next();
			for (Video video : post.getListaDeVideo()) {
				if (video.getId().equals(videoId)) {
					return video.getDescricao();
				}
			}
		}
		throw new FileNotFoundException(Constantes.ATRIBUTO_INVALIDO.getName());
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
		Iterator<Post> it = iteradorPost();
		while(it.hasNext()){
			Post post = (Post) it.next();
			for (Video video : post.getListaDeVideo()) {
				if (video.getId().equals(videoId)) {
					return video.getDado();
				}
			}
		}
		throw new FileNotFoundException(Constantes.ATRIBUTO_INVALIDO.getName());
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
			throw new PersistenceException(Constantes.INDICE_INCORRETO.getName());
		return getVideo(index, post);
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
		return Integer.valueOf(getVideo(index, postRecuperado));

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
		List<Blog> blogs = getBlogs();
		Blog blg = null;
		for (Blog blog : blogs) {
			if (blog.getId().equals(blogId))
				blg = blog;
		}
		if (blg == null)
			throw new ArgumentInvalidException(Constantes.BLOG_INVALIDO.getName());
		int retorno = 0;
		for (String idPostagem : blg.getListaDePostagens())
			retorno += numeroDeComentarios(idPostagem);
		return retorno;
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
		return Integer.valueOf(getImagem(index, postRecuperado));
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
	 * @param listaPosts
	 *            the listaPosts to set
	 */
	public void setListaPosts(List<Post> listaPosts) {
		this.listaPosts = listaPosts;
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
		validateSession(sessionId);
		Blog dono = getBlogByIdDono(postId);
		if (!dono.getIdSessao().equals(sessionId))
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA.getName());
	
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

		Blog blog = getBlog(post);

		verificaDonoDoBlog(sessionID, blog);

		String login = recuperaLoginPorSessionId(sessionID);
		Usuario user = recuperaUsuarioPorLogin(login);

		user.removeBlog(blog);
		blog.removePost(post);

		post.setAtributo(atributo, novoTexto);

		blog.addPost(post);
		user.addBlog(blog);

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
			throw new ArgumentInvalidException(Constantes.INDICE_INCORRETO.getName());
		removeMidiaByIdVideo(idMovie, post);
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
			throw new ArgumentInvalidException(Constantes.INDICE_INCORRETO.getName());
		removeMidiaAudio(idAudio, post);
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
			throw new ArgumentInvalidException(Constantes.INDICE_INCORRETO.getName());
		removeMidiaImagem(idImagem, post);
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
			removeComentario(removido);
		}
		listaPosts.remove(post);
		String blogDonoId = post.getIdBlogDono();
		Blog blog = getBlog(blogDonoId);
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
		Iterator<Post> it = iteradorPost();
		while(it.hasNext()){
			Post post = (Post) it.next();
			if (post.getId().equals(postId))
				return;
		}
		throw new ArgumentInvalidException(Constantes.POST_INVALIDO.getName());
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
			throw new ArgumentInvalidException(Constantes.INDICE_INVALIDO.getName());
		return post.getComentario(index);
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
		Iterator<Post> it = iteradorPost();
		while(it.hasNext()){
			Post post = (Post) it.next();
			if (post.getId().equals(idDoPost)) {
				return post;
			}
		}
		throw new PersistenceException(Constantes.POST_INVALIDO.getName());
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
		Iterator<Post> it = iteradorPost();
		while(it.hasNext()){
			Post post = (Post) it.next();
			if (post.getIdBlogDono().equals(blog.getId()))
				listaPostsProcurados.add(post);
		}
		return listaPostsProcurados;
	}

	/**
	 * @return the listaPosts
	 */
	public List<Post> getListaPosts() {
		return listaPosts;
	}

	private void avisaListeners(Blog blog, Post post)
			throws UserInvalidException {
		GerenciadorDeDados.getInstance().getGerenteBlogs().avisaListeners(blog, post.getId());
	}

	private Usuario recuperaUsuarioPorLogin(String login)
			throws UserInvalidException {
		return GerenciadorDeDados.getInstance().getGerenteUsuarios().getUsuario(login);
	}

	private String recuperaLoginPorSessionId(String idSessao)
			throws ArgumentInvalidException {
		return GerenciadorDeDados.getInstance().getGerenteSessoes().getLoginPorSessao(idSessao);
	}

	private void verificaDonoDoBlog(String idSessao, Blog blog)
			throws ArgumentInvalidException {
		GerenciadorDeDados.getInstance().getGerenteBlogs().validaDonoBlog(blog, idSessao);
	}

	private Blog getBlog(Post post) throws ArgumentInvalidException {
		return GerenciadorDeDados.getInstance().getGerenteBlogs()
				.getBlog(post.getIdBlogDono());
	}

	private String getSom(int index, Post post) {
		return post.getListaDeAudio().get(Integer.valueOf(index)).getId();
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
	
		Iterator<Post> it = iteradorPost();
		while(it.hasNext()){
			Post post = (Post) it.next();
			for (Audio audio : post.getListaDeAudio()) {
				if (audio.getId().equals(audioId)) {
					return audio;
				}
			}
		}
		throw new FileNotFoundException(Constantes.ATRIBUTO_INVALIDO.getName());
	}

	private String getImagem(int index, Post post) {
		return post.getListaDeImagem().get(index).getId();
	}

	private String getVideo(int index, Post post) {
		return post.getListaDeVideo().get(index).getId();
	}

	private void removeMidiaImagem(String idImagem, Post post) {
		post.removeMidia(post.getMapaImagens().get(idImagem));
	}

	private void removeMidiaByIdVideo(String idMovie, Post post) {
		post.removeMidia(post.getMapaVideos().get(idMovie));
	}

	private Post getPostByVideoID(String movieID) {
		Iterator<Post> it = iteradorPost();
		while(it.hasNext()){
			Post post = (Post) it.next();
			if (post.getMapaVideos().containsKey(movieID)) {
				return post;
			}
		}
		return null;
	}

	private void removeMidiaAudio(String idAudio, Post post) {
		post.removeMidia(post.getMapaAudio().get(idAudio));
	}

	private Post getPostByAudioID(String audioID) {
		Iterator<Post> it = iteradorPost();
		while(it.hasNext()){
			Post post = (Post) it.next();
			if (post.getMapaAudio().containsKey(audioID)) {
				return post;
			}
		}
		return null;
	}

	private Post getPostByImagemID(String imagemID) {
		Iterator<Post> it = iteradorPost();
		while(it.hasNext()){
			Post post = (Post) it.next();
			if (post.getMapaImagens().containsKey(imagemID)) {
				return post;
			}
		}
		return null;
	}

	private int numeroDeComentarios(String idPostagem)
			throws PersistenceException {
		return GerenciadorDeDados.getInstance().getGerentePosts().getNumberOfComments(idPostagem);
	}

	private List<Blog> getBlogs() {
		return GerenciadorDeDados.getInstance().getGerenteBlogs().getListaDeBlogs();
	}

	private void validateLoggedUser(String login) throws UserInvalidException {
		getUser(login);
	}

	private Usuario getUser(String login) throws UserInvalidException {
		return GerenciadorDeDados.getInstance().getGerenciadorDeUsuarios()
				.getUsuario(login);
	}

	private void removeComentario(Comentario removido) {
		GerenciadorDeDados.getInstance().getGerenteComentarios().remove(removido);
	}

	private Blog getBlog(String blogDonoId) throws ArgumentInvalidException {
		return GerenciadorDeDados.getInstance().getGerenteBlogs().getBlog(blogDonoId);
	}

	private Blog getBlogByIdDono(String postId)
			throws ArgumentInvalidException, PersistenceException {
		return GerenciadorDeDados.getInstance().getGerenteBlogs().getBlog(
				getPostPorId(postId).getIdBlogDono());
	}

	private void validateSession(String sessionId)
			throws ArgumentInvalidException {
		GerenciadorDeDados.getInstance().getGerenteSessoes().validaSessao(sessionId);
	}
	
	/**
	 * Iterador sobre a lista de Posts.
	 * @return Iterator<Blog>
	 */
	public Iterator<Post> iteradorPost(){
		return new Iterator<Post>() {
			private int cursor = 0;


			@Override
			public boolean hasNext() {
				while(cursor < listaPosts.size()) {
					if(listaPosts.get(cursor) instanceof Post)
						return true;
					cursor++;
				}				
				return false;
			}


			@Override
			public Post next() {
				try {
					Post b = listaPosts.get(cursor);
					if(b instanceof Post) {
						cursor++;
						return b;
					}
					cursor++;
				} catch (NoSuchElementException e) {
					throw e;
				}
				return next();
			}


			@Override
			public void remove() {				
			}
		};
	}
}
