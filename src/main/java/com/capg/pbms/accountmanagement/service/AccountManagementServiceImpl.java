package com.capg.pbms.accountmanagement.service;


import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.capg.pbms.accountmanagement.exceptions.AccountAlreadyExistException;
import com.capg.pbms.accountmanagement.exceptions.AccountNotFoundException;
import com.capg.pbms.accountmanagement.exceptions.EmptyAccountListException;
import com.capg.pbms.accountmanagement.model.Customer;

import com.capg.pbms.accountmanagement.repository.AccountRepository;
import com.capg.pbms.accountmanagement.repository.AddressRepository;

@Service
public class AccountManagementServiceImpl implements AccountManagementService {
	
	@Autowired
	AccountRepository accountrepo;
	
	@Autowired
	AddressRepository addressRepo;

	
	
	Customer customer=new Customer();
	
	@Override
	public Customer addAccount(Customer customer) {
		customer.setAccountId(Long.parseLong(String.valueOf(Math.abs(new Random().nextLong())).substring(0, 12)));
		customer.setAccountHolderId(String.valueOf(Math.abs(new Random().nextLong())).substring(0, 12));
		customer.setAccountBranchId("1");
//		customer.setAccountBalance(getBalanceById(customer.getAccountId()));
		customer.setAccountIntrest(0.0);
		customer.setCustomerId(String.valueOf(Math.abs(new Random().nextLong())).substring(0,4));
		if(accountrepo.existsById(customer.getAccountId()))
		throw new AccountAlreadyExistException("Customer with Id: " +customer.getAccountId()+" is Already Exist");
		addressRepo.save(customer.getCustomerAddress());
		return accountrepo.save(customer);
	}
	@Override
	public Customer getAccount(long accountId) {
		if(!accountrepo.existsById(accountId)) 
		{	
		   throw new AccountNotFoundException("Customer with Id "+accountId+" Not Found");
		}
	    return accountrepo.getOne(accountId);
	}

	
	@Override
	public Customer updateCustomerName(long accountId,Customer customer) {
		if(!accountrepo.existsById(customer.getAccountId()))
		{
	    throw new AccountNotFoundException("Customer with Id : " +customer.getAccountId()+" Not Found");
		}
		Customer newAccount=accountrepo.getOne(customer.getAccountId());
		newAccount.setCustomerName(customer.getCustomerName());
        accountrepo.save(newAccount);
	   return newAccount;	
	}

	@Override
	public boolean deleteAccount(long accountId) {
			if(!accountrepo.existsById(accountId)) 
			{	
			   throw new AccountNotFoundException("Customer with Id "+accountId+" Not Found");
			}
			accountrepo.deleteById(accountId);
			
			return !accountrepo.existsById(accountId);

		}

	@Override
	public List<Customer> getAllAccounts() {
	
		 if(accountrepo.count()==0)
		    throw new EmptyAccountListException("No Account Found in Account Database");
			return accountrepo.findAll();
	}

	@Override
	public Customer updateCustomerContact(long accountId,Customer customer) {
		
	 if(!accountrepo.existsById(customer.getAccountId()))
			
	 throw new AccountNotFoundException("Customer with Id : " +customer.getAccountId()+" Not Found");
			
		Customer newAccount=accountrepo.getOne(customer.getAccountId());
		newAccount.setCustomerContact(customer.getCustomerContact());
		     accountrepo.save(newAccount);
		      return newAccount;	
	}

	@Override
	public Customer updateCustomerAddress(long accountId,Customer customer) {
		
		 if(!accountrepo.existsById(customer.getAccountId()))
				
		 throw new AccountNotFoundException("Customer with Id : " +customer.getAccountId()+" Not Found");
					
			Customer newAccount=accountrepo.getOne(customer.getAccountId());
			newAccount.setCustomerAddress(customer.getCustomerAddress());
				     accountrepo.save(newAccount);
				      return newAccount;	
   }
	
	public boolean isValidAccountId(long accountId) {
		String str=String.valueOf(accountId);
		if(str.matches("[0-9]{12}")) 
		{
			 
			return true;	
		}
		throw new AccountNotFoundException("Account No should be in 12 digits");
		}


	 	public boolean isValidZipcode(String addressZipcode) 
	 	{
		if(addressZipcode.matches("[0-9]{6}")) 
		{
	 			return true;
	     }
			return false;
		}
		
}
