package antifraud.security;


import java.util.Base64;


public class TokenService {

    public static String getUsername(String base64Token) {
        String token = new String(Base64.getDecoder().decode(base64Token));

        return token.substring(0, token.lastIndexOf(':'));
    }

    public static String getPassword(String base64Token) {
        String token = new String(Base64.getDecoder().decode(base64Token));
        return token.substring(token.lastIndexOf(":") + 1);
    }
}
