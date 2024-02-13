package vieira.pedro.gerenciadorsenhas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vieira.pedro.gerenciadorsenhas.entity.Password;
import vieira.pedro.gerenciadorsenhas.entity.User;

import java.util.Optional;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

    @Query("from Password p join fetch p.owner o where o = :user")
    Page<Password> findAllByUser(@Param("user") User user, Pageable pageable);

    @Query("from Password p " +
            "join fetch p.owner o " +
            "where p = :password and o = :user")
    Optional<Password> findByUserAndPassword(@Param("user") User user, @Param("password") Password password);
}
