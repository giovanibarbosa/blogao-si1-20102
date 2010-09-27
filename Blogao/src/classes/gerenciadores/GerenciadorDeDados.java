package classes.gerenciadores;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.PersistenceException;

import classes.Blog;
import classes.Post;
import classes.func.usuario.Perfil;
import classes.func.usuario.Usuario;

import interfaces.Gerenciador;
import persistencia.daos.*;

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
//		gerenteComentarios = new GerenciadorDeComentarios();
	}
	
	/**
	 * Metodo que carrega todos* os dados do BD para a aplicação.
	 * @throws FileNotFoundException
	 */
	//FIXME ajeitar o email e os comentarios.
	public void loadData() throws FileNotFoundException {
		gerenteUsuarios.loadData();
		gerentePerfis.loadData();
		gerenteSessoes.loadData();
		gerenteBlogs.loadData();
		gerentePosts.loadData();
//		gerenteComentarios.loadData();
	}

	public void saveData() throws PersistenceException, IOException{
		gerenteUsuarios.saveData();
		//gerentePerfis.saveData();
		gerenteSessoes.saveData();
		gerenteBlogs.saveData();
		gerentePosts.saveData();
//		gerenteComentarios.saveData();
	}
	public void cleanPersistence(){
		gerenteUsuarios.cleanPersistence();
		//gerentePerfis.cleanPersistence();
		gerenteSessoes.cleanPersistence();
		gerenteBlogs.cleanPersistence();
		gerentePosts.cleanPersistence();
//		gerenteComentarios.cleanPersistence();
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
