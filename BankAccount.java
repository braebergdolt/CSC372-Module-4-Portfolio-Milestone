/**
 * Represents a bank account with basic operations such as deposit and withdrawal.
 */
public class BankAccount {
    private double balance;

    /**
     * Constructs a bank account with an initial balance.
     *
     * @param initialBalance The initial balance of the account.
     * @throws IllegalArgumentException if the initial balance is negative.
     */
    public BankAccount(double initialBalance) {
        validateAmount(initialBalance, "Initial balance cannot be negative.");
        this.balance = initialBalance;
    }

    /**
     * Deposits a specified amount into the account.
     *
     * @param amount The amount to be deposited.
     * @throws IllegalArgumentException if the deposit amount is negative.
     */
    public void deposit(double amount) {
        validateAmount(amount, "Deposit amount cannot be negative.");
        balance += amount;
    }

    /**
     * Withdraws a specified amount from the account.
     *
     * @param amount The amount to be withdrawn.
     * @throws IllegalArgumentException if the withdrawal amount is negative or exceeds the current balance.
     */
    public void withdraw(double amount) {
        validateAmount(amount, "Withdrawal amount cannot be negative.");
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
    }

    /**
     * Returns the current balance of the account.
     *
     * @return The current balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Validates that the amount is not negative.
     *
     * @param amount The amount to validate.
     * @param errorMessage The error message to display if the validation fails.
     * @throws IllegalArgumentException if the amount is negative.
     */
    private void validateAmount(double amount, String errorMessage) {
        if (amount < 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
