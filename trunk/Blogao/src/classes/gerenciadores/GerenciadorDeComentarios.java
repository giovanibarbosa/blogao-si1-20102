package classes.gerenciadores;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;

import persistencia.daos.ComentariosDAO;

import classes.Comentario;
import classes.Post;
import classes.Sessao;
import classes.func.usuario.Usuario;
import enuns.Constantes2;

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
public class GerenciadorDeComentarios implements Gerenciador {

	private GerenciadorDeDados gerenteDados;
	private List<String> listaIdsComentarios;
	private HashMap<String, Comentario> mapaComentarios;
	private ComentariosDAO comentariosDAO = ComentariosDAO.getInstance();

	/**
	 * Construtor para este {@link GerenciadorDeComentarios}
	 * 
	 * @param gerenteDados
	 *            {@link GerenciadorDeDados}
	 */
	public GerenciadorDeComentarios(GerenciadorDeDados gerenteDados) {
		listaIdsComentarios = new ArrayList<String>();
		mapaComentarios = new HashMap<String, Comentario>();
		this.gerenteDados = gerenteDados;
	}

	@Override
	public void saveData() throws PersistenceException, IOException {
		comentariosDAO.limparComentarios();
		Iterator<String> it = iteradorComentarios();
		while(it.hasNext()){
			criaComentario(it.next());
		}

	}

	@Override
	public void loadData() {
		try {
			List<Comentario> listaComents = comentariosDAO
					.recuperaComentarios();
			for (Comentario comentario : listaComents) {
				addComentarioNaList(comentario);
				addComentarioNoMapa(comentario);
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
	 * Remove um {@link Comentario}
	 * 
	 * @param removido
	 *            {@link Comentario}
	 */
	public void remove(Comentario removido) {
		removeComentario(removido);
		while (listaIdsComentarios.contains(removido.getId()))
			listaIdsComentarios.remove(removido.getId());
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

		verificaSessaoValida(sessionId);

		Comentario coment = new Comentario(sessionId, texto);
		Post post = getPost(postId);

		post.addComentario(coment);
		addComentarioNaList(coment);
		addComentarioNoMapa(coment);
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
		return getCorpoComentario(idComentario);
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

		String idSessaoDono = getSessionIdOwner(idComentario);
		for (Usuario user : gerenteDados.getGerenciadorDeUsuarios()
				.getListaUsuarios()) {
			if (String.valueOf(user.getLogin().getName().hashCode()).equals(
					idSessaoDono)) {
				return (user.getLogin().getName());
			}

		}
		throw new ArgumentInvalidException(Constantes2.COMENTARIO_INEXISTENTE.getName());
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
		
		validaLogin(sessionId);
		Comentario comentPai = mapaComentarios.get(idComentario);
		if (comentPai == null) {
			throw new ArgumentInvalidException(Constantes2.COMENTARIO_INVALIDO.getName());
		}
		Comentario coment = new Comentario(sessionId, texto);
		addComentarioNaList(coment);
		addComentarioNoMapa(coment);
		addSubComentario(comentPai, coment);
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
			throw new ArgumentInvalidException(Constantes2.COMENTARIO_INVALIDO.getName());
		if (index >= coment.getListaSubComentarios().size())
			throw new ArgumentInvalidException(Constantes2.INDICE_INCORRETO.getName());

		return getIdComentario(index, coment);
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
			throw new ArgumentInvalidException(Constantes2.COMENTARIO_INVALIDO.getName());
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
			throw new ArgumentInvalidException(Constantes2.COMENTARIO_INVALIDO.getName());
		int total = 0;
		for (Comentario subComent : coment.getListaSubComentarios()) {
			total += getNumberOfAllSubComments(subComent.getId());
			total++;
		}
		return total;
	}

	private void criaComentario(String comentId) throws PersistenceException,
			IOException {
		comentariosDAO.criar(mapaComentarios.get(comentId));
	}

	private Comentario addComentarioNoMapa(Comentario comentario) {
		return mapaComentarios.put(comentario.getId(), comentario);
	}

	private boolean addComentarioNaList(Comentario comentario) {
		return listaIdsComentarios.add(comentario.getId());
	}

	private Post getPost(String postId) throws PersistenceException {
		return gerenteDados.getGerentePosts().getPostPorId(postId);
	}

	private Sessao verificaSessaoValida(String sessionId)
			throws ArgumentInvalidException {
		return gerenteDados.getGerenteSessoes().getSessao(sessionId);
	}

	private String getCorpoComentario(String idComentario) {
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
			throw new ArgumentInvalidException(Constantes2.COMENTARIO_INVALIDO.getName());
	
	}

	private String getSessionIdOwner(String idComentario) {
		return mapaComentarios.get(idComentario)
				.getIdSessaoDono();
	}

	private Comentario removeComentario(Comentario removido) {
		return mapaComentarios.remove(removido.getId());
	}

	private boolean addSubComentario(Comentario comentPai, Comentario coment) {
		return comentPai.getListaSubComentarios().add(coment);
	}

	private String validaLogin(String sessionId)
			throws ArgumentInvalidException {
		return gerenteDados.getGerenteSessoes().getLoginPorSessao(sessionId);
	}

	private String getIdComentario(int index, Comentario coment) {
		return coment.getListaSubComentarios().get(index).getId();
	}

	
	/**
	 * Iterador sobre a lista de Ids dos Comentarios.
	 * @return Iterator<String>
	 */
	public Iterator<String> iteradorComentarios(){
		return new Iterator<String>() {
			private int cursor = 0;


			@Override
			public boolean hasNext() {
				while(cursor < listaIdsComentarios.size()) {
					if(listaIdsComentarios.get(cursor) instanceof String)
						return true;
					cursor++;
				}				
				return false;
			}


			@Override
			public String next() {
				try {
					String b = listaIdsComentarios.get(cursor);
					if(b instanceof String) {
						cursor++;
						return b;
					}
					cursor++;
				} catch (NoSuchElementException e) {
					throw e;
				}
				return next();
			}


			@Override
			public void remove() {				
			}
		};
	}
}
