package br.com.gilbertopapa.wsjwt.webservice.resource;


import br.com.gilbertopapa.wsjwt.domian.Marca;
import br.com.gilbertopapa.wsjwt.service.MarcaService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/marcas")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@PermitAll
public class MarcaResource {

    private final MarcaService marcaService = new MarcaService();

    @POST
    public Response salvarMarca(Marca marca, @Context UriInfo uriInfo) {

        marcaService.salvarMarca(marca);

        return Response.created(uriInfo.getAbsolutePathBuilder()
                .path(Long.toString
                        (marca.getId())).build())
                .entity(marca)
                .build();
    }

    @GET
    public List<Marca>recuperarMarcas(@QueryParam("nome")
                                             @DefaultValue("") String nome) {
        return (nome.isEmpty()) ? marcaService.recuperarMarcas() :
                marcaService.recuperarMarcasPorNome(nome);
    }

    @GET
    @Path("")
    public Marca recuperarMarcaPorId(@PathParam("marcaId")
                                             long marcaId) {
        return marcaService.recuperarMarcaPorId(marcaId);
    }


    @PUT
    @Path("")
    @RolesAllowed({"ADMIN", "SUPERVISOR"})
    public Marca atualizarMarca(@PathParam("marcaId")
                                        long marcaId, Marca marca) {
        marcaService.atualizarMarca(marca, marcaId);
        return marca;
    }


    @DELETE
    @Path("")
    @RolesAllowed({"ADMIN"})
    public void excluirMarca(@PathParam("marcaId") long marcaId) {
        marcaService.excluirMarca(marcaId);
    }



    @Path("/produtos")
    public ProdutoResource obterProdutoResource() {
        return new ProdutoResource();
    }

}
