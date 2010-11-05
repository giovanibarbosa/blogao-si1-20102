package interfaces;

import ourExceptions.ArgumentInvalidException;

public interface Midia {
	
	public String getDescricao();
	
	public void setDescricao(String descricao);
	
	public String getDado();
	
	public void setDado(String dado)throws ArgumentInvalidException;
	
	public String getId();
	
	public void setId(String id);
	
}
