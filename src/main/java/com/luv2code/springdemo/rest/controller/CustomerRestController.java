package com.luv2code.springdemo.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		List<Customer> theCustomers = customerService.getCustomers();
		return theCustomers;
	}

	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		Customer tmpCustomer = customerService.getCustomer(customerId);

		if (null == tmpCustomer) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}

		return tmpCustomer;
	}

	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {

		// null or zero results in creating a new customer in the database
		theCustomer.setId(0);

		customerService.saveCustomer(theCustomer);

		return theCustomer;

	}

	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		customerService.saveCustomer(theCustomer);

		return theCustomer;
	}

	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {

		Customer tmpCustomer = customerService.getCustomer(customerId);

		if (null == tmpCustomer) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}

		customerService.deleteCustomer(customerId);

		return "Deleted customer id - " + customerId;
	}

}
