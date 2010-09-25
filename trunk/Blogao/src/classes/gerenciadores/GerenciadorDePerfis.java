package classes.gerenciadores;

import interfaces.Gerenciador;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.EmailsDAO;
import persistencia.daos.UsuariosDAO;
import classes.func.usuario.Perfil;
import classes.func.usuario.Usuario;
import classes.Comentario;
import classes.Login;
import classes.Senha;
import classes.Email;

public class GerenciadorDePerfis implements Gerenciador {
	
	private Perfil perfil;
	
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private EmailsDAO emailsDAO = EmailsDAO.getInstance();
	
	
	
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

		emailsDAO.criar(mail);
		userDAO.criar(user1);
	}
	
	
	public String getProfileInformation(String login, String atributo) throws ArgumentInvalidException {
		String retorno;
		try {
			Usuario us = userDAO.recupera(login);
			Perfil perf = us.getPerfil();
			retorno = perf.getAtributo(atributo);
			
			if(retorno == null)
				return login;
			
		} catch (FileNotFoundException e) {
			return e.getMessage();
		} catch (PersistenceException e) {
			return e.getMessage();
		} catch (ArgumentInvalidException e) {
			throw e;
		}
		return retorno;
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void loadData() {
		//TODO Man-generated method stub
//		try {
//			listaPerfis = perfisDAO.recuperaPerfis();
//		} catch (FileNotFoundException e) {
//			listaPerfis = new ArrayList<Perfil>();
//		}

	}

}
