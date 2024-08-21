package sesac.sesaccdemo.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sesac.sesaccdemo.auth.dto.LoginRequest;
import sesac.sesaccdemo.auth.exception.AccountErrorCode;
import sesac.sesaccdemo.auth.exception.AccountException;
import sesac.sesaccdemo.auth.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("accounts")
public class AccountController {
    private final JwtUtil jwtUtil;

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
        throw new AccountException(AccountErrorCode.NO);
    }

}
