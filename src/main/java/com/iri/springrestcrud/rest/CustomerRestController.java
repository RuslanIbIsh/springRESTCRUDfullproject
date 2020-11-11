package com.iri.springrestcrud.rest;

import com.iri.springrestcrud.entity.Customer;
import com.iri.springrestcrud.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

   @Autowired
    private CustomerService customerService;


    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCusomer(@PathVariable int customerId) {

        Customer customer = customerService.getCustomer(customerId);

        if (customer == null) {
            throw new CustomerNotFoudException(
   "Customer id not found - " + customerId
            );
        }

        return customer;
    }

// add mapping for POST / customers - add new customer
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {
// also just in case the pass an id in JSON ... set id to 0
        // this is force a save of new item... instead of update

        customer.setId(0);
        customerService.saveCustomer(customer);
        return customer;
    }

    // add mapping for PUT /customers update an existing customer
    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return customer;
    }


    // add mapping for DELETE / customers delete customer
    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer(@PathVariable int customerId) {
        Customer customer = customerService.getCustomer(customerId);


        if (customer == null) {
            throw new CustomerNotFoudException(
                    "Customer id not found " + customerId
            );
        }


        customerService.deleteCustomer(customerId);
        return "Deleted customer id - " + customerId;


    }

}
