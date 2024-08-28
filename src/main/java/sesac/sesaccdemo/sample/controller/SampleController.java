package sesac.sesaccdemo.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sesac.sesaccdemo.auth.dto.AuthPrincipal;
import sesac.sesaccdemo.auth.dto.PrincipalRecord;
import sesac.sesaccdemo.common.exception.BaseException;
import sesac.sesaccdemo.common.exception.GlobalErrorCode;

@RestController
@Slf4j
public class SampleController {
    @GetMapping
    public String hello(@AuthPrincipal PrincipalRecord principal) {
        log.info("hello, {}", GlobalErrorCode.INTERNAL_SERVER_ERROR.getStatus());
        log.info("hello, {}", GlobalErrorCode.INTERNAL_SERVER_ERROR.getMessage());
        throw new BaseException(GlobalErrorCode.INTERNAL_SERVER_ERROR);

//        return "Hello World!";
    }
}
