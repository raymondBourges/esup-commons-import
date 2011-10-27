package org.esupportail.formation.domain;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.esupportail.formation.domain.beans.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/testApplicationContext.xml")
public class DomainServiceTest {
	@Autowired
	DomainService domainService;
	
	@Test
	public void testAddTask() {
		Task t=new Task(true, "Test de tache", "Tache de test", new Date());
		domainService.addTask(t);
		List<Task> listedestaches = domainService.getTasks();
		//on cherche la tâche en base
		Task task=null;
		for (int i = 0; i < listedestaches.size(); i++) {
			if(listedestaches.get(i).getTitle().equals("Test de tache"))
				task=listedestaches.get(i);	
		}
		Assert.assertNotNull("La tache de test créée en base n'a pas été retrouvée" , task);
	}

}
