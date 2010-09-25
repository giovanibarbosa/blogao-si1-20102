package classes.func.usuario;

import java.io.FileNotFoundException;
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
	
	public void loadData() {
		
	}
	
	/**
	 * Loga
	 * @param login
	 * @param senha
	 * @return
	 * @throws PersistenceException
	 * @throws FileNotFoundException
	 * @throws ArgumentInvalidException
	 */
	public String logon(String login, String senha) throws PersistenceException,
			FileNotFoundException, ArgumentInvalidException{
		try {
			return gerente.logon(login, senha);
		} catch (PersistenceException e){
			throw e;
		}
	}
	
	//TODO Fazer...
	public int getNumberOfBlogsByLogin(String login) {
		return 0;
	}
	
	//TODO Fazer...
	public int getNumberOfBlogsBySessionId(String sessionID) {
		return 0;
	}
	
	//TODO Fazer...
	public int getBlogBySessionId(String sessionID, int index) {
		return 0; //retorna o blogID
	}
	
	//TODO Fazer...
	public int getBlogByLogin(String login, int index) {
		return 0; //retorna o blogID
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
	
	public int getSound(String postID, int index) {
		return 0;		 //id som
	}
	
	public int getPicture(String postID, int index) {
		return 0;		//id imagem
	}
	
	public void deleteMovie(String sessionID, String idMovie) {}
	
	public void deletePicture(String sessionID, String idImagem) {}
	
	public void deleteSound(String sessionID, String idSom) {}
	
	public void logoff (String sessionID) {}
	
	public void saveData() {}
	
	
	
	
	
	
	
	
	

}
