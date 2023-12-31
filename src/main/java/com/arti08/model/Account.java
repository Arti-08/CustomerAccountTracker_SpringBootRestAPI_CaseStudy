package com.arti08.model;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int accountNumber;
	
	String accountType;
	double balanceAmount;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cid")
	Customer customer;

	
	
	public Account() {
		super();
	}

	public Account(int accountNumber, String accountType, double balanceAmount, Customer customer) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balanceAmount = balanceAmount;
		this.customer = customer;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}

