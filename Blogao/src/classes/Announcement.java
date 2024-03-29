package classes;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que inicializa um Announcement
 * 
 * @author Giovani Barbosa - giovanicb@lcc.ufcg.edu.br
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * @author Tiago Brasileiro - tiagoba@lcc.ufcg.edu.br
 * @author Tiago H S Leite - tiagohsl@lcc.ufcg.edu.br
 *
 */
public class Announcement {

	private String id;
	private String idBlogDeInteresse;
	private List<String> atualizacoes;

	/**
	 * Construtor da classe
	 * @param {@link String} blogId
	 */
	public Announcement(String blogId){
		idBlogDeInteresse = blogId;
		id = geraId();
		atualizacoes = new ArrayList<String>();
	}

	/**
	 * Metodo acessador de id
	 * @return {@link String} id
	 */
	public String getId(){
		return id;
	}

	/**
	 * Metodo acessador de id do blog interessado
	 * @return {@link String} id blog
	 */
	public String getIdBlogDeInteresse() {
		return idBlogDeInteresse;
	}

	/**
	 * Metodo acessador de lista de atualizacoes
	 * @return List<{@link String}> lista de atualizacoes
	 */
	public List<String> getAtualizacoes() {
		return atualizacoes;
	}

	/**
	 * Metodo que adiciona uma atualizacao
	 * @param  att {@link String}
	 */
	public void addAtualizacao(String att){
		if(!atualizacoes.contains(att))
			atualizacoes.add(att);
	}
	
	/**
	 * Metodo que remove uma atualizacao
	 * @param  att {@link String}
	 */
	public void removeAtualizacao(String att){
		if(atualizacoes.contains(att))
			atualizacoes.remove(att);
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Announcement)) return false;
		Announcement ann = (Announcement) obj;
		return ann.getIdBlogDeInteresse().equals(idBlogDeInteresse);
	}
	
	private String geraId() {
		return String.valueOf(this.hashCode());
	}
}
