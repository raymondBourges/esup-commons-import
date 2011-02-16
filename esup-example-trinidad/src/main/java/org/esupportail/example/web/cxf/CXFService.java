/**
 * ESUP-Portail Directory Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.example.web.cxf;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.esupportail.example.domain.beans.User;

/**
 * @author Yves Deschamps (Université de Lille 1) - 2010
 * 
 */
@SuppressWarnings("restriction")
@WebService(serviceName="CXFService", endpointInterface = "org.esupportail.example.web.cxf.CXFService")
public interface CXFService {	
	
	/**
	 * @return the application administrators.
	 */
	List<User> getAdministrators();

}