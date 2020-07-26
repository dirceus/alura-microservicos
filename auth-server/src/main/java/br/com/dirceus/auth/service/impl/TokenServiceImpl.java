package br.com.dirceus.auth.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.dirceus.auth.common.exception.BusinessException;
import br.com.dirceus.auth.dto.TokenInfoDTO;
import br.com.dirceus.auth.model.Usuario;
import br.com.dirceus.auth.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class TokenServiceImpl implements TokenService {

	private long tempoExpiracao = 1800000;
	
	@Value("${jwt.secret}")
	private String segredo;
	
	@Override
	public String gerarToken(Usuario usuario) {
		usuario.setSenha(null);
		String subject = new Gson().toJson(usuario);
		return Jwts.builder()
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + tempoExpiracao))
				.signWith(SignatureAlgorithm.HS256, segredo)
				.compact();
	}

	@Override
	public Boolean validarToken(String token) {
		Claims claims;
		try {
			claims = decodificarToken(token);
		}catch (Exception e) {
			return false;
		}
		if(claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
			return false;
		}
		return true;
	}

	private Claims decodificarToken(String token) {
		return Jwts.parser()
				.setSigningKey(segredo)
				.parseClaimsJws(token)
				.getBody();
	}
	
	
	@Override
	public TokenInfoDTO tokenInfo(String token) throws BusinessException {
		Claims claims;
		try {
			claims = decodificarToken(token);
			return new Gson().fromJson(claims.getSubject(), TokenInfoDTO.class);
		}catch (Exception e) {
			throw new BusinessException("Token inválido");
		}
	}	
	
	@Override
	public void destruirToken(String token) throws BusinessException{
		// TODO Auto-generated method stub

	}

	

}
