package func.user;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.DataInvalidaException;
import ourExceptions.SexoInvalidoException;


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
	
	@Test (expected=SexoInvalidoException.class)
	public void testeSexo() throws SexoInvalidoException {
		Assert.assertEquals("Não informado", perfil1.getSexo().getSexo());
		perfil1.setSexo("Masculino");
		Assert.assertEquals("Masculino", perfil1.getSexo().getSexo());
		perfil1.setSexo("Feminino");
		Assert.assertEquals("Feminino", perfil1.getSexo().getSexo());
		perfil1.setSexo("sexo invalido");//lanca a excecao		
	}
	
	@Test (expected=ArgumentInvalidException.class)
	public void testeEndereco() throws ArgumentInvalidException {
		Assert.assertEquals("", perfil1.getEndereco());
		perfil1.setEndereco("Rua do rio");
		Assert.assertEquals("Rua do rio", perfil1.getEndereco());
		perfil1.setEndereco(null);		
	}
	
	@Test (expected=DataInvalidaException.class)
	public void testeDataNascimento() throws Exception {
		Assert.assertEquals(null, perfil1.getDataDeNascimento());
		perfil1.setDataDeNascimento("27/12/1987");
		Assert.assertEquals("27/12/1987", perfil1.getDataDeNascimento().toString());
		perfil1.setDataDeNascimento("31/13/3322");		
	}
	
	@Test
	public void testeGetAtributo() throws ArgumentInvalidException, DataInvalidaException { 
		Assert.assertEquals("", perfil1.getAtributo("endereco"));
		Assert.assertEquals("", perfil1.getAtributo("nome_exibicao"));
		Assert.assertEquals("", perfil1.getAtributo("filmes"));
		Assert.assertEquals("", perfil1.getAtributo("musicas"));
		Assert.assertEquals("", perfil1.getAtributo("livros"));
		Assert.assertEquals("", perfil1.getAtributo("quem_sou_eu"));
		Assert.assertEquals("", perfil1.getAtributo("interesses"));
		Assert.assertEquals(perfil1.getSexo().getSexo(), perfil1.getAtributo("sexo"));
		Assert.assertEquals(null, perfil1.getAtributo("dataNasc"));		
	}
	
	@Test (expected=ArgumentInvalidException.class)
	public void testeSetAtributo() throws Exception {
		perfil1.setAtributo("endereco", "rua do rio");
		Assert.assertEquals("rua do rio", perfil1.getEndereco());
		perfil1.setAtributo("nome_exibicao", "tiago leite");
		Assert.assertEquals("tiago leite", perfil1.getNomeDeExibicao());
		perfil1.setAtributo("filmes", "21g, lost");
		Assert.assertEquals("21g, lost", perfil1.getFilmesFavoritos());
		perfil1.setAtributo("musicas", "varias");
		Assert.assertEquals("varias", perfil1.getMusicasFavoritas());
		perfil1.setAtributo("quem_sou_eu", "alto, gordo");
		Assert.assertEquals("alto, gordo", perfil1.getQuemSouEu());
		perfil1.setAtributo("interesses", "tecnologia, outras coisas");
		Assert.assertEquals("tecnologia, outras coisas", perfil1.getInteresses());
		perfil1.setAtributo("sexo", "Masculino");
		Assert.assertEquals("Masculino", perfil1.getSexo().getSexo());
		perfil1.setAtributo("dataNasc", "27/12/1987");
		Assert.assertEquals("27/12/1987", perfil1.getDataDeNascimento().toString());	
		perfil1.setAtributo("nao tem", "vai lancar excecao");
	}
}
