/**
 * ESUP-Portail Directory Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.example.web.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.esupportail.commons.utils.BeanUtils;
import org.esupportail.example.domain.DomainService;
import org.esupportail.example.domain.beans.User;

import com.thoughtworks.xstream.XStream;


/**
 * @author Yves Deschamps (Université de Lille 1) - 2011
 * 
 */
public class JSONServlet extends HttpServlet {

	/**
	 * For Serialize.
	 */
	private static final long serialVersionUID = -7668753452311942420L;

	private DomainService domainService = (DomainService) BeanUtils
			.getBean("domainService");

	private ObjectMapper mapper = new ObjectMapper();

	private PrintWriter writer;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		String mode = request.getParameter("mode");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		List<User> result = new ArrayList<User>();

		try {
			writer = response.getWriter();
			result = domainService.getAdministrators();
			if (result != null && (mode == null || mode.equals("json"))) {
				mapper.writeValue(writer, result);
			} else if (result != null && mode.equals("xml")) {
				XStream xstream = new XStream();
				xstream.alias("person", User.class);
				String xml = xstream.toXML(result);
				writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
				writer.println(xml);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
