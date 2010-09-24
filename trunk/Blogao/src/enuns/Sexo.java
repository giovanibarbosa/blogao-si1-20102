package enuns;

/**
 * Enum de Sexo
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public enum Sexo {
	Mas("Masculino"), Fem("Feminino"), Nao_Inf("N�o informado");
	
	private final String tipo;
	
	Sexo(String tipo) {
		this.tipo = tipo;		
	}
	
	public String getSexo() {
		return tipo;
	}
	
	public static boolean verificaSexo(String sex) {
		if (sex == null || sex.trim().isEmpty()
				|| (!sex.equalsIgnoreCase("Masculino") &&
					!sex.equalsIgnoreCase("Feminino") &&
					!sex.equalsIgnoreCase("N�o informado"))) {
			return false;
		}
		return true;
	}
	
	public static Sexo setadorSexo(String sex) {
		if (sex.equalsIgnoreCase(Sexo.Fem.getSexo())) {
			return Sexo.Fem;
		}
		else if (sex.equalsIgnoreCase(Sexo.Mas.getSexo())) {
			return Sexo.Mas;
		}
		return Sexo.Nao_Inf;		
	}


}
