package classes.gerenciadores;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import classes.Blog;
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
	private GerenciadorDeUsuarios gerenteUsuarios = new GerenciadorDeUsuarios();
	
	private GerenciadorDeSessoes gerenteSessoes = new GerenciadorDeSessoes();
	private GerenciadorDeBlogs gerenteBlogs = new GerenciadorDeBlogs(gerenteSessoes);
	private GerenciadorDeComentarios gerenteComentarios = new GerenciadorDeComentarios(gerenteSessoes);
	private GerenciadorDePerfis gerentePerfis = new GerenciadorDePerfis();
	private GerenciadorDePosts gerentePosts = new GerenciadorDePosts(gerenteSessoes, gerenteBlogs);
	
	
	/**
	 * Metodo que carrega todos* os dados do BD para a aplicação.
	 * @throws FileNotFoundException
	 */
	//FIXME ajeitar o email e os comentarios.
	public void loadData() throws FileNotFoundException {
		gerenteUsuarios.setListaUsuarios(UsuariosDAO.getInstance().loadData());
		povoaListaDeBlogs();		
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
		List<Perfil> listaPerfis = new ArrayList<Perfil>();
		for(Usuario user : gerenteUsuarios.getListaUsuarios()) {
			listaPerfis.add(user.getPerfil());			
		}
		gerentePerfis.setListaPerfis(listaPerfis);
	}
	
	public void povoaListaDeBlogs() {
		List<Blog> listaBlogs = new ArrayList<Blog>();
		for(Usuario user : gerenteUsuarios.getListaUsuarios()) {
			for(Blog blog : user.getListaBlogs()) {
				listaBlogs.add(blog);
				gerenteBlogs.getListaDeBlogs().add(blog);
			}
		}	
		gerenteBlogs.setListaDeBlogs(listaBlogs);
	}
	
	public void setTeste(GerenciadorDeUsuarios us) {
		this.gerenteUsuarios = us;
	}
	
	public GerenciadorDeUsuarios getGerenciadorDeUsuarios() {
		return gerenteUsuarios;
	}
}
