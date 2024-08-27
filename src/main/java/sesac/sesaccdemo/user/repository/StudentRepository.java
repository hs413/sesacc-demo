package sesac.sesaccdemo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sesac.sesaccdemo.user.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
