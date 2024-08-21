package sesac.sesaccdemo.auth.exception;

import sesac.sesaccdemo.common.exception.BaseException;
import sesac.sesaccdemo.common.exception.ErrorCode;

public class AccountException extends BaseException {

    public AccountException(ErrorCode errorCode) {
        super(errorCode);
    }
}
