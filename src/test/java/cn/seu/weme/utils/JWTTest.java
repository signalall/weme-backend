package cn.seu.weme.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

/**
 * Created by LCN on 2017-1-4.
 */
public class JWTTest {

    @Test
    public void testCreateAndSignToken() throws UnsupportedEncodingException {
        Key key = MacProvider.generateKey();

        System.out.println(key);

        String compactJws = Jwts.builder()
                .setSubject("lcn").signWith(SignatureAlgorithm.HS512, key)
                .compact();

        System.out.println(compactJws);

        System.out.println(Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws)
                .getBody().getSubject());

    }


    //Sample method to construct a JWT
    private String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("TrSggG0JdADdCT6T+oyZ2Q==s");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    private void parseJWT(String jwt) {

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("TrSggG0JdADdCT6T+oyZ2Q=="))
                .parseClaimsJws(jwt).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
    }


    @Test
    public void testJWT() {
        String token = createJWT("1", "lcn", "weme", 5000);
        System.out.println("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNDgzNTIwMjg3LCJzdWIiOiJ3ZW1lIiwiaXNzIjoibGNuIiwiZXhwIjoxNDgzNTIwMjkyfQ.ztU1UbpEYJJfUCPq8fWmAukK9LmK5mjbhILWoCSMfNc\n");
        parseJWT(token);
    }


    @Test
    public void generateKey() {
        SecretKey secretKey = null;
        try {
            secretKey = KeyGenerator.getInstance("AES").generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // get base64 encoded version of the key
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println(encodedKey);
    }


    @Test
    public void testDecodeToken() {

    }
}
