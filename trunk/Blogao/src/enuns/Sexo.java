package enuns;

import interfaces.Constantes;

/**
 * Enum de Sexo
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public enum Sexo {
	Mas("Masculino"), Fem("Feminino"), Nao_Inf(Constantes.NAO_INFORMADO);
	
	private final String tipo;
	
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
	 */
	public static Sexo setadorSexo(String sex) {
		if (sex.equalsIgnoreCase(Sexo.Fem.getSexo())) {
			return Sexo.Fem;
		}
		else if (sex.equalsIgnoreCase(Sexo.Mas.getSexo())) {
			return Sexo.Mas;
		}
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
