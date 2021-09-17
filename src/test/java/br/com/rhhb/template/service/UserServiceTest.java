package br.com.rhhb.template.service;

import br.com.rhhb.template.dto.UserDTO;
import br.com.rhhb.template.entity.User;
import br.com.rhhb.template.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @InjectMocks
    UserService service;
    @Mock
    UserRepository repository;

    User object = new User();
    UserDTO objectDTO = new UserDTO();

    @BeforeEach
    public void setUp() {
        object = create();
        objectDTO = createDto();

        MockitoAnnotations.openMocks(this);
    }

    private UserDTO createDto() {
        var userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("Teste DTO");
        return userDTO;
    }

    private User create() {
        var user = new User();
        user.setId(1L);
        user.setName("Teste");
        return user;
    }

    @Test
    void inserirTest() {
        when(repository.save(any()))
                .thenReturn(object);
        var objectDTO = createDto();
        var objectDTOSave = service.insertUser(objectDTO);

        assertEquals(1L, objectDTOSave.getId());
    }

    @Test
    void listarTest() {
        when(repository.findAll())
                .thenReturn(Collections.singletonList(object));

        var result = service.findAll();

        assertEquals(Collections.singletonList(object).size(),
                result.size());
    }

    @Test
    void buscarPorIdTest() {
        when(repository.findById(any())).thenReturn(Optional.of(object));

        var result = service.findUserById(1L);

        assertEquals(result.getId(), object.getId());
    }

    @Test
    void alterarTest() {
        service.updateUser(objectDTO, objectDTO.getId());

        verify(repository).save(any());
    }

    @Test
    void excluirTest() {
        service.deleteUser(object);

        verify(repository).delete(any());
    }

    @Test
    void excluirPorIdTest() {
        when(repository.findById(any())).thenReturn(Optional.of(object));

        service.deleteUser(object.getId());

        verify(repository).delete(any());
    }
}
