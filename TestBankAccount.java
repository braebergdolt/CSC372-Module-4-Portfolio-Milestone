public class TestBankAccount {
    public static void main(String[] args) {
        // Test creating a BankAccount with a positive initial balance
        BankAccount account = new BankAccount(1000);
        System.out.println("Initial balance: " + account.getBalance());

        // Test deposit
        account.deposit(500);
        System.out.println("Balance after deposit: " + account.getBalance());

        // Test withdrawal
        account.withdraw(200);
        System.out.println("Balance after withdrawal: " + account.getBalance());

        // Test negative deposit (should throw an exception)
        testNegativeDeposit(account);

        // Test negative withdrawal (should throw an exception)
        testNegativeWithdrawal(account);

        // Test withdrawal with insufficient balance (should throw an exception)
        testInsufficientBalanceWithdrawal(account);
    }

    private static void testNegativeDeposit(BankAccount account) {
        try {
            account.deposit(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }

    private static void testNegativeWithdrawal(BankAccount account) {
        try {
            account.withdraw(-50);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }

    private static void testInsufficientBalanceWithdrawal(BankAccount account) {
        try {
            account.withdraw(2000);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}
