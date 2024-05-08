import java.util.Scanner;
public class BankSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = null;
        Account account=null;

        while (true) {
            // Display menu
            System.out.println("\n********** Welcome to Bank System  **********");
            System.out.println("1. Create a Bank");
            System.out.println("2. Create an Account");
            System.out.println("3. Perform Transaction");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. List Transactions for an Account");
            System.out.println("7. Check Account Balance");
            System.out.println("8. List Bank Accounts");
            System.out.println("9. Check Bank Total Transaction Fee Amount");
            System.out.println("10. Check Bank Total Transfer Amount");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter bank name: ");
                    String bankName = scanner.nextLine();
                    System.out.print("Enter transaction flat fee amount: $");
                    double transactionFlatFeeAmount = scanner.nextDouble();
                    System.out.print("Enter transaction percent fee value (%): ");
                    double transactionPercentFeeValue = scanner.nextDouble();
                     bank = new Bank( bankName,  transactionFlatFeeAmount,  transactionPercentFeeValue);
                    System.out.println("Bank created successfully.");
                    break;
                case 2:
                    if (bank == null) {
                        System.out.println("Error: Bank not created yet.");
                        break;
                    }
                    System.out.print("Enter account ID: ");
                    String accountId = scanner.nextLine();
                    System.out.print("Enter account username: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter account balance: ");
                    double accountBalance = scanner.nextDouble();
                    bank.addAccount(new Account(accountId,userName,accountBalance));
                    System.out.println("Account created successfully.");
                    break;
                case 3:
                    if (bank == null && account == null) {
                        System.out.println("Error: Bank and Account are not created yet.");
                        break;
                    }
                    performTransaction(scanner, bank);
                    break;
                case 4:
                    if (bank == null) {
                        System.out.println("Error: Bank not created yet.");
                        break;
                    }
                    depositMoney(scanner, bank);
                    break;
                case 5:
                    if (bank == null) {
                        System.out.println("Error: Bank not created yet.");
                        break;
                    }
                    withdrawMoney(scanner, bank);
                    break;
                case 6:
                    if (bank == null) {
                        System.out.println("Error: Bank not created yet.");
                        break;
                    }
                    listTransactions(scanner, bank);
                    break;
                case 7:
                    if (bank == null) {
                        System.out.println("Error: Bank not created yet.");
                        break;
                    }
                    checkAccountBalance(scanner, bank);
                    break;
                case 8:
                    if (bank == null) {
                        System.out.println("Error: Bank not created yet.");
                        break;
                    }
                    listBankAccounts(bank);
                    break;
                case 9:
                    if (bank == null) {
                        System.out.println("Error: Bank not created yet.");
                        break;
                    }
                    System.out.println("Bank Total Transaction Fee Amount: $" + bank.getTotalTransactionFeeAmount());
                    break;
                case 10:
                    if (bank == null) {
                        System.out.println("Error: Bank not created yet.");
                        break;
                    }
                    System.out.println("Bank Total Transfer Amount: $" + bank.getTotalTransferAmount());
                    break;
                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void performTransaction(Scanner scanner, Bank bank) {
        System.out.print("Enter originating account ID: ");
        String originatingAccountId = scanner.nextLine();
        System.out.print("Enter resulting account ID: ");
        String resultingAccountId = scanner.nextLine();
        System.out.print("Enter amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Transaction transaction = new Transaction(amount, originatingAccountId, resultingAccountId, TransactionType.TRANSFER);
        bank.performTransaction(transaction);
        System.out.println("Transaction performed successfully.");
    }

    private static void depositMoney(Scanner scanner, Bank bank) {
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Account account = findAccountById(accountId, bank);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Money deposited successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawMoney(Scanner scanner, Bank bank) {
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Account account = findAccountById(accountId, bank);
        if (account != null) {
            account.withdraw(amount);
            System.out.println("Money withdrawn successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void listTransactions(Scanner scanner, Bank bank) {
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();

        Account account = findAccountById(accountId, bank);
        if (account != null) {
            System.out.println("Transaction History for Account " + accountId + ":");
            for (Transaction transaction : account.getTransactionHistory()) {
                System.out.println(transaction.getTransactionType() + ": $" + transaction.getAmount());
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkAccountBalance(Scanner scanner, Bank bank) {
        System.out.print("Enter account ID: ");
        String accountId = scanner.nextLine();

        Account account = findAccountById(accountId, bank);
        if (account != null) {
            System.out.println("Account Balance for Account " + accountId + ": $" + account.getAccountBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static Account findAccountById(String accountId, Bank bank) {
        for (Account account : bank.getAccounts()) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    private static void listBankAccounts(Bank bank) {
        System.out.println("Bank Accounts:");
        for (Account account : bank.getAccounts()) {
            System.out.println("Account ID: " + account.getAccountId() + ", Balance: $" + account.getAccountBalance());
        }
    }

}
