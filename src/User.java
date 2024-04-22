import java.io.*;

public class User implements Serializable{
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;


    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    // Getters only for the values i will be using
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getPassword() {
        return password;
    }

    //this function saves the users information in a file that we can access later
    public void writeToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("User written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing user to file: " + e.getMessage());
        }
    }
}
