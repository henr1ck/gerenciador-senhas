package vieira.pedro.gerenciadorsenhas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vieira.pedro.gerenciadorsenhas.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
