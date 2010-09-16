package enuns;

/**
 * Enum de Sexo
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public enum Sexo {
	Mas("Masculino"), Fem("Feminino"), Nao_Inf("Nao informado");
	
	private final String tipo;
	
	Sexo(String tipo) {
		this.tipo = tipo;		
	}
	
	public String getSexo() {
		return tipo;
	}

}
