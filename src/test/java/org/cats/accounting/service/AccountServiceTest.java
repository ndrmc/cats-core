package org.cats.accounting.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.cats.accounting.domain.Account;
import org.cats.accounting.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	@Mock
	AccountRepository accountRepository;
	
	private AccountService accountService;
	
	@Before
	public void setUp() throws Exception {
		accountService = new AccountService(accountRepository);
	}
	
	private List<Account> stubRepoToReturnAccountsList(int size) {
		List<Account> accounts = new ArrayList<Account>();
		for(int i=0; i<size ;i++){
			Account account= new Account();
			accounts.add(account);
		}
		when(accountRepository.findAll()).thenReturn(accounts);
		return accounts;
	}

	@Test
	public void testGetList() {
		List<Account> accounts = stubRepoToReturnAccountsList(2);
		
		List<Account> returnedAccounts = accountService.getList();
		
		assertEquals(accounts,returnedAccounts);
		assertEquals(2,returnedAccounts.size());
		verify(accountRepository,times(1)).findAll();
	}

}





