package com.ngworks.app.web.controller;

import com.ngworks.app.App;
import com.ngworks.app.web.representation.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserControllerIntegrationTest {

    @Autowired
    private UserController userController;

    @Test
    public final void saveUser() {
        String name = "Name1";
        String password = "Password1";
        String hashedPassword = "b9efd0093bc77cd96e6f86db09321ffe5cb4ffa7634df89df586379fe6276cc8";

        UserDTO dto = userController.save(name, password);

        assertNotNull(dto);
        assertNotNull(dto.getName());
        assertNotNull(dto.getHashedPassword());
        assertEquals(dto.getHashedPassword(), hashedPassword);
    }
}
