import java.util.ArrayList;
import java.util.List;
public class Account {
    private String accountId;
    private String userName;
    private double accountBalance;
    private List<Transaction> transactionHistory;

    public Account(String accountId, String userName, double accountBalance){
        this.accountId=accountId;
        this.userName=userName;
        this.accountBalance=accountBalance;
        this.transactionHistory = new ArrayList<>();
    }
    public String getAccountId(){
        return accountId;
    }
    public String getUserName(){
        return userName;
    }
    public double getAccountBalance(){
        return accountBalance;
    }
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit number must be positive");
        }
        accountBalance += amount;
        double feeAmount = calculateTransactionFee(amount); 
        accountBalance -= (amount + feeAmount);
        transactionHistory.add(new Transaction(amount, accountId, "Deposit", "Regular Deposit", TransactionType.DEPOSIT, feeAmount));
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal number must be positive");
        }
        if (accountBalance < amount) {
            throw new IllegalStateException("Insufficient funds");
        }
        
        double feeAmount = calculateTransactionFee(amount); // Calculate the fee amount
    accountBalance -= (amount + feeAmount);
        transactionHistory.add(new Transaction(amount, accountId, "Withdrawal", "Regular withdrawal", TransactionType.WITHDRAWAL, feeAmount));
    }

    private double calculateTransactionFee(double amount) {
        double feeAmount = amount * 0.01;
        return feeAmount;
    }
    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}
