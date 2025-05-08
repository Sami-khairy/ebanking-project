package ma.khairy.ebankingbackend.services;

import ma.khairy.ebankingbackend.entities.BankAccount;
import ma.khairy.ebankingbackend.entities.CurrentAccount;
import ma.khairy.ebankingbackend.entities.Customer;
import ma.khairy.ebankingbackend.entities.SavingAccount;

import java.util.List;

public interface IBankAccountService {
    Customer saveCustomer(Customer customer);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId);
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId);
    List<Customer> listCustomers();
    BankAccount getBankAccount(String accountId);
    void debit(String accountId, double amount, String description);
    void credit(String accountId, double amount, String description);
    void transfer(String accountIdSource, String accountIdDestination, double amount);
    List<BankAccount> bankAccountList();
}
