package classes.gerenciadores;

import interfaces.Constantes;
import interfaces.Gerenciador;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.BlogsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;
import classes.func.multimidia.Audio;
import classes.func.usuario.Usuario;
import classes.gerenciadores.GerenciadorDeBlogs;
import classes.Comentario;
import classes.Post;
import classes.Blog;
import classes.Texto;

public class GerenciadorDePosts implements Gerenciador {
	private BlogsDAO blogsDAO = BlogsDAO.getInstance();
	private PostsDAO postsDAO = PostsDAO.getInstance();
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private GerenciadorDeBlogs gerenteDeBlogs;
	private GerenciadorDeSessoes gerenteDeSessao;
	private List<Post> listaPosts;
	
	private static final int TEXTO = 110256354;
	private static final int TITULO = -873444423;
	
	public GerenciadorDePosts(GerenciadorDeSessoes gereteDeSessao, GerenciadorDeBlogs gerenteBlogs){
		this.gerenteDeSessao = gereteDeSessao;
		this.gerenteDeBlogs = gerenteBlogs;
		
	}
	
	/**
	 * Metodo que gerancia a criacao de um Post.
	 * @param idBlog
	 * @param titulo
	 * @param texto
	 * @param texto2 
	 * @return
	 * @throws IOException
	 * @throws ArgumentInvalidException
	 * @throws PersistenceException
	 */
	public String createPost(String idSessao, String idBlog, String titulo, String texto) throws IOException, ArgumentInvalidException, PersistenceException {
		Post post;
		try {
			Texto txt = new Texto(titulo, texto);
			String log = gerenteDeSessao.getLogin(idSessao);
			Usuario user = userDAO.recupera(log);
			List<Blog> listaBlogs = user.listaDeBlogs();
			Blog blog = null;
			post = new Post(txt, idBlog);
			
			for(Blog blg : listaBlogs){
				if(idBlog.equals(blg.getId())){
						blog = blg;
				}
			}
			
			if(blog == null){
				throw new ArgumentInvalidException("Blog inválido");
			}else{
				blog.addPost(post);	
			}
			
			
			
			
			
			
			/*gerenteDeSessao.getLogin(idSessao);
			Blog blog = gerenteDeBlogs.getBlog(idBlog);
			Texto txt = new Texto(titulo, texto);
			post = new Post(txt, idBlog);
			blog.listaDePosts().add(post);
			postsDAO.criar(post);
			blogsDAO.atualizar(blog);*/
			
			
		} catch (FileNotFoundException e) {
			throw e;
		} catch (PersistenceException e) {
			throw e;
		} catch (ArgumentInvalidException e) {
			throw e;
		}
			
		return post.getId();		
	}
	
	public String getAtributo(Post post, String atributo) throws ArgumentInvalidException {
		int codigoAtributo = atributo.hashCode();
		
		switch(codigoAtributo) {
			
			case(TEXTO):
				return post.getTexto().getCorpo().toString();
			case(TITULO):
				return post.getTexto().getTitulo().toString();
			default:
				throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO2);
		}
	}
	

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadData() {
		try {
			listaPosts = postsDAO.recuperaPosts();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			listaPosts = new ArrayList<Post>();
		}

	}
	
	public Blog getBlog(String idBlog, String idSessao) throws FileNotFoundException, PersistenceException, ArgumentInvalidException{
		Blog blog = blogsDAO.recupera(idBlog);
		if(!idSessao.equals(blog.getIdSessao())){
			throw new ArgumentInvalidException(Constantes.SESSAO_INVALIDA);
		}
		return blog;
	}

	public int getNumberOfComments(String postId) throws FileNotFoundException, PersistenceException {
		return postsDAO.recupera(postId).getNumberOfComments();
	}
	
	public int recuperaTotalDeMusicasDoPost(String postID) throws FileNotFoundException, PersistenceException {
		Post postRecuperado = postsDAO.recupera(postID);
		int totalDeAudio = 0;
		for (Audio audio : postRecuperado.getListaDeAudio()) {
			totalDeAudio++;
		}
		return totalDeAudio;
	}
	
	public int recuperaTotalDeFilmesDoPost(String postID) throws FileNotFoundException, PersistenceException {
		Post postRecuperado = postsDAO.recupera(postID);
		int totalDeFilme = 0;
		for (Audio audio : postRecuperado.getListaDeAudio()) {
			totalDeFilme++;
		}
		return totalDeFilme;
	}
	
	public int recuperaTotalDeImagensDoPost(String postID) throws FileNotFoundException, PersistenceException {
		Post postRecuperado = postsDAO.recupera(postID);
		int totalDeImagens = 0;
		for (Audio audio : postRecuperado.getListaDeAudio()) {
			totalDeImagens++;
		}
		return totalDeImagens;
	}
	
	public int recuperaIDaudio(String postID, int index) throws FileNotFoundException, PersistenceException {
		Post postRecuperado = postsDAO.recupera(postID);
		return Integer.valueOf(postRecuperado.getListaDeAudio().get(index).getId());
		
	}
	
	public int recuperaIDvideo(String postID, int index) throws FileNotFoundException, PersistenceException {
		Post postRecuperado = postsDAO.recupera(postID);
		return Integer.valueOf(postRecuperado.getListaDeVideo().get(index).getId());
		
	}
	
	public int recuperaIDimagem(String postID, int index) throws FileNotFoundException, PersistenceException {
		Post postRecuperado = postsDAO.recupera(postID);
		return Integer.valueOf(postRecuperado.getListaDeImagem().get(index).getId());
		
	}
	
	
	
	
}
