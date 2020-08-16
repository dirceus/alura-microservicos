package br.com.dirceus.meudoc.commons.security;

import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import br.com.dirceus.meudoc.commons.dto.UsuarioDTO;
import br.com.dirceus.meudoc.commons.exception.PermissionDeniedException;

@Aspect
@Component
public class VerificaPermissaoContidaNoRequest {

	@Autowired
	@Lazy
	private EurekaClient eurekaClient;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Before("@annotation(permissaoRequerida)")
    public void doBefore(PermissaoRequerida permissaoRequerida) throws PermissionDeniedException {
        HttpServletRequest servletRequest = currentRequest();
        if (Objects.isNull(servletRequest)) {
            throw new PermissionDeniedException("Sem Requisição HTTP");
        }
        
        String token= servletRequest.getHeader("Authorization");
        
        Permissao permissao = permissaoRequerida.value();
        HttpEntity<String> httpRequest = new HttpEntity<>(token);
		
        InstanceInfo instance = eurekaClient.getNextServerFromEureka("AUTH-SERVER", false);
        String url = instance.getHomePageUrl();
        
        ResponseEntity<UsuarioDTO> exchange = 
				restTemplate.exchange(url+"auth/usuario",
						HttpMethod.POST, httpRequest, UsuarioDTO.class);
		
		UsuarioDTO usuario = exchange.getBody();
		if (Objects.isNull(usuario)) {
	            throw new PermissionDeniedException("Token inválido");
	    }
		
		if(!usuario.getPermissoes().contains(permissao)) {
			throw new PermissionDeniedException("Não possui permissão");
		};
		 
    }

    /**
     * Return request current thread bound or null if none bound.
     *
     * @return Current request or null
     */
    private HttpServletRequest currentRequest() {
        // Use getRequestAttributes because of its return null if none bound
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Optional.ofNullable(servletRequestAttributes).map(ServletRequestAttributes::getRequest).orElse(null);
    }
	
	
}
