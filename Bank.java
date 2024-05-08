
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
        this.totalTransactionFeeAmount = 0.0;
        this.totalTransferAmount = 0.0;
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
        // // Perform the transaction between accounts
        // for (Account account : accounts) {
        //     if (account.getAccountId().equals(transaction.getOriginatingAccountId())) {
        //         // Withdraw amount from the originating account
        //         account.withdraw(amount);
        //     }
        //     if (account.getAccountId().equals(transaction.getResultingAccountId())) {
        //         // Deposit amount to the resulting account
        //         account.deposit(amountAfterFee);
        //     }
        // }

        // // Update total transfer amount
        // totalTransferAmount += amountAfterFee;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccounts'");
    }
}
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         System.out.println(" **** Welcome to Bank System **** ");
//         System.out.println("1. Create a Bank");
//         System.out.println("2. Create an Account");
//         System.out.println("3. Exit");
//         System.out.print("Choose an option: ");
        
//         // Get user's choice
//         int choice = scanner.nextInt();

//         // Process user's choice
//         switch (choice) {
//             case 1:
//                 createBank(scanner);
//                 break;
//             case 2:
//                 System.out.println("Exiting...");
//                 break;
//             default:
//                 System.out.println("Invalid choice. Please try again.");
//                 break;
//         }

//         scanner.close();
//     }
//     // Method to create a bank
//     public static void createBank(Scanner scanner) {
//         // Get bank name from the user
//         System.out.print("Enter bank name: ");
//         String bankName = scanner.nextLine();

//         // Get transaction flat fee amount from the user
//         System.out.print("Enter transaction flat fee amount: $");
//         double transactionFlatFeeAmount = scanner.nextDouble();

//         // Get transaction percent fee value from the user
//         System.out.print("Enter transaction percent fee value (%): ");
//         double transactionPercentFeeValue = scanner.nextDouble();

//         // Create a bank with user-provided values
//         Bank bank = new Bank(bankName, transactionFlatFeeAmount, transactionPercentFeeValue);

//         // Display bank details
//         System.out.println("Bank created successfully!");
//         System.out.println("Bank Name: " + bank.getBankName());
//         System.out.println("Transaction Flat Fee Amount: $" + bank.getTransactionFlatFeeAmount());
//         System.out.println("Transaction Percent Fee Value: " + bank.getTransactionPercentFeeValue() + "%");
//     }

//     //     // Create a bank with flat fee of $10 and percent fee of 2%
//     //     Bank bank = new Bank("MyBank", 10.0, 2.0);

//     //     // Create some accounts
//     //     Account account1 = new Account(1, "filan.fisteku");
//     //     Account account2 = new Account(2, "filan_fisteku");

//     //     // Add accounts to the bank
//     //     bank.addAccount(account1);
//     //     bank.addAccount(account2);

//     //     // Display initial total balance in the bank
//     //     System.out.println("Initial total balance in the bank: $" + bank.getTotalBalance());

//     //     // Perform a transaction: transfer $200 from account1 to account2
//     //     Transaction transaction = new Transaction(200.0, "123456", "789012", "Transfer",TransactionType.DEPOSIT);
//     //     bank.performTransaction(transaction);

//     //     // Display total balance in the bank after the transaction
//     //     System.out.println("Total balance in the bank after transfer: $" + bank.getTotalBalance());

//     //     // Display total transfer amount and total transaction fee amount
//     //     System.out.println("Total transfer amount: $" + bank.getTotalTransferAmount());
//     //     System.out.println("Total transaction fee amount: $" + bank.getTotalTransactionFeeAmount());
//     // }
// }
