package com.springboot.sv.optica.security;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class TokenJwtConfig {

    //Archivo para poder llamar la llave
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build(); //Permite Generar la llave
    public static final String PREFIX_TOKEN = "Bearer "; //String por Default
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
}
