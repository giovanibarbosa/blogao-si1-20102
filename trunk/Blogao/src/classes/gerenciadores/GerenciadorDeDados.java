package classes.gerenciadores;

import java.io.FileNotFoundException;

import classes.func.usuario.Usuario;

import interfaces.Gerenciador;
import persistencia.daos.*;
public class GerenciadorDeDados {
	
	private GerenciadorDeSessoes gerenteSessoes = new GerenciadorDeSessoes();
	private GerenciadorDeBlogs gerenteBlogs = new GerenciadorDeBlogs(gerenteSessoes);
	private GerenciadorDeComentarios gerenteComentarios = new GerenciadorDeComentarios(gerenteSessoes);
	private GerenciadorDePerfis gerentePerfis = new GerenciadorDePerfis();
	private GerenciadorDePosts gerentePosts = new GerenciadorDePosts(gerenteSessoes, gerenteBlogs);
	private GerenciadorDeUsuarios gerenteUsuarios = new GerenciadorDeUsuarios();
	
	/**
	 * Metodo que carrega todos* os dados do BD para a aplicação.
	 * @throws FileNotFoundException
	 */
	//FIXME ajeitar o email e os comentarios.
	public void loadData() throws FileNotFoundException {
		gerenteBlogs.setListaDeBlogs(BlogsDAO.getInstance().loadData());
		gerenteUsuarios.setListaUsuarios(UsuariosDAO.getInstance().loadData());	
		gerentePosts.setListaPosts(PostsDAO.getInstance().loadData());
		gerenteSessoes.setListaSessoes(SessoesDAO.getInstance().loadData());
		povoaGerenciadoDePerfis();
		//Fazer o mesmo para email(acho que nao deveria ter um dao para email)
		//Fazer o mesmo para comentarios
	}

	public void loadData(Gerenciador[] gerenciadores){
		for (Gerenciador gerenciador : gerenciadores) {
			gerenciador.loadData();
		}
	}
	public void saveData(Gerenciador[] gerenciadores){
		for (Gerenciador gerenciador : gerenciadores) {
			gerenciador.saveData();
		}
	}
	public void cleanPersistence(){
		BlogsDAO.getInstance().limparBlogs();
		ComentariosDAO.getInstance().limparComentarios();
		EmailsDAO.getInstance().limparEmails();
		PostsDAO.getInstance().limparPosts();
		SessoesDAO.getInstance().limparSessoes();
		UsuariosDAO.getInstance().limparUsuarios();
	}
	
	//Gabiarra usada para add os perfis
	public void povoaGerenciadoDePerfis() {
		for(Usuario user : gerenteUsuarios.getListaUsuarios()) {
			gerentePerfis.getListaPerfis().add(user.getPerfil());
			
		}
	}
}
