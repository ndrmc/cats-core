package org.cats.accounting.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.cats.accounting.service.JournalService;
import org.cats.accounting.domain.Journal;

@RunWith(MockitoJUnitRunner.class)
public class JournalControllerTest {

	@Mock
	JournalService journalService;

	private JournalController journalController;

	private void stubServiceToReturnJournalsList(int size) {
		List<Journal> journals = new ArrayList<Journal>();
		for(int i=0; i<size ;i++){
			Journal journal= new Journal();
			journals.add(journal);
		}
		when(journalService.getList()).thenReturn(journals);
	}

	@Before
	public void setUp()  {
		journalController = new JournalController(journalService);

	}

	@Test
	public void testListJournals() {

		stubServiceToReturnJournalsList(2);

		List<Journal> returnedJournals = journalController.listJournals();

		assertEquals(2,returnedJournals.size());
		verify(journalService,times(1)).getList();

	}



}
