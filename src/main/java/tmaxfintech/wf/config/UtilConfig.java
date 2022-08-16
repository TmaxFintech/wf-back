package tmaxfintech.wf.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tmaxfintech.wf.util.jwt.JwtUtility;

@Configuration
public class UtilConfig {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public JwtUtility jwtUtility() {
        return new JwtUtility();
    }

}
