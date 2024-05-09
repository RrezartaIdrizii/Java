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

    public Bank(String bankName, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
        this.bankName = bankName;
        this.accounts = new Account[0];
        this.totalTransactionFeeAmount = 0.0;
        this.totalTransferAmount = 0.0;
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
        this.transactionPercentFeeValue = transactionPercentFeeValue;
    }

    public void addAccount(Account account) {
        Account[] newArray = new Account[accounts.length + 1];
        System.arraycopy(accounts, 0, newArray, 0, accounts.length);
        newArray[accounts.length] = account;
        accounts = newArray;
    }

    public void performTransaction(Transaction transaction) {
        double amount = transaction.getAmount();
        double transactionFee;

        if (transaction.getTransactionType() == TransactionType.WITHDRAWAL) {
            transactionFee = calculateTransactionFee(amount);
        } else {
            transactionFee = 0; 
        }


        double amountAfterFee = amount - transactionFee;


        totalTransactionFeeAmount += transactionFee;
        
        Account originatingAccount = findAccountById(transaction.getOriginatingAccountId());
        Account resultingAccount = findAccountById(transaction.getResultingAccountId());
    
        if (originatingAccount != null && resultingAccount != null) {
            if (transaction.getTransactionType() == TransactionType.WITHDRAWAL) {
                if (originatingAccount.getAccountBalance() >= amount + transactionFee) {
                    originatingAccount.withdraw(amount + transactionFee);
                    resultingAccount.deposit(amountAfterFee);

                    totalTransferAmount += amountAfterFee;
                } else {
                    System.out.println("Error: Insufficient funds in the originating account");
                }
            } else {
                originatingAccount.withdraw(amount + transactionFee);
                resultingAccount.deposit(amountAfterFee);
                totalTransferAmount += amountAfterFee;
            }
        } else {
            System.out.println("Error: One or both of the accounts do not exist");
        }
        
    }
    double calculateTransactionFee(double amount) {
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

    public double getTotalBalance() {
        double totalBalance = 0;
        for (Account account : accounts) {
            totalBalance += account.getAccountBalance();
        }
        return totalBalance;
    }

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
