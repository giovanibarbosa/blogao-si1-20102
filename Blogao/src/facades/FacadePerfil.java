package facades;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.DataInvalidaException;
import ourExceptions.PersistenceException;
import ourExceptions.SexoInvalidoException;
import ourExceptions.UserInvalidException;
import classes.func.usuario.Perfil;
import classes.gerenciadores.GerenciadorDePerfis;


public class FacadePerfil {
	private static FacadePerfil instance;
	private GerenciadorDePerfis gerentePerfil;
	
	
	protected FacadePerfil() {
		gerentePerfil = GerenciadorDePerfis.getInstance();
	}

	public static FacadePerfil getInstance() {
		if (instance == null) {
			instance = new FacadePerfil();
		}
		return instance;
	}
	

	/**
	 * Metodo que encapsula o metodo createProfile de gerenciador de perfil.
	 * @param login
	 * @param senha
	 * @param nome_exibicao
	 * @param email
	 * @param sexo
	 * @param dataNasc
	 * @param endereco
	 * @param interesses
	 * @param quem_sou_eu
	 * @param filmes
	 * @param musicas
	 * @param livros
	 * @throws Exception 
	 * @throws Exception
	 */
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception{
		gerentePerfil.createProfile(login, senha, nome_exibicao, email, sexo, dataNasc, endereco, interesses, quem_sou_eu, filmes, musicas, livros);
		}

	
	/**
	 * Metodo que encapsula o metodo getProfileInformation de gerenciador de perfil.
	 * @throws DataInvalidaException 
	 * @throws UserInvalidException 
	 * @throws PersistenceException 
	 * @throws ArgumentInvalidException 
	 * @throws FileNotFoundException 
	 */
	public String getProfileInformation(String login, String atributo) throws FileNotFoundException, ArgumentInvalidException, PersistenceException, UserInvalidException, DataInvalidaException{
		return gerentePerfil.getProfileInformation(login, atributo);
			
	}

	/**
	 * Metodo que encapsula o metodo saveData de gerenciador de perfil.
	 */
	public void saveData() {
		gerentePerfil.saveData();
	}

	/**
	 * Metodo que encapsula o metodo loadData de gerenciador de perfil.
	 */
	public void loadData() {
		gerentePerfil.loadData();
	}

	/**
	 * Metodo que encapsula o metodo getListaPerfis de gerenciador de perfil.
	 */
	public List<Perfil> getListaPerfis() {
		return gerentePerfil.getListaPerfis();
	}

	/**
	 * Metodo que encapsula o metodo cleanPersistence de gerenciador de perfil.
	 */
	public void cleanPersistence() {
		gerentePerfil.cleanPersistence();

	}

	/**
	 * Metodo que encapsula o metodo changeProfileInformation de gerenciador de perfil.
	 * @throws SexoInvalidoException 
	 * @throws IOException 
	 * @throws PersistenceException 
	 * @throws UserInvalidException 
	 * @throws ArgumentInvalidException 
	 */
	public void changeProfileInformation(String idSessao, String atributo,
			String novoValor) throws ArgumentInvalidException, UserInvalidException, PersistenceException, IOException, SexoInvalidoException {
		gerentePerfil.changeProfileInformation(idSessao, atributo, novoValor);
	}
	/**
	 * Metodo que encapsula o metodo getPerfilPorNome de gerenciador de perfil.
	 */
	public List<String> getPerfilPorNome(String nome) {
		return gerentePerfil.getPerfilPorNome(nome);
	}
	 
	
	/**
	 * Metodo que encapsula o metodo getPerfilPorInteresse de gerenciador de perfil.
	 */
	public List<String> getPerfilPorInteresse(String interesse) {
		return gerentePerfil.getPerfilPorInteresse(interesse);
	}
	
	
	/**
	 * Metodo que encapsula o metodo getPerfilPorSexo de gerenciador de perfil.
	 */
	public List<String> getPerfilPorSexo(String sexo) {
		return gerentePerfil.getPerfilPorSexo(sexo);
	}
	
	
	
	/**
	 * Metodo que encapsula o metodo deletePerfil de gerenciador de perfil.
	 * @throws PersistenceException 
	 * @throws ArgumentInvalidException 
	 * @throws FileNotFoundException 
	 */
	public void deletePerfil(String sessionId) throws FileNotFoundException, ArgumentInvalidException, PersistenceException{
		gerentePerfil.deletePerfil(sessionId);

	}

}
