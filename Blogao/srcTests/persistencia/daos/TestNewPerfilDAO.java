package persistencia.daos;


import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import classes.Email;
import classes.Login;
import classes.Senha;
import classes.func.usuario.NewPerfil;

public class TestNewPerfilDAO {
	private NewPerfil perfil1Real;
	private NewPerfilDAO daoPerfil;

	@Before
	public void setUp() throws Exception {
		Login log;
		Senha sen;
		Email email;
		try {
			log = new Login("tiagohsl");
			sen = new Senha("12345");
			email = new Email("tiagooleite@gmail.com");
			NewPerfil perfil1 = new NewPerfil(log, sen, email);
			daoPerfil = new NewPerfilDAO();
			daoPerfil.criar(perfil1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testAtributoSalvo() throws FileNotFoundException {
		List<NewPerfil> listaPerfis = daoPerfil.recuperaUsuarios();
		System.out.println(listaPerfis.size());
		daoPerfil.limparUsuarios();
		
	}
	
//	@Test
//	public void limpaPerfis() {
//		daoPerfil.limparUsuarios();
//	}

}
