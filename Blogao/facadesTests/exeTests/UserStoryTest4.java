package exeTests;


import java.util.ArrayList;
import java.util.List;

import classes.func.usuario.FacadeUserStore4;

import easyaccept.EasyAcceptFacade;

/**
 * Classe necessaria para rodar um teste.
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public class UserStoryTest4 {
	
	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		
        //Put the us4.txt file into the "test scripts" list
        files.add("Tests/us4.txt");
        
        FacadeUserStore4 perfFac = new FacadeUserStore4();
        
        EasyAcceptFacade eaFacade = new EasyAcceptFacade(perfFac, files);
        
        eaFacade.executeTests();
        
        System.out.println(eaFacade.getCompleteResults());
        
        
	}

}

