package io.upepo.baharirestapi.controller;


import io.upepo.baharirestapi.exception.ResourceNotFoundException;
import io.upepo.baharirestapi.model.Customer;
import io.upepo.baharirestapi.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('CUSTOMER_READ')")
    public ResponseEntity<?> getAllCustomers()
    {
         return ResponseEntity.ok( customerRepository.findAll());
    }

    @PreAuthorize("hasAuthority('CUSTOMER_READ')")
    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable(value="id") Long customerId) throws  ResourceNotFoundException
    {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        return ResponseEntity.ok(customer);
    }

    @PreAuthorize("hasAuthority('CUSTOMER_CREATE')")
    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer)
    {
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    @PreAuthorize("hasAuthority('CUSTOMER_UPDATE')")
    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable(value="id") Long customerId, @Valid @RequestBody Customer updatedCustomer) throws ResourceNotFoundException
    {
        Customer customerInDB = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customerInDB.setCustomerType(updatedCustomer.getCustomerType());
        customerInDB.setName(updatedCustomer.getName());
        customerInDB.setIdNumber(updatedCustomer.getIdNumber());
        customerInDB.setPinNumber(updatedCustomer.getPinNumber());
        customerInDB.setPhone(updatedCustomer.getPhone());
        customerInDB.setPostalAddress(updatedCustomer.getPostalAddress());
        customerInDB.setPostalCode(updatedCustomer.getPostalCode());
        customerInDB.setEmail(updatedCustomer.getEmail());

        return ResponseEntity.ok(customerRepository.save(customerInDB));

    }

    @DeleteMapping("/customers/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER_DELETE')")
    public ResponseEntity<?>deleteCustomer(@PathVariable(value="id") Long customerId) throws ResourceNotFoundException
    {
        Customer customerInDB = customerRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customerRepository.delete(customerInDB);

        return ResponseEntity.ok("Deleted");
    }

}
