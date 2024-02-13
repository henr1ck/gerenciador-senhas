package vieira.pedro.gerenciadorsenhas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import vieira.pedro.gerenciadorsenhas.entity.Password;
import vieira.pedro.gerenciadorsenhas.entity.User;
import vieira.pedro.gerenciadorsenhas.exception.ResourceNotFoundException;
import vieira.pedro.gerenciadorsenhas.repository.PasswordRepository;

@Service
public class UserPasswordService {

    private final UserService userService;

    private final PasswordRepository repository;

    public UserPasswordService(UserService userService, PasswordRepository repository) {
        this.userService = userService;
        this.repository = repository;
    }

    public Page<Password> findAllByUser(Long userId, Pageable pageable){
        User userFound = userService.findById(userId);
        return repository.findAllByUser(userFound, pageable);
    }

    public Password findById(Long id){
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Password not found!")
        );
    }

    public Password findByUserIdAndPasswordId(Long userId, Long passwordId){
        User userFound = userService.findById(userId);
        Password passwordFound = findById(passwordId);
        
        return repository.findByUserAndPassword(userFound, passwordFound)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT));
    }

    @Transactional(rollbackFor = Exception.class)
    public Password create(Long userId, Password password) {
        User user = userService.findById(userId);
        password.setOwner(user);
        return repository.save(password);
    }

    @Transactional(rollbackFor = Exception.class)
    public Password replace(Long userId, Long passwordId, Password password) {
        Password passwordFound = findByUserIdAndPasswordId(userId, passwordId);
        password.setId(passwordFound.getId());
        password.setOwner(passwordFound.getOwner());
        password.setCreationTimestamp(passwordFound.getCreationTimestamp());
        password.setUpdateTimestamp(passwordFound.getUpdateTimestamp());

        return repository.save(password);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteByUserIdAndPasswordId(Long userId, Long passwordId) {
        Password passwordToDelete = findByUserIdAndPasswordId(userId, passwordId);
        repository.delete(passwordToDelete);
    }
}
