package com.example.cinema.jwt;

import com.example.cinema.exception.ExceptionResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static io.jsonwebtoken.security.Keys.secretKeyFor;


@Component
@Slf4j
public class JwtTokenProvider {
    @Autowired
    private UserDetailsService userDetailsService;
    public Key getAccessKey() {
        byte[] key = Decoders.BASE64.decode("404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970");
        return Keys.hmacShaKeyFor(key);
    }
    public Key getRefreshKey() {
        byte[] key = Decoders.BASE64.decode("MwmENr347b7VPj6Z7Sc/qw0Q/MDtrOVqrFZig/zL7RvPbVd/eOhSWrCRAYdOLz8ZX0Lc4DSSsLmiVegOaSeSnZyHLTs6s6E+tQ6QONzibPMiou23OIpgCjdeFZBCKjoVO7WRdwPpU1tB8tTucSzUYtLOn8ZMUVZB3mySXRwsEdO+5NP1PiKVji8Vy8b+7mcSKe3enOZW1hmp444jfUaWEVJFmcbhgPXaQ7Ds0hJnHVXqPG3X0p8oKrVauwuZl5FJIGTPO5DGfCELV4o2oWF7jGQGwMxAQlX5OPwGSdwXn5+hbILi+6WfZxF5bO2yHfKZ3GJjGdA0xFf30OuM/jp0TAVJShLEOYrVr4vP0iTCQ6g9rICNyAc7KLXAVQtHy7XT2vbiS4aNC4miN5MOPTPSf3OezfT99S0kMD6s/4oF38hKlxZhCILyvzdV924on31ZNpzQEJZMnkqR2cjr7O/gM6Nf6Hk2vvYhnWD+YHthnYPPFhaM4ii/1+4d8mVEuXfropRAbg1ggxOo3XoOAO7pP3cZNMJKVK1jW4MPTKJbgR0m8vQODGyhWxAd9mSAsPMqTyVLXwcApzxLqJvl9xIi3yLRa/U+lgtRzUJ2dE5k4Wy18eUckDKj45tJuiVUFeZipyeqZ/mi7zEl+zS89LjoRHslYoFN8JB7PfnACx1Z2krLGbinzmsj5OFh+ccNBtE1");
        return Keys.hmacShaKeyFor(key);
    }


    public String createToken(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 60000);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(getAccessKey(),SignatureAlgorithm.HS256)
                .compact();
    }
    public String refreshToken(Authentication authentication){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 1200000);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(getRefreshKey(),SignatureAlgorithm.HS512)
                .compact();
    }
    public String refreshTokenIfExpired(String refreshToken){
        log.error(refreshToken);
        String username = getUsername(refreshToken,getRefreshKey());
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails != null){
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + 60000);
            return Jwts.builder()
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(expiryDate)
                    .signWith(getAccessKey(),SignatureAlgorithm.HS256)
                    .compact();
        }else{
            log.error("user not found");
            throw new ExceptionResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()),"User not found? " + username);
        }
    }
    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token, Key key){
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
            throw new ExceptionResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()), "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
            throw new ExceptionResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()), "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
            throw new ExceptionResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()),"Unsupported JWT Token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
            throw new ExceptionResponse(String.valueOf(HttpStatus.UNAUTHORIZED.value()),"JWT claims string is empty");
        } catch (SignatureException ex) {
            log.error("there is an error with the signature of you token ");
            throw new ExceptionResponse(HttpStatus.BAD_REQUEST.toString(),"there is an error with the signature of you token ");
        }catch (Exception e){
            throw new ExceptionResponse(HttpStatus.BAD_REQUEST.toString(),e.getMessage());
        }
    }

    public String getUsername(String token,Key key) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public Date getExpiration(String token, Key key){
        Date expiryDate =  Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
//
//        String formattedExpiration = sdf.format(expiryDate);
//        return formattedExpiration;
        return expiryDate;
    }
}
