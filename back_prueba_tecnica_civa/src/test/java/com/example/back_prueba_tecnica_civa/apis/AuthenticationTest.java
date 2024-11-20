package com.example.back_prueba_tecnica_civa.apis;

import com.example.back_prueba_tecnica_civa.constants.TestConstants;
import com.example.back_prueba_tecnica_civa.constants.UrlsConstants;
import com.example.back_prueba_tecnica_civa.models.request.AuthLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser(roles = TestConstants.ROLE_ADMIN)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = "/create_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AuthenticationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    @Transactional
    @DisplayName("POST /login success")
    @Sql(scripts = {"/login-script.sql"})
    void login_success() throws Exception {
        String response =
                this.mockMvc
                        .perform(
                                MockMvcRequestBuilders.post(UrlsConstants.LOGIN_USER_ENDPOINT)
                                        .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(
                                                this.objectMapper.writeValueAsString(
                                                        this.getAuthLogin("ever123@gmail.com", "123"))))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        log.info("Inicio de sesión exitoso {}", response);
    }

    @Test
    @DisplayName("POST /login bad credentials")
    void login_with_error() throws Exception {
        String response =
                this.mockMvc
                        .perform(
                                MockMvcRequestBuilders.post(UrlsConstants.LOGIN_USER_ENDPOINT)
                                        .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(
                                                this.objectMapper.writeValueAsString(
                                                        this.getAuthLogin("ever123@gmail.com", "bad"))))
                        .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        log.info("Wrong credentials response: {}", response);
    }

    private AuthLoginRequest getAuthLogin(String email, String password) {
        AuthLoginRequest authLoginRequest = new AuthLoginRequest();
        authLoginRequest.setEmail(email);
        authLoginRequest.setPassword(password);
        return authLoginRequest;
    }

}
