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

    private JLabel messageLabel = new JLabel();
   private User user;

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

    Boolean  loginUser(String username, String password, User user){



      if ( user == null){
          return false;
      }

      if (!password.equals(user.getPassword())){

          return  false;
      }

      return true;

    }

     String returnLoginStatus() {
         String username = usernameField.getText().trim(); // Trim whitespace
         String enteredPassword = new String(passwordField.getPassword()).trim();
         user = readUser(username);

        if (loginUser(username,enteredPassword,user)) {
            Home nextScreen = new Home(user);
            nextScreen.showHome();
            // Close the current window
            dispose();
            return "Welcome"+ user.getLastName()+ " , " +user.getFirstName()+" it is great to see you again.";
        } else {

            return "Username or password incorrect, please try again";
        }
    }

    // Method to read User object from a file
      User readUser(String username) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(username))) {
            return (User) inputStream.readObject();
        } catch (IOException | ClassNotFoundException error) {
            // Print error message if file cannot be read because it means the use does not exist
            System.out.println("user does not exist" + error.getMessage());
            return null;
        }
    }
}
