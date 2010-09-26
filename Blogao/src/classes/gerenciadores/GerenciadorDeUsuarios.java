package classes.gerenciadores;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.UsuariosDAO;
import classes.Login;
import classes.func.usuario.Perfil;
import classes.func.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;


import interfaces.Constantes;
import interfaces.Gerenciador;

public class GerenciadorDeUsuarios implements Gerenciador{
	
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private GerenciadorDeSessoes gerenteDeSessao;

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
	
	public Usuario recuperaUsuarioPorIdSessao(String sessionID) throws ArgumentInvalidException,
					FileNotFoundException, PersistenceException {
		String log = gerenteDados.getGerenteSessoes().getLoginPorSessao(sessionID);
		for (Usuario user : listaUsuarios) {
			if(log.equals(user.getLogin().getLogin())){
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
	 * @param listaUsuarios the listaUsuarios to set
	 */
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	@Override
	public void cleanPersistence() {
		userDAO.limparUsuarios();		
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

	public Usuario getUsuario(String login) throws PersistenceException {
		for (Usuario user : listaUsuarios) {
			if (user.getLogin().getLogin().equals(login)) {
				return user;
			}
		}
		throw new PersistenceException(Constantes.USUARIO_INEXISTENTE);
	}
	
	
	
	

}
