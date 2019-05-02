package com.ngworks.app.web.rest.controller;

import com.ngworks.app.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class UserStorageControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void helloMapping() throws Exception {
       /* mvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Tomasz Kalisiak."));*/
    }
}
