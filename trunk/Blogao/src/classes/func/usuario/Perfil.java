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
	private String nomeDeExibicao;
	private String endereco;
	private Texto interesses;
	private Data dataDeNascimento;
	private Sexo sexo;
	private Texto quemSouEu;
	private Texto filmesFavoritos;
	private Texto musicasFavoritas;
	private Texto livrosFavoritos;
	
	
	
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
	public Texto getInteresses() {
		return interesses;
	}
	public void setInteresses(Texto interesses) {
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
	
	public void setSexo(String sex) throws ArgumentInvalidException {
		if (!Sexo.verificaSexo(sex)) {
			throw new ArgumentInvalidException("Sexo inválido");
		}
		this.sexo = Sexo.setadorSexo(sex);		
	}
	
	public Sexo getSexo() {
		return sexo;
	}
	
	
}
