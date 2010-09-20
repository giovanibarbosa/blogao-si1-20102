package persistencia.daos;

import java.util.List;

import ourExceptions.ArgumentInvalidException;
import classes.Email;

public class EmailsDAO {

	/**
	 * Cria um {@link Email}, armazenando-o no formato xml se este mesmo não
	 * existir
	 * 
	 * @param email
	 *            O {@link Email} a ser criado e armazenado
	 * @throws ArgumentInvalidException
	 *             Caso o email seja null ou ja exista no banco de dados dos
	 *             emails
	 */
	public void criar(Email email) throws ArgumentInvalidException {
	}

	public void atualizar(Email email, Email novoEmail)
			throws ArgumentInvalidException {
	}

	public List<Email> recuperar() {
		return null;
	}

	public void deleta(Email email) throws ArgumentInvalidException {
	}
}
