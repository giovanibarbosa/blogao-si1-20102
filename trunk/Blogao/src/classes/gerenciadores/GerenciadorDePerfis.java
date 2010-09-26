package classes.gerenciadores;

import interfaces.Constantes;
import interfaces.Gerenciador;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.generic.NEW;

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
	private List<Perfil> listaPerfis;
	
	//private EmailsDAO emailsDAO = EmailsDAO.getInstance();
	private GerenciadorDeDados gerenteDados ;
	
	
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

		//emailsDAO.criar(mail);
		validaEmail(mail);
		gerenteDados.getGerenteUsuarios().validaLogin(log);
		gerenteDados.getGerenciadorDeUsuarios().criarUsuario(user1);
		listaPerfis.add(perfil);
	}
	
	
	public String getProfileInformation(String login, String atributo)
			throws ArgumentInvalidException, FileNotFoundException, PersistenceException {
			String retorno;
			Usuario user = gerenteDados.getGerenteUsuarios().getUsuario(login);
			
			Perfil perf = user.getPerfil();
			retorno = perf.getAtributo(atributo);
			
			if(retorno == null)
				return login;
		return retorno;
	}

	@Override
	public void saveData() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
	
	private void validaEmail(Email mail) throws ArgumentInvalidException {
		for (Perfil perf : listaPerfis) {
			if (perf.getEmail().equals(mail))
				throw new ArgumentInvalidException(Constantes.EMAIL_EXISTENTE);
		}
	}
	
	
	

}
