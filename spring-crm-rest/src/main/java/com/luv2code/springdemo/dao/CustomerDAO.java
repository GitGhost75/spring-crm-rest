package com.luv2code.springdemo.dao;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerDAO {

	public void saveCustomer(Customer customer);

	public Customer getCustomer(Integer customerId);

	public void deleteCustomer(Integer customerId);

	public List<Customer> searchCustomers(String searchName);

	public List<Customer> getCustomers();
}
