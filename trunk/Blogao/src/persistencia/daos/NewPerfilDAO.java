package persistencia.daos;

import interfaces.Constantes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import classes.Email;
import classes.Login;
import classes.Senha;
import classes.func.usuario.NewPerfil;
import classes.func.usuario.Usuario;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Classe DAO que cria, deleta, atualiza e recupera um perfil ({@link Perfil})
 * no BD.
 * @author Tiago Leite - tiagohsl@lcc.ufcg.edu.br
 */

public class NewPerfilDAO {

	private final static String SEPARADOR = System
			.getProperty("file.separator");
	
	private final static String CAMINHO = "src" + SEPARADOR + "persistencia"
			+ SEPARADOR + "arquivosXML" + SEPARADOR + "perfis" + SEPARADOR;
	
	private final static String TIPO_DE_ARQUIVO = ".xml";
	private static NewPerfilDAO instancia;
	private static XStream xstream = new XStream(new DomDriver());


	/**
	 * Recupera uma instancia unica para este objeto {@link UsuariosDAO}
	 * 
	 * @return A instancia unica para este objeto {@link UsuariosDAO}
	 */
	public static synchronized NewPerfilDAO getInstance() {
		if (instancia == null)
			instancia = new NewPerfilDAO();
		return instancia;

	}

	/**
	 * Cria um {@link Perfil} no formato de arquivo xml
	 * 
	 * @param perfil
	 *            O {@link Perfil} a ser criado
	 * @throws ArgumentInvalidException
	 *             Caso o usuario passado como parametro seja null
	 * @throws IOException
	 *             Caso haja um problema ao gerar o arquivo xml
	 */
	public void criar(NewPerfil perfil) throws PersistenceException, IOException {
		if (perfil == null
				|| new File(CAMINHO + perfil + TIPO_DE_ARQUIVO).exists()) {
			throw new PersistenceException(Constantes.LOGIN_EXISTENTE);
		}
		File file = new File(CAMINHO + perfil + TIPO_DE_ARQUIVO);
		xstream.toXML(perfil, new FileOutputStream(file));
	}

	/**
	 * Apaga um {@link Perfil} no formato de arquivo xml
	 * 
	 * @param Perfil
	 *            O {@link Perfil} a ser apagado
	 * @throws ArgumentInvalidException
	 *             Caso o Perfil passado como parametro seja null ou nao exista
	 *             como dado persistente
	 */
	public void deletar(NewPerfil perfil) throws PersistenceException {
		if (perfil == null
				|| !(new File(CAMINHO + perfil + TIPO_DE_ARQUIVO).exists())) {
			throw new PersistenceException(Constantes.USUARIO_NAO_PODE_SER_REMOVIDO);
		}
		File file = new File(CAMINHO + perfil + TIPO_DE_ARQUIVO);
		file.delete();
	}

	/**
	 * Recupera todos os perfis ({@link Perfil}) como forma de {@link List}
	 * 
	 * @return Uma {@link List} contento todos os usuarios ({@link Perfil})
	 *         persistentes
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public List<NewPerfil> recuperaUsuarios() throws FileNotFoundException {
		List<NewPerfil> perfis = new ArrayList<NewPerfil>();
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.toString().endsWith(TIPO_DE_ARQUIVO)) {
				NewPerfil perfil = (NewPerfil) xstream
						.fromXML(new FileInputStream(arquivo));
				perfis.add(perfil);
			}
		}
		return perfis;
	}

	/**
	 * Atualiza as informacoes do {@link Usuario} passado como parametro a
	 * partir de um {@link Usuario} atualizado
	 * 
	 * @param usuario
	 *            O {@link Usuario} a ser atualizado
	 * @param usuarioAtualizado
	 *            O {@link Usuario} atualizado
	 * @throws ArgumentInvalidException
	 *             Caso o {@link Usuario} a ser atualizado ou o {@link Usuario}
	 *             atualizado sejam null, ou o {@link Usuario} a ser atualizado
	 *             nao exista de forma persistente
	 * @throws IOException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public void atualizar(NewPerfil perfil) throws PersistenceException,
			IOException {
		if (perfil == null
				|| !(new File(CAMINHO + perfil + TIPO_DE_ARQUIVO).exists())) {
			throw new PersistenceException(Constantes.USUARIO_EXISTENTE);
		}
		this.deletar(perfil);
		this.criar(perfil);
	}


	/**
	 * Limpa todos os arquivos contendo os usuarios {@link Usuario}
	 */
	public void limparUsuarios() {
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.toString().endsWith(TIPO_DE_ARQUIVO))
				arquivo.delete();
		}
	}

	/**
	 * Recupera um array dos arquivos contidos no path dos usuarios
	 * @return O array dos arquivos contidos no path dos usuarios
	 */
	private File[] arrayDosArquivos() {
		File file = new File(CAMINHO);
		return file.listFiles();
	}
	
}
