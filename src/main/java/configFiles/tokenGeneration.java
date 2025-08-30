package configFiles;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.testng.annotations.Test;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class tokenGeneration {


        public static String generateJwtToken(String secretKeyString, SignatureAlgorithm algorithm, Map<String, Object> claims) {
            Key secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes()); // For HMAC algorithms

            Date now = new Date();
            Date expiration = new Date(now.getTime() + 3600 * 1000); // 1 hour expiration

            return Jwts.builder()
                    .setClaims(claims) // Set your payload claims
                    .setIssuedAt(now)
                    .setExpiration(expiration)
                    .signWith(secretKey, algorithm) // Sign with the secret key and algorithm
                    .compact();
        }


        public static String JWTToken() {
            String secret = configReader.getProperty("APIkeys");

            SignatureAlgorithm algo = SignatureAlgorithm.HS256;

            Map<String, Object> userClaims = new HashMap<>();
            userClaims.put("userId", "123");

            return generateJwtToken(secret, algo, userClaims);

        }


    }

