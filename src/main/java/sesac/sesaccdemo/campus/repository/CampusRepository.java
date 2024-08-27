package sesac.sesaccdemo.campus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sesac.sesaccdemo.campus.entity.Campus;

public interface CampusRepository extends JpaRepository<Campus, Long> {
    
}
