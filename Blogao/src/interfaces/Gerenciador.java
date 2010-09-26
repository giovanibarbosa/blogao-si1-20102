package interfaces;

import java.io.IOException;

import ourExceptions.PersistenceException;

/**
 * Interface com assinatura dos metódos que um gerenciador de dados deve ter
 * 
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * 
 */
public interface Gerenciador {

	public void saveData() throws PersistenceException, IOException;

	public void loadData();

	void cleanPersistence();
}
