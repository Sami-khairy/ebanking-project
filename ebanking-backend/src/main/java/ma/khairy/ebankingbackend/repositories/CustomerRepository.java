package ma.khairy.ebankingbackend.repositories;

import ma.khairy.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameContaining(String keyword);
}
