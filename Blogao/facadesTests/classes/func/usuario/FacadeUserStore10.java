package classes.func.usuario;

public class FacadeUserStore10 {
	
	public void loadData() {
		
	}


	//Efetua login dos usuários
	public String logon(String login, String senha) {
		
	}

	public String getBlogByLogin login=sicrano index=0

	postId1=createPost sessionId=${sessionId1} blogId=${blogId1} titulo="Meu primeiro post" texto="Sobre esse primeiro post, iremos falar sobre..."
	postId2=createPost sessionId=${sessionId1} blogId=${blogId1} titulo="Meu segundo post" texto=

	deleteBlog sessionId=${sessionId1} blodId=${blogId1}
	expectError "Blog inválido" deleteBlog sessionId=${sessionId1} blodId=${blogId1}

	#Validação: os posts devem ser deletados
	expectError "Post inválido" getPostInformation id=${postId1} atributo=titulo

	#Desloga usuarios
	logoff sessionId=${sessionId1}

	#Salva os dados de forma permanente
	saveData	
}
