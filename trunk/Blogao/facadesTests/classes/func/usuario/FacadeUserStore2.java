package classes.func.usuario;

import interfaces.Logavel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import persistencia.daos.BlogsDAO;
import persistencia.daos.EmailsDAO;
import persistencia.daos.LogaveisDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;

import classes.Login;
import classes.Senha;

/**
 * Facade do Gerenciamento de Secao. Usado para os testes.
 * @author Tiago
 */
public class FacadeUserStore2 {
	private Usuario user;
	private UsuariosDAO usuarioDao;
	private LogaveisDAO logavelDao;
	private List<Usuario> logados = new ArrayList<Usuario>();
	
	
	
	
	//TODO CARREGA TODOS OS DADOS DO BD
	public void loadData(){
		usuarioDao = UsuariosDAO.getInstance();
		logavelDao = LogaveisDAO.getInstance();
	}
	
	
	//TODO METODO QUE LOGA O USUARIO
	public void logon(String login, String senha) throws Exception{
		Login log = new Login(login);
		Senha sen = new Senha(senha);
		Logavel logavel = new LogavelImpl(log, sen);
		List<Logavel> listaDeLogaveis = logavelDao.recuperaLogaveis();
		
		if(listaDeLogaveis != null && listaDeLogaveis.contains(logavel)){
			user = new Usuario(log, sen);
			usuarioDao.criar(user);
		}else{
			throw new Exception("Login ou senha inválido");
		}
	}
	
	//TODO METODO QUE VERIFICA SE O USUARIO JA ESTA LOGADO
	public boolean isUserLogged(String login) throws Exception{
		Login log = new Login(login);
		
		if(existeLogavel(log)){
			for(int i = logados.size(); i >= 0; i-- ){
				if(logados.get(i).getLogin().equals(log))
					return true;
			}
			return false;
		}
		
		throw new Exception("Usuário inexistente");
	}
	
	/**
	 * metodo que retorna a existencia de um logavel dado seu login.
	 * @param log
	 * @return
	 * @throws FileNotFoundException
	 */
	private boolean existeLogavel(Login log) throws FileNotFoundException {
		List<Logavel> listaDeLogaveis = logavelDao.recuperaLogaveis();
		
		for(int i = listaDeLogaveis.size(); i >=0; i--){
			if(listaDeLogaveis.get(i).getLogin().equals(log))
				return true;
		}
		return false;
		
	}


	//VERIFICAR SE ESSE 'ID' VAI SER UM DOUBLE MESMO
	public void getProfileInformationBySessionId(int id, String login, String senha, String nome_exibicao, String email,
			String sexo, String dataNasc, String endereco, String interesses, String quem_sou_eu,
			String filmes, String musicas, String livros){}
	
	//TODO METODO QUE DESLOGA O USUARIO.
	public void logoff(String login) throws Exception{
		for(int i = logados.size(); i >= 0; i-- ){
			if(logados.get(i).getLogin().equals(new Login(login)));
				logados.remove(i);
		}
	}
	
}
