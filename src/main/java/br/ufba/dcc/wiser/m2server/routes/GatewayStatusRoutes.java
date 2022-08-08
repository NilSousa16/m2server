package br.ufba.dcc.wiser.m2server.routes;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import br.ufba.dcc.wiser.m2model.model.GatewayStatus;
import br.ufba.dcc.wiser.m2server.exception.GatewayException;
import br.ufba.dcc.wiser.m2server.service.GatewayStatusService;
import io.swagger.annotations.Api;

/**
 * http://localhost:8181/cxf/m2fot/fot-gateway-status/
 */

@Path("/fot-gateway-status")
@Api(value = "/fot-gateway-status")
public class GatewayStatusRoutes {

	GatewayStatusService gatewayStatusService;

	Gson gson = new Gson();

	public GatewayStatusService getGatewayStatusService() {
		return gatewayStatusService;
	}

	public void setGatewayStatusService(GatewayStatusService gatewayStatusService) {
		this.gatewayStatusService = gatewayStatusService;
	}

	@POST
	@Path("/")
	public Response add(String value) {
		GatewayStatus gatewayStatus = gson.fromJson(value, GatewayStatus.class);

		try {
			gatewayStatus = gatewayStatusService.add(gatewayStatus);

			return Response.status(Status.OK).build();
		} catch (Exception e) {
			GatewayException gatewayException = new GatewayException();

			gatewayException.setError("Register");
			gatewayException.setMessage("Error in persisting gateway status information");

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(gatewayException))
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

	@GET
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByMac(@QueryParam("mac") String mac) {
		try {
			List<GatewayStatus> gatewayStatusList = gatewayStatusService.findByMac(mac);

			// Necessary - Access-Control-Allow-Origin
			return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(gson.toJson(gatewayStatusList)).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			GatewayException gatewayException = new GatewayException();

			gatewayException.setError("Find by Mac");
			gatewayException.setMessage("Error fetching gateway status information " + mac);

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(gson.toJson(gatewayException)).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListAll() {
		try {
			List<GatewayStatus> listGatewayStatus = gatewayStatusService.getListAll();

			return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(gson.toJson(listGatewayStatus)).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			GatewayException gatewayException = new GatewayException();

			gatewayException.setError("List");
			gatewayException.setMessage("Gateways status listing error");

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(gson.toJson(gatewayException)).type(MediaType.APPLICATION_JSON).build();
		}
	}

}
