/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.task1;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Mehmet Turan Yardımcı
 */
public class BankTest {
    
    private Bank bank;
    
    // This method is invoked before each method with @Test annotation.
    // As a result each method with @Test starts with new (empty) Bank instance and
    // do not influence other test methods (each work on separate bank instance).
    @BeforeEach
    public void setup() {
        bank = new BankImpl();
    }
    
    @Test
    public void testCreateAccount() {
        Long id = bank.createAccount("joe", "usa");
        assert id!=null;
        Long id2 = bank.createAccount("joe", "usa");
        assert id.equals(id2);
        
    }
    
    @Test
    public void testFindAccount1() { 
        Long id = bank.createAccount("joe", "usa");
        assert id!=null;
        Long id2 = bank.findAccount("joe", "usa");
        assert id.equals(id2);
        
    }
    
    @Test
    public void testFindAccount2() { 
        Long id2 = bank.findAccount("joe", "usa");
        assert id2 == null;

    }
    
    @Test
    public void testGetBalance1() { 
        // Verifies if new account has initial balance of zero
        Long id = bank.createAccount("joe", "usa");
        assert bank.getBalance(id).equals(new BigDecimal(0));

    }
    
    @Test
    public void testGetBalance2() { 
        // Verifies if an exception to a selected type (first argument) is thrown from code in lambda expression (second argument)
        Assertions.assertThrows(
          // The type of expected exception
          Bank.AccountIdException.class,         
          () -> {
            // Code that should throw an exception. Here we expect that getBalance throws
            // AccountIdException because there is no Account with id 1L, 'L' is used to convert int constant to long
            BigDecimal balance  = bank.getBalance(1L);
          }
        );

    }
    
    @Test
    public void testDeposit() { 
        Long id = bank.createAccount("joe", "usa");
        bank.deposit(id, new BigDecimal(100));
        assert bank.getBalance(id).equals(BigDecimal.valueOf(100));
        bank.deposit(id, new BigDecimal("100.20"));
        assert bank.getBalance(id).equals(new BigDecimal("200.20"));

    }
    
    @Test
    public void testDeposit2() {
       // TODO: verify if deposit throws AccountIdException when id of non-existing account is passed as argument

        Assertions.assertThrows(
                // The type of expected exception
                Bank.AccountIdException.class,
                () -> {

                    bank.deposit(1L, new BigDecimal("100"));
                }
        );

    }
    
    @Test
    public void testWithdraw1() {
       // TODO: verify if withdraw correctly reduces balance of selected account

        Long id = bank.createAccount("Turan", "Turkey");

        bank.deposit(id, new BigDecimal(200));
        assert bank.getBalance(id).equals(BigDecimal.valueOf(200));
        bank.withdraw(id, new BigDecimal(100));
        assert bank.getBalance(id).equals(BigDecimal.valueOf(100));

        bank.deposit(id, new BigDecimal("100.20"));
        assert bank.getBalance(id).equals(new BigDecimal("200.20"));
        bank.withdraw(id, new BigDecimal("50.20"));
        assert bank.getBalance(id).equals(new BigDecimal("150.00"));

    }
    
    @Test
    public void testWithdraw2() {
       // TODO: verify if withdraw throws AccountIdException when id of non-existing account is passed as argument

        Long id = 1L;

        Assertions.assertThrows(
                // The type of expected exception
                Bank.AccountIdException.class,
                () -> {

                    bank.withdraw(id, new BigDecimal(100));
                }
        );

    }
    
    @Test
    public void testWithdraw3() {
        // TODO: verify if withdraw throws InsufficientFundsException if source account do not have sufficient balance (balance is less that requested amount)

        Long id = bank.createAccount("Turan", "Turkey");

        Assertions.assertThrows(
                // The type of expected exception
                Bank.InsufficientFundsException.class,
                () -> {

                    bank.withdraw(id, new BigDecimal(100));
                }
        );

    }
    
    @Test
    public void testTransfer1() {
        // TODO: verify if transfer correctly transfers money between accounts (source account balance should be decreased, destination balance increased)

        Long idSender = bank.createAccount("Joe", "Usa");
        Long idReceiver = bank.createAccount("Turan", "Turkey");

        bank.deposit(idSender, new BigDecimal(500));
        assert bank.getBalance(idSender).equals(BigDecimal.valueOf(500));

        bank.transfer(idSender, idReceiver, new BigDecimal(200));
        assert bank.getBalance(idSender).equals(BigDecimal.valueOf(300));
        assert bank.getBalance(idReceiver).equals(BigDecimal.valueOf(200));

    }
    
    @Test
    public void testTransfer2() {
        // TODO: verify if transfer throws AccountIdException when id of non-existing account is passed as argument

        Long idSender = 1L;
        Long idReceiver = bank.createAccount("Turan", "Turkey");

        Assertions.assertThrows(
                // The type of expected exception
                Bank.AccountIdException.class,
                () -> {

                    bank.transfer(idSender, idReceiver, new BigDecimal(100));
                }
        );

    }

    @Test
    public void testTransfer21() {
        // TODO: verify if transfer throws AccountIdException when id of non-existing account is passed as argument

        Long idSender = bank.createAccount("Turan", "Turkey");
        Long idReceiver = 2L;

        Assertions.assertThrows(
                // The type of expected exception
                Bank.AccountIdException.class,
                () -> {

                    bank.transfer(idSender, idReceiver, new BigDecimal(100));
                }
        );

    }

    @Test
    public void testTransfer3() {
        // TODO: verify if transfer throws InsufficientFundsException if source account do not have sufficient balance (balance is less that transfer amount)

        Long idSender = bank.createAccount("Joe", "Usa");
        Long idReceiver = bank.createAccount("Turan", "Turkey");

        Assertions.assertThrows(
                // The type of expected exception
                Bank.InsufficientFundsException.class,
                () -> {

                    bank.transfer(idSender, idReceiver, new BigDecimal(100));
                }
        );

    }  
    
}
