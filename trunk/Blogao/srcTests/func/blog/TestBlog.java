package func.blog;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ourExceptions.ArgumentInvalidException;

import classes.func.blog.NewBlog;


public class TestBlog {

	private NewBlog blog;
	
	@Before
	public void setUp() throws Exception {
		blog = new NewBlog("Meu primeiro blog", "Blog dedicado a emos e choroes", "45");
	}
	
	@Test
	public void testConstrutor() {
		Assert.assertEquals("Meu primeiro blog", blog.getTitulo());
		Assert.assertEquals("Blog dedicado a emos e choroes", blog.getDescricao());
		Assert.assertEquals("45", blog.getIdSessao());
		Assert.assertNotNull(blog.getId());
	}
	
	@Test (expected=ArgumentInvalidException.class)
	public void testDescricao() throws ArgumentInvalidException {
		blog.setDescricao("blog unicamente para emos");
		Assert.assertEquals("blog unicamente para emos", blog.getDescricao());
		blog.setDescricao("");	
	}
	
	@Test (expected=ArgumentInvalidException.class)
	public void testTitulo() throws ArgumentInvalidException {
		blog.setTitulo("O blogao do caldeirao");
		Assert.assertEquals("O blogao do caldeirao", blog.getTitulo());
		blog.setTitulo("");		
	}
	
	
	
	

}
