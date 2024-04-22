import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterFormTest {


    @Test
    public void checkPasswordSuccessful() {
        RegisterForm registerForm = new RegisterForm();
        boolean checkedPassword = registerForm.checkPasswordComplexity("Ch&&sec@ke99");
        assertTrue("password is correctly formatted", checkedPassword);

    }

    @Test
    public void checkPasswordFail() {
        RegisterForm registerForm = new RegisterForm();
        boolean checkedPassword = registerForm.checkPasswordComplexity("password");
        assertFalse("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.", checkedPassword);
    }

    @Test
    public void testIncorrectUsername() {
        RegisterForm registerForm = new RegisterForm();
        Boolean checkedUserName = registerForm.checkUserName("kyl_1");
        assertTrue("The username contains an underscore and is no more than 5 characters long", checkedUserName);
    }

    @Test
    public void testCorrectUsername() {
        RegisterForm registerForm = new RegisterForm();
        Boolean checkedUserName = registerForm.checkUserName("kyle!!!!!!!");
        assertFalse("Username is correctly formatted", checkedUserName);
    }
}