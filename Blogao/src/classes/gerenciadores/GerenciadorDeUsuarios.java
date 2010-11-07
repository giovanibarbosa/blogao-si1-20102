package classes.gerenciadores;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import persistencia.daos.UsuariosDAO;
import classes.Announcement;
import classes.Blog;
import classes.Login;
import classes.func.usuario.Perfil;
import classes.func.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;

import enuns.Constantes2;
import facades.FacadeUsuario;
import interfaces.Gerenciador;

/**
 * Classe que inicializa o gerente de usuarios {@link GerenciadorDeUsuarios}
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 * 
 */
public class GerenciadorDeUsuarios implements Gerenciador {

	private UsuariosDAO userDAO = UsuariosDAO.getInstance();

	private List<Usuario> listaUsuarios;
	private GerenciadorDeDados gerenteDados;

	/**
	 * Construtor da classe.
	 * 
	 * @param gerenciadorDeDados
	 *            {@link GerenciadorDeDados}
	 */
	public GerenciadorDeUsuarios(GerenciadorDeDados gerenciadorDeDados) {
		listaUsuarios = new ArrayList<Usuario>();
		this.gerenteDados = gerenciadorDeDados;
	}

	@Override
	public void saveData() throws PersistenceException, IOException {
		userDAO.limparUsuarios();
		for (Usuario user : listaUsuarios) {
			userDAO.criar(user);
		}
	}

	@Override
	public void loadData() {
		try {
			listaUsuarios = userDAO.recuperaUsuarios();
		} catch (Exception e) {
			listaUsuarios = new ArrayList<Usuario>();
		}

	}

	@Override
	public void cleanPersistence() {
		userDAO.limparUsuarios();
		listaUsuarios = new ArrayList<Usuario>();
	}

	/**
	 * Recupera o usuario atraves do Id da Sessao.
	 * 
	 * @param sessionID
	 *            {@link String}
	 * @return usuario {@link Usuario}
	 * @throws ArgumentInvalidException
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 */
	public Usuario recuperaUsuarioPorIdSessao(String sessionID)
			throws ArgumentInvalidException, FileNotFoundException,
						PersistenceException {
		String login = getLoginPorSessao(sessionID);
		for (Usuario user : listaUsuarios) {
			if (login.equals(getLoginUser(user))) {
				return user;
			}
		}
		throw new ArgumentInvalidException(Constantes2.SESSAO_INVALIDA.getName());
	}

	/**
	 * Retorna um Usuario dado seu login.
	 * 
	 * @param login
	 *            {@link String}
	 * @return Usuario {@link Usuario}
	 * @throws UserInvalidException
	 */
	public Usuario getUsuario(String login) throws UserInvalidException {
		for (Usuario user : listaUsuarios) {
			if (getLoginUser(user).equals(login)) {
				return user;
			}
		}
		throw new UserInvalidException(Constantes2.USUARIO_INEXISTENTE.getName());
	}

	/**
	 * @return the listaUsuarios
	 */
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * Retorna a lista de perfis.
	 * 
	 * @return List<{@link Perfis}> lista de perfis.
	 */
	public List<Perfil> getListaPerfis() {
		List<Perfil> listaPerfis = new ArrayList<Perfil>();
		for (Usuario user : listaUsuarios) {
			listaPerfis.add(user.getPerfil());
		}
		return listaPerfis;
	}

	/**
	 * Retorna o numero de notificadores.
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @return numero de notificadores.
	 * @throws FileNotFoundException
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 */
	public int getNumberOfAnnouncements(String sessionId)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		Usuario user = recuperaUsuarioPorIdSessao(sessionId);
		int total = 0;
		for (Announcement announcement : user.getListaAnnouncement()) {
			total += announcement.getAtualizacoes().size();
		}
		return total;
	}

	/**
	 * Retorna o notificador.
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @param index
	 *            {@link int}
	 * @return
	 * @throws FileNotFoundException
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 */
	public String getAnnouncement(String sessionId, int index)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		Usuario user = recuperaUsuarioPorIdSessao(sessionId);
		if (index >= user.getListaAnnouncement().size() || index < 0)
			throw new ArgumentInvalidException(Constantes2.INDICE_INVALIDO.getName());
		return user.getListaAnnouncement().get(index).getId();
	}

	/**
	 * Retorna um Post criado.
	 * 
	 * @param announcementId
	 *            {@link String}
	 * @return
	 * @throws ArgumentInvalidException
	 */
	public String getPostJustCreated(String announcementId)
			throws ArgumentInvalidException {
		for (Usuario user : listaUsuarios) {
			for (Announcement announcement : user.getListaAnnouncement()) {
				if (announcement.getId().equals(announcementId)) {
					List<String> atualizacoes = announcement.getAtualizacoes();
					if (atualizacoes.size() > 0) {
						return atualizacoes.get(atualizacoes.size() - 1);
					}
				}
			}
		}
		throw new ArgumentInvalidException(Constantes2.ANNOUNCEMENT_INVALIDO.getName());
	}

	/**
	 * Cria o usuario.
	 * 
	 * @param user1
	 *            {@link Usuario}
	 */
	public void criarUsuario(Usuario user1) {
		listaUsuarios.add(user1);
	}

	/**
	 * Remove o Usuario na lista.
	 * 
	 * @param us
	 *            {@link Usuario}
	 */
	public void remover(Usuario us) {
		this.listaUsuarios.remove(us);
	}

	/**
	 * Adiciona um Usuario na lista.
	 * 
	 * @param us
	 *            {@link Usuario}
	 */
	public void adicionar(Usuario us) {
		this.listaUsuarios.add(us);
	
	}

	/**
	 * Valida um Login.
	 * 
	 * @param log
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 */
	public void validaLogin(Login log) throws ArgumentInvalidException {
		for (Usuario user : listaUsuarios) {
			if (user.getLogin().equals(log))
				throw new ArgumentInvalidException(Constantes2.LOGIN_EXISTENTE.getName());
		}
	}

	/**
	 * Adiciona um notificador de post.
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @param blogId
	 *            {@link String}
	 * @throws FileNotFoundException
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 */
	public void addPostAnnouncement(String sessionId, String blogId)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		Usuario user = recuperaUsuarioPorIdSessao(sessionId);
		Blog blog = recuperaBlog(blogId);
		user.addPostAnnouncement(blogId);
		blog.addModificationListener(getLoginUser(user));
	
	}

	/**
	 * @param listaUsuarios
	 *            {@link Usuario} the listaUsuarios to set
	 */
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	/**
	 * Deleta Notificador.
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @param announcementId
	 *            {@link String}
	 * @throws FileNotFoundException
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 */
	public void deleteAnnouncement(String sessionId, String announcementId)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		Usuario user = recuperaUsuarioPorIdSessao(sessionId);
		for (Announcement announcement : user.getListaAnnouncement()) {
			if (announcement.getId().equals(announcementId)) {
				String idBlog = announcement.getIdBlogDeInteresse();
				removeAnnouncement(user, announcement);
				Blog blog = recuperaBlog(idBlog);
				blog.getModificationListeners().remove(
						getLoginUser(user));
				return;
			}
		}
		throw new ArgumentInvalidException(Constantes2.ANNOUNCEMENT_INVALIDO.getName());
	}

	private String getLoginPorSessao(String sessionID)
			throws ArgumentInvalidException {
		return gerenteDados.getGerenteSessoes().getLoginPorSessao(
				sessionID);
	}

	private String getLoginUser(Usuario user) {
		return user.getLogin().getName();
	}

	private Blog recuperaBlog(String idBlog) throws ArgumentInvalidException {
		return gerenteDados.getGerenteBlogs().getBlog(idBlog);
	}

	private void removeAnnouncement(Usuario user, Announcement announcement) {
		user.getListaAnnouncement().remove(announcement);
	}

}
