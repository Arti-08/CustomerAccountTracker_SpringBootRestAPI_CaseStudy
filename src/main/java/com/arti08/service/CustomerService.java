package com.arti08.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.arti08.model.Customer;

@Service
public interface CustomerService {

	public Customer updateCustomer(int customerId, Customer customer);

	public List<Customer> getAllCustomers();

	public Customer findCustomerbyId(int customerId);

	//public String deleteCustomer(int customerId);
}
