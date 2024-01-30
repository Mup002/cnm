package com.example.cinema.config.filter;

import com.example.cinema.utils.BaseResponse;
import com.example.cinema.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private JwtTokenProvider jwtTokenProvider;
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);

        try{
            if(token != null && jwtTokenProvider.validateToken(token,jwtTokenProvider.getAccessKey())){
                UserDetails userDetails = userDetailsService.loadUserByUsername(
                        jwtTokenProvider.getUsername(token,jwtTokenProvider.getAccessKey())
                );
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,null, userDetails.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            BaseResponse resDto = new BaseResponse();
            resDto.setStatusCode(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
            resDto.setMessage(e.getLocalizedMessage());

            String json = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(resDto);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(json);
            return;
        }
        filterChain.doFilter(request,response);
    }
}
