package sesac.sesaccdemo.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sesac.sesaccdemo.auth.dto.LoginRequest;
import sesac.sesaccdemo.auth.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("accounts")
public class AccountController {
    private final JwtUtil jwtUtil;

    @PostMapping("login2")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", loginRequest.getUsername());

        String token = jwtUtil.generateToken(claims, 1);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(token);
    };


}
