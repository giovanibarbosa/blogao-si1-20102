package classes.gerenciadores;

import java.io.FileNotFoundException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.UsuariosDAO;
import classes.func.usuario.Usuario;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import persistencia.daos.UsuariosDAO;

import classes.Comentario;
import classes.func.usuario.Usuario;
import interfaces.Gerenciador;

public class GerenciadorDeUsuarios implements Gerenciador{
	
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private GerenciadorDeSessoes gerenteDeSessao = new GerenciadorDeSessoes();

	private UsuariosDAO usuariosDAO = UsuariosDAO.getInstance();
	private List<Usuario> listaUsuarios;

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadData() {
		try {
			listaUsuarios = usuariosDAO.recuperaUsuarios();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			listaUsuarios = new ArrayList<Usuario>();
		}

	}
	
	public Usuario recuperaUsuarioPorIdSessao(String sessionID) throws ArgumentInvalidException,
					FileNotFoundException, PersistenceException {
		String log = gerenteDeSessao.getLogin(sessionID);
		return userDAO.recupera(log);
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
	
	

}
