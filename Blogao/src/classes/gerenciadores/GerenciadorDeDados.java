package classes.gerenciadores;

import interfaces.Gerenciador;
import persistencia.daos.*;
public class GerenciadorDeDados {

	public void loadData(Gerenciador[] gerenciadores){
		for (Gerenciador gerenciador : gerenciadores) {
			gerenciador.loadData();
		}
	}
	public void saveData(Gerenciador[] gerenciadores){
		for (Gerenciador gerenciador : gerenciadores) {
			gerenciador.saveData();
		}
	}
	public void cleanPersistence(){
		BlogsDAO.getInstance().limparBlogs();
		ComentariosDAO.getInstance().limparComentarios();
		EmailsDAO.getInstance().limparEmails();
		PostsDAO.getInstance().limparPosts();
		SessoesDAO.getInstance().limparSessoes();
		UsuariosDAO.getInstance().limparUsuarios();
	}
}
