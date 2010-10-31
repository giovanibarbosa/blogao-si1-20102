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
import classes.Comentario;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XomDriver;

/**
 * Classe DAO que cria, deleta, atualiza e recupera comentarios ({@link Comentario})
 * no BD.
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @colaborator Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 */
public class ComentariosDAO {

	private final static String SEPARADOR = System
			.getProperty("file.separator");
	private final static String CAMINHO = "src" + SEPARADOR + "persistencia"
			+ SEPARADOR + "arquivosXML" + SEPARADOR + "comentarios" + SEPARADOR;
	private final static String TIPO_DE_ARQUIVO = ".xml";
	private static ComentariosDAO instancia;
	private static XStream xstream = new XStream(new XomDriver());


	/**
	 * Recupera uma instancia unica para este objeto {@link ComentariosDAO}
	 * @return {@link ComentariosDAO} A instancia unica para este objeto 
	 */
	public static synchronized ComentariosDAO getInstance() {
		if (instancia == null)
			instancia = new ComentariosDAO();
		return instancia;

	}
	
	/**
	 * Cria um {@link Comentario} no formato de arquivo xml
	 * 
	 * @param comentario
	 *            O {@link Comentario} a ser criado
	 * @throws ArgumentInvalidException
	 *             Caso o comentario passado como parametro seja null
	 * @throws IOException
	 *             Caso haja um problema ao gerar o arquivo xml
	 */
	public void criar(Comentario comentario) throws PersistenceException, IOException {
		if (comentario == null)
			throw new PersistenceException(Constantes.COMENTARIO_NAO_CRIADO);
		File file = new File(CAMINHO + comentario + TIPO_DE_ARQUIVO);
		xstream.toXML(comentario, new FileOutputStream(file));
	}
	
	/**
	 * Apaga um {@link Comentario} no formato de arquivo xml
	 * 
	 * @param comentario
	 *            O {@link Comentario} a ser apagado
	 * @throws ArgumentInvalidException
	 *             Caso o comentario passado como parametro seja null ou não exista
	 *             como dado persistente
	 */
	public void deletar(Comentario comentario) throws PersistenceException {
		if (comentario == null
				|| !(new File(CAMINHO + comentario + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(Constantes.COMENTARIO_NAO_PODE_SER_REMOVIDO);
		File file = new File(CAMINHO + comentario + TIPO_DE_ARQUIVO);
		file.delete();
	}
	
	
	/**
	 * Recupera todos os comentarios ({@link Comentario}) como forma de {@link List}
	 * 
	 * @return Uma {@link List} contento todos os comentarios ({@link Comentario})
	 *         persistentes
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public List<Comentario> recuperaComentarios() throws FileNotFoundException {
		List<Comentario> comentarios = new ArrayList<Comentario>();
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.toString().endsWith(TIPO_DE_ARQUIVO)) {
				Comentario comentario = (Comentario) xstream
						.fromXML(new FileInputStream(arquivo));
				comentarios.add(comentario);
			}
		}
		return comentarios;
	}
	
	/**
	 * Recupera um {@link Comentario} de um arquivo xml
	 * 
	 * @param comentario
	 *            O {@link Comentario} a ser recuperado do arquivo xml
	 * @return O {@link Comentario} recuperado de um arquivo xml
	 * @throws PersistenceException
	 *             Caso o usuario passado como parametro seja null ou não exista
	 *             como dado persistente
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public Comentario recupera(Comentario comentario) throws PersistenceException,
			FileNotFoundException {
		if (comentario == null
				|| !(new File(CAMINHO + comentario + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(Constantes.COMENTARIO_INEXISTENTE);
		File file = new File(CAMINHO + comentario + TIPO_DE_ARQUIVO);
		return (Comentario) xstream.fromXML(new FileInputStream(file));
	}
	
	/**
	 * Limpa todos os arquivos contendo os comentarios {@link Comentario}
	 */
	public void limparComentarios() {
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.toString().endsWith(TIPO_DE_ARQUIVO))
				arquivo.delete();
		}
	}
	
	/**
	 * Recupera um array dos arquivos contidos no path dos comentarios
	 * @return O array dos arquivos contidos no path dos comentarios
	 */
	private File[] arrayDosArquivos() {
		File file = new File(CAMINHO);
		return file.listFiles();
	}
	
}
