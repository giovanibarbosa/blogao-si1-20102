package interfaces;

import classes.*;

/**
 * @author Ana Clara Lacerda - anacls@lcc.ufcg.edu.br
 *
 */
public interface PerfilIF {
	
	// Dados obrigatorios:
	public Login getLogin();
	public Senha getSenha();
	public Email getEmail();
	// Dados nao obrigatorios:
	public String getNomeDeExibicao();
	public String getEndereco();
	public Texto getInteresses();
	public String getDataDeNascimento();
	public Texto getQuemSouEu();
	public Texto getFilmesFavoritos();
	public Texto getMusicasFavoritas();
	public Texto getLivrosFavoritos();
//   	* Login;
//		* E-mail
//		Obs.: O campo sexo obrigatoriamente deve assumir
//		um dos seguintes valores: 'Masculino', 'Feminino' ou 'Não informado'
	
	

}
