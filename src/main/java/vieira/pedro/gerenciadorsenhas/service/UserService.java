package vieira.pedro.gerenciadorsenhas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vieira.pedro.gerenciadorsenhas.entity.User;
import vieira.pedro.gerenciadorsenhas.exception.ResourceNotFoundException;
import vieira.pedro.gerenciadorsenhas.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    @Transactional(rollbackFor = Exception.class)
    public User create(User user){
        return repository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public User replaceById(Long id, User user){
        User userFound = findById(id);
        user.setId(userFound.getId());
        user.setCreationTimestamp(userFound.getCreationTimestamp());

        return repository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id){
        repository.delete(findById(id));
    }
}
