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

/**
 * Classe que gerencia comentários ({@link Comentario})
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 * 
 */
public class NewGerenciadorDeComentarios implements Gerenciador {

	private NewGerenciadorDeDados gerenteDados;
	private List<String> listaIdsComentarios;
	private HashMap<String, Comentario> mapaComentarios;
	private ComentariosDAO comentariosDAO = ComentariosDAO.getInstance();

	/**
	 * Construtor para este {@link GerenciadorDeComentarios}
	 * 
	 * @param gerenteDados
	 *            {@link GerenciadorDeDados}
	 */
	public NewGerenciadorDeComentarios(NewGerenciadorDeDados gerenteDados) {
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

	/**
	 * Adiciona um {@link Comentario}
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @param postId
	 *            {@link String}
	 * @param texto
	 *            {@link String}
	 * @return O id do {@link Comentario} adicionado
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 */
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

	/**
	 * Recupera um texto pra um {@link Comentario}
	 * 
	 * @param idComentario
	 *            {@link String}
	 * @return Um texto pra um {@link Comentario}
	 * @throws ArgumentInvalidException
	 */
	public String getTextoComentario(String idComentario)
			throws ArgumentInvalidException {
		validaIdComentario(idComentario);
		return mapaComentarios.get(idComentario).getCorpoComentario();
	}

	/**
	 * Valida um {@link Comentario}
	 * 
	 * @param idComentario
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 */
	private void validaIdComentario(String idComentario)
			throws ArgumentInvalidException {
		if (!listaIdsComentarios.contains(idComentario))
			throw new ArgumentInvalidException(Constantes.COMENTARIO_INVALIDO);

	}

	/**
	 * Recupera um id {@link Comentario}
	 * 
	 * @param idComentario
	 *            {@link String}
	 * @return Um id {@link Comentario}
	 * @throws ArgumentInvalidException
	 * @throws FileNotFoundException
	 * @throws PersistenceException
	 */
	public String getCommentAuthor(String idComentario)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException {
		validaIdComentario(idComentario);

		String idSessaoDono = mapaComentarios.get(idComentario)
				.getIdSessaoDono();
		for (Usuario user : gerenteDados.getGerenciadorDeUsuarios()
				.getListaUsuarios()) {
			if (String.valueOf(user.getLogin().getLogin().hashCode()).equals(
					idSessaoDono)) {
				return (user.getLogin().getLogin());
			}

		}
		throw new ArgumentInvalidException(Constantes.COMENTARIO_INEXISTENTE);
	}

	/**
	 * Remove um {@link Comentario}
	 * 
	 * @param removido
	 *            {@link Comentario}
	 */
	public void remove(Comentario removido) {
		mapaComentarios.remove(removido.getId());
		while (listaIdsComentarios.contains(removido.getId()))
			listaIdsComentarios.remove(removido.getId());
	}

	/**
	 * Adiciona um sub comentario
	 * 
	 * @param sessionId
	 *            {@link String}
	 * @param idComentario
	 *            {@link String}
	 * @param texto
	 *            {@link String}
	 * @return O id do sub comentario adicionado
	 * @throws ArgumentInvalidException
	 */
	public String addSubComment(String sessionId, String idComentario,
			String texto) throws ArgumentInvalidException {
		gerenteDados.getGerenteSessoes().getLoginPorSessao(sessionId);
		Comentario comentPai = mapaComentarios.get(idComentario);
		if (comentPai == null)
			throw new ArgumentInvalidException(Constantes.COMENTARIO_INVALIDO);
		Comentario coment = new Comentario(sessionId, texto);
		listaIdsComentarios.add(coment.getId());
		mapaComentarios.put(coment.getId(), coment);
		comentPai.getListaSubComentarios().add(coment);
		return coment.getId();
	}

	/**
	 * Recupera o id de um sub comentario
	 * 
	 * @param idComentario
	 *            {@link String}
	 * @param index
	 *            {@link String}
	 * @return o id de um sub comentario
	 * @throws ArgumentInvalidException
	 */
	public String getSubComment(String idComentario, int index)
			throws ArgumentInvalidException {
		Comentario coment = mapaComentarios.get(idComentario);
		if (coment == null)
			throw new ArgumentInvalidException(Constantes.COMENTARIO_INVALIDO);
		if (index >= coment.getListaSubComentarios().size())
			throw new ArgumentInvalidException(Constantes.INDICE_INVALIDO);

		return coment.getListaSubComentarios().get(index).getId();
	}

	/**
	 * Recupera o número de subcomentarios de um {@link Comentario}
	 * 
	 * @param idComentario
	 *            {@link String}
	 * @return O número de subcomentarios de um {@link Comentario}
	 * @throws ArgumentInvalidException
	 */
	public int getNumberOfSubComments(String idComentario)
			throws ArgumentInvalidException {
		Comentario coment = mapaComentarios.get(idComentario);
		if (coment == null)
			throw new ArgumentInvalidException(Constantes.COMENTARIO_INVALIDO);
		return coment.getListaSubComentarios().size();
	}

	/**
	 * Recupera o número de todos os subcomentários de um {@link Comentario}
	 * 
	 * @param idComentario
	 * @return
	 * @throws ArgumentInvalidException
	 */
	public int getNumberOfAllSubComments(String idComentario)
			throws ArgumentInvalidException {
		Comentario coment = mapaComentarios.get(idComentario);
		if (coment == null)
			throw new ArgumentInvalidException(Constantes.COMENTARIO_INVALIDO);
		int total = 0;
		for (Comentario subComent : coment.getListaSubComentarios()) {
			total += getNumberOfAllSubComments(subComent.getId());
			total++;
		}
		return total;
	}

}
