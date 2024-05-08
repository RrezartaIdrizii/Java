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
    }

    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal number must be positive");
        }
        if (accountBalance < amount) {
            throw new IllegalStateException("Insufficient funds");
        }
        accountBalance -= amount;
    }
    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}
