package com.example.finals.security;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;

public class GenerateSecretKey {
    public static void main(String[] args) {
        String secretKey = Base64.getEncoder().encodeToString(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
        System.out.println("Generated Secret Key: " + secretKey);
    }
}
