package classes.func.usuario;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;

import persistencia.daos.BlogsDAO;
import persistencia.daos.EmailsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;
import classes.Email;
import classes.func.Data;
import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.GerenciadorDePerfis;
import classes.gerenciadores.GerenciadorDePosts;
import classes.gerenciadores.GerenciadorDeSessoes;
import classes.Login;
import classes.Senha;
import classes.gerenciadores.GerenciadorDeBlogs;

public class FacadeUserStore6 {
	private GerenciadorDeSessoes gerente = new GerenciadorDeSessoes();
	private GerenciadorDeBlogs gerenteBlog = new GerenciadorDeBlogs(gerente);
	private GerenciadorDePosts gerentePost = new GerenciadorDePosts(gerente, gerenteBlog);
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();
	private GerenciadorDePerfis gerentePerfis = new GerenciadorDePerfis();
	
	//TODO APAGAR OS DADOS SALVOS
	public void cleanPersistence() {
		gerenteDados.cleanPersistence();
	}
	
	//TODO RETORNA A DATA DE ATUAL.
	public String todaysDate(){
		return Data.todaysDate();		
	}

	
	//Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {
		gerentePerfis.createProfile(login, senha, nome_exibicao, email, sexo,
				dataNasc, endereco, interesses, quem_sou_eu, filmes, musicas, livros);

		
	}
	
	// TODO METODO QUE LOGA O USUARIO
	public String logon(String login, String senha) throws PersistenceException, FileNotFoundException, ArgumentInvalidException{
		return gerente.logon(login, senha);
	}
	
	//TODO CRIA O BLOG
	public void createBlog(String idSession, String titulo, String descricao) throws Exception{	
		gerenteBlog.createBlog(idSession, titulo, descricao);


	}
	
	//TODO CRIA O POST
	public String createPost(String idSession, String idBlog, String titulo, String texto) throws ArgumentInvalidException, PersistenceException, IOException{
		return gerentePost.createPost(idSession, idBlog, titulo, texto);

	}
	
	//TODO METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws ArgumentInvalidException{
		gerente.logoff(idSession);

	}
	
	//TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {}
}
