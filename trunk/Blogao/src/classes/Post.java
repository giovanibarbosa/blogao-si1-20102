package classes;

import interfaces.Constantes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classes.func.Data;
import classes.func.multimidia.Audio;
import classes.func.multimidia.Imagem;
import classes.func.multimidia.Video;

import ourExceptions.ArgumentInvalidException;

/**
 * Classe que inicializa um Blog.
 * 
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 * @colaborator Rodolfo Marinho -  rodolfoams@lcc.ufcg.edu.br
 * @colaborator Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 *
 */
public class Post {
	private String id;
	private String dataCriacao;
	private String idBlogDono;

	private List<Comentario> comentarios;
	
	private Map<String, Video> mapaVideos;

	private Map<String, Audio> mapaAudio;
	private Map<String, Imagem> mapaImagens;
	
	private List<String> listaIDsVideos;
	private List<String> listaIDsAudios;
	private List<String> listaIDsImagens;
	
	private static final int TEXTO = 110256354;
	private static final int TITULO = -873444423;

	private String titulo;
	private String corpo;



	/**
	 * Construtor da classe {@link Post} 
	 * @param titulo {@link String} do post
	 * @param corpo {@link String} do post
	 * @param idBlogDono {@link String}
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
		listaIDsVideos = new ArrayList<String>();
		listaIDsAudios = new ArrayList<String>();
		listaIDsImagens = new ArrayList<String>();
	}


	/**
	 * Metodo modificador de titulo
	 * @param titulo {@link String} novo
	 * @throws {@link ArgumentInvalidException} caso o titulo seja invalido
	 */
	public void setTitulo(String titulo) throws ArgumentInvalidException {
		if (titulo == null || titulo.trim().equals("")) {
			throw new ArgumentInvalidException(Constantes.TITULO_OBRIGATORIO);
		}
		this.titulo = titulo;
	}
	
	/**
	 * Metodo acessador de Titulo
	 * @return String titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	
	/**
	 * Metodo acessador de data
	 * @return String data da criacao do post
	 */
	public String getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * Metodo que adiciona um comentario a lista de comentarios.
	 * @param coment {@link Comentario}
	 */
	public void addComentario(Comentario coment) {
		if (!comentarios.contains(coment))
			comentarios.add(coment);
	}

	/**
	 * Metodo que remove um comentario da lista de comentarios.
	 * @param coment {@link Comentario}
	 */
	public void removeComentario(Comentario coment) {
		if (comentarios.contains(coment))
			comentarios.remove(coment);
	}
	
	/**
	 * Metodo que adiciona um audio ao post.
	 * @param audio {@link Audio}
	 */
	public void addAudio(Audio audio) {
		if (!mapaAudio.containsValue(audio)){
			mapaAudio.put(audio.getId(),audio);
			listaIDsAudios.add(audio.getId());
		}
	}

	/**
	 * Metodo que remove um audio do post.
	 * @param audio {@link Audio}
	 */
	public void removeAudio(Audio audio) {
		if (mapaAudio.containsValue(audio)){
			mapaAudio.remove(audio.getId());
			listaIDsAudios.remove(audio.getId());
		}
	}

	/**
	 * Metodo que adiciona uma {@link Imagem} ao post
	 * @param imagem {@link Imagem}
	 */
	public void addImagem(Imagem imagem) {
		if (!mapaImagens.containsValue(imagem)){
			mapaImagens.put(imagem.getId(),imagem);
			listaIDsImagens.add(imagem.getId());
		}
	}
	
	/**
	 * Metodo que remove uma {@link Imagem} do post.
	 * @param {@link Imagem} imagem
	 */
	public void removeImagem(Imagem imagem) {
		if (mapaImagens.containsValue(imagem)){
			mapaImagens.remove(imagem.getId());
			listaIDsImagens.remove(imagem.getId());
		}
	}
	
	/**
	 * Metodo que adiciona um {@link Video} ao post
	 * @param {@link Video} video
	 */
	public void addVideo(Video video) {
		if (!mapaVideos.containsValue(video)){
			mapaVideos.put(video.getId(), video);
			listaIDsVideos.add(video.getId());
		}
	}
	/**
	 * Metodo que remove um {@link Video} do post
	 * @param {@link Video} video
	 */
	public void removeVideo(Video video) {
		if (mapaVideos.containsValue(video)){
			mapaVideos.remove(video);
			listaIDsVideos.remove(video.getId());
		}
	}
	
	/**
	 * Metodo acessador do id do blog
	 * @return id do dono do blog
	 */
	public String getIdBlogDono() {
		return idBlogDono;
	}

	/**
	 * Metodo acessador que retorna uma lista de comentarios.
	 * @return List<{@link Comentario}> lista de comentarios
	 */
	public List<Comentario> getListaComentarios() {
		return comentarios;
	}

	/**
	 * Metodo acessador que retorna o total de comentarios num post.
	 * @return int total de {@link Comentario} do post
	 */
	public int getTamanhoDaLista() {
		return comentarios.size();
	}
	
	/**
	 * Metodo modificador que seta um atributo desejado e a sua atualizacao
	 * @param atributo {@link String} desejado
	 * @param mudanca {@link String} (atualizacao)
	 * @throws ArgumentInvalidException caso o atributo passado nao exista
	 */
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
	 * Metodo acessador de id do post
	 * @return String id do post
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Metodo acessador da lista de audios
	 * @return List<{@link Audio}> lista de audios do post
	 */
	public List<Audio> getListaDeAudio() {
		List<Audio> listaDeAudio = new ArrayList<Audio>();
		for (String audioID : listaIDsAudios) {
			listaDeAudio.add(mapaAudio.get(audioID));
		}
		return listaDeAudio;
	}

	/**
	 * Metodo acessador da lista de videos
	 * @return List<{@link Video}> lista de videos
	 */
	public List<Video> getListaDeVideo() {
		List<Video> listaDeVideo = new ArrayList<Video>();
		for (String videoID : listaIDsVideos) {
			listaDeVideo.add(mapaVideos.get(videoID));
		}
		return listaDeVideo;
	}

	/**
	 * Metodo acessador da lista de Imagens
	 * @return List<{@link Imagem}> lista de imagens
	 */
	public List<Imagem> getListaDeImagem() {
		List<Imagem> listaDeImagem = new ArrayList<Imagem>();
		for (String imagemID : listaIDsImagens) {
			listaDeImagem.add(mapaImagens.get(imagemID));
		}
		return listaDeImagem;
	}

	
	/**
	 * Metodo acessador do mapa de videos
	 * @return Map<{@ling String}, {@link Video}> mapa de videos
	 */
	public Map<String, Video> getMapaVideos() {
		return mapaVideos;
	}

	/**
	 * Metodo acessador do mapa de audios
	 * @return Map<{@ling String}, {@link Audio}> mapa de audios
	 */
	public Map<String, Audio> getMapaAudio() {
		return mapaAudio;
	}

	/**
	 * Metodo acessador do mapa de imagens
	 * @return Map<{@ling String}, {@link Imagem}> mapa de imagens
	 */
	public Map<String, Imagem> getMapaImagens() {
		return mapaImagens;
	}
	
	/**
	 * Metodo acessador de corpo do post
	 * @return String corpo do post
	 */
	public String getCorpo() {
		return corpo;
	}
	
	@Override
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

	
	private String gerarId() {
		return String.valueOf(this.hashCode());
	}
	
	private void setDataCriacao(String data) {
		this.dataCriacao = data;
	}

	private void setCorpo(String texto2) {
		this.corpo = texto2;		
	}

	public Comentario getComentario(int index) {
		return comentarios.get(index);
	}

}
