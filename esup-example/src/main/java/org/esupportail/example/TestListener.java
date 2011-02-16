package org.esupportail.example;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;

/**
 * Application Lifecycle Listener implementation class TestListener
 *
 */
public class TestListener implements ServletRequestListener {
	
	/**
	 * A logger.
	 */
	private final Logger logger = new LoggerImpl(getClass());	

    /**
     * Default constructor. 
     */
    public TestListener() {
    	logger.debug("Dans constructeur...");
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent sre) {
    	logger.debug("Dans requestDestroyed...");
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre) {
    	logger.debug("Dans requestInitialized...");
    }
	
}
