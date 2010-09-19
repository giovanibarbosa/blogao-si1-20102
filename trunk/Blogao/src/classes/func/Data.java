package classes.func;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	private static final int ANO_ATUAL = 2010; //FIXME Ajeitar para automatizar isso.
	private Calendar data;
	
	
	public Data(String data) throws Exception {
		setData(data);
	}
	
	
	/**
     * Verifica a validade de configuracao de uma data.
     * @param String data
     * @return boolean
	 * @throws Exception 
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
	 * @return the data
	 */
	public Calendar getData() {
		return data;
	}


	/**
	 * @param data the data to set
	 */
	public void setData(Calendar data) {
		this.data = data;
	}
	
	public void setData(String data) throws ArgumentInvalidException {
		if (!verificaData(data)) {
			throw new ArgumentInvalidException("Data inválida");			
		}		
		try {
			Calendar date = conversorData(data);
			this.data = date;
			
		} catch (Exception e) {
			throw new ArgumentInvalidException("Data inválida");			
		}
		
	}
	
	
	
	

}
