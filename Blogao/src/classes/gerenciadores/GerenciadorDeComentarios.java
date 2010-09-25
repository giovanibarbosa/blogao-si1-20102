package classes.gerenciadores;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import persistencia.daos.ComentariosDAO;

import classes.Comentario;

import interfaces.Gerenciador;

public class GerenciadorDeComentarios implements Gerenciador {

	List<Comentario> listaComentarios;
	ComentariosDAO comentariosDAO = ComentariosDAO.getInstance();

	@Override
	public void cleanPersistence() {
		comentariosDAO.limparComentarios();

	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadData() {
		try {
			listaComentarios = comentariosDAO.recuperaComentarios();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			listaComentarios = new ArrayList<Comentario>();
		}

	}

}
