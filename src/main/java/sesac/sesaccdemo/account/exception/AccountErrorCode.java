package sesac.sesaccdemo.account.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import sesac.sesaccdemo.common.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum AccountErrorCode implements ErrorCode {
    NO_COURSE(HttpStatus.BAD_REQUEST, "과정이 없습니다."),
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "이미 가입된 이메일 입니다.");

    private final HttpStatus status;
    private final String message;
}
