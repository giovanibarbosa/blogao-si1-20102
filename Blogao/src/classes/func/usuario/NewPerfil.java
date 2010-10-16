package classes.func.usuario;

import interfaces.Constantes;
import interfaces.Logavel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.DataInvalidaException;
import ourExceptions.PersistenceException;
import ourExceptions.SexoInvalidoException;

import classes.Announcement;
import classes.Blog;
import classes.Email;
import classes.Login;
import classes.Senha;
import classes.func.Data;
import enuns.Sexo;

public class NewPerfil {
	
	private Email email;
	private String nomeDeExibicao = "";
	private String endereco = "";
	private String interesses = "";
	private Data dataDeNascimento;
	private Sexo sexo;
	private String quemSouEu = "";
	private String filmesFavoritos = "";
	private String musicasFavoritas = "";
	private String livrosFavoritos = "";
	
	private Logavel logavel;
	private List<Blog> listaBlogs;
	private List<Announcement> listaAnnouncements;


	/**
	 * Construtor do objeto Usuario
	 * 
	 * @param log
	 *            {@link Login}
	 * @param sen
	 *            {@link Senha}
	 * @throws Exception
	 *             caso algum dos parametros passados seja invalido
	 */
	public NewPerfil(Login log, Senha sen, Email email) throws Exception {
		logavel = new LogavelImpl(log, sen);
		listaBlogs = new ArrayList<Blog>();
		listaAnnouncements = new ArrayList<Announcement>();
		setEmail(email);
		setSexo(Sexo.Nao_Inf.getSexo());
	}
	
	/**
	 * Metodo modificador de {@link Email}
	 * 
	 * @param email
	 *            {@link Email}
	 */
	public void setEmail(Email email) {
		this.email = email;
	}

	/**
	 * Metodo acessador de login
	 * 
	 * @return {@link Login}
	 */
	public Login getLogin() {
		return logavel.getLogin();
	}

	/**
	 * Metodo modificador de {@link Login}
	 * 
	 * @param {@link Login} novo login
	 * @throws ArgumentInvalidException
	 *             caso o login passado seja invalido
	 */
	public void setLogin(Login log) throws ArgumentInvalidException {
		logavel.setLogin(log);
	}

	/**
	 * Metodo acessador de {@link Senha}
	 * 
	 * @return {@link Senha}
	 */
	public Senha getSenha() {
		return logavel.getSenha();
	}

	/**
	 * Metodo modificador de {@link Senha}
	 * 
	 * @param senha
	 *            {@link Senha}Senha nova.
	 */
	public void setSenha(Senha sen) {
		logavel.setSenha(sen);
	}

	/**
	 * Metodo modificar de {@link Login}
	 * 
	 * @param log
	 *            {@link Login} Novo login.
	 */
	public void setLogavel(Logavel log) {
		this.logavel = log;
	}

	/**
	 * Metodo acessador de lista de blogs
	 * 
	 * @return List<{@link Blog}> lista de blogs do usuario
	 */
	public List<Blog> getListaBlogs() {
		return listaBlogs;
	}

	/**
	 * Metodo que adiciona um {@link Blog} lista de blogs do usuario.
	 * 
	 * @param blg
	 *            {@link Blog}
	 */
	public void addBlog(Blog blg) {
		listaBlogs.add(blg);
	}

	/**
	 * Metodo que remove um {@link Blog} da lista de blogs do usuario.
	 * 
	 * @param blg
	 *            {@link Blog}
	 */
	public void removeBlog(Blog blg) {
		if (listaBlogs.contains(blg))
			listaBlogs.remove(blg);
	}



	@Override
	public String toString() {
		return logavel.toString();
	}

	/**
	 * Metodo acessador de lista de Announcements
	 * 
	 * @return List<{@link Announcement}>
	 */
	public List<Announcement> getListaAnnouncement() {
		return listaAnnouncements;
	}

	/**
	 * Metodo que cadastra um Announcement
	 * 
	 * @param blogId
	 *            {@link String} desejado
	 * @throws {@ArgumentInvalidException} caso o
	 *         Announcement nao seja cadastrado
	 */
	public void addPostAnnouncement(String blogId)
			throws ArgumentInvalidException {
		Announcement announcement = new Announcement(blogId);
		if (!(listaAnnouncements.contains(announcement)))
			listaAnnouncements.add(announcement);
		else
			throw new ArgumentInvalidException(
					Constantes.ANNOUNCEMENT_CADASTRADO);

	}

	/**
	 * Metodo acessador de Announcements
	 * 
	 * @param idBlog
	 *            {@link String}
	 * @return {@liunk Announcement}
	 * @throws {@link ArgumentInvalidException} caso o Announcement seja
	 *         invalido
	 */
	public Announcement getAnnouncementByIdBlog(String idBlog)
			throws ArgumentInvalidException {
		for (Announcement announcement : listaAnnouncements) {
			if (announcement.getIdBlogDeInteresse().equals(idBlog)) {
				return announcement;
			}
		}
		throw new ArgumentInvalidException(Constantes.ANNOUNCEMENT_INVALIDO);
	}

	/**
	 * Metodo que adiciona um aviso ao blog
	 * 
	 * @param blog
	 *            {@link Blog}
	 * @param id
	 *            {@link String} id do announcement
	 */
	public void addAviso(Blog blog, String id) {
		for (Announcement announcement : listaAnnouncements) {
			if (announcement.getIdBlogDeInteresse().equals(blog.getId())) {
				announcement.addAtualizacao(id);
			}
		}

	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Usuario))
			return false;
		Usuario user = (Usuario) obj;
		return getLogin().equals(user.getLogin());
	}
	
	
	/**
	 * Metodo acessador de {@link Email}
	 * 
	 * @return {@link Email}
	 */
	public Email getEmail() {
		return email;
	}

//	/**
//	 * Metodo acessador do login do dono do perfil
//	 * 
//	 * @return {@link String} login
//	 */
//	public String getLoginUsuarioDono() {
//		return loginUsuarioDono;
//	}

//	/**
//	 * Metodo modificador de logins
//	 * 
//	 * @param loginUsuarioDono
//	 *            {@link String}
//	 */
//	public void setLoginUsuario(String loginUsuarioDono) {
//		this.loginUsuarioDono = loginUsuarioDono;
//	}


	/**
	 * Metodo acessador de nome de exibicao
	 * 
	 * @return {@link String}
	 */
	public String getNomeDeExibicao() {
		return nomeDeExibicao;
	}

	/**
	 * Metodo modificador de nome de exibicao
	 * 
	 * @param nomeDeExibicao
	 *            {@link String}
	 */
	public void setNomeDeExibicao(String nomeDeExibicao) {
		if (nomeDeExibicao != null)
			this.nomeDeExibicao = nomeDeExibicao;
	}

	/**
	 * Metodo acessador de Endereco
	 * 
	 * @return {@link String}
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Metodo modificador de endereco
	 * 
	 * @param endereco
	 *            {@link String}
	 * @throws ArgumentInvalidException 
	 */
	public void setEndereco(String endereco) throws ArgumentInvalidException {
		if (endereco == null) {
			throw new ArgumentInvalidException("Endereço inválido");
		}
		this.endereco = endereco;
		
	}

	/**
	 * Metodo acessador de interesses
	 * 
	 * @return {@link String} interesses
	 */
	public String getInteresses() {
		return interesses;
	}

	/**
	 * Metodo modificador de interesses
	 * 
	 * @param interesses
	 *            {@link String}
	 */
	public void setInteresses(String interesses) {
		if (interesses != null)
			this.interesses = interesses;
	}

	/**
	 * Metodo acessador de {@link Data}
	 * 
	 * @return {@link Data}
	 */
	public Data getDataDeNascimento() {
		return dataDeNascimento;
	}

	/**
	 * Metodo modificador de data de nascimento
	 * 
	 * @param dataDeNascimento
	 *            {@link String}
	 * @throws Exception 
	 */
	public void setDataDeNascimento(String dataDeNasc)
			throws Exception {
		dataDeNascimento = new Data(dataDeNasc);
	}

	/**
	 * Metodo acessador de Quem eu sou
	 * 
	 * @return {@link String} quem eu sou
	 */
	public String getQuemSouEu() {
		return quemSouEu;
	}

	/**
	 * Metodo moficador de quem eu sou
	 * 
	 * @param quemSouEu
	 *            {@link String}
	 */
	public void setQuemSouEu(String quemSouEu) {
		if (quemSouEu != null)
			this.quemSouEu = quemSouEu;
	}

	/**
	 * Metodo acessador de Filmes favoritos
	 * 
	 * @return {@link String} filmes favoritos
	 */
	public String getFilmesFavoritos() {
		return filmesFavoritos;
	}

	/**
	 * Metodo modificador de filmes favoritos
	 * 
	 * @param filmesFavoritos
	 *            {@link String}
	 */
	public void setFilmesFavoritos(String filmesFavoritos) {
		if (filmesFavoritos != null)
			this.filmesFavoritos = filmesFavoritos;
	}

	/**
	 * Metodo acessador de musicas favoritas
	 * 
	 * @return {@link String} Musicas favoritas
	 */
	public String getMusicasFavoritas() {
		return musicasFavoritas;
	}

	/**
	 * Metodo modificador de musicas favoritas
	 * 
	 * @param musicasFavoritas
	 *            {@link String}
	 */
	public void setMusicasFavoritas(String musicasFavoritas) {
		if (musicasFavoritas != null)
			this.musicasFavoritas = musicasFavoritas;
	}

	/**
	 * Metodo acessador de livros favoritos
	 * 
	 * @return {@link String} Livros favoritdos
	 */
	public String getLivrosFavoritos() {
		return livrosFavoritos;
	}

	/**
	 * Metodo modificador de livros favoritos
	 * 
	 * @param livrosFavoritos
	 *            {@link String}
	 */
	public void setLivrosFavoritos(String livrosFavoritos) {
		if (livrosFavoritos != null)
			this.livrosFavoritos = livrosFavoritos;
	}

	/**
	 * Metodo modificador de {@link Sexo}
	 * 
	 * @param sex
	 *            {@link Sexo}
	 * @throws ArgumentInvalidException
	 *             caso o sexo seja invalido
	 */
	public void setSexo(String sex) throws SexoInvalidoException {
		if (!Sexo.verificaSexo(sex)) {
			throw new SexoInvalidoException(Constantes.SEXO_INVALIDO);
		}
		this.sexo = Sexo.setadorSexo(sex);
	}

	/**
	 * Metodo acessador de {@link Sexo}
	 * 
	 * @return {@link Sexo}
	 */
	public Sexo getSexo() {
		return sexo;
	}

	/**
	 * Metodo acessador de atributo
	 * 
	 * @param atributo
	 *            {@link String} desejado
	 * @return {@link String} o atributo do Perfil
	 * @throws ArgumentInvalidException
	 *             caso o atributo passado seja invalido
	 * @throws DataInvalidaException 
	 */
	public String getAtributo(String atributo) throws ArgumentInvalidException, DataInvalidaException {
		if (atributo == null)
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO);
		int codigoAtributo = atributo.hashCode();

		switch (codigoAtributo) {
		case (Constantes.EMAIL):
			return this.email.toString();
		case (Constantes.NOME):
//			if (this.nomeDeExibicao.equals(""))
//				return this.loginUsuarioDono;
			return this.nomeDeExibicao;
		case (Constantes.ENDERECO):
			return this.endereco;
		case (Constantes.INTERESSES):
			return this.interesses;
		case (Constantes.DATA):
			if(dataDeNascimento != null)
				return Data.calendarToString(dataDeNascimento.getData());
			return null;
		case (Constantes.SEXO):
			return sexo.getSexo();
		case (Constantes.QUEM):
			return quemSouEu;
		case (Constantes.FILMES):
			return filmesFavoritos;
		case (Constantes.MUSICAS):
			return musicasFavoritas;
		case (Constantes.LIVROS):
			return livrosFavoritos;

		default:
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO);
		}
	}

	/**
	 * Metodo modificador de atributo
	 * 
	 * @param atributo
	 *            {@link String} desejado
	 * @param novoValor
	 *            {@link String}
	 * @throws ArgumentInvalidException
	 *             caso o atributo passado seja invalido
	 * @throws PersistenceException
	 * @throws IOException
	 */
	public void setAtributo(String atributo, String novoValor)
			throws ArgumentInvalidException, PersistenceException, IOException,
			SexoInvalidoException, Exception {
		if (atributo == null)
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO);
		int codigoAtributo = atributo.hashCode();

		switch (codigoAtributo) {
		case (Constantes.EMAIL):
			this.setEmail(new Email(novoValor));
			break;
		case (Constantes.NOME):
			this.setNomeDeExibicao(novoValor);
			break;
		case (Constantes.ENDERECO):
			this.setEndereco(novoValor);
			break;
		case (Constantes.INTERESSES):
			this.setInteresses(novoValor);
			break;
		case (Constantes.DATA):
			this.setDataDeNascimento(novoValor);
			break;
		case (Constantes.SEXO):
			this.setSexo(novoValor);
			break;
		case (Constantes.QUEM):
			this.setQuemSouEu(novoValor);
			break;
		case (Constantes.FILMES):
			this.setFilmesFavoritos(novoValor);
			break;
		case (Constantes.MUSICAS):
			this.setMusicasFavoritas(novoValor);
			break;
		case (Constantes.LIVROS):
			this.setLivrosFavoritos(novoValor);
			break;

		default:
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO);
		}
	}

	//@Override
	public String toString2() {
		return nomeDeExibicao;
	}


	
}
