package classes.gerenciadores;

import interfaces.Constantes;
import interfaces.Gerenciador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.DataInvalidaException;
import ourExceptions.PersistenceException;
import ourExceptions.UserInvalidException;
import classes.func.usuario.Perfil;
import classes.func.usuario.Usuario;
import classes.Blog;
import classes.Login;
import classes.Senha;
import classes.Email;
import enuns.Sexo;

public class GerenciadorDePerfis implements Gerenciador {

	private Perfil perfil;
	private List<Perfil> listaPerfis;

	// private EmailsDAO emailsDAO = EmailsDAO.getInstance();
	private GerenciadorDeDados gerenteDados;

	public GerenciadorDePerfis(GerenciadorDeDados gerenteDados) {
		listaPerfis = new ArrayList<Perfil>();
		this.gerenteDados = gerenteDados;
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

		perfil.setLoginUsuario(log.getLogin());

		user1.setPerfil(perfil);

		validaEmail(mail);
		gerenteDados.getGerenteUsuarios().validaLogin(log);
		gerenteDados.getGerenciadorDeUsuarios().criarUsuario(user1);
		listaPerfis.add(perfil);
	}

	public String getProfileInformation(String login, String atributo)
			throws ArgumentInvalidException, FileNotFoundException,
			PersistenceException, UserInvalidException, DataInvalidaException {
		String retorno;
		Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);

		Perfil perf = user.getPerfil();
		retorno = perf.getAtributo(atributo);

		if (retorno == null)
			return login;
		return retorno;
	}

	@Override
	public void saveData() {
	}

	@Override
	public void loadData() {
		try {
			listaPerfis = gerenteDados.getGerenteUsuarios().getListaPerfis();
		} catch (Exception e) {
			listaPerfis = new ArrayList<Perfil>();
		}
	}

	/**
	 * @return the listaPerfis
	 */
	public List<Perfil> getListaPerfis() {
		return listaPerfis;
	}

	@Override
	public void cleanPersistence() {
		listaPerfis = new ArrayList<Perfil>();

	}

	private void validaEmail(Email mail) throws ArgumentInvalidException {
		for (Perfil perf : listaPerfis) {
			if (perf.getEmail().equals(mail))
				throw new ArgumentInvalidException(Constantes.EMAIL_EXISTENTE);
		}
	}

	public void changeProfileInformation(String idSessao, String atributo,
			String novoValor) throws ArgumentInvalidException,
			UserInvalidException, PersistenceException, IOException {
		String login = gerenteDados.getGerenteSessoes().getLoginPorSessao(
				idSessao);

		try {
			Usuario us = gerenteDados.getGerenciadorDeUsuarios().getUsuario(
					login);

			if ("senha".equals(atributo)) {
				us.setSenha(new Senha(novoValor));
			} else if ("email".equals(atributo)) {
				validaEmail(new Email(novoValor));
			}

			else if ("login".equals(atributo)) {
				gerenteDados.getGerenciadorDeUsuarios().validaLogin(
						new Login(novoValor));
				gerenteDados.getGerenciadorDeUsuarios().remover(us);
				us.setLogin(new Login(novoValor));
				gerenteDados.getGerenciadorDeUsuarios().adicionar(us);
			}

			else {
				Perfil perfil = us.getPerfil();
				perfil.setAtributo(atributo, novoValor);
				us.setPerfil(perfil);
			}
		} catch (UserInvalidException e) {
			throw new ArgumentInvalidException(Constantes.LOGIN_INVALIDO);
		}

	}

	public List<String> getPerfilPorNome(String nome) {
		List<String> listaPerfil = new ArrayList<String>();
		for (Perfil pf : listaPerfis) {
			if (pf.getNomeDeExibicao().toLowerCase()
					.startsWith(nome.toLowerCase())
					|| pf.getLoginUsuarioDono().toLowerCase()
							.startsWith(nome.toLowerCase()))
				listaPerfil.add(pf.getLoginUsuarioDono());
		}

		return listaPerfil;
	}
	 
	
	//PODE SER APAGADO!
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

	public List<String> getPerfilPorInteresse(String interesse) {
		List<String> listaPerfil = new ArrayList<String>();
		for (Perfil pf : listaPerfis) {
			if (pf.getInteresses().toLowerCase()
					.startsWith(interesse.toLowerCase()))
				listaPerfil.add(pf.getLoginUsuarioDono());
		}
		ordenaPerfisPorNome(listaPerfil);
		return listaPerfil;
	}

	public List<String> getPerfilPorSexo(String sexo) {
		List<String> listaPerfil = new ArrayList<String>();
		if (sexo.equalsIgnoreCase(Sexo.Nao_Inf.getSexo())) {
			for (Perfil pf : listaPerfis) {
				listaPerfil.add(pf.getLoginUsuarioDono());
			}
		ordenaPerfisPorNome(listaPerfil);	
		return listaPerfil; 
		} 
		for (Perfil pf : listaPerfis) {
			if (pf.getSexo().getSexo().equalsIgnoreCase(sexo))
				listaPerfil.add(pf.getLoginUsuarioDono());
			}
		ordenaPerfisPorNome(listaPerfil);
		return listaPerfil;
	}

	public void deletePerfil(String sessionId) throws FileNotFoundException,
			ArgumentInvalidException, PersistenceException {
		Perfil perfil = getPerfil(sessionId);
		List<Blog> listaBlogsAApagar = gerenteDados.getGerenteBlogs()
				.getListaDeBlogsPorIdSessao(sessionId);
		while (!listaBlogsAApagar.isEmpty()) {
			gerenteDados.getGerenteBlogs().deleteBlog(listaBlogsAApagar.get(0));
			listaBlogsAApagar.remove(0);
		}
		listaPerfis.remove(perfil);
		Usuario user = gerenteDados.getGerenteUsuarios()
				.recuperaUsuarioPorIdSessao(sessionId);
		user.setPerfil(null);

	}

	private Perfil getPerfil(String sessionId) throws FileNotFoundException,
			ArgumentInvalidException, PersistenceException {
		Usuario user = gerenteDados.getGerenteUsuarios()
				.recuperaUsuarioPorIdSessao(sessionId);
		return user.getPerfil();
	}

}
