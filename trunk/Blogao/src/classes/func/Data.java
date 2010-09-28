package classes.func;

import interfaces.Constantes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ourExceptions.ArgumentInvalidException;

/**
 * Classe que inicializa uma data.
 * @author Tiago Leite - tiagohsl@lcc.ufcg.edu.br
 *
 */
public class Data {

	private static final long serialVersionUID = 1L;
	private static final int DIAS_DO_MES = 31;
	private static final int MESES_DO_ANO = 12;
	private static int ANO_ATUAL;
	private Calendar data;
	
	/**
	 * Construtor default da classe Data
	 */
	public Data() {
		this.ANO_ATUAL = new GregorianCalendar().get(Calendar.YEAR);
	}
	
	
	/**
	 * Construtor da classe Data
	 * @param String data
	 * @throws Exception caso a cada seja invalida
	 */
	public Data(String data) throws Exception {
		this.ANO_ATUAL = new GregorianCalendar().get(Calendar.YEAR);
		setData(data);
	}
	
	/**
     * Metodo que verifica a validade de configuracao de uma data.
     * @param String data
     * @return True caso a data seja valida
     */
	public static boolean verificaData(String data) {
		if (data == null || !data.matches("\\d\\d\\/\\d\\d\\/\\d\\d\\d\\d")
				|| Integer.valueOf(data.substring(0, 2)) > DIAS_DO_MES
				|| Integer.valueOf(data.substring(3, 5)) > MESES_DO_ANO
				|| Integer.valueOf(data.substring(6, 10)) > ANO_ATUAL ) {
			return false;
		}
		return true;
	}
	
	/**
	 * Metodo que converte de Calendar para String no formato "dd/MM/yyyy"
	 * @param Calendar data
	 * @return String data *dd/MM/yyyy*
	 */
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
	public static Calendar conversorData(String dat) throws ParseException {
		String data = dat;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(data));
		
		return cal;

	}

	/**
	 * Metodo acessador de uma data
	 * @return Calendar data
	 */
	public Calendar getData() {
		return data;
	}
	
	/**
	 * Metodo que seta uma data
	 * @param String nova data
	 * @throws ArgumentInvalidException caso a data seja invalida
	 */
	public void setData(String data) throws ArgumentInvalidException {
		if (!verificaData(data)) {
			throw new ArgumentInvalidException(Constantes.DATA_INVALIDA);			
		}		
		try {
			Calendar date = conversorData(data);
			this.data = date;
			
		} catch (Exception e) {
			throw new ArgumentInvalidException(Constantes.DATA_INVALIDA);			
		}		
	}
	
	/**
	 * Metodo que retorna a data de hoje
	 * @return String data
	 */
	public String todaysDate() {
		Date data = new Date();  
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
		return formatador.format(data);
	}
	
	
	
	
	

}
