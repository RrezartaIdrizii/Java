import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private String bankName;
    private Account[] accounts;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFeeAmount;
    private double transactionPercentFeeValue;

    // Constructor
    public Bank(String bankName, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
        this.bankName = bankName;
        this.accounts = new Account[0];
        this.totalTransactionFeeAmount = totalTransactionFeeAmount;
        this.totalTransferAmount = totalTransferAmount;
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
        this.transactionPercentFeeValue = transactionPercentFeeValue;
    }

    // Method to add an account to the bank
    public void addAccount(Account account) {
        Account[] newArray = new Account[accounts.length + 1];
        System.arraycopy(accounts, 0, newArray, 0, accounts.length);
        newArray[accounts.length] = account;
        accounts = newArray;
    }

    // Method to perform a transaction
    public void performTransaction(Transaction transaction) {
        double amount = transaction.getAmount();
        double transactionFee;

        // Calculate transaction fee based on flat or percent fee
        if (transaction.getTransactionType() == TransactionType.WITHDRAWAL) {
            transactionFee = calculateTransactionFee(amount);
        } else {
            transactionFee = 0; // No fee for deposit transactions
        }

        // Deduct transaction fee from the transaction amount
        double amountAfterFee = amount - transactionFee;

        // Update bank's total transaction fee amount
        totalTransactionFeeAmount += transactionFee;
        Account originatingAccount = findAccountById(transaction.getOriginatingAccountId());
        Account resultingAccount = findAccountById(transaction.getResultingAccountId());
    
        if (originatingAccount != null && resultingAccount != null) {
            if (transaction.getTransactionType() == TransactionType.WITHDRAWAL) {
                // Check if the originating account has sufficient funds
                if (originatingAccount.getAccountBalance() >= amount + transactionFee) {
                    originatingAccount.withdraw(amount + transactionFee);
                    resultingAccount.deposit(amountAfterFee);
                    // Update total transfer amount
                    totalTransferAmount += amountAfterFee;
                } else {
                    System.out.println("Error: Insufficient funds in the originating account");
                }
            } else {
                originatingAccount.withdraw(amount + transactionFee);
                resultingAccount.deposit(amountAfterFee);
                // Update total transfer amount
                totalTransferAmount += amountAfterFee;
            }
        } else {
            System.out.println("Error: One or both of the accounts do not exist");
        }
        
    }
    private double calculateTransactionFee(double amount) {
        if (transactionPercentFeeValue > 0) {
            return amount * (transactionPercentFeeValue / 100.0);
        } else {
            return transactionFlatFeeAmount;
        }
    }
    private Account findAccountById(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }
    // Method to calculate total balance across all accounts
    public double getTotalBalance() {
        double totalBalance = 0;
        for (Account account : accounts) {
            totalBalance += account.getAccountBalance();
        }
        return totalBalance;
    }

    // Getters and Setters
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public void setTotalTransactionFeeAmount(double totalTransactionFeeAmount) {
        this.totalTransactionFeeAmount = totalTransactionFeeAmount;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public void setTotalTransferAmount(double totalTransferAmount) {
        this.totalTransferAmount = totalTransferAmount;
    }

    public double getTransactionFlatFeeAmount() {
        return transactionFlatFeeAmount;
    }

    public void setTransactionFlatFeeAmount(double transactionFlatFeeAmount) {
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
    }

    public double getTransactionPercentFeeValue() {
        return transactionPercentFeeValue;
    }

    public void setTransactionPercentFeeValue(double transactionPercentFeeValue) {
        this.transactionPercentFeeValue = transactionPercentFeeValue;
    }

    public Account[] getAccounts() {
        return accounts;
    }
}
