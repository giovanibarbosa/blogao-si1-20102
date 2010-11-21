package InterfaceGraficaDesktop;

import javax.swing.ImageIcon;

/**
 * Classe que representa uma imagem
 * 
 * @author Giovani
 * 
 */
public class Imagem {
	private String caminho;

	/**
	 * Construtor
	 * 
	 * @param caminho
	 *            O caminho da imagem a ser carregada
	 */
	public Imagem(String caminho) {
		this.caminho = caminho;
	}

	/**
	 * Carrega esta imagem, disponibilizando-a como um ícone para algum botão ou
	 * plano de fundo
	 * 
	 * @return Um ícone de imagem
	 */
	public ImageIcon carregaImagem() {
		return new ImageIcon(getClass().getResource(caminho));
	}
}
