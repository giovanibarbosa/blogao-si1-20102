package classes.func.usuario;

import interfaces.Logavel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import persistencia.daos.BlogsDAO;
import persistencia.daos.EmailsDAO;
import persistencia.daos.PostsDAO;
import persistencia.daos.UsuariosDAO;

import classes.Login;
import classes.Senha;

/**
 * Facade do Gerenciamento de Secao. Usado para os testes.
 * @author Tiago B.
 */
public class FacadeUserStore2 {
	private Usuario user;
	private UsuariosDAO usuarioDao;
	private List<Usuario> logados = new ArrayList<Usuario>();
	private UsuariosDAO userDAO = UsuariosDAO.getInstance();
	
	
	
	
	//TODO CARREGA TODOS OS DADOS DO BD
	public void loadData(){
		usuarioDao = UsuariosDAO.getInstance();
	}
	
	
	//TODO METODO QUE LOGA O USUARIO
	public void logon(String login, String senha) throws Exception{
		try {
			Usuario us = userDAO.recupera(login);
			if (us.getLogin().getLogin().equals(senha)) {
				if (logados.contains(us)) {
					throw new Exception("Usuário já logado");
				}
				logados.add(us);
				//usuario ira logar e sera criada uma ID de sessao para o mesmo.
			} else {
				throw new Exception("Login ou senha inválido");
			}
		} catch (Exception e) {
			throw new Exception("Login ou senha inválido");
		}
//		Login log = new Login(login);
//		Senha sen = new Senha(senha);
//		Logavel logavel = new LogavelImpl(log, sen);
//		List<Logavel> listaDeLogaveis = logavelDao.recuperaLogaveis();
//		
//		if(listaDeLogaveis != null && listaDeLogaveis.contains(logavel)){
//			user = new Usuario(log, sen);
//			usuarioDao.criar(user);
//		}else{
//			throw new Exception("Login ou senha inválido");
//		}
	}
	
	//TODO METODO QUE VERIFICA SE O USUARIO JA ESTA LOGADO
	public boolean isUserLogged(String login) throws Exception{
		try {
			Usuario us = userDAO.recupera(login);
			return logados.contains(us) ? true : false;		
		} catch (Exception e) {
			e.getMessage();			
		}		
//		if(existeLogavel(log)){
//			for(int i = logados.size(); i >= 0; i-- ){
//				if(logados.get(i).getLogin().equals(log))
//					return true;
//			}
//			return false;
//		}
		
		throw new Exception("Usuário inexistente");
	}
	
	/**
	 * metodo que retorna a existencia de um logavel dado seu login.
	 * @param log
	 * @return
	 * @throws FileNotFoundException
	 */
//	private boolean existeLogavel(Login log) throws FileNotFoundException {
//		List<Logavel> listaDeLogaveis = logavelDao.recuperaLogaveis();
//		
//		for(int i = listaDeLogaveis.size(); i >=0; i--){
//			if(listaDeLogaveis.get(i).getLogin().equals(log))
//				return true;
//		}
//		return false;
//		
//	}


	//VERIFICAR SE ESSE 'ID' VAI SER UM DOUBLE MESMO
	public String getProfileInformationBySessionId(int id, String atributo){
		return null;
	}
	
	//TODO METODO QUE DESLOGA O USUARIO.
	public void logoff(String idSession) throws Exception{
		
	}
	
	
}
