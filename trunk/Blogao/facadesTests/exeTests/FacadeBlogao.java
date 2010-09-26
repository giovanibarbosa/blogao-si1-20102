package exeTests;


import java.util.ArrayList;
import java.util.List;


import easyaccept.EasyAcceptFacade;
import classes.func.usuario.FacadeUserStore1;
import classes.func.usuario.FacadeUserStore2;
import classes.func.usuario.FacadeUserStore3;
import classes.func.usuario.FacadeUserStore4;
import classes.func.usuario.FacadeUserStore5;
import classes.func.usuario.FacadeUserStore6;
import classes.func.usuario.FacadeUserStore7;
import classes.func.usuario.FacadeUserStore8;

/**
 * Classe necessaria para rodar um teste.
 * @author Tiago Leite - tiagohsl@lsd.ufcg.edu.br
 *
 */
public class FacadeBlogao {
	private final static String SEPARADOR = System
		.getProperty("file.separator");
	public static void main(String[] args) {
		
		
		
		List<String> files1 = new ArrayList<String>();
		List<String> files2 = new ArrayList<String>();
		List<String> files3 = new ArrayList<String>();
		List<String> files4 = new ArrayList<String>();
		List<String> files5 = new ArrayList<String>();
		List<String> files6 = new ArrayList<String>();
		List<String> files7 = new ArrayList<String>();
		List<String> files8 = new ArrayList<String>();
		
        //Put the us1.txt file into the "test scripts" list
        files1.add("Tests/us1.txt");		
        files2.add("Tests/us2.txt");
        files3.add("Tests/us3.txt");
        files4.add("Tests/us4.txt");
        files5.add("Tests/us5.txt");
        files6.add("Tests/us6.txt");
        files7.add("Tests/us7.txt");
        files8.add("Tests/us8.txt");

        
        //Testes para a us1.txt
        FacadeUserStore1 perfFac1 = new FacadeUserStore1();        
        EasyAcceptFacade eaFacade1 = new EasyAcceptFacade(perfFac1, files1);        
        eaFacade1.executeTests();
        
//        //Testes para a us2.txt        
//        FacadeUserStore2 perfFac2 = new FacadeUserStore2();
//        EasyAcceptFacade eaFacade2 = new EasyAcceptFacade(perfFac2, files2);  
//        eaFacade2.executeTests();
//        
//        //Testes para a us3.txt        
//        FacadeUserStore3 perfFac3 = new FacadeUserStore3();
//        EasyAcceptFacade eaFacade3 = new EasyAcceptFacade(perfFac3, files3);  
//        eaFacade3.executeTests();
//        
//        //Testes para a us4.txt        
//        FacadeUserStore4 perfFac4 = new FacadeUserStore4();
//        EasyAcceptFacade eaFacade4 = new EasyAcceptFacade(perfFac4, files4);  
//        eaFacade4.executeTests();
//        
//        //Testes para a us5.txt  
//        FacadeUserStore5 perfFac5 = new FacadeUserStore5();
//        EasyAcceptFacade eaFacade5= new EasyAcceptFacade(perfFac5, files5);  
//        eaFacade5.executeTests();
//        
//        FacadeUserStore6 perfFac6 = new FacadeUserStore6();
//        EasyAcceptFacade eaFacade6= new EasyAcceptFacade(perfFac6, files6);  
//        eaFacade6.executeTests();
//        
//        FacadeUserStore7 perfFac7 = new FacadeUserStore7();
//        EasyAcceptFacade eaFacade7= new EasyAcceptFacade(perfFac7, files7);  
//        eaFacade7.executeTests();
//        
//        FacadeUserStore8 perfFac8 = new FacadeUserStore8();
//        EasyAcceptFacade eaFacade8= new EasyAcceptFacade(perfFac8, files8);  
//        eaFacade8.executeTests();
        
        
        
        System.out.println(eaFacade1.getCompleteResults()); //Resultados para a us1.txt
//        System.out.println(eaFacade2.getCompleteResults()); //Resultados para a us2.txt
//        System.out.println(eaFacade3.getCompleteResults()); //Resultados para a us3.txt
//        System.out.println(eaFacade4.getCompleteResults()); //Resultados para a us4.txt
//        System.out.println(eaFacade5.getCompleteResults());
//        System.out.println(eaFacade6.getCompleteResults());
//        System.out.println(eaFacade7.getCompleteResults());
//        System.out.println(eaFacade8.getCompleteResults());
	}

}
