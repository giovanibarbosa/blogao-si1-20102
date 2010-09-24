package classes;

import interfaces.Constantes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.ComentariosDAO;
import persistencia.daos.PostsDAO;

public class Post {
	private Texto post;
	private PostsDAO postDao;
	private ComentariosDAO comentDao;
	private int id;


	
	/**
	 * Construtor da classe Post, que recebe como parametro
	 * o texto do post.
	 * @param post
	 * @throws ArgumentInvalidException
	 */
	public Post(Texto post) throws ArgumentInvalidException{
		if(validaPost(post)){
			this.post = post;
		}else{
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO);
		}
	}
	
	/**destructor do post.
	 * 
	 * @return
	 * @throws PersistenceException 
	 */
	public void deleta() throws PersistenceException{
		postDao.deletar(this);
		
	}
	

	
	/**
	 * retorna a lista de comentarios do post.
	 * @return
	 * @throws FileNotFoundException
	 */
	public List<Comentario> getComentarios(){
		List<Comentario>listaDeComentarios = new ArrayList<Comentario>();
		try {
			listaDeComentarios = comentDao.recuperaComentarios();
		} catch (Exception e) {
			System.out.println(Constantes.ERRO_LISTA_COMENTARIOS);
		}
		return listaDeComentarios;
	}
	/**
	 * Adiciona um comentario ao um post.
	 * @param comentario
	 * @return
	 * @throws FileNotFoundException 
	 */
	public boolean addComentario(Comentario comentario){
		if(getComentarios().contains(comentario)){
			return false;
		}else{
			try {
				comentDao.criar(comentario);
			} catch (PersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
	}
	
	/**
	 * remove um comentario do post.
	 * @param comentario
	 * @return
	 */
	public boolean removeComentario(Comentario comentario){
		if(getComentarios().contains(comentario)){
			try {
				comentDao.deletar(comentario);
			} catch (PersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Salva as alteracoes do post.
	 * @throws PersistenceException
	 * @throws IOException
	 */
	public void saveData() throws PersistenceException, IOException{
		postDao.getInstance();
		postDao.atualizar(this , this);
	}
	
	/**
	 * @return o id do Post.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Altera o id do Post.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Metodo que valida as exigencias do post.
	 * @param post
	 * @return
	 */
	private boolean validaPost(Texto post) {
		if(post == null || post.getCorpo().trim().equals(""))
			return true;
		else
			return true;
	}
	
}
