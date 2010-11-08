package persistencia.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ourExceptions.PersistenceException;

import classes.Sessao;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import enuns.Constantes;

/**
 * Classe DAO que cria, deleta, atualiza e recupera sessoes ({@link Sessao})
 * no BD.
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @colaborator Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 */
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
	 * @param {@link Sessao}
	 *            A sessao a ser criado
	 * @throws PersistenceException
	 *             Caso a sessao passado como parametro seja null ou ja exista
	 *             como dado persistente
	 * @throws IOException
	 *             Caso haja um problema ao gerar o arquivo xml
	 */
	public void criar(Sessao sessao) throws PersistenceException, IOException {
		if (sessao == null)
			throw new PersistenceException(Constantes.SESSAO_INVALIDA.getName());
		File file = new File(CAMINHO + sessao + TIPO_DE_ARQUIVO);
		file.getParentFile().mkdirs();
		xstream.toXML(sessao, new FileOutputStream(file));
	}

	/**
	 * Apaga uma sessao no formato de arquivo xml
	 * @param {@link Sessao}
	 *            A sessão a ser apagada
	 * @throws PersistenceException
	 *             Caso a sessao passado como parametro seja null ou não exista
	 *             como dado persistente
	 */
	public void deletar(Sessao sessao) throws PersistenceException {
		if (sessao == null
				|| !(new File(CAMINHO + sessao + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(Constantes.EMAIL_NAO_VALIDO.getName());
		File file = new File(CAMINHO + sessao + TIPO_DE_ARQUIVO);
		file.delete();
	}

	/**
	 * Recupera todas as sessoes({@link Sessao}) como forma de {@link List}
	 * 
	 * @return Uma {@link List} contento todos as sessoes({@link Sessao})
	 * 							 persistentes
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public List<Sessao> recuperaSessoes() throws FileNotFoundException {
		List<Sessao> sessoes = new ArrayList<Sessao>();
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.toString().endsWith(TIPO_DE_ARQUIVO)) {
				Sessao sessao = (Sessao) xstream.fromXML(new FileInputStream(
						arquivo));
				sessoes.add(sessao);
			}
		}
		return sessoes;
	}

	/**
	 * Recupera uma Sessao de um arquivo xml
	 * 
	 * @param {@link Sessao}
	 *            A sessao a ser recuperado do arquivo xml
	 * @return A sessao recuperada de um arquivo xml
	 * @throws PersistenceException
	 *             Caso a sessao passado como parametro seja null ou não exista
	 *             como dado persistente
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public Sessao recupera(Sessao sessao) throws PersistenceException,
			FileNotFoundException {
		if (sessao == null
				|| !(new File(CAMINHO + sessao + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(Constantes.EMAIL_INEXISTENTE.getName());
		File file = new File(CAMINHO + sessao + TIPO_DE_ARQUIVO);
		return (Sessao) xstream.fromXML(new FileInputStream(file));
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
		file.mkdirs();
		return file.listFiles();
	}

}
