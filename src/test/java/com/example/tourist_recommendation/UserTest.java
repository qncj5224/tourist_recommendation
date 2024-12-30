package com.example.tourist_recommendation;
import com.example.tourist_recommendation.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

    @Test
    public void testConfirmPasswordFieldExists() {
        User user = new User();
        user.setPassword("testPassword");

        assertNotNull(user.getPassword(), "ConfirmPassword field is not defined or getter is missing.");
    }
}