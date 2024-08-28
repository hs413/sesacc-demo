package sesac.sesaccdemo.account.controller;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sesac.sesaccdemo.account.dto.EmailCheckRequest;
import sesac.sesaccdemo.account.dto.SignupRequest;
import sesac.sesaccdemo.account.exception.AccountBindHandler;
import sesac.sesaccdemo.account.service.AccountService;
import sesac.sesaccdemo.auth.dto.LoginRequest;
import sesac.sesaccdemo.account.exception.AccountErrorCode;
import sesac.sesaccdemo.account.exception.AccountException;
import sesac.sesaccdemo.auth.util.JwtUtil;
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("accounts")
public class AccountController {
    private final JwtUtil jwtUtil;
    private final AccountService accountService;
    private final AccountBindHandler bindHandler;

    @PostMapping("signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        bindHandler.signupRequest(bindingResult);
        accountService.saveStudent(signupRequest);

        return ResponseEntity.ok().build();
    }

    @PostMapping("signup/check-email")
    public ResponseEntity<Void> checkEmail(@Valid @RequestBody EmailCheckRequest request) {
        accountService.emailCheck(request.email());

        return ResponseEntity.ok().build();
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // TODO: 서비스로 로직 이동
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", loginRequest.username());
        claims.put("nickname", "nickname");
        claims.put("role", "MANAGER");

        String token = jwtUtil.generateToken(claims, 1);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(token);
    };

    @GetMapping
    public ResponseEntity<String> getUser() {
        throw new AccountException(AccountErrorCode.NO_COURSE);
    }

}
