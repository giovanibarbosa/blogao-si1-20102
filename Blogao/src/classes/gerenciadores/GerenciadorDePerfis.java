package classes.gerenciadores;

import interfaces.Gerenciador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.DataInvalidaException;
import ourExceptions.PersistenceException;
import ourExceptions.SexoInvalidoException;
import ourExceptions.UserInvalidException;
import classes.Blog;
import classes.Email;
import classes.Login;
import classes.Senha;
import classes.func.usuario.Perfil;
import classes.func.usuario.Usuario;
import enuns.Constantes2;
import enuns.Sexo;

public class GerenciadorDePerfis implements Gerenciador {

	private Perfil perfil;
	private List<Perfil> listaPerfis = new ArrayList<Perfil>();;
	private static GerenciadorDePerfis instancia;

	
	private GerenciadorDePerfis() {
	}
	
	public static GerenciadorDePerfis getInstance() {
		if(instancia == null)
			instancia = new GerenciadorDePerfis();
		return instancia;
	}

	@Override
	public void saveData() {
	}

	@Override
	public void loadData() {
		try {
			listaPerfis = GerenciadorDeDados.getInstance().getGerenteUsuarios().getListaPerfis();
		} catch (Exception e) {
			listaPerfis = new ArrayList<Perfil>();
		}
	}

	@Override
	public void cleanPersistence() {
		listaPerfis = new ArrayList<Perfil>();
	
	}

	public String getProfileInformation(String login, String atributo)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException, UserInvalidException, DataInvalidaException {
		String retorno;
		Usuario user = getUser(login);

		Perfil perf = user.getPerfil();
		retorno = perf.getAtributo(atributo);

		if (retorno == null)
			return login;
		return retorno;
	}

	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {
	
		Login log = new Login(login);
		Senha sen = new Senha(senha);
		Email mail = new Email(email);
	
		perfil = new Perfil();
		perfil.setNomeDeExibicao(nome_exibicao);
		perfil.setEmail(mail);
		perfil.setSexo(sexo);
		perfil.setDataDeNascimento(dataNasc);
		perfil.setEndereco(endereco);
		perfil.setInteresses(interesses);
		perfil.setQuemSouEu(quem_sou_eu);
		perfil.setFilmesFavoritos(filmes);
		perfil.setMusicasFavoritas(musicas);
		perfil.setLivrosFavoritos(livros);
	
		Usuario user1 = new Usuario(log, sen, perfil);
	
		perfil.setLoginUsuario(log.getName());
	
		user1.setPerfil(perfil);
	
		validaEmail(mail);
		validaLogin(log);
		criaUsuario(user1);
		listaPerfis.add(perfil);
	}

	public void changeProfileInformation(String idSessao, String atributo,
			String novoValor) throws ArgumentInvalidException,
			UserInvalidException, PersistenceException, IOException, SexoInvalidoException {
		String login = getLoginBySessionId(idSessao);

		try {
			Usuario usuario = getUserByLogin(login);

			if (Constantes2.SENHA.getName().equals(atributo)) {
				usuario.setSenha(new Senha(novoValor));
			} else if (Constantes2.EMAIL.getName().equals(atributo)) {
				validaEmail(new Email(novoValor));
			}

			else if (Constantes2.LOGIN.getName().equals(atributo)) {
				validaLogin(novoValor);
				removeUsuario(usuario);
				usuario.setLogin(new Login(novoValor));
				addUsuario(usuario);
			}

			else {
				Perfil perfil = usuario.getPerfil();
				perfil.setAtributo(atributo, novoValor);
				usuario.setPerfil(perfil);
			}
		} catch (UserInvalidException e) {
			throw new ArgumentInvalidException(Constantes2.LOGIN_INVALIDO.getName());
		}

	}

	/**
	 * @return the listaPerfis
	 */
	public List<Perfil> getListaPerfis() {
		return listaPerfis;
	}

	public List<String> getPerfilPorNome(String nome) {
		List<String> listaPerfil = new ArrayList<String>();
		Iterator<Perfil> it = iteradorPerfil();
		while(it.hasNext()){
			Perfil perfil = (Perfil) it.next();
			if (primeiraLetraPerfil(nome, perfil)
					|| primeiraLetraDono(nome, perfil))
				listaPerfil.add(perfil.getLoginUsuarioDono());
		}
		return listaPerfil;
	}

	public List<String> getPerfilPorInteresse(String interesse) {
		List<String> listaPerfil = new ArrayList<String>();
		Iterator<Perfil> it = iteradorPerfil();
		while(it.hasNext()){
			Perfil perfil = (Perfil) it.next();
			if (interessesEquivalentes(interesse, perfil))
				listaPerfil.add(perfil.getLoginUsuarioDono());
		}
		ordenaPerfisPorNome(listaPerfil);
		return listaPerfil;
	}

	public List<String> getPerfilPorSexo(String sexo) {
		List<String> listaPerfil = new ArrayList<String>();
		if (sexo.equalsIgnoreCase(Sexo.Nao_Inf.getSexo())) {
			Iterator<Perfil> it = iteradorPerfil();
			while(it.hasNext()){
				Perfil perfil = (Perfil) it.next();
				listaPerfil.add(perfil.getLoginUsuarioDono());
			}
		ordenaPerfisPorNome(listaPerfil);	
		return listaPerfil; 
		} 
		Iterator<Perfil> it = iteradorPerfil();
		while(it.hasNext()){
			Perfil perfil = (Perfil) it.next();
			if (perfil.getSexo().getSexo().equalsIgnoreCase(sexo))
				listaPerfil.add(perfil.getLoginUsuarioDono());
			}
		ordenaPerfisPorNome(listaPerfil);
		return listaPerfil;
	}

	public void deletePerfil(String sessionId) throws FileNotFoundException,
			ArgumentInvalidException, PersistenceException {
		Perfil perfil = getPerfil(sessionId);
		List<Blog> listaBlogsAApagar = listaBlogsDel(sessionId);
		while (!listaBlogsAApagar.isEmpty()) {
			deletaBlog(listaBlogsAApagar);
			listaBlogsAApagar.remove(0);
		}
		listaPerfis.remove(perfil);
		Usuario user = getUserBySessionId(sessionId);
		user.setPerfil(null);
	}

	private void criaUsuario(Usuario user1) {
		GerenciadorDeDados.getInstance().getGerenciadorDeUsuarios().criarUsuario(user1);
	}

	private void validaLogin(Login log) throws ArgumentInvalidException {
		GerenciadorDeDados.getInstance().getGerenteUsuarios().validaLogin(log);
	}

	private Usuario getUser(String login) throws UserInvalidException {
		return GerenciadorDeDados.getInstance().getGerenteUsuarios().getUsuario(login);
	}

	private void validaEmail(Email mail) throws ArgumentInvalidException {
		Iterator<Perfil> it = iteradorPerfil();
		while(it.hasNext()){
			Perfil perfil = (Perfil) it.next();
			if (perfil.getEmail().equals(mail))
				throw new ArgumentInvalidException(Constantes2.EMAIL_EXISTENTE.getName());
		}
	}

	private void addUsuario(Usuario usuario) {
		GerenciadorDeDados.getInstance().getGerenciadorDeUsuarios().adicionar(usuario);
	}

	private void removeUsuario(Usuario usuario) {
		GerenciadorDeDados.getInstance().getGerenciadorDeUsuarios().remover(usuario);
	}

	private void validaLogin(String novoValor) throws ArgumentInvalidException {
		GerenciadorDeDados.getInstance().getGerenciadorDeUsuarios().validaLogin(
				new Login(novoValor));
	}

	private Usuario getUserByLogin(String login) throws UserInvalidException {
		return GerenciadorDeDados.getInstance().getGerenciadorDeUsuarios().getUsuario(
				login);
	}

	private String getLoginBySessionId(String idSessao)
			throws ArgumentInvalidException {
		return GerenciadorDeDados.getInstance().getGerenteSessoes().getLoginPorSessao(
				idSessao);
	}

	private boolean primeiraLetraDono(String nome, Perfil perfil) {
		return perfil.getLoginUsuarioDono().toLowerCase()
				.startsWith(nome.toLowerCase());
	}

	private boolean primeiraLetraPerfil(String nome, Perfil perfil) {
		return perfil.getNomeDeExibicao().toLowerCase()
				.startsWith(nome.toLowerCase());
	}

	private void ordenaPerfisPorNome(List<String> lista) {
		for (int i = 0; i < lista.size() - 1; i++) {
			for (int j = 0; j < lista.size() - 1 - i; j++) {
				if (lista.get(j).compareToIgnoreCase(lista.get(j + 1)) > 0) {
					String elemento1 = lista.get(j);
					String elemento2 = lista.get(j + 1);
					lista.set(j + 1, elemento1);
					lista.set(j, elemento2);
				}
			}
		}
	}

	private boolean interessesEquivalentes(String interesse, Perfil perfil) {
		return perfil.getInteresses().toLowerCase()
				.startsWith(interesse.toLowerCase());
	}

	private Usuario getUserBySessionId(String sessionId)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException {
		return GerenciadorDeDados.getInstance().getGerenteUsuarios()
				.recuperaUsuarioPorIdSessao(sessionId);
	}

	private void deletaBlog(List<Blog> listaBlogsAApagar)
			throws PersistenceException, ArgumentInvalidException {
		GerenciadorDeDados.getInstance().getGerenteBlogs().deleteBlog(listaBlogsAApagar.get(0));
	}

	private List<Blog> listaBlogsDel(String sessionId) {
		return GerenciadorDeDados.getInstance().getGerenteBlogs()
				.getListaDeBlogsPorIdSessao(sessionId);
	}

	private Perfil getPerfil(String sessionId) throws FileNotFoundException,
			ArgumentInvalidException, PersistenceException {
		Usuario user = getUserBySessionId(sessionId);
		return user.getPerfil();
	}
	
	/**
	 * Iterador sobre a lista de Perfis.
	 * @return Iterator<Perfil>
	 */
	public Iterator<Perfil> iteradorPerfil(){
		return new Iterator<Perfil>() {
			private int cursor = 0;


			@Override
			public boolean hasNext() {
				while(cursor < listaPerfis.size()) {
					if(listaPerfis.get(cursor) instanceof Perfil)
						return true;
					cursor++;
				}				
				return false;
			}


			@Override
			public Perfil next() {
				try {
					Perfil b = listaPerfis.get(cursor);
					if(b instanceof Perfil) {
						cursor++;
						return b;
					}
					cursor++;
				} catch (NoSuchElementException e) {
					throw e;
				}
				return next();
			}


			@Override
			public void remove() {				
			}
		};
	}

}
