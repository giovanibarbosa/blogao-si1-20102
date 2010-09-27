package classes;

import interfaces.Constantes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classes.func.Data;
import classes.func.multimidia.Audio;
import classes.func.multimidia.Imagem;
import classes.func.multimidia.Video;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.PostsDAO;

public class Post {
	private String id;
	private String blogRaiz;
	private String dataCriacao;
	private String idBlogDono;

	private List<Comentario> comentarios;
	
	private Map<String, Video> mapaVideos;
	private Map<String, Audio> mapaAudio;
	private Map<String, Imagem> mapaImagens;

	private static final int TEXTO = 110256354;
	private static final int TITULO = -873444423;

	private String titulo;
	private String corpo;
	
	
	public String getCorpo() {
		return corpo;
	}

	/**
	 * Construtor da classe Post, que recebe como parametro o texto do post.
	 * 
	 * @param post
	 * @throws ArgumentInvalidException
	 */
	
	public Post(String titulo, String corpo, String idBlogDono) throws ArgumentInvalidException {
		setTitulo(titulo);
		setCorpo(corpo);
		this.id = gerarId();
		setDataCriacao(new Data().todaysDate());
		this.idBlogDono = idBlogDono;
		comentarios = new ArrayList<Comentario>();
		mapaVideos = new HashMap<String, Video>();
		mapaAudio = new HashMap<String, Audio>();
		mapaImagens = new HashMap<String, Imagem>();
	}
	
	private void setCorpo(String texto2) {
		this.corpo = texto2;
		
	}

	public void setTitulo(String titulo) throws ArgumentInvalidException {
		if (titulo == null || titulo.trim().equals("")) {
			throw new ArgumentInvalidException(Constantes.TITULO_OBRIGATORIO);
		}
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(String data) {
		this.dataCriacao = data;
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
	 * Classe que adiciona um audio ao mapa.
	 * 
	 * @param audio
	 */
	public void addAudio(Audio audio) {
		if (!mapaAudio.containsValue(audio))
			mapaAudio.put(audio.getId(),audio);
	}

	/**
	 * Remove o audio do mapa.
	 * 
	 * @param coment
	 */
	public void removeAudio(Audio audio) {
		if (mapaAudio.containsValue(audio))
			mapaAudio.remove(audio.getId());
	}

	public void addImagem(Imagem imagem) {
		if (!mapaImagens.containsValue(imagem))
			mapaImagens.put(imagem.getId(),imagem);
	}
	
	public void removeImagem(Imagem imagem) {
		if (mapaImagens.containsValue(imagem))
			mapaImagens.remove(imagem.getId());
	}
	
	public void addVideo(Video video) {
		if (!mapaVideos.containsValue(video))
			mapaVideos.put(video.getId(), video);
	}
	
	public void removeVideo(Video video) {
		if (mapaVideos.containsValue(video))
			mapaVideos.remove(video);
	}
	
	
	
	public String getIdBlogDono() {
		return idBlogDono;
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
		if (atributo == null) {
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO2);
		}
		int codigoAtributo = atributo.hashCode();
		
		switch (codigoAtributo) {

		case (TEXTO):
			this.setCorpo(mudanca);
			break;
		case (TITULO):
			this.setTitulo(mudanca);
			break;
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

	}

	/**
	 * retorna a lista de comentarios do post.
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
//	public List<Comentario> getComentarios() {
//		List<Comentario> listaDeComentarios = new ArrayList<Comentario>();
//		try {
//			listaDeComentarios = comentDao.recuperaComentarios();
//		} catch (Exception e) {
//			System.out.println(Constantes.ERRO_LISTA_COMENTARIOS);
//		}
//		return listaDeComentarios;
//	}

	/**
	 * Adiciona um comentario ao um post.
	 * 
	 * @param comentario
	 * @return
	 * @throws FileNotFoundException
	 */
//	public boolean addComentario(Comentario comentario) {
//		if (getComentarios().contains(comentario)) {
//			return false;
//		} else {
//			try {
//				comentDao.criar(comentario);
//			} catch (PersistenceException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return true;
//		}
//	}

	/**
	 * remove um comentario do post.
	 * 
	 * @param comentario
	 * @return
	 */
//	public boolean removeComentario(Comentario comentario) {
//		if (getComentarios().contains(comentario)) {
//			try {
//				comentDao.deletar(comentario);
//			} catch (PersistenceException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return true;
//		} else {
//			return false;
//		}
//	}

	/**
	 * Salva as alteracoes do post.
	 * 
	 * @throws PersistenceException
	 * @throws IOException
	 */
	public void saveData() throws PersistenceException, IOException {
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
	 * Metodo que valida as exigencias do post.
	 * 
	 * @param post
	 * @return
	 */
	private boolean validaPost(Texto post) {
		return (post == null || post.getTextoCompleto().trim().equals("")) ? false
				: true;
	}
	
	public String getBlogRaiz() {
		return blogRaiz;
	}

	public void setBlogRaiz(String blogRaiz) {
		this.blogRaiz = blogRaiz;
	}

//	public int getNumberOfComments() {
//		return getComentarios().size();
//	}

	public List<Audio> getListaDeAudio() {
		return (List<Audio>) mapaAudio.values();
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
	
	@Override
	public String toString(){
		return this.id;
	}

}
