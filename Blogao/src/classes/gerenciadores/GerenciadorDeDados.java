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
public class GerenciadorDeDados {
	
	private GerenciadorDeUsuarios gerenteUsuarios;
	private GerenciadorDeSessoes gerenteSessoes;
	private GerenciadorDeBlogs gerenteBlogs;
	private GerenciadorDeComentarios gerenteComentarios;
	private GerenciadorDePerfis gerentePerfis;
	private GerenciadorDePosts gerentePosts;	
	private static GerenciadorDeDados instancia;
	
	/**
	 * Construtor privado para garantir o singleton
	 */
	private GerenciadorDeDados() {
		
	}
	
	/**
	 * Recupera a instancia unica para esta classe
	 * @return A a instancia unica para esta classe
	 */
	public static GerenciadorDeDados getInstance() {
		if(instancia == null)
			instancia = new GerenciadorDeDados();
		return instancia;
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
	public GerenciadorDeUsuarios getGerenciadorDeUsuarios() {
		return gerenteUsuarios;
	}

	/**
	 * Recupera um {@link GerenciadorDeUsuarios}
	 * 
	 * @return Um {@link GerenciadorDeUsuarios}
	 */
	public GerenciadorDeUsuarios getGerenteUsuarios() {
		return gerenteUsuarios;
	}

	/**
	 * Recupera um {@link GerenciadorDeSessoes}
	 * 
	 * @return Um {@link GerenciadorDeSessoes}
	 */
	public GerenciadorDeSessoes getGerenteSessoes() {
		return gerenteSessoes;
	}

	/**
	 * Recupera um {@link GerenciadorDeBlogs}
	 * 
	 * @return Um {@link GerenciadorDeBlogs}
	 */
	public GerenciadorDeBlogs getGerenteBlogs() {
		return gerenteBlogs;
	}

	/**
	 * Recupera um {@link GerenciadorDeComentarios}
	 * 
	 * @return Um {@link GerenciadorDeComentarios}
	 */
	public GerenciadorDeComentarios getGerenteComentarios() {
		return gerenteComentarios;
	}

	/**
	 * Recupera um {@link GerenciadorDePerfis}
	 * 
	 * @return Um {@link GerenciadorDePerfis}
	 */
	public GerenciadorDePerfis getGerentePerfis() {
		return gerentePerfis;
	}

	/**
	 * Recupera um {@link GerenciadorDePosts}
	 * 
	 * @return Um {@link GerenciadorDePosts}
	 */
	public GerenciadorDePosts getGerentePosts() {
		return gerentePosts;
	}
}
