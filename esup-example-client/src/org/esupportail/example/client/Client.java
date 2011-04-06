package org.esupportail.example.client;

import org.esupportail.example.services.remote.Information;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * The client class of esup-example WS. 
 */
public class Client {
	
	/**
	 * The configuration file where Spring beans are defined.
	 */
	private static final String SPRING_CONFIG_FILE = "/properties/applicationContext.xml"; 

	/**
	 * The name of the client bean.
	 */
	private static final String BEAN_NAME = "remoteInformation";
	
	/**
	 * The client bean.
	 */
	private Information remoteService;

	/**
	 * @return the remote service.
	 */
	public Information getRemoteService() {
		if (remoteService == null) {
			ClassPathResource res = new ClassPathResource(SPRING_CONFIG_FILE);
			BeanFactory beanFactory = new XmlBeanFactory(res);
			remoteService = (Information) beanFactory.getBean(BEAN_NAME);
		}
		return remoteService;
	}

	/**
	 * JSF callback.
	 * @return A String.
	 */
	public String getRemoteVersion() {
		return getRemoteService().getVersion();
	}	
	
	/**
	 * JSF callback.
	 * @return A String.
	 */
	public String getRemoteCopyright() {
		return getRemoteService().getCopyright();
	}	
	
	/**
	 * JSF callback.
	 * @return A String.
	 */
	public int getRemoteDepartmentCount() {
		return getRemoteService().getDepartmentCount();
	}	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Client client = new Client();
		System.err.println("version = " + client.getRemoteVersion());
		System.err.println("copyright = " + client.getRemoteCopyright());
		System.err.println("departmentCount = " + client.getRemoteDepartmentCount());
	}

}
