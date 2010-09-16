package classes.func.usuario;

import interfaces.Logavel;
import classes.Email;
import classes.Login;
import classes.Senha;
import classes.Texto;

public class Perfil {

	public Logavel logavel;
	public Email email;
	public String nomeDeExibicao;
	public String endereco;
	public Texto interesses;
	public String dataDeNascimento;
	public Texto quemSouEu;
	public Texto filmesFavoritos;
	public Texto musicasFavoritas;
	public Texto livrosFavoritos;
	
	public Login getLogin() {
		return logavel.getLogin();
	}
	public void setLogin(Login login) {
		logavel.setLogin(login);
	}
	public Senha getSenha() {
		return logavel.getSenha();
	}
	public void setSenha(Senha senha) {
		logavel.setSenha(senha);
	}
	public Email getEmail() {
		return email;
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	public String getNomeDeExibicao() {
		return nomeDeExibicao;
	}
	public void setNomeDeExibicao(String nomeDeExibicao) {
		this.nomeDeExibicao = nomeDeExibicao;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Texto getInteresses() {
		return interesses;
	}
	public void setInteresses(Texto interesses) {
		this.interesses = interesses;
	}
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public Texto getQuemSouEu() {
		return quemSouEu;
	}
	public void setQuemSouEu(Texto quemSouEu) {
		this.quemSouEu = quemSouEu;
	}
	public Texto getFilmesFavoritos() {
		return filmesFavoritos;
	}
	public void setFilmesFavoritos(Texto filmesFavoritos) {
		this.filmesFavoritos = filmesFavoritos;
	}
	public Texto getMusicasFavoritas() {
		return musicasFavoritas;
	}
	public void setMusicasFavoritas(Texto musicasFavoritas) {
		this.musicasFavoritas = musicasFavoritas;
	}
	public Texto getLivrosFavoritos() {
		return livrosFavoritos;
	}
	public void setLivrosFavoritos(Texto livrosFavoritos) {
		this.livrosFavoritos = livrosFavoritos;
	}
	
	
}
