package org.cats.accounting.controller;

import java.util.List;

import org.cats.accounting.domain.Account;
import org.cats.accounting.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "account", description = "View accounts" )
public class AccountController {

	private AccountService accountService;

	@Autowired
	AccountController(AccountService accountService){
		this.accountService=accountService;
	}

	@ApiOperation(value = "Returns all accounts" )
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public List<Account> listAccounts() {
		return accountService.getList();
	}

}
