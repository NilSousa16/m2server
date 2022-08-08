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

import br.ufba.dcc.wiser.m2model.model.Device;
import br.ufba.dcc.wiser.m2server.exception.DeviceException;
import br.ufba.dcc.wiser.m2server.exception.GatewayException;
import br.ufba.dcc.wiser.m2server.service.DeviceService;
import io.swagger.annotations.Api;

/**
 * http://localhost:8181/cxf/m2fot/fot-device/
 */

@Path("/fot-device")
@Api(value = "/fot-device")
public class DeviceRoutes {

	DeviceService deviceService;

	Gson gson = new Gson();

	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@POST
	@Path("/")
	public Response add(String value) {

		Device device = gson.fromJson(value, Device.class);

		try {
			device = deviceService.add(device);

			return Response.status(Status.OK).build();
		} catch (Exception e) {
			DeviceException deviceException = new DeviceException();

			deviceException.setError("Register");
			deviceException.setMessage("Error in information persistence device");

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(deviceException))
					.type(MediaType.APPLICATION_JSON).build();
		}

	}

	@PUT
	@Path("/")
	public Response update(String value) {
		// error - I can't update just one information
		Device device = gson.fromJson(value, Device.class);

		try {
			device = deviceService.update(device);

			return Response.status(Status.OK).build();
		} catch (Exception e) {
			DeviceException deviceException = new DeviceException();

			deviceException.setError("Update");
			deviceException.setMessage("Error updating device data");

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(deviceException))
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

	@DELETE
	@Path("/remove")
	public Response delete(@QueryParam("id") String id) {

		// update path - no include remove
		try {
			deviceService.delete(id);
			return Response.status(Status.NO_CONTENT).build();
		} catch (Exception e) {
			DeviceException deviceException = new DeviceException();

			deviceException.setError("Delete");
			deviceException.setMessage("Error in deleting device information");

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(deviceException))
					.type(MediaType.APPLICATION_JSON).build();
		}

	}

	@GET
	@Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@QueryParam("id") String id) {
		// update path - no include find
		try {

			Device deviceFound = deviceService.find(id);

			return Response.status(Status.OK).entity(gson.toJson(deviceFound)).type(MediaType.APPLICATION_JSON)
					.build();
		} catch (Exception e) {
			DeviceException deviceException = new DeviceException();

			deviceException.setError("Find");
			deviceException.setMessage("Error fetching device information ");

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(deviceException))
					.type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@GET
	@Path("/find/device_gateway")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listDevicesByGateway(@QueryParam("gatewayMac") String gatewayMac) {
		try {
			List<Device> listDevice = deviceService.getListByGateway(gatewayMac);

			return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(gson.toJson(listDevice)).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			GatewayException gatewayException = new GatewayException();

			gatewayException.setError("listDevicesByGateway");
			gatewayException.setMessage("Information listing device error ");

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
	public Response listDevices() {
		try {
			List<Device> listDevice = deviceService.getList();

			return Response.status(Status.OK).entity(gson.toJson(listDevice)).
					type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			GatewayException gatewayException = new GatewayException();

			gatewayException.setError("List");
			gatewayException.setMessage("Information listing device error ");

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(gatewayException))
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

}
