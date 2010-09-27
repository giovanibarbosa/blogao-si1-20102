package classes.gerenciadores;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;

import persistencia.daos.ComentariosDAO;

import classes.Blog;
import classes.Comentario;
import classes.Post;
import classes.Sessao;
import classes.func.usuario.Usuario;

import interfaces.Gerenciador;

public class GerenciadorDeComentarios implements Gerenciador {

	private GerenciadorDeDados gerenteDados;
	private List<Comentario> listaComentarios;
	private ComentariosDAO comentariosDAO = ComentariosDAO.getInstance();

	public GerenciadorDeComentarios(GerenciadorDeDados gerenteDados) {
		listaComentarios = new ArrayList<Comentario>();
		this.gerenteDados = gerenteDados;
	}

	@Override
	public void saveData() throws PersistenceException, IOException {
		comentariosDAO.limparComentarios();
		for (Comentario coment : listaComentarios) {
			comentariosDAO.criar(coment);
		}

	}

	@Override
	public void loadData() {
		try {
			listaComentarios = comentariosDAO.recuperaComentarios();
		} catch (FileNotFoundException e) {
			listaComentarios = new ArrayList<Comentario>();
		}

	}

	// FIXME
	// public Comentario GetComentario(String postId, int index)
	// throws ArgumentInvalidException {
	// return gerenteDados.getGerentePosts().getPost(postId)
	// .getListaComentarios().get(index);
	// }

	// FIXME
	// public String addComentario(String sessionId, String postId, String
	// texto)
	// throws ArgumentInvalidException, UserInvalidException {
	// Comentario coment = new Comentario(texto);
	// gerenteDados.getGerentePosts().getPost(postId, sessionId)
	// .addComentario2(coment);
	// return coment.getId();
	//
	// }

	@Override
	public void cleanPersistence() {
		comentariosDAO.limparComentarios();

	}

	public String addComentario(String sessionId, String postId, String texto)
			throws ArgumentInvalidException, PersistenceException {
		gerenteDados.getGerenteSessoes().getSessao(sessionId);
		
		Comentario coment = new Comentario(sessionId, texto);
		Post post = gerenteDados.getGerentePosts().getPostPorId(postId);
		
		post.addComentario2(coment);
		return coment.getId();
	}

}
