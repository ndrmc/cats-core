package org.cats.accounting.controller;

import java.util.List;

import org.cats.accounting.domain.Account;
import org.cats.accounting.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "journal", description = "View journals" )
public class JournalController {
	
	private JournalService journalService;

	@Autowired
	JournalController(JournalService journalService){
		this.journalService=journalService;
	}

	@ApiOperation(value = "Returns all journals" )
	@RequestMapping(value = "/journal", method = RequestMethod.GET)
	public List<Account> listAccounts() {
		return journalService.getList();
	}

}
