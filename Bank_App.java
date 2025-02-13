import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    private String accountHolder;
    private double balance;
    private TransactionHistory transactionHistory;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new TransactionHistory();
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.addTransaction("Deposit: +" + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.addTransaction("Withdrawal: -" + amount);
        } else {
            System.out.println("Withdrawal failed: Insufficient balance");
        }
    }

    public void showBalance() {
        System.out.printf("Current Balance: $%.2f%n", balance);
    }

    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }

    class TransactionHistory {
        private ArrayList<String> transactions = new ArrayList<>();

        public void addTransaction(String record) {
            transactions.add(record);
        }

        public void showTransactions() {
            System.out.println("\n══════════════════════════════════════");
            System.out.println("        TRANSACTION HISTORY");
            System.out.println("══════════════════════════════════════");
            for (String transaction : transactions) {
                System.out.println("  ➤ " + transaction);
            }
            System.out.println("══════════════════════════════════════\n");
        }

        class TransactionAnalyzer {
            public int countDeposits() {
                return (int) transactions.stream()
                        .filter(t -> t.startsWith("Deposit"))
                        .count();
            }

            public int countWithdrawals() {
                return (int) transactions.stream()
                        .filter(t -> t.startsWith("Withdrawal"))
                        .count();
            }
        }
    }
}

public class Bank_App {
    private static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private static void showHeader() {
        System.out.println("══════════════════════════════════════");
        System.out.println("      SIMPLE BANKING SYSTEM");
        System.out.println("══════════════════════════════════════\n");
    }

    private static void pressEnterToContinue(Scanner scanner) {
        System.out.print("\n↵ Press Enter to continue...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        clearConsole();
        showHeader();

        System.out.print(" Enter account holder name: ");
        String name = scanner.nextLine();

        System.out.print(" Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        BankAccount account = new BankAccount(name, balance);

        while (true) {
            clearConsole();
            showHeader();
            System.out.println(" MAIN MENU");
            System.out.println("══════════════════════════════════════");
            System.out.println(" 1. Deposit Funds");
            System.out.println(" 2. Withdraw Funds");
            System.out.println(" 3. Check Balance");
            System.out.println(" 4. Transaction History");
            System.out.println(" 5. Financial Analysis");
            System.out.println(" 6. Exit System");
            System.out.println("══════════════════════════════════════");
            System.out.print(" Enter your choice (1-6): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    clearConsole();
                    showHeader();
                    System.out.print(" Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    account.deposit(depositAmount);
                    System.out.println("\n ✔ Success: $" + depositAmount + " deposited!");
                    pressEnterToContinue(scanner);
                    break;

                case "2":
                    clearConsole();
                    showHeader();
                    System.out.print(" Enter withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine();
                    account.withdraw(withdrawAmount);
                    System.out.println("\n ✔ Transaction processed!");
                    pressEnterToContinue(scanner);
                    break;

                case "3":
                    clearConsole();
                    showHeader();
                    System.out.println(" ACCOUNT BALANCE");
                    System.out.println("══════════════════════════════════════");
                    account.showBalance();
                    System.out.println("══════════════════════════════════════");
                    pressEnterToContinue(scanner);
                    break;

                case "4":
                    clearConsole();
                    showHeader();
                    account.getTransactionHistory().showTransactions();
                    pressEnterToContinue(scanner);
                    break;

                case "5":
                    clearConsole();
                    showHeader();
                    BankAccount.TransactionHistory.TransactionAnalyzer analyzer = account
                            .getTransactionHistory().new TransactionAnalyzer();
                    System.out.println(" FINANCIAL ANALYSIS");
                    System.out.println("══════════════════════════════════════");
                    System.out.println(" Total Deposits:    " + analyzer.countDeposits());
                    System.out.println(" Total Withdrawals: " + analyzer.countWithdrawals());
                    System.out.println("══════════════════════════════════════");
                    pressEnterToContinue(scanner);
                    break;

                case "6":
                    clearConsole();
                    showHeader();
                    System.out.println(" Thank you for using our banking system!");
                    System.out.println("══════════════════════════════════════\n");
                    scanner.close();
                    return;

                default:
                    clearConsole();
                    showHeader();
                    System.out.println(" ⚠ Invalid choice! Please try again.");
                    pressEnterToContinue(scanner);
            }
        }
    }
}