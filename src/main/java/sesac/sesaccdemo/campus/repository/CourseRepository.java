package sesac.sesaccdemo.campus.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sesac.sesaccdemo.campus.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    public List<Course> findByCampusId(Long campusId);
}
