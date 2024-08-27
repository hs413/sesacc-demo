package sesac.sesaccdemo.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sesac.sesaccdemo.campus.entity.Course;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
public class Student {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private char gender;

    @Column(nullable = false, unique = true)
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "first_course_id", nullable = false)
    private Course firstCourse;

    @ManyToOne
    @JoinColumn(name = "second_course_id")
    private Course secondCourse;

    private String profileImage;

    @Column(nullable = false)
    private int statusCode;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}