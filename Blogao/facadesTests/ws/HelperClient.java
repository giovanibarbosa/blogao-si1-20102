package ws;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class HelperClient {

	/**
	 * Retorna uma instancia do objeto BlogWS
	 * @param port
	 * @return
	 */
	public static BlogWS getInstance(String port) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(BlogWS.class);
		factory.setAddress(port);
		return (BlogWS) factory.create();
	}
}