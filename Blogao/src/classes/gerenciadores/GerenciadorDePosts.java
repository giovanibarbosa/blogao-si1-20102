package classes.gerenciadores;

import interfaces.Constantes;
import interfaces.Gerenciador;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.BlogsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;
import classes.func.Data;
import classes.func.multimidia.Audio;
import classes.func.multimidia.Imagem;
import classes.func.multimidia.Video;
import classes.func.usuario.Usuario;
import classes.gerenciadores.GerenciadorDeBlogs;
import classes.Comentario;
import classes.Post;
import classes.Blog;
import classes.Texto;

public class GerenciadorDePosts implements Gerenciador {
	private BlogsDAO blogsDAO = BlogsDAO.getInstance();
	private PostsDAO postsDAO = PostsDAO.getInstance();
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private GerenciadorDeBlogs gerenteDeBlogs;
	private GerenciadorDeSessoes gerenteDeSessao;
	private List<Post> listaPosts;

	private static final int TEXTO = 110256354;
	private static final int TITULO = -873444423;
	private static final int DATA_CRIACAO = 358082837;

	public GerenciadorDePosts(GerenciadorDeSessoes gereteDeSessao,
			GerenciadorDeBlogs gerenteBlogs) {
		this.gerenteDeSessao = gereteDeSessao;
		this.gerenteDeBlogs = gerenteBlogs;
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
	 */
	public String createPost(String idSessao, String blogId, String titulo,
			String texto) throws IOException, ArgumentInvalidException,
			PersistenceException {
		Post post;
		Texto txt = new Texto(titulo, texto);
		String log = gerenteDeSessao.getLogin(idSessao);
		Usuario user = userDAO.recupera(log);
		Blog blog = null;

		if (blogId == null || blogId.trim().isEmpty())
			throw new ArgumentInvalidException(Constantes.BLOG_INVALIDO);
		post = new Post(titulo, texto);
		post.setDataCriacao(new Date());

		List<Usuario> listaUser = userDAO.recuperaUsuarios();
		for (Usuario u : listaUser) {
			for (Blog b : u.getListaBlogs()) {
				if (b.getId().equals(blogId)) {
					if (!u.equals(user))
						throw new ArgumentInvalidException(
								Constantes.SESSAO_INVALIDA);
					else
						blog = b;
				}
			}
		}

		if (blog == null)
			throw new ArgumentInvalidException(Constantes.BLOG_INVALIDO);

		blog.addPost(post);
		user.removeBlog2(blog);
		user.addBlog2(blog);
		userDAO.atualizar(user);
		blogsDAO.atualizar(blog);
		return post.getId();
	}
	
	

	public void deletePost(String sessionId, String postId) throws ArgumentInvalidException, PersistenceException, IOException {
		String log = gerenteDeSessao.getLogin(sessionId);
		Usuario user = userDAO.recupera(log);
		Post post = null;
		Blog blog = null;
		List<Usuario> listaUser = userDAO.recuperaUsuarios();
		
		if (postId == null || postId.trim().isEmpty())
			throw new ArgumentInvalidException(Constantes.POST_INVALIDO);
		
		
		for(Usuario u : listaUser){
			for(Blog blg : user.getListaBlogs()){
				for(Post pt : blg.getListaDePostagens()){
					if(pt.getId().equals(postId))
						if (!u.equals(user)){
							throw new ArgumentInvalidException(
									Constantes.SESSAO_INVALIDA);
						}else{
							post = pt;
							blog = blg;
						}
				}
			}
		}
		
		if(post == null)
			throw new ArgumentInvalidException(Constantes.POST_INVALIDO);
			
		blog.removePost(post);
		userDAO.atualizar(user);
		postsDAO.deletar(post);
		postsDAO.atualizar(post);
		
	}
	

	@Override
	public void saveData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadData() {
		try {
			listaPosts = postsDAO.recuperaPosts();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			listaPosts = new ArrayList<Post>();
		}

	}

	public Blog getBlog(String idBlog, String idSessao)
			throws FileNotFoundException, PersistenceException,
			ArgumentInvalidException {
		Blog blog = blogsDAO.recupera(idBlog);
		if (!idSessao.equals(blog.getIdSessao())) {
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
		}
		return blog;
	}

	public int getNumberOfComments(String postId) throws FileNotFoundException,
			PersistenceException {
		return postsDAO.recupera(postId).getNumberOfComments();
	}

	public int recuperaTotalDeMusicasDoPost(String postID)
			throws FileNotFoundException, PersistenceException {
		Post postRecuperado = postsDAO.recupera(postID);
		int totalDeAudio = 0;
		for (Audio audio : postRecuperado.getListaDeAudio()) {
			totalDeAudio++;
		}
		return totalDeAudio;
	}

	public int recuperaTotalDeFilmesDoPost(String postID)
			throws FileNotFoundException, PersistenceException {
		Post postRecuperado = postsDAO.recupera(postID);
		int totalDeFilme = 0;
		for (Audio audio : postRecuperado.getListaDeAudio()) {
			totalDeFilme++;
		}
		return totalDeFilme;
	}

	public int recuperaTotalDeImagensDoPost(String postID)
			throws FileNotFoundException, PersistenceException {
		Post postRecuperado = postsDAO.recupera(postID);
		int totalDeImagens = 0;
		for (Audio audio : postRecuperado.getListaDeAudio()) {
			totalDeImagens++;
		}
		return totalDeImagens;
	}

	public int recuperaIDaudio(String postID, int index)
			throws FileNotFoundException, PersistenceException {
		Post postRecuperado = postsDAO.recupera(postID);
		return Integer.valueOf(postRecuperado.getListaDeAudio().get(index)
				.getId());

	}

	public int recuperaIDvideo(String postID, int index)
			throws FileNotFoundException, PersistenceException {
		Post postRecuperado = postsDAO.recupera(postID);
		return Integer.valueOf(postRecuperado.getListaDeVideo().get(index)
				.getId());

	}

	public int recuperaIDimagem(String postID, int index)
			throws FileNotFoundException, PersistenceException {
		Post postRecuperado = postsDAO.recupera(postID);
		return Integer.valueOf(postRecuperado.getListaDeImagem().get(index)
				.getId());
	}

	// FIXME .ARRUMAR OUTRO JEITO DE FAZER.
	public void deletaVideo(String sessionID, String idMovie)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException {
		String log = gerenteDeSessao.getLogin(sessionID);
		Usuario user = userDAO.recupera(log);
		for (Blog blog : user.getListaBlogs()) {
			for (Post post : blog.getListaDePostagens())
				for (Video vid : post.getListaDeVideo())
					if (vid.getId().equals(idMovie)) {
						post.getListaDeVideo().remove(vid);
					}
		}
	}

	// FIXME .ARRUMAR OUTRO JEITO DE FAZER.
	public void deletaImagem(String sessionID, String idImagem)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException {
		String log = gerenteDeSessao.getLogin(sessionID);
		Usuario user = userDAO.recupera(log);
		for (Blog blog : user.getListaBlogs()) {
			for (Post post : blog.getListaDePostagens())
				for (Imagem imag : post.getListaDeImagem())
					if (imag.getId().equals(idImagem)) {
						post.getListaDeImagem().remove(imag);
					}
		}
	}

	// FIXME .ARRUMAR OUTRO JEITO DE FAZER.
	public void deletaMusica(String sessionID, String idmusica)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException {

		String log = gerenteDeSessao.getLogin(sessionID);
		Usuario user = userDAO.recupera(log);
		for (Blog blog : user.getListaBlogs()) {
			for (Post post : blog.getListaDePostagens())
				for (Audio mus : post.getListaDeAudio())
					if (mus.getId().equals(idmusica)) {
						post.getListaDeAudio().remove(mus);
					}
		}
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

	public String attachSound(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException {

		Audio audio = new Audio(descricao, dado);
		String login = gerenteDeSessao.getLogin(sessionId);
		Usuario us = userDAO.recupera(login);
		Post post = null;
		Blog blog = null;
		for (Blog b : us.getListaBlogs()) {
			for (Post p : b.getListaDePostagens()) {
				if (p.getId().equals(postId)) {
					post = p;
					blog = b;
				}
			}
		}
		if (post != null && blog != null) {
			post.addAudio(audio);
			blog.addPost(post);
			us.removeBlog2(blog);
			us.addBlog2(blog);
			userDAO.atualizar(us);
		}
		return audio.getId();
	}

	public String attachMovie(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException {
		Video video = new Video(descricao, dado);
		String login = gerenteDeSessao.getLogin(sessionId);
		Usuario us = userDAO.recupera(login);
		Post post = null;
		Blog blog = null;
		for (Blog b : us.getListaBlogs()) {
			for (Post p : b.getListaDePostagens()) {
				if (p.getId().equals(postId)) {
					post = p;
					blog = b;
				}
			}
		}
		if (post != null && blog != null) {
			post.addVideo(video);
			blog.addPost(post);
			us.removeBlog2(blog);
			us.addBlog2(blog);
			userDAO.atualizar(us);
		}
		return video.getId();
	}

	public String attachPicture(String sessionId, String postId,
			String descricao, String dado) throws ArgumentInvalidException,
			PersistenceException, IOException {
		Imagem imagem = new Imagem(descricao, dado);
		String login = gerenteDeSessao.getLogin(sessionId);
		Usuario us = userDAO.recupera(login);
		Post post = null;
		Blog blog = null;
		for (Blog b : us.getListaBlogs()) {
			for (Post p : b.getListaDePostagens()) {
				if (p.getId().equals(postId)) {
					post = p;
					blog = b;
				}
			}
		}
		if (post != null && blog != null) {
			post.addImagem(imagem);
			blog.addPost(post);
			us.removeBlog2(blog);
			us.addBlog2(blog);
			userDAO.atualizar(us);
		}
		return imagem.getId();
	}

	public String informacaoDoPost(String idDoPost, String atributo)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException {

		List<Usuario> users = userDAO.recuperaUsuarios();
		Post post = null;

		for (Usuario u : users) {
			for (Blog b : u.getListaBlogs()) {
				for (Post p : b.getListaDePostagens()) {
					if (idDoPost.equals(p.getId()))
						post = p;
				}
			}
		}
		int codigoAtributo = atributo.hashCode();
		String retorno = "";
		if (post != null) {
			switch (codigoAtributo) {
			case TITULO:
				retorno = post.getTitulo();
				break;
			case TEXTO:
				retorno = post.getText();
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

	public int getNumeroDeSons(String idDoPost) throws FileNotFoundException {
		Post post = getPostPorId(idDoPost);
		return post == null ? 0 : post.getListaDeAudio().size();
	}

	public int getNumeroDeVideos(String idDoPost) throws FileNotFoundException {
		Post post = getPostPorId(idDoPost);
		return post == null ? 0 : post.getListaDeVideo().size();
	}

	public int getNumeroDeImagens(String idDoPost) throws FileNotFoundException {
		Post post = getPostPorId(idDoPost);
		return post == null ? 0 : post.getListaDeImagem().size();
	}
	
	public String getSom(String idDoPost, String index) throws FileNotFoundException {
		Post post = getPostPorId(idDoPost);
		return post.getListaDeAudio().get(Integer.valueOf(index)).getId();
	}

	public String getDescricaoDoSom(String audioId) throws FileNotFoundException {
		Audio audio = getAudio(audioId);
		return audio.getDescricao();
	}
	
	public String getDadoDoSom(String audioId) throws FileNotFoundException {
		Audio audio = getAudio(audioId);		
		return audio.getDado();
	}
	
	private Audio getAudio(String audioId) throws FileNotFoundException {
		List<Usuario> users = userDAO.recuperaUsuarios();
		for (Usuario u : users) {
			for (Blog b : u.getListaBlogs()) {
				for (Post p : b.getListaDePostagens()) {
					for (Audio a : p.getListaDeAudio()) {
						if (audioId.equals(a.getId()))
							return a;
					}
				}
			}
		}
		return null;
	}
	
	private Post getPostPorId(String idDoPost) throws FileNotFoundException {
		List<Usuario> users = userDAO.recuperaUsuarios();
		for (Usuario u : users) {
			for (Blog b : u.getListaBlogs()) {
				for (Post p : b.getListaDePostagens()) {
					if (idDoPost.equals(p.getId()))
						return p;
				}
			}
		}
		return null;
	}

	public String getVideo(String id, String index) throws FileNotFoundException {
		Post post = getPostPorId(id);
		return post.getListaDeVideo().get(Integer.valueOf(index)).getId();
	}
	
	private Video getMovie(String videoId) throws FileNotFoundException {
		List<Usuario> users = userDAO.recuperaUsuarios();
		for (Usuario u : users) {
			for (Blog b : u.getListaBlogs()) {
				for (Post p : b.getListaDePostagens()) {
					for (Video v : p.getListaDeVideo()) {
						if (videoId.equals(v.getId()))
							return v;
					}
				}
			}
		}
		return null;
	}
	
	private Imagem getPicture(String imagemId) throws FileNotFoundException {
		List<Usuario> users = userDAO.recuperaUsuarios();
		for (Usuario u : users) {
			for (Blog b : u.getListaBlogs()) {
				for (Post p : b.getListaDePostagens()) {
					for (Imagem i : p.getListaDeImagem()) {
						if (imagemId.equals(i.getId()))
							return i;
					}
				}
			}
		}
		return null;
	}
	
	public String getDescricaoDoVideo(String videoId) throws FileNotFoundException {
		Video video = getMovie(videoId);
		return video.getDescricao();
	}
	
	public String getDadoDoVideo(String videoId) throws FileNotFoundException {
		Video video = getMovie(videoId);
		return video.getDado();
	}
	
	public String getImagem(String id, String index) throws FileNotFoundException {
		Post post = getPostPorId(id);
		return post.getListaDeImagem().get(Integer.valueOf(index)).getId();
	}
	
	public String getDescricaoDaImagem(String id) throws FileNotFoundException {
		Imagem imagem = getPicture(id);
		return imagem.getDescricao();
	}
	
	public String getDadoDaImagem(String id) throws FileNotFoundException {
		Imagem imagem = getPicture(id);
		return imagem.getDado();
	}
	
}

