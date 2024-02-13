package vieira.pedro.gerenciadorsenhas.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vieira.pedro.gerenciadorsenhas.dto.PasswordRequest;
import vieira.pedro.gerenciadorsenhas.dto.PasswordResponse;
import vieira.pedro.gerenciadorsenhas.entity.Password;
import vieira.pedro.gerenciadorsenhas.entity.User;
import vieira.pedro.gerenciadorsenhas.mapper.PasswordMapper;
import vieira.pedro.gerenciadorsenhas.service.UserPasswordService;

import java.net.URI;

@RestController
@RequestMapping(path = "/users/{id}/passwords")
public class UserPasswordController {

    private final UserPasswordService service;

    private final PasswordMapper mapper = PasswordMapper.INSTANCE;

    public UserPasswordController(UserPasswordService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<PasswordResponse>> findAllPasswordByUser(@PathVariable Long id, Pageable pageable){
        Page<Password> passwordPage = service.findAllByUser(id, pageable);
        Page<PasswordResponse> passwordResponsesPage = passwordPage.map(mapper::toDto);
        return ResponseEntity.ok(passwordResponsesPage);
    }

    @PostMapping
    public ResponseEntity<PasswordResponse> create(@PathVariable(name = "id") Long userId, @RequestBody PasswordRequest passwordRequest){
        Password passwordEntity = mapper.toEntity(passwordRequest);
        Password passwordSaved = service.create(userId, passwordEntity);
        PasswordResponse passwordResponse = mapper.toDto(passwordSaved);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/users/{id}/passwords")
                .buildAndExpand(passwordResponse.getId())
                .toUri();
        return ResponseEntity.created(uri).body(passwordResponse);
    }

    @PutMapping(path = "/{passwordId}")
    public ResponseEntity<PasswordResponse> replace(
            @PathVariable(name = "id") Long userId,
            @PathVariable(name = "passwordId") Long passwordId,
            @RequestBody PasswordRequest passwordRequest
    ){
        Password passwordEntity = mapper.toEntity(passwordRequest);
        Password passwordUpdate = service.replace(userId, passwordId, passwordEntity);
        PasswordResponse passwordResponse = mapper.toDto(passwordUpdate);
        return ResponseEntity.ok(passwordResponse);
    }

    @DeleteMapping(path = "/{passwordId}")
    public ResponseEntity<Void> deleteByUserIdAndPasswordId(
            @PathVariable(name = "id") Long userId,
            @PathVariable(name = "passwordId") Long passwordId
    ){
        service.deleteByUserIdAndPasswordId(userId, passwordId);
        return ResponseEntity.noContent().build();
    }
}
