package func.blog;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.BlogInvalidException;

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
	
	@Test (expected=ArgumentInvalidException.class)
	public void testAddSubBlog() throws ArgumentInvalidException, BlogInvalidException {
		NewBlog subBlog = new NewBlog("SubBlog de saude", "Para as pessoas que gostam de saude", "45");
		blog.addSubNewBlog(subBlog);
		Assert.assertEquals(1, blog.getTotalSubNewBlogs());
		
		NewBlog subBlog2 = new NewBlog("SubBlog de luta", "Para as pessoas que gostam de lutas", "45");
		blog.addSubNewBlog(subBlog2);
		Assert.assertEquals(2, blog.getTotalSubNewBlogs());
		
		blog.addSubNewBlog(subBlog2);
		Assert.assertEquals(2, blog.getTotalSubNewBlogs());
	}
	
	@Test (expected=ArgumentInvalidException.class)
	public void testRemoveSubBlog() throws ArgumentInvalidException, BlogInvalidException {
		NewBlog subBlog = new NewBlog("SubBlog de saude", "Para as pessoas que gostam de saude", "45");
		blog.addSubNewBlog(subBlog);
		Assert.assertEquals(1, blog.getTotalSubNewBlogs());
		
		NewBlog subBlog2 = new NewBlog("SubBlog de luta", "Para as pessoas que gostam de lutas", "45");
		blog.addSubNewBlog(subBlog2);
		Assert.assertEquals(2, blog.getTotalSubNewBlogs());
		
		blog.removeSubNewBlog(subBlog);
		Assert.assertEquals(1, blog.getTotalSubNewBlogs());
		blog.removeSubNewBlog(subBlog2);
		Assert.assertEquals(0, blog.getTotalSubNewBlogs());
		
		NewBlog subBlog3 = new NewBlog("SubBlog de comida", "Para as pessoas que gostam de comida", "45");
		blog.removeSubNewBlog(subBlog3);
	}
	
	

}
