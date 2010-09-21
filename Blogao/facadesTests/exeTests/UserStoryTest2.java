package exeTests;


import java.util.ArrayList;
import java.util.List;

import classes.func.usuario.FacadeUserStore2;

import easyaccept.EasyAcceptFacade;
 
/**
 * Classe que executa o teste referente a US2.
 * @author Tiago
 */
public class UserStoryTest2 {
	
	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		
        //Put the us1.txt file into the "test scripts" list
        files.add("Tests/us2.txt");
        
        FacadeUserStore2 perfFac = new FacadeUserStore2();
        
        EasyAcceptFacade eaFacade = new EasyAcceptFacade(perfFac, files);
        
        eaFacade.executeTests();
        
        System.out.println(eaFacade.getCompleteResults());
        
        
	}

}
