package classes.func.usuario;

import interfaces.Gerenciador;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.gerenciadores.GerenciadorDeBlogs;
import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.GerenciadorDePerfis;
import classes.gerenciadores.GerenciadorDePosts;
import classes.gerenciadores.GerenciadorDeSessoes;
import classes.gerenciadores.GerenciadorDeComentarios;
import classes.Comentario;

/**
 * Classe para testes do us8
 * 
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * 
 */

public class FacadeUserStore8 {
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();

	// CARREGA TODOS OS DADOS DO BD
	public void loadData() throws FileNotFoundException {
		gerenteDados.loadData();

	}

	// Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {

		gerenteDados.getGerentePerfis().createProfile(login, senha,
				nome_exibicao, email, sexo, dataNasc, endereco, interesses,
				quem_sou_eu, filmes, musicas, livros);
	}

	// METODO QUE LOGA O USUARIO
	public String logon(String login, String senha)
			throws FileNotFoundException, ArgumentInvalidException,
			PersistenceException {
		return gerenteDados.getGerenteSessoes().logon(login, senha);

	}

	// RETORNA O ID DO BLOG DADO O LOGIN DO USUARIO E O INDICE.
	public String getBlogByLogin(String login, int index)
			throws UserInvalidException, FileNotFoundException,
			PersistenceException, ArgumentInvalidException {
		return gerenteDados.getGerenteBlogs().recuperaIdBlogPorLogin(login,
				index);

	}

	// RETORNA O ID DO POST DADO O ID DO BLOG E O INDICE.
	public String getPost(String blogId, int index)
			throws ArgumentInvalidException, NumberFormatException,
			FileNotFoundException, PersistenceException {
		return gerenteDados.getGerenteBlogs().recuperaIdDoPost(blogId, index);
	}

	// ADICIONA UM COMENTARIO. o RETORNO DO METODO E O ID DO COMENTARIO
	public String addComment(String sessionId, String postId, String texto)
			throws ArgumentInvalidException, UserInvalidException {
		return gerenteDados.getGerenteComentarios().addComentario(sessionId,
				postId, texto);
	}

	// RETORNA O NUMERO DE COMETRAIOS DO POST.
	public int getNumberOfComments(String postId) throws FileNotFoundException,
			PersistenceException {
		return gerenteDados.getGerentePosts().getNumberOfComments(postId);
	}

	// RETORNA O COMENTARIO SEGUNDO O POST(PARAMENTRO) E SEU INDICE.
	public String getComment(String postId, int index)
			throws ArgumentInvalidException {
		return gerenteDados.getGerenteComentarios()
				.GetComentario(postId, index).getId();
	}

	// // TODO RETORNA O TEXTO DO COMETARIO.
	public String getCommentText(String idComentario) {
		return gerenteDados.getGerenteComentarios()
	}

	// TODO RETORNA O NOME DO AUTOR DO COMENTARIO.
	public String getCommentAuthor(String idComentario) {
		return null;
	}

	public void logoff(String idSessao) throws ArgumentInvalidException {
		gerenteDados.getGerenteSessoes().logoff(idSessao);
	}

	public void saveData() throws PersistenceException, IOException {
		gerenteDados.saveData();
	}
}
