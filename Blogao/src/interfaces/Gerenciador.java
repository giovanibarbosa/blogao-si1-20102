package interfaces;

import java.io.IOException;

import ourExceptions.PersistenceException;

/**
 * Interface com assinatura dos metódos que um gerenciador de dados deve ter.
 * @author Rodolfo Marinho - rodolfoams@lcc.ufcg.edu.br
 * 
 */
public interface Gerenciador {

	/**
	 * Salvar os arquivos no BD
	 * @throws PersistenceException
	 * @throws IOException
	 */
	public void saveData() throws PersistenceException, IOException;

	/**
	 * Carregar os dados do BD
	 */
	public void loadData();

	/**
	 * Limpar a persistencia do BD
	 */
	void cleanPersistence();
}
