package exeTests;


import java.util.ArrayList;
import java.util.List;

import classes.func.usuario.FacadeUserStore1;
import classes.func.usuario.FacadeUserStore2;
import classes.func.usuario.FacadeUserStore3;
import classes.func.usuario.FacadeUserStore4;
import classes.func.usuario.FacadeUserStore5;

import easyaccept.EasyAcceptFacade;

/**
 * Classe necessaria para rodar um teste.
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public class UserStoryTest1 {
	
	public static void main(String[] args) {
		List<String> files1 = new ArrayList<String>();
		List<String> files2 = new ArrayList<String>();
		List<String> files3 = new ArrayList<String>();
		List<String> files4 = new ArrayList<String>();
		List<String> files5 = new ArrayList<String>();
		
        //Put the us1.txt file into the "test scripts" list
        files1.add("Tests/us1.txt");
		
        files2.add("Tests/us2.txt");
        files3.add("Tests/us3.txt");
        files4.add("Tests/us4.txt");
//        files5.add("Tests/us5.txt");
//        files.add("Tests/us4.txt");
       // files.add("Tests/us5.txt");
        
        //Testes para a us1.txt
        FacadeUserStore1 perfFac1 = new FacadeUserStore1();        
        EasyAcceptFacade eaFacade1 = new EasyAcceptFacade(perfFac1, files1);        
        eaFacade1.executeTests();
        
        //Testes para a us2.txt        
        FacadeUserStore2 perfFac2 = new FacadeUserStore2();
        EasyAcceptFacade eaFacade2 = new EasyAcceptFacade(perfFac2, files2);  
        eaFacade2.executeTests();
        
        //Testes para a us3.txt        
        FacadeUserStore3 perfFac3 = new FacadeUserStore3();
        EasyAcceptFacade eaFacade3 = new EasyAcceptFacade(perfFac3, files3);  
        eaFacade3.executeTests();
        
        //Testes para a us3.txt        
        FacadeUserStore4 perfFac4 = new FacadeUserStore4();
        EasyAcceptFacade eaFacade4 = new EasyAcceptFacade(perfFac4, files4);  
        eaFacade4.executeTests();
        
        FacadeUserStore5 perfFac5 = new FacadeUserStore5();
        EasyAcceptFacade eaFacade5= new EasyAcceptFacade(perfFac5, files5);  
        eaFacade5.executeTests();
        
        
        
        System.out.println(eaFacade1.getCompleteResults()); //Resultados para a us1.txt
        System.out.println(eaFacade2.getCompleteResults()); //Resultados para a us2.txt
        System.out.println(eaFacade3.getCompleteResults()); //Resultados para a us3.txt
        System.out.println(eaFacade4.getCompleteResults()); //Resultados para a us4.txt
//          System.out.println(eaFacade5.getCompleteResults());
	}

}
