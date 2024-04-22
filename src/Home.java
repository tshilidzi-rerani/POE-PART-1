import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {

    private final User user;

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

        setTitle("Kanban App");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setMinimumSize(new Dimension(1200, 700));
        setVisible(true);
        setLocationRelativeTo(null);


    }
    public Home(User user) {
        this.user = user;
        // Add any initialization code here
    }


}
