package br.edu.ufcg.dsc.si.blog.webservice;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class HelperServer {
	/**
	 * Inicia o servidor no endereco especificado
	 * @param port endereco do WS (ex.: http://192.168.1.190:9000)
	 */
	public static void startServer(String port) {
		BlogWSImpl implementor = new BlogWSImpl();
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(BlogWS.class);
		svrFactory.setAddress(port);
		svrFactory.setServiceBean(implementor);
		svrFactory.getInInterceptors().add(new LoggingInInterceptor());
		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		svrFactory.create();
	}
}
