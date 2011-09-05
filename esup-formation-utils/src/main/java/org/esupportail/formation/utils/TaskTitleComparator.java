package org.esupportail.formation.utils;

import java.util.Comparator;

import org.esupportail.formation.domain.beans.Task;

public class TaskTitleComparator implements Comparator<Task> {
	public int compare(Task t1, Task t2) 
	  {
	    int result = t1.getTitle().compareTo(t2.getTitle());
	    //si le titre est identique on trie ensuite par la date
	    if(result==0)
	      result = t1.getDate().compareTo(t2.getDate());
	  	return result;
	  }
}
