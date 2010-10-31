import br.edu.ufcg.dsc.si.blog.webservice.BlogWS;
import br.edu.ufcg.dsc.si.blog.webservice.HelperClient;

public class MainClient {
	public static void main(String[] args) throws Exception {
		BlogWS blog = HelperClient.getInstance("http://192.168.1.190:9000/helloWorld");		
		blog.isUserLogged("gfd");
	}

	
}
