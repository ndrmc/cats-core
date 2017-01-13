package org.cats.accounting.service;

import java.util.List;

import org.cats.accounting.domain.Account;
import org.cats.accounting.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
	

	private AccountRepository accountRepository;
	
	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Transactional(readOnly = true)
	public List<Account> getList() {
		return accountRepository.findAll();
	}

	public Account getAccount(String accountCode){
		return accountRepository.findByCode(accountCode);
	}
}
