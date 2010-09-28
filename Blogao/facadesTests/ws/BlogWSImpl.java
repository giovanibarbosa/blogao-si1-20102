package ws;

import classes.gerenciadores.GerenciadorDeDados;

public class BlogWSImpl implements BlogWS {
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();

	@Override
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {
		
		gerenteDados.getGerentePerfis().createProfile(login, senha, nome_exibicao, email, sexo,
				dataNasc, endereco, interesses, quem_sou_eu, filmes, musicas, livros);
		
	}

	@Override
	public String getProfileInformation(String login, String atributo)
			throws Exception {
		
		return gerenteDados.getGerentePerfis().getProfileInformation(login, atributo);
	}

	@Override
	public String logon(String login, String senha) throws Exception {
		
		return gerenteDados.getGerenteSessoes().logon(login, senha);
	}

	@Override
	public Boolean isUserLogged(String login) {
		//FIXME
		return gerenteDados.getGerenteSessoes().isUserLogged(login);
	}

	@Override
	public String getProfileInformationBySessionId(String sessionId,
			String atributo) throws Exception {
		
		return gerenteDados.getGerenteSessoes().getProfileInformationBySessionId(sessionId, atributo);
	}

	@Override
	public void logoff(String sessionId) {
		//FIXME
		gerenteDados.getGerenteSessoes().logoff(sessionId);		
	}

	@Override
	public void changeProfileInformation(String sessionId, String atributo)
			throws Exception {
		// FIXME
		
	}

	@Override
	public String createBlog(String sessionId, String titulo, String descricao)
			throws Exception {
		
		return gerenteDados.getGerenteBlogs().createBlog(sessionId, titulo, descricao);
	}

	@Override
	public String getBlogInformation(String BlogId, String atributo)
			throws Exception {
		
		return gerenteDados.getGerenteBlogs().getBlogInformation(BlogId, atributo);
	}

	@Override
	public void changeBlogInformation(String sessionId, String BlogId,
			String atributo, String valor) throws Exception {
		
		gerenteDados.getGerenteBlogs().changeBlogInformation(sessionId, BlogId,
				atributo, valor);
		
	}

	@Override
	public String createPost(String sessionId, String blogId, String titulo,
			String texto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String attachSound(String sessionId, String postId,
			String descricao, String dado) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String attachMovie(String sessionId, String postId,
			String descricao, String dado) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String attachPicture(String sessionId, String postId,
			String descricao, String dado) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPostInformation(String postId, String atributo)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberOfSounds(String postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberOfMovies(String postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberOfPictures(String postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSound(String postId, Integer index) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSoundDescription(String soundId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSoundData(String soundId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMovie(String postId, Integer index) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMovieDescription(String soundId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMovieData(String soundId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPicture(String postId, Integer index) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPictureDescription(String soundId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPictureData(String soundId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberOfBlogsByLogin(String login) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberOfBlogsBySessionId(String sessiongId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getBlogBySessionId(String sessiongId, Integer index)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getBlogByLogin(String login, Integer index) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberOfPosts(String blogId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getPost(String blogId, Integer index) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePostInformation(String sessionId, String postId,
			String atributo, String valor) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMovie(String sessionId, String videoId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSound(String sessionId, String soundId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePicture(String sessionId, String pictureId)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String addComment(String sessionId, String postId, String texto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberOfComments(String postId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getComment(String postId, Integer index) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCommentText(String commentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCommentAuthor(String commentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(String sessionId, String postId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBlog(String sessionId, String blogId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProfile(String sessionId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findProfileByName(String match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findProfileByInterests(String match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findProfileByGender(String match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findBlogByName(String match) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getNumberOfAnnouncements(String sessionId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPostAnnouncements(String sessionId, String blogId)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAnnouncement(String sessionId, Integer index)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPostJustCreated(String announcementId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAnnouncement(String sessionId, String announcementId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createSubBlog(String sessionId, String blogId, String titulo,
			String descricao) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberOfSubBlogs(String blogId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberOfSubAllBlogs(String blogId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getSubBlog(String blogId, Integer index) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addSubComment(String sessionId, String commentId, String texto)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSubComment(String commentId, Integer index)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberOfSubComments(String commentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNumberOfAllSubComments(String commentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
