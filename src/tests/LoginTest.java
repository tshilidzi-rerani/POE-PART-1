import org.junit.Test;

import static org.junit.Assert.*;

public class LoginTest {
    @Test
    public void testUserDoesNotExist() {
        Login login = new Login();
        String username = "kyle!!!!!!!";
        String password = "password";
        User user = login.readUser(username);
        Boolean loggedIn = login.loginUser(username, password, user);
        assertFalse("Login Failed", loggedIn);
    }

    @Test
    public void testFailedWrongPassword() {
        Login login = new Login();
        String username = "kyl_1";
        String password = "password";
        User user = login.readUser(username);
        Boolean loggedIn = login.loginUser(username, password, user);
        assertFalse("Login Failed", loggedIn);
    }

    @Test
    public void testSuccessLogin() {
        Login login = new Login();
        String username = "kyl_1";
        String password = "Ch&&sec@ke99!";
        User user = login.readUser(username);
        Boolean loggedIn = login.loginUser(username, password, user);
        assertTrue("Login Successful", loggedIn);
    }

}