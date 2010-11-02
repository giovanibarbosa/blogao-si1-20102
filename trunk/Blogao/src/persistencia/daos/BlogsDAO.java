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
import classes.Blog;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XomDriver;

/**
 * Classe DAO que cria, deleta, atualiza e recupera blogs ({@link Blog})
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @colaborator Rodolfo Marinho -  rodolfoams@lcc.ufcg.edu.br
 * 
 */
public class BlogsDAO {
	private final static String SEPARADOR = System
			.getProperty("file.separator");
	private final static String CAMINHO = "src" + SEPARADOR + "persistencia"
			+ SEPARADOR + "arquivosXML" + SEPARADOR + "blogs" + SEPARADOR;
	private final static String TIPO_DE_ARQUIVO = ".xml";
	private static BlogsDAO instancia;
	private static XStream xstream = new XStream(new DomDriver());


	/**
	 * Recupera uma instancia unica para este objeto {@link BlgsDAO}
	 * 
	 * @return A instancia unica para este objeto {@link BlogsDAO}
	 */
	public static synchronized BlogsDAO getInstance() {
		if (instancia == null)
			instancia = new BlogsDAO();
		return instancia;

	}

	/**
	 * Cria um {@link Blog} no formato de arquivo xml
	 * 
	 * @param blog
	 *            O {@link Blog} a ser criado
	 * @throws ArgumentInvalidException
	 *             Caso o blog passado como parametro seja null
	 * @throws IOException
	 *             Caso haja um problema ao gerar o arquivo xml
	 */
	public void criar(Blog blog) throws PersistenceException, IOException {
		if (blog == null)
			throw new PersistenceException(Constantes.BLOG_NAO_CRIADO);
		File file = new File(CAMINHO + blog + TIPO_DE_ARQUIVO);
		xstream.toXML(blog, new FileOutputStream(file));
	}

	/**
	 * Apaga um {@link Blog} no formato de arquivo xml
	 * 
	 * @param blog
	 *            O {@link Blog} a ser apagado
	 * @throws ArgumentInvalidException
	 *             Caso o blog passado como parametro seja null ou não exista
	 *             como dado persistente
	 */
	public void deletar(Blog blog) throws PersistenceException {
		if (blog == null
				|| !(new File(CAMINHO + blog + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(Constantes.BLOG_NAO_REMOVIDO);
		File file = new File(CAMINHO + blog + TIPO_DE_ARQUIVO);
		file.delete();
	}

	/**
	 * Recupera todos os blogs ({@link Blog}) como forma de {@link List}
	 * 
	 * @return Uma {@link List} contento todos os blogs ({@link Blog})
	 *         persistentes
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public List<Blog> recuperaBlogs() throws FileNotFoundException {
		List<Blog> blogs = new ArrayList<Blog>();
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.toString().endsWith(TIPO_DE_ARQUIVO)) {
				Blog blog = (Blog) xstream
						.fromXML(new FileInputStream(arquivo));
				blogs.add(blog);
			}
		}
		return blogs;
	}

	/**
	 * Recupera um {@link Blog} de um arquivo xml
	 * 
	 * @param blog
	 *            O {@link Blog} a ser recuperado do arquivo xml
	 * @return O {@link Blog} recuperado de um arquivo xml
	 * @throws PersistenceException
	 *             Caso o blog passado como parametro seja null ou não exista
	 *             como dado persistente
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public Blog recupera(Blog blog) throws PersistenceException,
			FileNotFoundException {
		if (blog == null
				|| !(new File(CAMINHO + blog + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(Constantes.BLOG_INEXISTENTE);
		File file = new File(CAMINHO + blog + TIPO_DE_ARQUIVO);
		return (Blog) xstream.fromXML(new FileInputStream(file));
	}

	/**
	 * Recupera um {@link Blog} de um arquivo xml
	 * 
	 * @param blog
	 *            O {@link Blog} a ser recuperado do arquivo xml, segundo seu id
	 * @return O {@link Blog} recuperado de um arquivo xml
	 * @throws PersistenceException
	 *             Caso o blog passado como parametro seja null ou nÃ£o exista
	 *             como dado persistente
	 * @throws FileNotFoundException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public Blog recupera(String id) throws PersistenceException,
			FileNotFoundException {
		if (id == null || !(new File(CAMINHO + id + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(Constantes.BLOG_INVALIDO);

		File file = new File(CAMINHO + id + TIPO_DE_ARQUIVO);
		return (Blog) xstream.fromXML(new FileInputStream(file));
	}

	/**
	 * Atualiza as informacoes do {@link Blog} passado como parametro a partir
	 * de um {@link Blog} atualizado
	 * 
	 * @param blog
	 *            O {@link Blog} a ser atualizado
	 * @throws ArgumentInvalidException
	 *             Caso o {@link Blog} a ser atualizado ou o {@link Blog}
	 *             atualizado sejam null, ou o {@link Blog} a ser atualizado nao
	 *             exista de forma persistente
	 * @throws IOException
	 *             Caso haja algum problema com arquivos ({@link File})
	 */
	public void atualizar(Blog blog) throws PersistenceException, IOException {
		if (blog == null
				|| !(new File(CAMINHO + blog + TIPO_DE_ARQUIVO).exists()))
			throw new PersistenceException(
					Constantes.BLOG_NAO_PODE_SER_ATUALIZADO);
		File file = new File(CAMINHO + blog + TIPO_DE_ARQUIVO);
		xstream.toXML(blog, new FileOutputStream(file));
	}

	/**
	 * Limpa todos os arquivos contendo os {@link Blog}
	 */
	public void limparBlogs() {
		for (File arquivo : arrayDosArquivos()) {
			if (arquivo.toString().endsWith(TIPO_DE_ARQUIVO))
				arquivo.delete();
		}
	}
	
	/**
	 * Metodo acessador que recupera todos os Blogs do BD
	 * @return List<{@link Blog}}>
	 * @throws FileNotFoundException
	 */
	public List<Blog> loadData() throws FileNotFoundException {
		return recuperaBlogs();	
	}
	
	/**
	 * Recupera um array dos arquivos contidos no path dos blogs
	 * @return O array dos arquivos contidos no path dos blogs
	 */
	private File[] arrayDosArquivos() {
		File file = new File(CAMINHO);
		return file.listFiles();
	}

}
