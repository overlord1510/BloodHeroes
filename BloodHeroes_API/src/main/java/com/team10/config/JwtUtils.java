package com.team10.config;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.team10.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	@Value("${jwt.token.expiration}")
	private long EXPIRATIONTIME;

	@Value("${jwt.secret.key}")
	private String SECRET;

	private SecretKey getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private Claims extractAllClaims(String jwtToken) {
		//@formatter:off
		return Jwts
					.parser()
					.verifyWith(getSigningKey())
					.build()
					.parseSignedClaims(jwtToken)
					.getPayload();
		//@formatter:on
	}
	private <T> T extractClaims(String jwtToken, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(jwtToken);
		return claimsResolver.apply(claims);
	}
	
	
	public String extractUsername(String jwtToken) {
		return extractClaims(jwtToken, Claims::getSubject);
	}
	
	public boolean isTokenvalid(String jwtToken,UserDetails userDetails) {
		String username=extractUsername(jwtToken);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken));
	}

	private boolean isTokenExpired(String jwtToken) {
		return extractExpiration(jwtToken).before(new Date());
	}

	private Date extractExpiration(String jwtToken) {
		return extractClaims(jwtToken, Claims::getExpiration);
	}
	
	public String generateToken(User user) {
		return generateToken(new HashMap<String,Object>(),user);
	}

	private String generateToken(HashMap<String, Object> extraClaims, User user) {
		//@formatter:off
		return Jwts
					.builder()
					.claims(extraClaims)
					.subject(user.getEmail())
					.issuedAt(new Date(System.currentTimeMillis()))
					.expiration(new Date(System.currentTimeMillis()+EXPIRATIONTIME))
					.signWith(getSigningKey())
					.compact();
		//@formatter:on
	}

}
