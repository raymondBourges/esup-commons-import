/**
 * ESUP-Portail example Application - Copyright (c) 2010 ESUP-Portail consortium.
 */
package org.esupportail.example.web.jsf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.log4j.Logger;

/**
 * @author Yves Deschamps (Université de Lille 1) - 2010
 * 
 */
public class ContentCaptureServletResponse extends HttpServletResponseWrapper {

	@SuppressWarnings("unused")
	private final Logger logger = Logger.getLogger(this.getClass());

	private ByteArrayOutputStream contentBuffer;

	private PrintWriter writer;

	/**
	 * @param response
	 */
	public ContentCaptureServletResponse(HttpServletResponse response) {
		super(response);
	}

	/**
	 * @see javax.servlet.ServletResponseWrapper#getWriter()
	 */
	@Override
	public PrintWriter getWriter() throws IOException {
		if (writer == null) {
			contentBuffer = new ByteArrayOutputStream();
			writer = new PrintWriter(contentBuffer);
		}
		return writer;
	}

	/**
	 * @return the content.
	 */
	public String getContent() {
		writer.flush();
		String xhtmlContent = new String(contentBuffer.toByteArray());
		return xhtmlContent;
	}
	
}
