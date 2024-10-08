package sesac.sesaccdemo.auth.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import sesac.sesaccdemo.auth.dto.AuthPrincipal;
import sesac.sesaccdemo.auth.dto.PrincipalRecord;

import java.util.Map;

@Component
public class AuthPrincipalArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AuthPrincipal.class) != null
                && parameter.getParameterType().equals(PrincipalRecord.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            Map<String, Object> principal = (Map<String, Object>) authentication.getPrincipal();

            PrincipalRecord record = new PrincipalRecord(
                    (String) principal.get("nickname"),
                    (String) principal.get("role")
            );

            return record;
        }
        return null;
    }
}
