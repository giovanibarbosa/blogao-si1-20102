package exeTests;


import java.util.ArrayList;
import java.util.List;

import classes.func.usuario.FacadeUserStore3;

import easyaccept.EasyAcceptFacade;

/**
 * Classe que executa o teste US3
 * @author Tiago
 */
public class UserStoryTest3 {
	
	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		
        //Put the us3.txt file into the "test scripts" list
        files.add("Tests/us3.txt");
        
        FacadeUserStore3 perfFac = new FacadeUserStore3();
        
        EasyAcceptFacade eaFacade = new EasyAcceptFacade(perfFac, files);
        
        eaFacade.executeTests();
        
        System.out.println(eaFacade.getCompleteResults());
        
        
	}

}

