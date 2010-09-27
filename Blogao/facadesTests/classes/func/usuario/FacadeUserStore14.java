package classes.func.usuario;

import classes.gerenciadores.GerenciadorDeBlogs;
import classes.gerenciadores.GerenciadorDeComentarios;
import classes.gerenciadores.GerenciadorDeDados;
import classes.gerenciadores.GerenciadorDePerfis;
import classes.gerenciadores.GerenciadorDePosts;
import classes.gerenciadores.GerenciadorDeSessoes;

public class FacadeUserStore14 {
	private GerenciadorDeSessoes gerenteSessoes;
	private GerenciadorDeComentarios gerenteComentarios;
	private GerenciadorDePerfis gerentePerfis;
	private GerenciadorDeBlogs gerenteBlogs;
	private GerenciadorDePosts gerentePosts;
	private GerenciadorDeDados gerenteDados = new GerenciadorDeDados();

	public FacadeUserStore14() {
		gerenteSessoes = new GerenciadorDeSessoes();
		gerenteComentarios = new GerenciadorDeComentarios();
		gerentePerfis = new GerenciadorDePerfis();
		gerentePosts = new GerenciadorDePosts();
	}
	
	//Limpa quaisquer dados pre-existentes
	public void cleanPersistence() {
		gerenteDados.cleanPersistence();
	}

	#Cria tres usuário
	createProfile login=sicrano senha=senhona nome_exibicao= email=sicrano@gmail.com sexo=Masculino dataNasc=01/01/1980 endereco="Rua das cocadas" interesses="ping pong, paraquedismo" quem_sou_eu="E... boa pergunta!" filmes="e o vento levou" musicas=MPB livros="poeira em alto mar"
	createProfile login=mariasilva senha=qwe2 nome_exibicao="Maria Silva" email=maria@gmail.com sexo=Feminino dataNasc=01/01/1980 endereco="Rua das cocadas" interesses="assasinatos em série" quem_sou_eu="E... boa pergunta!" filmes="e o vento levou" musicas=MPB livros="poeira em alto mar"
	createProfile login=zefina senha=123456 nome_exibicao= email=zef@gmail.com sexo="Não informado" dataNasc=01/01/1980 endereco= interesses="seriados" quem_sou_eu= filmes= musicas= livros=

	sessionId1=logon login=sicrano senha=senhona
	sessionId2=logon login=mariasilva senha=qwe2
	sessionId3=logon login=zefina senha=123456


	blogId1=createBlog sessionId=${sessionId1} titulo="Meu primeiro blog" descricao="Quidquid latine dictum sit, altum viditur"

	blogId2=createSubBlog sessionId=${sessionId1} blogPai=${blogId1} titulo="Minha seção de Java" descricao=

	blogId3=createSubBlog sessionId=${sessionId1} blogPai=${blogId2} titulo="Minha seção de j2me" descricao=

	# sub blogs não entram na contabilização de blogs de usuários, ou seja, um subblog é encarado como uma seção de um blog
	expect 1 getNumberOfBlogsByLogin login=sicrano
	expect 1 getNumberOfBlogsBySessionId login=${sessionId1}

	#Considera apenas um nivel de subblogs.A flag passada indica se deve ser feita uma busca em subniveis
	expect 1 getNumberOfSubBlogs blogId=${blogId1}
	expect 1 getNumberOfSubBlogs blogId=${blogId2}
	expect 0 getNumberOfSubBlogs blogId=${blogId3}


	expect 2 getNumberOfAllSubBlogs blogId=${blogId1} 
	expect 1 getNumberOfAllSubBlogs blogId=${blogId2} 
	expect 0 getNumberOfAllSubBlogs blogId=${blogId3} 


	# Considera-se apenas um nível
	expect ${blogId2} getSubBlog blogId=${blogId1} index=0


	postId1=createPost sessionId=${sessionId1} blogId=${blogId2} titulo="Em java temos..." texto=

	postId2=createPost sessionId=${sessionId1} blogId=${blogId1} titulo="Meu segundo post" texto=


	#Retorna o numero de posts apenas do blog pai. 
	expect 1 getNumberOfPosts blogId=${blogId1}

	#Retorna o numero total de posts de um blog, Considerando todos os niveis

	expect 2 getNumberOfAllPosts blogId=${blogId1}

	idComentario1=addComment sessionId=${sessionId2} postId=${postId1} texto="que massa esse seu novo post"
	idComentario2=addSubComment sessionId=${sessionId3} idComentario=${idComentario1} texto="Tb acho ;)"
	idComentario3=addSubComment sessionId=${sessionId3} idComentario=${idComentario2} texto="Discordo :("

	expect ${idComentario2} getSubComment idComentario=${idComentario1} index=0

	#Considera apenas um nivel de Comentario.A flag passada indica se deve ser feita uma busca em subniveis
	expect 1 getNumberOfSubComments idComentario=${idComentario1}
	expect 1 getNumberOfSubComments idComentario=${idComentario2}
	expect 0 getNumberOfSubComments idComentario=${idComentario3}

	#Retorna o numero total de subcomentários de um comentário, Considerando todos os niveis
	expect 2 getNumberOfAllSubComments idComentario=${idComentario1} 
	expect 1 getNumberOfAllSubComments idComentario=${idComentario2} 
	expect 0 getNumberOfAllSubComments idComentario=${idComentario3} 
}
