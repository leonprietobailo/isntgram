package com.leonprieto.ig.isntgram_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
public class JwtTokenUtil {

  private static final String SECRET = "isntgram";
  private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);
  private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hour.

  public static String generateToken(String id) {
    return JWT.create().withSubject(id).withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(ALGORITHM);
  }
  
  public static String validateToken(String token) {
    try {
      JWTVerifier verifier = JWT.require(ALGORITHM).build();
      final DecodedJWT decodedJwt = verifier.verify(token);
      return decodedJwt.getSubject();
    } catch (JWTVerificationException e) {
      return null;
    }
  }
}
