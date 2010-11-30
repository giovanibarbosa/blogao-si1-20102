import java.net.InetAddress;

import br.edu.ufcg.dsc.si.blog.webservice.BlogWS;
import br.edu.ufcg.dsc.si.blog.webservice.HelperClient;

public class MainClient {
	public static void main(String[] args) throws Exception {
		try {
		      InetAddress thisAddress = InetAddress.getLocalHost();
			
		      BlogWS blog = HelperClient.getInstance("http://" + thisAddress.getHostAddress()           +":9000/blogao");
		
		      blog.logon("tiagooleit", "tiago");
		
		} catch (Exception e) {
			
		}
//		BlogWS blog = HelperClient.getInstance("http://192.168.1.190:9000/helloWorld");		
//		blog.isUserLogged("gfd");
	}

	
}
