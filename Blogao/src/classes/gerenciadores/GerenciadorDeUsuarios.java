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

import interfaces.Constantes;
import interfaces.Gerenciador;

public class GerenciadorDeUsuarios implements Gerenciador {

	private UsuariosDAO userDAO = UsuariosDAO.getInstance();

	private List<Usuario> listaUsuarios;
	private GerenciadorDeDados gerenteDados;

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
		} catch (FileNotFoundException e) {
			listaUsuarios = new ArrayList<Usuario>();
		}

	}

	public Usuario recuperaUsuarioPorIdSessao(String sessionID)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException {
		String log = gerenteDados.getGerenteSessoes().getLoginPorSessao(
				sessionID);
		for (Usuario user : listaUsuarios) {
			if (log.equals(user.getLogin().getLogin())) {
				return user;
			}
		}
		throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
	}

	/**
	 * @return the listaUsuarios
	 */
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	/**
	 * @param listaUsuarios
	 *            the listaUsuarios to set
	 */
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	@Override
	public void cleanPersistence() {
		userDAO.limparUsuarios();
		listaUsuarios = new ArrayList<Usuario>();
	}

	public List<Perfil> getListaPerfis() {
		List<Perfil> listaPerfis = new ArrayList<Perfil>();
		for (Usuario user : listaUsuarios) {
			listaPerfis.add(user.getPerfil());
		}
		return listaPerfis;
	}

	public void criarUsuario(Usuario user1) {
		listaUsuarios.add(user1);
	}

	public void validaLogin(Login log) throws ArgumentInvalidException {
		for (Usuario user : listaUsuarios) {
			if (user.getLogin().equals(log))
				throw new ArgumentInvalidException(Constantes.LOGIN_EXISTENTE);
		}
	}

	public Usuario getUsuario(String login) throws UserInvalidException {
		for (Usuario user : listaUsuarios) {
			if (user.getLogin().getLogin().equals(login)) {
				return user;
			}
		}
		throw new UserInvalidException(Constantes.USUARIO_INEXISTENTE);
	}

	public void remover(Usuario us) {
		this.listaUsuarios.remove(us);
	}

	public void adicionar(Usuario us) {
		this.listaUsuarios.add(us);

	}

	public void addPostAnnouncement(String sessionId, String blogId)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		Usuario user = recuperaUsuarioPorIdSessao(sessionId);
		Blog blog = gerenteDados.getGerenteBlogs().getBlog(blogId);
		user.addPostAnnouncement(blogId);
		blog.addModificationListener(user.getLogin().getLogin());

	}

	public int getNumberOfAnnouncements(String sessionId) throws FileNotFoundException, ArgumentInvalidException, PersistenceException {
		Usuario user = recuperaUsuarioPorIdSessao(sessionId);
		int total = 0;
		for (Announcement announcement : user.getListaAnnouncement()) {
			total += announcement.getAtualizacoes().size();
		}
		return total;
	}

	public String getAnnouncement(String sessionId, int index) throws FileNotFoundException, ArgumentInvalidException, PersistenceException {
		Usuario user = recuperaUsuarioPorIdSessao(sessionId);
		if (index >= user.getListaAnnouncement().size() || index < 0) throw new ArgumentInvalidException(Constantes.INDICE_INVALIDO2);
		return user.getListaAnnouncement().get(index).getId();
	}

	public String getPostJustCreated(String announcementId) throws ArgumentInvalidException {
		for(Usuario user : listaUsuarios){
			for(Announcement announcement : user.getListaAnnouncement()){
				if (announcement.getId().equals(announcementId)){
					List<String> atualizacoes = announcement.getAtualizacoes();
					if (atualizacoes.size() > 0){
						return atualizacoes.get(atualizacoes.size() - 1);
					}
				}
			}
		}
		throw new ArgumentInvalidException(Constantes.ANNOUNCEMENT_INVALIDO);
	}

	public void deleteAnnouncement(String sessionId, String announcementId) throws FileNotFoundException, ArgumentInvalidException, PersistenceException {
		Usuario user = recuperaUsuarioPorIdSessao(sessionId);
		for(Announcement announcement : user.getListaAnnouncement()){
			if(announcement.getId().equals(announcementId)){
				String idBlog = announcement.getIdBlogDeInteresse();
				user.getListaAnnouncement().remove(announcement);
				Blog blog = gerenteDados.getGerenteBlogs().getBlog(idBlog);
				blog.getModificationListeners().remove(user.getLogin().getLogin());
				return;
			}
		}
		throw new ArgumentInvalidException(Constantes.ANNOUNCEMENT_INVALIDO);
	}

}
