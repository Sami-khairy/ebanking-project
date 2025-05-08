package ma.khairy.ebankingbackend.web;

import lombok.AllArgsConstructor;
import ma.khairy.ebankingbackend.dto.AccountHistoryDto;
import ma.khairy.ebankingbackend.dto.AccountOperationDto;
import ma.khairy.ebankingbackend.dto.BankAccountDto;
import ma.khairy.ebankingbackend.services.IBankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountController {

    private IBankAccountService bankAccountService;

    @GetMapping("/accounts/{accountId}")
    public BankAccountDto getBankAccount(String accountId) {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    public List<BankAccountDto> bankAccountList() {
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public List<AccountOperationDto> accountHistory(@PathVariable String accountId) {
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDto accountHistory(@PathVariable String accountId,
                                            @RequestParam(name = "page", defaultValue = "0") int page,
                                            @RequestParam(name = "size", defaultValue = "10") int size) {
        return bankAccountService.getAccountHistory(accountId, page, size);
    }
}
