package antifraud.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@Service
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        if(response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            response.setStatus(SC_FORBIDDEN);
        }
        if(response.getStatus() == HttpServletResponse.SC_OK) {
            response.setStatus(SC_UNAUTHORIZED);
        }
        response.sendError(response.getStatus(), authException.getMessage());
    }


}