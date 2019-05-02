package com.ngworks.app.web.rest.controller;

import com.ngworks.app.App;
import com.ngworks.app.service.UserService;
import com.ngworks.app.web.rest.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserService userService;

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

    @Test
    public void getUser() throws Exception {
        userService.save("lila", "tomojehaslo");

        mockMvc.perform(get("/api/v1/users/lila"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("lila"))
                .andExpect(jsonPath("$.hashedPassword")
                        .value("5838a16b10c7b7441066dac8805c647fb128849169e8d139d067f9d1a4d1118a"));
    }
    @Test
    public void getUser_expectedNoResourceFoundException() throws Exception {
        mockMvc.perform(get("/api/v1/users/Ala"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User not found for given name :: Ala"));
    }

    @Test
    public void saveUserWithMockMvc() throws Exception {
        mockMvc.perform(post("/api/v1/users/zygmunt")
                .content("password"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                .value("zygmunt"))
                .andExpect(jsonPath("$.hashedPassword")
                        .value("6e1dbff875c3b98e830a5e5895bc6a70bc44e6fcabe7ccde80ef75c4398e3f7c"));

        mockMvc.perform(post("/api/v1/users/zygmunt")
                .content("otherPassword"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("zygmunt"))
                .andExpect(jsonPath("$.hashedPassword")
                        .value("39fee9680228256b5af09e13bb164d8c42abbd5ab172b2cf9dd9dba59c496ebf"));
    }

    @Test
    public void updateUserWithMockMvc() throws Exception {
        mockMvc.perform(put("/api/v1/users/zygmunt")
                .content("password"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                        .value("zygmunt"))
                .andExpect(jsonPath("$.hashedPassword")
                        .value("6e1dbff875c3b98e830a5e5895bc6a70bc44e6fcabe7ccde80ef75c4398e3f7c"));
    }

    @Test
    public void updateUserWithMockMvc_expectResourceNotFoundException() throws Exception {
        mockMvc.perform(put("/api/v1/users/unknown")
                .content("password"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User not found for given name :: unknown"));
    }

    @Test
    public void saveUserWithMockMvc_noPassword() throws Exception {
        mockMvc.perform(post("/api/v1/users/unknown")
                .content(""))
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void updateUserWithMockMvc_noPassword() throws Exception {
        mockMvc.perform(put("/api/v1/users/unknown")
                .content(""))
                .andExpect(status().isInternalServerError());
    }
}
