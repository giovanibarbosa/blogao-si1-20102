package classes.func.usuario;

import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;

import interfaces.Logavel;
import classes.Blog;
import classes.Login;
import classes.Senha;

/**
 * Classe que inicializa um Usuario
 * @author  Rodolfo Marinho -  rodolfoams@lcc.ufcg.edu.br
 * @colaborator Tiago Leite - tiagohsl@lcc.ufcg.edu.br
 */
public class Usuario {

	private Logavel logavel;
	private Perfil perfil;
	private List<Blog> listaBlogs;
	
	/**
	 * Construtor do objeto Usuario
	 * @param {@link Login}
	 * @param {@link Senha}
	 * @throws Exception caso algum dos parametros passados seja invalido
	 */
	public Usuario(Login log, Senha sen) throws Exception {
		logavel = new LogavelImpl(log, sen); 
		listaBlogs = new ArrayList<Blog>();
	}
	
	/**
	 * Construtor do objeto Usuario
	 * @param {@link Login}
	 * @param {@link Senha}
	 * @param {@link Perfil}
	 */
	public Usuario(Login log, Senha sen, Perfil per) {
		this.logavel = new LogavelImpl(log, sen);
		setPerfil(per);
		listaBlogs = new ArrayList<Blog>();
	}

	/**
	 * Metodo acessador de login
	 * @return {@link Login}
	 */
	public Login getLogin() {
		return logavel.getLogin();
	}
	
	/**
	 * Metodo modificador de {@link Login}
	 * @param {@link Login} novo login
	 * @throws ArgumentInvalidException caso o login passado seja invalido
	 */
	public void setLogin(Login log) throws ArgumentInvalidException {
		logavel.setLogin(log);
	}
	
	/**
	 * Metodo acessador de {@link Senha}
	 * @return {@link Senha}
	 */
	public Senha getSenha() {
		return logavel.getSenha();
	}
	
	/**
	 * Metodo modificador de {@link Senha}
	 * @param {@link Senha} nova senha
	 */
	public void setSenha(Senha sen) {
		logavel.setSenha(sen);
	}
	
	/**
	 * Metodo acessador de {@link Perfil}
	 * @return {@link Perfil}
	 */
	public Perfil getPerfil() {
		return perfil;
	}

	/**
	 * Metodo modificador de {@link Perfil}
	 * @param {@link Perfil} novo perfil
	 */
	public void setPerfil(Perfil perfil) {
		if (perfil != null )
			this.perfil = perfil;
	}
	
	/**
	 * Metodo modificar de {@link Login}
	 * @param {@link Login} novo login
	 */
	public void setLogavel(Logavel log) {
		this.logavel = log;
	}
	
	/**
	 * Metodo acessador de lista de blogs
	 * @return List<{@link Blog}> lista de blogs do usuario
	 */
	public List<Blog> getListaBlogs() {
		return listaBlogs;
	}
	
	/**
	 * Metodo que adiciona um {@link Blog} lista de blogs do usuario.
	 * @param {@link Blog}
	 */
	public void addBlog2(Blog blg) {
		if(!listaBlogs.contains(blg))
			listaBlogs.add(blg);
	}
	
	/**
	 * Metodo que remove um {@link Blog} da lista de blogs do usuario.
	 * @param {@link Blog}
	 */
	public void removeBlog2(Blog blg) {
		if(listaBlogs.contains(blg))
			listaBlogs.remove(blg);
	}

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Usuario)) return false;
		Usuario user = (Usuario) obj;
		return getLogin().equals(user.getLogin());
	}
	
	@Override
	public String toString(){
		return logavel.toString();
	}

}
