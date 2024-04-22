import javax.swing.*;
import java.awt.*;


public class Home extends JFrame {

    private final User user;

//this method runs show the home screen if the user is successful in logging in

    public void showHome() {
        Font headingFont = new Font("Arial", Font.BOLD, 36);


        JLabel formLabel = new JLabel("Welcome to Kanban", SwingConstants.CENTER);
        JLabel name = new JLabel("Welcome "+ user.getFirstName()+" "+ user.getLastName()+", it is great to see you again.",SwingConstants.CENTER);
        formLabel.setFont(headingFont);


        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(0, 1, 10, 10));
        loginPanel.add(formLabel);
        loginPanel.add(name);
        add(loginPanel);

        //this block is to make sure the app is the correct size and open in the center of the screen
        setTitle("Kanban App");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setMinimumSize(new Dimension(1200, 700));
        setVisible(true);
        setLocationRelativeTo(null);


    }

    //here we are passing the user to this screen if the are able to reach this screen it means they are logged in
    public Home(User user) {
        this.user = user;
    }


}
