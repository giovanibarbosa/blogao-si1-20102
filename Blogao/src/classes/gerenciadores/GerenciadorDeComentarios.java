package classes.gerenciadores;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;

import persistencia.daos.ComentariosDAO;

import classes.Comentario;
import classes.Post;
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
		listaIdsComentarios = new ArrayList<String>();
		mapaComentarios = new HashMap<String, Comentario>();

	}

	public String addComentario(String sessionId, String postId, String texto)
			throws ArgumentInvalidException, PersistenceException {

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
		throw new ArgumentInvalidException(Constantes.COMENTARIO_INVALIDO);

	}

	public String getCommentAuthor(String idComentario) throws ArgumentInvalidException, FileNotFoundException, PersistenceException {
		validaIdComentario(idComentario);
		String idSessaoDono = mapaComentarios.get(idComentario).getIdSessaoDono();
		for (Usuario user : gerenteDados.getGerenciadorDeUsuarios().getListaUsuarios()) {
			if (String.valueOf(user.getLogin().getLogin().hashCode()).equals(idSessaoDono)){
				return user.getLogin().getLogin();
			}
			
		}
		throw new ArgumentInvalidException(Constantes.COMENTARIO_INEXISTENTE);
	}

	public void remove(Comentario removido) {
		mapaComentarios.remove(removido.getId());
		listaIdsComentarios.remove(removido.getId());
	}

	public String addSubComment(String sessionId, String idComentario,
			String texto) throws ArgumentInvalidException {
		gerenteDados.getGerenteSessoes().getLoginPorSessao(sessionId);
		Comentario comentPai = mapaComentarios.get(idComentario);
		if(comentPai == null) throw new ArgumentInvalidException(Constantes.COMENTARIO_INVALIDO);
		Comentario coment = new Comentario(sessionId, texto);
		listaIdsComentarios.add(coment.getId());
		mapaComentarios.put(coment.getId(), coment);
		comentPai.getListaSubComentarios().add(coment);
		return coment.getId();
	}

	public String getSubComment(String idComentario, int index) throws ArgumentInvalidException {
		Comentario coment = mapaComentarios.get(idComentario);
		if(coment == null) throw new ArgumentInvalidException(Constantes.COMENTARIO_INVALIDO); 
		if(index >= coment.getListaSubComentarios().size()) throw new ArgumentInvalidException(Constantes.INDICE_INVALIDO); 
		
		return coment.getListaSubComentarios().get(index).getId();
	}

	public int getNumberOfSubComments(String idComentario) throws ArgumentInvalidException {
		Comentario coment = mapaComentarios.get(idComentario);
		if(coment == null) throw new ArgumentInvalidException(Constantes.COMENTARIO_INVALIDO); 
		return coment.getListaSubComentarios().size();
	}

	public int getNumberOfAllSubComments(String idComentario) throws ArgumentInvalidException {
		Comentario coment = mapaComentarios.get(idComentario);
		if(coment == null) throw new ArgumentInvalidException(Constantes.COMENTARIO_INVALIDO);
		int total = 0;
		for (Comentario subComent : coment.getListaSubComentarios()) {
			total += getNumberOfAllSubComments(subComent.getId());
			total++;
		}
		return total;
	}
	
	

}
