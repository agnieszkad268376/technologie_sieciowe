package org.example.libraryDataBase.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JWTTokenFilter extends OncePerRequestFilter {
    private String key;

    public JWTTokenFilter(String key){
        this.key = key;
    }
    @Override
    // cała logika filter domyślnie wyrzuca błędy
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // odczytujemy header z zapytania
        // sprawdzamy header
        // jak tak to odczytujemy token i jego zawartość
        // tworzymy sprawdzenie użytkowniki żeby go dopuścić do dobrym metod
        String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
        // jeżeli token jest zły użytkownik nie może się dostać do części endpointów
         if (headerAuth!=null && headerAuth.startsWith("Bearer ")){
             // rozdzielamy po spacji i bierzemy dugi element z tablicy
             String token = headerAuth.split(" ")[1];
             Claims claims = Jwts.parser()
                     .setSigningKey(key)
                     .build()
                     .parseSignedClaims(token)
                     .getPayload();
             // odczytanie upoważnień użytkownika z tokenu
             // to się zapisze w tokenie przy tworzniu
             String userId = ((Integer) claims.get("userId")).toString() ;
             String role = (String) claims.get("role");

             Authentication authentication = new UsernamePasswordAuthenticationToken(
                     userId, null, List.of(new SimpleGrantedAuthority(role)));

             SecurityContextHolder.getContext().setAuthentication(authentication);

         } else {
             SecurityContextHolder.getContext().setAuthentication(null);
         }
        // to za każdym razem na samym końcu musi być
        // bo inaczej zapytanie nie zostanie dokładnie
        filterChain.doFilter(request, response);
    }
}
