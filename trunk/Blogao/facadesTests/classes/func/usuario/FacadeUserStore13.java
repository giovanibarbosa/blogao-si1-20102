package classes.func.usuario;

public class FacadeUserStore13 {
	#Limpa quaisquer dados pre-existentes
	cleanPersistence

	#Cria tres usuário
	createProfile login=sicrano senha=senhona nome_exibicao= email=sicrano@gmail.com sexo=Masculino dataNasc=01/01/1980 endereco="Rua das cocadas" interesses="ping pong, paraquedismo" quem_sou_eu="E... boa pergunta!" filmes="e o vento levou" musicas=MPB livros="poeira em alto mar"
	createProfile login=mariasilva senha=qwe2 nome_exibicao="Maria Silva" email=maria@gmail.com sexo=Feminino dataNasc=01/01/1980 endereco="Rua das cocadas" interesses="assasinatos em série" quem_sou_eu="E... boa pergunta!" filmes="e o vento levou" musicas=MPB livros="poeira em alto mar"
	createProfile login=zefina senha=123456 nome_exibicao= email=zef@gmail.com sexo="Não informado" dataNasc=01/01/1980 endereco= interesses="seriados" quem_sou_eu= filmes= musicas= livros=

	sessionId1=logon login=sicrano senha=senhona
	sessionId2=logon login=mariasilva senha=qwe2
	sessionId3=logon login=zefina senha=123456

	expect 0 getNumberOfAnnouncements sessionId=${sessionId2}

	blogId1=createBlog sessionId=${sessionId1} titulo="Meu primeiro blog" descricao="Quidquid latine dictum sit, altum viditur"

	postId1=createPost sessionId=${sessionId1} blogId=${blogId1} titulo="Meu primeiro post" texto=

	expect 0 getNumberOfAnnouncements sessionId=${sessionId2}

	#Adiciona um notificador para novos posts
	addPostAnnouncements sessionId=${sessionId2} blogId=${blogId1}

	postId2=createPost sessionId=${sessionId1} blogId=${blogId1} titulo="Meu segundo post" texto=

	expect 1 getNumberOfAnnouncements sessionId=${sessionId2}

	announcementId1=getAnnouncement sessionId=${sessionId2} index=0
	expect ${postId2} getPostJustCreated announcementId=${announcementId1}

	expectError "Sessão inválida" getAnnouncement sessionId= index=0
	expectError "Sessão inválida" getAnnouncement sessionId="" index=0
	expectError "Índice inválido" getAnnouncement sessionId=${sessionId2} index=-1


	deleteAnnouncement sessionId=${sessionId2} announcementId=${announcementId1}


	expect 0 getNumberOfAnnouncements sessionId=${sessionId2}


	#Desloga usuarios
	logoff sessionId=${sessionId1}
	logoff sessionId=${sessionId2}
	logoff sessionId=${sessionId3}

	#Salva os dados de forma permanente
	saveData

}
