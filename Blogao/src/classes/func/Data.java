package classes.func;

import interfaces.Constantes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import enuns.Constantes2;

import ourExceptions.ArgumentInvalidException;
import ourExceptions.DataInvalidaException;

/**
 * Classe que inicializa uma data.
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 * 
 */
public class Data {

	private static final long serialVersionUID = 1L;
	private static final int DIAS_DO_MES = 31;
	private static final int MESES_DO_ANO = 12;
	private static int ANO_ATUAL = new GregorianCalendar().get(Calendar.YEAR);
	private Calendar data;

	/**
	 * Construtor default da classe Data
	 */
	public Data() {}

	/**
	 * Construtor da classe Data
	 * 
	 * @param data
	 *            {@link String}
	 * @throws Exception
	 *             caso a cada seja invalida
	 */
	public Data(String data) throws Exception {
		setData(data);
	}

	/**
	 * Metodo que verifica a validade de configuracao de uma data.
	 * 
	 * @param data
	 *            {@link String}
	 * @return True caso a data seja valida
	 */
	public static boolean verificaData(String data) {
		if (data == null || !data.matches("\\d\\d\\/\\d\\d\\/\\d\\d\\d\\d")
				|| Integer.valueOf(data.substring(0, 2)) > DIAS_DO_MES
				|| Integer.valueOf(data.substring(3, 5)) > MESES_DO_ANO
				|| Integer.valueOf(data.substring(6, 10)) > ANO_ATUAL) {
			return false;
		}
		return true;
	}

	/**
	 * Metodo que converte de Calendar para String no formato "dd/MM/yyyy"
	 * 
	 * @param data
	 *            {@link Calendar}
	 * @return String data *dd/MM/yyyy
	 * @throws DataInvalidaException *
	 */
	public static String calendarToString(Calendar data) throws DataInvalidaException {
		if (data == null) {
			throw new DataInvalidaException(Constantes2.DATA_INVALIDA.getName());
		}
		String strdate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if (data != null) {
			strdate = sdf.format(data.getTime());
		}
		return strdate;
	}

	/**
	 * Metodo usado para converter uma String num Calendar.
	 * 
	 * @param data
	 *            {@link String}
	 * @return Calendar data
	 * @throws ParseException
	 *             caso nao consiga converter.
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
	 * 
	 * @return Calendar data
	 */
	public Calendar getData() {
		return data;
	}

	/**
	 * Metodo que seta uma data
	 * 
	 * @param data
	 *            {@link String} nova
	 * @throws ArgumentInvalidException
	 *             caso a data seja invalida
	 */
	public void setData(String data) throws DataInvalidaException {
		if (!verificaData(data)) {
			throw new DataInvalidaException(Constantes2.DATA_INVALIDA.getName());
		}
		try {
			Calendar date = conversorData(data);
			this.data = date;

		} catch (Exception e) {
			throw new DataInvalidaException(Constantes2.DATA_INVALIDA.getName());
		}
	}

	/**
	 * Metodo que retorna a data de hoje
	 * 
	 * @return String data
	 */
	public String todaysDate() {
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(data);
	}
	
	@Override
	public String toString() {
		try {
			return calendarToString(data);
		} catch (DataInvalidaException e) {
			return "";
		}
	}

}
