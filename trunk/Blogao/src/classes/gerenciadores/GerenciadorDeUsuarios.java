package classes.gerenciadores;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import persistencia.daos.UsuariosDAO;

import classes.Comentario;
import classes.func.usuario.Usuario;
import interfaces.Gerenciador;

public class GerenciadorDeUsuarios implements Gerenciador{

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

}
