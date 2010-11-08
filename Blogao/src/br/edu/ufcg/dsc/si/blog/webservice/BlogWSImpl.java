package br.edu.ufcg.dsc.si.blog.webservice;

import br.edu.ufcg.dsc.si.blog.webservice.BlogWS;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.func.Data;
import facades.FacadeBlog;
import facades.FacadeComentarios;
import facades.FacadeDados;
import facades.FacadePerfil;
import facades.FacadePost;
import facades.FacadeSessao;
import facades.FacadeUsuario;

/**
 * @author tiagohsl
 *
 */
public class BlogWSImpl implements BlogWS {
	private FacadeDados facadeDados = FacadeDados.getInstance();
	private FacadePost facadePost = FacadePost.getInstance();
	private FacadeComentarios facadeComent = FacadeComentarios.getInstance();
	private FacadeUsuario facadeUser = FacadeUsuario.getInstance();
	private FacadeBlog facadeBlog = FacadeBlog.getInstance();
	private FacadePerfil facadePerfil = FacadePerfil.getInstance();
	private FacadeSessao facadeSessao = FacadeSessao.getInstance();

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#addComment(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addComment(String sessionId, String postId, String texto)
			throws Exception {
		return facadeComent.addComentario(sessionId, postId, texto);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#addPostAnnouncements(java.lang.String, java.lang.String)
	 */
	@Override
	public void addPostAnnouncements(String sessionId, String blogId)
			throws Exception {
		facadeUser.addPostAnnouncement(sessionId, blogId);
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#addSubComment(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addSubComment(String sessionId, String commentId, String texto)
			throws Exception {
		return facadeComent.addSubComment(sessionId, commentId, texto);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#attachMovie(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String attachMovie(String sessionId, String postId,
			String descricao, String dado) throws Exception {
		return facadePost.attachMovie(sessionId, postId, descricao, dado);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#attachPicture(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String attachPicture(String sessionId, String postId,
			String descricao, String dado) throws Exception {
		return facadePost.attachPicture(sessionId, postId, descricao, dado);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#attachSound(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String attachSound(String sessionId, String postId,
			String descricao, String dado) throws Exception {
		return facadePost.attachSound(sessionId, postId, descricao, dado);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#changeBlogInformation(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void changeBlogInformation(String sessionId, String BlogId,
			String atributo, String valor) throws Exception {
		facadeBlog.changeBlogInformation(sessionId, BlogId, atributo, valor);
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#changePostInformation(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void changePostInformation(String sessionId, String postId,
			String atributo, String valor) throws Exception {
		facadePost.mudarInformacaoDoPost(sessionId, postId, atributo, valor);
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#changeProfileInformation(java.lang.String, java.lang.String)
	 */
	@Override
	public void changeProfileInformation(String sessionId, String atributo, String valor)
			throws Exception {
		facadePerfil.changeProfileInformation(sessionId, atributo, valor);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#createBlog(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String createBlog(String sessionId, String titulo, String descricao)
			throws Exception {
		return facadeBlog.createBlog(sessionId, titulo, descricao); //FIXME caso quebrar verificar aqui
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#createPost(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String createPost(String sessionId, String blogId, String titulo,
			String texto) throws Exception {
		return facadePost.createPost(sessionId, blogId, titulo, texto);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#createProfile(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void createProfile(String login, String senha, String nomeExibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quemEuSou, String filmes,
			String musicas, String livros) throws Exception {
		
		facadePerfil.createProfile(login, senha, nomeExibicao, 
				email, sexo, dataNasc, endereco, interesses, quemEuSou, filmes,
				musicas, livros);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#createSubBlog(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String createSubBlog(String sessionId, String blogId, String titulo,
			String descricao) throws Exception {
		return facadeBlog.createSubBlog(sessionId, blogId, titulo, descricao);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#deleteAnnouncement(java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteAnnouncement(String sessionId, String announcementId)
			throws Exception {
		facadeUser.deleteAnnouncement(sessionId, announcementId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#deleteBlog(java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteBlog(String sessionId, String blogId) throws Exception {
		facadeBlog.deleteBlog(sessionId, blogId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#deleteMovie(java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteMovie(String sessionId, String videoId) throws Exception {
		facadePost.deletaVideo(sessionId, videoId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#deletePicture(java.lang.String, java.lang.String)
	 */
	@Override
	public void deletePicture(String sessionId, String pictureId)
			throws Exception {
		facadePost.deletaImagem(sessionId, pictureId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#deletePost(java.lang.String, java.lang.String)
	 */
	@Override
	public void deletePost(String sessionId, String postId) throws Exception {
		facadePost.deletePost(sessionId, postId);	
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#deleteProfile(java.lang.String)
	 */
	@Override
	public void deleteProfile(String sessionId) throws Exception {
		facadePerfil.deletePerfil(sessionId);
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#deleteSound(java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteSound(String sessionId, String soundId) throws Exception {
		facadePost.deletaAudio(sessionId, soundId);	
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#findBlogByName(java.lang.String)
	 */
	@Override
	public String findBlogByName(String match) {
		return facadeBlog.getBlogsByName(match).toString().replace(" ", "");
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#findProfileByGender(java.lang.String)
	 */
	@Override
	public String findProfileByGender(String match) {
		return facadePerfil.getPerfilPorSexo(match)
		.toString().replace(" ", "");
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#findProfileByInterests(java.lang.String)
	 */
	@Override
	public String findProfileByInterests(String match) {
		return facadePerfil.getPerfilPorInteresse(match).toString().replace(" ", "");
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#findProfileByName(java.lang.String)
	 */
	@Override
	public String findProfileByName(String match) {
		return facadePerfil.getPerfilPorNome(match).toString().replace(" ", "");
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getAnnouncement(java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getAnnouncement(String sessionId, Integer index)
			throws Exception {
		return facadeUser.getAnnouncement(sessionId, index);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getBlogByLogin(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Integer getBlogByLogin(String login, Integer index) throws Exception {
		return Integer.valueOf(facadeBlog.recuperaIdBlogPorLogin(login,
				index));
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getBlogBySessionId(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Integer getBlogBySessionId(String sessiongId, Integer index)
			throws Exception {
		return Integer.valueOf(facadeBlog.recuperaIdBlogDesejado(sessiongId,
				index));
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getBlogInformation(java.lang.String, java.lang.String)
	 */
	@Override
	public String getBlogInformation(String BlogId, String atributo)
			throws Exception {
		return facadeBlog.getBlogInformation(BlogId, atributo);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getComment(java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getComment(String postId, Integer index) throws Exception {
		return facadePost.GetComentario(postId, index).getId();
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getCommentAuthor(java.lang.String)
	 */
	@Override
	public String getCommentAuthor(String commentId) throws Exception {
		return facadeComent.getCommentAuthor(commentId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getCommentText(java.lang.String)
	 */
	@Override
	public String getCommentText(String commentId) throws Exception {
		return facadeComent.getTextoComentario(commentId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getMovie(java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getMovie(String postId, Integer index) throws Exception {
		return facadePost.getVideo(postId, index);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getMovieData(java.lang.String)
	 */
	@Override
	public String getMovieData(String movieId) throws Exception {
		return facadePost.getDadoDoVideo(movieId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getMovieDescription(java.lang.String)
	 */
	@Override
	public String getMovieDescription(String movieId) throws Exception {
		return facadePost.getDescricaoDoVideo(movieId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfAllSubComments(java.lang.String)
	 */
	@Override
	public Integer getNumberOfAllSubComments(String commentId) throws Exception {
		return facadeComent.getNumberOfAllSubComments(commentId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfAnnouncements(java.lang.String)
	 */
	@Override
	public Integer getNumberOfAnnouncements(String sessionId) throws Exception {
		return facadeUser.getNumberOfAnnouncements(sessionId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfBlogsByLogin(java.lang.String)
	 */
	@Override
	public Integer getNumberOfBlogsByLogin(String login) throws Exception {
		return facadeBlog.totalBlogsByLogin(login);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfBlogsBySessionId(java.lang.String)
	 */
	@Override
	public Integer getNumberOfBlogsBySessionId(String sessionId)
			throws Exception {
		return facadeBlog.totalBlogsBySessionId(sessionId);
	}

	@Override
	public Integer getNumberOfAllPosts(String blogId) throws ArgumentInvalidException{
		return facadeBlog.getNumberOfAllPosts(blogId);
	}
	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfComments(java.lang.String)
	 */
	@Override
	public Integer getNumberOfComments(String postId) throws Exception {
		return facadePost.getNumberOfComments(postId);
	}
	/* (non-Javadoc
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfComments(java.lang.String)
	 */
	public String getNumberOfComments(String login, String blogId) throws UserInvalidException, ArgumentInvalidException, PersistenceException{
		return String.valueOf(facadePost.getNumberOfComments(login, blogId));
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfMovies(java.lang.String)
	 */
	@Override
	public Integer getNumberOfMovies(String postId) {
		return facadePost.getNumeroDeVideos(postId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfPictures(java.lang.String)
	 */
	@Override
	public Integer getNumberOfPictures(String postId) {
		return facadePost.getNumeroDeImagens(postId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfPosts(java.lang.String)
	 */
	@Override
	public Integer getNumberOfPosts(String blogId) throws Exception {
		return facadeBlog.totalPostsByIdBlog(blogId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfSounds(java.lang.String)
	 */
	@Override
	public Integer getNumberOfSounds(String postId) {
		return facadePost.getNumeroDeSons(postId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfSubAllBlogs(java.lang.String)
	 */
	@Override
	public Integer getNumberOfAllSubBlogs(String blogId) throws Exception {
		return facadeBlog.getNumberOfAllSubBlogs(blogId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfSubBlogs(java.lang.String)
	 */
	@Override
	public Integer getNumberOfSubBlogs(String blogId) throws Exception {
		return facadeBlog.getNumberOfSubBlogs(blogId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getNumberOfSubComments(java.lang.String)
	 */
	@Override
	public Integer getNumberOfSubComments(String commentId) throws Exception {
		return facadeComent.getNumberOfSubComments(commentId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getPicture(java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getPicture(String postId, Integer index) throws Exception {
		return facadePost.getImagem(postId, index);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getPictureData(java.lang.String)
	 */
	@Override
	public String getPictureData(String pictureId) throws Exception {
		return facadePost.getDadoDaImagem(pictureId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getPictureDescription(java.lang.String)
	 */
	@Override
	public String getPictureDescription(String pictureId) throws Exception {
		return facadePost.getDescricaoDaImagem(pictureId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getPost(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Integer getPost(String blogId, Integer index) throws Exception {
		return Integer.valueOf(facadeBlog.getIdPost(blogId, index));
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getPostInformation(java.lang.String, java.lang.String)
	 */
	@Override
	public String getPostInformation(String postId, String atributo)
			throws Exception {
		return facadePost.informacaoDoPost(postId, atributo);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getPostJustCreated(java.lang.String)
	 */
	@Override
	public String getPostJustCreated(String announcementId) throws Exception {
		return facadeUser.getPostJustCreated(announcementId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getProfileInformation(java.lang.String, java.lang.String)
	 */
	@Override
	public String getProfileInformation(String login, String atributo)
			throws Exception {
		
		return facadePerfil.getProfileInformation(login, atributo);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getProfileInformationBySessionId(java.lang.String, java.lang.String)
	 */
	@Override
	public String getProfileInformationBySessionId(String sessionId,
			String atributo) throws Exception {		
		return facadeSessao.getProfileInformationBySessionId(sessionId, atributo);
	}
	//TODO AQUI

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getSound(java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getSound(String postId, Integer index) throws Exception {
		return facadePost.getSom(postId, index);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getSoundData(java.lang.String)
	 */
	@Override
	public String getSoundData(String soundId) throws Exception {
		return facadePost.getDadoDoSom(soundId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getSoundDescription(java.lang.String)
	 */
	@Override
	public String getSoundDescription(String soundId) throws Exception {
		return facadePost.getDescricaoDoSom(soundId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getSubBlog(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Integer getSubBlog(String blogId, Integer index) throws Exception {
		return Integer.valueOf(facadeBlog.getSubBlogId(blogId, index));
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#getSubComment(java.lang.String, java.lang.Integer)
	 */
	@Override
	public String getSubComment(String commentId, Integer index)
			throws Exception {
		return facadeComent.getSubComment(commentId, index);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#isUserLogged(java.lang.String)
	 */
	@Override
	public Boolean isUserLogged(String login) throws Exception {
		return facadeSessao.isUserLogged(login);		
	}
	
	public String todaysDate(){
		return new Data().todaysDate();
	}

	
	public void cleanPersistence() {
		facadeDados.cleanPersistence();
	}

	public void loadData() throws Exception {
		facadeDados.loadData();
		
	}

	public void saveData() throws Exception {
		facadeDados.saveData();
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#logoff(java.lang.String)
	 */
	@Override
	public void logoff(String sessionId) throws Exception {
		facadeSessao.logoff(sessionId);		
		
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.dsc.si.blog.webservice.BlogWS#logon(java.lang.String, java.lang.String)
	 */
	@Override
	public String logon(String login, String senha) throws Exception {
		return facadeSessao.logon(login, senha);
	}
}