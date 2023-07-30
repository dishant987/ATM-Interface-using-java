import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private double balance;
    private List<Transaction> transactionHistory;

    public ATM(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdraw", amount));
            System.out.println("Withdrawal successful: " + amount + " remaining balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Deposit successful: " + amount + " new balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void transfer(double amount, ATM destinationAccount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            destinationAccount.deposit(amount);
            transactionHistory.add(new Transaction("Transfer to " + destinationAccount, amount));
            System.out.println("Transfer successful: " + amount + " remaining balance: " + balance);
        } else {
            System.out.println("Invalid transfer amount or insufficient balance.");
        }
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public static void main(String[] args) {
        ATM myAccount = new ATM(1000.0);
        ATM recipientAccount = new ATM(500.0);

        Scanner scanner = new Scanner(System.in);
        int choice;
        double amount;

        do {
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer");
            System.out.println("4. Display Transaction History");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter withdrawal amount: ");
                    amount = scanner.nextDouble();
                    myAccount.withdraw(amount);
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    amount = scanner.nextDouble();
                    myAccount.deposit(amount);
                    break;
                case 3:
                    System.out.print("Enter transfer amount: ");
                    amount = scanner.nextDouble();
                    myAccount.transfer(amount, recipientAccount);
                    break;
                case 4:
                    myAccount.displayTransactionHistory();
                    break;
                case 5:
                    System.out.println("Quitting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
