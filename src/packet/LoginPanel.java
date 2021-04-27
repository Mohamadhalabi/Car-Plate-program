import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoginPanel {
    private JTextField UsernameField;
    private JButton loginButton;
    public JPanel panel;
    private JPasswordField passwordField;
    private JTextField PasswordField;

    public LoginPanel() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean accountFound = false; // Mark when an account is logged in
                String usernameInput = UsernameField.getText(); // Get username input
                String  password = PasswordField.getText(); // Get password input


                try {
                    // Read from registration.txt file
                    Scanner input = new Scanner(new File("registration.txt"));

                    // Reads through the lines of the text file until an account is found
                    while (input.hasNextLine() && !accountFound) {
                        String s = input.nextLine();
                        String[] sArray = s.split(",");

                        // Check if the user input matches the registration file information
                        if (usernameInput.equals(sArray[0]) && password.equals(sArray[1])) {
                            JOptionPane.showMessageDialog(null, "Login successful", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                            accountFound = true; // Found account login


                            // Otherwise, the users input does not match an email and password
                        } else {
                            PasswordField.setText("");
                            accountFound = false;
                        }

                    }

                    // Show error message if an account is not found
                    if (    accountFound == false) {
                        JOptionPane.showMessageDialog(null, "Invalid login \nPlease check your username and password", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        input.close(); // Close input
                    }

                } catch ( FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error");
                }

            }
        });
    }
}
