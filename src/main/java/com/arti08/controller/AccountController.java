package com.arti08.controller;

	import java.util.List;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.PutMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RestController;

    import com.arti08.exception.InValidAmount;
	import com.arti08.exception.NoDataFoundException;
	import com.arti08.exception.ResourceNotFoundException;
	import com.arti08.model.Account;
	import com.arti08.service.AccountService;

	@RestController
	@RequestMapping("/account")
	public class AccountController {

		@Autowired
		AccountService accountService;

		@PostMapping("/add")
		public ResponseEntity<?> addAccount(@RequestBody Account acc) throws NoDataFoundException {
			Account msg = accountService.addAccount(acc);
			if (msg != null) {
				return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
			} else {
				throw new NoDataFoundException("Empty data");
			}
		}

		//SaveALL()
		@PostMapping("/addAll")
		public ResponseEntity<?> addListAccount(@RequestBody List<Account> acc) throws NoDataFoundException {
			List<Account> list = (List<Account>) accountService.addListAccount(acc);
			if (list != null) {
				return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
			} else {
				throw new NoDataFoundException("Empty data");
			}
		}
		
		@PutMapping("/update/{accountNumber}")
		public ResponseEntity<?> updateAccount(@PathVariable("accountNumber") int accountNumber,
				@RequestBody Account account) throws NoDataFoundException {
			Account acc = accountService.updateAccount(accountNumber, account);
			if (acc != null) {
				return new ResponseEntity<>(acc, HttpStatus.OK);
			} else {
				throw new NoDataFoundException("Account not found ");
			}
		}

		@GetMapping("/getAllAccount")
		public ResponseEntity<?> getAllAccounts() throws NoDataFoundException {

			List<Account> list = accountService.getAllAccounts();
			if (!(list.isEmpty())) {
				return new ResponseEntity<>(list, HttpStatus.OK);
			} else {
				throw new NoDataFoundException("No data available");
			}
		}

		@GetMapping("/findAccountById/{accountNumber}")
		public ResponseEntity<?> findAccountById(@PathVariable("accountNumber") int accountNumber)
				throws NoDataFoundException {
			Account account = accountService.findAccountById(accountNumber);
			if (account != null) {
				return new ResponseEntity<>(account, HttpStatus.OK);
			} else {
				throw new NoDataFoundException("Account not found ");
			}
		}

		@GetMapping("/accountBytype/{accountType}")
		public ResponseEntity<?> accountByType(@PathVariable("accountType") String accountType) throws NoDataFoundException {
			List<Account> list = accountService.accountByType(accountType);
			if (!(list.isEmpty())) {
				return new ResponseEntity<>(list, HttpStatus.OK);
			} else {
				throw new NoDataFoundException("No Data found");
			}
		}

		@GetMapping("/getBalanceOf/{accountNumber}")
		public ResponseEntity<?> getBalanceOf(@PathVariable("accountNumber") int accountNumber) throws NoDataFoundException {
			String account = accountService.getBalanceOf(accountNumber);
			if (account != null) {
				return new ResponseEntity<>(account, HttpStatus.OK);
			} else {
				throw new NoDataFoundException("No Data found");
			}
		}

		@DeleteMapping("/delete/{accountNumber}")
		public ResponseEntity<?> deleteAccount(@PathVariable("accountNumber") int accountNumber) throws NoDataFoundException {
			String msg = accountService.deleteAccount(accountNumber);
			if (msg != null) {
				return new ResponseEntity<>("Deleted", HttpStatus.OK);
			} else {
				throw new NoDataFoundException("Account Not Found");
			}
		}

		@PutMapping("/transfer/{from}/{to}/{amount}")
		public ResponseEntity<?> transferFunds(@PathVariable("from") int from, @PathVariable("to") int to,
				@PathVariable("amount") double amount) throws ResourceNotFoundException, InValidAmount {

			if (amount >= 1) {
				String msg = accountService.transferFunds(from, to, amount);
				if (msg != null) {
					return new ResponseEntity<>(msg, HttpStatus.OK);
				} else {
					throw new ResourceNotFoundException("Resourcce not found");
				}
			} else {
				throw new InValidAmount("Enter valid amount");
			}
		}

		@PutMapping("/deposite/{amount}/{accountNumber}")
		public ResponseEntity<?> deposite(@PathVariable("amount") double amount,
				@PathVariable("accountNumber") int accountNumber) throws ResourceNotFoundException, NoDataFoundException {

			Account account = accountService.findAccountById(accountNumber);
			if (account != null) {
				String msg = accountService.deposite(amount, accountNumber);
				if (msg != null) {
					return new ResponseEntity<>(msg, HttpStatus.OK);
				} else {
					throw new ResourceNotFoundException("Not able to deposite");
				}
			} else {
				throw new NoDataFoundException("No data available");
			}
		}

		@PutMapping("/withdraw/{amount}/{accountNumber}")
		public ResponseEntity<?> withdraw(@PathVariable("amount") double amount,
				@PathVariable("accountNumber") int accountNumber) throws ResourceNotFoundException, NoDataFoundException {
			Account account = accountService.findAccountById(accountNumber);
			if (account != null) {
				String msg = accountService.withdraw(amount, accountNumber);
				if (msg != null) {
					return new ResponseEntity<>(msg, HttpStatus.OK);
				} else {
					throw new ResourceNotFoundException("Not able to deposite");
				}
			} else {
				throw new NoDataFoundException("No data available");
			}

		}

		@DeleteMapping("/deleteAll")
		public ResponseEntity<?> deleteAll() throws NoDataFoundException {

			List<Account> account = accountService.getAllAccounts();
			if (!(account.isEmpty())) {
				accountService.deleteAll();
				return new ResponseEntity<>("Deleted All Records", HttpStatus.OK);
			} else {
				throw new NoDataFoundException("No data available");
			}

		}
	}
