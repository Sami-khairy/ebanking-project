package ma.khairy.ebankingbackend.services.implementations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.khairy.ebankingbackend.entities.*;
import ma.khairy.ebankingbackend.enums.AccountStatus;
import ma.khairy.ebankingbackend.enums.OperationType;
import ma.khairy.ebankingbackend.exceptions.BalanceNotSufficientException;
import ma.khairy.ebankingbackend.exceptions.BankAccountNotFoundException;
import ma.khairy.ebankingbackend.exceptions.CustomerNotFoundException;
import ma.khairy.ebankingbackend.repositories.AccountOperationRepository;
import ma.khairy.ebankingbackend.repositories.BankAccountRepository;
import ma.khairy.ebankingbackend.repositories.CustomerRepository;
import ma.khairy.ebankingbackend.services.IBankAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements IBankAccountService {

    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;


    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new CustomerNotFoundException("Customer not found with ID: " + customerId)
        );

        CurrentAccount bankAccount = new CurrentAccount();
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setBalance(initialBalance);
        bankAccount.setCreatedAt(new Date());
        bankAccount.setCustomer(customer);
        bankAccount.setStatus(AccountStatus.CREATED);
        bankAccount.setOverDraft(overDraft);

        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new CustomerNotFoundException("Customer not found with ID: " + customerId)
        );

        SavingAccount bankAccount = new SavingAccount();
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setBalance(initialBalance);
        bankAccount.setCreatedAt(new Date());
        bankAccount.setCustomer(customer);
        bankAccount.setStatus(AccountStatus.CREATED);
        bankAccount.setInterestRate(interestRate);

        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public BankAccount getBankAccount(String accountId) {
        return bankAccountRepository.findById(accountId).orElseThrow(
                () -> new BankAccountNotFoundException("Bank account not found with ID: " + accountId)
        );
    }

    @Override
    public void debit(String accountId, double amount, String description) {
        BankAccount bankAccount = getBankAccount(accountId);
        if (bankAccount.getBalance() < amount) {
            throw new BalanceNotSufficientException("Insufficient funds");
        }

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setAmount(amount);
        accountOperation.setOperationDate(new Date());
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);

        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountId, double amount, String description) {
        BankAccount bankAccount = getBankAccount(accountId);

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setAmount(amount);
        accountOperation.setOperationDate(new Date());
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);

        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) {
        debit(accountIdSource, amount, "Transfer to " + accountIdDestination);
        credit(accountIdDestination, amount, "Transfer from " + accountIdSource);
    }

    @Override
    public List<BankAccount> bankAccountList() {
        return bankAccountRepository.findAll();
    }
}
