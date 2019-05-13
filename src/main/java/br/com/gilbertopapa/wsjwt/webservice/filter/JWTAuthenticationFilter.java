package br.com.gilbertopapa.wsjwt.webservice.filter;

import br.com.gilbertopapa.wsjwt.utils.UserDetails;
import br.com.gilbertopapa.wsjwt.webservice.exception.UnauthenticatedException;
import br.com.gilbertopapa.wsjwt.webservice.jwt.JWTSecurityContext;
import br.com.gilbertopapa.wsjwt.webservice.jwt.KeyGenerator;
import br.com.gilbertopapa.wsjwt.webservice.jwt.TokenJWTUtil;
import br.com.gilbertopapa.wsjwt.webservice.resource.LoginJWTResource;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.security.Key;
import java.util.List;

public class JWTAuthenticationFilter implements ContainerRequestFilter {

    private KeyGenerator keyGenerator = new KeyGenerator();

    @Context
    private UriInfo uriInfo;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.contains("Bearer")){
            String token = authorizationHeader.substring("Bearer".length()).trim();

            Key key = keyGenerator.generateKey();

            if (TokenJWTUtil.tokenValido(token, key)){
                String nome = TokenJWTUtil.recuperarNome(token, key);
                List<String> regras = TokenJWTUtil.recuperarRoles(token, key);
                UserDetails userDetails = new UserDetails(nome ,regras);


                boolean secure = requestContext.getSecurityContext().isSecure();
                requestContext.setSecurityContext(new JWTSecurityContext(userDetails,secure));

                return;

            }

        }else if (acessoParaLoginNaAPI(requestContext))return;

        else if (acessoParaMetodosDeConsulta(requestContext))return;

        throw new UnauthenticatedException("Token inválido/expirado ou usuário não autenticado!");

    }

    private boolean acessoParaLoginNaAPI(ContainerRequestContext requestContext) {
        return requestContext.getUriInfo().getAbsolutePath().toString()
                .equals(uriInfo.getBaseUriBuilder().path
                        (LoginJWTResource.class).build().toString());

    }


    private boolean acessoParaMetodosDeConsulta(ContainerRequestContext requestContext) {
        return "GET".equalsIgnoreCase(requestContext.getMethod());
    }

}
