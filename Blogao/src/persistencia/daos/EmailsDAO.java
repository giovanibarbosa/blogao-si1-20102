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

import ourExceptions.PersistenceException;
import classes.Blog;
import classes.Email;

/**
 * Classe DAO que cria, deleta, atualiza e recupera emails ({@link Email})
 * 
 * @author giovanicb
 * 
 */
public class EmailsDAO {

	private final static String SEPARADOR = System
			.getProperty("file.separator");
	private final static String CAMINHO = "src" + SEPARADOR + "persistencia"
			+ SEPARADOR + "arquivosXML" + SEPARADOR + "emails" + SEPARADOR;
	private final static String TIPO_DE_ARQUIVO = ".xml";
	private static EmailsDAO instancia;
	private static XStream xstream = new XStream(new DomDriver());

	private EmailsDAO() {

	}

	/**
	 * Recupera uma instancia unica para este objeto {@link EmailsDAO}
	 * 
	 * @return A instancia unica para este objeto {@link EmailsDAO}
	 */
	public static synchronized EmailsDAO getInstance() {
		if (instancia == null)
			instancia = new EmailsDAO();
		return instancia;

	}

	// FIXME
	// acho que n vamos precisar atualizar os emails
	public void atualizar(Email email) throws PersistenceException, IOException {
		if (email == null
				|| !(new File(CAMINHO + email + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException("O email nao é válido");
		File file = new File(CAMINHO + email + TIPO_DE_ARQUIVO);
		xstream.toXML(email, new FileOutputStream(file));
	}

	/**
	 * Cria um {@link Email} no formato de arquivo xml
	 * 
	 * @param email
	 *            O {@link Email} a ser criado
	 * @throws PersistenceException
	 *             Caso o email passado como parametro seja null ou ja exista
	 *             como dado persistente
	 * @throws IOException
	 *             Caso haja um problema ao gerar o arquivo xml
	 */
	public void criar(Email email) throws PersistenceException, IOException {
		if (email == null
				|| new File(CAMINHO + email + TIPO_DE_ARQUIVO).exists())
			throw new PersistenceException("O email nao é válido");
		File file = new File(CAMINHO + email + TIPO_DE_ARQUIVO);
		xstream.toXML(email, new FileOutputStream(file));
	}

	// FIXME
	public void atualizar(Email email, Email novoEmail)
			throws PersistenceException {
	}

	/**
	 * Apaga um {@link Email} no formato de arquivo xml
	 * 
	 * @param email
	 *            O {@link Email} a ser apagado
	 * @throws PersistenceException
	 *             Caso o email passado como parametro seja null ou não exista
	 *             como dado persistente
	 */
	public void deletar(Email email) throws PersistenceException {
		if (email == null
				|| !(new File(CAMINHO + email + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException("O email nao é válido");
		File file = new File(CAMINHO + email + TIPO_DE_ARQUIVO);
		file.delete();
	}

	/**
	 * Recupera todos os emails ({@link Email}) como forma de {@link List}
	 * 
	 * @return Uma {@link List} contento todos os emails ({@link Email})
	 *         persistentes
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public List<Email> recuperaEmails() throws FileNotFoundException {
		List<Email> emails = new ArrayList<Email>();
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.toString().endsWith(TIPO_DE_ARQUIVO)) {
				Email email = (Email) xstream.fromXML(new FileInputStream(
						arquivo));
				emails.add(email);
			}
		}
		return emails;
	}

	/**
	 * Limpa todos os arquivos contendo os emails {@link Email}
	 */
	public void limparEmails() {
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.toString().endsWith(TIPO_DE_ARQUIVO))
				arquivo.delete();
		}
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
