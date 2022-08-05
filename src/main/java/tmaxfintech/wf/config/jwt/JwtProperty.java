package tmaxfintech.wf.config.jwt;

public class JwtProperty {

    public static final String SECRET = "wfbackendwf";
    public static final int EXPRIATION_TIME = 60000 * 60 * 24;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
