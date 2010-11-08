package persistencia.daos;


import interfaces.Constantes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import classes.func.usuario.Usuario;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import enuns.Constantes2;

/**
 * Classe DAO que cria, deleta, atualiza e recupera usuarios ({@link Usuario})
 * no BD.
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 */

public class UsuariosDAO {

	private final static String SEPARADOR = System
			.getProperty("file.separator");
	private final static String CAMINHO = "src" + SEPARADOR + "persistencia"
			+ SEPARADOR + "arquivosXML" + SEPARADOR + "usuarios" + SEPARADOR;
	private final static String TIPO_DE_ARQUIVO = ".xml";
	private static UsuariosDAO instancia;
	private static XStream xstream = new XStream(new DomDriver());
	
	private UsuariosDAO() {
		
	}
	
	/**
	 * Recupera uma instancia unica para este objeto {@link UsuariosDAO}
	 * 
	 * @return A instancia unica para este objeto {@link UsuariosDAO}
	 */
	public static synchronized UsuariosDAO getInstance() {
		if (instancia == null)
			instancia = new UsuariosDAO();
		return instancia;

	}

	/**
	 * Cria um {@link Usuario} no formato de arquivo xml
	 * 
	 * @param usuario
	 *            O {@link Usuario} a ser criado
	 * @throws ArgumentInvalidException
	 *             Caso o usuario passado como parametro seja null
	 * @throws IOException
	 *             Caso haja um problema ao gerar o arquivo xml
	 */
	public void criar(Usuario usuario) throws IOException {
		if (usuario != null) {
			File file = new File(CAMINHO + usuario + TIPO_DE_ARQUIVO);
			file.getParentFile().mkdirs();
			FileOutputStream output = new FileOutputStream(file);
			xstream.toXML(usuario, output);
			output.close();
		}
	}

	/**
	 * Apaga um {@link Usuario} no formato de arquivo xml
	 * 
	 * @param usuario
	 *            O {@link Usuario} a ser apagado
	 * @throws ArgumentInvalidException
	 *             Caso o usuario passado como parametro seja null ou nao exista
	 *             como dado persistente
	 */
	public void deletar(Usuario usuario) throws PersistenceException {
		if (usuario == null
				|| !(new File(CAMINHO + usuario + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(
					Constantes2.USUARIO_NAO_PODE_SER_REMOVIDO.getName());
		File file = new File(CAMINHO + usuario + TIPO_DE_ARQUIVO);
		file.delete();
	}

	/**
	 * Recupera todos os usuarios ({@link Usuario}) como forma de {@link List}
	 * 
	 * @return Uma {@link List} contento todos os usuarios ({@link Usuario})
	 *         persistentes
	 * @throws IOException
	 */
	public List<Usuario> recuperaUsuarios() throws IOException {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.getName().endsWith(TIPO_DE_ARQUIVO)) {
				FileInputStream input = new FileInputStream(arquivo);
				Usuario usuario = (Usuario) xstream.fromXML(input);
				usuarios.add(usuario);
				input.close();
			}
		}
		return usuarios;
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
	public void atualizar(Usuario usuario) throws PersistenceException,
			IOException {
		if (usuario == null
				|| !(new File(CAMINHO + usuario + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(Constantes2.USUARIO_EXISTENTE.getName());
		this.deletar(usuario);
		this.criar(usuario);
	}

	/**
	 * Limpa todos os arquivos contendo os usuarios {@link Usuario}
	 */
	public void limparUsuarios() {
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.getName().endsWith(TIPO_DE_ARQUIVO) && !arquivo.getName().endsWith(".svn")) {
				System.gc();
				arquivo.delete();					
			}
		}
	}

	/**
	 * Recupera um array dos arquivos contidos no path dos usuarios
	 * 
	 * @return O array dos arquivos contidos no path dos usuarios
	 */
	private File[] arrayDosArquivos() {
		File file = new File(CAMINHO);
		file.mkdirs();
		return file.listFiles();
	}
}
