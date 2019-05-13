package br.com.gilbertopapa.wsjwt.webservice.resource;


import br.com.gilbertopapa.wsjwt.domian.Usuario;
import br.com.gilbertopapa.wsjwt.exception.UnauthenticatedException;
import br.com.gilbertopapa.wsjwt.service.UsuarioService;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginJWTResource {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response autenticarUsuario(@FormParam("login") String login, @FormParam("password") String password){

        Usuario  usuario = validarCredenciais(login,password);

        String token = TokenJWTUtil.gerarToken(usuario.getUsername());

    return Response.ok().header("Authorization","Bearer" + token).build();
    }


    public  Usuario validarCredenciais(){
        UsuarioService usuarioService =  new UsuarioService();

        Usuario usuario = usuarioService.recuperarUsuarioComLoginESenha(login,password);


        if (usuario == null){
        throw new UnauthenticatedException("Usuário não autenticado: nome do usuário ou senha inválidos!");

        return usuario;
        }
    }


}
