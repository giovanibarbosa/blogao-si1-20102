package classes.func.usuario;

import java.io.FileNotFoundException;

import classes.gerenciadores.GerenciadorDeBlogs;
import classes.gerenciadores.GerenciadorDePosts;
import classes.gerenciadores.GerenciadorDeSessoes;
import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;

/*
 * #Permite a edição de um post existente. os seguintes campos podem ser alterados:
#*Título do post
#*Texto
#*Áudio
#*Vídeo
#*Imagem
#
#No caso dos recursos multimídia, o cliente deve ser capaz de definir se deseja manter os dados existentes no servidor, ou se serão deletados.
#Restrições:
#    -O usuário deve estar logado no sistema (Fornecimento de um id de sessão válido)
#   
#    -Campos não-nulo:
#        *Título do post    
#Obs.: No caso do acesso via celular, não é necessário implementar a edição dos recursos multimídia existentes, ou seja, será realizada apenas #a edição da parte textual do post

 */

public class FacadeUserStore7 {
	
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	private GerenciadorDeBlogs gerenciaBlogs = new GerenciadorDeBlogs(gerente);
	private GerenciadorDePosts gerenteDePosts = new GerenciadorDePosts(gerente, gerenciaBlogs);
	
	public void loadData() {
		
	}
	
	/**
	 * Loga
	 * @param login
	 * @param senha
	 * @return String 
	 * @throws PersistenceException
	 * @throws FileNotFoundException
	 * @throws ArgumentInvalidException
	 */
	public String logon(String login, String senha) throws PersistenceException,
			FileNotFoundException, ArgumentInvalidException{
		return gerente.logon(login, senha);
	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public int getNumberOfBlogsByLogin(String login) throws PersistenceException, ArgumentInvalidException, FileNotFoundException  {
		return gerenciaBlogs.totalDeBlogsPorLogin(login);
			
	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public int getNumberOfBlogsBySessionId(String sessionID) throws FileNotFoundException,
			ArgumentInvalidException, PersistenceException {
		return gerenciaBlogs.totalDeBlogsPorSessao(sessionID);	
	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public int getBlogBySessionId(String sessionID, int index) throws 
					FileNotFoundException, ArgumentInvalidException, PersistenceException {
		return gerenciaBlogs.recuperaIdBlogDesejado(sessionID, index);
	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public int getBlogByLogin(String login, int index) throws FileNotFoundException,
					PersistenceException {
		return gerenciaBlogs.recuperaIdBlogPorLogin(login, index);
	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public int getNumberOfPosts(String blogID) throws 
					FileNotFoundException, PersistenceException {
		return gerenciaBlogs.totalDePosts(blogID);
	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public int getPost(String blogID, int index) throws NumberFormatException,
				FileNotFoundException, PersistenceException {
		return gerenciaBlogs.recuperaIdDoPost(blogID, index);
	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	//#o metodo changePostInformation sera responsavel por alterar apenas a
	//parte textual de um post.
	public void changePostInformation(String sessionID, String postID, String atributo, String novoTexto)
					throws FileNotFoundException, ArgumentInvalidException, PersistenceException {
		gerenciaBlogs.mudarInformacaoDoPost(sessionID, postID, atributo, novoTexto);
	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public int getNumberOfSounds(String postID) throws FileNotFoundException,
					PersistenceException {
		return gerenteDePosts.recuperaTotalDeMusicasDoPost(postID);		
	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public int getNumberOfMovies(String postID) throws FileNotFoundException, PersistenceException {
		return gerenteDePosts.recuperaTotalDeFilmesDoPost(postID);
	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public int getNumberOfPictures(String postID) throws FileNotFoundException, PersistenceException {
		return gerenteDePosts.recuperaTotalDeImagensDoPost(postID);		
	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public int getMovie(String postID, int index) throws FileNotFoundException, PersistenceException {
		return gerenteDePosts.recuperaIDvideo(postID, index); //id movie
	}
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public int getSound(String postID, int index) throws FileNotFoundException, PersistenceException {
		return gerenteDePosts.recuperaIDaudio(postID, index);		 //id som
	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public int getPicture(String postID, int index) throws FileNotFoundException, PersistenceException {
		return gerenteDePosts.recuperaIDimagem(postID, index);	//id imagem
	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public void deleteMovie(String sessionID, String idMovie) throws FileNotFoundException,
					PersistenceException, ArgumentInvalidException {
		gerenteDePosts.deletaVideo(sessionID, idMovie);

	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public void deletePicture(String sessionID, String idImagem) throws 
				PersistenceException, ArgumentInvalidException, FileNotFoundException {
		gerenteDePosts.deletaImagem(sessionID, idImagem);

	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public void deleteSound(String sessionID, String idSom) throws FileNotFoundException
					, ArgumentInvalidException, PersistenceException {
		gerenteDePosts.deletaMusica(sessionID, idSom);

	}
	
	public void logoff(String idSession) throws ArgumentInvalidException{
		gerente.logoff(idSession);
	}
	
	//TODO Fazer...
	public void saveData() {}
	
	
	
	
	
	
	
	
	

}
