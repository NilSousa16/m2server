package br.ufba.dcc.wiser.m2server.routes;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import br.ufba.dcc.wiser.m2model.model.Gateway;
import br.ufba.dcc.wiser.m2server.exception.GatewayException;
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

		try {
			gateway = gatewayService.add(gateway);

			return Response.status(Status.OK).build();
		} catch (Exception e) {
			GatewayException gatewayException = new GatewayException();

			gatewayException.setError("Register");
			gatewayException.setMessage("Error in information persistence");

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(gatewayException))
					.type(MediaType.APPLICATION_JSON).build();
		}

	}

	@PUT
	@Path("/")
	public Response update(String value) {

		Gateway gateway = gson.fromJson(value, Gateway.class);

		try {
			gateway = gatewayService.update(gateway);

			return Response.status(Status.OK).build();
		} catch (Exception e) {
			GatewayException gatewayException = new GatewayException();

			gatewayException.setError("Update");
			gatewayException.setMessage("Error updating data");

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(gatewayException))
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

	@DELETE
	@Path("/remove")
	public Response delete(@QueryParam("mac") String mac) {

		try {
			gatewayService.delete(mac);
			return Response.status(Status.NO_CONTENT).build();
		} catch (Exception e) {
			GatewayException gatewayException = new GatewayException();

			gatewayException.setError("Delete");
			gatewayException.setMessage("Error in deleting information");

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(gatewayException))
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

	@GET
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@QueryParam("mac") String mac) {

		try {
			Gateway gatewayFound = gatewayService.find(mac);

			return Response.status(Status.OK).entity(gson.toJson(gatewayFound)).type(MediaType.APPLICATION_JSON)
					.build();
		} catch (Exception e) {
			GatewayException gatewayException = new GatewayException();

			gatewayException.setError("Find");
			gatewayException.setMessage("Error fetching information");

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(gatewayException))
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listGateways() {

		try {
			List<Gateway> listGateway = gatewayService.getList();

			return Response.status(Status.OK).entity(gson.toJson(listGateway)).
					type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			GatewayException gatewayException = new GatewayException();

			gatewayException.setError("List");
			gatewayException.setMessage("Information listing error");

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(gatewayException))
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

}
