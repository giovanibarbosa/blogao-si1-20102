package classes.func.usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ourExceptions.ArgumentInvalidException;
import interfaces.Logavel;
import classes.Email;
import classes.Login;
import classes.Senha;
import classes.Texto;
import enuns.Sexo;

public class Perfil {

	public Logavel logavel;
	public Email email;
	public String nomeDeExibicao;
	public String endereco;
	public Texto interesses;
	public Calendar dataDeNascimento;
	public Sexo sexo;
	public Texto quemSouEu;
	public Texto filmesFavoritos;
	public Texto musicasFavoritas;
	public Texto livrosFavoritos;
	
	public Perfil(Logavel userLog, Email email) {
		setLogavel(userLog);
		setEmail(email);
	}
	
	public void setLogavel(Logavel userLog) {
		this.logavel = userLog;
	}
	
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
	public Calendar getDataDeNascimento() {
		return dataDeNascimento;
	}
	//FIXME AJEITAR AQUI
	public void setDataDeNascimento(String dataDeNascimento) throws Exception {
		try {
			verificaData(dataDeNascimento);
			Calendar data = conversorData(dataDeNascimento);
			this.dataDeNascimento = data;
			
		} catch (Exception e) {
			throw new ArgumentInvalidException("Data inv�lida");			
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
			throw new ArgumentInvalidException("Sexo inv�lido");
		}
		this.sexo = Sexo.setadorSexo(sex);		
	}
	
	public Sexo getSexo() {
		return sexo;
	}
	
	/**
     * Verifica a validade de configuracao de uma data.
     * @param String data
     * @return boolean
	 * @throws Exception 
     */
	//FIXME HERE TOO (Tirar tudo de Data e criar uma nova classe)
	public static void verificaData(String data) throws Exception {
		if (data == null || !data.matches("\\d\\d\\/\\d\\d\\/\\d\\d\\d\\d")
				|| Integer.valueOf(data.substring(0, 2)) > 31
				|| Integer.valueOf(data.substring(3, 5)) > 12
				|| Integer.valueOf(data.substring(6, 10)) > 2010 ) {
			throw new ArgumentInvalidException("Data inv�lida");
		}
	}
	
	/**
	 * Metodo que converte de Calendar para String no formato "dd/MM/yyyy"
	 * @param Calendar data
	 * @return String data *dd/MM/yyyy*
	 */
	//FIXME HERE TOO (Tirar tudo de Data e criar uma nova classe)
	public static String calendarToString(Calendar data) {
		String strdate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if (data != null) {
			strdate = sdf.format(data.getTime());
		}
		return strdate;
	}
	
	/**
	 * Metodo usado para converter uma String num Calendar.
	 * @param String data
	 * @return Calendar data
	 * @throws ParseException caso nao consiga converter.
	 */
	//FIXME HERE TOO (Tirar tudo de Data e criar uma nova classe)
	public static Calendar conversorData(String dat) throws ParseException {
		String data = dat;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(data));
		
		return cal;

	}
	
	
}
