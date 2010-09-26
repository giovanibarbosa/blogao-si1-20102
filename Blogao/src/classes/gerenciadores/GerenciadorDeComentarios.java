package classes.gerenciadores;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.UserInvalidException;

import persistencia.daos.ComentariosDAO;

import classes.Blog;
import classes.Comentario;
import classes.func.usuario.Usuario;

import interfaces.Gerenciador;

public class GerenciadorDeComentarios implements Gerenciador {
	
	private GerenciadorDeDados gerenteDados;
	private List<Comentario> listaComentarios;
	private ComentariosDAO comentariosDAO = ComentariosDAO.getInstance();
	private GerenciadorDeSessoes gerenteSessoes;

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
	
	public Comentario GetComentario(String postId, int index) throws ArgumentInvalidException{
		return gerenteDados.getGerentePosts().getPost(postId).getListaComentarios().get(index);
	}

	
	public String addComentario(String sessionId, String postId, String texto) throws ArgumentInvalidException, UserInvalidException {
		Comentario coment = new Comentario(texto);
		gerenteDados.getGerentePosts().getPost(postId, sessionId).addComentario2(coment);
		return coment.getId();
		
	}
	
	

	@Override
	public void cleanPersistence() {
		// TODO Auto-generated method stub
		
	}

}
