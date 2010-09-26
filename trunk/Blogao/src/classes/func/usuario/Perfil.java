package classes.func.usuario;

import interfaces.Constantes;

import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import persistencia.daos.EmailsDAO;
import classes.Email;
import classes.func.Data;
import enuns.Sexo;

public class Perfil {

	private Email email;
	private String nomeDeExibicao;
	private String endereco;
	private String interesses;
	private Data dataDeNascimento;
	private Sexo sexo;
	private String quemSouEu;
	private String filmesFavoritos;
	private String musicasFavoritas;
	private String livrosFavoritos;
	private EmailsDAO mailDAO = EmailsDAO.getInstance();

	private static final int EMAIL = 96619420;
	private static final int NOME = 513276986;
	private static final int ENDERECO = 1731028937;
	private static final int INTERESSES = -1598911081;
	private static final int DATA = 1788869133;
	private static final int SEXO = 3526857;
	private static final int QUEM = -2073846039;
	private static final int FILMES = -1274498766;
	private static final int MUSICAS = 1412695319;
	private static final int LIVROS = -1102420835;

	// public Perfil(Logavel userLog, Email email) {
	// setEmail(email);
	// }

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

	public void setDataDeNascimento(String dataDeNasc)
			throws ArgumentInvalidException {
		try {
			dataDeNascimento = new Data(dataDeNasc);
		} catch (Exception e) {
			throw new ArgumentInvalidException(Constantes.DATA_INVALIDA);
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
			throw new ArgumentInvalidException(Constantes.SEXO_INVALIDO);
		}
		this.sexo = Sexo.setadorSexo(sex);
	}

	public Sexo getSexo() {
		return sexo;
	}

	public String toString() {
		return nomeDeExibicao;
	}

	public String getAtributo(String atributo) throws ArgumentInvalidException {
		if (atributo == null)
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO);
		int codigoAtributo = atributo.hashCode();

		switch (codigoAtributo) {
		case (EMAIL):
			return this.email.toString();
		case (NOME):
			return this.nomeDeExibicao;
		case (ENDERECO):
			return this.endereco;
		case (INTERESSES):
			return this.interesses;
		case (DATA):
			return Data.calendarToString(dataDeNascimento.getData());
		case (SEXO):
			return sexo.getSexo();
		case (QUEM):
			return quemSouEu;
		case (FILMES):
			return filmesFavoritos;
		case (MUSICAS):
			return musicasFavoritas;
		case (LIVROS):
			return livrosFavoritos;

		default:
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO);
		}
	}

	public void setAtributo(String atributo, String novoValor)
			throws ArgumentInvalidException, PersistenceException, IOException {
		if (atributo == null)
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO);
		int codigoAtributo = atributo.hashCode();

		switch (codigoAtributo) {
		case (EMAIL):
			this.setEmail(new Email(novoValor));
			break;
		case (NOME):
			this.setNomeDeExibicao(novoValor);
			break;
		case (ENDERECO):
			this.setEndereco(novoValor);
			break;
		case (INTERESSES):
			this.setInteresses(novoValor);
			break;
		case (DATA):
			this.setDataDeNascimento(novoValor);
			break;
		case (SEXO):
			this.setSexo(novoValor);
			break;
		case (QUEM):
			this.setQuemSouEu(novoValor);
			break;
		case (FILMES):
			this.setFilmesFavoritos(novoValor);
			break;
		case (MUSICAS):
			this.setMusicasFavoritas(novoValor);
			break;
		case (LIVROS):
			this.setLivrosFavoritos(novoValor);
			break;

		default:
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO);
		}
	}

}
