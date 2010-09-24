package classes.func.usuario;

import classes.Email;
import classes.Login;
import classes.Senha;
import interfaces.Logavel;
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

	private Perfil perfil1;
	private Usuario user1;

	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	private BlogsDAO blogsDAO = BlogsDAO.getInstance();
	private EmailsDAO emailsDAO = EmailsDAO.getInstance();
	private PostsDAO postsDAO = PostsDAO.getInstance();

	// TODO APAGAR OS DADOS SALVOS
	public void cleanPersistence() {
		userDAO.limparUsuarios();
		blogsDAO.limparBlogs();
		emailsDAO.limparEmails();
		postsDAO.limparPosts();
	}

	// TODO SALVA TODOS OS DADOS NO BD
	public void saveData() {
	}

	// TODO FAZER ESTE METODO
	// Precisa-se do BD para tal!
	public void getProfileInformation(String login, String atributo) {

	}

	// TODO Armazenar no BD.
	public void createProfile(String login, String senha, String nome_exibicao,
			String email, String sexo, String dataNasc, String endereco,
			String interesses, String quem_sou_eu, String filmes,
			String musicas, String livros) throws Exception {

		Login log = new Login(login);
		Senha sen = new Senha(senha);
		Email mail = new Email(email);

		perfil1 = new Perfil();
		perfil1.setNomeDeExibicao(nome_exibicao);
		perfil1.setEmail(mail);
		perfil1.setSexo(sexo);
		perfil1.setDataDeNascimento(dataNasc);
		perfil1.setEndereco(endereco);
		perfil1.setInteresses(interesses);
		perfil1.setQuemSouEu(quem_sou_eu);
		perfil1.setFilmesFavoritos(filmes);
		perfil1.setMusicasFavoritas(musicas);
		perfil1.setLivrosFavoritos(livros);

		user1 = new Usuario(log, sen, perfil1);

		emailsDAO.criar(mail);
		userDAO.criar(user1);
	}

}
