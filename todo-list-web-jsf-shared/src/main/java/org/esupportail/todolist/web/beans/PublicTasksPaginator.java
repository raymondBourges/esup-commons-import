package org.esupportail.todolist.web.beans;

import java.util.List;

import org.esupportail.todolist.domain.beans.Task;

public class PublicTasksPaginator extends TasksPaginator {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 7538938603688701222L;
	/*
	 * ************************** METHODS ***********************************
	 */
	@Override
	protected List<Task> getData() {
		
		return getDomainService().getPublicTasks();
	}
	

}
