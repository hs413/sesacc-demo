package sesac.sesaccdemo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sesac.sesaccdemo.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
