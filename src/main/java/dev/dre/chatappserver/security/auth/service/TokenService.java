package dev.dre.chatappserver.security.auth.service;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@Service
public class TokenService {
    public String genrateAccessToken(String userId, String sessionId, String expireAt) {
        //todo add role for the user in include it in the token
        try {
            String SECRET = "super-long-random-secret-key-256-bits"; // todo change this

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(expireAt);
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(userId)
                    .claim("sessionId", sessionId)
                    .expirationTime(date)
                    .issueTime(new Date())
                    .build();
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
            SignedJWT jwt = new SignedJWT(header, claims);
            JWSSigner signer = new MACSigner(SECRET);

            jwt.sign(signer);

            return jwt.serialize();

        } catch (Exception e) {
            log.error("error parsing date", e);
        }

        return userId + sessionId + expireAt; // todo change this
    }

    public boolean validateToken(String token) {
        if (token.isBlank()) return false;

        return token.equals("token");
    }

    public String extractUserId(String token) {
        return "1";
    }

    public Boolean isTokenExpired(String token) {
        return false;
    }
    //todo
    // - JWT verification
    // - DB lookup
    // - Expiration check
}
