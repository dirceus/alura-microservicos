package br.com.dirceus.auth.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.dirceus.auth.service.TokenService;
import br.com.dirceus.meudoc.commons.dto.UsuarioDTO;
import br.com.dirceus.meudoc.commons.exception.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class TokenServiceImpl implements TokenService {

	private long tempoExpiracao = 1800000;
	
	@Value("${jwt.secret}")
	private String segredo;
	
	@Override
	public String gerarToken(UsuarioDTO usuario) {
		String subject = new Gson().toJson(usuario);
		return Jwts.builder()
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + tempoExpiracao))
				.signWith(SignatureAlgorithm.HS256, segredo)
				.compact();
	}

	

	private Claims decodificarToken(String token) {
		return Jwts.parser()
				.setSigningKey(segredo)
				.parseClaimsJws(token)
				.getBody();
	}
	
	
	@Override
	public UsuarioDTO getUsuarioDoToken(String token) throws BusinessException {
		Claims claims;
		try {
			claims = decodificarToken(token);
			return new Gson().fromJson(claims.getSubject(),UsuarioDTO.class);
		}catch (Exception e) {
			throw new BusinessException("Token inválido");
		}
	}	
	
	@Override
	public void destruirToken(String token) throws BusinessException{
		// TODO Auto-generated method stub

	}

	

}
