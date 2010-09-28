package classes.gerenciadores;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;

import com.sun.xml.xsom.impl.scd.Iterators.Map;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;

import persistencia.daos.ComentariosDAO;

import classes.Blog;
import classes.Comentario;
import classes.Post;
import classes.Sessao;
import classes.func.usuario.Usuario;

import interfaces.Constantes;
import interfaces.Gerenciador;

public class GerenciadorDeComentarios implements Gerenciador {

	private GerenciadorDeDados gerenteDados;
	private List<String> listaIdsComentarios;
	private HashMap<String, Comentario> mapaComentarios;
	private ComentariosDAO comentariosDAO = ComentariosDAO.getInstance();

	public GerenciadorDeComentarios(GerenciadorDeDados gerenteDados) {
		listaIdsComentarios = new ArrayList<String>();
		mapaComentarios = new HashMap<String, Comentario>();
		this.gerenteDados = gerenteDados;
	}

	@Override
	public void saveData() throws PersistenceException, IOException {
		comentariosDAO.limparComentarios();
		for (String comentId : listaIdsComentarios) {
			comentariosDAO.criar(mapaComentarios.get(comentId));
		}

	}

	@Override
	public void loadData() {
		try {
			List<Comentario> listaComents = comentariosDAO
					.recuperaComentarios();
			for (Comentario comentario : listaComents) {
				listaIdsComentarios.add(comentario.getId());
				mapaComentarios.put(comentario.getId(), comentario);
			}
		} catch (FileNotFoundException e) {
			listaIdsComentarios = new ArrayList<String>();
			mapaComentarios = new HashMap<String, Comentario>();
		}

	}

	@Override
	public void cleanPersistence() {
		comentariosDAO.limparComentarios();

	}

	public String addComentario(String sessionId, String postId, String texto)
			throws ArgumentInvalidException, PersistenceException {
		System.out.println("Id sessao: " + sessionId + "; Id Post: " + postId
				+ "; Texto: " + texto);

		gerenteDados.getGerenteSessoes().getSessao(sessionId);

		Comentario coment = new Comentario(sessionId, texto);
		Post post = gerenteDados.getGerentePosts().getPostPorId(postId);

		post.addComentario(coment);
		listaIdsComentarios.add(coment.getId());
		mapaComentarios.put(coment.getId(), coment);
		return coment.getId();
	}

	public String getTextoComentario(String idComentario)
			throws ArgumentInvalidException {
		validaIdComentario(idComentario);
		return mapaComentarios.get(idComentario).getCorpoComentario();
	}

	private void validaIdComentario(String idComentario)
			throws ArgumentInvalidException {
		for (String idComentario2 : listaIdsComentarios) {
			if (idComentario.equals(idComentario2))
				return;
		}
		throw new ArgumentInvalidException(Constantes.COMENTARIO_INEXISTENTE);

	}

	public String getCommentAuthor(String idComentario) throws ArgumentInvalidException, FileNotFoundException, PersistenceException {
		validaIdComentario(idComentario);
		String idSessaoDono = mapaComentarios.get(idComentario).getIdSessaoDono();
		return gerenteDados.getGerenteUsuarios().recuperaUsuarioPorIdSessao(idSessaoDono).getLogin().getLogin();
	}

}
