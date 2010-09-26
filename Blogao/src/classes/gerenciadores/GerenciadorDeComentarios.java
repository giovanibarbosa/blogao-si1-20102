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
	GerenciadorDeSessoes gerenteSessoes;

	public GerenciadorDeComentarios() {
		this.gerenteSessoes = gerenteSessoes;
		listaComentarios = new ArrayList<Comentario>();
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadData() {
		if (listaComentarios == null) {
			try {
				listaComentarios = comentariosDAO.recuperaComentarios();
			} catch (FileNotFoundException e) {
				listaComentarios = new ArrayList<Comentario>();
			}
		}

	}

	public int addComentario(String sessionId, String postId, String texto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void cleanPersistence() {
		// TODO Auto-generated method stub
		
	}

}
