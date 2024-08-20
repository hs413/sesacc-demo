package sesac.sesaccdemo.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sesac.sesaccdemo.common.exception.BaseException;
import sesac.sesaccdemo.common.exception.ErrorCode;

@RestController
@Slf4j
public class SampleController {
    @GetMapping
    public String hello(Authentication authentication) {
        log.info("hello, {}", ErrorCode.INTERNAL_SERVER_ERROR.getStatus());
        log.info("hello, {}", ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
        log.info("hello, {}", ErrorCode.INVALID.getMessage());
        throw new BaseException(ErrorCode.INTERNAL_SERVER_ERROR);

//        return "Hello World!";
    }
}
