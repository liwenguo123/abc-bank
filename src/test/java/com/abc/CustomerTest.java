package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new Account(Account.CHECKING);
        Account savingsAccount = new Account(Account.SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test //Test customer statement generation
	public void testApp(String name, ArrayList<Account> accounts, ArrayList<ArrayList<double>> amounts) {
		Customer customer = new Customer(name);
		String stmt = "";
		int i = 0;
		for (Account account : accounts) {
			customer.openAccount(account);
			String atype="";
			if (account.accountType == Account.CHECKING)
				atype = "Checking";
			else if (account.accountType == Account.SAVINGS)
				atype = "Saving";
			else if (account.accountType == Account.MAXI_SAVINGS)
				atype = "Maxi Savings";
			stmt += "\n\n“ + atype + " Account\n";
			for (ArrayList<double> accountAmounts : amounts[i]) {
				for (double amount : accountAmounts) {
					if (amount > 0) {
						account.deposit(amount);
						stmt += " deposit " + _toDollars(abs(amount));
					}
					else if (amount < 0) {
						account.withdraw(-amount);
						stmt += " withdraw " + _toDollars(abs(amount));
					}
				}
			}
			stmt += "\nTotal" + _toDollars(abs(account.balance));
			i++;
		}
		stmt += "\n\nTotal In All Accounts " + _toDollars(customer.getTotalBalance());
		
		assert_equals(customer.getStatement(), stmt); 
	}
	
    @Test
	public void testApp_call() {
		String name = "Henry";
		int anum = 12345;
		List<Account> accounts = new ArrayList<Account>();
		ArrayList<int> atypes = Stream.of(Account.CHECKING, Account.SAVINGS, Account.MAXI_SAVINGS).collect(Collectors.toList());
		for (int atype : atypes) {
			Account account = new Account(anum, atype);
			accounts.add(account);
		}
		ArrayList<double> camount = Stream.of(100.0, -50.0, -30.0).collect(Collectors.toList());
		ArrayList<double> samount = Stream.of(100.0, -50.0, 10.0).collect(Collectors.toList());
		ArrayList<double> mamount = Stream.of(1000.0, -500.0, 2000.0).collect(Collectors.toList());
		ArrayList<ArrayList<double>> amounts = Stream.of(camount, samount, mamount).collect(Collectors.toList());
		testApp(name, accounts, amounts);
	}

    @Test
	public void testMultipleAccount(String customerName, ArrayList<Account> accounts) {
		customer = new Customer(customerName);
		for (Account account : accounts)
			customer.openAccount(account); 
		assert_equals(customer.numAccs(), accounts.size()); 
	}
	
    @Test
	public void testMultipleAccount_call() { 
		String name = "John";
		int anum = 12345;
		List<Account> accounts = new ArrayList();
		ArrayList<int> atypes = Stream.of(Account.CHECKING, Account.SAVINGS, Account.MAXI_SAVINGS).collect(Collectors.toList());
		for (int atype : atypes) {
			Account account = new Account(anum, atype);
			accounts.add(account);
		}
		testMultipleAccount(name, accounts);
	}
	
}
