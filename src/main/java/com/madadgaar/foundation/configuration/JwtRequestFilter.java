package com.madadgaar.foundation.configuration;

import com.madadgaar.foundation.service.SimpleUserDetailsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.madadgaar.foundation.configuration.JwtUtils.getUserIdFromToken;
import static com.madadgaar.foundation.configuration.JwtUtils.validateJwtToken;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class JwtRequestFilter extends OncePerRequestFilter {

    @NonNull
    private final JwtUtils jwtUtils;

    @NonNull
    private final SimpleUserDetailsService simpleUserDetailsService;

//    @Value("${app.security.jwt.secret}")
    private String secretKey = "2B4B6250655368566D5971337436763979244226452948404D635166546A576E";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = parseJwt(request);
            if(jwt != null){
                validateJwtToken(jwt, secretKey);
                String userId = getUserIdFromToken(jwt, secretKey);
                UserDetails userDetails = simpleUserDetailsService.loadUserByUsername(userId);
                setUpUserAuthenticationObject(request, userDetails);
            }
        }catch (Exception e){
            logger.error("Cannot set user authentication: {}", e);
//            AppUtils.logRequestVitals(request, response);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request){
        String headerAuth = request.getHeader("Authorization");
        if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")){
            return headerAuth.substring(7);
        }
        return null;
    }

    private void setUpUserAuthenticationObject(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authenticationToken = buildAuthenticationToken(userDetails);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private UsernamePasswordAuthenticationToken buildAuthenticationToken(UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
