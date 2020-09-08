package br.ufba.dcc.wiser.m2server.routes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.ufba.dcc.wiser.m2model.model.Gateway;
import br.ufba.dcc.wiser.m2server.service.GatewayService;
import io.swagger.annotations.Api;

/**
 * http://localhost:8181/cxf/m2fot/fot-gateway/
 */

@Path("/fot-gateway")
@Api(value = "/fot-gateway")
public class GatewayRoutes {
	
	GatewayService gatewayService;
	
	Gson gson = new Gson();

	public GatewayService getGatewayService() {
		return gatewayService;
	}

	public void setGatewayService(GatewayService gatewayService) {
		this.gatewayService = gatewayService;
	}

	@POST
	@Path("/")
	public Response add(String value) {
		
		Gateway gateway = gson.fromJson(value, Gateway.class);
				
		return gatewayService.add(gateway);
	}

	@PUT
	@Path("/")
	public Response update(String value) {
		System.out.println(value);
		
		Gateway gateway = gson.fromJson(value, Gateway.class);
		
		return gatewayService.update(gateway);
	}

	@DELETE
	@Path("/remove")
	public Response delete(@QueryParam("mac") String mac) {
		
		return gatewayService.delete(mac);
	}

	@GET
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@QueryParam("mac") String mac) {
		
		return gatewayService.find(mac);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listGateways() {
		
		return gatewayService.getList();
	}

}
