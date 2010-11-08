package facades;


import classes.gerenciadores.GerenciadorDeDados;

public class FacadeDados {
	
	private GerenciadorDeDados gerenteDados;
	private static FacadeDados instance;
	
	public static FacadeDados getInstance() {
		if (instance != null) {
			return instance;
		}
		return new FacadeDados();
	}
	
	private FacadeDados() {
		gerenteDados = GerenciadorDeDados.getInstance();
	}
	
	public void loadData() throws Exception {
		gerenteDados.loadData();
	}
	
	public void saveData() throws Exception {
		gerenteDados.saveData();
	}
	
	public void cleanPersistence() {
		gerenteDados.cleanPersistence();
	}

}
