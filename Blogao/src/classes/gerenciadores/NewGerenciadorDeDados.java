package classes.gerenciadores;

import java.io.FileNotFoundException;
import java.io.IOException;

import ourExceptions.PersistenceException;

/**
 * Classe que gerencia a base de dados.
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 * 
 */
public class NewGerenciadorDeDados {
	private NewGerenciadorDeUsuarios gerenteUsuarios;
	private static NewGerenciadorDeDados instance;

	private NewGerenciadorDeSessoes gerenteSessoes;
	private NewGerenciadorDeBlogs gerenteBlogs;
	private NewGerenciadorDeComentarios gerenteComentarios;
	private NewGerenciadorDePerfis gerentePerfis;
	private NewGerenciadorDePosts gerentePosts;
	
	public static NewGerenciadorDeDados getInstance() {
		if (instance != null) {
			return instance;
		}
		return new NewGerenciadorDeDados();
	}

	/**
	 * Construtor para um {@link GerenciadorDeDados}
	 */
	protected NewGerenciadorDeDados() {
		gerentePerfis = new NewGerenciadorDePerfis(this);
		gerenteUsuarios = new NewGerenciadorDeUsuarios(this);
		gerenteSessoes = new NewGerenciadorDeSessoes(this);
		gerenteBlogs = new NewGerenciadorDeBlogs(this);
		gerentePosts = new NewGerenciadorDePosts(this);
		gerenteComentarios = new NewGerenciadorDeComentarios(this);
	}

	/**
	 * Metodo que carrega todos* os dados do BD para a aplicação.
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadData() throws FileNotFoundException {
		gerenteUsuarios.loadData();
		gerentePerfis.loadData();
		gerenteSessoes.loadData();
		gerenteBlogs.loadData();
		gerentePosts.loadData();
		gerenteComentarios.loadData();
	}

	/**
	 * Salva todos os dados para persistirem
	 * 
	 * @throws PersistenceException
	 * @throws IOException
	 */
	public void saveData() throws PersistenceException, IOException {
		gerenteUsuarios.saveData();
		gerenteSessoes.saveData();
		gerenteBlogs.saveData();
		gerentePosts.saveData();
		gerenteComentarios.saveData();
	}

	/**
	 * Deleta todos os dados da persistencia
	 */
	public void cleanPersistence() {
		gerenteUsuarios.cleanPersistence();
		gerentePerfis.cleanPersistence();
		gerenteSessoes.cleanPersistence();
		gerenteBlogs.cleanPersistence();
		gerentePosts.cleanPersistence();
		gerenteComentarios.cleanPersistence();
	}

	/**
	 * Recupera um {@link GerenciadorDeUsuarios}
	 * 
	 * @return Um {@link GerenciadorDeUsuarios}
	 */
	public NewGerenciadorDeUsuarios getGerenciadorDeUsuarios() {
		return gerenteUsuarios;
	}

	/**
	 * Recupera um {@link GerenciadorDeUsuarios}
	 * 
	 * @return Um {@link GerenciadorDeUsuarios}
	 */
	public NewGerenciadorDeUsuarios getGerenteUsuarios() {
		return gerenteUsuarios;
	}

	/**
	 * Recupera um {@link GerenciadorDeSessoes}
	 * 
	 * @return Um {@link GerenciadorDeSessoes}
	 */
	public NewGerenciadorDeSessoes getGerenteSessoes() {
		return gerenteSessoes;
	}

	/**
	 * Recupera um {@link GerenciadorDeBlogs}
	 * 
	 * @return Um {@link GerenciadorDeBlogs}
	 */
	public NewGerenciadorDeBlogs getGerenteBlogs() {
		return gerenteBlogs;
	}

	/**
	 * Recupera um {@link GerenciadorDeComentarios}
	 * 
	 * @return Um {@link GerenciadorDeComentarios}
	 */
	public NewGerenciadorDeComentarios getGerenteComentarios() {
		return gerenteComentarios;
	}

	/**
	 * Recupera um {@link GerenciadorDePerfis}
	 * 
	 * @return Um {@link GerenciadorDePerfis}
	 */
	public NewGerenciadorDePerfis getGerentePerfis() {
		return gerentePerfis;
	}

	/**
	 * Recupera um {@link GerenciadorDePosts}
	 * 
	 * @return Um {@link GerenciadorDePosts}
	 */
	public NewGerenciadorDePosts getGerentePosts() {
		return gerentePosts;
	}
}
