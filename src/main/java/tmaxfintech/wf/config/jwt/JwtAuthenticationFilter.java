package tmaxfintech.wf.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import tmaxfintech.wf.config.auth.PrincipalDetails;
import tmaxfintech.wf.domain.user.entity.User;
import tmaxfintech.wf.util.jwt.JwtUtility;
import tmaxfintech.wf.util.response.DefaultResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final AuthenticationManager authenticationManager;

//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private JwtUtility jwtUtility;

    private final ObjectMapper objectMapper;

    private final JwtUtility jwtUtility;
//
//    public JwtAuthenticationFilter(String defaultFilterProcessUrl, AuthenticationManager authenticationManager) {
//        super(defaultFilterProcessUrl);
//        this.authenticationManager = authenticationManager;
//    }

    public JwtAuthenticationFilter(String defaultFilterProcessUrl, AuthenticationManager authenticationManager, ObjectMapper objectMapper, JwtUtility jwtUtility) {
        super(defaultFilterProcessUrl);
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        this.jwtUtility = jwtUtility;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user = objectMapper.readValue(request.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        String jwtToken = jwtUtility.createJwtToken(principalDetails.getUsername(), principalDetails.getUser().getId());
        response.addHeader(JwtProperty.HEADER_STRING, JwtProperty.TOKEN_PREFIX + jwtToken);
        OutputStream os = response.getOutputStream();
        objectMapper.writeValue(os, DefaultResponse.response(HttpStatus.OK.value(), "로그인 성공", principalDetails.getUser().toLoginResponseDto()));
        os.flush();
    }
}
