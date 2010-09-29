package classes.func.usuario;

import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;

import interfaces.Constantes;
import interfaces.Logavel;
import classes.Announcement;
import classes.Blog;
import classes.Login;
import classes.Senha;

/**
 * Classe que inicializa um Usuario
 * 
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @colaborator Tiago Leite - tiagohsl@lcc.ufcg.edu.br
 */
public class Usuario {

	private Logavel logavel;
	private Perfil perfil;
	private List<Blog> listaBlogs;
	private List<Announcement> listaAnnouncements;

	/**
	 * Construtor do objeto Usuario
	 * 
	 * @param log {@link Login}
	 * @param sen {@link Senha}
	 * @throws Exception
	 *             caso algum dos parametros passados seja invalido
	 */
	public Usuario(Login log, Senha sen) throws Exception {
		logavel = new LogavelImpl(log, sen);
		listaBlogs = new ArrayList<Blog>();
		listaAnnouncements = new ArrayList<Announcement>();
	}

	/**
	 * Construtor do objeto Usuario
	 * 
	 * @param log {@link Login}
	 * @param sen {@link Senha}
	 * @param per {@link Perfil}
	 */
	public Usuario(Login log, Senha sen, Perfil per) {
		this.logavel = new LogavelImpl(log, sen);
		setPerfil(per);
		listaBlogs = new ArrayList<Blog>();
		listaAnnouncements = new ArrayList<Announcement>();
	}

	/**
	 * Metodo acessador de login
	 * 
	 * @return {@link Login}
	 */
	public Login getLogin() {
		return logavel.getLogin();
	}

	/**
	 * Metodo modificador de {@link Login}
	 * 
	 * @param {@link Login} novo login
	 * @throws ArgumentInvalidException
	 *             caso o login passado seja invalido
	 */
	public void setLogin(Login log) throws ArgumentInvalidException {
		logavel.setLogin(log);
	}

	/**
	 * Metodo acessador de {@link Senha}
	 * 
	 * @return {@link Senha}
	 */
	public Senha getSenha() {
		return logavel.getSenha();
	}

	/**
	 * Metodo modificador de {@link Senha}
	 * 
	 * @param sen {@link Senha}Senha nova.
	 */
	public void setSenha(Senha sen) {
		logavel.setSenha(sen);
	}

	/**
	 * Metodo acessador de {@link Perfil}
	 * 
	 * @return {@link Perfil}
	 */
	public Perfil getPerfil() {
		return perfil;
	}

	/**
	 * Metodo modificador de {@link Perfil}
	 * 
	 * @param perfil{@link Perfil} novo.
	 */
	public void setPerfil(Perfil perfil) {
		if (perfil != null)
			this.perfil = perfil;
	}

	/**
	 * Metodo modificar de {@link Login}
	 * 
	 * @param  log {@link Login} Novo login.
	 */
	public void setLogavel(Logavel log) {
		this.logavel = log;
	}

	/**
	 * Metodo acessador de lista de blogs
	 * 
	 * @return List<{@link Blog}> lista de blogs do usuario
	 */
	public List<Blog> getListaBlogs() {
		return listaBlogs;
	}

	/**
	 * Metodo que adiciona um {@link Blog} lista de blogs do usuario.
	 * 
	 * @param blg {@link Blog}
	 */
	public void addBlog2(Blog blg) {
		if (!listaBlogs.contains(blg))
			listaBlogs.add(blg);
	}

	/**
	 * Metodo que remove um {@link Blog} da lista de blogs do usuario.
	 * 
	 * @param blg {@link Blog}
	 */
	public void removeBlog2(Blog blg) {
		if (listaBlogs.contains(blg))
			listaBlogs.remove(blg);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Usuario))
			return false;
		Usuario user = (Usuario) obj;
		return getLogin().equals(user.getLogin());
	}

	@Override
	public String toString() {
		return logavel.toString();
	}

	/**
	 * Metodo acessador de lista de Announcements
	 * @return List<{@link Announcement}>
	 */
	public List<Announcement> getListaAnnouncement() {
		return listaAnnouncements;
	}

	/**
	 * Metodo que cadastra um Announcement
	 * @param blogId {@link String} desejado
	 * @throws {@ArgumentInvalidException} caso 
	 * 				o Announcement nao seja cadastrado
	 */
	public void addPostAnnouncement(String blogId) throws ArgumentInvalidException {
		Announcement announcement = new Announcement(blogId);
		if (!(listaAnnouncements.contains(announcement)))
			listaAnnouncements.add(announcement);
		else
			throw new ArgumentInvalidException(Constantes.ANNOUNCEMENT_CADASTRADO);

	}
	
	/**
	 * Metodo acessador de Announcements
	 * @param idBlog {@link String}
	 * @return {@liunk Announcement}
	 * @throws {@link ArgumentInvalidException} caso o 
	 * 					 Announcement seja invalido
	 */
	public Announcement getAnnouncementByIdBlog(String idBlog)
			throws ArgumentInvalidException{
		for (Announcement announcement : listaAnnouncements){
			if (announcement.getIdBlogDeInteresse().equals(idBlog)){
				return announcement;
			}
		}
		throw new ArgumentInvalidException(Constantes.ANNOUNCEMENT_INVALIDO);
	}

	/**
	 * Metodo que adiciona um aviso ao blog
	 * @param blog {@link Blog}
	 * @param id {@link String} id do announcement
	 */
	public void addAviso(Blog blog, String id) {
		for(Announcement announcement : listaAnnouncements){
			if (announcement.getIdBlogDeInteresse().equals(blog.getId())){
				announcement.addAtualizacao(id);
			}
		}
		
	}

}
