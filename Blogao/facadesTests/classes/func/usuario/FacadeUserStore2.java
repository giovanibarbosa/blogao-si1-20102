package classes.func.usuario;

import interfaces.Logavel;

import java.util.ArrayList;
import java.util.List;

import persistencia.daos.UsuariosDAO;

import classes.Login;
import classes.Senha;

/**
 * Facade do Gerenciamento de Secao. Usado para os testes.
 * @author Tiago
 */
public class FacadeUserStore2 {
	private Logavel user;
	private UsuariosDAO usuarioDao;
	private List<Logavel> logados = new ArrayList<Logavel>();
	
	//TODO CARREGA TODOS OS DADOS DO BD
	public void loadData(){}
	
	//TODO METODO QUE LOGA O USUARIO
	public void logon(String login, String senha) throws Exception{
		Login log = new Login(login);
		Senha sen = new Senha(senha);
		user = (Logavel) new Usuario(log, sen);
	}
	
	//TODO METODO QUE VERIFICA SE O USUARIO JA ESTA LOGADO
	public boolean isUserLogged(String login) throws Exception{
		Login log = new Login(login);
		for(int i = logados.size(); i >= 0; i-- ){
			if(logados.get(i).getLogin().equals(log))
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
