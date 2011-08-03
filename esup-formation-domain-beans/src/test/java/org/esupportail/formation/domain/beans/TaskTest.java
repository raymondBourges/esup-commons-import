package org.esupportail.formation.domain.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TaskTest {

	@Test
	public void testSetTitle() {
		Task t=new Task();
		t.setTitle("test titre");
		assertEquals("test titre",t.getTitle());
	}
}
