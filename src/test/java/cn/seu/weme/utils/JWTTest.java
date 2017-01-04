package cn.seu.weme.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.Key;

/**
 * Created by LCN on 2017-1-4.
 */
public class JWTTest {

    @Test
    public void testCreateAndSignToken() throws UnsupportedEncodingException {
        Key key = MacProvider.generateKey();

        System.out.println(key);

        String compactJws = Jwts.builder()
                .setSubject("lcn")
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        System.out.println(compactJws);

        Jws<Claims> claims =  Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws);

    }


    @Test
    public void testDecodeToken(){

    }
}
