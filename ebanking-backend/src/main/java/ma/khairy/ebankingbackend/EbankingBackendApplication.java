package ma.khairy.ebankingbackend;

import ma.khairy.ebankingbackend.entities.AccountOperation;
import ma.khairy.ebankingbackend.entities.CurrentAccount;
import ma.khairy.ebankingbackend.entities.Customer;
import ma.khairy.ebankingbackend.entities.SavingAccount;
import ma.khairy.ebankingbackend.enums.AccountStatus;
import ma.khairy.ebankingbackend.enums.OperationType;
import ma.khairy.ebankingbackend.repositories.AccountOperationRepository;
import ma.khairy.ebankingbackend.repositories.BankAccountRepository;
import ma.khairy.ebankingbackend.repositories.CustomerRepository;
import ma.khairy.ebankingbackend.services.IBankAccountService;
import ma.khairy.ebankingbackend.services.implementations.BankAccountServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }

//    @Bean
    CommandLineRunner commandLineRunner(
            BankAccountRepository bankAccountRepository,
            CustomerRepository customerRepository,
            AccountOperationRepository accountOperationRepository) {
        return args -> {
            Stream.of("Ahmed", "Said", "Fatima", "Nour").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(customer -> {
                    CurrentAccount currentAccount = new CurrentAccount();
                    currentAccount.setId(UUID.randomUUID().toString());
                    currentAccount.setBalance(Math.random() * 90000);
                    currentAccount.setCreatedAt(new Date());
                    currentAccount.setCustomer(customer);
                    currentAccount.setStatus(AccountStatus.CREATED);
                    currentAccount.setOverDraft(9000);
                    bankAccountRepository.save(currentAccount);

                    SavingAccount savingAccount = new SavingAccount();
                    savingAccount.setId(UUID.randomUUID().toString());
                    savingAccount.setBalance(Math.random() * 90000);
                    savingAccount.setCreatedAt(new Date());
                    savingAccount.setCustomer(customer);
                    savingAccount.setStatus(AccountStatus.CREATED);
                    savingAccount.setInterestRate(5.5);
                    bankAccountRepository.save(savingAccount);

            });

            bankAccountRepository.findAll().forEach(account -> {
                for (int i = 0; i < 10; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setAmount(Math.random() * 12000);
                    accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setBankAccount(account);
                    accountOperationRepository.save(accountOperation);
                }
            });
        };
    }

//    @Bean
    CommandLineRunner commandLineRunner2(
            IBankAccountService bankAccountService) {
        return args -> {
            Stream.of("Hassan", "Imane", "Sami").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(
                    customer -> {
                        bankAccountService.saveCurrentBankAccount(10000, 2000, customer.getId());
                        bankAccountService.saveSavingBankAccount(20000, 5.5, customer.getId());
                        bankAccountService.bankAccountList().forEach(
                                account -> {
                                    for (int i = 0; i < 10; i++) {
                                        bankAccountService.credit(account.getId(), Math.random() * 900, "credit");
                                        bankAccountService.debit(account.getId(), Math.random() * 900, "debit");
                                    }
                                }
                        );
                    }
            );
        };
    }
}
