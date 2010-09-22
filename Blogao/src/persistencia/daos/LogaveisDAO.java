package persistencia.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import ourExceptions.ArgumentInvalidException;
import interfaces.Logavel;

/**
 * Classe DAO que cria, deleta, atualiza e recupera logaveis ({@link Logavel})
 * 
 * 
 */
public class LogaveisDAO {

	private final static String SEPARADOR = System
			.getProperty("file.separator");
	private final static String CAMINHO = "src" + SEPARADOR + "persistencia"
			+ SEPARADOR + "arquivosXML" + SEPARADOR + "logaveis" + SEPARADOR;
	private final static String TIPO_DE_ARQUIVO = ".xml";
	private static LogaveisDAO instancia;
	private static XStream xstream = new XStream(new DomDriver());

	public LogaveisDAO() {

	}

	/**
	 * Recupera uma instancia unica para este objeto {@link EmailsDAO}
	 * 
	 * @return A instancia unica para este objeto {@link EmailsDAO}
	 */
	public static synchronized LogaveisDAO getInstance() {
		if (instancia == null)
			instancia = new LogaveisDAO();
		return instancia;

	}

	// acho que n vamos precisar atualizar os emails
	public void atualizar(Logavel logavel, Logavel newLogavel)
			throws ArgumentInvalidException, IOException {
		if (logavel == null || newLogavel == null
				|| !(new File(CAMINHO + logavel + TIPO_DE_ARQUIVO).exists()))
			throw new ArgumentInvalidException("O login nao é válido");
		File file = new File(CAMINHO + logavel + TIPO_DE_ARQUIVO);
		xstream.toXML(logavel, new FileOutputStream(file));
	}

	/**
	 * Cria um {@link Logavel} no formato de arquivo xml
	 * 
	 * @param email
	 *            O {@link Logavel} a ser criado
	 * @throws ArgumentInvalidException
	 *             Caso o logavel passado como parametro seja null ou ja exista
	 *             como dado persistente
	 * @throws IOException
	 *             Caso haja um problema ao gerar o arquivo xml
	 */
	public void criar(Logavel logavel) throws ArgumentInvalidException,
			IOException {
		if (logavel == null
				|| new File(CAMINHO + logavel + TIPO_DE_ARQUIVO).exists())
			throw new ArgumentInvalidException("O login nao é válido");
		File file = new File(CAMINHO + logavel + TIPO_DE_ARQUIVO);
		xstream.toXML(logavel, new FileOutputStream(file));
	}

	/**
	 * Apaga um {@link Logavel} no formato de arquivo xml
	 * 
	 * @param logavel
	 *            O {@link Logavel} a ser apagado
	 * @throws ArgumentInvalidException
	 *             Caso o logavel passado como parametro seja null ou não exista
	 *             como dado persistente
	 */
	public void deletar(Logavel logavel) throws ArgumentInvalidException {
		if (logavel == null
				|| !(new File(CAMINHO + logavel + TIPO_DE_ARQUIVO).exists()))
			throw new ArgumentInvalidException("O logavell nao é válido");
		File file = new File(CAMINHO + logavel + TIPO_DE_ARQUIVO);
		file.delete();
	}

	/**
	 * Recupera todos os logaveis ({@link Logavel}) como forma de {@link List}
	 * 
	 * @return Uma {@link List} contento todos os logaveis ({@link Logavel})
	 *         persistentes
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public List<Logavel> recuperaLogaveis() throws FileNotFoundException {
		List<Logavel> logaveis = new ArrayList<Logavel>();
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.toString().endsWith(TIPO_DE_ARQUIVO)) {
				Logavel email = (Logavel) xstream.fromXML(new FileInputStream(
						arquivo));
				logaveis.add(email);
			}
		}
		return logaveis;
	}

	/**
	 * Recupera um array dos arquivos contidos no path dos emails
	 * 
	 * @return O array dos arquivos contidos no path dos emails
	 */
	private File[] arrayDosArquivos() {
		File file = new File(CAMINHO);
		return file.listFiles();
	}
}
