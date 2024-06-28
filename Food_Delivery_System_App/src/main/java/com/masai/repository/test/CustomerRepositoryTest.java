package com.masai.repository.test;

import com.masai.model.Customer;
import com.masai.repo.CustomerRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class) // Ensure this import is correct
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void whenFindById_thenReturnCustomer() {
        // Given
        Customer customer = new Customer();
        customer.setName("John Doe");
        entityManager.persist(customer);
        entityManager.flush();

        // When
        Optional<Customer> found = customerRepository.findById(customer.getId());

        // Then
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getName()).isEqualTo(customer.getName());
    }
}


