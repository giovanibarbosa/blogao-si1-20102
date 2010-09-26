package classes.gerenciadores;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.UsuariosDAO;
import classes.func.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;


import interfaces.Constantes;
import interfaces.Gerenciador;

public class GerenciadorDeUsuarios implements Gerenciador{
	
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private GerenciadorDeSessoes gerenteDeSessao = new GerenciadorDeSessoes();

	private List<Usuario> listaUsuarios;
	
	public GerenciadorDeUsuarios() {
		listaUsuarios = new ArrayList<Usuario>();
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
		String log = gerenteDeSessao.getLogin(sessionID);
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
	
	

}
