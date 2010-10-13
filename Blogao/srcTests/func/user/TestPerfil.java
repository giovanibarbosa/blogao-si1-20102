package func.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import classes.Email;
import classes.Login;
import classes.Senha;
import classes.func.usuario.NewPerfil;


public class TestPerfil {
	private NewPerfil perfil1;
	
	
	@Before
	public void setUp() {
		Login log;
		Senha sen;
		Email email;
		try {
			log = new Login("tiagohsl");
			sen = new Senha("12345");
			email = new Email("tiagooleite@gmail.com");
			perfil1 = new NewPerfil(log, sen, email);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testeConstrutor() {
		Assert.assertNotNull("Objeto perfil nao deveria ser nulo", perfil1);
		Assert.assertNotNull("Login do perfil nao deveria ser nulo", perfil1.getLogin());
		Assert.assertNotNull("Senha do perfil nao deveria ser nula", perfil1.getSenha());
		Assert.assertNotNull("Email do perfil nao deveria ser nulo", perfil1.getEmail());
	}
	
	

}
