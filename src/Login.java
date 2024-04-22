import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    private final JLabel messageLabel = new JLabel();
   private User user;



    // Method to display the login form
    public void showLoginForm() {
        Font headingFont = new Font("Arial", Font.BOLD, 36);

        messageLabel.setText("FIll in your information below");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel formLabel = new JLabel("Login Page", SwingConstants.CENTER);
        formLabel.setFont(headingFont);
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");


        usernameField = new JTextField();
        passwordField = new JPasswordField();


        // listener for the login button that executes the attempt to login
        JButton loginButton = new JButton("Login ");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnLoginStatus();
                String loginStatus = returnLoginStatus();
                JOptionPane.showMessageDialog(null, loginStatus);


            }
        });

        loginButton.setForeground(Color.blue);

        // listener for the Register button that opens the signup button
        JButton registerButton = new JButton("Signup Instead");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm nextScreen = new RegisterForm();
                nextScreen.showRegisterForm();
            }
        });

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(0, 1, 10, 10));
        loginPanel.add(formLabel);
        loginPanel.add(messageLabel);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);

        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);

        loginPanel.add(loginButton);
        loginPanel.add(registerButton);
      
        add(loginPanel);

        setTitle("Kanban App");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 700);
        setMinimumSize(new Dimension(400, 700));
        setVisible(true);
        setLocationRelativeTo(null);
    }
    // Method to validate user login
    Boolean  loginUser(String password, User user){


        //if the user is null it means the user does not exist.
      if ( user == null){
          return false;
      }

        return password.equals(user.getPassword());

    }

//this function checks whether the login attempt was successful and return the answer as a scree nwe can display
     String returnLoginStatus() {
         String username = usernameField.getText().trim(); // Trim whitespace for simpler comparison
         String enteredPassword = new String(passwordField.getPassword()).trim();
         user = readUser(username);

        if (loginUser(enteredPassword,user)) {
            Home nextScreen = new Home(user);
            nextScreen.showHome();
            // Close the current window
            dispose();

            return "Welcome"+ user.getLastName()+ " , " +user.getFirstName()+" it is great to see you again.";
        } else {
            //this means teh user was unable to login.
            return "Username or password incorrect, please try again";
        }
    }

    // Function to read User object from a file
      User readUser(String username) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(username))) {
            return (User) inputStream.readObject();
        } catch (IOException | ClassNotFoundException error) {
            // Show error message if file cannot be read because it means the use does not exist
            System.out.println("user does not exist" + error.getMessage());
            return null;
        }
    }
}
