package sesac.sesaccdemo.campus.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import sesac.sesaccdemo.campus.dto.CourseResponse;
import sesac.sesaccdemo.campus.entity.Course;
import sesac.sesaccdemo.campus.repository.CourseRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<CourseResponse> findAll(Long campusId) {
        List<Course> list = courseRepository.findByCampusId(campusId);

        List<CourseResponse> response =
                list.stream().map(r -> campusToResponse(r)).toList();

        return response;
    }

    private CourseResponse campusToResponse(Course course) {
        return new CourseResponse(course.getId(), course.getName(), course.getClassNumber());
    }
}
