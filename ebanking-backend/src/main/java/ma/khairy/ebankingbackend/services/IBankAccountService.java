package ma.khairy.ebankingbackend.services;

import ma.khairy.ebankingbackend.dto.CustomerDto;
import ma.khairy.ebankingbackend.entities.BankAccount;
import ma.khairy.ebankingbackend.entities.CurrentAccount;
import ma.khairy.ebankingbackend.entities.Customer;
import ma.khairy.ebankingbackend.entities.SavingAccount;

import java.util.List;

public interface IBankAccountService {
    CustomerDto saveCustomer(CustomerDto customerDto);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId);
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId);
    List<CustomerDto> listCustomers();
    BankAccount getBankAccount(String accountId);
    void debit(String accountId, double amount, String description);
    void credit(String accountId, double amount, String description);
    void transfer(String accountIdSource, String accountIdDestination, double amount);
    List<BankAccount> bankAccountList();
    CustomerDto getCustomer(Long id);

    CustomerDto updateCustomer(CustomerDto customerDto);

    void deleteCustomer(Long id);
}
