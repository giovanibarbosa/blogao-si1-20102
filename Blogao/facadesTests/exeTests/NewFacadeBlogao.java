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

	//FIXME AJEITAR ISSO AQUI
	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();

		files.add("Tests/us1.txt");
		files.add("Tests/us2.txt");
		files.add("Tests/us3.txt");
		files.add("Tests/us4.txt");
		files.add("Tests/us5.txt");
		files.add("Tests/us6.txt");
		files.add("Tests/us7.txt");
		files.add("Tests/us8.txt");
		files.add("Tests/us9.txt");
		files.add("Tests/us10.txt");
		files.add("Tests/us11.txt");
		files.add("Tests/us12.txt");
		
		 BlogWS perfFac = new BlogWSImpl();        
		 EasyAcceptFacade eaFacade = new EasyAcceptFacade(perfFac, files);        
	     eaFacade.executeTests();
	     
	     System.out.println(eaFacade.getCompleteResults());
	     
	}

}
