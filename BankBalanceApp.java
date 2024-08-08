import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * BankBalanceApp class for managing a simple bank balance application.
 */
public class BankBalanceApp {
    private double balance;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankBalanceApp app = new BankBalanceApp();
            app.createAndShowGUI();
        });
    }

    /**
     * Creates and displays the GUI for the application.
     */
    private void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Bank Balance Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create the main panel
        JPanel panel = createMainPanel();

        // Add panel to frame
        frame.add(panel);

        // Display the frame
        frame.setVisible(true);

        // Add a window listener to display the remaining balance before exiting
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                JOptionPane.showMessageDialog(frame, "Your final balance is: " + String.format("$%.2f", balance));
            }
        });
    }

    /**
     * Creates the main panel with components.
     * @return the main panel
     */
    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel balanceLabel = new JLabel("Current Balance: ");
        JLabel balanceValue = new JLabel(String.format("$%.2f", balance));

        JLabel amountLabel = new JLabel("Enter Amount: ");
        JTextField amountField = new JTextField();

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        depositButton.addActionListener(e -> handleDeposit(amountField, balanceValue));
        withdrawButton.addActionListener(e -> handleWithdraw(amountField, balanceValue));

        panel.add(balanceLabel);
        panel.add(balanceValue);
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(depositButton);
        panel.add(withdrawButton);

        return panel;
    }

    /**
     * Handles the deposit action.
     * @param amountField the text field for the amount
     * @param balanceValue the label displaying the balance
     */
    private void handleDeposit(JTextField amountField, JLabel balanceValue) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0) {
                balance += amount;
                balanceValue.setText(String.format("$%.2f", balance));
                amountField.setText("");
            } else {
                showErrorDialog("Please enter a positive amount for deposit.");
            }
        } catch (NumberFormatException ex) {
            showErrorDialog("Please enter a valid number.");
        }
    }

    /**
     * Handles the withdraw action.
     * @param amountField the text field for the amount
     * @param balanceValue the label displaying the balance
     */
    private void handleWithdraw(JTextField amountField, JLabel balanceValue) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                balanceValue.setText(String.format("$%.2f", balance));
                amountField.setText("");
            } else if (amount > balance) {
                showErrorDialog("Insufficient balance for withdrawal.");
            } else {
                showErrorDialog("Please enter a positive amount for withdrawal.");
            }
        } catch (NumberFormatException ex) {
            showErrorDialog("Please enter a valid number.");
        }
    }

    /**
     * Shows an error dialog with a given message.
     * @param message the error message to display
     */
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }
}
