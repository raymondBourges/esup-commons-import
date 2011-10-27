package org.esupportail.formation.utils;

import java.util.Comparator;

import org.esupportail.formation.domain.beans.Task;

public class TaskTitleComparator implements Comparator<Task> {
	public int compare(Task t1, Task t2) 
	  {
		if (t1.getTitle()==null)
			return -1;
		else if (t2.getTitle()==null)
			return 1;
		else{
		    int result =  t1.getTitle().compareTo(t2.getTitle());
		    //si le titre est identique on trie ensuite par la date
		    if(result==0){
		    	if (t1.getDate()==null)
		    		result =  -1;
				else if (t2.getDate()==null)
					result =  1;
				else
					result = t1.getDate().compareTo(t2.getDate());
		    }
		    return result;
		}
	  }
}
