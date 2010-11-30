import br.edu.ufcg.dsc.si.blog.webservice.HelperClient;
import br.edu.ufcg.dsc.si.blog.webservice.HelperServer;


public class MainTest {
	
	public static void main(String[] args) {
		HelperServer helperServer = new HelperServer();
		helperServer.startServer("http://localhost:9000");
		
		HelperClient helperClient = new HelperClient();
		helperClient.getInstance("http://localhost:9000");
	}

}
