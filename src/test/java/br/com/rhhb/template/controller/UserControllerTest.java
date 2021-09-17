package br.com.rhhb.template.controller;

import br.com.rhhb.template.dto.UserDTO;
import br.com.rhhb.template.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    private static final String ENDPOINT_USER = "/api/v1/user";
    private static final String PARAMETER_ID = "/{id}";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService service;

    @Test
    void insertTest() throws Exception {

        var objectDto = new UserDTO();
        objectDto.setName("Teste");

        when(service.insertUser(any())).thenReturn(objectDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(ENDPOINT_USER)
                        .content(new ObjectMapper().writeValueAsString(objectDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void findUserByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get(ENDPOINT_USER + PARAMETER_ID, 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findAllTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get(ENDPOINT_USER)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateTest() throws Exception {

        var objectDto = new UserDTO();
        objectDto.setId(1L);
        objectDto.setName("Teste");

        mockMvc.perform(MockMvcRequestBuilders
                        .put(ENDPOINT_USER + PARAMETER_ID, 1)
                        .content(new ObjectMapper().writeValueAsString(objectDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete(ENDPOINT_USER + PARAMETER_ID, 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
