package org.cats.accounting.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.cats.accounting.service.AccountService;
import org.cats.accounting.domain.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

	@Mock
	AccountService accountService;
	
	private AccountController accountController;
	
	private List<Account> stubServiceToReturnAccountsList(int size) {
		List<Account> accounts = new ArrayList<Account>();
		for(int i=0; i<size ;i++){
			Account account= new Account();
			accounts.add(account);
		}
		when(accountService.getList()).thenReturn(accounts);
		return accounts;
	}
	
	@Before
	public void setUp()  {
		accountController = new AccountController(accountService);
		
	}

	@Test
	public void testListAccounts() {
		
		List<Account> accounts = stubServiceToReturnAccountsList(2);
		
		List<Account> returnedAccounts = accountController.listAccounts();
		
		assertEquals(accounts,returnedAccounts);
		assertEquals(2,returnedAccounts.size());
		verify(accountService,times(1)).getList();
		
		
		
	}

	

}
