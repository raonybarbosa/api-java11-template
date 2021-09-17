package br.com.rhhb.template.controller;

import br.com.rhhb.template.dto.UserDTO;
import br.com.rhhb.template.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Api(value = "API para interações com a entidade do usuário", tags = {"User"})
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Listar usuários", response = UserDTO.class, tags = {"User"})
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @ApiOperation(value = "Buscar usuário por id", response = UserDTO.class, tags = {"User"})
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @ApiOperation(value = "Inserir usuário", response = UserDTO.class, tags = {"User"})
    @PostMapping
    public ResponseEntity<URI> insertUser(@RequestBody @Valid UserDTO userDto) {
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(userService.insertUser(userDto).getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Alterar o usuário pelo id", tags = {"User"})
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UserDTO userDto,
                                           @ApiParam(value = "ID do usuário", example = "1") @PathVariable Long id) {
        userService.updateUser(userDto, id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Excluir o usuário por id", tags = {"User"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@ApiParam(value = "ID do usuário", example = "1") @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}