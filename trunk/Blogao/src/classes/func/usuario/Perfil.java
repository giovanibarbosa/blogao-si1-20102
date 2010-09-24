package classes.func.usuario;


import ourExceptions.ArgumentInvalidException;
import interfaces.Logavel;
import classes.Email;
import classes.Login;
import classes.Senha;
import classes.Texto;
import classes.func.Data;
import enuns.Sexo;

public class Perfil {	
	
	private Email email;
	private String nomeDeExibicao = "";
	private String endereco;
	private String interesses;
	private Data dataDeNascimento;
	private Sexo sexo;
	private String quemSouEu;
	private String filmesFavoritos;
	private String musicasFavoritas;
	private String livrosFavoritos;
	
	
	
//	public Perfil(Logavel userLog, Email email) {
//		setEmail(email);
//	}
	
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
	public String getInteresses() {
		return interesses;
	}
	public void setInteresses(String interesses) {
		this.interesses = interesses;
	}
	public Data getDataDeNascimento() {
		return dataDeNascimento;
	}
	
	public void setDataDeNascimento(String dataDeNasc) throws Exception {
		try {
			dataDeNascimento = new Data(dataDeNasc);			
		}catch (Exception e) {
			throw new ArgumentInvalidException("Data inválida");
		}
		
	}
	public String getQuemSouEu() {
		return quemSouEu;
	}
	public void setQuemSouEu(String quemSouEu) {
		this.quemSouEu = quemSouEu;
	}
	public String getFilmesFavoritos() {
		return filmesFavoritos;
	}
	public void setFilmesFavoritos(String filmesFavoritos) {
		this.filmesFavoritos = filmesFavoritos;
	}
	public String getMusicasFavoritas() {
		return musicasFavoritas;
	}
	public void setMusicasFavoritas(String musicasFavoritas) {
		this.musicasFavoritas = musicasFavoritas;
	}
	public String getLivrosFavoritos() {
		return livrosFavoritos;
	}
	public void setLivrosFavoritos(String livrosFavoritos) {
		this.livrosFavoritos = livrosFavoritos;
	}
	
	public void setSexo(String sex) throws ArgumentInvalidException {
		if (!Sexo.verificaSexo(sex)) {
			throw new ArgumentInvalidException("Sexo inválido");
		}
		this.sexo = Sexo.setadorSexo(sex);		
	}
	
	public Sexo getSexo() {
		return sexo;
	}
	
	public String toString(){
		return nomeDeExibicao;
	}
	
}
