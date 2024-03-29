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

import br.ufba.dcc.wiser.m2model.model.Device;
import br.ufba.dcc.wiser.m2model.model.DeviceStatus;
import br.ufba.dcc.wiser.m2model.model.DeviceStatusTransfer;
import br.ufba.dcc.wiser.m2server.exception.DeviceException;
import br.ufba.dcc.wiser.m2server.service.DeviceStatusService;
import io.swagger.annotations.Api;

@Path("/fot-device-status")
@Api(value = "/fot-device-status")
public class DeviceStatusRoutes {

	DeviceStatusService deviceStatusService;

	Gson gson = new Gson();

	public DeviceStatusService getDeviceStatusService() {
		return deviceStatusService;
	}

	public void setDeviceStatusService(DeviceStatusService deviceStatusService) {
		this.deviceStatusService = deviceStatusService;
	}

	@POST
	@Path("/")
	public Response add(String value) {
		// DeviceStatus deviceStatus = gson.fromJson(value, DeviceStatus.class);

		// Conversion to object that is stored
		DeviceStatusTransfer deviceStatusTransfer = gson.fromJson(value, DeviceStatusTransfer.class);

		DeviceStatus deviceStatus = new DeviceStatus();

		deviceStatus.setDate(deviceStatusTransfer.getDate());
		deviceStatus.setSituation(deviceStatusTransfer.getSituation());

		Device device = new Device();
		device.setId(deviceStatusTransfer.getIdDevice());
		deviceStatus.setDevice(device);
		
		try {
			deviceStatus = deviceStatusService.addDevice(deviceStatus);
			
			return Response.status(Status.OK).build();
		} catch (Exception e) {
			DeviceException deviceException = new DeviceException();

			deviceException.setError("Register");
			deviceException.setMessage("Error in persisting device status information");

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(deviceException))
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

	@GET
	@Path("/find/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findById(@QueryParam("id") String id) {
		try {
			List<DeviceStatus> deviceStatusList = deviceStatusService.findById(id);

			return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(gson.toJson(deviceStatusList)).type(MediaType.APPLICATION_JSON)
					.build();
		} catch (Exception e) {
			DeviceException deviceException = new DeviceException();

			deviceException.setError("Find by Id");
			deviceException.setMessage("Error fetching device status information " + id);

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(gson.toJson(deviceException))
					.type(MediaType.APPLICATION_JSON).build();
		}
	}
	
	@GET
	@Path("/find/gateway")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByGateway(@QueryParam("gatewayMac") String gatewayMac) {
		try {
			List<DeviceStatus> deviceStatusList = deviceStatusService.findByGateway(gatewayMac);

			return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")					
					.entity(gson.toJson(deviceStatusList)).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			DeviceException deviceException = new DeviceException();

			deviceException.setError("Find by Gateway");
			deviceException.setMessage("Error fetching device status information " + gatewayMac);

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(gson.toJson(deviceException)).type(MediaType.APPLICATION_JSON).build();
		}
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListAll() {
		try {
			List<DeviceStatus> listDeviceStatus = deviceStatusService.getListAllStatus();

			return Response.status(Status.OK).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")	
					.entity(gson.toJson(listDeviceStatus)).type(MediaType.APPLICATION_JSON).build();
		} catch (Exception e) {
			DeviceException deviceException = new DeviceException();

			deviceException.setError("List");
			deviceException.setMessage("Devices status listing error");

			System.out.println("Log: " + e.toString());

			return Response.status(Status.INTERNAL_SERVER_ERROR).header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Credentials", "true")
					.header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
					.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
					.entity(gson.toJson(deviceException)).type(MediaType.APPLICATION_JSON).build();
		}
	}

}
