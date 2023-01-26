package com.luv2code.springdemo.service;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerService {

	public void saveCustomer(Customer customer);

	public Customer getCustomer(Integer customerId);

	public void deleteCustomer(Integer customerId);

	public List<Customer> searchCustomers(String searchName);

	public List<Customer> getCustomers();
}
