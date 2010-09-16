package exeTests;


import java.util.ArrayList;
import java.util.List;

import classes.func.usuario.FacadePerfil;

import easyaccept.EasyAcceptFacade;

/**
 * 
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public class PerfilUserStoryTest1 {
	
	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		
        //Put the us1.txt file into the "test scripts" list
        files.add("Tests/us1.txt");
        
        FacadePerfil perfFac = new FacadePerfil();
        
        EasyAcceptFacade eaFacade = new EasyAcceptFacade(perfFac, files);
        
        eaFacade.executeTests();
        
        System.out.println(eaFacade.getCompleteResults());
        
        
	}

}
