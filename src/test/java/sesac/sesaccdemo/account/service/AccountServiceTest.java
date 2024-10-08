package sesac.sesaccdemo.account.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindException;
import sesac.sesaccdemo.account.dto.SignupRequest;
import sesac.sesaccdemo.account.exception.AccountErrorCode;
import sesac.sesaccdemo.account.exception.AccountException;
import sesac.sesaccdemo.campus.entity.Campus;
import sesac.sesaccdemo.campus.entity.Course;
import sesac.sesaccdemo.user.entity.Student;
import sesac.sesaccdemo.user.entity.User;
import sesac.sesaccdemo.user.entity.UserType;
import sesac.sesaccdemo.user.repository.StudentRepository;

@SpringBootTest
@Transactional
@Log4j2
class AccountServiceTest {
    @Autowired
    private AccountService accountService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    private Long courseId;

    @BeforeEach
    public void setUp() {
        em.persist(User.builder().email("test@example.com").password("1234").type(UserType.STUDENT).build());

        Campus campus = Campus.builder().name("영등포").build();
        em.persist(campus);

        Course course = Course.builder().name("영등포 자바").instructorName("김자바").classNumber("1").campus(campus).build();
        em.persist(course);
        courseId = course.getId();
    }

    @Test
    @DisplayName("이메일 중복 체크 통과")
    public void emailCheck1() {
        accountService.emailCheck("test1@example.com");
    }

    @Test
    @DisplayName("이메일 중복 체크 실패")
    public void emailCheck2() {
        AccountException ex =
                assertThrows(AccountException.class, () -> accountService.emailCheck("test@example.com"));

        assertThat(ex.getErrorCode()).isEqualTo(AccountErrorCode.DUPLICATED_EMAIL);
        assertThat(ex.getMessage()).isEqualTo(AccountErrorCode.DUPLICATED_EMAIL.getMessage());
    }

    @Test
    @DisplayName("회원 가입 정상")
    public void signup1() {
        SignupRequest signupRequest = new SignupRequest(
                "test1@example.com",
                "asdf1234!",
                "asdf1234!",
                "김학생",
                "990101",
                1,
                courseId
        );

        Student created = accountService.saveStudent(signupRequest);
        Student student = studentRepository.findById(created.getId()).orElse(null);

        assertThat(student).isNotNull();
        assertThat(student.getBirthDate()).isEqualTo(LocalDate.parse("19990101",  DateTimeFormatter.ofPattern("yyyyMMdd")));
        assertThat(student.getName()).isEqualTo("김학생");
        assertThat(student.getGender()).isEqualTo('M');
        assertThat(student.getUser().getEmail()).isEqualTo("test1@example.com");
        assertThat(passwordEncoder.matches("asdf1234!", student.getUser().getPassword())).isEqualTo(true);
    }

    @Test
    @DisplayName("비밀번호 확인이 다른 경우")
    public void differentPasswordConfirm() {
        SignupRequest signupRequest = new SignupRequest(
                "test1@example.com",
                "asdf1234!",
                "asdf1234!2",
                "김학생f",
                "990101",
                1,
                courseId
        );

        AccountException ex =
                assertThrows(AccountException.class, () -> accountService.saveStudent(signupRequest));

        assertThat(ex.getErrorCode()).isEqualTo(AccountErrorCode.DIFFERENT_PASSWORD_CONFIRM);
    }

    @Test
    @DisplayName("과정이 없는 경우")
    public void noCourse() {
        SignupRequest signupRequest = new SignupRequest(
                "test1@example.com",
                "asdf1234!",
                "asdf1234!",
                "김학생",
                "990101",
                1,
                999999L
        );

        AccountException ex =
                assertThrows(AccountException.class, () -> accountService.saveStudent(signupRequest));

        assertThat(ex.getErrorCode()).isEqualTo(AccountErrorCode.NO_COURSE);
    }
}