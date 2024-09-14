package com.springboot.sv.optica.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.sv.optica.security.SimpleGrantedAuthorityJsonCreator; //
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.springboot.sv.optica.security.TokenJwtConfig.*;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    public JwtValidationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException, java.io.IOException {

        String header = request.getHeader(HEADER_AUTHORIZATION);

        if(header == null || !header.startsWith(PREFIX_TOKEN)){
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(PREFIX_TOKEN,"");

        try {
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
            String username = claims.getSubject();

            Object authoritiesClaims = claims.get("authorities");

            System.out.println("claims = " + claims);
            System.out.println("authoritiesClaims = " + authoritiesClaims);

            /*String authoritiesJson = authoritiesClaims.toString()
                    .replace("authority=", "\"authority\":\"") // Añade comillas dobles alrededor de los valores
                    .replace(", ", "\", ")                        // Añade comillas dobles alrededor de las llaves
                    .replace("{", "{\"")                     // Asegurar que cada llave esté dentro de comillas
                    .replace("}", "\"}");*/
            // Convertir authoritiesClaims en un JSON válido.
            String authoritiesJson = convertToJsonString(authoritiesClaims);

            System.out.println("Formatted authoritiesJson = " + authoritiesJson);

            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                    new ObjectMapper()
                            .addMixIn(SimpleGrantedAuthority.class,
                                    SimpleGrantedAuthorityJsonCreator.class)
                            .readValue(authoritiesJson,SimpleGrantedAuthority[].class)
            );

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request,response);

        } catch (JwtException e){
            Map<String, String> body = new HashMap<>();
            body.put("error",e.getMessage());
            body.put("message","El token JWT es invalido!");

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(CONTENT_TYPE);
        }
    }

    private String convertToJsonString(Object authoritiesClaims) {
        // Aseguramos que authoritiesClaims es una lista o array y formateamos correctamente.
        // Si es una cadena como "[{authority=ROLE_ADMIN}]", la convertimos en JSON válido.
        String rawString = authoritiesClaims.toString(); // Esto convierte [{authority=ROLE_ADMIN}] a un string.

        // Reemplazos corregidos para formar JSON válido
        rawString = rawString.replace("authority=", "\"authority\":\"");  // Añadir comillas alrededor del valor de "authority"
        rawString = rawString.replace(", ", "\", ");                      // Asegurar que cada par clave-valor esté separado correctamente
        rawString = rawString.replace("{", "{\"");                       // Asegurar que cada llave esté dentro de comillas
        rawString = rawString.replace("}", "\"}");                      // Asegurar que cada cierre de llave esté dentro de comillas

        // Asegurar que las llaves y los valores están correctamente formateados en JSON.
        rawString = rawString.replace("=", ":"); // Cambiar '=' por ':' para hacer JSON válido.

        // Revisar y corregir posibles errores adicionales (como comillas dobles incorrectas).
        if (rawString.contains("{\"\"")) {
            rawString = rawString.replace("{\"\"", "{\"");  // Eliminar comillas adicionales
        }

        return rawString;
    }
}
