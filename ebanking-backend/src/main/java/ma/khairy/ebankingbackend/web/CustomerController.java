package ma.khairy.ebankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.khairy.ebankingbackend.dto.CustomerDto;
import ma.khairy.ebankingbackend.services.IBankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerController {

    private IBankAccountService bankAccountService;


    @GetMapping("/customers")
    public List<CustomerDto> customers() {
        return bankAccountService.listCustomers();
    }

    @GetMapping("/customers/{id}")
    public CustomerDto getCustomer(@PathVariable Long id) {
        return bankAccountService.getCustomer(id);
    }

    @PostMapping("/customers")
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerDto) {
        return bankAccountService.saveCustomer(customerDto);
    }

    @PutMapping("/customers/{id}")
    public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        customerDto.setId(id);
        return bankAccountService.updateCustomer(customerDto);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        bankAccountService.deleteCustomer(id);
    }
}
