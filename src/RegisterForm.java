import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//I made this page a JFrame that holds the ui for the form
public class RegisterForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    JLabel messageLabel = new JLabel();

    Color green = Color.green;
    Color black = Color.black;

    public void showRegisterForm() {
        Font headingFont = new Font("Arial", Font.BOLD, 36);

        messageLabel.setText("FIll in your information below");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);



        JLabel formLabel = new JLabel("Signup Page", SwingConstants.CENTER);
        formLabel.setFont(headingFont);
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JLabel firstNameLabel = new JLabel("First Name");
        JLabel lastNameLabel = new JLabel("Last Name");

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();

        JButton loginButton = new JButton("Login Instead");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login nextScreen = new Login();
                nextScreen.showLoginForm();
            }
        });

        loginButton.setForeground(Color.blue);

        JButton registerButton = new JButton("Signup");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, registerUser());
            }
        });

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(0, 1, 10, 10));
        loginPanel.add(formLabel);

        //adding the form to the panel
        loginPanel.add(messageLabel);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);

        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        loginPanel.add(firstNameLabel);
        loginPanel.add(firstNameField);

        loginPanel.add(lastNameLabel);
        loginPanel.add(lastNameField);

        loginPanel.add(registerButton);
        loginPanel.add(loginButton);
        add(loginPanel);

        setTitle("Kanban App");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 700);
        setMinimumSize(new Dimension(400, 700));
        setVisible(true);
        setLocationRelativeTo(null);


    }

    Boolean checkPasswordComplexity(String passwordValue) {

        if (passwordValue.length() >= 8 &&
                passwordValue.matches(".*[A-Z].*") &&
                passwordValue.matches(".*\\d.*") &&
                passwordValue.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            return true;
        } else {
            return false;
        }
    }

     Boolean checkUserName(String userNameValue) {
        if (userNameValue.contains("_") && userNameValue.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }

     Boolean fullIsNameValid() {

        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public String registerUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        messageLabel.setText("FIll in your information below");
        messageLabel.setForeground(black);
        // Check if the username is incorrectly formatted
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is  no more than 5 characters in length";
        } else {
            messageLabel.setText("Username successfully captured");
            messageLabel.setForeground(green);
        }

        // Check if the password meets the complexity requirements
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        } else {
            messageLabel.setText("Password successfully captured");
            messageLabel.setForeground(green);
        }

        // Check if the password meets the complexity requirements
        if (!fullIsNameValid()) {
            return "First and Last name cannot be empty.";
        } else {
            messageLabel.setText("Names successfully captured");
            messageLabel.setForeground(green);
        }


        // If both username and password are valid, consider the user registered successfully
       User user = new User(firstName,lastName,username,password);
       user.writeToFile(username);
        Home nextScreen = new Home(user);
        nextScreen.showHome();

        // Close the current window
        dispose(); // This will close the current JFrame
        return "User registered successfully.";
    }

}
