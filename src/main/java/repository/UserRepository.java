package repository;

import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 유저 조회
    Optional<User> findByEmail(String email);

    // 이메일로 유저 존재 여부
    boolean existByEmail(String email);

    Optional<User> findByRefreshToken(String regreshToken);

}
