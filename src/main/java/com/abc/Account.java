package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountNo;
    private final int accountType;
    private int balance;
	private Date latestWithdrawDate;
    public List<Transaction> transactions;

    public Account(int accountNo, int accountType) {
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.balance = 0;
		this.latestWithdrawDate = DateProvider.now();
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
			balance += amount;
        }
    }

public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
        transactions.add(new Transaction(-amount));
		if (amount > balance)
			throw new IllegalArgumentException("amount must be less than the balance");
		balance -= amount;
		latestWithdrawDate = DateProvider.now();
    }
}

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
            case MAXI_SAVINGS:
				Date diff = DateProvider.now() - latestWithdrawDate;
				int numDays = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
				if (numDays > 10):
					return amount * 0.05
				else:
					return amount * 0.001
            default:
                return amount * 0.001;
        }
    }

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }

	public getBalance() { 
		return balance;
	}		
}
