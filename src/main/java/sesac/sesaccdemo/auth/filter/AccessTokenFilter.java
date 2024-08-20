package sesac.sesaccdemo.auth.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sesac.sesaccdemo.auth.exception.AccessTokenException;
import sesac.sesaccdemo.auth.util.JwtUtil;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccessTokenFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    private String passPath;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
//        var path = request.getRequestURI();
//
//        if (path.equals(passPath)) {
//            filterChain.doFilter(request, response);
//            return;
//        }

        try {
            Map<String, Object> claim = validateAccessToken(request);
//            saveClaimToAuthentication(claim, request);
            filterChain.doFilter(request, response);
        } catch (AccessTokenException accessTokenException) {
//            accessTokenException.sendResponseError(response);
        }
    }

    private Map<String, Object> validateAccessToken(HttpServletRequest request) throws AccessTokenException {
        String headerStr = request.getHeader("Authorization");

        if (headerStr == null || headerStr.length() < 8) {
//            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.UNACCEPT);
        }

        String tokenType = headerStr.substring(0, 6);
        String tokenStr = headerStr.substring(7);

        if (tokenType.equalsIgnoreCase("Bearer") == false) {
//            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADTYPE);
        }

        try {
            return jwtUtil.validateToken(tokenStr);
        } catch (MalformedJwtException malformedJwtException) {
            log.error("MalFormedJwtException-------------");
//            throw new AccessTokenException((AccessTokenException.TOKEN_ERROR.MALFORM));
        } catch (SignatureException signatureException) {
            log.error("SignatureException-------------");
//            throw new AccessTokenException((AccessTokenException.TOKEN_ERROR.BADSIGN));
        } catch (ExpiredJwtException expiredJwtException) {
            log.error("ExpiredJwtException-------------");
//            throw new AccessTokenException((AccessTokenException.TOKEN_ERROR.EXPIRED));
        }
        return null;
    }

//    private void saveClaimToAuthentication(Map<String, Object> claim, HttpServletRequest request) {
//        String token = request.getHeader("Authorization").substring(7);  // "Bearer " 제거
//
//        // 권한 정보 생성 (예시)
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if (claim.containsKey("role")) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + claim.get("role")));
//        }
//
//        // JwtAuthenticationToken 생성
//        JwtAuthenticationToken authentication = new JwtAuthenticationToken(token, claim, authorities);
//
//        // SecurityContext에 Authentication 설정
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
}