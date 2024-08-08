/**
 * Represents a checking account that extends the basic bank account.
 * This account allows overdrafts up to a specified limit.
 */
public class CheckingAccount extends BankAccount {
    private final double overdraftLimit;

    /**
     * Constructs a checking account with an initial balance and an overdraft limit.
     *
     * @param initialBalance The initial balance of the account.
     * @param overdraftLimit The overdraft limit for the account.
     * @throws IllegalArgumentException if the initial balance or overdraft limit is negative.
     */
    public CheckingAccount(double initialBalance, double overdraftLimit) {
        super(initialBalance);
        validateAmount(overdraftLimit, "Overdraft limit cannot be negative.");
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Withdraws a specified amount from the account, allowing overdraft up to the limit.
     *
     * @param amount The amount to be withdrawn.
     * @throws IllegalArgumentException if the withdrawal amount is negative or exceeds the overdraft limit.
     */
    @Override
    public void withdraw(double amount) {
        validateAmount(amount, "Withdrawal amount cannot be negative.");
        if (amount > getBalance() + overdraftLimit) {
            throw new IllegalArgumentException("Insufficient balance and overdraft limit exceeded.");
        }
        super.withdraw(amount);
    }

    /**
     * Returns the overdraft limit of the account.
     *
     * @return The overdraft limit.
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}
