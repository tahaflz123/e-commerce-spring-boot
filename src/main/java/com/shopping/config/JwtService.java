package com.shopping.config;

import java.security.Key;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.shopping.entity.user.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService{
	
	
	public String createToken(User user) {
		Date creatingDate = new Date();
		return Jwts.builder().setSubject(user.getUsername())
		.setIssuedAt(creatingDate)
		.setExpiration(new Date(creatingDate.getTime() + (24*60*60*1000)))
		.signWith(this.getSignKey(), SignatureAlgorithm.HS256).compact();
		
	}
	
	
	public Authentication parseToken(String token) {
		String subject;
		if(token == null) {
			return null;
		}
		if(token.startsWith("Bearer")) {
			subject = Jwts.parserBuilder().setSigningKey(this.getSignKey()).build().parseClaimsJws(token.substring(7)).getBody().getSubject();
		}else {
			subject = Jwts.parserBuilder().setSigningKey(this.getSignKey()).build().parseClaimsJws(token).getBody().getSubject();
		}
		return new UsernamePasswordAuthenticationToken(subject, null, null);
		 
	}
	
	
	public Key getSignKey() {
		return Keys.hmacShaKeyFor(new String("mysecurekeymysecurekeymysecurekeymysecurekeymysecurekeymysecurekey").getBytes());
	}
	
	

}
