package org.cats.accounting.service;

import java.util.List;

import org.cats.accounting.domain.Account;
import org.cats.accounting.domain.Journal;
import org.cats.accounting.repository.AccountRepository;
import org.cats.accounting.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JournalService {
	

	private JournalRepository journalRepository;
	
	@Autowired
	public JournalService(JournalRepository journalRepository) {
		this.journalRepository = journalRepository;
	}

	@Transactional(readOnly = true)
	public List<Journal> getList() {
		return journalRepository.findAll();
	}

	public Journal getJournalByCode(String code){
		return journalRepository.findByCode(code);
	}
}
