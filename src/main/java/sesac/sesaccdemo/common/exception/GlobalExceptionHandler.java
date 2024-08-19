package sesac.sesaccdemo.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 사용자 정의 예외
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleException(BaseException e) {
        ErrorCode errorCode = e.getErrorCode();
        int statusValue = errorCode.getStatus().value();

        ErrorResponse errorResponse =
                new ErrorResponse(statusValue, errorCode.getMessage());

        log.error("사용자 정의 에러 : {}", e.getMessage(), e);
        return ResponseEntity
                .status(statusValue)
                .body(errorResponse);
    }

    // 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        int statusValue = HttpStatus.INTERNAL_SERVER_ERROR.value();

        ErrorResponse errorResponse =
                new ErrorResponse(statusValue, ErrorCode.INTERNAL_SERVER_ERROR.getMessage());

        log.error("서버 에러 : {}", e.getMessage(), e);
        return ResponseEntity
                .status(statusValue)
                .body(errorResponse);
    }
}
