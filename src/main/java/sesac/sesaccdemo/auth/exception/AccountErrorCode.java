package sesac.sesaccdemo.auth.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import sesac.sesaccdemo.common.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum AccountErrorCode implements ErrorCode {
    NO(HttpStatus.BAD_REQUEST, "안돼");

    private final HttpStatus status;
    private final String message;
}
