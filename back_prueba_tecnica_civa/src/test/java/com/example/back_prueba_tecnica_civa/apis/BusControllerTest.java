package com.example.back_prueba_tecnica_civa.apis;

import com.example.back_prueba_tecnica_civa.constants.TestConstants;
import com.example.back_prueba_tecnica_civa.constants.UrlsConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = "/create_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class BusControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("GET /api/v1/bus success")
    @WithMockUser(roles = {"USER", "ADMIN"})
    void get_bus_list() throws Exception {
        String response = this.mockMvc
                .perform(MockMvcRequestBuilders.get(UrlsConstants.BUSES_LIST_ENDPOINT)
                        .param("page", "0")
                        .param("size", "5")
                        .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String prettyResponse = this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectMapper.readTree(response));
        log.info("Response: {}", prettyResponse);
    }

    @Test
    @DisplayName("GET /api/v1/bus/{busId} success")
    @WithMockUser(roles = {"USER", "ADMIN"})
    void get_bus_by_id() throws Exception {
        String response = this.mockMvc
                .perform(MockMvcRequestBuilders.get(UrlsConstants.BUS_BY_ID_ENDPOINT, 1)
                        .header(TestConstants.UUID_HEADER, String.valueOf(UUID.randomUUID()))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        String prettyResponse = this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectMapper.readTree(response));
        log.info("Response: {}", prettyResponse);
    }

}
