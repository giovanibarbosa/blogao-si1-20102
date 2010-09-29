package exeTests;

import java.util.ArrayList;
import java.util.List;

import ws.BlogWS;
import ws.BlogWSImpl;

import easyaccept.EasyAcceptFacade;

/**
 * 
 * @author tiagohsl
 * 
 */
public class NewFacadeBlogao {

	// FIXME AJEITAR ISSO AQUI
	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		String saida = "";
		EasyAcceptFacade eaFacade;

		BlogWS perfFac = new BlogWSImpl();
		files.add("Tests/us1.txt");
		eaFacade = new EasyAcceptFacade(perfFac, files);
		eaFacade.executeTests();
		saida += eaFacade.getCompleteResults();
		files.remove(0);

		files.add("Tests/us2.txt");
		eaFacade = new EasyAcceptFacade(perfFac, files);
		eaFacade.executeTests();
		saida += eaFacade.getCompleteResults();
		files.remove(0);

		files.add("Tests/us3.txt");
		eaFacade = new EasyAcceptFacade(perfFac, files);
		eaFacade.executeTests();
		saida += eaFacade.getCompleteResults();
		files.remove(0);

		files.add("Tests/us4.txt");
		eaFacade = new EasyAcceptFacade(perfFac, files);
		eaFacade.executeTests();
		saida += eaFacade.getCompleteResults();
		files.remove(0);

		files.add("Tests/us5.txt");
		eaFacade = new EasyAcceptFacade(perfFac, files);
		eaFacade.executeTests();
		saida += eaFacade.getCompleteResults();
		files.remove(0);

		files.add("Tests/us6.txt");
		eaFacade = new EasyAcceptFacade(perfFac, files);
		eaFacade.executeTests();
		saida += eaFacade.getCompleteResults();
		files.remove(0);

		files.add("Tests/us7.txt");
		eaFacade = new EasyAcceptFacade(perfFac, files);
		eaFacade.executeTests();
		saida += eaFacade.getCompleteResults();
		files.remove(0);

		files.add("Tests/us8.txt");
		eaFacade = new EasyAcceptFacade(perfFac, files);
		eaFacade.executeTests();
		saida += eaFacade.getCompleteResults();
		files.remove(0);

		files.add("Tests/us9.txt");
		eaFacade = new EasyAcceptFacade(perfFac, files);
		eaFacade.executeTests();
		saida += eaFacade.getCompleteResults();
		files.remove(0);

		files.add("Tests/us10.txt");
		eaFacade = new EasyAcceptFacade(perfFac, files);
		eaFacade.executeTests();
		saida += eaFacade.getCompleteResults();
		files.remove(0);

		files.add("Tests/us11.txt");
		eaFacade = new EasyAcceptFacade(perfFac, files);
		eaFacade.executeTests();
		saida += eaFacade.getCompleteResults();
		files.remove(0);

		files.add("Tests/us12.txt");
		eaFacade = new EasyAcceptFacade(perfFac, files);
		eaFacade.executeTests();
		saida += eaFacade.getCompleteResults();
		files.remove(0);

		files.add("Tests/us13.txt");
		eaFacade = new EasyAcceptFacade(perfFac, files);
		eaFacade.executeTests();
		saida += eaFacade.getCompleteResults();
		files.remove(0);

		files.add("Tests/us14.txt");
		eaFacade = new EasyAcceptFacade(perfFac, files);
		eaFacade.executeTests();
		saida += eaFacade.getCompleteResults();
		files.remove(0);

		System.out.println(saida);

	}

}
