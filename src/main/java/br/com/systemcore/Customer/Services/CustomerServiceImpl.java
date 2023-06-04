package br.com.systemcore.Customer.Services;

import br.com.systemcore.Auth.DTO.RegisterRequest;
import br.com.systemcore.Customer.DTO.CustomerResponse;
import br.com.systemcore.Customer.Models.Customer;
import br.com.systemcore.Customer.Repositories.CustomerRepository;
import br.com.systemcore.Customer.Repositories.ICustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomer {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<CustomerResponse> listCustomers() {
        List<Customer> clientes  = customerRepository.findAll();
        List<CustomerResponse> customerResponsesList =
                clientes.stream()
                        .map(cliente -> new CustomerResponse(cliente))
                        .collect(Collectors.toList());
        return customerResponsesList;
    }
    @Override
    public Optional<Customer> findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer registerCustomer(RegisterRequest requestRegistro) {
        Customer customer = new Customer();
        customer.setName(requestRegistro.getName());
        customer.setPassword(requestRegistro.getPassword());
        customer.setEmail(requestRegistro.getEmail());
        customer.setBirthDate(requestRegistro.getBirthDate());
        customer.setPersonNrDocument(requestRegistro.getPersonNrDocument());
        customer.setCompanyNrDocument(requestRegistro.getCompanyNrDocument());
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
