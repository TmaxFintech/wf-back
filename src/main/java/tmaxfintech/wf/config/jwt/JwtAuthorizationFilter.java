package tmaxfintech.wf.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import tmaxfintech.wf.config.auth.PrincipalDetails;
import tmaxfintech.wf.domain.user.entity.User;
import tmaxfintech.wf.exception.UserNotFoundException;
import tmaxfintech.wf.domain.user.repository.UserRepository;
import tmaxfintech.wf.util.jwt.JwtUtility;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

//    @Autowired
//    private JwtUtility jwtUtility;

    private final JwtUtility jwtUtility;

//    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
//        super(authenticationManager);
//        this.userRepository = userRepository;
//    }

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository, JwtUtility jwtUtility) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.jwtUtility = jwtUtility;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        String jwtHeader = request.getHeader(JwtProperty.HEADER_STRING);

        if (requestURI.startsWith("/users") && jwtHeader == null) response.sendError(401,"회원 인증 실패");

        if (jwtHeader == null || !jwtHeader.startsWith(JwtProperty.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = request.getHeader(JwtProperty.HEADER_STRING).replace(JwtProperty.TOKEN_PREFIX, "");
        String username = JWT.require(Algorithm.HMAC512(JwtProperty.SECRET)).build().verify(jwtToken).getClaim("username").asString();
        if (username != null) {
            User userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
            PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }
    }
}
