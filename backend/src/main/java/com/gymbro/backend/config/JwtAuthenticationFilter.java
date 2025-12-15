package com.gymbro.backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    // --- CONSTRUTOR MANUAL (Sem Lombok) ---
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Procurar o cabeçalho "Authorization" no pedido
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // 2. Se não tiver cabeçalho ou não começar por "Bearer ", deixa passar (o SecurityConfig decide se bloqueia ou não)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extrair o Token (Tirar a parte "Bearer ")
        jwt = authHeader.substring(7);

        // 4. Extrair o email de dentro do Token (usando a nossa "Máquina de Chaves")
        userEmail = jwtService.extractUsername(jwt);

        // 5. Se encontrámos um email e o utilizador AINDA NÃO está autenticado no contexto...
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            // ...vamos buscar os dados dele à BD
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // 6. Verificar se o token é válido
            if (jwtService.isTokenValid(jwt, userDetails.getUsername())) {
                
                // 7. Criar o objeto de autenticação oficial do Spring
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 8. FINALMENTE: Marcar o utilizador como "Autenticado" para este pedido!
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        // 9. Passar a batata quente ao próximo filtro
        filterChain.doFilter(request, response);
    }
}