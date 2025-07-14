import java.util.Scanner;

// Class representing the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

// Class representing the ATM machine
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int option;
        do {
            showMenu();
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    withdraw();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    checkBalance();
                    break;
                case 4:
                    System.out.println("💳 Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("❌ Invalid option. Please try again.");
            }

        } while (option != 4);
    }

    private void showMenu() {
        System.out.println("\n=== ATM Menu ===");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private void withdraw() {
        System.out.print("Enter amount to withdraw: ₹");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("❌ Please enter a valid amount.");
        } else if (account.withdraw(amount)) {
            System.out.println("✅ Withdrawal successful. ₹" + amount + " withdrawn.");
        } else {
            System.out.println("❌ Insufficient balance.");
        }
    }

    private void deposit() {
        System.out.print("Enter amount to deposit: ₹");
        double amount = scanner.nextDouble();
        if (amount <= 0) {
            System.out.println("❌ Please enter a valid amount.");
        } else {
            account.deposit(amount);
            System.out.println("✅ Deposit successful. ₹" + amount + " added.");
        }
    }

    private void checkBalance() {
        System.out.printf("💰 Your current balance is: ₹%.2f\n", account.getBalance());
    }
}

// Main class
public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(0.0); // starting with ₹0.00
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
