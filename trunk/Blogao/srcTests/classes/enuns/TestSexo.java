package classes.enuns;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ourExceptions.SexoInvalidoException;

import classes.Email;
import classes.Login;
import classes.Senha;
import classes.func.usuario.NewPerfil;
import enuns.Sexo;


public class TestSexo {
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
	
	@Test (expected=SexoInvalidoException.class)
	public void testSetSexoPerfil() throws SexoInvalidoException {
		Assert.assertEquals("Não informado", perfil1.getSexo().getSexo());
		perfil1.setSexo("Feminino");
		Assert.assertEquals("Feminino", perfil1.getSexo().getSexo());
		perfil1.setSexo("Masculino");
		Assert.assertEquals("Masculino", perfil1.getSexo().getSexo());
		perfil1.setSexo("Não informado");
		Assert.assertEquals("Não informado", perfil1.getSexo().getSexo());
		perfil1.setSexo("Sexo invalido");
	}
	
	@Test
	public void testTipo() throws SexoInvalidoException {
		Assert.assertEquals(Sexo.Nao_Inf, perfil1.getSexo());
		perfil1.setSexo("Feminino");
		Assert.assertEquals(Sexo.Feminino, perfil1.getSexo());
		perfil1.setSexo("Masculino");
		Assert.assertEquals(Sexo.Masculino, perfil1.getSexo());
	}
	

}
