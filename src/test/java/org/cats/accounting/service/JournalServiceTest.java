package org.cats.accounting.service;

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
import org.cats.accounting.repository.JournalRepository;

@RunWith(MockitoJUnitRunner.class)
public class JournalServiceTest {

	@Mock
	JournalRepository journalRepository;

	private JournalService journalService;

	private void stubServiceToReturnJournalsList(int size) {
		List<Journal> journals = new ArrayList<Journal>();
		for(int i=0; i<size ;i++){
			Journal journal= new Journal();
			journals.add(journal);
		}
		when(journalRepository.findAll()).thenReturn(journals);
	}

	@Before
	public void setUp()  {
		journalService = new JournalService(journalRepository);

	}

	@Test
	public void testListJournals() {

		stubServiceToReturnJournalsList(2);

		List<Journal> returnedJournals = journalService.getList();

		assertEquals(2,returnedJournals.size());
		verify(journalRepository,times(1)).findAll();

	}



}
