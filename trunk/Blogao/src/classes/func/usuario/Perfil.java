package classes.func.usuario;

import interfaces.Constantes;

import java.io.IOException;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.PersistenceException;
import classes.Email;
import classes.func.Data;
import enuns.Sexo;

/**
 * Classe que inicializa uma perfil
 * @author Tiago Leite - tiagohsl@lcc.ufcg.edu.br
 * @colaborator Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * 
 */
public class Perfil {

	private Email email;
	private String nomeDeExibicao = "";
	private String endereco = "";
	private String interesses = "";
	private Data dataDeNascimento;
	private Sexo sexo;
	private String quemSouEu = "";
	private String filmesFavoritos = "";
	private String musicasFavoritas = "";
	private String livrosFavoritos = "";
	private String loginUsuarioDono;

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

	/**
	 * Metodo acessador de {@link Email}
	 * 
	 * @return {@link Email}
	 */
	public Email getEmail() {
		return email;
	}

	/**
	 * Metodo acessador do login do dono do perfil
	 * @return {@link String} login
	 */
	public String getLoginUsuarioDono() {
		return loginUsuarioDono;
	}

	/**
	 * Metodo modificador de logins
	 * @param  loginUsuarioDono {@link String}
	 */
	public void setLoginUsuario(String loginUsuarioDono) {
		this.loginUsuarioDono = loginUsuarioDono;
	}

	/**
	 * Metodo modificador de {@link Email}
	 * 
	 * @param email {@link Email}
	 */
	public void setEmail(Email email) {
		this.email = email;
	}

	/**
	 * Metodo acessador de nome de exibicao
	 * 
	 * @return {@link String}
	 */
	public String getNomeDeExibicao() {
		return nomeDeExibicao;
	}

	/**
	 * Metodo modificador de nome de exibicao
	 * 
	 * @param nomeDeExibicao {@link String}
	 */
	public void setNomeDeExibicao(String nomeDeExibicao) {
		if (nomeDeExibicao != null)
			this.nomeDeExibicao = nomeDeExibicao;
	}

	/**
	 * Metodo acessador de Endereco
	 * 
	 * @return {@link String}
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Metodo modificador de endereco
	 * 
	 * @param endereco {@link String} 
	 */
	public void setEndereco(String endereco) {
		if (endereco != null)
			this.endereco = endereco;
	}

	/**
	 * Metodo acessador de interesses
	 * 
	 * @return {@link String} interesses
	 */
	public String getInteresses() {
		return interesses;
	}

	/**
	 * Metodo modificador de interesses
	 * 
	 * @param interesses {@link String} 
	 */
	public void setInteresses(String interesses) {
		if (interesses != null)
			this.interesses = interesses;
	}

	/**
	 * Metodo acessador de {@link Data}
	 * 
	 * @return {@link Data}
	 */
	public Data getDataDeNascimento() {
		return dataDeNascimento;
	}

	/**
	 * Metodo modificador de data de nascimento
	 * 
	 * @param dataDeNascimento {@link String}
	 * @throws ArgumentInvalidException
	 *             caso a data seja invalida
	 */
	public void setDataDeNascimento(String dataDeNasc)
			throws ArgumentInvalidException {
		try {
			dataDeNascimento = new Data(dataDeNasc);
		} catch (Exception e) {
			throw new ArgumentInvalidException(Constantes.DATA_INVALIDA);
		}
	}

	/**
	 * Metodo acessador de Quem eu sou
	 * 
	 * @return {@link String} quem eu sou
	 */
	public String getQuemSouEu() {
		return quemSouEu;
	}

	/**
	 * Metodo moficador de quem eu sou
	 * 
	 * @param quemSouEu {@link String}
	 */
	public void setQuemSouEu(String quemSouEu) {
		if (quemSouEu != null)
			this.quemSouEu = quemSouEu;
	}

	/**
	 * Metodo acessador de Filmes favoritos
	 * 
	 * @return {@link String} filmes favoritos
	 */
	public String getFilmesFavoritos() {
		return filmesFavoritos;
	}

	/**
	 * Metodo modificador de filmes favoritos
	 * 
	 * @param filmesFavoritos {@link String}
	 */
	public void setFilmesFavoritos(String filmesFavoritos) {
		if (filmesFavoritos != null)
			this.filmesFavoritos = filmesFavoritos;
	}

	/**
	 * Metodo acessador de musicas favoritas
	 * 
	 * @return {@link String} Musicas favoritas
	 */
	public String getMusicasFavoritas() {
		return musicasFavoritas;
	}

	/**
	 * Metodo modificador de musicas favoritas
	 * 
	 * @param musicasFavoritas {@link String}
	 */
	public void setMusicasFavoritas(String musicasFavoritas) {
		if (musicasFavoritas != null)
			this.musicasFavoritas = musicasFavoritas;
	}

	/**
	 * Metodo acessador de livros favoritos
	 * 
	 * @return {@link String} Livros favoritdos
	 */
	public String getLivrosFavoritos() {
		return livrosFavoritos;
	}

	/**
	 * Metodo modificador de livros favoritos
	 * 
	 * @param livrosFavoritos {@link String}
	 */
	public void setLivrosFavoritos(String livrosFavoritos) {
		if (livrosFavoritos != null)
			this.livrosFavoritos = livrosFavoritos;
	}

	/**
	 * Metodo modificador de {@link Sexo}
	 * 
	 * @param sex {@link Sexo}
	 * @throws ArgumentInvalidException
	 *             caso o sexo seja invalido
	 */
	public void setSexo(String sex) throws ArgumentInvalidException {
		if (!Sexo.verificaSexo(sex)) {
			throw new ArgumentInvalidException(Constantes.SEXO_INVALIDO);
		}
		this.sexo = Sexo.setadorSexo(sex);
	}

	/**
	 * Metodo acessador de {@link Sexo}
	 * 
	 * @return {@link Sexo}
	 */
	public Sexo getSexo() {
		return sexo;
	}

	/**
	 * Metodo acessador de atributo
	 * 
	 * @param atributo {@link String} desejado
	 * @return {@link String} o atributo do Perfil
	 * @throws ArgumentInvalidException
	 *             caso o atributo passado seja invalido
	 */
	public String getAtributo(String atributo) throws ArgumentInvalidException {
		if (atributo == null)
			throw new ArgumentInvalidException(Constantes.ATRIBUTO_INVALIDO);
		int codigoAtributo = atributo.hashCode();

		switch (codigoAtributo) {
		case (EMAIL):
			return this.email.toString();
		case (NOME):
			if(this.nomeDeExibicao.equals("")) return this.loginUsuarioDono;
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

	/**
	 * Metodo modificador de atributo
	 * 
	 * @param atributo {@link String} desejado
	 * @param novoValor {@link String}
	 * @throws ArgumentInvalidException
	 *             caso o atributo passado seja invalido
	 * @throws PersistenceException
	 * @throws IOException
	 */
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

	@Override
	public String toString() {
		return nomeDeExibicao;
	}

}
