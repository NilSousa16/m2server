package br.ufba.dcc.wiser.m2server.routes;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.ufba.dcc.wiser.m2model.model.Gateway;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

/**
 * http://localhost:8181/cxf/m2fot/fot-gateway/add
 */

@Path("/fot-gateway")
@Api(value = "/fot-gateway")
public class GatewayRoutes {
	
	@POST
	@Path("/")
	public Response add(String value) {
		System.out.println(value);
		
		// adicionar novo gateway e/ou setar como ativo
		
		return null;
	}
	
	@PUT
	@Path("/")
	public void update(String value) {
		System.out.println(value);
		
		// atualiza informações de gateway ativos
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@ApiParam(value = "ID to delete", required = true) @PathParam("id") String id) {
		System.out.println("Id for delete: " + id);

		// busca um gateway para deleção (neste caso para desativação já que ele pode ser ativo novamente)
		// na deleção deverá haver um time para identificar que umm gateway está desconectado
		// o time deve ser uma rotina determinada no apache camel a ser disparada em períodos específicos
		
		return null;
	}
	
	@GET
    @Path("{id}")
	public Response find(@ApiParam(value = "ID to search", required = true) @PathParam("id") String id) {
		System.out.println("Id for search: " + id);
		
		// busca todas as informações de um gateway específico 
		
		return null;
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Collection<Gateway> getGateway(){
		System.out.println("Request list gateways");
		
		// busca uma lista com as informações de todos os gateways
		// deverá existir uma opção para um conjunto menor de informações por gateway
		
		return null;
	}
	
	
}
