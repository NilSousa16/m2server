package br.ufba.dcc.wiser.m2server.service;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import br.ufba.dcc.wiser.m2dbX.service.GatewayServiceDB;
import br.ufba.dcc.wiser.m2model.model.Gateway;
import br.ufba.dcc.wiser.m2server.exception.GatewayException;

public class GatewayService {
	
	Gson gson = new Gson();

	GatewayServiceDB gatewayServiceDB;

	public GatewayServiceDB getGatewayServiceDB() {
		return gatewayServiceDB;
	}

	public void setGatewayServiceDB(GatewayServiceDB gatewayServiceDB) {
		this.gatewayServiceDB = gatewayServiceDB;
	}

	public Response add(Gateway gateway) {
		
		try {
			gatewayServiceDB.add(gateway);
			return Response.status(Status.OK).entity(gson.toJson(gateway)).type(MediaType.APPLICATION_JSON).build();
		} catch (PersistenceException e) {
			GatewayException gatewayException = new GatewayException();
			
			gatewayException.setError("Duplicate entity");
			gatewayException.setMessage("Add gateway route error");			
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(gson.toJson(gatewayException)).type(MediaType.APPLICATION_JSON)
					.build();
		}
		
	}

	public Response update(Gateway gateway) {

		gatewayServiceDB.update(gateway);
		
		return Response.status(Status.OK).entity(gson.toJson(gateway)).type(MediaType.APPLICATION_JSON).build();
	}

	public Response delete(String mac) {		
		
		gatewayServiceDB.delete(mac);
		
		return Response.status(Status.NO_CONTENT).type(MediaType.APPLICATION_JSON).build();
	}
	
	public Response find(String mac) {
		
		Gateway gatewayFound = gatewayServiceDB.find(mac); 
		
		return Response.status(Status.OK).entity(gson.toJson(gatewayFound)).type(MediaType.APPLICATION_JSON).build(); 
	}

	public Response getList() {

		List<Gateway> listGateway = gatewayServiceDB.list();

		for (Gateway gateway : listGateway) {
			System.out.println(gateway.getMac() + ", " + gateway.getIp() + ", " + gateway.getManufacturer() + ", "
					+ gateway.getHostName());
		}
		
		return Response.status(Status.OK).entity(gson.toJson(listGateway)).type(MediaType.APPLICATION_JSON).build(); 
	}
}
