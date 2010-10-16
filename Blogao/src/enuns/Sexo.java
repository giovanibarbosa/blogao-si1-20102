package enuns;

import ourExceptions.SexoInvalidoException;
import interfaces.Constantes;

/**
 * Enum de Sexo
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public enum Sexo {
	Masculino("Masculino"), Feminino("Feminino"), Nao_Inf(Constantes.NAO_INFORMADO);
	
	private String tipo;
	
	/**
	 * Construtor do Enum de sexo
	 * @param  tipo {@link String}
	 */
	Sexo(String tipo) {
		this.tipo = tipo;		
	}
	
	/**
	 * Verificador de Sexo
	 * @param sex {@link String} Sexo
	 * @return True se o sexo passado foi validado
	 */
	public static boolean verificaSexo(String sex) {
		if (sex == null || sex.trim().isEmpty()
				|| (!sex.equalsIgnoreCase("Masculino") &&
					!sex.equalsIgnoreCase("Feminino") &&
					!sex.equalsIgnoreCase(Constantes.NAO_INFORMADO))) {
			return false;
		}
		return true;
	}
	
	/**
	 * Metodo acessador de sexo que retorna o Enum sexo
	 * @param sex {@link String} 
	 * @return {@link Sexo}
	 * @throws SexoInvalidoException 
	 */
	public static Sexo setadorSexo(String sex) throws SexoInvalidoException {
		if (!verificaSexo(sex)) {
			throw new SexoInvalidoException(Constantes.SEXO_INVALIDO);
		}	
		if (sex.equalsIgnoreCase(Sexo.Feminino.getSexo())) {
			//this.tipo = Sexo.Feminino.getSexo();
			return Sexo.Feminino;
		}
		else if (sex.equalsIgnoreCase(Sexo.Masculino.getSexo())) {
			//this.tipo = Sexo.Masculino.getSexo();
			return Sexo.Masculino;
		}
		//this.tipo = Sexo.Nao_Inf.getSexo();
		return Sexo.Nao_Inf;		
	}
	
	/**
	 * Metodo acessador do Sexo
	 * @return {@link Sexo}
	 */
	public String getSexo() {
		return tipo;
	}


}
