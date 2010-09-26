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
import classes.Post;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PostsDAO {
	private final static String SEPARADOR = System
			.getProperty("file.separator");
	private final static String CAMINHO = "src" + SEPARADOR + "persistencia"
			+ SEPARADOR + "arquivosXML" + SEPARADOR + "posts" + SEPARADOR;
	private final static String TIPO_DE_ARQUIVO = ".xml";
	private static PostsDAO instancia;
	private static XStream xstream = new XStream(new DomDriver());

	private PostsDAO() {

	}

	/**
	 * Recupera uma instancia unica para este objeto {@link PostsDAO}
	 * 
	 * @return A instancia unica para este objeto {@link PostsDAO}
	 */
	public static synchronized PostsDAO getInstance() {
		if (instancia == null)
			instancia = new PostsDAO();
		return instancia;

	}

	/**
	 * Cria um {@link Post} no formato de arquivo xml
	 * 
	 * @param post
	 *            O {@link Post} a ser criado
	 * @throws ArgumentInvalidException
	 *             Caso o post passado como parametro seja null
	 * @throws IOException
	 *             Caso haja um problema ao gerar o arquivo xml
	 */
	public void criar(Post post) throws PersistenceException, IOException {
		if (post == null)
			throw new PersistenceException(Constantes.POST_NAO_PODE_SER_CRIADO);
		post.setId(geraId());
		File file = new File(CAMINHO + post + TIPO_DE_ARQUIVO);
		xstream.toXML(post, new FileOutputStream(file));
	}

	/**
	 * Apaga um {@link Post} no formato de arquivo xml
	 * 
	 * @param post
	 *            O {@link Post} a ser apagado
	 * @throws ArgumentInvalidException
	 *             Caso o post passado como parametro seja null ou não exista
	 *             como dado persistente
	 */
	public void deletar(Post post) throws PersistenceException {
		if (post == null
				|| !(new File(CAMINHO + post + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(Constantes.POST_NAO_PODE_SER_REMOVIDO);
		File file = new File(CAMINHO + post + TIPO_DE_ARQUIVO);
		file.delete();
	}

	/**
	 * Recupera todos os posts ({@link Post}) como forma de {@link List}
	 * 
	 * @return Uma {@link List} contento todos os posts ({@link Post})
	 *         persistentes
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public List<Post> recuperaPosts() throws FileNotFoundException {
		List<Post> posts = new ArrayList<Post>();
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.toString().endsWith(TIPO_DE_ARQUIVO)) {
				Post post = (Post) xstream
						.fromXML(new FileInputStream(arquivo));
				posts.add(post);
			}
		}
		return posts;
	}

	/**
	 * Atualiza as informacoes do {@link Post} passado como parametro a partir
	 * de um {@link Post} atualizado
	 * 
	 * @param post
	 *            O {@link Post} a ser atualizado
	 * @param postAtualizado
	 *            O {@link Post} atualizado
	 * @throws ArgumentInvalidException
	 *             Caso o {@link Post} a ser atualizado ou o {@link Post}
	 *             atualizado sejam null, ou o {@link Post} a ser atualizado nao
	 *             exista de forma persistente
	 * @throws IOException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public void atualizar(Post post)
			throws PersistenceException, IOException {
		if (post == null
				|| !(new File(CAMINHO + post + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(
					Constantes.POST_NAO_PODE_SER_ATUALIZADO);
		File file = new File(CAMINHO + post + TIPO_DE_ARQUIVO);
		xstream.toXML(post, new FileOutputStream(file));
	}

	/**
	 * Recupera um {@link Post} de um arquivo xml
	 * 
	 * @param post
	 *            O {@link Post} a ser recuperado do arquivo xml
	 * @return O {@link Post} recuperado de um arquivo xml
	 * @throws PersistenceException
	 *             Caso o post passado como parametro seja null ou não exista
	 *             como dado persistente
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public Post recupera(String postId) throws PersistenceException,
			FileNotFoundException {
		if (postId == null
				|| !(new File(CAMINHO + postId + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(Constantes.POST_INEXISTENTE);
		File file = new File(CAMINHO + postId + TIPO_DE_ARQUIVO);
		return (Post) xstream.fromXML(new FileInputStream(file));
	}

	/**
	 * Limpa todos os arquivos contendo os posts {@link Post}
	 */
	public void limparPosts() {
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.toString().endsWith(TIPO_DE_ARQUIVO))
				arquivo.delete();
		}
	}

	/**
	 * Recupera um array dos arquivos contidos no path dos posts
	 * 
	 * @return O array dos arquivos contidos no path dos posts
	 */
	private File[] arrayDosArquivos() {
		File file = new File(CAMINHO);
		return file.listFiles();
	}

	/**
	 * Gera um id para um {@link Post}
	 * 
	 * @return O id a ser incrementado para um {@link Post}
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	private String geraId() throws FileNotFoundException {
		List<Post> lista = recuperaPosts();
		int index = 0;
		for (int i = 1; i < lista.size(); i++) {
			if (Integer.parseInt(lista.get(i).getId()) > Integer.parseInt(lista.get(index).getId()))
				index = i;
		}
		return lista.isEmpty() ? "1" : (Integer.parseInt(lista.get(index).getId()) + 1)+ "";
	}
	
	public List<Post> loadData() throws FileNotFoundException {
		return recuperaPosts();
	}
}
