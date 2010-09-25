package classes;

import interfaces.Constantes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import classes.func.multimidia.Audio;
import classes.func.multimidia.Imagem;
import classes.func.multimidia.Video;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.ComentariosDAO;
import persistencia.daos.PostsDAO;

public class Post {
	private Texto post;
	private PostsDAO postDao;
	private ComentariosDAO comentDao;
	private String id;
	private Imagem imagem;
	private Audio audio;
	private Video video;
	private String blogRaiz;


	




	/**
	 * Construtor da classe Post, que recebe como parametro
	 * o texto do post.
	 * @param post
	 * @throws ArgumentInvalidException
	 */
	public Post(Texto post, String blog) throws ArgumentInvalidException{
		if(validaPost(post)){
			this.post = post;
			setId(gerarId());
			setBlogRaiz(blog);
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
	 * Metodo que gera o id da classe.
	 * @return
	 */
	private String gerarId() {
		return String.valueOf(this.hashCode());
	}
	
	/**
	 * @return o id do Post.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Altera o id do Post.
	 * @param id
	 */
	public void setId(String id) {
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
	
	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}

	public Audio getAudio() {
		return audio;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
	
	public String getBlogRaiz() {
		return blogRaiz;
	}

	public void setBlogRaiz(String blogRaiz) {
		this.blogRaiz = blogRaiz;
	}
	
}
