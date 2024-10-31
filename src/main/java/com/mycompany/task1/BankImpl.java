/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.task1;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mehmet Turan Yardımcı
 */
public class BankImpl implements Bank {
    
    private List<Account> accounts = new ArrayList<>();
    private long idgen = 0;
    private long nextId() {
        return ++idgen;
    }

    @Override
    public Long createAccount(String name, String address) {
        Account account = findAccountObj(name, address);
        if (account == null) {
            account = new Account(nextId(), name, address);
            accounts.add(account);
        }
        return account.getId();
    }
    
    private Account findAccountObj(String name, String address) {
        for (Account account:accounts) {
            if (account.getName().equals(name) && account.getAddress().equals(address)) {
                return account;
            }
        }
        return null;
    }
    
     private Account findAccountObj(Long id) {
        for (Account account:accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public Long findAccount(String name, String address) {
        Account a = findAccountObj(name, address);
        return a != null ? a.getId() : null;
    }

    @Override
    public void deposit(Long id, BigDecimal amount) {
        Account a = findAccountObj(id);
        if (a == null)
            throw new AccountIdException();
       
        BigDecimal currentBalance = a.getBalance();
        a.setBalance(currentBalance.add(amount));                
    }

    @Override
    public BigDecimal getBalance(Long id) {
        Account a = findAccountObj(id);
        if (a == null)
            throw new AccountIdException();
        
        return a.getBalance();  
    }

    @Override
    public void withdraw(Long id, BigDecimal amount) {
        //TODO: implement withdraw operation

        Account a = findAccountObj(id);
        if (a == null)
            throw new AccountIdException();

        if (0 > a.getBalance().compareTo(amount))
            throw new InsufficientFundsException();

        BigDecimal currentBalance = a.getBalance();
        a.setBalance(currentBalance.subtract(amount));
    }

    @Override
    public void transfer(Long idSource, Long idDestination, BigDecimal amount) {
        //TODO: implement transfer operation

        Account Source = findAccountObj(idSource);
        Account Destination = findAccountObj(idDestination);

        if (Source == null || Destination == null)
            throw new AccountIdException();

        if (0 > Source.getBalance().compareTo(amount))
            throw new InsufficientFundsException();

        BigDecimal SourceBalance = Source.getBalance();
        Source.setBalance(SourceBalance.subtract(amount));

        BigDecimal DestinationBalance = Destination.getBalance();
        Destination.setBalance(DestinationBalance.add(amount));
    }
}
