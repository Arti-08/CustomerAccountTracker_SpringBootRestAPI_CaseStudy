package com.arti08.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arti08.exception.NoDataFoundException;
import com.arti08.model.Customer;
import com.arti08.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService service;

	@PutMapping("/update/{customerId}")
	public ResponseEntity<?> updateEmployee(@PathVariable("customerId") int customerId, @RequestBody Customer customer)
			throws NoDataFoundException {
		Customer c = service.updateCustomer(customerId, customer);
		if (c != null) {
			return new ResponseEntity<>(c, HttpStatus.OK);
		} else {
			throw new NoDataFoundException("Customer Not Found");
		}
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllCustomers() throws NoDataFoundException {
		List<Customer> list = service.getAllCustomers();
		if (!(list.isEmpty())) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			throw new NoDataFoundException("Empty data");
		}
	}

	@GetMapping("/findCustomer/{customerId}")
	public ResponseEntity<?> findCustomerbyId(@PathVariable("customerId") int customerId) throws NoDataFoundException {
		Customer customer = service.findCustomerbyId(customerId);
		if (customer != null) {
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} else {
			throw new NoDataFoundException("Customer not found ");
		}
	}

}
