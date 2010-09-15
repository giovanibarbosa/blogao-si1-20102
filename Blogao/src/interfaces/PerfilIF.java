package interfaces;

/**
 * @author Ana Clara Lacerda - anacls@lcc.ufcg.edu.br
 *
 */
public interface PerfilIF {
	
	// Dados obrigatorios:
	public String getLogin();
	public String getSenha();
	public String getEmail();
	// Dados nao obrigatorios:
	public String getNomeDeExibicao();
	public String getEndereco();
	public String getInteresses();
	public String getDataDeNascimento();
	public String getQuemSouEu();
	public String getFilmesFavoritos();
	public String getMusicasFavoritas();
	public String getLivrosFavoritos();
//   	* Login;
//		* E-mail
//		Obs.: O campo sexo obrigatoriamente deve assumir
//		um dos seguintes valores: 'Masculino', 'Feminino' ou 'Não informado'
	
	

}
