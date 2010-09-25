package classes.func.usuario;

import java.io.FileNotFoundException;

import classes.gerenciadores.GerenciadorDeBlogs;
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
//		try {
//			return gerente.logon(login, senha);
//		} catch (PersistenceException e){
//			throw e;
//		}
	}
	
	//FIXME VERIFICAR QUANDO O 6 ESTIVER PRONTO
	public int getNumberOfBlogsByLogin(String login) throws FileNotFoundException,
						ArgumentInvalidException, PersistenceException {
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
	
	//TODO Fazer...
	public int getNumberOfPosts(String blogID) {
		return 0; //numero de posts
	}
	
	//TODO Fazer...
	public int getPost(String blogID, int index) {
		return 0; //id do post
	}
	
	//TODO Fazer...
	//#o metodo changePostInformation sera responsavel por alterar apenas a
	//parte textual de um post.
	public void changePostInformation(String sessionID, String postID, String atributo, String novoTexto) {}
	
	//TODO Fazer...
	public int getNumberOfSounds(String postID) {
		return 0;		
	}
	
	//TODO Fazer...
	public int getNumberOfMovies(String postID) {
		return 0;
	}
	
	//TODO Fazer...
	public int getNumberOfPictures(String postID) {
		return 0;		
	}
	
	//TODO Fazer...
	public int getMovie(String postID, int index) {
		return 0; //id movie
	}
	//TODO Fazer...
	public int getSound(String postID, int index) {
		return 0;		 //id som
	}
	
	//TODO Fazer...
	public int getPicture(String postID, int index) {
		return 0;		//id imagem
	}
	
	//TODO Fazer...
	public void deleteMovie(String sessionID, String idMovie) {}
	
	//TODO Fazer...
	public void deletePicture(String sessionID, String idImagem) {}
	
	//TODO Fazer...
	public void deleteSound(String sessionID, String idSom) {}
	
	//TODO Fazer...
	public void logoff (String sessionID) {}
	
	//TODO Fazer...
	public void saveData() {}
	
	
	
	
	
	
	
	
	

}
