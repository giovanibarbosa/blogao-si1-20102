package exeTests;


import java.util.ArrayList;
import java.util.List;

import classes.func.usuario.FacadeUserStore1;

import easyaccept.EasyAcceptFacade;

/**
 * Classe necessaria para rodar um teste.
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public class UserStoryTest1 {
	
	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		
        //Put the us1.txt file into the "test scripts" list
        files.add("Tests/us1.txt");
        files.add("Tests/us2.txt");
        files.add("Tests/us3.txt");
        files.add("Tests/us4.txt");
        
        FacadeUserStore1 perfFac = new FacadeUserStore1();
        
        EasyAcceptFacade eaFacade = new EasyAcceptFacade(perfFac, files);
        
        eaFacade.executeTests();
        
        System.out.println(eaFacade.getCompleteResults());
        
        
	}

}
