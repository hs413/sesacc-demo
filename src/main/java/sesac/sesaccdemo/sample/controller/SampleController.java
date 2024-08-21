package sesac.sesaccdemo.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sesac.sesaccdemo.common.exception.BaseException;
import sesac.sesaccdemo.common.exception.GlobalErrorCode;

import java.security.Principal;

@RestController
@Slf4j
public class SampleController {
    @GetMapping
    public String hello(Principal authentication) {
        log.info("hello, {}", GlobalErrorCode.INTERNAL_SERVER_ERROR.getStatus());
        log.info("hello, {}", GlobalErrorCode.INTERNAL_SERVER_ERROR.getMessage());
        log.info("hello, {}", GlobalErrorCode.INVALID.getMessage());
        throw new BaseException(GlobalErrorCode.INTERNAL_SERVER_ERROR);

//        return "Hello World!";
    }
}
