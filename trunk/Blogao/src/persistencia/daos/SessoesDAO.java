package persistencia.daos;

import interfaces.Constantes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.PersistenceException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class SessoesDAO {

	private final static String SEPARADOR = System
			.getProperty("file.separator");
	private final static String CAMINHO = "src" + SEPARADOR + "persistencia"
			+ SEPARADOR + "arquivosXML" + SEPARADOR + "sessoesAbertas" + SEPARADOR;
	private final static String TIPO_DE_ARQUIVO = ".xml";
	private static SessoesDAO instancia;
	private static XStream xstream = new XStream(new DomDriver());

	private SessoesDAO() {

	}

	/**
	 * Recupera uma instancia unica para este objeto {@link SessoesDAO}
	 * 
	 * @return A instancia unica para este objeto {@link SessoesDAO}
	 */
	public static synchronized SessoesDAO getInstance() {
		if (instancia == null)
			instancia = new SessoesDAO();
		return instancia;

	}

	/**
	 * Cria uma sessao aberta no formato de arquivo xml
	 * 
	 * @param sessao
	 *            A sessao a ser criado
	 * @throws PersistenceException
	 *             Caso a sessao passado como parametro seja null ou ja exista
	 *             como dado persistente
	 * @throws IOException
	 *             Caso haja um problema ao gerar o arquivo xml
	 */
	public void criar(String sessao) throws PersistenceException, IOException {
		if (new File(CAMINHO + sessao + TIPO_DE_ARQUIVO).exists())
			throw new PersistenceException(Constantes.EMAIL_EXISTENTE);
		File file = new File(CAMINHO + sessao + TIPO_DE_ARQUIVO);
		xstream.toXML(sessao, new FileOutputStream(file));
	}

	/**
	 * Apaga uma sessão no formato de arquivo xml
	 * 
	 * @param sessao
	 *            A sessão a ser apagada
	 * @throws PersistenceException
	 *             Caso a sessao passado como parametro seja null ou não exista
	 *             como dado persistente
	 */
	public void deletar(String sessao) throws PersistenceException {
		if (sessao == null
				|| !(new File(CAMINHO + sessao + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(Constantes.EMAIL_NAO_VALIDO);
		File file = new File(CAMINHO + sessao + TIPO_DE_ARQUIVO);
		file.delete();
	}

	/**
	 * Recupera todas as sessoes como forma de {@link List}
	 * 
	 * @return Uma {@link List} contento todos as sessoes persistentes
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public List<String> recuperaSessoes() throws FileNotFoundException {
		List<String> sessoes = new ArrayList<String>();
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.toString().endsWith(TIPO_DE_ARQUIVO)) {
				String sessao = (String) xstream.fromXML(new FileInputStream(
						arquivo));
				sessoes.add(sessao);
			}
		}
		return sessoes;
	}

	/**
	 * Recupera uma Sessao de um arquivo xml
	 * 
	 * @param sessao
	 *            A sessao a ser recuperado do arquivo xml
	 * @return A sessao recuperada de um arquivo xml
	 * @throws PersistenceException
	 *             Caso a sessao passado como parametro seja null ou não exista
	 *             como dado persistente
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public String recupera(String sessao) throws PersistenceException,
			FileNotFoundException {
		if (sessao == null
				|| !(new File(CAMINHO + sessao + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(Constantes.EMAIL_INEXISTENTE);
		File file = new File(CAMINHO + sessao + TIPO_DE_ARQUIVO);
		return (String) xstream.fromXML(new FileInputStream(file));
	}

	/**
	 * Limpa todos os arquivos contendo as sessoes
	 */
	public void limparSessoes() {
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.toString().endsWith(TIPO_DE_ARQUIVO))
				arquivo.delete();
		}
	}

	/**
	 * Recupera um array dos arquivos contidos no path das sessoes
	 * 
	 * @return O array dos arquivos contidos no path das sessoes
	 */
	private File[] arrayDosArquivos() {
		File file = new File(CAMINHO);
		return file.listFiles();
	}
	
	public List<String> loadData() throws FileNotFoundException {
		return recuperaSessoes();
	}

}
