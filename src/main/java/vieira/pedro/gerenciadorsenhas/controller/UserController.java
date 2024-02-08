package vieira.pedro.gerenciadorsenhas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vieira.pedro.gerenciadorsenhas.dto.UserRequest;
import vieira.pedro.gerenciadorsenhas.dto.UserResponse;
import vieira.pedro.gerenciadorsenhas.entity.User;
import vieira.pedro.gerenciadorsenhas.mapper.UserMapper;
import vieira.pedro.gerenciadorsenhas.service.UserService;

import java.net.URI;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService service;

    private final UserMapper mapper = UserMapper.INSTANCE;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse findById(@PathVariable Long id){
        User userFound = service.findById(id);
        return mapper.toDto(userFound);
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest){
        User entity = mapper.toEntity(userRequest);
        User user = service.create(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        UserResponse dto = mapper.toDto(user);
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponse> replaceById(@PathVariable Long id, @RequestBody UserRequest userRequest){
        User entity = mapper.toEntity(userRequest);
        User user = service.replaceById(id, entity);
        UserResponse dto = mapper.toDto(user);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
