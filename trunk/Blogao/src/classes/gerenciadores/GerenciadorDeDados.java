package classes.gerenciadores;

import java.io.FileNotFoundException;
import java.io.IOException;


import ourExceptions.PersistenceException;

/**
 * Classe que gerencia a base de dados.
 * @author Rodolfo
 * @colaborator Tiago Leite - tiagohsl@lcc.ufcg.edu.br
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
	
	public GerenciadorDeDados(){
		gerentePerfis = new GerenciadorDePerfis(this);
		gerenteUsuarios = new GerenciadorDeUsuarios(this);
		gerenteSessoes = new GerenciadorDeSessoes(this);
		gerenteBlogs = new GerenciadorDeBlogs(this);
		gerentePosts = new GerenciadorDePosts(this);
		gerenteComentarios = new GerenciadorDeComentarios(this);
	}
	
	/**
	 * Metodo que carrega todos* os dados do BD para a aplicação.
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

	public void saveData() throws PersistenceException, IOException{
		gerenteUsuarios.saveData();
		gerenteSessoes.saveData();
		gerenteBlogs.saveData();
		gerentePosts.saveData();
		gerenteComentarios.saveData();
	}
	public void cleanPersistence(){
		gerenteUsuarios.cleanPersistence();
		gerentePerfis.cleanPersistence();
		gerenteSessoes.cleanPersistence();
		gerenteBlogs.cleanPersistence();
		gerentePosts.cleanPersistence();
		gerenteComentarios.cleanPersistence();
	}
	
	public GerenciadorDeUsuarios getGerenciadorDeUsuarios() {
		return gerenteUsuarios;
	}
	
	public static synchronized GerenciadorDeDados getInstance() {
		if (instancia == null)
			instancia = new GerenciadorDeDados();
		return instancia;

	}
	
	public GerenciadorDeUsuarios getGerenteUsuarios() {
		return gerenteUsuarios;
	}

	public GerenciadorDeSessoes getGerenteSessoes() {
		return gerenteSessoes;
	}

	public GerenciadorDeBlogs getGerenteBlogs() {
		return gerenteBlogs;
	}

	public GerenciadorDeComentarios getGerenteComentarios() {
		return gerenteComentarios;
	}

	public GerenciadorDePerfis getGerentePerfis() {
		return gerentePerfis;
	}

	public GerenciadorDePosts getGerentePosts() {
		return gerentePosts;
	}

	public static GerenciadorDeDados getInstancia() {
		return instancia;
	}
}
