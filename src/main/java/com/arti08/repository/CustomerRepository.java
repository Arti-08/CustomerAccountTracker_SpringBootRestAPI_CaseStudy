package com.arti08.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.arti08.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
