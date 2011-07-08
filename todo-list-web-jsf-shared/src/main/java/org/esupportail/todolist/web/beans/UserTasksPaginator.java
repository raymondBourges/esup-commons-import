package org.esupportail.todolist.web.beans;

import java.util.List;

import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.todolist.domain.beans.Task;
import org.esupportail.todolist.domain.beans.User;

public class UserTasksPaginator extends TasksPaginator {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 4971575146028954870L;

	private User user=null;
	private final Logger logger = new LoggerImpl(this.getClass());
	@Override
	protected List<Task> getData() {
		
		if (this.user==null)
			return null;
		else{
			if (logger.isDebugEnabled()) {
				logger.debug("UserTasksPaginator :: getData for User "+user.getId());
			}
			return getDomainService().getUserTasks(user);
		}
	
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
