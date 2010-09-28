package ws;

import ourExceptions.ArgumentInvalidException;
import classes.gerenciadores.GerenciadorDeDados;

/**
 * @author ivocalado
 *
 */
public class BlogWSImplNovo implements BlogWSNovo {
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#addComment(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addComment(String sessionId, String postId, String texto)
			throws Exception {
		return gerenteDados.getGerenteComentarios().addComentario(sessionId,
				postId, texto);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#addPostAnnouncements(java.lang.String, java.lang.String)
	 */
	@Override
	public void addPostAnnouncements(String sessionId, String blogId)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#addSubComment(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addSubComment(String sessionId, String commentId, String texto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#attachMovie(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String attachMovie(String sessionId, String postId,
			String descricao, String dado) throws Exception {
		return gerenteDados.getGerentePosts().attachMovie(sessionId, postId, descricao, dado);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#attachPicture(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String attachPicture(String sessionId, String postId,
			String descricao, String dado) throws Exception {
		return gerenteDados.getGerentePosts().attachPicture(sessionId, postId, descricao, dado);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#attachSound(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String attachSound(String sessionId, String postId,
			String descricao, String dado) throws Exception {
		return gerenteDados.getGerentePosts().attachSound(sessionId, postId, descricao, dado);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#changeBlogInformation(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void changeBlogInformation(String sessionId, String BlogId,
			String atributo, String valor) throws Exception {
		gerenteDados.getGerenteBlogs().changeBlogInformation(sessionId, BlogId,
				atributo, valor);
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#changePostInformation(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void changePostInformation(String sessionId, String postId,
			String atributo, String valor) throws Exception {
		gerenteDados.getGerentePosts().mudarInformacaoDoPost(sessionId, postId,
				atributo, valor);	
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#changeProfileInformation(java.lang.String, java.lang.String)
	 */
	@Override
	public void changeProfileInformation(String sessionId, String atributo, String valor)
			throws Exception {
		gerenteDados.getGerentePerfis().changeProfileInformation(sessionId, atributo, valor);
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#createBlog(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String createBlog(String sessionId, String titulo, String descricao)
			throws Exception {
		return gerenteDados.getGerenteBlogs().createBlog(sessionId, titulo, descricao);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#createPost(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String createPost(String sessionId, String blogId, String titulo,
			String texto) throws Exception {
		return gerenteDados.getGerentePosts().createPost(sessionId, blogId, titulo, texto);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#createProfile(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {
		
		gerenteDados.getGerentePerfis().createProfile(login, senha, nome_exibicao, email, sexo,
				dataNasc, endereco, interesses, quem_sou_eu, filmes, musicas, livros);
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#createSubBlog(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String createSubBlog(String sessionId, String blogId, String titulo,
			String descricao) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#deleteAnnouncement(java.lang.String, java.lang.String)
	 */
	@Override
	public String deleteAnnouncement(String sessionId, String announcementId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#deleteBlog(java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteBlog(String sessionId, String blogId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#deleteMovie(java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteMovie(String sessionId, String videoId) throws Exception {
		gerenteDados.getGerentePosts().deletaVideo(sessionId, videoId);	
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#deletePicture(java.lang.String, java.lang.String)
	 */
	@Override
	public void deletePicture(String sessionId, String pictureId)
			throws Exception {
		gerenteDados.getGerentePosts().deletaImagem(sessionId, pictureId);	
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#deletePost(java.lang.String, java.lang.String)
	 */
	@Override
	public void deletePost(String sessionId, String postId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#deleteProfile(java.lang.String)
	 */
	@Override
	public void deleteProfile(String sessionId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#deleteSound(java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteSound(String sessionId, String soundId) throws Exception {
		gerenteDados.getGerentePosts().deletaAudio(sessionId, soundId);	
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#findBlogByName(java.lang.String)
	 */
	@Override
	public void findBlogByName(String match) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#findProfileByGender(java.lang.String)
	 */
	@Override
	public void findProfileByGender(String match) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#findProfileByInterests(java.lang.String)
	 */
	@Override
	public void findProfileByInterests(String match) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#findProfileByName(java.lang.String)
	 */
	@Override
	public void findProfileByName(String match) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getAnnouncement(java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getAnnouncement(String sessionId, Integer index)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getBlogByLogin(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Integer getBlogByLogin(String login, Integer index) throws Exception {
		return Integer.valueOf(gerenteDados.getGerenteBlogs().recuperaIdBlogPorLogin(login,
				index));
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getBlogBySessionId(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Integer getBlogBySessionId(String sessiongId, Integer index)
			throws Exception {
		return Integer.valueOf(gerenteDados.getGerenteBlogs().recuperaIdBlogDesejado(sessiongId,
				index));
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getBlogInformation(java.lang.String, java.lang.String)
	 */
	@Override
	public String getBlogInformation(String BlogId, String atributo)
			throws Exception {
		return gerenteDados.getGerenteBlogs().getBlogInformation(BlogId, atributo);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getComment(java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getComment(String postId, Integer index) throws Exception {
		return gerenteDados.getGerentePosts().GetComentario(postId, index).getId();
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getCommentAuthor(java.lang.String)
	 */
	@Override
	public String getCommentAuthor(String commentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getCommentText(java.lang.String)
	 */
	@Override
	public String getCommentText(String commentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getMovie(java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getMovie(String postId, Integer index) throws Exception {
		return gerenteDados.getGerentePosts().getVideo(postId, index);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getMovieData(java.lang.String)
	 */
	@Override
	public String getMovieData(String movieId) throws Exception {
		return gerenteDados.getGerentePosts().getDadoDoVideo(movieId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getMovieDescription(java.lang.String)
	 */
	@Override
	public String getMovieDescription(String movieId) throws Exception {
		return gerenteDados.getGerentePosts().getDescricaoDoVideo(movieId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfAllSubComments(java.lang.String)
	 */
	@Override
	public Integer getNumberOfAllSubComments(String commentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfAnnouncements(java.lang.String)
	 */
	@Override
	public Integer getNumberOfAnnouncements(String sessionId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfBlogsByLogin(java.lang.String)
	 */
	@Override
	public Integer getNumberOfBlogsByLogin(String login) throws Exception {
		return gerenteDados.getGerenteBlogs().totalDeBlogsPorLogin(login);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfBlogsBySessionId(java.lang.String)
	 */
	@Override
	public Integer getNumberOfBlogsBySessionId(String sessiongId)
			throws Exception {
		return gerenteDados.getGerenteBlogs().totalDeBlogsPorSessao(sessiongId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfComments(java.lang.String)
	 */
	@Override
	public Integer getNumberOfComments(String postId) throws Exception {
		return gerenteDados.getGerentePosts().getNumberOfComments(postId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfMovies(java.lang.String)
	 */
	@Override
	public Integer getNumberOfMovies(String postId) {
		return gerenteDados.getGerentePosts().getNumeroDeVideos(postId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfPictures(java.lang.String)
	 */
	@Override
	public Integer getNumberOfPictures(String postId) {
		return gerenteDados.getGerentePosts().getNumeroDeImagens(postId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfPosts(java.lang.String)
	 */
	@Override
	public Integer getNumberOfPosts(String blogId) throws Exception {
		return gerenteDados.getGerenteBlogs().totalDePosts(blogId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfSounds(java.lang.String)
	 */
	@Override
	public Integer getNumberOfSounds(String postId) {
		return gerenteDados.getGerentePosts().getNumeroDeSons(postId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfSubAllBlogs(java.lang.String)
	 */
	@Override
	public Integer getNumberOfSubAllBlogs(String blogId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfSubBlogs(java.lang.String)
	 */
	@Override
	public Integer getNumberOfSubBlogs(String blogId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfSubComments(java.lang.String)
	 */
	@Override
	public Integer getNumberOfSubComments(String commentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getPicture(java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getPicture(String postId, Integer index) throws Exception {
		return gerenteDados.getGerentePosts().getImagem(postId, index);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getPictureData(java.lang.String)
	 */
	@Override
	public String getPictureData(String pictureId) throws Exception {
		return gerenteDados.getGerentePosts().getDadoDaImagem(pictureId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getPictureDescription(java.lang.String)
	 */
	@Override
	public String getPictureDescription(String pictureId) throws Exception {
		return gerenteDados.getGerentePosts().getDescricaoDaImagem(pictureId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getPost(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Integer getPost(String blogId, Integer index) throws Exception {
		return Integer.valueOf(gerenteDados.getGerenteBlogs().recuperaIdDoPost(blogId, index));
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getPostInformation(java.lang.String, java.lang.String)
	 */
	@Override
	public String getPostInformation(String postId, String atributo)
			throws Exception {
		return gerenteDados.getGerentePosts().informacaoDoPost(postId, atributo);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getPostJustCreated(java.lang.String)
	 */
	@Override
	public String getPostJustCreated(String announcementId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getProfileInformation(java.lang.String, java.lang.String)
	 */
	@Override
	public String getProfileInformation(String login, String atributo)
			throws Exception {
		
		return gerenteDados.getGerentePerfis().getProfileInformation(login, atributo);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getProfileInformationBySessionId(java.lang.String, java.lang.String)
	 */
	@Override
	public String getProfileInformationBySessionId(String sessionId,
			String atributo) throws Exception {
		
		return gerenteDados.getGerenteSessoes().getProfileInformationBySessionId(sessionId, atributo);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getSound(java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getSound(String postId, Integer index) throws Exception {
		return gerenteDados.getGerentePosts().getSom(postId, index);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getSoundData(java.lang.String)
	 */
	@Override
	public String getSoundData(String soundId) throws Exception {
		return gerenteDados.getGerentePosts().getDadoDoSom(soundId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getSoundDescription(java.lang.String)
	 */
	@Override
	public String getSoundDescription(String soundId) throws Exception {
		return gerenteDados.getGerentePosts().getDescricaoDoSom(soundId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getSubBlog(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Integer getSubBlog(String blogId, Integer index) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getSubComment(java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getSubComment(String commentId, Integer index)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#isUserLogged(java.lang.String)
	 */
	@Override
	public Boolean isUserLogged(String login) {
		try {
			return gerenteDados.getGerenteSessoes().isUserLogged(login);
		} catch (Exception e) {
			return false;
		}
	}

	
	public void cleanPersistence() {
		// TODO Auto-generated method stub
		
	}

	public void loadData() {
		// TODO Auto-generated method stub
		
	}

	public void saveData() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#logoff(java.lang.String)
	 */
	@Override
	public void logoff(String sessionId) throws Exception {
		gerenteDados.getGerenteSessoes().logoff(sessionId);		
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#logon(java.lang.String, java.lang.String)
	 */
	@Override
	public String logon(String login, String senha) throws Exception {
		return gerenteDados.getGerenteSessoes().logon(login, senha);
	}

}