package sesac.sesaccdemo.manager.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Log4j2
@RestController
@RequestMapping("manager")
public class ManagerController {

    @GetMapping
    public void manager(Principal authentication) {
        log.info("================== manager");
    }

}
