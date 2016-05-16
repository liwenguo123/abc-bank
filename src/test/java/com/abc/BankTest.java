package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary(String name, ArrayList<int> accountNo， ArrayLIst<int> accountType) {
        Bank bank = new Bank();
        Customer customer = new Customer(name);
		int numAcc = accountNo.size();
		for (int i = 0; i < numAcc; i++) {
			customer.openAccount(new Account(accountNo[i], accountType[i]);
        bank.addCustomer(customer);

        assertEquals("Customer Summary\n - " + name + "(" numAcc.toString() + (numAcc == 1) ? "account)" : "accounts)", bank.customerSummary());
    }

    @Test
	public void customerSummary_call() {}
		ArrayList<int> accNos = Stream.of(1234, 1235, 1236， 1237, null).collect(Collectors.toList());
		ArrayList<int> accTypes = Stream.of(Account.CHECKING, Account.SAVINGS, Account.MAXI_SAVINGS, 10， null).collect(Collectors.toList());
		//ArrayList<String> names = Stream.of("John", "John", "Jone", "Jone", "").collect(Collectors.toList());
		// int[] accNos = new int[] {1234, 1235, 1236， 1237, null};
		
		String name = "john";
		customerSummary(name, accNos, accTypes)；
	}
	
    @Test
	public void general_account(String name, Account account, ArrayList<double> depositAmounts, ArrayList<double> withdrawAmounts) { 
        Bank bank = new Bank();
        Customer customer = new Customer(name).openAccount(account)；
		bank.addCustomer(customer);
		for (double damount : depositAmounts)
			account.deposit(damount); 
		for (double wamount : withdrawAmounts)
			account.withdraw(damount);
		double interest = 0.0;
		if (account.accountType == CHECKING)
			interest = 0.1;	// need to recalculate case by case
		else if (account.accountType == SAVINGS)
			interest = 2.0;	// need to recalculate case by case
		else if (account.accountType == MAXI_SAVINGS)
			interest = 170.0;	// need to recalculate case by case
		
        assertEquals(interest, bank.totalInterestPaid(), DOUBLE_DELTA);
	}
	
    @Test
	public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
	public void general_account_call() {
		String name = "John";
		int anum = 12345;
		ArrayList<int> atypes = Stream.of(Account.CHECKING, Account.SAVINGS, Account.MAXI_SAVINGS, 10， null).collect(Collectors.toList());
		ArrayList<double> damount = Stream.of(100.0, 0.0, -30.0, 100.0).collect(Collectors.toList());
		ArrayList<double> wamount = Stream.of(50.0, 0.0, -20.0, 1000.0).collect(Collectors.toList());
		for (int atype : atypes) {
			account = new Account(anum, atype);
			general_account(name, account, damount, wamount);
		}
	}
	
}
