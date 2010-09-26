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

	private List<Comentario> comentarios;
	private List<Audio> listaDeAudio;
	private List<Video> listaDeVideo;
	private List<Imagem> listaDeImagem;

	private static final int TEXTO = 110256354;
	private static final int TITULO = -873444423;

	/**
	 * Construtor da classe Post, que recebe como parametro o texto do post.
	 * 
	 * @param post
	 * @throws ArgumentInvalidException
	 */
	public Post(Texto post, String idBlog) throws ArgumentInvalidException {
		if (idBlog != null || !idBlog.trim().isEmpty() || validaPost(post)) {
			this.post = post;
			setId(gerarId());
			setBlogRaiz(idBlog);
			comentarios = new ArrayList<Comentario>();
			listaDeAudio = new ArrayList<Audio>();
			listaDeVideo = new ArrayList<Video>();
			listaDeImagem = new ArrayList<Imagem>();
		} else {
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO);
		}
	}

	/**
	 * Classe que adiciona um comentario a lista.
	 * 
	 * @param coment
	 */
	public void addComentario2(Comentario coment) {
		if (!comentarios.contains(coment))
			comentarios.add(coment);
	}

	/**
	 * Remove o comentarios da lista.
	 * 
	 * @param coment
	 */
	public void removeComentario2(Comentario coment) {
		if (comentarios.contains(coment))
			comentarios.remove(coment);
	}

	/**
	 * Retorna a lista de comentarios.
	 * 
	 * @return
	 */
	public List<Comentario> getListaComentarios() {
		return comentarios;
	}

	/**
	 * Retorna o tamanho da lista de comentarios.
	 * 
	 * @return
	 */
	public int getTamanhoDaLista() {
		return comentarios.size();
	}

	public void setAtributo(String atributo, String mudanca)
			throws ArgumentInvalidException {
		int codigoAtributo = atributo.hashCode();

		switch (codigoAtributo) {

		case (TEXTO):
			this.getTexto().setCorpo(mudanca);
		case (TITULO):
			this.getTexto().setTitulo(mudanca);
		default:
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO2);
		}
	}

	/**
	 * destructor do post.
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public void deleta() throws PersistenceException {
		postDao.deletar(this);

	}

	/**
	 * retorna a lista de comentarios do post.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public List<Comentario> getComentarios() {
		List<Comentario> listaDeComentarios = new ArrayList<Comentario>();
		try {
			listaDeComentarios = comentDao.recuperaComentarios();
		} catch (Exception e) {
			System.out.println(Constantes.ERRO_LISTA_COMENTARIOS);
		}
		return listaDeComentarios;
	}

	/**
	 * Adiciona um comentario ao um post.
	 * 
	 * @param comentario
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean addComentario(Comentario comentario) {
		if (getComentarios().contains(comentario)) {
			return false;
		} else {
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
	 * 
	 * @param comentario
	 * @return
	 */
	public boolean removeComentario(Comentario comentario) {
		if (getComentarios().contains(comentario)) {
			try {
				comentDao.deletar(comentario);
			} catch (PersistenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Salva as alteracoes do post.
	 * 
	 * @throws PersistenceException
	 * @throws IOException
	 */
	public void saveData() throws PersistenceException, IOException {
		postDao.getInstance();
		postDao.atualizar(this, this);
	}

	/**
	 * Metodo que gera o id da classe.
	 * 
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
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	public Texto getTexto() {
		return this.post;
	}

	/**
	 * Metodo que valida as exigencias do post.
	 * 
	 * @param post
	 * @return
	 */
	private boolean validaPost(Texto post) {
		return (post == null || post.getTextoCompleto().trim().equals("")) ? false
				: true;
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

	public int getNumberOfComments() {
		return getComentarios().size();
	}

	public List<Audio> getListaDeAudio() {
		return listaDeAudio;
	}

	public List<Video> getListaDeVideo() {
		return listaDeVideo;
	}

	public List<Imagem> getListaDeImagem() {
		return listaDeImagem;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof Post))
			return false;
		Post post = (Post) obj;
		return getId().equals(post.getId());
	}

}
