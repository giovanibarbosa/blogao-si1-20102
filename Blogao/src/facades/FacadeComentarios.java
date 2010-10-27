package facades;


import interfaces.Constantes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import classes.Comentario;
import classes.Post;
import classes.func.usuario.Usuario;
import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.NewGerenciadorDeBlogs;
import classes.gerenciadores.NewGerenciadorDeComentarios;
import classes.gerenciadores.NewGerenciadorDeDados;

public class FacadeComentarios {
	private static FacadeComentarios instance;
	private NewGerenciadorDeComentarios gerenteComentario;
	
	/**
	 * Contrutor da facade de comentarios.
	 */
	protected FacadeComentarios() {
		gerenteComentario = new NewGerenciadorDeComentarios(NewGerenciadorDeDados.getInstance());
	}

	/**
	 * Metodo que cria o objeto.
	 * Usando o padrão singleton temos uma unica intancia.
	 * @return
	 */
	public static FacadeComentarios getInstance() {
		if (instance == null) {
			instance = new FacadeComentarios();
		}
		return instance;
	}
	

	/**
	 * Metodo do facade que acessa o saveData de Gerenciado de Comentario.
	 * @throws PersistenceException
	 * @throws IOException
	 */
	public void saveData() throws PersistenceException, IOException {
		gerenteComentario.saveData();

	}

	/**
	 * Metodo do facade que acessa o loadData de Gerenciado de Comentario.
	 */
	public void loadData() {
		gerenteComentario.loadData();

	}

	/**
	 * Metodo do facade que acessa o cleanPersistence de Gerenciado de Comentario.
	 */
	public void cleanPersistence() {
		gerenteComentario.cleanPersistence();

	}

	/**
	 *Metodo do facade que acessa o addComentario de Gerenciado de Comentario.
	 * @throws PersistenceException 
	 * @throws ArgumentInvalidException 
	 */
	public String addComentario(String sessionId, String postId, String texto) throws ArgumentInvalidException, PersistenceException{
		return gerenteComentario.addComentario(sessionId, postId, texto);
	}

	/**
	 *Metodo do facade que acessa o  getTextoComentario de Gerenciado de Comentario.
	 * @throws ArgumentInvalidException 
	 */
	public String getTextoComentario(String idComentario) throws ArgumentInvalidException{
		return gerenteComentario.getTextoComentario(idComentario);
	}


	/**
	 * Metodo do facade que acessa o  getCommentAuthor de Gerenciado de Comentario.
	 * @throws PersistenceException 
	 * @throws ArgumentInvalidException 
	 * @throws FileNotFoundException 
	 */
	public String getCommentAuthor(String idComentario) throws FileNotFoundException, ArgumentInvalidException, PersistenceException{
		return gerenteComentario.getCommentAuthor(idComentario);
	}

	/**
	 * Metodo do facade que acessa o remover de Gerenciado de Comentario.
	 */
	public void remove(Comentario removido) {
		gerenteComentario.remove(removido);
	}

	/**
	 * Metodo do facade que acessa o  addSubComment de Gerenciado de Comentario.
	 * @throws ArgumentInvalidException 
	 */
	public String addSubComment(String sessionId, String idComentario,
			String texto) throws ArgumentInvalidException{
		return gerenteComentario.addSubComment(sessionId, idComentario, texto);
	}

	/**
	 * Metodo do facade que acessa o  getSubComment de Gerenciado de Comentario.
	 * @throws ArgumentInvalidException 
	 */
	public String getSubComment(String idComentario, int index) throws ArgumentInvalidException{
		return gerenteComentario.getSubComment(idComentario, index);
	}

	/**
	 *  Metodo do facade que acessa o  getNumberOfSubComments de Gerenciado de Comentario.
	 * @throws ArgumentInvalidException 
	 */
	public int getNumberOfSubComments(String idComentario) throws ArgumentInvalidException{
		return gerenteComentario.getNumberOfSubComments(idComentario);
	}

	/**
	 * Metodo do facade que acessa o getNumberOfAllSubComments de Gerenciado de Comentario.
	 * @throws ArgumentInvalidException 
	 */
	public int getNumberOfAllSubComments(String idComentario) throws ArgumentInvalidException{
		return gerenteComentario.getNumberOfAllSubComments(idComentario);
	}
}
