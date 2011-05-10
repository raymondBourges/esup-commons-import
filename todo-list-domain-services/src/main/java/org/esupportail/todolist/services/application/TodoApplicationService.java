package org.esupportail.todolist.services.application;

import org.esupportail.commons.services.application.SimpleApplicationServiceImpl;


public class TodoApplicationService extends SimpleApplicationServiceImpl{

	/**
	 * Generated serial
	 */
	private static final long serialVersionUID = -883724712788203517L;
	
	private boolean useLdap=false;

	public boolean isUseLdap() {
		return useLdap;
	}

	public void setUseLdap(boolean useLdap) {
		this.useLdap = useLdap;
	}
	
}
