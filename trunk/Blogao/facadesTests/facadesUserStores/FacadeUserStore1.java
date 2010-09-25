package facadesUserStores;

import classes.GerenciadorDePerfis;
import persistencia.daos.*;

/**
 * Facade do Perfil. Utilizada para chamar os metodos necessarios para os
 * testes.
 * 
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 * @colaborator Rodolfo Marinho - rodolfo (at) grupomarinho (dot) com (dot) br
 * @colaborator Giovani Barbosa - giovanibarbosa (at) gmail (dot) com
 * 
 */
public class FacadeUserStore1 {

	private GerenciadorDePerfis gerentePerfis = new GerenciadorDePerfis();

	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private BlogsDAO blogsDAO = BlogsDAO.getInstance();
	private EmailsDAO emailsDAO = EmailsDAO.getInstance();
	private PostsDAO postsDAO = PostsDAO.getInstance();

	//APAGAR OS DADOS SALVOS
	public void cleanPersistence() {
		userDAO.limparUsuarios();
		blogsDAO.limparBlogs();
		emailsDAO.limparEmails();
		postsDAO.limparPosts();
	}

	//SALVA TODOS OS DADOS NO BD
	public void saveData() {
	}

	//FAZER ESTE METODO
	public String getProfileInformation(String login, String atributo) throws Exception {
		try {
			return gerentePerfis.getProfileInformation(login, atributo);			
		
		} catch (Exception e) {
			throw e;
		}	

	}

	//Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {
		
		try {
			gerentePerfis.createProfile(login, senha, nome_exibicao, email, sexo,
					dataNasc, endereco, interesses, quem_sou_eu, filmes, musicas, livros);
			
		} catch (Exception e) {
			throw e;
		}
	}

}
