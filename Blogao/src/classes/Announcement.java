package classes;

import java.util.ArrayList;
import java.util.List;

import ourExceptions.ArgumentInvalidException;

import classes.gerenciadores.GerenciadorDeDados;

public class Announcement {

	String id;
	String idBlogDeInteresse;
	List<String> atualizacoes;

	public Announcement(String blogId){
		idBlogDeInteresse = blogId;
		id = geraId();
		atualizacoes = new ArrayList<String>();
	}

	private String geraId() {
		return String.valueOf(this.hashCode());
	}
	
	public String getId(){
		return id;
	}

	public String getIdBlogDeInteresse() {
		return idBlogDeInteresse;
	}

	public List<String> getAtualizacoes() {
		return atualizacoes;
	}

	public void addAtualizacao(String att){
		if(!atualizacoes.contains(att))
			atualizacoes.add(att);
	}
	
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
}
