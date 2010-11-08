package facades;

import interfaces.Constantes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.Announcement;
import classes.Blog;
import classes.Login;
import classes.func.usuario.Perfil;
import classes.func.usuario.Usuario;
import classes.gerenciadores.GerenciadorDeBlogs;
import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.GerenciadorDeUsuarios;

public class FacadeUsuario {
	private static FacadeUsuario instance;
	private GerenciadorDeUsuarios gerenteUsuario;

	protected FacadeUsuario() {
		gerenteUsuario = new GerenciadorDeUsuarios(GerenciadorDeDados.getInstance());
	}

	public static FacadeUsuario getInstance() {
		if (instance == null) {
			instance = new FacadeUsuario();
		}
		return instance;
	}
	
	/**
	 * Metodo que encapsula o metodo saveData de gerenciador de usuarios.
	 * @throws PersistenceException
	 * @throws IOException
	 */
	public void saveData() throws PersistenceException, IOException {
		gerenteUsuario.saveData();

	}

	/**
	 * Metodo que encapsula o metodo loadData de gerenciador de usuarios.
	 */
	public void loadData() {
		gerenteUsuario.loadData();

	}

	/**
	 * Metodo que encapsula o metodo recuperaUsuarioPorIdSessao de gerenciador de usuarios.
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
		return gerenteUsuario.recuperaUsuarioPorIdSessao(sessionID);
	}

	

	/**
	 * Metodo que encapsula o metodo getListaUsuarios de gerenciador de usuarios.
	 * @return the listaUsuarios
	 */
	public List<Usuario> getListaUsuarios() {
		return gerenteUsuario.getListaUsuarios();
	}

	/**
	 * Metodo que encapsula o metodo setListaUsuarios de gerenciador de usuarios.
	 * @param listaUsuarios
	 *            {@link Usuario} the listaUsuarios to set
	 */
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		gerenteUsuario.setListaUsuarios(listaUsuarios);
	}

	/**
	 * Metodo que encapsula o metodo cleanPersistence de gerenciador de usuarios.
	 */
	public void cleanPersistence() {
		gerenteUsuario.cleanPersistence();
	}

	/**
	 * Metodo que encapsula o metodo getListaPerfis de gerenciador de usuarios.
	 * 
	 * @return List<{@link Perfis}> lista de perfis.
	 */
	public List<Perfil> getListaPerfis() {
		return gerenteUsuario.getListaPerfis();
	}

	/**
	 * Metodo que encapsula o metodo criarUsuario de gerenciador de usuarios.
	 * 
	 * @param user1
	 *            {@link Usuario}
	 */
	public void criarUsuario(Usuario user1) {
		gerenteUsuario.criarUsuario(user1);
	}

	/**
	 * Metodo que encapsula o metodo validaLogin de gerenciador de usuarios.
	 * 
	 * @param log
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 */
	public void validaLogin(Login log) throws ArgumentInvalidException {
		gerenteUsuario.validaLogin(log);
	}

	/**
	 * Metodo que encapsula o metodo getUsuario de gerenciador de usuarios.
	 * 
	 * @param login
	 *            {@link String}
	 * @return Usuario {@link Usuario}
	 * @throws UserInvalidException
	 */
	public Usuario getUsuario(String login) throws UserInvalidException {
		return gerenteUsuario.getUsuario(login);
	}

	/**
	 * Metodo que encapsula o metodo remover de gerenciador de usuarios.
	 * 
	 * @param us
	 *            {@link Usuario}
	 */
	public void remover(Usuario us) {
		gerenteUsuario.remover(us);
	}

	/**
	 * Metodo que encapsula o metodo adicionar de gerenciador de usuarios.
	 * 
	 * @param us
	 *            {@link Usuario}
	 */
	public void adicionar(Usuario us) {
		gerenteUsuario.adicionar(us);

	}

	/**
	 * Metodo que encapsula o metodo addPostAnnouncement de gerenciador de usuarios.
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
		gerenteUsuario.addPostAnnouncement(sessionId, blogId);

	}

	/**
	 * Metodo que encapsula o metodo getNumberOfAnnouncements de gerenciador de usuarios.
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
		return gerenteUsuario.getNumberOfAnnouncements(sessionId);
	}

	/**
	 * Metodo que encapsula o metodo getAnnouncement de gerenciador de usuarios.
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
		return gerenteUsuario.getAnnouncement(sessionId, index);
	}

	/**
	 * Metodo que encapsula o metodo getPostJustCreated de gerenciador de usuarios.
	 * 
	 * @param announcementId
	 *            {@link String}
	 * @return
	 * @throws ArgumentInvalidException
	 */
	public String getPostJustCreated(String announcementId)
			throws ArgumentInvalidException {
		return gerenteUsuario.getPostJustCreated(announcementId);
	}

	/**
	 * Metodo que encapsula o metodo deleteAnnouncement de gerenciador de usuarios.
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @param announcementId
	 *            {@link String}
	 * @throws PersistenceException 
	 * @throws ArgumentInvalidException 
	 * @throws FileNotFoundException 
	 * @throws FileNotFoundException
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 */
	public void deleteAnnouncement(String sessionId, String announcementId) throws FileNotFoundException, ArgumentInvalidException, PersistenceException{
		gerenteUsuario.deleteAnnouncement(sessionId, announcementId);
	}
}
